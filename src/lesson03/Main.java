package lesson03;

import ru.jawawebinar.webapp.model.Contact;
import ru.jawawebinar.webapp.model.ContactType;

/**
 * denis
 * 31.10.2016.
 */
public class Main {

    public static void main(String[] args) {
        Contact c = new Contact(ContactType.MOBILE,"+7(900)100-10-00");
        System.out.println(c.getType() + ": " + c.getValue());

    }
}


