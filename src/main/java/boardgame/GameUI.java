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

/**
* 
*/
public class GameUI extends JFrame{
    private JPanel gameContainer;
    private JPanel buttons = new JPanel(new GridBagLayout());
    private JPanel p =new JPanel();
    private GridBagConstraints gbc = new GridBagConstraints();


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


    private JButton ticTacToeButton(){
        JButton ticTacToeButton = new JButton("Play Tic Tac Toe");
        ticTacToeButton.setPreferredSize(new Dimension(100, 75));
        ticTacToeButton.addActionListener(e->playTicTacToe());
        buttons.add(ticTacToeButton, gbc);
        return ticTacToeButton;
    }

    private JButton numericTTTButton(){
        JButton numericTTTButton = new JButton("Play Numeric Tic Tac Toe");
        numericTTTButton.setPreferredSize(new Dimension(100, 75));
        numericTTTButton.addActionListener(e->playNumericTTT());
        buttons.add(numericTTTButton, gbc);
        return numericTTTButton;
    }

    private JButton loadScoresButton(){
        JButton scoreButton = new JButton("Load Score");
        buttons.add(scoreButton, gbc);
        scoreButton.setPreferredSize(new Dimension(100, 75));
        scoreButton.addActionListener(e->showScores());
        return scoreButton;
    }

    private JButton exitButton(){
        JButton exitButton = new JButton("Exit");
        buttons.add(exitButton, gbc);
        exitButton.setPreferredSize(new Dimension(100, 75));
        return exitButton;
    }

    public void start(){
        gameContainer.removeAll();
        gameContainer.add(makeButtonPanel());
        getContentPane().repaint();
        getContentPane().revalidate();
    }

    
    protected void playTicTacToe(){
        gameContainer.removeAll();
        buttons.removeAll();
        p.removeAll();
        gameContainer.add(new TicTacToeView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
    }

    protected void playNumericTTT(){
        gameContainer.removeAll();
        buttons.removeAll();
        p.removeAll();
        gameContainer.add(new NumericalTTTView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
    }

    protected void showScores(){
        gameContainer.removeAll();
        buttons.removeAll();
        p.removeAll();
        gameContainer.add(new ShowScores(this));
        getContentPane().repaint();
        getContentPane().revalidate();
    }

public static void main(String[] args) throws IOException{
    GameUI example = new GameUI("Game Suite by Manu Konnur");
    example.setVisible(true);
    example.setLocationRelativeTo(null); // center the window
    }
} 



