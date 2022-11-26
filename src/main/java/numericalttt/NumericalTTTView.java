package numericalttt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;


import boardgame.ui.PositionAwareButton;
import boardgame.GameUI;
import player.Player;
import game.SaveToFile;
import boardgame.Saveable;





public class NumericalTTTView extends JPanel {
private boolean odd = true;
private String player = "";
private PositionAwareButton[][] buttons;
private NumericalTTTGame game;
private GameUI root;
private JLabel messageLabel;
private JLabel turnLabel;
private JMenuBar menuBar;
private Player playerX = new Player();
private Player playerO = new Player();


public NumericalTTTView(int wide, int tall, GameUI gameFrame) {
super();
setLayout(new BorderLayout());
root = gameFrame;
setGameController(new NumericalTTTGame(wide,tall));   
initializeButtons(wide,tall);

messageLabel = new JLabel("Welcome to Numerical TicTacToe", SwingConstants.CENTER);
messageLabel.setFont(new Font("Monospaced", Font.BOLD, 26));      

add(messageLabel, BorderLayout.NORTH);     
game.newGame();
add(initializeButtons(tall,wide), BorderLayout.CENTER);
add(makeButtonPanel(),BorderLayout.SOUTH);

makeMenu();
gameFrame.setJMenuBar(menuBar); 
playerName();

player = getPlayer(odd);

turnLabel = new JLabel("TURN: " + player, SwingConstants.CENTER);
turnLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
add(turnLabel, BorderLayout.WEST);  // Messages go on top 
}

private String getPlayer(boolean turn){
    if(turn) {
        player = "odd";
    } else{
        player = "even";
    }
    return player;
}

public void playerName(){
    String p1 = JOptionPane.showInputDialog(null, "Please input a player EVEN name: ", 
                                            JOptionPane.YES_NO_CANCEL_OPTION);
    playerX.setName(p1);
    playerX.setGamesPlayed(1);

    String p2 = JOptionPane.showInputDialog(null, "Please input a player ODD name: ");
    playerO.setName(p2);
    playerO.setGamesPlayed(1);
    askLoadProfile();
}

public void askLoadProfile(){
    JOptionPane pane = new JOptionPane();
    int askLoad = JOptionPane.showConfirmDialog(pane, 
                                "Would you like to load any existing player profiles?",
                                "Load Player Profile?", 
                                JOptionPane.YES_NO_OPTION);
    
    if(askLoad == JOptionPane.YES_OPTION){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Load " + playerX.getName() + " Profile");
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            try{
                SaveToFile.load(playerX, "", chooser.getSelectedFile().getAbsolutePath());
                chooser.setDialogTitle("Load " + playerO.getName() + " Profile");
                int returnValue = chooser.showOpenDialog(this);
                if(returnValue == JFileChooser.APPROVE_OPTION){
                    SaveToFile.load(playerO, "", chooser.getSelectedFile().getAbsolutePath());
                }
            } catch(NumberFormatException | NullPointerException e){
                JOptionPane errorPane = new JOptionPane();
                JOptionPane.showConfirmDialog(errorPane, 
                                "Hmmm. There seems to be something wrong with the file. Please exit and try again.",
                                "Invalid File.", 
                                JOptionPane.OK_CANCEL_OPTION);
            }
        }
    }
}

private JPanel makeButtonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);
    buttonPanel.add(makeNewGameButton(), BorderLayout.CENTER);
    buttonPanel.add(makeGoBackButton(), BorderLayout.CENTER);
    return buttonPanel;
}

private JButton makeNewGameButton(){
    JButton button = new JButton("Reset");
    button.addActionListener(e->newGame());
    button.add(Box.createVerticalStrut(25));
    return button;
}

private JButton makeGoBackButton(){
    JButton back = new JButton("Go Back");
    back.addActionListener(e->root.start());
    back.add(Box.createVerticalStrut(25));
    return back;
}

public void setGameController(NumericalTTTGame controller){
    this.game = controller;
}

