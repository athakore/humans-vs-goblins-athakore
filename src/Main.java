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
        do {
            test.updateBoard();
            test.printBoard();
            test.enemyMove();
            boolean temp = false;
            do {
                temp = test.playerMove(input.next());
            }while (!temp);
            test.resolveMove();
        }while(!test.isDead);
        test.printBoard();
        System.out.printf("Game Over");
    }
}
