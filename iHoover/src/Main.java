import java.util.Scanner;
/*
    TODO:
    [x] account for illegal inputs for dimGrille
    [x] account for illegal and out of bounds inputs for initPos
    [x] account for illegal inputs for direction set
    [x] return a copy of dimGrille using .
    [] return a copy of dimGrille with vacuum position displayed as x
    [] Parse aspiInstructions function
    [] Clear trailing empty strings from aspiControl
 */

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
    public static void printHoover() {
        //TODO
    }

    //updating position + direction
    public static char[] parseInstructions(String userIn) {
        char[] userInArray =  userIn.toCharArray();
        char[] errorMessage = {'T', 'O', 'O', 'L','O','N','G'};
        if(userInArray.length <= 20 ) {
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
        char[] aspiControl = new char[20];


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
        int startPosition = startDirection(initDir);
        hooverPos.dir = directions[startPosition];


        System.out.println("Enter a set of directions for the vacuum using format 'D', 'G', 'A' meaning 'turn right', 'turn left' and 'advance' (max 20 characters): ");
        String aspiInstructions = scanner.nextLine().toUpperCase();
        aspiControl = parseInstructions(aspiInstructions);

        for(char elem: aspiControl) {
            switch(elem) {
                case 'D':

                case 'G':
                    //TODO
                case 'A':
                    //TODO
            }
        }
    //===========================================================================

        hooverPos.echoPosition();

    }

}





