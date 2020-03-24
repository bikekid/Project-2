//REEDX500
import java.util.Scanner;

//Board class to create an instance of a game board
public class Board {
    //board array
    private static int[][] spaces;
    private static int playerInput;
    //constructor to intialize board

    public Board() {
        //creating a scanner class to allow for user input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a play style: Standard:1 or Expert:2");
        String choice = input.nextLine();
        if (!(choice.equals("1") || choice.equals("2"))){
            playerInput = 0;
        }else{
            playerInput = Integer.parseInt(choice);
        }
        //while loop ensures that the user either puts in standard or expert
        while (!(playerInput == 1 || playerInput== 2)) {
            System.out.println("Invalid answer select playstyle (standard:1 expert:2)");
            choice = input.nextLine();
            if (!(choice.equals("1") || choice.equals("2"))){
                playerInput = 0;
            }else{
                playerInput = Integer.parseInt(choice);
            }
        }
        //creates a standard board
        if (playerInput == 1) {
            System.out.println("Standard mode chosen!");
            spaces = new int[8][8];
        } else {
            //expert board is created
            System.out.println("Expert mode chosen!");
            spaces = new int[12][12];
        }
    }
    public static int[][] getBoard()
    {
        return spaces;
    }
    public static int getChoice(){
        return playerInput;
    }
    //Main for testing
    public static void main(String[] args) {
    }
}
