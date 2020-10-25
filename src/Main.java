import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final char[][] field = new char[3][3];

    public static void main(String[] args) {
        /* Stage 1: Welcome to the battlefield! */
        // System.out.println("X O X");
        // System.out.println("O X O");
        // System.out.println("O X O");

        /* Stage 2: The user is the game master */
        String cells;
        while (true) {
             System.out.print("Enter cells: ");
            // get input cells
            cells = scanner.nextLine();
            if (!validInput(cells)) {
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
        setField(cells);
        printField();
        System.out.println(getGameStatus());
    }

    /* validate input cells - to be removed in stage 5*/
    private static boolean validInput(String cells) {
        boolean isValid = true;
        String validChars = "XO_";

        for (int i = 0; i < cells.length(); i++) {
            if (validChars.indexOf(cells.charAt(i)) < 0) {
                isValid = false;
            }
        }

        isValid &= cells.length() == 9;
        return isValid;
    }

    /* set field */
    private static void setField(String cells) {
        int index = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = cells.charAt(index++);
            }
        }
    }

    /* print field */
    private static void printField() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    /* calculate counts */
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

    /* check rows */
    private static boolean checkRows(char c) {
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] == c) {
                return true;
            }
        }

        return false;
    }

    /* check columns */
    private static boolean checkColumns(char c) {
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] == c) {
                return true;
            }
        }

        return false;
    }

    /* check main diagonal */
    private static boolean checkDiag(char c) {
        return field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[1][1] == c;

    }

    /* check cross diagonal */
    private static boolean checkCrossDiag(char c) {
        return field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[1][1] == c;
    }

    /* check game and get result */
    private static String getGameStatus() {
        int xCount = getCharCount('X');
        int oCount = getCharCount('O');
        int _Count = getCharCount('_');

        boolean xWins = checkRows('X') || checkColumns('X') || checkDiag('X') || checkCrossDiag('X');
        boolean oWins = checkRows('O') || checkColumns('O') || checkDiag('O') || checkCrossDiag('O');

        if (Math.abs(xCount - oCount) > 1 || (xWins && oWins)) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (_Count > 0) {
            return "Game not finished";
        } else {
            return "Draw";
        }
    }
}
