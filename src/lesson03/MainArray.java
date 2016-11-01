package lesson03;

import ru.jawawebinar.webapp.model.Link;
import ru.jawawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * denis
 * 01.11.2016.
 */
public class MainArray {


    public static void main(String[] args) {

        int[] array = new int[]{1,2,3};
        System.out.println(array.length);
        System.out.println(array.getClass());
        System.out.println(Arrays.toString(array));

        Resume[] resumes = new Resume[10];
        System.out.println(resumes.length);
        System.out.println(resumes.getClass());

        Link l = new Link();
        ((Object[]) resumes)[0] = l;

    }


}
