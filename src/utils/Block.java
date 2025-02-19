package utils;

import java.util.ArrayList;

public class Block {
    private int lengthBlock;
    private int widthBlock;
    private char[][] block;
    private char letter;

    // TODO: add color property
    private String color;

    public ArrayList<char[][]> allVariantBlock = new ArrayList<char[][]>();

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
                if (this.block[i][j] == letter) {
                    newBlock[i][j] = letter;
                }
            }
        }
        this.block = newBlock;
    }

    public void addRow(String row, int index) {
        for (int i = 0; i < row.length(); i++) {
            block[index][i] = row.charAt(i);
        }
    }

    public void rotateBlock() {
        char[][] rotatedBlock = new char[widthBlock][lengthBlock];
        for (int i = 0; i < lengthBlock; i++) {
            for (int j = 0; j < widthBlock; j++) {
                rotatedBlock[j][lengthBlock - 1 - i] = block[i][j];
            }
        }
        this.widthBlock = lengthBlock;
        this.lengthBlock = widthBlock;
        this.block = rotatedBlock;
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
                    // TODO: print letter with color
                    System.out.print(letter);
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    public void getOrientations() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                rotateBlock();
                allVariantBlock.add(block);
            }
            mirrorBlock();
        }
    }
}
