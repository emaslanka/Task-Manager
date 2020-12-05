package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {


    public static void main(String[] args) {

        //ViewofTasks();
        System.out.println("Hello, My FRiend ! Click any button + enter to start work");
        Scanner choseOne = new Scanner(System.in);
        String Input = choseOne.nextLine();                                                                 // User can choose one task
                                                                         // Reading table from file, and getting it to 2D Table

        do {
            String[][] TasksDatesIMp = getTableStrings();
            ViewofTasks();
            Input = choseOne.nextLine();

            switch (Input) {
                case "Add": {
                    String[][] tab = Addtask(TasksDatesIMp);
                    SavetoFile(tab);
                    break;
                }
                case "List": {
                    try {                                                                                                   //REad a file and saving datas in 2D array, check if file exists
                        readFile();
                    } catch (IOException E) {
                        E.printStackTrace();
                    }
                    break;
                }


                case "Remove": {                                                                                             //Removing task
                    System.out.println("Please select number to remove ?");
                    String number = choseOne.nextLine();

                    String[][] reducedTable = RemoveTask(TasksDatesIMp, number);
                    SavetoFile(reducedTable);
                    break;
                }

                case "Exit": {
                    System.out.println(ConsoleColors.PURPLE + "Bye bye. I hope to see you again");
                    break;
                }
                default: {
                    System.out.println(ConsoleColors.RED + "PLease, select correct option");
                }
            }

        }
        while(!Input.equals("Exit"));

        choseOne.close();

    }


    //Methods
    public static String[][] getTableStrings() {

        Path path1 = Paths.get("tasks.csv");
        List<String[]> Lines = new ArrayList<>();                                                                       //List to collect lines from file
        String[][] TasksDatesIMp = {};

        try {
            Scanner scan = new Scanner(path1);
            String line;

            while (scan.hasNextLine()) {
                line = scan.nextLine();
                String[] splited = line.split(", ");                                                              // save splited by ", " line to new 1d array
                Lines.add(splited);                                                                                     // 1d array is added to List
            }

            TasksDatesIMp = new String[Lines.size()][];                                                                 // create 2D array [lines in LIst]
            for (int j = 0; j < TasksDatesIMp.length; j++) {                                                            // every row is fulfilled with one line of list = 1d array
                TasksDatesIMp[j] = Lines.get(j);
            }

        } catch (IOException E) {
            E.printStackTrace();
        }
        return TasksDatesIMp;
    }


    public static void SavetoFile(String[][] tab) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                builder.append(tab[i][j] + ", ");
            }
            builder.append("\n");
        }

        try {                                                                                                         //Saving to file

            BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.csv"));
            writer.write(builder.toString());
            writer.close();

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

        String[][] TabletoRead = getTableStrings();
        for (int i = 0; i < TabletoRead.length; i++) {
            System.out.println(ConsoleColors.YELLOW + (i + 1) + " " + Arrays.toString(TabletoRead[i]));
        }
    }


    public static String[][] Addtask(String[][] TasksDatesIMp) {

        Scanner add = new Scanner(System.in);
        System.out.println("Please add task description :");
        String descripton = add.nextLine();
        System.out.println("PLease add task due date YYYY-MM-DD:");
        String date = add.nextLine();
        System.out.println("Is your task important?  true/false");
        String importance = add.nextLine();

        String[][] newTaskDatesImp = new String[TasksDatesIMp.length + 1][TasksDatesIMp[0].length];

        for (int i = 0; i < newTaskDatesImp.length - 1; i++) {

            System.arraycopy(TasksDatesIMp[i], 0, newTaskDatesImp[i], 0, TasksDatesIMp[i].length);

        }

        newTaskDatesImp[newTaskDatesImp.length - 1][0] = descripton;                                                     //inserting new values to
        newTaskDatesImp[newTaskDatesImp.length - 1][1] = date;
        newTaskDatesImp[newTaskDatesImp.length - 1][2] = importance;


        return newTaskDatesImp;


    }

    public static String[][] RemoveTask(String[][] TasksDatesIMp, String number) {


        if (NumberUtils.isParsable(number) && Integer.parseInt(number) > 0) {
            if (Integer.parseInt(number) <= TasksDatesIMp.length) {

                int numbertoDel = Integer.parseInt(number) - 1;
                TasksDatesIMp = ArrayUtils.remove(TasksDatesIMp, numbertoDel);
            } else {
                System.out.println("Value is greater than amount of tasks.");
            }
        } else {
            System.out.println("Incorrect value. Try again");
        }

       return TasksDatesIMp;

    }
}
