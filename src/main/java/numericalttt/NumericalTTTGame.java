package numericalttt;


public class NumericalTTTGame extends boardgame.BoardGame implements boardgame.Saveable{

private boolean odd = true;
            

public NumericalTTTGame(int wide, int high){
    super(wide,high);
    setGrid(new NumericalTTTGrid(wide,high));

}

@Override
public void newGame(){
    super.newGame();
    //should be a call to some puzzle database
    //across headers 16, 10  down 17, 9
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

public boolean setTurn(boolean turn){
    if(turn) {
        odd = false;
    } else {
        odd = true;
    }
    return odd;
}

public void changeEmptyCell(){
    for(int i = 1; i < 4; i++){
        for(int j = 1; j < 4; j++){
            if(getCell(i,j) == ""){
                setValue(i,j, "100");
            }
        }
    }
}

//This method calculates if winner can be determined horizontally
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

//This method calculates if winner can be determined verically
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

//This method calculates if winner can be determined diagonally
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

@Override
public boolean isDone(){
    return winnerHorizontal(odd) || winnerVertical(odd) || winnerDiagonal(odd);
}

@Override
public String getGameStateMessage(){
    return "You Won!"; //should be a message based on the state of the game
}

@Override
public String getStringToSave(){
    String stringToSave = "";
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

@Override
public void loadSavedString(String saved){
    NumericalTTTGrid myGrid = (NumericalTTTGrid)getGrid();  
    odd = myGrid.parseStringIntoBoard(saved);
    loadedBoardPlayer();
}

protected boolean loadedBoardPlayer(){
    return odd;
}


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
