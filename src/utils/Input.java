package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 

public class Input {
    public Board readFile() {
        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);

            // read board size
            Integer n = myReader.nextInt();
            Integer m = myReader.nextInt();

            // read block amount
            Integer p = myReader.nextInt();
            myReader.nextLine();

            // read shape
            String s = myReader.nextLine();
            // TODO: check shape
            if (s == "DEFAULT") {
                // biasa
            } else if (s == "CUSTOM") {
                // read line
            } else if (s == "PYRAMID") {
                // read line
            }

            // read block shapes
            Integer blockHeight = 1;
            char blockLetter = 'A';
            ArrayList<Block> blocks = new ArrayList<Block>();
            Block newBlock = new Block(n, m, blockLetter);

            for (int i = 1; i < p; i++) {
                // check if its last line
                if (myReader.hasNextLine() == false) {
                    newBlock.shrinkBlock();
                    blocks.add(newBlock);
                    break;
                }
            
                // read line
                String row = myReader.nextLine();
                // if its new block
                if (row.charAt(0) != blockLetter) {
                    // add block to list
                    newBlock.shrinkBlock();
                    
                    blocks.add(newBlock);

                    // create new block
                    blockLetter = row.charAt(0);
                    newBlock = new Block(n, m, blockLetter);
                    blockHeight = 1;
                    newBlock.addRow(row, blockHeight - 1);
                    blockHeight++;

                } else {
                    newBlock.addRow(row, blockHeight - 1);
                    blockHeight++;
                    i--;
                }
            }
            myReader.close();
            return new Board(n, m, blocks);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return new Board(0, 0, null);
    }
}