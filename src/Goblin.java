import java.util.Scanner;

public class Goblin extends Actor {
    public Goblin(){
        this.setMaxHealth(10);
        this.setCurrHealth(10);
        this.setPower(3);
        this.setDefense(1);
        this.setPosition(new int[]{9, 2});
        this.setNextPosition(new int[]{this.getPosition()[0], this.getPosition()[1]});
    }

    @Override
    void loseHealth(int dmg) {
        setCurrHealth(getCurrHealth() + getDefense() <= dmg ? 0 : getCurrHealth() + getDefense() - dmg);
    }
}
