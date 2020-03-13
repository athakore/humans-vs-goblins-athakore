import java.util.Random;

public class Board {
    private String[][] board;
    public Human player;
    private Goblin enemy;
    private Item loot;
    private int score;
    private String playerToken = "\uD83D\uDC78";
    private String enemyToken = "\uD83D\uDC7F";
    private String lootToken = "\uD83D\uDCB0";
    private boolean isDead = false;

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

    void printBoard() {
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
                Health: %s / %s | Power: %s | Defense: %s                         SWD => Sword(Increases power by 3)
                Inventory:                                                      SHD => Shield(Increases defense by 2)
                    ---------------------                                       RAP => Rapier(Increases power by 2 & defense by 1)
                    |%s|%s|%s|%s|%s|
                    ---------------------
                """,
                Integer.toString(score),
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
                player.getInventory()[0] == null ? "   " : player.getInventory()[0],
                player.getInventory()[1] == null ? "   " : player.getInventory()[1],
                player.getInventory()[2] == null ? "   " : player.getInventory()[2],
                player.getInventory()[3] == null ? "   " : player.getInventory()[3],
                player.getInventory()[4] == null ? "   " : player.getInventory()[4]
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
        if(temp > 1 && temp <= 21) temp = 2;
        else if(temp > 21 && temp <= 41) temp = 3;
        else if(temp > 41 && temp <= 61) temp = 4;
        else if(temp > 61 && temp <= 76) temp = 5;
        else if(temp > 76 && temp <= 91) temp = 6;
        else if(temp > 91 && temp <= 99) temp = 7;
        else if(temp == 100) temp = 8;
        switch(temp) {
            case 1:
                return new Item("WTF",ItemType.shield,0,4,0,pos); //Weapon Termination Field
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
                return new Item("FFS",ItemType.weapon,6,0,0,pos); //Final Fantasy Sword
            default:
                return new Item();

        }
    }

    void resolveMove() {
        if(checkForCombat()) {
            resolveCombat();
        }
        else {
            enemy.setPosition(new int[]{enemy.getNextPosition()[0], enemy.getNextPosition()[1]});
            player.setPosition(new int[]{player.getNextPosition()[0], player.getNextPosition()[1]});
        }
    }

    void resolveCombat() {
        enemy.loseHealth(player.getModifiedPower());
        player.loseHealth(enemy.getPower());
        if(enemy.getCurrHealth() == 0) {
            loot = generateItem(enemy.getPosition());
            enemy = new Goblin();
            Random rand = new Random();
            int[] temp = new int[2];
            do{
                temp = new int[]{rand.nextInt(5), rand.nextInt(10)};
            } while(temp == player.getPosition() || temp == loot.getPosition());
            enemy.setPosition(temp);
        }
    }

    boolean checkForCombat() {
        if(player.getNextPosition() == enemy.getNextPosition() || (player.getNextPosition() == enemy.getPosition() && enemy.getNextPosition() == player.getPosition())) return true;
        else return false;
    }
}
