package tictactoe;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JMenuBar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
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

public class TicTacToeView extends JPanel {
private String playerMark = "X";
private PositionAwareButton[][] buttons;
private TicTacToeGame game;
private GameUI root;
private JLabel messageLabel;
private JLabel turnLabel;
private JMenuBar menuBar;
private Player playerX = new Player();
private Player playerO = new Player();



public TicTacToeView(int wide, int tall, GameUI gameFrame){
    super();
    setLayout(new BorderLayout());
    root = gameFrame;
    setGameController(new TicTacToeGame(wide,tall));   
    initializeButtons(wide,tall);
    messageLabel = new JLabel("Welcome to TicTacToe", SwingConstants.CENTER);
    messageLabel.setFont(new Font("Monospaced", Font.BOLD, 26));
    add(messageLabel, BorderLayout.NORTH);  // Messages go on top   
    game.newGame();
    add(initializeButtons(tall,wide), BorderLayout.CENTER);
    add(makeButtonPanel(),BorderLayout.SOUTH);
    makeMenu();
    gameFrame.setJMenuBar(menuBar); 
    turnLabel = new JLabel("TURN: " + playerMark, SwingConstants.CENTER);
    turnLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
    add(turnLabel, BorderLayout.WEST);  // Messages go on top  
    playerName();
    askLoadProfile();
}

public void playerName(){
    String p1 = JOptionPane.showInputDialog(null, 
    "Please input an player X name: ", 
    JOptionPane.YES_NO_CANCEL_OPTION);
    playerX.setName(p1);
    playerX.setGamesPlayed(1);
    String p2 = JOptionPane.showInputDialog(null, "Please input an player O name: ");
    playerO.setName(p2);
    playerO.setGamesPlayed(1);
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
                    SaveToFile.load(playerO, "", 
                    chooser.getSelectedFile().getAbsolutePath());
                }
            } catch(NumberFormatException | NullPointerException e){
                JOptionPane errorPane = new JOptionPane();
                JOptionPane.showConfirmDialog(errorPane, "Hmmm. " 
                + "There seems to be something wrong with the file. " 
                + "Please exit and try again.",
                "Invalid File.", 
                JOptionPane.OK_CANCEL_OPTION);
            }
        }
    }
}



/**
 * It creates a new JPanel, sets its layout to BoxLayout, sets its alignment to center, adds the new
 * game button and the go back button to it, and returns the panel
 * 
 * @return buttonPanel
 */
private JPanel makeButtonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);
    buttonPanel.add(makeNewGameButton(), BorderLayout.CENTER);
    buttonPanel.add(makeGoBackButton(), BorderLayout.CENTER);
    return buttonPanel;
}



/**
 * makeNewGameButton() creates a button that resets the game when clicked
 * 
 * @return the reset button.
 */
private JButton makeNewGameButton(){
    JButton button = new JButton("Reset");
    button.addActionListener(e->{
    playerMark = "X";
    newGame();
    });
    button.add(Box.createVerticalStrut(25));
    return button;
}



/**
 * It creates a button that, when clicked, will return the user to the start screen
 * 
 * @return the go back button.
 */
private JButton makeGoBackButton(){
    JButton back = new JButton("Go Back");
    back.addActionListener(e->root.start());
    back.add(Box.createVerticalStrut(25));
    return back;
}



/**
 * This method sets the game controller
 * 
 * @param controller The controller object that will be used to control the game.
 */
public void setGameController(TicTacToeGame controller){
    this.game = controller;
}



/**
 * It creates a 2D array of buttons, and adds them to a panel
 * 
 * @param tall the number of rows in the grid
 * @param wide the number of columns in the grid
 * @return A JPanel with a grid layout of buttons.
 */
public JPanel initializeButtons(int tall, int wide){
    JPanel panel = new JPanel();
    buttons = new PositionAwareButton[tall][wide];
    panel.setLayout(new GridLayout(wide, tall));
    for (int y=0; y<wide; y++){
        for (int x=0; x<tall; x++){ 
        buttons[y][x] = new PositionAwareButton();
        buttons[y][x].setAcross(x+1); //made the choice to be 1-based
        buttons[y][x].setDown(y+1);
        buttons[y][x].setText(" ");
        buttons[y][x].setPreferredSize(new Dimension(150, 150));
        buttons[y][x].addActionListener(e->buttonClick(e));
        panel.add(buttons[y][x]);
        }
    }
    return panel;
}



/** 
 * When the grid button is clicked, it takes the turns and disables the cell
 * @param e the ActionEvent
 */
private void buttonClick(ActionEvent e){
    PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
    clicked.setFont(new Font("Monospaced", Font.PLAIN, 26));
    clicked.setEnabled(false);
    if(game.takeTurn(clicked.getAcross(), clicked.getDown(),playerMark)){
        clicked.setText(game.getCell(clicked.getAcross(),clicked.getDown()));
    }

    playerMark = game.setTurn(playerMark);
    turnLabel.setText("TURN: " + playerMark);
    add(turnLabel, BorderLayout.WEST);  
    displayVictor();
}

