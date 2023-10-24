import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Class to read the save file.
 */
public class ReadLevelFile {

    //private int x;
//
    ///**
    // * Constructor for objects of class ReadSaveFile.
    // * @param x
    // */
    //public ReadLevelFile(int x) {
    //    this.x = x;
    //}
    
    private ArrayList<String> levelList = new ArrayList<>(); // Create a new array to store the level data
    // the function with values are stored
    

    //String fileName = "./Assets/levels/level" + x + ".txt";
    
    private File file /* = new File(fileName)*/;

    String[] readSaveFile(int level_number) {
        try {
            file = new File("./Assets/levels/level" + level_number + ".txt");
            // Read the file
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            //System.out.println("Contents of the file:");

            do {
                line = bufferedReader.readLine();
                System.out.println(line); // Print each line to the console
                if (!"".equals(line) && line != null) { // If the line is not empty
                    levelList.add(line); // Add the line to the array
                }

            } while (line != null);
            bufferedReader.close();
            return levelList.toArray(new String[levelList.size()]);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
