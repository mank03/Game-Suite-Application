package boardgame;
/* methods in an interface are automatically public and don't
don't need a modifier in the interface definintion*/
public interface Saveable{
    /* Object returns a string in the format required for 
    a text save file for that object */
     String getStringToSave();
    /* Object parses the string given as a parameter and restores
    its state based on the values in the string*/
     void loadSavedString(String toLoad);
}