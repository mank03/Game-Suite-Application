package tictactoe;
import java.util.Iterator;

public class TicTacToeGrid extends boardgame.Grid{
    private String line = null;
    private Character cellWall = '|';
    private static int cellWidth = 4;


/**
 * Calling the super class constructor and then calling the makeGridLine method.
 */ 
public TicTacToeGrid(int wide, int tall){
        super(wide,tall);
        makeGridLine();
}

/**
 * This function creates a string of dashes that is the same length as the width of the grid
 */
public void makeGridLine(){
    line = "";
    for(int j=0; j<getWidth()*cellWidth; j++){
        line +="-";
    }
}

/**
 * This function iterates through the grid and prints out the grid in a string format
 * 
 * @return A string representation of the grid.
 */
@Override
public String getStringGrid() {
    String toPrint = "";
    int i = 0;
    Iterator<String> iter = iterator();


    String item;
    while(iter.hasNext()){
        item = iter.next();
        toPrint = toPrint+item;
        i++;
        if (i == getWidth()){
            toPrint = toPrint + "\n" + cellWall + "\n";
            i = 0;
        }

    }
    return toPrint;
}

/** 
 * wrapper around getStringGrid
 * @return String
 */
public String toString(){
    return getStringGrid();
}

 
/**
 * This function takes a string as an argument, splits the string into an array, and then iterates
 * through the array to determine the current player and the current state of the board
 * 
 * @param toParse This is the string that is passed in
 * @return The player who's turn it is.
 */
public String parseStringIntoBoard(String toParse){
    String[] parts = toParse.split(",|\\\n");
    int p1Count = 0;
    int p2Count = 0;
    String player = "";

        for(int i = 0; i < parts.length; i++){
        if(parts[i].equals("X")){
            player = "X";
            p1Count++;
            positionToArray(i, player);
        } else if(parts[i].equals("O")){
            player = "O";
            p2Count++;
            positionToArray(i, player);
        } else if(parts[i].equals("")){
            player = " ";
            positionToArray(i, player);
        } else {
            player = null;
            return player;
        }
    }
    if(p1Count == p2Count){
        player = "X";
    } else if (p1Count > p2Count){
        player = "O";
    }
    return player;
}

/**
 * It takes the position of the player's move and the player's name and sets the value of the array to
 * the player's name
 * 
 * @param i the position of the button that was clicked
 * @param player The player who is making the move
 */
private void positionToArray(int i, String player){
    if(i == 0 || i == 1 || i == 2){
        setValue(i+1,1,player);
    } else if(i == 3) {
        setValue(1,2,player);
    } else if(i == 4){
        setValue(2,2, player);
    } else if(i == 5){
        setValue(3,2, player);
    } else if(i == 6){
        setValue(1,3, player);
    } else if(i == 7){
        setValue(2,3, player);
    } else if(i == 8){
        setValue(3,3, player);
    }
}

}