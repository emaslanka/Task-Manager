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


        Scanner choseOne = new Scanner(System.in);
        String Input = choseOne.nextLine();  // User can choose one task

        switch (Input) {
            case "Add": {
                Addtask();
                break;
            }
            case "List": {
                //REad a file and saving datas in 2D array, check if file exists
                try {
                    readFile();
                } catch (IOException E) {
                    E.printStackTrace();
                }
                break;
            }
            case "Exit": {
                System.out.println(ConsoleColors.PURPLE+"Bye bye. I hope to see you again");
                break;
            }
            default: {
                System.out.println(ConsoleColors.RED + "PLease, select correct option");
            }
        }

        choseOne.close();


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
                System.out.println(j + " " + Arrays.toString(TasksDatesIMp[j]));//LIst of tasks is viewed
            }


        } catch (IOException E) {
            E.printStackTrace();
        }

    }



    public static void Addtask()
    {
        Scanner add = new Scanner(System.in);
        System.out.println("Please add task description :");
        String descripton = add.nextLine();
        System.out.println("PLease add task due date YYYY-MM-DD:");
        String date = add.nextLine();
        System.out.println("Is your task important?  true/false");
        String importance = add.nextLine();


        add.close();


    }

}
