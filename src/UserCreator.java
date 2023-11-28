import java.io.*;
import java.util.ArrayList;

public class UserCreator {

    private static ArrayList<User> users;

    public UserCreator() {
        users = new ArrayList<>();
    }

    private File file = new File("resources/users.txt");

    public void read() throws IOException {

        if (!file.exists()) throw new FileNotFoundException("File not found.");

        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                User user = UserFactory.createUser(line);
                users.add(user);
            }
        }
        bufferedReader.close();
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}
