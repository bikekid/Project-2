

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

    public Boats() {
        shipArr = new int[10][10];//Board.getBoard();//FILL IN FOR BOARD
        shipClone = new int[10][10];
        upperBound = shipClone.length;
        lowerBound = 0;

        for (int i = 0; i < shipArr.length; i++) {
            for (int j = 0; j < shipArr[0].length; j++) {
                shipArr[i][j] = 0;
            }
        }
        this.playstyle = "standard"; //Board.getChoice(); FILL IN FOR BOARD CLASS
    }

    public int[][] shipGenerated() {
        if (playstyle.equals("standard")) {
            for (int i = 2; i <= 5; i++) {
                boolean generated = false;
                while (generated == false) {
                    generated = generatedSpot(i);
                }
            }
            return shipClone;
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
            return shipClone;
        }
    }
    public boolean initalSpaceCheck(int x, int y){
        if (shipClone[y][x] == 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean generatedSpot(int shipSize){
        int x = (int) (Math.random() * shipClone.length - 1);
        int y = (int) (Math.random() * shipClone.length - 1);

        if(initalSpaceCheck(x, y) == true) {

            westLoc = westBranch(0,shipSize, x-1, y);
            eastLoc = eastBranch(0,shipSize, x+1, y);
            northLoc = northBranch(0,shipSize, x, y+1);
            southLoc = southBranch(0, shipSize, x, y-1);

            int countHorizontal = 0;
            int countWest = 0;
            int countEast = 0;

            int countVertical = 0;
            int countNorth = 0;
            int countSouth = 0;

            if (westLoc + eastLoc >= shipSize || northLoc + southLoc >= shipSize) {
                shipClone[x][y] = shipSize;
                int randOption = (int) (Math.random() * 2 + 1);
                //if 1 vertical
                //if 2 horizontal

                //with randOption 1
                if (westLoc +eastLoc >= shipSize) {
                    while (countHorizontal < shipSize) {
                        int randSpot = (int) (Math.random() * 2 +1);
                        if(randSpot == 1 && countWest < westLoc){
                            countWest += 1;
                            countHorizontal += 1;
                            shipClone[y][x-countWest] = shipSize;
                        }else if(randSpot == 2 && countEast < eastLoc){
                            countEast += 1;
                            countHorizontal += 1;
                            shipClone[y][countEast + x] = shipSize;
                        }else{
                            return false;
                        }
                    }
                    return true;
                }else{
                    while (countVertical < shipSize-1) {
                        int randSpot = (int) (Math.random() * 2 +1);
                        if(randSpot == 1 && countNorth < northLoc){
                            countNorth += 1;
                            countVertical += 1;
                            shipClone[y+countNorth][x] = shipSize;
                        }else if(randSpot == 2 && countSouth < southLoc){
                            countSouth += 1;
                            countVertical += 1;
                            shipClone[y-countSouth][x] = shipSize;
                        }else{
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public int westBranch (int count, int sizeofShip, int x, int y){
        if(x <= lowerBound || x >= upperBound) {
            return count;
        }else if (shipClone[y][x] != 0){
            return count;
        } else if (sizeofShip == 0){
            return count;
        }else{
            count += 1;
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
    public static void main(String[] args) {
        Boats y = new Boats();
        System.out.println(y.toString(y.shipArr));
        System.out.println(y.westBranch(0,3,3,4));
        System.out.println(y.southBranch(0,2,3,4));
        int[][] x = y.shipGenerated();
        System.out.println(y.toString(x));

    }
}




//    public boolean generatedSpot(int sizeofShip){
//        double genCol = Math.random() * shipArr.length;
//        double genRow = Math.random() * shipArr.length;
//        int x = (int) Math.floor(genCol);
//        int y = (int) Math.floor(genRow);
//        if (shipArr[x][y] == 0) {
//            shipArr[x][y] = sizeofShip;
//            //West side
//            if (spotFinder(sizeofShip, x, y) == 1) {
//                for (int a = y; a < sizeofShip; a--) {
//                    shipArr[x][a] = sizeofShip;
//                }
//                    return true;
//            } else if (spotFinder(sizeofShip, x, y) == 2) {
//                for (int b = y; b < sizeofShip; b++) {
//                    shipArr[x][b] = sizeofShip;
//                }
//                    return true;
//            } else if (spotFinder(sizeofShip, x, y) == 3) {
//                for (int c = y - this.westLoc; c < y + this.eastLoc; c++) {
//                    shipArr[x][c] = sizeofShip;
//                }
//                    return true;
//            }
//            else if (spotFinder(sizeofShip,x,y) == 4){
//                // need to find a way to add the two if they are not gonna add up nicely
//                    int choice = (int) Math.random() * 2;
//                    if (choice == 1) {
//                        for(int i = y - this.westLoc; i < this.westLoc; i ++){
//                            shipArr[x][i] = sizeofShip;
//                        }
//                        for(int j = y; j <= sizeofShip - this.eastLoc; j ++){
//                            shipArr[x][y + j] = sizeofShip;
//                        }
//                    } else{
//                        for(int i = y - this.eastLoc; i < this.eastLoc; i ++){
//                            shipArr[x][i] = sizeofShip;
//                        }
//                        for(int j = y; j <= sizeofShip - this.westLoc; j ++){
//                            shipArr[x][y + j] = sizeofShip;
//                        }
//                        return true;
//                    }
//                }
//            if (spotFinder(sizeofShip, x, y) == 5) {
//                for (int a = x; a < sizeofShip; a--) {
//                    shipArr[a][y] = sizeofShip;
//                }
//                return true;
//            } else if (this.spotFinder(sizeofShip, x, y) == 6) {
//                for (int b = x; b < sizeofShip; b++) {
//                    shipArr[b][y] = sizeofShip;
//                }
//                return true;
//            } else if (this.spotFinder(sizeofShip, x, y) == 7) {
//                for (int c = x - this.northLoc; c < x + this.southLoc; c++) {
//                    shipArr[c][y] = sizeofShip;
//                }
//                return true;
//            }
//            else if (this.spotFinder(sizeofShip,x,y) == 8) {
//                // need to find a way to add the two if they are not gonna add up nicely
//                int choice = (int) Math.random() * 2;
//                if (choice == 1) {
//                    for (int i = y - this.northLoc; i < this.southLoc; i++) {
//                        shipArr[i][y] = sizeofShip;
//                    }
//                    for (int j = y; j <= (sizeofShip - this.eastLoc); j++){
//                        shipArr[j][y] = sizeofShip;
//                    }
//                } else {
//                    for (int i = x - this.eastLoc; i < this.eastLoc; i++) {
//                        shipArr[i][y] = sizeofShip;
//                    }
//                    for (int j = x; j <= (sizeofShip - this.westLoc); j++){
//                        shipArr[j][y] = sizeofShip;
//                    }
//                }
//            }
//        }
//        return fa
//    public int spotFinder(int sizeOfShip, int x, int y){
//        int shipOptions = sizeOfShip - 1;
//        this.westLoc = westBranch(shipOptions, x, y);
//        this.eastLoc = eastBranch(shipOptions, x, y);
//        this.northLoc = northBranch(shipOptions, x, y);
//        this.southLoc = southBranch(shipOptions, x, y);
//
//        if(westLoc == shipOptions){
//            return 1;
//            }
//        else if(eastLoc == shipOptions){
//            return 2;
//        }
//        else if (eastLoc + westLoc == shipOptions){
//            return 3;
//        }
//        else if (eastLoc + westLoc >= shipOptions){
//            return 4;
//        }
//        if(northLoc == shipOptions){
//            return 5;
//        }
//        else if(southLoc == shipOptions){
//            return 6;
//        }
//        else if (southLoc + northLoc == shipOptions){
//            return 7;
//        }
//        else if (southLoc + northLoc >= shipOptions){
//            return 8;
//        }
//        return -1;
//    }

