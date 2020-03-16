
public class Boats {

    private int[][] shipArr;//ship array
    private int westLoc;//west location of spaces from starting point
    private int eastLoc;//same thing for other cardnial directions
    private int northLoc;
    private int southLoc;
    private int playstyle;//takes input from the board class and
    private int shipSize;//each individual ship size is passed in
    private int[][] shipClone;//ship array made to mimic
    private int lowerBound;//need to know borders for the array
    private int upperBound;// need to know borders for array
    private int shipCounter = 1;// incrementer to set each ship apart.

    //constructor for board lass
    public Boats(Board b) {

        shipClone = b.getBoard();//points to the memory address for the board made.
        upperBound = shipClone.length;
        lowerBound = 0;
        playstyle = b.getChoice();
        shipClone = this.shipsGenerated();
        String boardToString = this.toString(shipClone);// will not need these 2 lines for testing.
        System.out.print(boardToString);// for testing
    }
    // generates a board populated with numbers representing ships
    // this is all inverted because it is easier within the turns class.
    public int[][] shipsGenerated() {
        // this is for standard playstyle
        if (playstyle == 1) {
            for (int i = 1; i <= 5; i++) {
                boolean generated = false;
                if (i == 1) {
                    shipSize = 5;
                } else if (i == 2) {
                    shipSize = 4;
                } else if (i == 3 || i == 4) {
                    shipSize = 3;
                } else {
                    shipSize = 2;
                }
                while (generated == false) {
                    generated = generatedSpot(shipSize);
                }
                //this is an incrementer. It is useful for the turns class because it makes sure each ship is unique
                shipCounter += 1;
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
                shipCounter += 1;
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
        int x = (int) (Math.random() * shipClone.length);
        int y = (int) (Math.random() * shipClone[0].length);

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
                shipClone[y][x] = shipCounter;
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
                            shipClone[y][x - countWest] = shipCounter;
                        }
                        if (randSpot == 2 && countEast < eastLoc) {
                            countEast += 1;
                            countHorizontal += 1;
                            shipClone[y][countEast + x] = shipCounter;
                        }
                    }
                    return true;
                } else {//if randOption = 2, we do north - south side
                    shipClone[y][x] = shipCounter;
                    while (countVertical < shipSize - 1) {
                        int randSpot = (int) (Math.random() * 2 + 1);
                        if (randSpot == 1 && countNorth < northLoc) {
                            countNorth += 1;
                            countVertical += 1;
                            shipClone[y + countNorth][x] = shipCounter;
                        }
                        if (randSpot == 2 && countSouth < southLoc) {
                            countSouth += 1;
                            countVertical += 1;
                            shipClone[y - countSouth][x] = shipCounter;
                        }
                    }
                    return true; // we return true bc we know either side works

                }// else if only west - east works we try that
            } else if (westLoc + eastLoc >= shipSize) {
                shipClone[y][x] = shipCounter;
                while (countHorizontal < shipSize - 1) {
                    int randSpot = (int) (Math.random() * 2 + 1);
                    if (randSpot == 1 && countWest < westLoc) {
                        countWest += 1;
                        countHorizontal += 1;
                        shipClone[y][x - countWest] = shipCounter;
                    }
                    if (randSpot == 2 && countEast < eastLoc) {
                        countEast += 1;
                        countHorizontal += 1;
                        shipClone[y][countEast + x] = shipCounter;
                    }
                }
                return true;
            }// if west - east is not going to work, we do north and south
            else if (northLoc + southLoc >= shipSize) {
                shipClone[y][x] = shipCounter;
                while (countVertical < shipSize - 1) {
                    int rand = (int) (Math.random() * 2 + 1);
                    if (rand == 1 && countNorth < northLoc) {
                        countNorth += 1;
                        countVertical += 1;
                        shipClone[y + countNorth][x] = shipCounter;
                    }
                    if (rand == 2 && countSouth < southLoc) {
                        countSouth += 1;
                        countVertical += 1;
                        shipClone[y - countSouth][x] = shipCounter;
                    }
                }
                return true;
            }
        } else {
            // if no options work, we return false and look for the next spot
            return false;
        }
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
    public int[][] getShipClone(){
        return shipClone;
    }
    public int getLowerBound(){
        return lowerBound;
    }
    public int getUpperBound(){
        return upperBound;
    }

    public static void main(String[] args) {
    }
}