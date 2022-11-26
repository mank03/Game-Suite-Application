package numericalttt;

import java.util.Iterator;

public class NumericalTTTGrid extends boardgame.Grid{
private String line = null;
private Character cellWall = '|';
private static int cellWidth = 4;

public NumericalTTTGrid(int wide, int tall){
        super(wide,tall);
        makeGridLine();
}

private void makeGridLine(){
    line = "";
    for(int j=0; j<getWidth()*cellWidth; j++){
        line +="-";
        }
}
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
 * this is a no-op method right now that is just a
 * placeholder for something that could be useful in 
 * saving/loading games
 * **/


public boolean parseStringIntoBoard(String toParse){
    try{
        String[] parts = toParse.split(",|\\\n");
        int p = parts.length;
        int p1Count = 0;
        int p2Count = 0;
        boolean odd = true;
            for(int i = 0; i < p; i++){
            if(parts[i] == ""){
                positionToArray(i, "");
            } else if(Integer.parseInt(parts[i]) % 2 != 0){
                p1Count++;
                positionToArray(i, parts[i]);
            } else if(Integer.parseInt(parts[i]) % 2 == 0){
                p2Count++;
                positionToArray(i, parts[i]);
            } 
        }
        if(p1Count == p2Count){
            odd = true;
        } else if (p1Count > p2Count){
            odd = false;
        }
        return odd;
    } catch (NumberFormatException e){
        e.printStackTrace();
    }
    return false;
}

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