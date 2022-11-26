package tictactoe;

import boardgame.Grid;

public class TicTacToeGame extends boardgame.BoardGame implements boardgame.Saveable{

private String turn = "X";

/*
 * This is the constructor for the TicTacToeGame class. 
 */
public TicTacToeGame(int wide, int high){
    super(wide,high);
    setGrid(new TicTacToeGrid(wide,high));
}

/*
 * When the newGame method is called, reset the values
 */
@Override
public void newGame(){
    super.newGame();
    setValue(1,1," ");
    setValue(1,2," ");
    setValue(1,3," ");
    setValue(2,1," ");
    setValue(2,2," ");
    setValue(2,3," ");
    setValue(3,1," ");
    setValue(3,2," ");
    setValue(3,3," ");
}

/**
 * `takeTurn` takes in the coordinates of the cell to be changed and the value to be changed to, and
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
 * `takeTurn` takes in the coordinates of the cell to be changed and the value to be changed to, and
 * returns true if the value is changed
 * 
 * @param across the column number of cell
 * @param down the row number of the cell 
 * @param input the value the user entered
 * @return True if turn is taken, false otherwise
 */
@Override
public boolean takeTurn(int across, int down, int input){
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
public String setTurn(String player){
    if(player == "X" || turn == "X") {
        player = "O";
        turn = "O";
    } else {
        player = "X";
        turn = "X";
    }
    return player;
}

/**
 * This method calculates if winner can be determined horizontally
 * 
 * @param player The player who is currently playing.
 * @return The winner of the game.
 */
public String winnerHorizontal(String player){
    if(getCell(1,1).equals(getCell(2,1)) && getCell(1,1).equals(getCell(3,1)) && getCell(1,1) != " "){
        return player;
    } else if(getCell(1,2).equals(getCell(2,2)) && getCell(1,2).equals(getCell(3,2)) && getCell(1,2) != " "){
        return player;
    } else if(getCell(1,3).equals(getCell(2,3)) && getCell(1,3).equals(getCell(3,3)) && getCell(1,3).charAt(0) != ' '){
        return player;
    }
    return "0";
}

/**
 * This method calculates if winner can be determined vertically
 * 
 * @param player The player who is currently playing.
 * @return The winner of the game.
 */
public String winnerVertical(String player){
    if(getCell(1,1).equals(getCell(1,2)) && getCell(1,1).equals(getCell(1,3)) && getCell(1,1).charAt(0) != ' '){
        return player;
    } else if(getCell(2,1).equals(getCell(2,2)) && getCell(2,1).equals(getCell(2,3)) && getCell(2,1).charAt(0) != ' '){
        return player;
    } else if(getCell(3,1).equals(getCell(3,2)) && getCell(3,1).equals(getCell(3,3)) && getCell(3,1).charAt(0) != ' '){
        return player;
    }
    return "0";
}

/**
 * This method calculates if winner can be determined diagonally
 * 
 * @param player The player who is currently playing.
 * @return The winner of the game.
 */
public String winnerDiagonal(String player){
    if(getCell(1,1).equals(getCell(2,2)) && getCell(1,1).equals(getCell(3,3)) && getCell(1,1).charAt(0) != ' '){
        return player;
    } else if(getCell(3,1).equals(getCell(2,2)) && getCell(3,1).equals(getCell(1,3)) && getCell(3,1).charAt(0) != ' '){
        return player;
    }
    return "0";
}

/**
 * If the current player has won horizontally, vertically, or diagonally, then the game is over.
 * 
 * @return The winner of the game.
 */
@Override 
public boolean isDone() {
    return winnerHorizontal(turn) == turn || winnerVertical(turn) == turn || winnerDiagonal(turn) == turn; 
}

/**
 * If the board is full, return true. Otherwise, return false
 * 
 * @return True if board is full, false otherwise
 */
public boolean checkDraw() {
    boolean full = true;
    for (int y=1; y<getHeight()+1; y++){
        for (int x=1; x<getWidth()+1; x++){ 
            if(getCell(y,x) == " ") {
            full = false;
            }
        }
    }
    return full;
}

/**
 * This method sets the grid using super class constructor
 * 
 * @param grid The grid to be set.
 */
@Override
public void setGrid(boardgame.Grid grid){ 
    super.setGrid(grid);
}   



/**
 * This function returns a string that is displayed to the user when the game is over
 * 
 * @return A string that says "You Won!"
 */
@Override
public String getGameStateMessage(){
    return "You Won!";
}

/**
 * This function returns a string that contains the current state of the board
 * 
 * @return A string of the board.
 */
@Override
public String getStringToSave(){
    String stringToSave = "";

    for(int i = 1; i < getHeight()+1; i++){
        for(int j = 1; j < getWidth()+1; j++){
            if(getCell(j,i) == "" || getCell(j,i) == null ||  getCell(j,i) == "0"){
                stringToSave += "";
            } else if(getCell(j,i) == "X"){
                stringToSave += "X";
            } else if(getCell(j,i) == "O"){
                stringToSave += "O";
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
 * The function takes in a string, parses it into a 2D array, and then sets the turn to the player
 * who's turn it is
 * 
 * @param saved The string that was saved in the saveString() method.
 */
@Override
public void loadSavedString(String saved){
    TicTacToeGrid myGrid = (TicTacToeGrid)getGrid();  
    turn = myGrid.parseStringIntoBoard(saved);
    loadedBoardPlayer();
}

/**
 * This function returns the player who's turn it is
 * 
 * @return The turn of the player.
 */
protected String loadedBoardPlayer(){
    return turn;
}

/**
 * If the turn is X, then X wins. If the turn is O, then O wins. If the game is done, then it's a tie.
 * Otherwise, the game is still in progress
 * 
 * @return The winner of the game.
 */
@Override
public int getWinner(){
    if(turn == "X"){
        return 1;
    } else if(turn == "O"){
        return 2;
    } else if(isDone()){
        return -1;
    }

    return 0;
}
}