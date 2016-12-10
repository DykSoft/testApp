package lesson04;

import ru.javawebinar.webapp.model.Organization;
import ru.javawebinar.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

/**
 * denis
 * 08.11.2016.
 */
public class Main {

    public static void main(String[] args) {

        new Organization.Period();

        Map<String, Resume> map = new HashMap<>();
        map.put("uuid", new Resume("uuid", "", ""));

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }


    }
}
