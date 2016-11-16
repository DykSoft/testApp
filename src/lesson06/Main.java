package lesson06;

import ru.jawawebinar.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * denis
 * 16.11.2016.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println(Integer.valueOf(10) == Integer.valueOf(10));
        System.out.println(Integer.valueOf(1000).equals(Integer.valueOf(1000)));

        List<String> es = Collections.emptyList();
        List<Resume> er = Collections.emptyList();

        print(Collections.<List<String>>emptyList());

        new ArrayList<Resume>(){
            {
                add(new Resume());
                System.out.println(getClass());

            }

        };



    }

    public static <T> void print(List<T> list) {

        System.out.println(list.toString());

    }
}
