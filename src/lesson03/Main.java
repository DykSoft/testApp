package lesson03;

import ru.jawawebinar.webapp.model.Contact;
import ru.jawawebinar.webapp.model.ContactType;
import ru.jawawebinar.webapp.model.Link;

import java.lang.reflect.Field;

/**
 * denis
 * 31.10.2016.
 */
public class Main {

    public static void main(String[] args) {
        Contact c = new Contact(ContactType.MOBILE,"+7(900)100-10-00");
        System.out.println(c.getType() + ": " + c.getValue());

        //Link l = new Link();
        try {
            Field f = Link.class.getDeclaredField("url");
            f.setAccessible(true);
            Link l = new Link("qwerty","URL");
            System.out.println(f.get(l));
            System.out.println(l.getUrl());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}


