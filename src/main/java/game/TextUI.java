package game;

import java.util.InputMismatchException;
import java.util.Scanner;
import tictactoe.TicTacToeGame;

public class TextUI {

private TicTacToeGame game = new TicTacToeGame(3,3); 
private String playerMark = "X";
private Scanner input = new Scanner(System.in); 
private int across;
private int down;
private int again;

/*
 * This is the constructor for the TextUI class. It creates a new TicTacToeGame object and sets the
 * grid to a new grid.
 */
public TextUI(){
    game = new TicTacToeGame(3,3);
}


/**
 * This is the main method that calls the startGame() method to begin playing
 */
public static void main(String[] args) {
    TextUI game = new TextUI();
    game.startGame();
}

    
/**
 * The function starts a new game, then loops through the game until the game is over
 */
public void startGame(){
    game.newGame();
    boolean loop = true;
    while (loop){
        System.out.println("\n\nTurn = " + playerMark);
        if (this.checkPosition()) {
            continue;
            } else {
            game.takeTurn(across,down,playerMark);            
            for(int i = 1; i < 4; i++){
                for(int j = 1; j < 4; j++){
                    String q = game.getCell(i,j);
                    System.out.print("|" + q + " ");
                }
                System.out.print("|\n");
            }
            if(checkDone()){
                loop = true;
            }
        playerMark = game.setTurn(playerMark);
    }
}
}

/**
 * If the game is done, print the game state message and ask the user if they want to play again.
 * @return The boolean value of loop.
 */
private boolean checkDone(){
    boolean loop = false;
    if(game.isDone()){
        System.out.println(playerMark + ", " + game.getGameStateMessage());
        System.out.println("Play again? (1=yes, 0=no): ");
        again = input.nextInt();
        if(again == 1){
            game.newGame();
            playerMark = "O";
            loop = true;
            return loop;
        } else{
            System.exit(0);
        }
    } else if(checkDraw()){
        System.out.println("It's a draw\n");
    } 
    return loop;
}


/**
 * If the game is a draw, return true. Otherwise, return false
 * 
 * @return The method is returning a boolean value.
 */
private boolean checkDraw() {
    if(game.checkDraw()){
        return true;
    }
    return false;
}

/**
 * This function checks the board to see if the cell already contains X or O
 * 
 * @param wide the width of the board
 * @param high the height of the board
 * @return True if cell contains X or O, false otherwise.
 */
public boolean checkBoard(int wide, int high) {
    if (game.getCell(wide,high) == "X" || game.getCell(wide,high) == "O") {
        return true;
    }
    return false;
}

/**
 * This function checks the position of the player's move and returns true if the position is invalid
 * 
 * @return The method is returning a boolean value.
 */
private boolean checkPosition(){
try{
    System.out.print("\nEnter Position across: ");
    across = input.nextInt();
    System.out.print("\nEnter Position down: ");
    down = input.nextInt();

    if(across > 3 || across < 1 || down > 3 || down < 1){
    System.out.println("Error - Please enter value in range");
    return true;
    }
    if(checkBoard(across, down)){
    System.out.println("Illegal Move! Try Again");
    return true;
    }  
} catch(InputMismatchException e) {
    System.out.println("Please enter a valid numeric value");
    input.nextLine();
    return true;
}
return false;
}

}
/**
 * The function takes in a string, parses it into a 2D array, and then sets the turn to the player
 * who's turn it is
 * 
 * @param saved The string that was saved in the saveString() method.
 */
