import java.util.Scanner;
import java.util.ArrayList;

import utils.*;

public class Main {

    public static Scanner keyboardInput = new Scanner(System.in);
    static InputOutput fileInput = new InputOutput();
    static String result = "";
    static boolean status = false;
    // public static int iteration = 0;
    
    public static void main(String[] args) {
        // WELCOME!
        Utils.clearScreen();
        Utils.printWelcome();

        // INPUT
        while (true) {
            result = Utils.readInput(keyboardInput, "filename: ");
            if (fileInput.fileExist(result)) break;
            System.out.println("File not found.");
        }
        System.out.println();

        Board board = fileInput.readFile(result);
        if (board.getBlockAmount() == 0) {
            // error
            // System.out.println("Input tidak valid.");
            return;
        }
        ArrayList<Block> blocks = board.blocks;

        // START TIMER
        long startTime = System.currentTimeMillis();

        // ALGORITHM
        Algorithm algorithm = new Algorithm(board, blocks);
        if (algorithm.algorithmV2(0, 0)) {
            // solved
            board.printBoard();
            status = true;
        } else {
            System.out.println("tidak ada solusi!");
        }
        
        // END TIMER
        long endTime = System.currentTimeMillis();

        // OUTPUT
        long executionTime = endTime - startTime;
        if (status) {
            System.out.println("\nWaktu Pencarian: " + executionTime + " ms\n");
            System.out.println("Banyak kasus ditinjau: " + algorithm.getIteration() + "\n");
            result = Utils.readInput(keyboardInput, "Apakah anda ingin menyimpan solusi? (ya/tidak): ");
            if (result.equals("ya")) {
                result = Utils.readInput(keyboardInput, "filename: ");
                fileInput.writeFile(result, board, algorithm.getIteration(), executionTime);
                System.out.println("Solusi berhasil disimpan di" + result + ".txt");
            } else {
                System.out.println("hasil tidak disimpan!");
            }
        }

        System.out.println("Terima kasih!");
    }
}