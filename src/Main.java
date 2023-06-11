import java.util.Scanner;

public class Main {
    public static char[] directions = {'N','E','W','S'};
    // input checking
    public static void checkInputDim(int userIn) {
        if(userIn > 20) {
            throw new IllegalArgumentException("Dimensions too big, try again!");
        } else if (userIn < 5) {
            throw new IllegalArgumentException("Dimensions too small, try again!");
        }
    }
    public static boolean checkInputPos(char userIn) {
        for(char item: directions) {
            if(userIn == item) {
                return true;
            }
        }
        return false;
    }


    // printing game board
    public static void printGrille(int x, int y) {
        System.out.println("Grille: ");
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                System.out.print(". "); //
            }
            System.out.println(); //move to the next line after printing a row
        }
    }
    //updating position + direction
    public static char[] parseInstructions(String userIn) {
        char[] userInArray =  userIn.toCharArray();
        char[] errorMessage = {'T', 'O', 'O', 'L','O','N','G'};
        if(userInArray.length <= 20 && userInArray.length > 0) {
            return userInArray;
        }
        return errorMessage;
    }
    public static int startDirection(char positionCheck) {
        for(int i = 1; i < directions.length; i++) {
            if(positionCheck == directions[i]) {
                return i;
            }
        }
        return 0;
    }

//===================================================================================

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Position hooverPos = new Position(0,0, directions[0]);
        int[] dimGrille = {10,10}; //dimGrille[0] == x, dimGrill[1] == y


    //================================================================================

        System.out.println("Enter dimension x for the play area (min 5, max 20): ");
        dimGrille[0] = scanner.nextInt();
        checkInputDim(dimGrille[0]);

        System.out.println("Enter dimension y for the play area (min 5, max 20): ");
        dimGrille[1] = scanner.nextInt();
        checkInputDim(dimGrille[1]);
        printGrille(dimGrille[0], dimGrille[1]);

        System.out.println("Enter an initial position x for the vacuum: ");
        int initPosX = scanner.nextInt();

        System.out.println("Enter an initial position y for the vacuum: ");
        int initPosY = scanner.nextInt();
        if(initPosX > dimGrille[0]) {
            throw new IllegalArgumentException("X coordinate out of bounds, try again!");
        } else if(initPosY > dimGrille[1]) {
            throw new IllegalArgumentException("Y coordinate out of bounds, try again!");
        } else {
            hooverPos.x =  initPosX;
            hooverPos.y = initPosY;
        }
        scanner.nextLine(); // could Integer.parseInt() for all previous nextInt calls, but this is arguably cleaner10

        System.out.println("Enter an initial direction for the vacuum using a letter from N, S, E or W: ");
        char initDir = scanner.nextLine().toUpperCase().charAt(0);
        if(!checkInputPos(initDir)) {
            throw new IllegalArgumentException("Invalid input, please use a letter from N, S, E or W");
        }
        int startDir = startDirection(initDir);
        hooverPos.dir = directions[startDir];


        System.out.println("Enter a set of directions for the vacuum using format 'D', 'G', 'A' meaning 'turn right', 'turn left' and 'advance' (max 20 characters): ");
        String aspiInstructions = scanner.nextLine().toUpperCase();
        char[] aspiControl = parseInstructions(aspiInstructions);

        int currentDir = startDir;
        // as soon as there is more than one instruction it ignores everything and prints out original hoover position
        for(char elem: aspiControl) {
            switch(elem) {
                case 'D':
                    if(currentDir == 3) {
                        currentDir = 0;
                    } else {
                        currentDir += 1;
                    }
                    hooverPos.dir = directions[currentDir];
                    break;
                case 'G':
                    if(currentDir == 0) {
                        currentDir = 3;
                    } else {
                        currentDir -= 1;
                    }
                    hooverPos.dir = directions[currentDir];
                    break;
                case 'A':
                    switch(currentDir) {
                        case 0: //N
                            hooverPos.y += 1;
                            break;
                        case 1: //E
                            hooverPos.x += 1;
                            break;
                        case 2: //W
                            hooverPos.x -= 1;
                            break;
                        case 3: //S
                            hooverPos.y -= 1;
                            break;
                    }
                    break;
            }
        }
    //===========================================================================

        hooverPos.echoPosition();

    }

}





