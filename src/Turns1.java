//REEDX500
import java.util.Scanner;
public class Turns1 {
    private int[][] shipClone;
    private int[][] coordinateTracker;
    private int x;
    private int y;
    private int turn;
    private int upperBound;//upper bound of board array
    private int lowerBound;//lower bound of board array
    private int shipHit;//tracks how many ships have been hit

    //these counters and ship ints are for the playstyles. They are basically how we keep track of the ships and how many are left
    private int counter1 = 0;
    private int counter2 = 0;
    private int counter3 = 0;
    private int counter4 = 0;
    private int counter5 = 0;
    private int counter6 = 0;
    private int counter7 = 0;
    private int counter8 = 0;
    private int counter9 = 0;
    private int counter10 = 0;
    private int ship1;
    private int ship2;
    private int ship3;
    private int ship4;
    private int ship5;
    private int ship6;
    private int ship7;
    private int ship8;
    private int ship9;
    private int ship10;
    private int playStyle;//determines what game style the player chose: standard or expert
    private int shipSunk;// counts all the ships sunk
    private int missileCounter = 0;//counts how many missiles have been fired
    private int missileLimit;// how many missiles can be used based on playstyle
    private int droneCounter = 0;//same functions as above
    private int droneLimit;//same
    private int[][] shotBoard;
    private int shotFired = 0;

    //Create global private linked list

    //private Board board = new Board();
    //private Boats play = new Boats();
    // have a tracker that tracks how many times power.drone has been called
    private int tracker;

    public Turns1(Board play, Boats u) {
        playStyle = play.getChoice();//gets the playstyle from board
        shipClone = u.getShipClone();//gets the ship array from boats
        if (playStyle == 1) {//for standard mode
            ship1 = 5;
            ship2 = 4;
            ship3 = 3;
            ship4 = 3;
            ship5 = 2;
            missileLimit = 1;
            droneLimit = 1;
            coordinateTracker = new int[8][8];
            shotBoard = new int[8][8];
        } else {
            ship1 = 5;
            ship2 = 5;
            ship3 = 4;
            ship4 = 4;
            ship5 = 3;
            ship6 = 3;
            ship7 = 3;
            ship8 = 3;
            ship9 = 2;
            ship10 = 2;
            missileLimit = 2;
            droneLimit = 2;
            coordinateTracker = new int[12][12];
            shotBoard = new int[12][12];
        }
        turn = 0;
        shipSunk = 0;
        upperBound = u.getUpperBound();
        lowerBound = u.getLowerBound();

        Scanner userInput = new Scanner(System.in);

        if (playStyle == 1) {//if standard
            while (shipSunk < 5) {
                // while all the ships are not sunk this thread executes

                System.out.println("Hello what would you like to do? Enter 1 to hit a ship, enter 2 to use drone, enter 3 to use missile, or enter 4 to print board");
                String choice = userInput.nextLine();
                if (choice.equals("1")) {
                    System.out.println(this.toString());
                    System.out.println("Select x coordinate");
                    String xCoord = userInput.nextLine();

                    System.out.println("Select y coordinate");
                    String yCoord = userInput.nextLine();

                    int x = 0;
                    int y = 0;
                    try {
                        x = Integer.parseInt(xCoord);
                        y = Integer.parseInt(yCoord);
                        System.out.println(this.hit(x, y));
                    } catch (NumberFormatException NFE) {
                        System.out.println("Wrong input try again");
                    }
                } else if (choice.equals("2")) {
                    System.out.println(this.toString());
                    int result = this.drone();
                    if (result != -1) {
                        System.out.println(result + " spots with ships were found");
                    } else {
                        System.out.println("You have used all your drones you cannot use one at this time");
                    }


                } else if (choice.equals("3")) {
                    System.out.println(this.toString());
                    this.missle();
                } else if (choice.equals("4")) {
                    String board = u.toString(shipClone);
                    System.out.println(board);
                } else {
                    System.out.println("Invalid input try again");
                }
            }
            System.out.println("Game over you took " + turn + " turns to sink all boats. with " + shotFired +" shots fired" );//this is what happens at the end of the game.
        } else {
            while (shipSunk < 10) {//for expert playstyle
                // while all the ships are not sunk this thread executes
                System.out.println("Hello what would you like to do? Enter 1 to hit a ship, enter 2 to use drone, enter 3 to use missile, or enter 4 to print board");
                String choice = userInput.nextLine();
                if (choice.equals("1")) {
                    System.out.println(this.toString());
                    System.out.println("Select x coordinate");
                    String xCoord = userInput.nextLine();

                    System.out.println("Select y coordinate");
                    String yCoord = userInput.nextLine();

                    int x = 0;
                    int y = 0;
                    try {
                        x = Integer.parseInt(xCoord);
                        y = Integer.parseInt(yCoord);
                        System.out.println(this.hit(x, y));
                    } catch (NumberFormatException NFE) {
                        System.out.println("Wrong input try again");
                    }
                } else if (choice.equals("2")) {
                    System.out.println(this.toString());
                    int result = this.drone();
                    if (result != -1) {
                        System.out.println(result + " spots with ships were found");
                    } else {
                        System.out.println("You have used all your drones you cannot use one at this time");
                    }


                } else if (choice.equals("3")) {
                    System.out.println(this.toString());
                    this.missle();
                } else if (choice.equals("4")) {
                    System.out.println(this.toString());
                    String board = u.toString(shipClone);
                    System.out.println(board);
                } else {
                    System.out.println("Invalid input try again");
                }
            }
            System.out.println("Game over you took " + turn + " turns to sink all boats. with " + shotFired +" shots fired" );
        }
    }

