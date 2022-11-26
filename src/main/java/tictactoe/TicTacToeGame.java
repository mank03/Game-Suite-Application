package tictactoe;

import boardgame.Grid;

public class TicTacToeGame extends boardgame.BoardGame implements boardgame.Saveable{

private String turn = "X";

public TicTacToeGame(int wide, int high){
    super(wide,high);
    setGrid(new TicTacToeGrid(wide,high));
}

@Override
public void newGame(){
    super.newGame();
    //should be a call to some puzzle database
    //across headers 16, 10  down 17, 9
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

@Override
public boolean takeTurn(int across, int down, String input){
    //check that input is a digit ƒbetween 1-9
    setValue(across,down,input);
    return true;
}

@Override
public boolean takeTurn(int across, int down, int input){
    //check that input is a digit between 1-9
    setValue(across,down,String.valueOf(input));
    return true;
}

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

//This method calculates if winner can be determined horizontally
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

//This method calculates if winner can be determined horizontally
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

//This method calculates if winner can be determined horizontally
public String winnerDiagonal(String player){
    if(getCell(1,1).equals(getCell(2,2)) && getCell(1,1).equals(getCell(3,3)) && getCell(1,1).charAt(0) != ' '){
        return player;
    } else if(getCell(3,1).equals(getCell(2,2)) && getCell(3,1).equals(getCell(1,3)) && getCell(3,1).charAt(0) != ' '){
        return player;
    }
    return "0";
}

@Override 
public boolean isDone() {
    return winnerHorizontal(turn) == turn || winnerVertical(turn) == turn || winnerDiagonal(turn) == turn; 
}

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

@Override
public void setGrid(boardgame.Grid grid){ //used full package name instead of import
    super.setGrid(grid);
}   

public static Grid newGrid(int kind, int wide, int tall){
        return new TicTacToeGrid(wide,tall);
}

@Override
public String getGameStateMessage(){
    return "You Won!"; //should be a message based on the state of the game
}

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

@Override
public void loadSavedString(String saved){
    TicTacToeGrid myGrid = (TicTacToeGrid)getGrid();  
    turn = myGrid.parseStringIntoBoard(saved);
    loadedBoardPlayer();
}

protected String loadedBoardPlayer(){
    return turn;
}

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