import java.util.Scanner;// DELETE LATER!!!

public class Turns {
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
	int ship1;
	int ship2;
	int ship3;
	int ship4;
	int ship5;
	int ship6;
	int ship7;
	int ship8;
	int ship9;
	int ship10;
    private int playStyle;
    //private Board board = new Board();
    //private Boats play = new Boats();
	// have a tracker that tracks how many times power.drone has been called
	private int tracker;
	public Turns(int x, int y, Board play, Boats u) {
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
		}
		else {
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
	}
	// if a user's input hits a ship it returns "hit". if the user misses it returns "miss"
	public String hit(int x, int y) {
		if (shipClone[y][x] == 0) {
			turn++;
			return "miss";
		}
		// if the user has already attacked a location or the location is out of bounds, it prints "penalty"
		else if (shipClone[y][x] == shipHit || y > upperBound || x > upperBound || y < lowerBound || x < lowerBound) {
			turn+=2;
			return "penalty";

		}
		else {
			shipHit = shipClone[y][x];
				if(shipHit == 1){
					counter1 += 1;
					if(counter1 == ship1){

				}
			}
			//			shipHit = shipClone[y][x];
//            shipClone[y][x] = (shipHit * -1);
//            turn ++;
//            if (playStyle == 1) {
//            	if (shipClone[y][x] == ship1) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//            	}
//            	else if (shipClone[y][x] == ship2) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//            	}
//            	else if (shipClone[y][x] == ship3) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//            	}
//            	else if (shipClone[y][x] == ship4) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//            	}
//            	else if (shipClone[y][x] == ship5) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//            	}
//            }
//        	if (playStyle == 2) {
//        		if (shipClone[y][x] == ship1) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//        		}
//        		else if (shipClone[y][x] == ship2) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//        		}
//        		else if (shipClone[y][x] == ship3) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//        		}
//        		else if (shipClone[y][x] == ship4) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//        		}
//        		else if (shipClone[y][x] == ship5) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//        		}
//        		else if (shipClone[y][x] == ship6) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//        		}
//        		else if (shipClone[y][x] == ship7) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//        		}
//        		else if (shipClone[y][x] == ship8) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//        		}
//        		else if (shipClone[y][x] == ship9) {
//            		shipClone[y][x] = 666;
//            		return "sink";
//        		}
//        		else {
//            		shipClone[y][x] = 666;
//            		return "sink";
//        		}
//        	}
			return "hit";
		} 
	}
}