// a method used to create 9 buttons
// set the text, add action listeners
// and add them to the screen
public JPanel initializeButtons(int tall, int wide){
    JPanel panel = new JPanel();
    buttons = new PositionAwareButton[tall][wide];
    panel.setLayout(new GridLayout(wide, tall));
    for (int y=0; y<wide; y++){
        for (int x=0; x<tall; x++){ 
            buttons[y][x] = new PositionAwareButton();
            buttons[y][x].setAcross(x+1); 
            buttons[y][x].setDown(y+1);
            buttons[y][x].setText("");
            buttons[y][x].setPreferredSize(new Dimension(150, 150));
            buttons[y][x].addActionListener(e->enterNumber(e));
            panel.add(buttons[y][x]);
        }
    }
    return panel;
}

private String updatePlayerProfile(boolean turn){
    String name;
    if(turn) {
        player = "Even";
        name = playerX.getName();
        playerX.setLoss(1);
        playerO.setWins(1);
    } else {
        player = "Odd";
        name = playerO.getName();
        playerX.setWins(1);
        playerO.setLoss(1);
    }
    return name;
}

private void gameOverMessage(){
    JOptionPane pane = new JOptionPane();
    int askSave = JOptionPane.showConfirmDialog(pane, 
                        "Game Over. "+ updatePlayerProfile(odd) + " (" + player 
                        + ") wins. Would you like to save player profiles?",
                        "Game over.", 
                        JOptionPane.YES_NO_OPTION);
    if(askSave == JOptionPane.YES_OPTION) {
        saveProfile();
    } 
}

private void saveProfile(){
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setDialogTitle("Save " + playerX.getName() + " Profile");
    chooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV File", ".csv"));
    chooser.setAcceptAllFileFilterUsed(true);
    chooser.setSelectedFile(new File(playerX.getName() + "Profile.csv"));

    int returnVal = chooser.showSaveDialog(this);
    if(returnVal == JFileChooser.APPROVE_OPTION){
        game.getStringToSave();
        System.out.println(chooser.getSelectedFile().getAbsolutePath());
        SaveToFile.save(playerX, "", chooser.getSelectedFile().getAbsolutePath());
    }

    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setDialogTitle("Save " + playerO.getName() + " Profile");
    chooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV File", ".csv"));
    chooser.setAcceptAllFileFilterUsed(true);
    chooser.setSelectedFile(new File(playerO.getName() + "Profile.csv"));

    int returnValue = chooser.showSaveDialog(this);
    if(returnValue == JFileChooser.APPROVE_OPTION){
        game.getStringToSave();
        System.out.println(chooser.getSelectedFile().getAbsolutePath());
        SaveToFile.save(playerO, "", chooser.getSelectedFile().getAbsolutePath());

    }
}

private void playAgainMessage(){
    JOptionPane pane = new JOptionPane();
    int dialogResult = JOptionPane.showConfirmDialog(pane, 
                        "OK, Would you like to play again?",
                        "Game over.", 
                        JOptionPane.YES_NO_OPTION);
    if(dialogResult == JOptionPane.YES_OPTION) {
        newGame();
    } else{
        root.start();
        menuBar.setVisible(false);
    }
}

private void drawMessage(){
    JOptionPane pane = new JOptionPane();
    int dialogResult = JOptionPane.showConfirmDialog(pane, 
                    "Draw. Would you like to save player profiles?",
                    "Game over.", 
                    JOptionPane.YES_NO_OPTION);
    if(dialogResult == JOptionPane.YES_OPTION)  {
        saveProfile();
        playAgainMessage();
        // newGame();
    } else{
        root.start();
        menuBar.setVisible(false);
    } 
}

// display the victorious player
public void displayVictor() {
    if(game.isDone()) {
        player = getPlayer(odd);
        // updatePlayerProfile(odd);
        gameOverMessage();
        playAgainMessage();
    } else if(checkDraw()) {
        drawMessage();
    }
}

protected void newGame(){
    odd = true;
    turnLabel.setText("TURN: " + "odd");
    add(turnLabel, BorderLayout.WEST);
    for (int y=0; y<game.getHeight(); y++){
        for (int x=0; x<game.getWidth(); x++){  
            buttons[y][x].setText("");
        }
    }
    game.newGame();
    updateView();
}

