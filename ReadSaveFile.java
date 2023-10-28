import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Class to read the save file (save.txt).
 * 
 * (See documentation for more info on the save file)
 */
public class ReadSaveFile {
    private String fileName = "./Assets/savadata/save.txt";
    private File file = new File(fileName);
    private int[] saveData;
    
    /**
     * Read the save file.
     * 
     * Initialize the saveData with the default values,
     *     these are values impossible to reach by normal save data.
     *     Therefore, the game will know if the save file is empty. (values don't change)
     * 
     * Use a do-while loop to be able to check if the line is null.
     *     If the line in not null, and not a blank line, save the value in the saveData array.
     *   If the line is null, the loop will exit, because the file ended.
     * 
     * @return The save data as an array of integers.
     */
    int[] readSaveFile() {
        saveData = new int[]{1, -1, -1, -1};
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int i = 0;
            
            do {
                line = bufferedReader.readLine();
                if (!"".equals(line) && line != null) { 
                    saveData[i] = Integer.valueOf(line);
                }
                i++;
            } while (line != null && i < 4);

            bufferedReader.close();
            return saveData;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
