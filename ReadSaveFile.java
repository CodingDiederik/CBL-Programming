import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Class to read the save file.
 */
public class ReadSaveFile {
    private String fileName = "./save.txt"; // Replace with the path to your file
    private File file = new File(fileName);
    private int[] saveData = {-1, -1, -1};
    
    int[] readSaveFile() {
        try {
            // Read the file
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            System.out.println("Contents of the file:");
            int i = 0;
            //while ((line = bufferedReader.readLine()) != null && i < 3) {
            //    System.out.println(line); // Print each line to the console
            //    saveData[i] = Integer.valueOf(line);
            //    i++;
            //}
            //bufferedReader.close();
            //return saveData;
            do {
                line = bufferedReader.readLine();
                System.out.println(line); // Print each line to the console
                if (!"".equals(line) && line != null) {
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
