package numericalttt;


public class NumericalTTTGame extends boardgame.BoardGame implements boardgame.Saveable{

private boolean odd = true;
private boolean loadedPlayerError = false;
            

/*
 * This is the constructor for the NumericalTTTGame class.
 */
public NumericalTTTGame(int wide, int high){
    super(wide,high);
    setGrid(new NumericalTTTGrid(wide,high));

}

/*
 * When the newGame method is called, reset the values
 */
@Override
public void newGame(){
    super.newGame();
    setValue(1,1,"");
    setValue(1,2,"");
    setValue(1,3,"");
    setValue(2,1,"");
    setValue(2,2,"");
    setValue(2,3,"");
    setValue(3,1,"");
    setValue(3,2,"");
    setValue(3,3,"");
}

/**
 * takeTurn takes in the coordinates of the cell to be changed and the value to be changed to, and
 * returns true if the value is changed
 * 
 * @param across the colunm number of cell
 * @param down the row number of the cell
 * @param input the value the user entered
 * @return True if turn is taken, false otherwise
 */
@Override
public boolean takeTurn(int across, int down, String input){
    setValue(across,down,input);
    return true;
}

/**
 * takeTurn takes in the coordinates of the cell to be changed and the value to be changed to, and
 * returns true if the value is changed
 * 
 * @param across the column number of cell
 * @param down the row number of the cell 
 * @param input the value the user entered
 * @return True if turn is taken, false otherwise
 */
@Override
public boolean takeTurn(int across, int down, int input){
    //check that input is a digit between 1-9
    setValue(across,down,String.valueOf(input));
    return true;
}

/**
 * If the player is X or the turn is X, then the player is O and the turn is O, otherwise the player is
 * X and the turn is X
 * 
 * @param player The player who's turn it is.
 * @return The player's turn.
 */
public boolean setTurn(boolean turn){
    if(turn) {
        odd = false;
    } else {
        odd = true;
    }
    return odd;
}

/**
 * This methdo changes all empty cells to a temporary value to avoid exception errors
 */
public void changeEmptyCell(){
    for(int i = 1; i < 4; i++){
        for(int j = 1; j < 4; j++){
            if(getCell(i,j) == ""){
                setValue(i,j, "100");
            }
        }
    }
}

/**
 * This method calculates if winner can be determined horizontally
 * 
 * @param player The player who is currently playing.
 * @return The winner of the game.
 */
public boolean winnerHorizontal(boolean player){
    if((Integer.parseInt(getCell(1,1))) 
                + (Integer.parseInt(getCell(2,1))) 
                + (Integer.parseInt(getCell(3,1))) == 15){
        return true;
    } else if((Integer.parseInt(getCell(1,2))) 
                + (Integer.parseInt(getCell(2,2))) 
                + (Integer.parseInt(getCell(3,2))) == 15){
        return true;
    } else if((Integer.parseInt(getCell(1,3))) 
                + (Integer.parseInt(getCell(2,3))) 
                + (Integer.parseInt(getCell(3,3))) == 15){
        return true;
    }
    return false;
}

/**
 * This method calculates if winner can be determined vertically
 * 
 * @param player The player who is currently playing.
 * @return The winner of the game.
 */
public boolean winnerVertical(boolean player){
    if((Integer.parseInt(getCell(1,1))) 
                + (Integer.parseInt(getCell(1,2))) 
                + (Integer.parseInt(getCell(1,3))) == 15){
        return true;
    } else if((Integer.parseInt(getCell(2,1))) 
                + (Integer.parseInt(getCell(2,2))) 
                + (Integer.parseInt(getCell(2,3))) == 15){
        return true;
    } else if((Integer.parseInt(getCell(3,1))) 
                + (Integer.parseInt(getCell(3,2))) 
                + (Integer.parseInt(getCell(3,3))) == 15){
        return true;
    }
    return false;
}

/**
 * This method calculates if winner can be determined diagonally
 * 
 * @param player The player who is currently playing.
 * @return The winner of the game.
 */
public boolean winnerDiagonal(boolean player){
    if((Integer.parseInt(getCell(1,1))) 
                + (Integer.parseInt(getCell(2,2))) 
                + (Integer.parseInt(getCell(3,3))) == 15){
        return true;
    } else if((Integer.parseInt(getCell(3,1))) 
                + (Integer.parseInt(getCell(2,2))) 
                + (Integer.parseInt(getCell(1,3))) == 15){
        return true;
    }
    return false;
}

/**
 * If the current player has won horizontally, vertically, or diagonally, then the game is over.
 * 
 * @return The winner of the game.
 */
@Override
public boolean isDone(){
    return winnerHorizontal(odd) || winnerVertical(odd) || winnerDiagonal(odd);
}

/**
 * This method returns a string that is displayed to the user when the game is over
 * 
 * @return A string that says "You Won!"
 */
@Override
public String getGameStateMessage(){
    return "You Won!";
}

/**
 * This method returns a string that contains the current state of the board
 * 
 * @return A string of the board.
 */
@Override
public String getStringToSave(){
    String stringToSave = "";
    if(odd){
        stringToSave += "O"; 
    } else{
        stringToSave += "E";
    }
    stringToSave += "\n";
    for(int i = 1; i < 4; i++){
        for(int j = 1; j < 4; j++){
            if(getCell(j,i) == "100" || getCell(j,i) == null){
                stringToSave += "";
            } else {
                stringToSave += getCell(j,i);
            }
            if(j < 3){
                stringToSave += ",";
            }
        }
        stringToSave += "\n";
    }
    return stringToSave;
}

/**
 * The method takes in a string, parses it into a 2D array, and then sets the turn to the player
 * who's turn it is
 * 
 * @param saved The string that was saved in the saveString() method.
 */
@Override
public void loadSavedString(String saved){
    String loadedStringTurn = "";
    NumericalTTTGrid myGrid = (NumericalTTTGrid)getGrid();  
    loadedStringTurn = myGrid.parseStringIntoBoard(saved);
    if(loadedStringTurn == "true"){
        odd = true;
    } else if(loadedStringTurn == "false"){
        odd = false;
    } else{
        loadedPlayerError = true;
    }
    loadedBoardPlayer();
}

/**
 * This is a getter if there is an error in the loaded board
 * 
 * @return The boolean value of odd.
 */
protected boolean getloadedPlayerError(){
    return loadedPlayerError;
}

/**
 * This is a setter if there is an error in the loaded board
 * 
 * @return The boolean value of odd.
 */
protected void setLoadedPlayerError(boolean error){
    loadedPlayerError = error;
}

/**
 * If the board is loaded, return true if the player is odd, false otherwise.
 * 
 * @return The boolean value of odd.
 */
protected boolean loadedBoardPlayer(){
    return odd;
}

/**
 * If the turn is X, then X wins. If the turn is O, then O wins. If the game is done, then it's a tie.
 * Otherwise, the game is still in progress
 * 
 * @return The winner of the game.
 */
@Override
public int getWinner(){
    if(odd){
        return 1;
    } else if(!odd){
        return 2;
    } else if(isDone()){
        return -1;
    }
    return 0;
}
}
