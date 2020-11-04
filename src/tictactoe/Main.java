package tictactoe;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final char[][] field = new char[3][3];

    public static void main(String[] args) {
        printManual();
        initField();
        printField();

        int turn = 0;
        boolean gameOver = false;

        while (!gameOver) {
            while (true) {
                System.out.print("Enter the coordinates: ");
                int inputRow, inputCol;

                try {
                    inputCol = scanner.nextInt();
                    inputRow = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("You should enter numbers!");
                    // Since the nextInt() method won't consume any input which is
                    // not an int, and we need the scanner to move the cursor forward
                    // Else, it will keep considering erroneous input (say "one")
                    // Reference: https://stackoverflow.com/questions/3572160/
                    scanner.nextLine();
                    continue;
                }

                if (validCoordinates(inputRow, inputCol)) {
                    int row = getFieldRow(inputRow);
                    int col = getFieldCol(inputCol);

                    // check cell occupancy
                    if (field[row][col] == ' ') {
                        field[row][col] = getPlayer(turn++);
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }

            printField();
            String gameStatus = getGameStatus();
            gameOver = isGameOver(gameStatus);

            if (gameOver) {
                System.out.println(gameStatus);
            }
        }

    }

    private static void printManual() {
        System.out.println("Welcome to Tic Tac Toe on the console!");
        System.out.println();
        System.out.println("Instructions");
        System.out.println("-------------");
        System.out.println("1. The game starts as an empty field.");
        System.out.println("2. To make a move you will need to enter coordinates.");
        System.out.println("3. The first coordinate goes from left to right and the second coordinate goes from bottom to top.");
        System.out.println("4. The bottom left cell has the coordinates (1, 1).");
        System.out.println("5. And, the top right cell has the coordinates (3, 3).");
        System.out.println("6. Input coordinates as space-separated numbers (ex: 1 2) between 1 and 3.");
        System.out.println("7. As a reference, here's a table showing cell coordinates:");
        System.out.println();
        System.out.println("(1, 3)\t(2, 3)\t(3, 3)");
        System.out.println("(1, 2)\t(2, 2)\t(3, 2)");
        System.out.println("(1, 1)\t(2, 1)\t(3, 1)");
        System.out.println();
        System.out.println("The game will start now. Happy gaming!");
    }

    private static void initField() {
        for (char[] vector: field) {
            Arrays.fill(vector, ' ');
        }
    }

    private static void printField() {
        System.out.println();
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("---------");
        System.out.println();
    }

    private static int getCharCount(char c) {
        int count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == c) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean checkRows(char c) {
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] == c) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkColumns(char c) {
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] == c) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkDiagonal(char c) {
        return field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[1][1] == c;

    }

    private static boolean checkCrossDiagonal(char c) {
        return field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[1][1] == c;
    }

    private static String getGameStatus() {
        int xCount = getCharCount('X');
        int oCount = getCharCount('O');
        int spaceCount = getCharCount(' ');

        boolean xWins = checkRows('X') || checkColumns('X') || checkDiagonal('X') || checkCrossDiagonal('X');
        boolean oWins = checkRows('O') || checkColumns('O') || checkDiagonal('O') || checkCrossDiagonal('O');

        if (Math.abs(xCount - oCount) > 1 || (xWins && oWins)) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (spaceCount > 0) {
            return "Game not finished";
        } else {
            return "Draw";
        }
    }

    private static int getFieldRow(int row) {
        return Math.abs(3 - row);
    }

    private static int getFieldCol(int col) {
        return col - 1;
    }

    private static boolean validCoordinates(int row, int col) {
        return row >= 1 && row <= 3 && col >= 1 && col <= 3;
    }

    private static char getPlayer(int turn) {
        return turn % 2 == 0 ? 'X' : 'O';
    }

    private static boolean isGameOver(String gameStatus) {
        switch (gameStatus) {
            case "X wins":
            case "O wins":
            case "Draw":
            case "Impossible":
                return true;
            case "Game not finished":
            default:
                return false;
        }
    }
}
