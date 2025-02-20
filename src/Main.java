import java.util.Scanner;
import java.util.ArrayList;

import utils.*;

public class Main {

    public static Scanner keyboardInput = new Scanner(System.in);
    static InputOutput fileInput = new InputOutput();
    public static Board board;
    static ArrayList<Block> blocks;
    static String result;
    static int iteration = 0;

    public static boolean algorithm(int index) {
        if (index == board.getBlockAmount()) {
            return true;
        }

        Block currentBlock = blocks.get(index);

        int[] emptyLocation = board.findEmptyLocation();

        for (char[][] blockVariant: currentBlock.getAllVariantBlock()) {
            iteration++;
            if (board.validLocation(blockVariant, emptyLocation[0], emptyLocation[1])) {
                board.addBlockUsingMatrix(blockVariant, emptyLocation[0], emptyLocation[1]);
                if (algorithm(index + 1)) {
                    return true;
                };
                // backtrack
                board.removeBlockUsingMatrix(blockVariant, emptyLocation[0], emptyLocation[1], currentBlock.getLetter());;
            }
        }
        return false;
    }

    public static boolean algorithmV2(int index) {
        if (index == board.getBlockAmount()) {
            for (int i = 0; i < board.getLength(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (board.getBoardLocation(i, j) == '.') {
                        return false;
                    }
                }
            }
            return true;
        }
        if (index > board.getBlockAmount()) {
            return false;
        }
        

        Block currentBlock = blocks.get(index);
        ArrayList<char[][]> allVariantBlock = currentBlock.getAllVariantBlock();

        // int[] emptyLocation = board.findEmptyLocation();

        for (int i = 0; i < board.getLength(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                for (char[][] blockVariant: allVariantBlock) {
                    iteration++;
                    if (board.validLocation(blockVariant, i, j)) {
                        board.addBlockUsingMatrix(blockVariant, i, j);
                        if (algorithmV2(index + 1)) {
                            return true;
                        };
                        // backtrack
                        board.removeBlockUsingMatrix(blockVariant, i, j, currentBlock.getLetter());
                    }
                }
            }
        }
        return false;
    }

    public static void solve() {
        if (algorithmV2(0)) {
            // solved
        } else {
            System.out.println("No solution");
        }
    }
    
    public static void main() {

        // welcome
        Utils.clearScreen();

        // input
        while (true) {
            result = Utils.readInput(keyboardInput, "filename: ");
            if (fileInput.fileExist(result)) break;
            System.out.println("File not found.");
        }

        board = fileInput.readFile(result);
        blocks = board.blocks;

        // start timer
        long startTime = System.currentTimeMillis();

        // algorithm
        solve();
        
        // end timer
        long endTime = System.currentTimeMillis();

        // output
        long executionTime = endTime - startTime;
        board.printBoard();
        System.out.println("iteration: " + iteration);
        System.out.println("executionTime: " + executionTime + " ms");

        result = Utils.readInput(keyboardInput, "save: ");
        if (result.equals("Y")) {
            result = Utils.readInput(keyboardInput, "filename: ");
            fileInput.writeFile(result, board, iteration, executionTime);
        }
    }
}