    public String hit(int x, int y) {//this method is for hitting ships.
        /* there are 3 checks
        (1. see if the values passed in are out of bounds
        (2. see if the coordinates hit "water" represented as 0
        (3. if it does hit a ship, it increments a counter for each individual ship and checks to see if it is sunk
         */
        shotFired += 1;
        //check 1
        if (y > upperBound || x > upperBound || y < lowerBound || x < lowerBound || coordinateTracker[y][x] != 0 ) {
            turn += 2;
            return "Penalty lose a turn";

         //check 2
        }else if (shipClone[y][x] == 0) {
            turn++;
            coordinateTracker[y][x] = turn;
                return "Miss";

         //check 3
        }else {
            shipHit = shipClone[y][x];
            turn += 1;
            coordinateTracker[y][x] = turn;
            if (shipHit == 1) {
                counter1 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter1 == ship1) {
                    for(int i = 0; i < shipClone.length; i ++) {
                        String row = "";
                        for (int j = 0; j < shipClone[i].length; j++) {
                            if (shipClone[i][j] == -1) {
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 2) {
                counter2 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter2 == ship2){
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -2){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 3) {
                counter3 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter3 == ship3) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -3){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 4) {
                counter4 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter4 == ship4) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -4){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 5) {
                counter5 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter5 == ship5) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -5){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 6) {
                counter6 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter6 == ship6) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -6){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 7) {
                counter7 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter7 == ship7) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -7){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 8) {
                counter8 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter8 == ship8) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -8){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 9) {
                counter9 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter9 == ship9) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -9){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 10) {
                counter10 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter10 == ship10) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -10){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            }
            return "hit";
        }
    }

    /*
    drone is one of the powers. It scans a column and counts how many ships are in the column
     */
    public int drone(){
        //if drone is at its limit then we don't want it to continue further down the code
        if(droneCounter == droneLimit){
            return -1;
        }else{
            turn ++;
            int count = 0;

           //increment the drone counter for powers
            Scanner s = new Scanner(System.in);

            //these next lines take in input and convert it to an integer.

            System.out.println("Would you like to scan a row or column? Type r for row, c for column");
            String choice = s.nextLine();
            if (!(choice.equals("r") || choice.equals("c"))){
                System.out.print("Invalid input! Type r for row, c for column");
                choice = s.nextLine();
            }else if (choice.equals("c")){
                droneCounter += 1;
                System.out.println("Which column would you like to scan?");
                choice = s.nextLine();
                int numChoice;
                try{
                    numChoice = Integer.parseInt(choice);
                } catch(NumberFormatException nfe) {
                    numChoice = -1;

                }
                //If the inital convert did not work, then we use a while loop to keep asking until they give the right input.
                while(numChoice > upperBound || lowerBound > numChoice){
                    System.out.println("Invalid input try again. Please type in a number within the boundaries of the board.");
                    choice = s.nextLine();
                    try{
                        numChoice = Integer.parseInt(choice);
                    } catch(NumberFormatException nfe) {
                        numChoice = -1;
                    }
                }
                //this checks the column nad returns how many ships it found
                for (int i = 0; i < shipClone.length; i++) {
                    if (shipClone[i][numChoice] != 0) {
                        count += 1;
                    }
                }
                return count;

            }else{
                //identical to column, but looks in rows
                System.out.println("Which row would you like to scan?");
                droneCounter += 1;
                choice = s.nextLine();
                int numChoice;
                try{
                    numChoice = Integer.parseInt(choice);
                } catch(NumberFormatException nfe) {
                    numChoice = -1;

                }
                while(numChoice > upperBound || lowerBound > numChoice){
                    System.out.println("Invalid input try again. Please type in a number within the boundaries of the board.");
                    choice = s.nextLine();
                    try{
                        numChoice = Integer.parseInt(choice);
                    } catch(NumberFormatException nfe) {
                        numChoice = -1;
                    }
                }
                for (int i = 0; i < shipClone.length; i++) {
                    if (shipClone[numChoice][i] != 0) {
                        count += 1;
                    }
                }
                return count;
            }
            return -1;
        }
        }

    //missle places a missle a missle strike on
    public void missle() {
    if (missileCounter != missileLimit) {
            missileCounter += 1;
            turn ++;
            Scanner missinInp = new Scanner(System.in);
            Boolean coordinates = false;
            while (coordinates == false) {
                System.out.println("What x coordinate would you like to strike?");
                String xCoord = missinInp.nextLine();
                System.out.println("What y coordinate would you like to strike?");
                String yCoord = missinInp.nextLine();

                 x = 0;
                 y = 0;
                try {
                    x = Integer.parseInt(xCoord);
                    y = Integer.parseInt(yCoord);
                    coordinates = true;
                } catch (Exception NumberFormatException) {
                    System.out.println("Wrong input try again");
                }
            }
            int[][] strikeZone = new int[3][3];
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    String outcome = this.hitForMissle(x + i, y + j);
                }
            }
            System.out.println("Missile fired!");
        } else{
            System.out.println("Out of missiles");
        }
    }
    //this issues a missle attack, but checks through each individual index. This is mainly here to catch errors before they happen
    // and we get an index out of bounds error.
    public String hitForMissle(int x, int y) {
        if (y >= upperBound || x >= upperBound || y <= lowerBound || x <= lowerBound){
            return "penalty";
        }
        // if the user has already attacked a location or the location is out of bounds, it prints "penalty"
        else if (shipClone[y][x] == 0) {
            coordinateTracker[y][x] = 22;
            return "miss";

        }else {
            coordinateTracker[y][x] = 22;
            shipHit = shipClone[y][x];
            if (shipHit == 1) {
                counter1 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter1 == ship1) {
                    for(int i = 0; i < shipClone.length; i ++) {
                        String row = "";
                        for (int j = 0; j < shipClone[i].length; j++) {
                            if (shipClone[i][j] == -1) {
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 2) {
                counter2 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter2 == ship2){
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -2){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 3) {
                counter3 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter3 == ship3) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -3){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 4) {
                counter4 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter4 == ship4) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -4){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 5) {
                counter5 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter5 == ship5) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -5){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 6) {
                counter6 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter6 == ship6) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -6){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 7) {
                counter7 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter7 == ship7) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -7){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 8) {
                counter8 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter8 == ship8) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -8){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 9) {
                counter9 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter9 == ship9) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -9){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 10) {
                counter10 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter10 == ship10) {
                    for(int i = 0; i < shipClone.length; i ++){
                        String row = "";
                        for(int j = 0; j< shipClone[i].length; j++) {
                            if(shipClone[i][j] == -10){
                                shipClone[i][j] = -666;
                            }
                        }
                    }
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            }
            return "hit";
        }
    }
        public String toString(){
        String column = "";
        for(int i = 0; i < shipClone.length; i ++){
            String row = "";
            for(int j = 0; j< shipClone[i].length; j++) {
                if (shipClone[i][j] == -666){
                    row+= "[S]";
                }else if(shipClone[i][j] < 0){
                row+= "[X]";

                }else if(coordinateTracker[i][j] != 0) {
                    row += "[O]";
                }else{
                    row += "[-]";
                }
            }
            column += row + "\n";
        }
        return column;
    }
    public static void main(String[] args) {
    }
}
