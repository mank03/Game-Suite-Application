package player;
import boardgame.Saveable;

public class Player implements Saveable{ 
    private String playerName;
    private int numWins;
    private int numLosses;
    private int gamesPlayed;
 

/**
 * This function sets the player's name to the value of the name parameter.
 * 
 * @param name The name of the player.
 */
public void setName(String name){
    playerName = name;
}

/**
 * This function returns the name of the player.
 * 
 * @return The name of the player.
 */
public String getName(){
    return playerName;
}

/**
 * This function adds the value of the parameter wins to the variable numWins.
 * 
 * @param wins The number of wins to add to the player's total.
 */
public void setWins(int wins){
    numWins += wins;
}

/**
 * This function returns the number of wins.
 * 
 * @return The number of wins.
 */
public int getWins(){
    return numWins;
}

/**
 * This function adds the value of the parameter loss to the variable numLosses.
 * 
 * @param loss The number of losses to add to the player's record.
 */
public void setLoss(int loss){
    numLosses += loss;
}

/**
 * This function returns the number of losses.
 * 
 * @return The number of losses.
 */
public int getLoss(){
    return numLosses;
}

/**
 * This function adds the value of the parameter games to the variable gamesPlayed.
 * 
 * @param games The number of games played.
 */
public void setGamesPlayed(int games){
    gamesPlayed += games;
}

/**
 * This function returns the number of games played by the player
 * 
 * @return The number of games played.
 */
public int getGamesPlayed(){
    return gamesPlayed;
}




/* Implementation of Saveable Interface  */

/**
 * The function returns a string that contains the name, wins, losses, and games played of the player
 * 
 * @return The name, wins, losses, and games played of the player.
 */
@Override
public String getStringToSave(){
    return getName() + "," + getWins() + "," + getLoss() + "," + getGamesPlayed();
}


/**
 * This function takes a string that was saved to a file and parses it into the appropriate variables
 * 
 * @param toLoad The string to load
 */
@Override
public void loadSavedString(String toLoad){
    String[] parts = toLoad.split(",");
    setName(parts[0]);
    setWins(Integer.parseInt(parts[1]));
    setLoss(Integer.parseInt(parts[2]));
    setGamesPlayed(Integer.parseInt(parts[3]));
}

}
