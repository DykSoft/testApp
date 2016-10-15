package main;

public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello " + args[0] + "!");
        System.out.format("Hello %s !\n", args[0]);

        Car raceCar = new RaceCar();
        Car simpleCar = new SimpleCar();
        raceCar.setSpeed(200);
        System.out.println(raceCar.getSpeed());
        raceCar.getDescription();
        simpleCar.getDescription();


       }
}
