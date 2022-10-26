package boardgame;

import java.util.Iterator;
/**
 * This class  represents an abstract
 * NxM board game.  
 **/

public abstract class BoardGame{

    private Grid grid;
    private Iterator<String> iter;

    /** 
     * @param wide The desired width of the board
     * @param high The desired height of teh board
     */      
    public BoardGame(int wide, int high){
        setGrid(new Grid(wide,high));

    }
    /**
     * Empties the board so that a new game can be started
     */
    public  void newGame(){
        grid.emptyGrid();

    }

    protected void setGrid(Grid aGrid){
        grid = aGrid;
        iter = grid.iterator();
    }
    protected Grid getGrid(){
        return grid;
    }

    protected void setValue(int across, int down, String input){
        grid.setValue(across,down,input);
    }
    

    protected void setValue(int across, int down, int input){
        grid.setValue(across,down,input);
    }

    
    /** 
     * Overloaded method that facilitates the placement of an input on the board 
     * with String input. Method should be overriden 
     * to validate input prior to placing the input value.  Overridden method 
     * should throw exceptions when validating input.
     * @param across across index, 1 based
     * @param down  down index, 1 based
     * @param input  String input from game
     * @return boolean  returns true if input was placed false otherwise
     */
    public abstract boolean takeTurn(int across, int down, String input);

    
    /** 
     * Overloaded method that facilitates the placement of an input on the board
     *  with integer input. Method should be overriden 
     * to validate input prior to placing the input value. 
     *  Overridden method should throw exceptions when validating input.
     * @param across across index, zero based
     * @param down  down index, zero based
     * @param input  int input from game
     * @return boolean  returns true if input was placed false otherwise
     */
    public  abstract boolean takeTurn(int across, int down, int input);
        
    
   
    
    /** 
     * Returns true when game is over, false otherwise.  Method must be overridden.
     * @return boolean
     */
    public abstract boolean isDone();
   
    /**
     * Method must be overridden.  Returns the winner of the game.
     * @return 0 for tie, 1 for player 1, 2 for player 2, -1 if no winner
     */
    public abstract int getWinner();
  

    
    /** 
     * @return int height of board
     */
    public int getHeight(){
        return grid.getHeight();
    }

    
    /** 
     * @return width of board
     */
    public int getWidth(){
        return grid.getWidth();
    }

    
    /** 
     * Must be overridden if used.  Returns a message that can be output to use that provides
     * information about the game state.    
     * @return String mesage to user
     */
    public abstract String getGameStateMessage();

    
    /** 
     * @param across
     * @param down
     * @return String
     */
    public String getCell(int across, int down){
        return grid.getValue(across,down);
    }

    
    /** 
     * Iterator method that will return the next value in the board data structure 
     * each time it is called.  If it runs out of values, it returns null and then resets
     * to the beginning of the data structure.
     * @return String String representation of the next value
     */
    public String getNextValue(){
        if(!iter.hasNext()){
            iter = grid.iterator();
            return null;
        }
        return iter.next();
    }

    
    /** 
     * Should be overridden to create a nicer representation.
     * @return String String representation of the board
     */
    public String toString(){
        return grid.toString();
    }
}