import java.util.Scanner;

public class Board {

    public Board(){
    }

    public String ScannerInput(){
        Scanner input = new Scanner(System.in);

        String gameChoice = "";

        while (!(gameChoice.toLowerCase().equals("standard") || !gameChoice.toLowerCase().equals("expert"))){

        }
        return gameChoice;
    }
}
