// Author: Nitin Kumar
// Registration No.: 24BCE10864
// Project: Smart Student Task Manager

package smarttask;
import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;
public class TaskManager {
    private List<Task> tasks;
    private FileHandler fh;
    public TaskManager(String dataFile) {
        fh = new FileHandler(dataFile);
        tasks = fh.loadTasks();
    }
    public void addTask(String title, String desc, Task.Priority priority, LocalDate due) {
        Task t = new Task(title, desc, priority, due);
        tasks.add(t);
        fh.saveTasks(tasks);
    }
    public boolean deleteTask(int id) {
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        if (removed) fh.saveTasks(tasks);
        return removed;
    }
    public Task getTaskById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
    public List<Task> listAll() {
        return new ArrayList<>(tasks);
    }
    public List<Task> search(String keyword) {
        String k = keyword.toLowerCase();
        return tasks.stream()
                .filter(t -> t.getTitle().toLowerCase().contains(k) || t.getDescription().toLowerCase().contains(k))
                .collect(Collectors.toList());
    }
    public void updateTask(int id, String title, String desc, Task.Priority priority, LocalDate due, boolean completed) {
        Task t = getTaskById(id);
        if (t != null) {
            t.setTitle(title);
            t.setDescription(desc);
            t.setPriority(priority);
            t.setDueDate(due);
            t.setCompleted(completed);
            fh.saveTasks(tasks);
        }
    }
}
