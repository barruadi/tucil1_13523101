package utils;

import java.util.ArrayList;

public class Board {
    private int length;
    private int width;
    private int blockAmount;
    private char[][] board;

    public ArrayList<Block> blocks;
    public ArrayList<Block> allVariantBlock = new ArrayList<Block>();
    public ArrayList<Block> blocksUsed = new ArrayList<Block>();
    public ArrayList<Character> usedLetter = new ArrayList<Character>();

    // constructor
    public Board(int length, int width, ArrayList<Block> blocks) {
        this.length = length;
        this.width = width;
        this.blocks = blocks;
        this.board = new char[length][width];
        this.blockAmount = blocks.size();
        // getAllVariantShapes();

        // default board
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = '.';
            }
        }
    }

    // getter
    public int getLength() {
        return this.length;
    }

    public int getWidth() {
        return this.width;
    }

    public int getBlockAmount() {
        return this.blockAmount;
    }

    public char getBoardLocation(int x, int y) {
        return this.board[x][y];
    }


    // private void getAllVariantShapes() {
    //     for (int i = 0; i < blocks.size(); i++) {
    //         Block newBlock = blocks.get(i);
    //         for (int j = 0; j < 2; j++) {
    //             for (int k = 0; k < 4; k++) {
    //                 char[][] newRotatedBlock = newBlock.rotateBlock();
    //                 allVariantBlock.add(newBlock);
    //             }
    //             newBlock.mirrorBlock();    
    //         }
    //     }
    //     return;
    // }

    // fucntions
    public void printBoard() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                // TODO: print letter with color
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public void printAvailableBlock() {
        System.out.print("available blocks: ");
        for (int i = 0; i < blocks.size(); i++) {
            System.out.print(blocks.get(i).getLetter() + " ");
        }
        System.out.println();
    }

    public void printUsedBlock() {
        System.out.print("used blocks: ");
        for (int i = 0; i < blocksUsed.size(); i++) {
            System.out.print(blocksUsed.get(i).getLetter() + " ");
        }
        System.out.println();
    }

    public boolean addBlock(Block block, int x, int y) {
        char[][] tempBoard = new char[length][width];
        for (int i = 0; i < length; i++) {
            System.arraycopy(board[i], 0, tempBoard[i], 0, width);
        }
        for (int i = 0; i < block.getLengthBlock(); i++) {
            for (int j = 0; j < block.getWidthBlock(); j++) {
                if (usedLetter.contains(block.getLetter())) {
                    return false;
                };

                if (block.getBlock()[i][j] == block.getLetter()) {
                    // check if its out of bound or invalid
                    if (x + i >= length || y + j >= width || x + i < 0 || y + j < 0) {
                        return false;
                    } else if (tempBoard[x + i][y + j] != '.') {
                        return false;
                    }
                    tempBoard[x + i][y + j] = block.getLetter();
                }
            }
        }
        blocksUsed.add(block);
        usedLetter.add(block.getLetter());
        this.board = tempBoard;
        return true;
    }

    public boolean validLocation(char[][] block, int x, int y) {
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                if (block[i][j] != '.') {
                    if (x + i >= length || y + j >= width || x + i < 0 || y + j < 0) {
                        return false;
                    } else if (board[x + i][y + j] != '.') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void addBlockUsingMatrix(char[][] block, int x, int y) {
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                if (block[i][j] != '.') {
                    board[x + i][y + j] = block[i][j];
                }
            }
        }
    }

    public void removeBlockUsingMatrix(char[][] block, int x, int y, char letter) {
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                if (block[i][j] == letter) {
                    board[x + i][y + j] = '.';
                }
            }
        }
    }

    public void removeBlock(Block block, int x, int y) {
        for (int i = 0; i < block.getLengthBlock(); i++) {
            for (int j = 0; j < block.getWidthBlock(); j++) {
                if (block.getBlock()[i][j] == block.getLetter()) {
                    board[x + i][y + j] = '.';
                    // remove block if removed, but check if its valid
                }
            }
        }
    }

    public int[] findEmptyLocation() {
        int[] location = new int[2];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == '.') {
                    location[0] = i;
                    location[1] = j;
                    return location;
                }
            }
        }
        location[0] = -1;
        location[1] = -1;
        return location;
    }
}
