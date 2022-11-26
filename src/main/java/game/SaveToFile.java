package game;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import boardgame.Saveable;

public class SaveToFile{

/**
 * It takes a Saveable object, a filename, a relative path and saves the Saveable object to the
 * file.
 * 
 * @param testPack The object that is being saved.
 * @param filename the name of the file you want to save to
 * @param relativePath The path to the folder where you want to save the file.
 */
public static void save(Saveable testPack, String filename, String relativePath){
    Path path = FileSystems.getDefault().getPath(relativePath, filename);
    try{
        Files.writeString(path, testPack.getStringToSave());
    } catch(IOException e){
        // System.out.println(e.getMessage());
    }
}

/**
 * It loads a file from the relative path and filename given.
 * 
 * @param toSave the object that you want to save
 * @param filename the name of the file to save to
 * @param relativePath the path to the folder where the file is located.
 * @return A boolean value that is true if the file exists and false if it does not.
 */
public static boolean load(Saveable toSave, String filename, String relativePath){
    Path path = FileSystems.getDefault().getPath(relativePath, filename);
    boolean exists = true;
    try{
        File f = new File(path.toString());
        if(f.isFile()){
            exists = true;
        } else{
            exists = false;
            return exists;
        }
        String fileLines = String.join("\n", Files.readAllLines(path));
        System.out.println("filelines = " + fileLines);
        toSave.loadSavedString(fileLines);
        return exists;
    } catch(IOException e){
        // System.out.println(e.getMessage());
    }
    return exists;
}
}
