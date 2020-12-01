package pl.coderslab;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        //Viewing all tasks available
        ViewofTasks();

        //REad a file and saving datas in 2D array
        try {
            readFile();
        } catch (IOException E) {
            E.printStackTrace();
        }

    }

    public static void ViewofTasks() {
        System.out.println(ConsoleColors.BLUE + "Please, select an option :");

        String[] TasksNames = {"Add", "Remove", "List", "Exit"};

        for (String tasksName : TasksNames) {
            System.out.println(ConsoleColors.WHITE + tasksName);
        }
    }



    public static void readFile() throws IOException {

        Path path1 = Paths.get("tasks.csv");

        //List to collect lines from file
        List<String[]> Lines = new ArrayList<>();


        try {
            Scanner scan = new Scanner(path1);
            String line;

            while (scan.hasNextLine()) {
                line = scan.nextLine();
                String[] splited = line.split(", ");        // save splited by ", " line to new 1d array
                Lines.add(splited);                               // 1d array is added to List
            }

            String[][] TasksDatesIMp = new String[Lines.size()][];  // create 2D array [lines in LIst]
            for (int j = 0; j < TasksDatesIMp.length; j++) {        // every row is fulfilled with one line of list = 1d array
                TasksDatesIMp[j] = Lines.get(j);
            }

            System.out.println(Arrays.deepToString(TasksDatesIMp));  // List of tasks is viewed

        } catch (IOException E) {
            E.printStackTrace();
        }

    }
}
