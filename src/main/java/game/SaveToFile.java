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

    public static void save(Saveable testPack, String filename, String relativePath){
        Path path = FileSystems.getDefault().getPath(relativePath, filename);
        try{
            Files.writeString(path, testPack.getStringToSave());
        } catch(IOException e){
            // System.out.println(e.getMessage());
        }
    }

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
