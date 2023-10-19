import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;

public class ReadSaveFile {
    private String fileName = "./save.txt"; // Replace with the path to your file
    private File file = new File(fileName);

    public ReadSaveFile() {
        try {
            // Read the file
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            System.out.println("Contents of the file:");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line); // Print each line to the console
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ReadSaveFile();
    }
}
