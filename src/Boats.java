
import java.util.Scanner;// DELETE LATER!!!

public class Boats {
    private int[][] shipArr;
    private int westLoc;
    private int eastLoc;
    private int northLoc;
    private int southLoc;
    private String playstyle;
    private int shipSize;
    private int[][] shipClone;
    private int lowerBound;
    private int upperBound;

    //constructor for class
    public Boats() {

        //takes in the board array and makes a clone for the board
        shipArr = new int[8][8];
        shipClone = new int[8][8]; // will fix in final version
        upperBound = shipClone.length;
        lowerBound = 0;

        for (int i = 0; i < shipArr.length; i++) {//populates array with 0's to start (these are blank spaces)
            for (int j = 0; j < shipArr[0].length; j++) {
                shipArr[i][j] = 0;
            }
        }
        this.playstyle = "standard"; //Board.getChoice(); FILL IN FOR BOARD CLASS
    }
    // generates a board populated with numbers representing ships
    public int[][] shipGenerated() {
        // this is for standard playstyle
        if (playstyle.equals("standard")) {
            for (int i = 1; i <= 5; i++) {
                boolean generated = false;
                if (i == 1) {
                    shipSize = 2;
                } else if (i == 2 || i == 3) {
                    shipSize = 3;
                } else if (i == 5) {
                    shipSize = 4;
                } else {
                    shipSize = 5;
                }
                while (generated == false) {
                    generated = generatedSpot(shipSize);
                }
            }
            return shipClone;
            // this is for expert playstyle
        } else {
            for (int i = 1; i <= 10; i ++) {
                boolean generated = false;
                if (i == 1 || i == 2) {
                    shipSize = 5;
                }else if(i <=4){
                    shipSize = 4;
                } else if(i<=8){
                    shipSize = 3;
                } else{
                    shipSize = 2;
                }
                while (generated == false) {
                    generated = generatedSpot(shipSize);

                }
            }
        }
        return shipClone;
    }
    // this makes sure the space that was where the ship is going to be generated is empty
    public boolean initalSpaceCheck(int x, int y){
        if (shipClone[y][x] == 0){
            return true;
        }else{
            return false;
        }
    }
    // this generates a ship
    public boolean generatedSpot(int shipSize) {
        // two random coordinates on the board
        int x = (int) (Math.random() * shipClone.length - 1);
        int y = (int) (Math.random() * shipClone.length - 1);

        //if we know that the space is available then we can proceed
        if (initalSpaceCheck(x, y) == true) {
            // we look to see how many spaces we have available on each side north, south, east, and west of our current spot
            westLoc = westBranch(0, shipSize - 1, x - 1, y);
            eastLoc = eastBranch(0, shipSize - 1, x + 1, y);
            northLoc = northBranch(0, shipSize - 1, x, y + 1);
            southLoc = southBranch(0, shipSize - 1, x, y - 1);

            // these are counters so when we add spaces we can increment
            int countHorizontal = 0;
            int countWest = 0;
            int countEast = 0;

            int countVertical = 0;
            int countNorth = 0;
            int countSouth = 0;

            // if both spots are available then we can do either
            if (westLoc + eastLoc >= shipSize && northLoc + southLoc >= shipSize) {
                shipClone[y][x] = shipSize;
                int randOption = (int) (Math.random() * 2 + 1);
                //if 1 vertical
                //if 2 horizontal

                //with randOption we chose to do the west - east side
                if (randOption == 1) {//say if randOption ==1
                    while (countHorizontal < shipSize - 1) {
                        // while loop because we don't know how far we need to go on each side
                        int randSpot = (int) (Math.random() * 2 + 1);
                        if (randSpot == 1 && countWest < westLoc) {
                            countWest += 1;
                            countHorizontal += 1;
                            shipClone[y][x - countWest] = shipSize;
                        } if (randSpot == 2 && countEast < eastLoc) {
                            countEast += 1;
                            countHorizontal += 1;
                            shipClone[y][countEast + x] = shipSize;
                        }
                    }
                    return true;
                } else if (randOption == 2) {//if randOption = 2, we do north - south side
                    shipClone[y][x] = shipSize;
                    while (countVertical < shipSize - 1) {
                        int randSpot = (int) (Math.random() * 2 + 1);
                        if (randSpot == 1 && countNorth < northLoc) {
                            countNorth += 1;
                            countVertical += 1;
                            shipClone[y + countNorth][x] = shipSize;
                        }if (randSpot == 2 && countSouth < southLoc) {
                            countSouth += 1;
                            countVertical += 1;
                            shipClone[y - countSouth][x] = shipSize;
                        }
                    }
                    return true; // we return true bc we know either side works

                }// else if only west - east works we try that
            } else if (westLoc + eastLoc >= shipSize) {
                shipClone[y][x] = shipSize;
                while (countHorizontal < shipSize - 1) {
                    int randSpot = (int) (Math.random() * 2 + 1);
                    if (randSpot == 1 && countWest < westLoc) {
                        countWest += 1;
                        countHorizontal += 1;
                        shipClone[y][x - countWest] = shipSize;
                    } if (randSpot == 2 && countEast < eastLoc) {
                        countEast += 1;
                        countHorizontal += 1;
                        shipClone[y][countEast + x] = shipSize;
                    }
                }
                return true;
            }// if west - east is not going to work, we do north and south
            else if (northLoc + southLoc >= shipSize){
                shipClone[y][x] = shipSize;
                while (countVertical < shipSize - 1) {
                    int rand = (int) (Math.random() * 2 + 1);
                    if (rand == 1 && countNorth < northLoc) {
                        countNorth += 1;
                        countVertical += 1;
                        shipClone[y + countNorth][x] = shipSize;
                    } if (rand == 2 && countSouth < southLoc) {
                        countSouth += 1;
                        countVertical += 1;
                        shipClone[y - countSouth][x] = shipSize;
                    }
                }
                return true;
            } else{
                return false;
            }
        }
        // if no options work, we return false and look for the next spot
        return false;
    }
    // these are recursive functions that determine how many spaces to each side there are open. Each of them are identical
    //but go in different directions
    public int westBranch (int count, int sizeofShip, int x, int y){
        if(x <= lowerBound/*minimum that the board can go*/ || x >= upperBound /* max board can go */) {
            return count;
            //if the spot is taken return how far the board was able to go
        }else if (shipClone[y][x] != 0){
            return count;
            //if you run out of spots forward, return the total count
        } else if (sizeofShip == 0){
            return count;
        }else{
            count += 1;//if the spot was open, move forward and increment count
            return westBranch(count ,sizeofShip-1, x-1, y);
        }
    }
    public int eastBranch (int count, int sizeofShip, int x, int y){
        if(x <= lowerBound || x >= upperBound) {
            return count;
        }else if (shipClone[y][x] != 0){
            return count;
        } else if (sizeofShip == 0){
            return count;
        }else{
            count += 1;
            return eastBranch(count ,sizeofShip-1, x+1, y);
        }
    }
    public int northBranch (int count, int sizeofShip, int x, int y){
        if(y <= lowerBound || y >= upperBound) {
            return count;
        }else if (shipClone[y][x] != 0){
            return count;
        } else if (sizeofShip == 0){
            return count;
        }else{
            count += 1;
            return northBranch(count ,sizeofShip-1, x, y+1);
        }
    }
    public int southBranch (int count, int sizeofShip, int x, int y){
        if(y <= lowerBound || y >= upperBound) {
            return count;
        }else if (shipClone[y][x] != 0){
            return count;
        } else if (sizeofShip == 0){
            return count;
        }else{
            count += 1;
            return southBranch(count, sizeofShip-1, x, y-1);
        }
    }
    public String toString(int[][] shipArr){
        String column = "";

        for(int i = 0; i < shipArr.length; i ++){
            String row = "";
            for(int j = 0; j< shipArr[i].length; j++) {
                row += "[" + shipArr[i][j] + "]";
            }
            column += row + "\n";
        }
        return column;
    }
    // this method is used for hitting ships. If a ship gets hit, it turns the space into a 9 and returns true, if there is nothing there, returns false
    public boolean hit(int x, int y){
        if (shipClone[y][x] == 0){
            return false;
        }else{
            shipClone[y][x] = 9;
            return true;
        }
    }
    public static void main(String[] args) {
        Boats y = new Boats();
        System.out.println(y.toString(y.shipArr));
        System.out.println(y.westBranch(0,3,3,4));
        System.out.println(y.southBranch(0,2,3,4));
        int[][] x = y.shipGenerated();
        System.out.println(y.toString(x));
        y.hit(3,4);

        Scanner s = new Scanner(System.in);
        int i = 0;
        while(i < 4){
            System.out.print("spot to hit");
            String a = s.nextLine();
            String b = s.nextLine();
            int u = Integer.parseInt(b);
            int w = Integer.parseInt(a);

            boolean effect = y.hit(u,w);
            System.out.println(effect);

            i += 1;
        }
        System.out.println(y.toString(x));

    }
}
