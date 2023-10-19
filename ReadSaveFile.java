import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;

public class ReadSaveFile {
    private String fileName = "./save.txt"; // Replace with the path to your file
    private File file = new File(fileName);
    private int[] saveData = new int[3];

    int[] readSaveFile() {
        try {
            // Read the file
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            System.out.println("Contents of the file:");
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line); // Print each line to the console
                saveData[i] = Integer.valueOf(line);
                i++;
            }
            bufferedReader.close();
            return saveData;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
