import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /* Stage 1: Welcome to the battlefield! */
        // System.out.println("X O X");
        // System.out.println("O X O");
        // System.out.println("O X O");

        /* Stage 2: The user is the game master */
        String cells;
        while (true) {
            System.out.print("Enter cells: ");
            cells = scanner.nextLine();
            if (cells.length() != 9) {
               System.out.println("Invalid input, please try again.");
            } else {
               break;
            }
        }

        // System.out.println("---------");
        // System.out.println("| " + cells.charAt(0) + " " + cells.charAt(1) + " " + cells.charAt(2) + " |");
        // System.out.println("| " + cells.charAt(3) + " " + cells.charAt(4) + " " + cells.charAt(5) + " |");
        // System.out.println("| " + cells.charAt(6) + " " + cells.charAt(7) + " " + cells.charAt(8) + " |");
        // System.out.println("---------");

        /* Stage 3: What's up on the field? */
        /* populate field with user input and count appearances of X and O */
        final int gameSize = 3;
        int index = 0;
        char[][] field = new char[gameSize][gameSize];
        int xCount = 0;
        int oCount = 0;
        int spaceCount = 0;
        boolean validGame = true;

        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                char current = cells.charAt(index++);
                switch (current) {
                    case 'X':
                        xCount++;
                        break;
                    case 'O':
                        oCount++;
                        break;
                    case '_':
                        spaceCount++;
                        break;
                    default:
                        validGame = false;
                }
                field[i][j] = current;
            }
        }

        /* print field */
        System.out.println("---------");
        for (int i = 0; i < gameSize; i++) {
            System.out.print("|");
            for (int j = 0; j < gameSize; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("---------");

        /* check game validity: count check */
        validGame &= Math.abs(xCount - oCount) <= 1;

        if (!validGame) {
            System.out.println("Impossible");
        } else {
            /* analyze field */
            char winner = 0;

            // check rows (reuse `validGame` variable, it's not needed)
            for (int i = 0; i < gameSize && validGame; i++) {
                if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] != '_') {
                    // no other checks needed since X (or O) winning 2 rows is handled by count check
                    if (winner != 0) {
                        validGame = false;
                    } else {
                        winner = field[i][0];
                    }
                }
            }

            // check columns only if game is valid
            for (int i = 0; i < gameSize && validGame; i++) {
                if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] != '_') {
                    // Check case when winner won both a row and a column (not impossible)
                    // winner != field[0][i] condition makes sure the special cases:
                    // { {'O', 'X', 'O'}, {'X', 'X', 'X'}, {'O', 'X', 'O'} } and
                    // { {'X', 'O', 'O'}, {'X', 'O', 'O'}, {'X', 'X', 'X'} }
                    // are not considered 'Impossible' since they can happen
                    // if the opponent picks moves randomly
                    if (winner != 0 && winner != field[0][i]) {
                        validGame = false;
                    } else {
                        winner = field[0][i];
                    }
                }
            }

            // check diagonal only if game is valid
            boolean winDiag = field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] != '_';

            // Check case when winner won both a row/ column and main diagonal (not impossible)
            // winner != field[1][1] condition makes sure the special cases like:
            // { {'X', 'X', 'X'}, {'O', 'X', 'O'}, {'O', 'O', 'X'} } (row + diag) and
            // { {'X', 'O', 'O'}, {'X', 'X', 'O'}, {'X', 'O', 'X'} } (col + diag)
            // are not considered 'Impossible' since they can happen
            // if the opponent picks moves randomly
            if (validGame && winDiag) {
                if (winner != 0 && winner != field[1][1]) {
                    validGame = false;
                } else {
                    winner = field[1][1];
                }
            }

            // check cross diagonal only if game is valid
            boolean winCrossDiag = field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] != '_';

            // Check case when winner won both a row/ column and cross diagonal (not impossible)
            // winner != field[1][1] condition makes sure the special cases like:
            // { {'X', 'X', 'X'}, {'O', 'X', 'O'}, {'X', 'O', 'O'} } (row + crossDiag) and
            // { {'X', 'O', 'X'}, {'X', 'X', 'O'}, {'X', 'O', 'O'} } (column + crossDiag)
            // are not considered 'Impossible' since they can happen
            // if the opponent picks moves randomly
            if (validGame && winCrossDiag) {
                if (winner != 0 && winner != field[1][1]) {
                    validGame = false;
                } else {
                    winner = field[1][1];
                }
            }

            if (!validGame) {
                System.out.println("Impossible");
            } else {
                if (winner != 0) {
                    System.out.println(winner + " wins");
                }
                else if (spaceCount > 0) {
                    System.out.println("Game not finished");
                } else {
                    System.out.println("Draw");
                }
            }
        }
    }
}
