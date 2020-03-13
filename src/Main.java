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
        Scanner input = new Scanner(System.in);
        Board test = new Board();
        while(true){
            test.updateBoard();
            test.printBoard();
            test.enemyMove();
            test.playerMove(input.next());
            test.resolveMove();
        }
    }
}
