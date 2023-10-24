import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Class to read the save file.
 */
public class ReadSaveFile {
    private String fileName = "./Assets/savadata/save.txt";
    private File file = new File(fileName);
    private int[] saveData = {-1, -1, -1}; //if the savedata is empty, the list stays as -1 -1 -1 (imposible to reach by normal save data)
    
    int[] readSaveFile() {
        try {
            // Read the file
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            //System.out.println("Contents of the file:");
            int i = 0;
            do {
                line = bufferedReader.readLine();
                //System.out.println(line); // Print each line to the console
                if (!"".equals(line) && line != null) { // If the line is not empty
                    saveData[i] = Integer.valueOf(line);
                }
                i++;
            } while (line != null && i < 3);
            bufferedReader.close();
            return saveData;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
