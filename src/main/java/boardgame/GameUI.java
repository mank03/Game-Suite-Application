package boardgame;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JPanel;
import numericalttt.NumericalTTTView;
import player.ShowScores;
import tictactoe.TicTacToeView;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Dimension;


public class GameUI extends JFrame{
    private JPanel gameContainer;
    private JPanel buttons = new JPanel(new GridBagLayout());
    private JPanel p =new JPanel();
    private GridBagConstraints gbc = new GridBagConstraints();

/**
 *  This is the constructor for the GameUI class. It sets the title, size, and layout of the window. 
 * 
 * @return A JPanel with buttons.
 * @throws IOException
 */
public GameUI(String title) throws IOException{
    super();    
    setVisible(true);
    setLayout(new BorderLayout());
    this.setSize(700, 700);
    this.setTitle(title);
    setContentPane(new JLabel(new ImageIcon("assets/image.jpeg")));
    setLayout(new FlowLayout());
    gameContainer = new JPanel();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(gameContainer);
    add(makeButtonPanel());
    this.setSize(700,700);
}
    
/**
 * This method creates a panel with a title and four buttons that link to the other games
 * 
 * @return A JPanel with buttons.
 */
private JPanel makeButtonPanel() {
        setLayout(new GridBagLayout());
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        buttons.add(new JLabel("<html><h1><strong><i>Game Suite by Manu Konnur</i></strong></h1><hr></html>"), gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ticTacToeButton();
        numericTTTButton();
        loadScoresButton();
        exitButton();
        add(buttons, gbc);
        return p;
}


/**
 * `ticTacToeButton()` creates a button that, when clicked, will open a new window that allows the user
 * to play Tic Tac Toe
 * 
 * @return The TicTacToe JButton object.
 */
private JButton ticTacToeButton(){
    JButton ticTacToeButton = new JButton("Play Tic Tac Toe");
    ticTacToeButton.setPreferredSize(new Dimension(100, 75));
    ticTacToeButton.addActionListener(e->playTicTacToe());
    buttons.add(ticTacToeButton, gbc);
    return ticTacToeButton;
}

/**
 * This method creates a button that, when clicked, will start a game of Numeric Tic Tac Toe
 * 
 * @return The Numerical TicTacToe JButton object.
 */
private JButton numericTTTButton(){
    JButton numericTTTButton = new JButton("Play Numeric Tic Tac Toe");
    numericTTTButton.setPreferredSize(new Dimension(100, 75));
    numericTTTButton.addActionListener(e->playNumericTTT());
    buttons.add(numericTTTButton, gbc);
    return numericTTTButton;
}

/**
 * This method creates a button that, when clicked, will load the scores of the players
 * 
 * @return The Load Scores JButton object.
 */
private JButton loadScoresButton(){
    JButton scoreButton = new JButton("Load Score");
    buttons.add(scoreButton, gbc);
    scoreButton.setPreferredSize(new Dimension(100, 75));
    scoreButton.addActionListener(e->showScores());
    return scoreButton;
}

/**
 * This method exits the program
 * 
 * @return The Exit JButton object.
 */
private JButton exitButton(){
    JButton exitButton = new JButton("Exit");
    buttons.add(exitButton, gbc);
    exitButton.setPreferredSize(new Dimension(100, 75));
    exitButton.addActionListener(e->System.exit(0));
    return exitButton;
}

/**
 * It removes all the components from the gameContainer, adds the button panel, and then repaints and
 * revalidates the content pane
 */
public void start(){
    gameContainer.removeAll();
    gameContainer.add(makeButtonPanel());
    getContentPane().repaint();
    getContentPane().revalidate();
}

/**
 * It removes all the components from the gameContainer, then adds a new TicTacToeView to the
 * gameContainer
 */
protected void playTicTacToe(){
    gameContainer.removeAll();
    buttons.removeAll();
    p.removeAll();
    gameContainer.add(new TicTacToeView(3,3,this));
    getContentPane().repaint();
    getContentPane().revalidate();
}

/**
 * It removes all the components from the gameContainer,  adds the NumericalTTTView to the gameContainer
 */
protected void playNumericTTT(){
    gameContainer.removeAll();
    buttons.removeAll();
    p.removeAll();
    gameContainer.add(new NumericalTTTView(3,3,this));
    getContentPane().repaint();
    getContentPane().revalidate();
}

/**
 * It removes all the components from the gameContainer, adds the ShowScores class to the gameContainer
 */
protected void showScores(){
    gameContainer.removeAll();
    buttons.removeAll();
    p.removeAll();
    gameContainer.add(new ShowScores(this));
    getContentPane().repaint();
    getContentPane().revalidate();
}

/**
 * This is the main method. It creates a new GameUI object and sets the title of the window. It also
 * sets the window to be visible and centers the window.
 */
public static void main(String[] args) throws IOException{
    GameUI example = new GameUI("Game Suite by Manu Konnur");
    example.setVisible(true);
    example.setLocationRelativeTo(null); // center the window
    }
} 



