package boardgame;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

public  class Grid{
    private int height;
    private int width;
    private ArrayList<String> data;


    public Grid(int wide, int tall){
            setWidth(wide);
            setHeight(tall);
            emptyGrid();

    }
    

    private void setWidth(int wide){
        width = wide;
    }
    

    private void setHeight(int tall){
        height = tall;
    }
    
    /** 
     * accessor method for width of grid.
     * @return int width of grid.
     */
    public int getWidth(){
        return width;
    }
    
    /** 
     * Accessor method for height of grid.
     * @return int  height of grid.
     */
    public int getHeight(){
        return height;
    }

    /** 
     * Empties the grid and sets all positions to a space
     * 
     */

    public  void emptyGrid(){
            data = new ArrayList<>();
            for(int i=0; i<width*height; i++){
                data.add(" "); //empty grid
            }
    }

    
    /** 
     * Sets the value at the specified position.  Grid is expecting positions 1 based.
     * @param across  the position across.
     * @param down   the position down.
     * @param val  String representation of the value.
     */
    public void setValue(int across, int down, String val){
            int position = (down-1)*width + (across-1);
            data.set(position,val);

    }

    
    /** 
     * Sets the value at the specified position.  Grid is expecting positions 1 based.
     * @param across  the position across.
     * @param down   the position down.
     * @param val  Integer representation of the value.
     */
    public void setValue(int across, int down, int val){
        int position = (down-1)*width + (across-1);
        data.set(position,String.valueOf(val));

}
    

    protected Iterator<String> iterator(){
        return data.iterator();
    }

    
    /** 
     * Returns the string representation of the grid position indicated
     * by the parameters
     * @param across  position across.
     * @param down  position down.
     * @return String   String value that is at the specified position.
     */
    public String getValue(int across, int down){
            int position = (down-1)*width + (across-1);
            return data.get(position);
    }

    
    /** 
     * Returns a formatted string representation of the grid.
     * The same string is returned by toString()
     * @return String
     */
    public String getStringGrid(){
        String toPrint ="";
 
        int i=0;
        for(String c: data){
            toPrint = toPrint + " "+ c;
            i++;
            if(i == width){
                toPrint = toPrint + "\n";
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
}




