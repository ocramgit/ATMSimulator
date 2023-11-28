import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

    private static File file = new File("resources/audit.txt");

    public static void write(String message) throws IOException {

        if(!file.exists()) throw new FileNotFoundException("File not found.");

        try (FileWriter log = new FileWriter(file)) {
            log.write(message);
            log.flush();
        } catch (Exception e) {
            System.out.println("Can't write on this file.");
        }
    }
}
