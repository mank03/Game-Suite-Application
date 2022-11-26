package player;
import boardgame.Saveable;

public class Player implements Saveable{ 
    private String playerName;
    private int numWins;
    private int numLosses;
    private int gamesPlayed;
 

public void setName(String name){
    playerName = name;
}
public String getName(){
    return playerName;
}

public void setWins(int wins){
    numWins += wins;
}
public int getWins(){
    return numWins;
}

public void setLoss(int loss){
    numLosses += loss;
}
public int getLoss(){
    return numLosses;
}

public void setGamesPlayed(int games){
    gamesPlayed += games;
}
public int getGamesPlayed(){
    return gamesPlayed;
}




/* Implementation of Saveable Interface  */

@Override
public String getStringToSave(){
    return getName() + "," + getWins() + "," + getLoss() + "," + getGamesPlayed();
}


@Override
public void loadSavedString(String toLoad){
    //parse the string
    String[] parts = toLoad.split(",");
    setName(parts[0]);
    setWins(Integer.parseInt(parts[1]));
    setLoss(Integer.parseInt(parts[2]));
    setGamesPlayed(Integer.parseInt(parts[3]));
}

}



//USE MERMAID LIVE EDITOR TO VISUALIZE CODE THROUGH DIAGRAMS