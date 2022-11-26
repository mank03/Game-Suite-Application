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

public TextUI(){
    game = new TicTacToeGame(3,3);
    game.setGrid(TicTacToeGame.newGrid(1,3,3));
}


public static void main(String[] args) {
    TextUI game = new TextUI();
    game.startGame();
}

    
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
                    // if(j == 1){
                    //     System.out.print("|\n");
                    // }
                    // if(j == 2){
                    //     System.out.print("|\n");
                    // }
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


private boolean checkDraw() {
    if(game.checkDraw()){
        return true;
    }
    return false;
}

public boolean checkBoard(int wide, int high) {
    if (game.getCell(wide,high) == "X" || game.getCell(wide,high) == "O") {
        return true;
    }
    return false;
}

//Method checks invalid inputs
private boolean checkPosition(){
//using try and catch statement to find input mismatch
try{
    System.out.print("\nEnter Position across: ");
    across = input.nextInt();
    System.out.print("\nEnter Position down: ");
    down = input.nextInt();

    //checks if input is out of range
    if(across > 3 || across < 1 || down > 3 || down < 1){
    System.out.println("Error - Please enter value in range");
    return true;
    }
    //checks if another turn has already been placed on board
    if(checkBoard(across, down)){
    System.out.println("Illegal Move! Try Again");
    return true;
    }  
} catch(InputMismatchException e) {
    System.out.println("Please enter a valid numeric value");
    input.nextLine();
    return true;
}
//counts the turn and returns false 
return false;
}

}
