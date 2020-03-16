import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Board {
    private String[][] board;
    public Human player;
    private Goblin enemy;
    private Item loot;
    private int score = 0;
    private String playerToken = "\uD83D\uDC78";
    private String enemyToken = "\uD83D\uDC7F";
    private String lootToken = "\uD83D\uDCB0";
    boolean isDead = false;
    int winCount = 0;

    public Board(){
        board = new String[][]{
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " "}
        };
        player = new Human();
        enemy = new Goblin();
        loot = new Item("DAG", ItemType.weapon, 1, 0, 0, new int[]{-1, -1});
        score = 0;
        isDead = false;
        winCount = 0;
    }

    void initiateMove(Actor actor, String dir) {
        switch (dir) {
            case "N":
                actor.getNextPosition()[1] += 1;
                break;
            case "E":
                actor.getNextPosition()[0] += 1;
                break;
            case "S":
                actor.getNextPosition()[1] -= 1;
                break;
            case "W":
                actor.getNextPosition()[0] -= 1;
                break;
            default:
                System.out.println(String.format("%s is trying to move in an illegal way.", actor.getClass().getName()));
        }
    };

    void initializeBoard() {
        board = new String[][]{
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "}
    };
        player = new Human();
        enemy = new Goblin();
        loot = new Item("DAG", ItemType.weapon, 1, 0, 0, new int[]{-1, -1});
        score = 0;
        isDead = false;
        winCount = 0;
    }

    void printBoard() {
        String scoreString;
        switch(winCount) {
            default:
                scoreString = Integer.toString(score);
                break;
            case 1:
                scoreString = Integer.toString(score) + " \"...WAIT, DID YOU JUST ONE-SHOT A GOBLIN AND HEAL FROM COMBAT? HOW DOES THAT EVEN WORK???\"";
                break;
            case 2:
                scoreString = Integer.toString(score) + " \"...OH, YOU'RE STILL PLAYING...WHY? IT'S NOT LIKE YOU CAN LOSE ANYMORE.\"";
                break;
            case 3:
                scoreString = "\"LOOK, YOU DON'T EVEN HAVE A SCORE, ANYMORE! JUST TURN OFF THE GAME AND WALK AWAY.\"";
                break;
            case 4:
                 scoreString = "\"STOP! PLAYING!\"";
                 break;
            case 5:
                 scoreString ="INFINITY";
                 break;
        }
        String finalFantasySword;
        String weaponTerminationField;
        if(player.checkForItem("FFS") && !player.checkForItem("WTF")) {
            finalFantasySword = "FFS => Final Fantasy Sword(Increases power by 5.̶͚́.̴̛̥̰͑.̷̱̝̑̇̊)̶͕̉̐̀";
            weaponTerminationField = "???";
        }
        else if(!player.checkForItem("FFS") && player.checkForItem("WTF")) {
            finalFantasySword = "???";
            weaponTerminationField = "WTF => Weapon Termination Field(Reduces all damage to 1.̷̧̧̛̀͘.̷̪̉͆́.̶̪̂̈́ͅ)̴̢̙̎͛͝";
        }
        else if(player.checkForItem("FFS") && player.checkForItem("WTF")){
            finalFantasySword = "FFS => Final Fantasy Sword(Kills all enemies in one shot...Wait, WHAT?!?!)";
            weaponTerminationField = "WTF => Weapon Termination Field(Heals 5 health every time you are hit...SERIOUSLY, WHO PUT THIS IN HERE?!?!)";
        }
        else {
            finalFantasySword = "???";
            weaponTerminationField = "???";
        }
        System.out.println(String.format("""
                Score: %s
                ---------------------------------------------------         Controls:
                | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s |             North => W
                ---------------------------------------------------             East => D
                | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s |             South => S
                ---------------------------------------------------             West => A
                | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s |             Use Potion => Q
                ---------------------------------------------------
                | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s |         Inventory Key:
                ---------------------------------------------------             PTN => Potion(Heals 5 health)
                | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s |             DAG => Dagger(Increases power by 1)
                ---------------------------------------------------             BKL => Buckler(Increases defense by 1)
                Health: %s / %s | Power: %s | Defense: %s                        %s%sSWD => Sword(Increases power by 3)
                Goblin: %s / %s | Power: %s | Defense: %s                         %sSHD => Shield(Increases defense by 2)
                Inventory:                                                      RAP => Rapier(Increases power by 2 & defense by 1)
                    ---------------------                                       %s
                    |%s|%s|%s|%s|%s|                                       %s
                    ---------------------
                """,
                scoreString,
                board[0][4] == " " ? "  " : board[0][4],
                board[1][4] == " " ? "  " : board[1][4],
                board[2][4] == " " ? "  " : board[2][4],
                board[3][4] == " " ? "  " : board[3][4],
                board[4][4] == " " ? "  " : board[4][4],
                board[5][4] == " " ? "  " : board[5][4],
                board[6][4] == " " ? "  " : board[6][4],
                board[7][4] == " " ? "  " : board[7][4],
                board[8][4] == " " ? "  " : board[8][4],
                board[9][4] == " " ? "  " : board[9][4],
                board[0][3] == " " ? "  " : board[0][3],
                board[1][3] == " " ? "  " : board[1][3],
                board[2][3] == " " ? "  " : board[2][3],
                board[3][3] == " " ? "  " : board[3][3],
                board[4][3] == " " ? "  " : board[4][3],
                board[5][3] == " " ? "  " : board[5][3],
                board[6][3] == " " ? "  " : board[6][3],
                board[7][3] == " " ? "  " : board[7][3],
                board[8][3] == " " ? "  " : board[8][3],
                board[9][3] == " " ? "  " : board[9][3],
                board[0][2] == " " ? "  " : board[0][2],
                board[1][2] == " " ? "  " : board[1][2],
                board[2][2] == " " ? "  " : board[2][2],
                board[3][2] == " " ? "  " : board[3][2],
                board[4][2] == " " ? "  " : board[4][2],
                board[5][2] == " " ? "  " : board[5][2],
                board[6][2] == " " ? "  " : board[6][2],
                board[7][2] == " " ? "  " : board[7][2],
                board[8][2] == " " ? "  " : board[8][2],
                board[9][2] == " " ? "  " : board[9][2],
                board[0][1] == " " ? "  " : board[0][1],
                board[1][1] == " " ? "  " : board[1][1],
                board[2][1] == " " ? "  " : board[2][1],
                board[3][1] == " " ? "  " : board[3][1],
                board[4][1] == " " ? "  " : board[4][1],
                board[5][1] == " " ? "  " : board[5][1],
                board[6][1] == " " ? "  " : board[6][1],
                board[7][1] == " " ? "  " : board[7][1],
                board[8][1] == " " ? "  " : board[8][1],
                board[9][1] == " " ? "  " : board[9][1],
                board[0][0] == " " ? "  " : board[0][0],
                board[1][0] == " " ? "  " : board[1][0],
                board[2][0] == " " ? "  " : board[2][0],
                board[3][0] == " " ? "  " : board[3][0],
                board[4][0] == " " ? "  " : board[4][0],
                board[5][0] == " " ? "  " : board[5][0],
                board[6][0] == " " ? "  " : board[6][0],
                board[7][0] == " " ? "  " : board[7][0],
                board[8][0] == " " ? "  " : board[8][0],
                board[9][0] == " " ? "  " : board[9][0],
                player.getCurrHealth(),
                player.getMaxHealth(),
                player.getModifiedPower(),
                player.getModifiedDefense(),
                player.getCurrHealth() > 9 ? "" : " ",
                player.getModifiedPower() > 9 ? "" : " ",
                enemy.getCurrHealth(),
                enemy.getMaxHealth(),
                enemy.getPower(),
                enemy.getDefense(),
                enemy.getCurrHealth() > 9 ? "" : " ",
                finalFantasySword,
                player.getInventory()[0] == null ? "   " : player.getInventory()[0],
                player.getInventory()[1] == null ? "   " : player.getInventory()[1],
                player.getInventory()[2] == null ? "   " : player.getInventory()[2],
                player.getInventory()[3] == null ? "   " : player.getInventory()[3],
                player.getInventory()[4] == null ? "   " : player.getInventory()[4],
                weaponTerminationField
                ));
    }

    void updateBoard() {
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[x].length; y++){
                if(player.getPosition()[0] == x && player.getPosition()[1] == y)board[x][y] = playerToken;
                else if(enemy.getPosition()[0] == x && enemy.getPosition()[1] == y)board[x][y] = enemyToken;
                else if(loot.getPosition()[0] == x && loot.getPosition()[1] == y)board[x][y] = lootToken;
                else board[x][y] = " ";
            }
        }
    }

    boolean playerMove(String input) {
        switch (input.toUpperCase()) {
            case "W":
                if(player.getPosition()[1] == 4) {
                    System.out.println("You can't move up, you're already at the top edge of the map!");
                    return false;
                }
                else {
                    initiateMove(player,"N");
                    return true;
                }
            case "A":
                if(player.getPosition()[0] == 0){
                    System.out.println("You can't move left, you're already at the leftmost edge of the map!");
                    return false;
                }
                else {
                    initiateMove(player,"W");
                    return true;
                }
            case "S":
                if(player.getPosition()[1] == 0) {
                    System.out.println("You can't move down, you're already at the bottom edge of the map!");
                    return false;
                }
                else {
                    initiateMove(player,"S");
                    return true;
                }
            case "D":
                if(player.getPosition()[0] == 9) {
                    System.out.println("You can't move right, you're already at the rightmost edge of the map!");
                    return false;
                }
                else {
                    initiateMove(player,"E");
                    return true;
                }
            case "Q":
                return player.consumePotion();
            default:
                System.out.println("Not a valid input. Try again.");
                return false;
        }
    }

    void enemyMove() {
        if(enemy.getPosition()[1] != player.getPosition()[1]) {
            if(enemy.getPosition()[1] > player.getPosition()[1]) initiateMove(enemy, "S");
            else initiateMove(enemy, "N");
        }
        else if(enemy.getPosition()[0] != player.getPosition()[0]) {
            if(enemy.getPosition()[0] > player.getPosition()[0]) initiateMove(enemy,"W");
            else initiateMove(enemy, "E");
        }
    }

    Item generateItem (int[] pos) {
        int temp = new Random().nextInt(100) + 1;
        if(temp > 1 && temp <= 41) temp = 2;
        else if(temp > 41 && temp <= 56) temp = 3;
        else if(temp > 56 && temp <= 71) temp = 4;
        else if(temp > 71 && temp <= 82) temp = 5;
        else if(temp > 82 && temp <= 93) temp = 6;
        else if(temp > 93 && temp <= 99) temp = 7;
        else if(temp == 100) temp = 8;
        switch(temp) {
            //Easter Egg Mode
            case 1, 2, 3:
                return new Item("WTF",ItemType.shield,0,5,0,pos); //Weapon Termination Field
            case 4, 5, 6, 7, 8:
                return new Item("FFS",ItemType.weapon,5,0,0,pos); //Final Fantasy Sword
            //Normal Mode
            /*case 1:
                return new Item("WTF",ItemType.shield,0,5,0,pos); //Weapon Termination Field
            case 2:
                return new Item("PTN",ItemType.consumable,0,0,5,pos); //Potion
            case 3:
                return new Item("DAG",ItemType.weapon,1,0,0,pos); //Dagger
            case 4:
                return new Item("BKL",ItemType.shield,0,1,0,pos); //Buckler
            case 5:
                return new Item("SWD",ItemType.weapon,2,0,0,pos); //Sword
            case 6:
                return new Item("SHD",ItemType.shield,0,2,0,pos); //Shield
            case 7:
                return new Item("RAP",ItemType.weapon,2,1,0,pos); //Rapier
            case 8:
                return new Item("FFS",ItemType.weapon,5,0,0,pos); //Final Fantasy Sword*/
            default:
                return new Item();

        }
    }

    void resolveMove() {
        if(checkForCombat()) {
            resolveCombat();
            enemy.setNextPosition(new int[]{enemy.getPosition()[0], enemy.getPosition()[1]});
            player.setNextPosition(new int[]{player.getPosition()[0], player.getPosition()[1]});
        }
        else {
            if(checkForLoot()) {
                loot.setPosition(new int[]{-1, -1});
                player.pickUpItem(new Item(loot));
            }
            enemy.setPosition(new int[]{enemy.getNextPosition()[0], enemy.getNextPosition()[1]});
            player.setPosition(new int[]{player.getNextPosition()[0], player.getNextPosition()[1]});
        }
    }

    void resolveCombat() {
        if(player.checkForItem("FFS") && player.checkForItem("WTF")) enemy.loseHealth(enemy.getCurrHealth() + enemy.getDefense());
        else enemy.loseHealth(player.getModifiedPower());
        player.loseHealth(enemy.getPower());
        if(player.getCurrHealth() == 0){
            isDead = true;
            player.setPosition(new int[]{-1, -1});
            return;
        }
        if(enemy.getCurrHealth() == 0) {
            if(player.checkForItem("FFS") && player.checkForItem("WTF")) winCount++;
            loot = generateItem(enemy.getPosition());
            score += 100;
            enemy = new Goblin();
            Random rand = new Random();
            int[] temp = new int[2];
            do{
                temp = new int[]{rand.nextInt(10), rand.nextInt(5)};
            } while(temp == player.getPosition() || temp == loot.getPosition());
            enemy.setPosition(temp);
        }
    }

    boolean checkForCombat() {
        if(Arrays.equals(player.getNextPosition(), enemy.getNextPosition()) || (Arrays.equals(player.getNextPosition(), enemy.getPosition()) && Arrays.equals(enemy.getNextPosition(), player.getPosition()))) return true;
        else return false;
    }

    boolean checkForLoot() {
        if(Arrays.equals(player.getNextPosition(), loot.getPosition())) return true;
        else return false;
    }

    void playGame() {
        Scanner input = new Scanner(System.in);
        do {
            updateBoard();
            printBoard();
            enemyMove();
            boolean temp = false;
            do {
                temp = playerMove(input.next());
            }while (!temp);
            resolveMove();
        }while(!isDead && winCount < 5);
        printBoard();
        System.out.println(winCount == 5 ? "\"YOU KNOW WHAT? FORGET THIS. CONGRATULATIONS, YOU WIN. NOW GET OUT OF HERE!\"" : "Game Over");
    }
}
