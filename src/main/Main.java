package main;

import ru.jawawebinar.webapp.model.Resume;

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



        Resume resume = new Achievement("Achievement");

        resume.setDescription("123");
        resume.setDescription("234");
        resume.getDescription();




       }
}
