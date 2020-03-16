import java.util.Scanner
public class Powers {
    private int[][] shipClone;
    private int spotToScan;
    private int upperBound;
    private int lowerBound;


    public Powers(Boats boaty){
        shipClone = boaty.getShipClone();
        upperBound = boaty.getUpperBound();
        lowerBound = boaty.getLowerBound();
    }

    public int drone(){
        int count;
        Scanner s = new Scanner(System.in);
        System.out.println("Would you like to scan a row or column? Type r for row, c for column");
        String choice = s.nextLine();
        if (!(choice.equals("r") || choice.equals("c"))){
            System.out.print("Invalid input! Type r for row, c for column");
            choice = s.nextLine();
        }else if (choice.equals("c")){
            System.out.println("Which column would you like to scan?");
                choice = s.nextLine();
                int numChoice;
                while(!((numChoice = Integer.parseInt(choice)) > upperBound || lowerBound > (numChoice = Integer.parseInt(choice)))){
                    System.out.println("Invalid input try again. Please type in a number within the boundaries of the board.");
                    choice = s.nextLine();
                    numChoice = -1;
                    if((numChoice = Integer.parseInt(choice)) < upperBound || lowerBound < (numChoice = Integer.parseInt(choice))){
                        numChoice = Integer.parseInt(choice))
                    }
                }
                for (int i = 0; i < shipClone.length; i++){
                    if (shipClone[i][numChoice] != 0){
                        count += 1;

            }
        }
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
