package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner; 

public class InputOutput {
    static char[][] layout;

    public boolean fileExist(String filename) {
        return new File("test/input/" + filename + ".txt").isFile();
    };

    public char findChar(String row) {
        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) != ' ') {
                return row.charAt(i);
            }
        }
        return ' ';
    }

    public Board readFile(String filename) {
        try {
            File myObj = new File("test/input/" + filename + ".txt");
            Scanner reader = new Scanner(myObj);

            // read board size
            Integer n = reader.nextInt();
            Integer m = reader.nextInt();


            // read block amount
            Integer p = reader.nextInt();
            reader.nextLine();

            // read shape
            String s = reader.nextLine();
            layout = new char[n][m];
            // TODO: check shape
            if (s.equals("DEFAULT")) {
                // biasa
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        layout[i][j] = '.';
                    }
                }

            } else if (s.equals("CUSTOM")) {
                // TODO: check layout valid
                for (int i = 0; i < n; i++) {
                    String layoutRow = reader.nextLine();
                    for (int j = 0; j < m; j++) {
                        if (layoutRow.charAt(j) == '.') {
                            layout[i][j] = '#';
                        } else {
                            layout[i][j] = '.';
                        }
                    }
                }

            } else if (s.equals("PYRAMID")) {
                // sulit
            }

            // read block shapes
            Integer blockHeight = 1;
            char blockLetter = 'A';
            ArrayList<Block> blocks = new ArrayList<Block>();
            Block newBlock = new Block(n, m, blockLetter);

            for (int i = 0; i < p; i++) {
                // check if its last line
                if (reader.hasNextLine() == false) {
                    newBlock.shrinkBlock();
                    blocks.add(newBlock);
                    break;
                }
            
                // read line
                String row = reader.nextLine();

                // if its new block
                if (findChar(row) != blockLetter) {
                    // add block to list
                    newBlock.shrinkBlock();
                    blocks.add(newBlock);

                    // create new block
                    blockLetter = findChar(row);
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
            reader.close();
            return new Board(n, m, blocks, layout);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // TODO: handle when error
        return new Board(0, 0, null, layout);
    }

    public void writeFile(String filename, Board board, int iteration, long time) {
        try {
            File file = new File("test/output/" + filename + ".txt");
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < board.getLength(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    writer.write(board.getBoardLocation(i, j));
                }
                writer.write("\n");
            }
            writer.write(iteration + "\n");
            writer.write(time + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}