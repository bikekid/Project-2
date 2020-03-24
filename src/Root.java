//REEDX500
import java.util.Scanner;
public class Root {
    public Root() {
        String playerChoice = "y";
        Scanner replay = new Scanner(System.in);
        while (playerChoice.equals("y")) {
            Board x = new Board();
            Boats y = new Boats(x);
            Turns1 z = new Turns1(x, y);
            System.out.println("Thank you for playing! Would you like to play again?(Type y for yes, n for no)");
            playerChoice = replay.nextLine();
            while(!(playerChoice.equals("y")|| playerChoice.equals("n"))){
                System.out.println("Did not catch that. type y for replay, n for no.");
                playerChoice = replay.nextLine();
            }

        }
    }

    public static void main(String[] args) {

    }
}
