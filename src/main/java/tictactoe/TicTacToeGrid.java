package tictactoe;
import java.util.Iterator;

public class TicTacToeGrid extends boardgame.Grid{
    private String line = null;
    private Character cellWall = '|';
    private static int cellWidth = 4;


    public TicTacToeGrid(int wide, int tall){
            super(wide,tall);
            makeGridLine();
    }

    public void makeGridLine(){
        line = "";
        for(int j=0; j<getWidth()*cellWidth; j++){
            line +="-";
        }
    }
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
 * this is a no-op method right now that is just a
 * placeholder for something that could be useful in 
 * saving/loading games
 * **/
 
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