import java.util.Scanner;
import java.util.ArrayList;

import utils.*;

public class Main {

    public static Scanner keyboardInput = new Scanner(System.in);
    private Input input = new Input();
    private Board board = input.readFile();
    private ArrayList<Block> blocks = board.blocks;

    public boolean algorithm(int index) {
        // if (index == board.getBlockAmount() - 1) {
        //     return true;
        // }

        Block currentBlock = blocks.get(index);
        for (Block block: blocks) {
            for (char[][] variant: block.allVariantBlock) {
                
            }
        }

        for (int i = 0; i < board.getLength(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.addBlock(currentBlock, i, j)) {
                    if (algorithm(index + 1)) {
                        return true;
                    };
                    // backtrack
                    board.removeBlock(currentBlock, i, j);
                }
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