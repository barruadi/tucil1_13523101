package utils;

import java.util.Scanner;

public class Utils {
    String colors[] = {
        "\u001B[30m", // Black
        "\u001B[31m", // Red
        "\u001B[32m", // Green
        "\u001B[33m", // Yellow
        "\u001B[34m", // Blue
        "\u001B[35m", // Magenta
        "\u001B[36m", // Cyan
        "\u001B[37m", // White
        "\u001B[90m", // Bright Black (Gray)
        "\u001B[91m", // Bright Red
        "\u001B[92m", // Bright Green1
        "\u001B[93m", // Bright Yellow
        "\u001B[94m", // Bright Blue
        "\u001B[95m", // Bright Magenta
        "\u001B[96m", // Bright Cyan
        "\u001B[97m", // Bright White
        "\u001B[38;5;202m", // Orange
        "\u001B[38;5;208m", // Deep Orange
        "\u001B[38;5;214m", // Light Orange
        "\u001B[38;5;172m", // Gold
        "\u001B[38;5;120m", // Lime Green
        "\u001B[38;5;57m",  // Purple
        "\u001B[38;5;198m", // Pink
        "\u001B[38;5;118m", // Light Green
        "\u001B[38;5;27m",  // Deep Blue
        "\u001B[38;5;135m"  // Lavender
    };

    String reset = "\u001B[0m";

    public void printColor(char alphabet) {
        if (alphabet == '#' || alphabet == '.') {
            System.out.print(' ');
            return;
        }
        System.out.print(colors[alphabet - 'A'] + alphabet + reset);
    };

    public static void printWelcome() {

    };

    public static String readInput(Scanner input, String line) {
        System.out.print(line);
        String result = input.nextLine();
        return result;
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    public static int findIndex(char[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] != '.') {
                return i;
            }
        }
        return -1;
    }
}