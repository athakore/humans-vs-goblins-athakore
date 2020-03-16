import java.util.Scanner;

public class Human extends Actor {
private Item[] inventory = new Item[5];
private int modifiedPower;
private int modifiedDefense;
    public Human(){
        this.setMaxHealth(25);
        this.setCurrHealth(25);
        this.setPower(5);
        this.modifiedPower = getPower();
        this.setDefense(0);
        this.modifiedDefense = getDefense();
        this.setPosition(new int[]{0, 2});
        this.setNextPosition(new int[]{this.getPosition()[0], this.getPosition()[1]});
        this.inventory = new Item[]{
                null,
                null,
                null,
                null,
                null
        };
        updateStats();
    }

    public boolean isInventoryFull(){
        boolean isFull = true;
        for (Item i : inventory){
            if(i == null){
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public void pickUpItem(Item i ) {
        int temp = checkForType(i.getType());
        if(temp == -1) {
            if(isInventoryFull()) {
                System.out.println("You're inventory is full, you can't pick up this item.");
            }
            else {
                for(int j = 0; j < inventory.length; j++) {
                    if(inventory[j] == null) {
                        inventory[j] = i;
                        updateStats();
                        break;
                    }
                }
            }
        }
        else {
            System.out.println(String.format(
            """
            You already have a %s. Would you like to replace it? (Y or N)
            Current:  Replace:
            -----     -----
            |%s|     |%s|
            -----     -----
            POW: +%s   POW: +%s
            DEF: +%s   DEF: +%S
            """,
                    i.getType().toString(),
                    inventory[temp].getName(),
                    i.getName(),
                    inventory[temp].getPower(),
                    i.getPower(),
                    inventory[temp].getDefense(),
                    i.getDefense()));
            if(new Scanner(System.in).next().equalsIgnoreCase("Y")) {
                inventory[temp] = i;
                updateStats();
            }
        }
    }

    public int checkForType(ItemType t) {
        int hasType = -1;
        for(int j = 0; j < inventory.length; j++) {
            if(inventory[j] != null && inventory[j].getType().equals(t) && !t.equals(ItemType.consumable)) {
                hasType = j;
                break;
            }
        }
        return hasType;
    }

    public boolean consumePotion() {
        boolean hasPotion = false;
        if(getCurrHealth() == getMaxHealth()) {
            System.out.println("You're at maximum health, you can't use a potion right now.");
            return false;
        }
        for(int i = inventory.length - 1; i >= 0; i--) {
            if(inventory[i] != null && inventory[i].getName().equals("PTN")) {
                hasPotion = true;
                gainHealth(inventory[i].getHealth());
                inventory[i] = null;
                break;
            }
        }
        if(!hasPotion) System.out.println("You don't have any potions!");
        return hasPotion;
    }

    public void updateStats() {
        modifiedPower = getPower();
        modifiedDefense = getDefense();
        for (Item i : inventory){
            if(i != null && (i.getType().equals(ItemType.weapon) || i.getType().equals(ItemType.shield))){
                modifiedPower += i.getPower();
                modifiedDefense += i.getDefense();
            }
        }
    }

    public boolean checkForItem(String name) {
        for (Item item: inventory) {
            if(item.getName().equals(name)) return true;
        }
        return false;
    }

    public Item[] getInventory() {
        return inventory;
    }

    public int getModifiedPower() {
        return modifiedPower;
    }

    public int getModifiedDefense() {
        return modifiedDefense;
    }

    @Override
    void loseHealth(int dmg) {
        setCurrHealth(getCurrHealth() + getModifiedDefense() <= dmg ? 0 : getCurrHealth() + getModifiedDefense() - dmg);

    }
}
