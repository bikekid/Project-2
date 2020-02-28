import java.util.Scanner;

public class Board {
    private int[][] spaces;

    public Board() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a play style: Standard or Expert");
        String choice = input.nextLine();
        String playerInput = choice.toLowerCase();
        while (!(playerInput.equals("standard") || playerInput.equals("expert"))) {
            System.out.println("Select playstyle");
            choice = input.nextLine();
            playerInput = choice.toLowerCase();
        }
        if (playerInput.equals("standard")) {
            this.spaces = new int[8][8];
        } else {
            this.spaces = new int[12][12];
        }
    }

    public static void main(String[] args) {
        Board A = new Board();
    }
}
