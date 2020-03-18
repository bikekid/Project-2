import java.util.Scanner;
public class Turns1 {
    private int[][] shipClone;
    private int x;
    private int y;
    private int turn;
    private int upperBound;
    private int lowerBound;
    private int shipHit;
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
    private int playStyle;
    private int shipSunk;
    //private Board board = new Board();
    //private Boats play = new Boats();
    // have a tracker that tracks how many times power.drone has been called
    private int tracker;

    public Turns1(Board play, Boats u) {
        this.x = x;
        this.y = y;
        playStyle = play.getChoice();
        shipClone = u.getShipClone();
        if (playStyle == 1) {
            ship1 = 5;
            ship2 = 4;
            ship3 = 3;
            ship4 = 3;
            ship5 = 2;
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
        }
        turn = 0;
        shipSunk = 0;
        upperBound = u.getUpperBound();
        lowerBound = u.getLowerBound();

        Scanner userInput = new Scanner(System.in);
        if (playStyle == 1){
            while(shipSunk < 5){
                System.out.println("Hello what would you like to do? Enter 1 to hit a ship, enter 2 to use powers, or enter 3 to print board");
                String choice = userInput.nextLine();
                if (choice.equals("1")){
                    System.out.println("Select x coordinate");
                    String xCoord = userInput.nextLine();

                    System.out.println("Select y coordinate");
                    String yCoord = userInput.nextLine();

                    int x = 0;
                    int y = 0;
                    try{
                        x = Integer.parseInt(xCoord);
                        y = Integer.parseInt(yCoord);
                        System.out.println(this.hit(x,y));
                    }catch (Exception NumberFormatException){
                        System.out.println("Wrong input try again");
                    }
                }

            }
        } else{
            while(shipSunk < 10){
                System.out.println("Hello what would you like to do? Enter 1 to hit a ship, enter 2 to use powers, or enter 3 to print board");
                String choice = userInput.nextLine();
                if (choice.equals("1")){
                    System.out.println("Select x coordinate");
                    String xCoord = userInput.nextLine();

                    System.out.println("Select y coordinate");
                    String yCoord = userInput.nextLine();

                    int x = 0;
                    int y = 0;
                    try{
                        x = Integer.parseInt(xCoord);
                        y = Integer.parseInt(yCoord);
                        System.out.println(this.hit(x,y));
                    }catch (Exception NumberFormatException){
                        System.out.println("Wrong input try again");
                    }
                }

            }
        }
    }

    public String hit(int x, int y) {
        if (shipClone[y][x] == 0) {
            turn++;
            return "miss";
        }
        // if the user has already attacked a location or the location is out of bounds, it prints "penalty"
        else if (y > upperBound || x > upperBound || y < lowerBound || x < lowerBound) {
            turn += 2;
            return "penalty";

        } else {
            shipHit = shipClone[y][x];
            if (shipHit == 1) {
                counter1 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter1 == ship1) {
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 2) {
                counter2 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter2 == ship2) {
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 3) {
                counter3 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter3 == ship3) {
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 4) {
                counter4 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter4 == ship4) {
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 5) {
                counter5 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter5 == ship5) {
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 6) {
                counter6 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter6 == ship6) {
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 7) {
                counter7 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter7 == ship7) {
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 8) {
                counter8 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter8 == ship8) {
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 9) {
                counter9 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter9 == ship9) {
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            } else if (shipHit == 10) {
                counter10 += 1;
                shipClone[y][x] = shipHit * -1;
                if (counter10 == ship10) {
                    shipSunk += 1;
                    return "sunk";
                }
                return "hit";
            }
            return "hit";
        }
    }
}
