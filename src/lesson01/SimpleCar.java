package lesson01;

/**
 * denis
 * 15.10.2016.
 */
public class SimpleCar extends AbstractCar {
    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Something custom ");
    }

    @Override
    public double getEngineVolume() {
        return 1.7;
    }
}
