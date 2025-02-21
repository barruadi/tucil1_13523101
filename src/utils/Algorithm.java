package utils;

import java.util.ArrayList;

public class Algorithm {
    private Board board;
    private ArrayList<Block> blocks;
    private int iteration = 0;

    public Algorithm(Board board, ArrayList<Block> blocks) {
        this.board = board;
        this.blocks = blocks;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getIteration() {
        return this.iteration;
    }

    public boolean algorithmV2(int index, int iteration) {
        if (index == board.getBlockAmount()) {
            // make a func in board
            for (int i = 0; i < board.getLength(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (board.getBoardLocation(i, j) == '.') {
                        return false;
                    }
                }
            }
            setIteration(iteration);
            return true;
        }
        if (index > board.getBlockAmount()) {
            return false;
        }
        
        Block currentBlock = blocks.get(index);
        ArrayList<char[][]> allVariantBlock = currentBlock.getAllVariantBlock();

        for (int i = 0; i < board.getLength(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                for (char[][] blockVariant: allVariantBlock) {
                    iteration++;
                    if (board.validLocation(blockVariant, i, j)) {
                        board.addBlockUsingMatrix(blockVariant, i, j);
                        if (algorithmV2(index + 1, iteration)) {
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


}
