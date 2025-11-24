// Author: Nitin Kumar
// Registration No.: 24BCE10864
// Project: Smart Student Task Manager

package smarttask;
import java.io.*;
import java.nio.file.*;
import java.util.*;
public class FileHandler {
    private Path file;
    public FileHandler(String path) {
        this.file = Paths.get(path);
        try {
            if (Files.notExists(file)) Files.createDirectories(file.getParent());
            if (Files.notExists(file)) Files.createFile(file);
        } catch (IOException e) {
            System.out.println("Error initializing data file: " + e.getMessage());
        }
    }
    public List<Task> loadTasks() {
        List<Task> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            while ((line = br.readLine()) != null) {
                Task t = Task.fromString(line);
                if (t != null) list.add(t);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return list;
    }
    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter bw = Files.newBufferedWriter(file)) {
            for (Task t : tasks) {
                bw.write(t.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
