package ru.jawawebinar.webapp.model;

/**
 * denis
 * 24.10.2016.
 */
public class Objective extends AbstractResume {

    private String description;

    public Objective(String object) {
        objective = object;
    }

    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("\n" + objective + ": " + description);
    }

    @Override
    public void setDescription(String str) {

        this.description = str;

    }
}
