public class Position {
    public int x;
    public int y;
    public char dir;

    public Position(int startX, int startY, char startDir) {
        x = startX;
        y = startY;
        dir = startDir;
    }

    void echoPosition() {
        System.out.println("Hoover position x: " + x);
        System.out.println("Hoover position y: " + y);
        System.out.println("Hoover direction: " + dir);
    }
}