/* **********************************
File:       TaskManager.java
Author:     Your Name
Date:       April 2024
Purpose:    TaskManager class for managing tasks
************************************* */

/**
 * All the imports required
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;

/**
 * Public Class TaskManager
 */
public class TaskManager {
    private ArrayList<Task> tasks;
    private JFrame frame;
    private JTextField txtTask;
    private JButton btnNext, btnPrev, btnSave, btnAdd, btnLoad, btnWrite;
    private JLabel taskID;
    private int currentTaskIndex = 0;

    /**
     * Public Method, Task Manager.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
        createFormObjects();
        loadTasksFromFile();
    }

    /**
     * Creates the form objects aka, the GUI itself.
     */
    private void createFormObjects() {
        frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        /**
         * Adds new features to JPanel with naming conventions
         * that make sense.
         */
        txtTask = new JTextField();
        taskID = new JLabel();
        btnNext = new JButton("Next");
        btnPrev = new JButton("Previous");
        btnSave = new JButton("Save");
        btnAdd = new JButton("Add");
        btnLoad = new JButton("Load");
        btnWrite = new JButton("Write");

        /**
         * Sets tooltips for accessibility for users
         */
        btnNext.setToolTipText("Click to view the next task");
        btnPrev.setToolTipText("Click to view the previous task");
        btnSave.setToolTipText("Click to save the current task");
        btnAdd.setToolTipText("Click to add a new task");
        btnLoad.setToolTipText("Click to load tasks from file");
        btnWrite.setToolTipText("Click to write tasks to file");

        /**
         * Sets Keyboard actions.
         */
        frame.getRootPane().setDefaultButton(btnSave); // Sets the default button to respond to Enter key
        /* Obtained this from Copilot. "How would I implement functions in my java Swing
        application such that, the user doesn't rely solely on a mouse */

        /**
         * Added action listeners for next task button
         */
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentTaskIndex < tasks.size() - 1) {
                    currentTaskIndex++;
                    displayCurrentTask();
                }
            }
        });

        /**
         * Added action listeners for previous task button
         */
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentTaskIndex > 0) {
                    currentTaskIndex--;
                    displayCurrentTask();
                }
            }
        });

        /**
         * Added action listeners for save task button
         */
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to save the current task
            }
        });

        /**
         * Added action listeners for add task button
         */
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to add a new task
            }
        });

        /**
         * Added action listeners for load task button
         */
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTasksFromFile();
            }
        });

        /**
         * Added action listeners for write task button
         */
        btnWrite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeTasksToFile();
            }
        });

        /**
         * Added buttons to the top of the panel.
         */
        topPanel.add(btnPrev);
        topPanel.add(btnNext);
        topPanel.add(btnSave);
        topPanel.add(btnAdd);       // For some reason, I couldn't figure out how to place them properly.
        topPanel.add(btnLoad);
        topPanel.add(btnWrite);
        topPanel.add(taskID);

        /**
         * Added a text field to the bottom of the panel.
         * Changed Some border elements, set it to visible (so we can see it)
         */
        bottomPanel.add(txtTask);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.CENTER);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Method to display current task
     * @returns taskID & String output
     */
    private void displayCurrentTask() {
        if (tasks.size() > 0 && currentTaskIndex >= 0 && currentTaskIndex < tasks.size()) {
            Task currentTask = tasks.get(currentTaskIndex);
            txtTask.setText(currentTask.getDescription());
            taskID.setText("Task ID: " + currentTask.getTaskID());
        }
    }

    /**
     * Load tasks method
     * Checks if a file exists, if not, it tries to create one.
     * If it does exist, it should print a prompt respectively.
     * If it catches the error, it will also display that.
     */
    private void loadTasksFromFile() {
        tasks.clear();
        File file = new File("tasks.txt");
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (tasks.size() > 0) {
            currentTaskIndex = 0;
            displayCurrentTask();
        }
    }

    /**
     * Write tasks method
     * Loops through the array list of tasks and prints them to a String.
     */
    private void writeTasksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("tasks.txt"))) {
            for (Task task : tasks) {
                writer.println(task.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
