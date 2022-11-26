package numericalttt;

import java.util.Iterator;

public class NumericalTTTGrid extends boardgame.Grid{
private String line = null;
private Character cellWall = '|';
private static int cellWidth = 4;


/*
 * This is the constructor for the NumericalTTTGrid class.
 */
public NumericalTTTGrid(int wide, int tall){
        super(wide,tall);
        makeGridLine();
}

/**
 * This function creates a string of dashes that is the same length as the width of the grid
 */
private void makeGridLine(){
    line = "";
    for(int j=0; j<getWidth()*cellWidth; j++){
        line +="-";
        }
}

/**
 * This function creates a string representation of the grid
 * 
 * @return A string representation of the grid.
 */
@Override
public String getStringGrid(){

    if(line == null){
        makeGridLine();
    }
    Iterator<String> iter = iterator();

    String toPrint ="";
    int i=0;
    String cell = iter.next();


    while(cell != null){
        toPrint = toPrint + " "+ cell+ cellWall;
        i++;
        if(i == getWidth()){
            toPrint = toPrint + "\n" + line + "\n";
            i = 0;
        }
        cell = iter.next();
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
 * This function takes a string, splits it into an array, and then assigns the values to the board
 * array
 * 
 * @param toParse The string to parse into the board.
 * @return Weather odd is true or false
 */
public String parseStringIntoBoard(String toParse){
    String odd = "";
    try{
        String[] parts = toParse.split(",|\\\n");
        int p1Count = 0; 
        int p2Count = 0;
            for(int i = 0; i < parts.length; i++){
            if(parts[i] == ""){
                positionToArray(i, "");
            } else if(Integer.parseInt(parts[i]) % 2 != 0){
                p1Count++;
                positionToArray(i, parts[i]);
            } else if(Integer.parseInt(parts[i]) % 2 == 0){
                p2Count++;
                positionToArray(i, parts[i]);
            } else{
                odd = "error";
                return odd;
            }
        }
        if(p1Count == p2Count){
            odd = "true";
        } else if (p1Count > p2Count){
            odd = "false";
        }
    } catch (NumberFormatException e){
        odd = "error";
    }
    return odd;
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