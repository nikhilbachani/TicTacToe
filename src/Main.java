import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /* Stage 1: Welcome to the battlefield! */
        // System.out.println("X O X");
        // System.out.println("O X O");
        // System.out.println("O X O");

        /* Stage 2: The user is the game master */
        String cells = scanner.nextLine();
        System.out.println("---------");
        System.out.println("| " + cells.charAt(0) + " " + cells.charAt(1) + " " + cells.charAt(2) + " |");
        System.out.println("| " + cells.charAt(3) + " " + cells.charAt(4) + " " + cells.charAt(5) + " |");
        System.out.println("| " + cells.charAt(6) + " " + cells.charAt(7) + " " + cells.charAt(8) + " |");
        System.out.println("---------");
    }
}
