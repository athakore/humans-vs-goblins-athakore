public class Item {
    private String name;
    private ItemType type;
    private int power;
    private int defense;
    private int health;
    private int[] position;

    public Item(){
        name = "PTN";
        type = ItemType.consumable;
        power = 0;
        defense = 0;
        health = 5;
        position = new int[]{-1, -1};
    }

    public Item(String name, ItemType type, int power, int defense, int health, int[] position) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.defense = defense;
        this.health = health;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name;
    }
}
