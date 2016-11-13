package lesson05;

import ru.jawawebinar.webapp.model.Resume;

import java.util.Comparator;

/**
 * denis
 * 12.11.2016.
 */
public class Calculator {

    public int abs(int value) {

        return Math.abs(value);

    }

    public static void main(String[] args) {
        System.out.println(new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {

                System.out.println(this.getClass().getName());
                return 0;
            }
        });
    }

}
