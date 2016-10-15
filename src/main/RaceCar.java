package main;

/**
 * denis
 * 15.10.2016.
 */
public class RaceCar extends AbstractCar {
    public RaceCar() {
        speed = 200;
    }

    @Override
    public double getEngineVolume() {
        return 3.5;
    }
}
