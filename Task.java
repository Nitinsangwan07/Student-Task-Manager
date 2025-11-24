// Author: Nitin Kumar
// Registration No.: 24BCE10864
// Project: Smart Student Task Manager

package smarttask;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Task {
    private static int counter = 1;
    private int id;
    private String title;
    private String description;
    private Priority priority;
    private LocalDate dueDate;
    private boolean completed;
    public enum Priority { HIGH, MEDIUM, LOW }
    public Task(String title, String description, Priority priority, LocalDate dueDate) {
        this.id = counter++;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false;
    }
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Priority getPriority() { return priority; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isCompleted() { return completed; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("%d|%s|%s|%s|%s|%b", id, title.replace("|","/"), description.replace("|","/"), priority, dueDate.format(f), completed);
    }
    public static Task fromString(String line) {
        try {
            String[] p = line.split("\\|");
            if (p.length < 6) return null;
            Task t = new Task(p[1], p[2], Priority.valueOf(p[3]), LocalDate.parse(p[4]));
            t.id = Integer.parseInt(p[0]);
            t.completed = Boolean.parseBoolean(p[5]);
            if (t.id >= counter) counter = t.id + 1;
            return t;
        } catch (Exception e) {
            return null;
        }
    }
}
