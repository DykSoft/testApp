package lesson01;

/**
 * denis
 * 15.10.2016.
 */
public abstract class AbstractCar implements Car {
    protected int speed = 100;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void getDescription() {
        out("\n" + this.getClass().getSimpleName() + " (Speed " + speed + ", EngineVolume:"  + getEngineVolume() + ")");

    }

    private void out(String str) {
        System.out.println(str);
    }

}
