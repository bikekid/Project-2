import java.util.Scanner;

public class Board {
    private int[][] spaces;

    public Board(){
        if (this.gameplayDeterminer() == "standard"){
            this.spaces = new int[8][8];
        } else{
            this.spaces = new int[12][12];
        }
    }

    public String gameplayDeterminer(){
        Scanner input = new Scanner(System.in);

        String gameChoice = "";

        int determinedChoice = 1;

        while (determinedChoice != 0){
            System.out.println("Enter a play style: Standard or Expert");
            gameChoice = input.nextLine();
            if((gameChoice.toLowerCase().equals("standard") || !gameChoice.toLowerCase().equals("expert"))){
                determinedChoice = 0;
            }
        }
        return gameChoice;
    }

    public static void main(String[] args) {
        Board A = new Board();
    }
}
