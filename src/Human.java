public class Human extends Actor {

    public Human(){
        this.setMaxHealth(25);
        this.setCurrHealth(25);
        this.setPower(3);
        this.setDefense(1);
        this.setPosition(new int[]{0, 2});
    }
    @Override
    void move() {

    }
}
