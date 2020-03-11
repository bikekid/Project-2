

public class Boats {
    private int[][] shipArr;
    private int westLoc;
    private int eastLoc;
    private int northLoc;
    private int southLoc;
    private String playstyle;
    private int shipSize;

    public Boats() {
        shipArr = new int[10][10];//Board.getBoard();//FILL IN FOR BOARD
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
                while (generated != true) {
                    generated = generatedSpot(i);
                }
                return shipArr;
            }
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
            return shipArr;
        }
        return shipArr;
    }
    public boolean generatedSpot(int sizeofShip){
        double genCol = Math.random() * shipArr.length;
        double genRow = Math.random() * shipArr.length;
        int x = (int) Math.floor(genCol);
        int y = (int) Math.floor(genRow);
        if (shipArr[x][y] == 0) {
            shipArr[x][y] = sizeofShip;
            //West side
            if (spotFinder(sizeofShip, x, y) == 1) {
                for (int a = y; a < sizeofShip; a--) {
                    shipArr[x][a] = sizeofShip;
                }
                    return true;
            } else if (spotFinder(sizeofShip, x, y) == 2) {
                for (int b = y; b < sizeofShip; b++) {
                    shipArr[x][b] = sizeofShip;
                }
                    return true;
            } else if (spotFinder(sizeofShip, x, y) == 3) {
                for (int c = y - this.westLoc; c < y + this.eastLoc; c++) {
                    shipArr[x][c] = sizeofShip;
                }
                    return true;
            }
            else if (spotFinder(sizeofShip,x,y) == 4){
                // need to find a way to add the two if they are not gonna add up nicely
                    int choice = (int) Math.random() * 2;
                    if (choice == 1) {
                        for(int i = y - this.westLoc; i < this.westLoc; i ++){
                            shipArr[x][i] = sizeofShip;
                        }
                        for(int j = y; j <= sizeofShip - this.eastLoc; j ++){
                            shipArr[x][y + j] = sizeofShip;
                        }
                    } else{
                        for(int i = y - this.eastLoc; i < this.eastLoc; i ++){
                            shipArr[x][i] = sizeofShip;
                        }
                        for(int j = y; j <= sizeofShip - this.westLoc; j ++){
                            shipArr[x][y + j] = sizeofShip;
                        }
                        return true;
                    }
                }
            if (spotFinder(sizeofShip, x, y) == 5) {
                for (int a = x; a < sizeofShip; a--) {
                    shipArr[a][y] = sizeofShip;
                }
                return true;
            } else if (this.spotFinder(sizeofShip, x, y) == 6) {
                for (int b = x; b < sizeofShip; b++) {
                    shipArr[b][y] = sizeofShip;
                }
                return true;
            } else if (this.spotFinder(sizeofShip, x, y) == 7) {
                for (int c = x - this.northLoc; c < x + this.southLoc; c++) {
                    shipArr[c][y] = sizeofShip;
                }
                return true;
            }
            else if (this.spotFinder(sizeofShip,x,y) == 8) {
                // need to find a way to add the two if they are not gonna add up nicely
                int choice = (int) Math.random() * 2;
                if (choice == 1) {
                    for (int i = y - this.northLoc; i < this.southLoc; i++) {
                        shipArr[i][y] = sizeofShip;
                    }
                    for (int j = y; j <= (sizeofShip - this.eastLoc); j++){
                        shipArr[j][y] = sizeofShip;
                    }
                } else {
                    for (int i = x - this.eastLoc; i < this.eastLoc; i++) {
                        shipArr[i][y] = sizeofShip;
                    }
                    for (int j = x; j <= (sizeofShip - this.westLoc); j++){
                        shipArr[j][y] = sizeofShip;
                    }
                }
            }
        }
        return false;
    }

    public int spotFinder(int sizeOfShip, int x, int y){
        int shipOptions = sizeOfShip - 1;
        this.westLoc = westBranch(shipOptions, x, y);
        this.eastLoc = eastBranch(shipOptions, x, y);
        this.northLoc = northBranch(shipOptions, x, y);
        this.southLoc = southBranch(shipOptions, x, y);

        if(westLoc == shipOptions){
            return 1;
            }
        else if(eastLoc == shipOptions){
            return 2;
        }
        else if (eastLoc + westLoc == shipOptions){
            return 3;
        }
        else if (eastLoc + westLoc >= shipOptions){
            return 4;
        }
        if(northLoc == shipOptions){
            return 5;
        }
        else if(southLoc == shipOptions){
            return 6;
        }
        else if (southLoc + northLoc == shipOptions){
            return 7;
        }
        else if (southLoc + northLoc >= shipOptions){
            return 8;
        }
        return -1;
    }

    public int westBranch (int sizeofShip, int x, int y){
        int count = 0;
        if (shipArr[x][y] != 0){
            return count;
        } else if (sizeofShip == 0){
            return count;
        }else{
            count += 1;
            return westBranch(sizeofShip-1, x+1 , y);
        }
    }
    public int eastBranch (int sizeofShip, int x, int y){
        int count = 0;
        if (shipArr[x][y] != 0){
            return count;
        } else if (sizeofShip == 0){
            return count;
        }else{
            count += 1;
            return westBranch(sizeofShip-1, x-1 , y);
        }
    }
    public int northBranch (int sizeofShip, int x, int y){
        int count = 0;
        if (shipArr[x][y] != 0){
            return count;
        } else if (sizeofShip == 0){
            return count;
        }else{
            count += 1;
            return westBranch(sizeofShip-1, x , y+1);
        }
    }
    public int southBranch (int sizeofShip, int x, int y){
        int count = 0;
        if (shipArr[x][y] != 0){
            return count;
        } else if (sizeofShip == 0){
            return count;
        }else{
            count += 1;
            return westBranch(sizeofShip-1, x, y-1);
        }
    }
    public String toString(){
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
        System.out.println(y.toString());
        y.shipGenerated();
        System.out.println(y.toString());
    }
}

