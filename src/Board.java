import java.util.Scanner;

//Board class to create an instance of a game board
public class Board {
    //board array
    public static int[][] spaces;
    public static String playerInput;
    //constructor to intialize board

    public Board() {
        //creating a scanner class to allow for user input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a play style: Standard or Expert");
        String choice = input.nextLine();
        String playerInput = choice.toLowerCase();

        //while loop ensures that the user either puts in standard or expert
        while (!(playerInput.equals("standard") || playerInput.equals("expert"))) {
            System.out.println("Select playstyle");
            choice = input.nextLine();
            playerInput = choice.toLowerCase();
        }
        //creates a standard board
        if (playerInput.equals("standard")) {
            spaces = new int[8][8];
        } else {
            //expert board is created
            spaces = new int[12][12];
        }
    }
    public static int[][] getBoard(){
        return spaces;
    }
    public static String getChoice(){
        return playerInput;
    }
    //Main for testing
    public static void main(String[] args) {
    }
}
