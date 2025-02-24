package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner; 

public class InputOutput {

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
            Integer n = null;
            if (reader.hasNextInt()) {
                n = reader.nextInt();
            } else {
                System.out.println("input tidak valid\nErrorInfo: N M P tidak sesuai format");
                reader.close();
                return new Board(0, 0, null, new char[0][0]);
            }
            Integer m = null;
            if (reader.hasNextInt()) {
                m = reader.nextInt();
            } else {
                System.out.println("input tidak valid\nErrorInfo: N M P tidak sesuai format");
                reader.close();
                return new Board(0, 0, null, new char[0][0]);
            }


            // read block amount
            Integer p = null;
            if (reader.hasNextInt()) {
                p = reader.nextInt();
            } else {
                System.out.println("input tidak valid\nErrorInfo: N M P tidak sesuai format");
                reader.close();
                return new Board(0, 0, null, new char[0][0]);
            }

            if (n == null || m == null || p == null) {
                System.out.println("error");
            }
            reader.nextLine();

            // read shape
            String s = reader.nextLine();
            char[][] layout = new char[n][m];
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
            Integer totalBlock = 0;
            char blockLetter = '-';
            ArrayList<Block> blocks = new ArrayList<Block>();
            Block newBlock = new Block(n, m, blockLetter);

            for (int i = 0; i < p; i++) {
                if (totalBlock >= p) {
                    System.out.println("input tidak valid\nErrorInfo: total piece melebihi");   
                    reader.close();
                    return new Board(0, 0, null, new char[0][0]);
                }
                // check if its last line
                if (reader.hasNextLine() == false) {
                    newBlock.shrinkBlock();
                    blocks.add(newBlock);
                    totalBlock++;
                    if (totalBlock < p) {
                        System.out.println("input tidak valid \nErrorInfo: total piece kurang");   
                        reader.close();
                        return new Board(0, 0, null, new char[0][0]);
                    }
                    break;
                }
            
                // read line
                String row = reader.nextLine();

                // if its new block
                if (findChar(row) != blockLetter) {
                    // add block to list
                    if (blockLetter != '-') {
                        newBlock.shrinkBlock();
                        blocks.add(newBlock);
                        i--;
                        totalBlock++;

                    }

                    // create new block
                    blockLetter = findChar(row);
                    newBlock = new Block(n, m, blockLetter);
                    blockHeight = 1;
                    if (blockHeight - 1 >= m) {
                        System.out.println("input tidak valid \nErrorInfo: tinggi melebihi piece " + blockLetter);   
                        reader.close();
                        return new Board(0, 0, null, new char[0][0]);
                    }
                    newBlock.addRow(row, blockHeight - 1);
                    blockHeight++;
                } else {
                    if (blockHeight -1 >= m) {
                        System.out.println("input tidak valid \nErrorInfo: tinggi melebihi piece " + blockLetter);   
                        reader.close();
                        return new Board(0, 0, null, new char[0][0]);
                    }
                    newBlock.addRow(row, blockHeight - 1);
                    blockHeight++;
                    i--;
                }
            }
            reader.close();
            return new Board(n, m, blocks, layout);

        } catch (FileNotFoundException e) {
            System.out.println("error..");
            e.printStackTrace();
        }

        return new Board(0, 0, null, new char[0][0]);
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