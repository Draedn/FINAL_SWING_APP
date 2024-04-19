/* ###################################
Title: Task Class.
Author: Draedn Groves
Date: April.15th/2024
Purpose: The Task class will contain various things related to task details.
################################### */


/**
 * Public Task Class
 */
public class Task {

    /**
     * AutoNumbering so that no two tasks are alike
     */
    private static int autoNumber = 0;

    /**
     * Properties
     */
    private int taskID;
    private String description;
    private int priority;


    /**
     * Parameterized Constructor
     * @param description
     * @param priority
     */
    public Task(String description, int priority) {
        this();
        setDescription(description);
        setPriority(priority);
    }


    /**
     * Default Constructor
     * Auto Number so no two tasks are alike.
     */
    public Task() {
        this.taskID = ++autoNumber;

    }


    // <editor-fold desc="Getters and Setters">

    /**
     * Get Description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get Task ID
     * @return taskID
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * Set Description
     * @param description
     */
    public void setDescription(String description) {
        if (Tools.validateStringWithConditions(description)) {
            this.description = description.toLowerCase();
        }
    }

    /**
     * Get Priority
     * @return
     */
    public int getPriority() {  // I should've Used Enums for this.
        return priority;
    }

    /**
     * Set Priority
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
    // </editor-fold>


    /**
     * To string method
     * @return int
     * @return String
     * @return String
     */
    @Override
    public String toString() {
        return taskID + "," + description + "," + priority;
    }

    /**
     * From String method
     * @param str
     * @return
     */
    public static Task fromString(String str) {
        String[] parts = str.split(",");
        if (parts.length == 3) {
            String description = parts[1];
            int priority = Integer.parseInt(parts[2]);
            return new Task(description, priority);
        }
        return null;
    }
}