// Author: Nitin Kumar
// Registration No.: 24BCE10864
// Project: Smart Student Task Manager

package smarttask;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static TaskManager manager;
    public static void main(String[] args) {
        String dataFile = System.getProperty("user.home") + "/.smarttask/tasks.txt";
        manager = new TaskManager(dataFile);
        greet();
        while (true) {
            showMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": addTask(); break;
                case "2": listTasks(); break;
                case "3": searchTasks(); break;
                case "4": updateTask(); break;
                case "5": deleteTask(); break;
                case "6": completeTask(); break;
                case "7": System.out.println("Goodbye!"); System.exit(0); break;
                default: System.out.println("Invalid choice. Try again."); 
            }
        }
    }
    private static void greet() {
        System.out.println("\n=== Smart Student Task Manager ===\nWelcome! This tool helps you manage tasks, deadlines, and priorities.\n");
    }
    private static void showMenu() {
        System.out.println("\n1) Add Task\n2) List All Tasks\n3) Search Tasks\n4) Update Task\n5) Delete Task\n6) Mark Task Completed\n7) Exit\nChoose: ");
    }
    private static void addTask() {
        try {
            System.out.print("Title: "); String title = sc.nextLine();
            System.out.print("Description: "); String desc = sc.nextLine();
            System.out.print("Priority (HIGH/MEDIUM/LOW): "); Task.Priority p = Task.Priority.valueOf(sc.nextLine().toUpperCase());
            System.out.print("Due date (YYYY-MM-DD): "); LocalDate due = LocalDate.parse(sc.nextLine());
            manager.addTask(title, desc, p, due);
            System.out.println("Task added successfully.");
        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }
    private static void listTasks() {
        List<Task> list = manager.listAll();
        if (list.isEmpty()) { System.out.println("No tasks found."); return; }
        System.out.println("\nID | Title | Priority | Due Date | Completed\n-------------------------------------------");
        for (Task t : list) {
            System.out.printf("%d | %s | %s | %s | %b\n", t.getId(), t.getTitle(), t.getPriority(), t.getDueDate(), t.isCompleted());
        }
    }
    private static void searchTasks() {
        System.out.print("Enter keyword: ");
        List<Task> res = manager.search(sc.nextLine());
        if (res.isEmpty()) { System.out.println("No matching tasks."); return; }
        for (Task t : res) {
            System.out.printf("%d | %s | %s | %s | %b\n", t.getId(), t.getTitle(), t.getPriority(), t.getDueDate(), t.isCompleted());
        }
    }
    private static void updateTask() {
        try {
            System.out.print("Enter task id to update: "); int id = Integer.parseInt(sc.nextLine());
            Task t = manager.getTaskById(id);
            if (t == null) { System.out.println("Task not found."); return; }
            System.out.print("New Title (leave blank to keep): "); String title = sc.nextLine(); if (title.isBlank()) title = t.getTitle();
            System.out.print("New Description (leave blank to keep): "); String desc = sc.nextLine(); if (desc.isBlank()) desc = t.getDescription();
            System.out.print("New Priority (HIGH/MEDIUM/LOW, leave blank to keep): "); String pr = sc.nextLine(); Task.Priority p = pr.isBlank() ? t.getPriority() : Task.Priority.valueOf(pr.toUpperCase());
            System.out.print("New Due Date (YYYY-MM-DD, leave blank to keep): "); String dd = sc.nextLine(); LocalDate due = dd.isBlank() ? t.getDueDate() : LocalDate.parse(dd);
            System.out.print("Mark completed? (true/false, leave blank to keep): "); String cm = sc.nextLine(); boolean completed = cm.isBlank() ? t.isCompleted() : Boolean.parseBoolean(cm);
            manager.updateTask(id, title, desc, p, due, completed);
            System.out.println("Task updated.");
        } catch (Exception e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }
    private static void deleteTask() {
        try {
            System.out.print("Enter task id to delete: "); int id = Integer.parseInt(sc.nextLine());
            if (manager.deleteTask(id)) System.out.println("Deleted."); else System.out.println("Not found.");
        } catch (Exception e) { System.out.println("Delete error: " + e.getMessage()); }
    }
    private static void completeTask() {
        try {
            System.out.print("Enter task id to mark completed: "); int id = Integer.parseInt(sc.nextLine());
            Task t = manager.getTaskById(id);
            if (t == null) { System.out.println("Task not found."); return; }
            manager.updateTask(id, t.getTitle(), t.getDescription(), t.getPriority(), t.getDueDate(), true);
            System.out.println("Task marked completed.");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
}
