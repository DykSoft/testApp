package lesson02;

import ru.jawawebinar.webapp.model.Link;

/**
 * denis
 * 27.10.2016.
 */
public class Main {
    public static void main(String[] args) {

       Link l1 = new Link("javawebinar","javawebinar.ru");
       Link l2 = l1;
       Link l3 = new Link(l1);
       System.out.println(l1.getClass());
       System.out.println(l1.equals(l2));
       System.out.println(l1.equals(l3));


    }
}
