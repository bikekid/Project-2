public class Root {
    public static void main(String[] args) {
        Board x = new Board();
        Boats y = new Boats(x);
        Turns1 z = new Turns1(x,y);
    }
}
