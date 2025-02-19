import java.util.Scanner;
import java.util.ArrayList;

import utils.*;

public class Main {

    public static Scanner keyboardInput = new Scanner(System.in);
    private Input input = new Input();
    private Board board = input.readFile();
    private ArrayList<Block> blocks = board.blocks;

    public boolean algorithm(int index) {
        if (index == board.getBlockAmount()) {
            return true;
        }

        Block currentBlock = blocks.get(index);

        int[] emptyLocation = board.findEmptyLocation();

        for (char[][] blockVariant: currentBlock.getAllVariantBlock()) {
            if (board.validLocation(blockVariant, emptyLocation[0], emptyLocation[1])) {
                board.addBlockUsingMatrix(blockVariant, emptyLocation[0], emptyLocation[1]);
                if (algorithm(index + 1)) {
                    return true;
                }
                // backtrack
                board.removeBlockUsingMatrix(blockVariant, emptyLocation[0], emptyLocation[1], currentBlock.getLetter());;
            }
        }
        return false;
    }

    public void solve() {
        if (algorithm(0)) {
            board.printBoard();
        } else {
            System.out.println("No solution");
            board.printBoard();
        }
    }
    
    public static void main() {
        // welcome

        // menu to select txt input

        // read input

        // start timer

        // algorithm
        Main main = new Main();
        main.solve();

        // end timer

        // output
        // board.printBoard();
    }
}