package utils;

import java.util.ArrayList;

public class Block {
    private int lengthBlock;
    private int widthBlock;
    private char[][] block;
    private char letter;

    private ArrayList<char[][]> allVariantBlock = new ArrayList<char[][]>();

    // constructor
    public Block(int lengthBlock, int widthBlock, char blockLetter) {
        this.lengthBlock = lengthBlock;
        this.widthBlock = widthBlock;
        this.block = new char[lengthBlock][widthBlock];
        this.letter = blockLetter;
    }

    public Block(int lengthBlock, int widthBlock, char[][] block) {
        this.lengthBlock = lengthBlock;
        this.widthBlock = widthBlock;
        this.block = block;
    }


    // getter
    public int getLengthBlock() {
        return this.lengthBlock;
    }

    public int getWidthBlock() {
        return this.widthBlock;
    }

    public char[][] getBlock() {
        return this.block;
    }

    public char getLetter() {
        return this.letter;
    }

    public ArrayList<char[][]> getAllVariantBlock() {
        ArrayList<char[][]> temp = new ArrayList<char[][]>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                temp.add(block);
                block = rotateBlock(block);
            }
            mirrorBlock();
        }
        return temp;
    }


    // basic functions
    public void shrinkBlock() {
        Integer maxWidth = 0;
        Integer maxLength = 0;
        for (int i = 0; i < lengthBlock; i++) {
            for (int j = 0; j < widthBlock; j++) {
                if (block[i][j] == letter) {
                    maxLength = Math.max(maxLength, i + 1);
                    maxWidth = Math.max(maxWidth, j + 1);     
                }
            }
        }
        this.lengthBlock = maxLength;
        this.widthBlock = maxWidth;

        char[][] newBlock = new char[maxLength][maxWidth];
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxWidth; j++) {
                newBlock[i][j] = '.';
            }
        }

        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < maxWidth; j++) {
                if (this.block[i][j] == letter) {
                    newBlock[i][j] = letter;
                }
            }
        }
        this.block = newBlock;
    }

    public void addRow(String row, int index) {
        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == letter) {
                this.block[index][i] = letter;
            } else {
                this.block[index][i] = '.';
            }
        }
    }

    public char[][] rotateBlock(char[][] block) {
        int newwidthBlock = block[0].length;
        int newlengthBlock = block.length;
        char[][] rotatedBlock = new char[newwidthBlock][newlengthBlock];
        for (int i = 0; i < newlengthBlock; i++) {
            for (int j = 0; j < newwidthBlock; j++) {
                rotatedBlock[j][newlengthBlock - 1 - i] = block[i][j];
            }
        }
        return rotatedBlock;
    }

    public void mirrorBlock() {
        char[][] mirroredBlock = new char[lengthBlock][widthBlock];
        for (int i = 0; i < lengthBlock; i++) {
            for (int j = 0; j < widthBlock; j++) {
                mirroredBlock[i][widthBlock - 1 - j] = block[i][j];
            }
        }
        this.block = mirroredBlock;
    }

    public void printBlock() {
        for (int i = 0; i < lengthBlock; i++) {
            for (int j = 0; j < widthBlock; j++) {
                if (block[i][j] == letter) {
                    new Utils().printColor(letter);
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}
