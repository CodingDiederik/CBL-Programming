import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class CreateSaveFile {
    private String fileName = "./Assets/savadata/save.txt"; // Replace with the path to your file

    public void createSaveFile(String content) {
        try {
            // Update the file by appending new content
            String newContent = content;
            FileWriter fileWriter = new FileWriter(fileName, false); // The 'true' parameter appends to the file.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newContent);
            //System.out.println("Saved: " + newContent);
            bufferedWriter.newLine(); // Add a newline for clarity
            bufferedWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
