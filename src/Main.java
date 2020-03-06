import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.Key;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //Goblin: 𓅷
        //Human: 𓁲
        System.out.println("""
                Score: 100
                -----------------------------------------                   Controls:
                | | | | | | | | | | | | | | | | | | | | |                       North => W
                -----------------------------------------                       East => D
                | | | | | | | |👸| | | | | | | | | | | | |                       South => S
                -----------------------------------------                       West => A
                | | | | | | | | | | | | | |	| | | | | | |                       Use Potion => Q
                -----------------------------------------
                | | | | | | | | | | | | | | |👿| | | | | |                   Inventory Key:
                -----------------------------------------                       PTN => Potion(Heals 5 health)
                | | | | | | | | | | | | | | | | | | | | |                       DAG => Dagger(Increases power by 1)
                -----------------------------------------                       BKL => Buckler(Increases defense by 1)
                Health: 50 / 50 | Power: 2 | Defense: 1                         SWD => Sword(Increases power by 3)
                Inventory:                                                      SHD => Shield(Increases defense by 2)
                    ---------------------                                       RAP => Rapier(Increases power by 2 & defense by 1)
                    |SWD|SHD|PTN|PTN|PTN|
                    ---------------------
                """);
    }
}
