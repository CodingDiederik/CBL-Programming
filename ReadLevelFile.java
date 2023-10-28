import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Class to read the save file.
 */
public class ReadLevelFile {

    // Create a new array to store the level data
    private ArrayList<String> levelList = new ArrayList<>(); 
    private File file;

    /**
     * Read the level file.
     * 
     * Use a do-while loop to be able to check if the line is null.
     *     If the line in not null, and not a blank line, save the value in the levelList array.
     *   If the line is null, the loop will exit, because the file ended.
     * 
     * @param levelNumber The level number to read.
     * @return The level data as an array of strings.
     */
    String[] readLevelFile(int levelNumber) {
        try {
            file = new File("./Assets/levels/level" + levelNumber + ".txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            do {
                line = bufferedReader.readLine();
                if (!"".equals(line) && line != null) {
                    levelList.add(line); 
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
