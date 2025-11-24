# Smart Student Task Manager

**Author:** Nitin Kumar
**Registration No.:** 24BCE10864

# Java  Student Task Manager

## Project Title
Java Student Task Manager


## Overview
The Student Task Manager allows students to create tasks, edit them, delete them, and view them in an organised way.
It uses CSV-based storage so the data stays saved even after the program is closed.
## Features
- Add new tasks
- View all tasks in a clean table-like format
- Edit any existing task
- Delete tasks easily
- Task attributes include:

Title

Description

Priority (High / Medium / Low)

Deadline (YYYY-MM-DD)
- CSV-based simple file storage
- Smooth menu-driven user interface
- No external libraries needed

## Technologies / Tools
Java 11+

CSV file storage

data/tasks.csv
## How to compile & run
1.Make sure you have Java 11 or above installed.
2.Open the terminal and navigate to the project folder.
3.Compile the project:

javac src/*.java src/models/*.java src/services/*.java src/utils/*.java
4.Run the project:

java -cp src Main
   

## Testing
- To test everything works properly:

- Start the program.

- Add a few tasks with different priorities.

- View all tasks and check if they are saved correctly.

- Edit a task and verify the updated data.

- Delete a task and confirm it is removed from data/tasks.csv.

## Notes
- Data is stored in plain CSV files, making the project simple to understand for students.

The project follows basic Java OOP structure — easy for beginners to modify or extend.

Feel free to add new features like notifications, sorting, or colour formatting.