protected void updateView(){
    //update the labels on the buttons according to the model
    for (int y=0; y<game.getHeight(); y++){
        for (int x=0; x<game.getWidth(); x++){  
            buttons[y][x].setText(game.getCell(buttons[y][x].getAcross(),buttons[y][x].getDown())); 
            buttons[y][x].setEnabled(false);
            buttons[y][x].setFont(new Font("Monospaced", Font.PLAIN, 26));
            if(buttons[y][x].getText() == ""){
                buttons[y][x].setEnabled(true);
            }
        }
    }
}

// checks for draw
public boolean checkDraw() {
    boolean full = true;
    for (int y=0; y<game.getHeight(); y++){
        for (int x=0; x<game.getWidth(); x++){ 
            if(buttons[y][x].getText() == "") {
            full = false;
            }
        }
    }
    return full;
}

private boolean checkExists(int num, boolean numberExists) {
    for(int i = 1; i < 4; i++){
        for(int j = 1; j < 4; j++){
            if(num == (Integer.parseInt(game.getCell(i,j)))){
                numberExists = true;
            }
        }
    }
    return numberExists;
}

private void updateButton(){
    odd = game.setTurn(odd);
    player = getPlayer(odd);
    turnLabel.setText("TURN: " + player);
    turnLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
    add(turnLabel, BorderLayout.WEST);  
    displayVictor();
}

private void enterNumber(ActionEvent e){
    int num = 0;
    player = getPlayer(odd);
    while (true) {
        try {
            boolean numberExists = false;
            num = Integer.parseInt(JOptionPane.showInputDialog(null, "Please input an " + player + " value: "));
            game.changeEmptyCell();
            numberExists = checkExists(num, numberExists);
            if (((odd && num % 2 != 0) || (!odd && num % 2 == 0)) 
                && (!numberExists) && (num >= 0) && (num <= 9)) {                    
                PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
                clicked.setFont(new Font("Monospaced", Font.PLAIN, 26));
                clicked.setEnabled(false);
                if(game.takeTurn(clicked.getAcross(), clicked.getDown(),num)){
                    clicked.setText(game.getCell(clicked.getAcross(),clicked.getDown()));
                }
                updateButton();
                break;
            } else if(numberExists){
                JOptionPane.showMessageDialog(null, "this number already exists", "error", JOptionPane.ERROR_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(null, 
                            "Enter an " + player + " integer between 0-9", "error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException error) { 
            JOptionPane.showMessageDialog(null, "Enter a valid integer", "error", JOptionPane.ERROR_MESSAGE);
        }
    }   
}

// checks for a winner
public boolean checkForWinner() {
    return game.winnerHorizontal(odd) || game.winnerVertical(odd) || game.winnerDiagonal(odd);
}
    
public void makeMenu(){
    menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem item = new JMenuItem("Save the Board");
    JMenuItem item2 = new JMenuItem("Load a Board");
    menu.add(item);
    menu.add(item2);
    menuBar.add(menu);
    item.addActionListener(e->{
        try {
            saveSomething();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    });
    item2.addActionListener(e->{
        try {
            loadBoard();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    });

}

protected void saveSomething() throws FileNotFoundException{
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setDialogTitle("Save the Board");
    chooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV File", ".csv"));
    chooser.setAcceptAllFileFilterUsed(true);
    chooser.setSelectedFile(new File("board.csv"));
    
    int returnVal = chooser.showSaveDialog(this);
    if(returnVal == JFileChooser.APPROVE_OPTION){
        game.getStringToSave();
        SaveToFile.save(game, "", chooser.getSelectedFile().getAbsolutePath());
    }
}

protected void loadBoard() throws FileNotFoundException{
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Load the Board");

    int returnVal = chooser.showOpenDialog(this);
    if(returnVal == JFileChooser.APPROVE_OPTION){
        game.getStringToSave();
        SaveToFile.load(game, "", chooser.getSelectedFile().getAbsolutePath());
        updateView();

        odd = game.loadedBoardPlayer();
        player = getPlayer(odd);
        turnLabel.setText("TURN: " + player);
        turnLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        add(turnLabel, BorderLayout.WEST);  // Messages go on top  
    }
}
}
