import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;

public class CreateSaveFile {
    private String fileName = "./save.txt"; // Replace with the path to your file
    private File file = new File(fileName);

    public void createSaveFile(String content) {
        try {
            // Update the file by appending new content
            String newContent = content;
            FileWriter fileWriter = new FileWriter(fileName, false); // The 'true' parameter appends to the file.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newContent);
            bufferedWriter.newLine(); // Add a newline for clarity
            bufferedWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
