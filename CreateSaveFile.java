import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to create the save file (save.txt).
 * 
 * (See documentation for more info on the save file)
 */
public class CreateSaveFile {
    private String fileName = "./Assets/savadata/save.txt";

    /**
     * Rewrite the save file, with the new save data.
     * 
     * @param content The content to write to the save file.
     */
    public void createSaveFile(String content) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(content);
            bufferedWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