/**
 * Set the values of player profile and output game over message
 */
private void gameOverMessage(){
    String name;
    if(playerMark == "X") {
        playerMark = "O";
        name = playerO.getName();
        playerX.setLoss(1);
        playerO.setWins(1);
    } else {
    playerMark = "X";
        name = playerX.getName();
        playerX.setWins(1);
        playerO.setLoss(1);
    }

    JOptionPane pane = new JOptionPane();
    int askSave = JOptionPane.showConfirmDialog(pane, 
    "Game Over. "+ name + " (" + playerMark + ") wins. Save player profiles?",
    "Game over.", 
    JOptionPane.YES_NO_OPTION);
    if(askSave == JOptionPane.YES_OPTION) {
        saveProfile();
    } 
}

/**
 * Saves profiles of both players
 */
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
        SaveToFile.save(playerO, "", chooser.getSelectedFile().getAbsolutePath());
    }
}

/**
 * It creates a new JOptionPane, asks the user if they want to play again, and if they do, it creates a
 * new game
 */
private void playAgainMessage(){
    JOptionPane pane = new JOptionPane();
    int dialogResult = JOptionPane.showConfirmDialog(pane, 
    "OK, Would you like to play again?",
    "Game over.", 
    JOptionPane.YES_NO_OPTION);
    if(dialogResult == JOptionPane.YES_OPTION) {
        playerX.setGamesPlayed(1);
        playerO.setGamesPlayed(1);
        newGame();
        playerMark = "X";
    } else{
        root.start();
        menuBar.setVisible(false);
    }
}

/**
 * This function creates a dialog box that asks the user if they would like to save their profile. If
 * they say yes, the saveProfile() function is called and the playAgainMessage() function is called. If
 * they say no, the game restarts and the menu bar is hidden
 */
private void drawMessage(){
    JOptionPane pane = new JOptionPane();
    int dialogResult = JOptionPane.showConfirmDialog(pane, 
    "Draw. Would you like to save player profiles?",
    "Game over.", 
    JOptionPane.YES_NO_OPTION);
    if(dialogResult == JOptionPane.YES_OPTION)  {
        saveProfile();
        playAgainMessage();
    } else{
        playAgainMessage();
    }
}

/**
 * If the game is over, display the game over message and the play again message. If the game is a
 * draw, display the draw message
 */
public void displayVictor() {
    if(game.isDone()) {
        gameOverMessage();
        playAgainMessage();
    } else if(game.checkDraw()){
        drawMessage();
    }
}

/**
 * This function resets the game board and the game model, and updates the view
 */
protected void newGame(){
    turnLabel.setText("TURN: " + "X");
    add(turnLabel, BorderLayout.WEST);  // Messages go on top  
    for (int y=0; y<game.getHeight(); y++){
        for (int x=0; x<game.getWidth(); x++){  
            buttons[y][x].setText(" ");
        }
    }
    game.newGame();
    updateView();

}

/**
 * This function updates the view by setting the text of each button to the corresponding cell in the
 * game
 */
protected void updateView(){
    for (int y=0; y<game.getHeight(); y++){
        for (int x=0; x<game.getWidth(); x++){  
        buttons[y][x].setText(game.getCell(buttons[y][x].getAcross(),buttons[y][x].getDown())); 
        buttons[y][x].setEnabled(true);
        buttons[y][x].setFont(new Font("Monospaced", Font.PLAIN, 26));

            if(buttons[y][x].getText() != " "){
                buttons[y][x].setEnabled(false);
            }
        }
    }
}

/**
 * This function creates a menu bar with two menu items, one to save the board and one to load a board
 */
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


/**
 * Saves the board to a file location chosen by the user
 * @throws FileNotFoundException if the file does not exist
 */
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



/**
 * The function loads a board from a file and updates the view
 * @throws FileNotFoundException if the file does not exist
 */
protected void loadBoard() throws FileNotFoundException{
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Load the Board");

    int returnVal = chooser.showOpenDialog(this);
    if(returnVal == JFileChooser.APPROVE_OPTION){
        game.getStringToSave();
        SaveToFile.load(game, "", chooser.getSelectedFile().getAbsolutePath());
        updateView();
        playerMark = game.loadedBoardPlayer();

        if(playerMark == null){
            JOptionPane pane = new JOptionPane();
            JOptionPane.showConfirmDialog(pane, 
            "Hmmm. There seems to be something wrong with the file.",
            "Invalid File.", 
            JOptionPane.OK_CANCEL_OPTION);
            newGame();
            playerMark = "X";
        } 
        turnLabel.setText("TURN: " + playerMark);
        turnLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        add(turnLabel, BorderLayout.WEST);  // Messages go on top  
        displayVictor();
    } 
}
}
