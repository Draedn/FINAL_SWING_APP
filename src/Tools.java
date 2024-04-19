/* ###################################
Title: Tool class.
Author: Draedn Groves
Date: April.15th/2024
Purpose: Tools class for a variety of tools including validation and other methods
that can be used throughout the program.
################################### */

/* I had copilot [Precise] generate all the Java Docs for this file.
                        On : April.18th
 */

/**
 * Public Tools Class
 */
public class Tools {

    /**
     * Checks if a string can be parsed to a double.
     * @param inputString The string to check.
     * @return True if the string can be parsed to a double, false otherwise.
     * @throws NumberFormatException If the string cannot be parsed to a double.
     */
    public static boolean isStringDouble(String inputString) throws NumberFormatException {
        try {
            double test = Double.parseDouble(inputString);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error! Invalid decimal value: " + inputString);
        }
    }

    /**
     * Checks if a string can be parsed to an integer.
     * @param inputString The string to check.
     * @return True if the string can be parsed to an integer, false otherwise.
     * @throws NumberFormatException If the string cannot be parsed to an integer.
     */
    public static boolean isStringInteger(String inputString) throws NumberFormatException {
        try {
            int test = Integer.parseInt(inputString);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error! Invalid integer value: " + inputString);
        }
    }

    /**
     * Validates a string with conditions.
     * @param str The string to validate.
     * @return True if the string is valid, false otherwise.
     * @throws IllegalArgumentException If the string is null, empty, or contains non-alphabetical characters.
     */
    public static boolean validateStringWithConditions(String str) throws IllegalArgumentException {
        if (str == null || str.isEmpty() || !str.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Error! Invalid string value: " + str);
        }
        return true;
    }

    /**
     * Validates a string and trims it.
     * @param str The string to validate and trim.
     * @return The trimmed string if it is valid.
     * @throws IllegalArgumentException If the string is null or empty after trimming.
     */
    public static String validateAndTrimString(String str) throws IllegalArgumentException {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("Error! Invalid string value: " + str);
        }
        return str.trim();
    }

}
