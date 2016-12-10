package lesson03;

import ru.javawebinar.webapp.model.ContactNotUsed;
import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Link;

import java.lang.reflect.Field;

/**
 * denis
 * 31.10.2016.
 */
public class Main {

    public static void main(String[] args) {
        ContactNotUsed c = new ContactNotUsed(ContactType.MOBILE,"+7(900)100-10-00");
        System.out.println(c.getType() + ": " + c.getValue());

        //Link l = new Link();
        try {
            Field f = Link.class.getDeclaredField("url");
            f.setAccessible(true);
            Link l = new Link("qwerty","URL");
            System.out.println(f.get(l));
            System.out.println(l.getUrl());
            System.out.println(l instanceof Link);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        StringBuilder fill = new StringBuilder();
        for(int i=0; i < 1000; i++) {
            fill.append("a");
        }

        System.out.println(fill.toString());


    }
}


