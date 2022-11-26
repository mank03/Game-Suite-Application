package player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import javax.swing.JMenuBar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import boardgame.GameUI;
import game.SaveToFile;
import boardgame.Saveable;




public class ShowScores extends JPanel {
private GameUI root;
private JLabel messageLabel;
private JLabel profileInfo = new JLabel();
private Player playerX;
private static JFrame f;
private static JButton b;
private JPanel stat = new JPanel(new GridBagLayout());
private GridBagConstraints gbc = new GridBagConstraints();

/*
 * This is the constructor of the class. Here two labels are being added to the panel 
 * as well as a go back button
 */
public ShowScores(GameUI gameFrame) {
    JPanel p = new JPanel();
    b = new JButton("select");
    setLayout(new GridBagLayout());
    root = gameFrame;
    
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    messageLabel = new JLabel("Choose a Player Profile to Load", SwingConstants.CENTER);
    messageLabel.setFont(new Font("Monospaced", Font.BOLD, 20));

    add(messageLabel, gbc);  

    profileInfo = new JLabel("", SwingConstants.CENTER);
    profileInfo.setFont(new Font("Monospaced", Font.BOLD, 20));

    add(profileInfo, gbc);  
    b.addActionListener(e->{
        buttonClicked(e);
    });
    p.add(b, gbc);    
    add(p, gbc);
    add(stat, gbc);
    add(makeGoBackButton(),gbc);
    gbc.weighty = 1;
}


/**
 * It creates a button that, when clicked, will return the user to the start screen
 * 
 * @return Go Back JButton object
 */
private JButton makeGoBackButton(){
    JButton back = new JButton("Go Back");
    back.addActionListener(e->root.start());
    back.add(Box.createVerticalStrut(25));
    return back;
}

/**
 * This method is called when the select button is clicked. It opens a file choose
 * and loads the file into a Player object
 * 
 * @param e the ActionEvent that is triggered when the button is clicked
 * @return The method is returning the player string
 */
public String buttonClicked(ActionEvent e){
    String s = e.getActionCommand();
    String player = "";
    // if (s.equals("select")) {

    // }
    playerX = new Player();
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Load Player Profile");
    int returnVal = chooser.showOpenDialog(this);
    if(returnVal == JFileChooser.APPROVE_OPTION){
        try{
            player = checkExists(SaveToFile.load(playerX, "", chooser.getSelectedFile().getAbsolutePath()));
            profileInfo.setText(player);
            stat.add(profileInfo, gbc);
        } catch (NumberFormatException error){
            JOptionPane pane = new JOptionPane();
            JOptionPane.showConfirmDialog(pane, 
                    "Hmmm. There seems to be something wrong with the file.",
                    "Invalid File.", 
                    JOptionPane.OK_CANCEL_OPTION);
        }
    } 

    return player;
}


/**
 * This function checks if the player profile exists and returns a string with the player's name, wins,
 * losses, and games played
 * 
 * @param exists boolean value that determines if the player profile exists
 * @return The method is returning the profile string
 */
private String checkExists(boolean exists){
    String profile = "";
    if(!exists){
        JOptionPane.showMessageDialog(null, 
                "this player profile does not exists", 
                "error", 
                JOptionPane.ERROR_MESSAGE);
    } else{
        profile = "Name: " + playerX.getName() + "\nWins: " 
                + playerX.getWins() + "\nLosses: "  + playerX.getLoss() 
                + "\nGames Played: " + playerX.getGamesPlayed();
    }
    return profile;
} 

}
