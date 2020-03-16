import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //Goblin: 👿
        //Human:
        //Treasure: 💰
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        boolean isDone = false;
        do{
            board.initializeBoard();
            board.playGame();
            boolean isValid = false;
            do{
                System.out.println("Would you like to play again? (Y or N)");
                switch (in.next().toUpperCase()) {
                    case "Y":
                        isValid = true;
                        isDone = false;
                        break;
                    case "N":
                        isValid = true;
                        isDone  = true;
                        break;
                    default:
                        System.out.println("That's not a valid input, try again.");
                        isValid = false;
                }
            }while(!isValid);
        }while(!isDone);
    }
}
