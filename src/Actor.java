public abstract class Actor {
    private int maxHealth = 50;
    private int currHealth = 50;
    private int power = 1;
    private int defense = 1;
    private int[] position = new int[]{0, 0};

    abstract void move();
    public void loseHealth(int dmg){
        currHealth = currHealth + defense <= dmg ? 0 : currHealth + defense - dmg;
    };
    public void gainHealth(int heal){
        currHealth = currHealth + heal >= maxHealth ? maxHealth : currHealth + heal;
    };
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public int getCurrHealth() {
        return currHealth;
    }
    public void setCurrHealth(int currHealth) {
        this.currHealth = currHealth;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int[] getPosition() {
        return position;
    }
    public void setPosition(int[] position) {
        this.position = position;
    }
}
