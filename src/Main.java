import java.util.Scanner;
import java.util.ArrayList;

import utils.*;

public class Main {

    public static Scanner keyboardInput = new Scanner(System.in);
    static InputOutput fileInput = new InputOutput();
    static String result = "";
    // public static int iteration = 0;
    
    public static void main(String[] args) {
        // WELCOME!
        Utils.clearScreen();

        // INPUT
        while (true) {
            result = Utils.readInput(keyboardInput, "filename: ");
            if (fileInput.fileExist(result)) break;
            System.out.println("File not found.");
        }

        Board board = fileInput.readFile(result);
        ArrayList<Block> blocks = board.blocks;

        // START TIMER
        long startTime = System.currentTimeMillis();

        // ALGORITHM
        Algorithm algorithm = new Algorithm(board, blocks);
        if (algorithm.algorithmV2(0, 0)) {
            // solved
            board.printBoard();
        } else {
            System.out.println("no solution!");
        }
        
        // END TIMER
        long endTime = System.currentTimeMillis();

        // OUTPUT
        long executionTime = endTime - startTime;
        System.out.println("iteration: " + algorithm.getIteration());
        System.out.println("executionTime: " + executionTime + " ms");

        result = Utils.readInput(keyboardInput, "save: ");
        if (result.equals("Y")) {
            result = Utils.readInput(keyboardInput, "filename: ");
            fileInput.writeFile(result, board, algorithm.getIteration(), executionTime);
        }
    }
}