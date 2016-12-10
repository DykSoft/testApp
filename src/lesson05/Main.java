package lesson05;

import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * denis
 * 14.11.2016.
 */
public class Main {

    public static void main(String[] args) {
/*        System.out.println(new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {

                System.out.println(this.getClass().getName());
                return 0;
            }
        });*/


        Comparator<Resume> comparator = new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                System.out.println(this.getClass().getSimpleName());
                return 0;
            }
        };

//лямда
/*        Comparator<Resume> comparator = (Resume o1, Resume o2) -> {
            System.out.println();
            return 0;
        };*/

        System.out.println(comparator);

        Resume R1 = new Resume("Полное Имя1", "location1");
        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");
        Resume R2 = new Resume("Полное Имя2", null);
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");
        Resume R3 = new Resume("Полное Имя3", null);

        //List<Resume> resumes = new LinkedList<>();

        Resume r2 = new Resume() {
            @Override
            public String getUuid() {
                return "uuid_R2";
            }
        };

        List<Resume> resumes = Arrays.asList(R1, R2, R3);

        print(resumes);

        List<Resume> resumes1 = Collections.singletonList(r2);
        print(resumes1);



    }

/*    public static void print(List<Resume> list) {

        list.forEach((r) -> System.out.println(r.toString()));
        list.forEach((r) -> System.out.println(r.getUuid()));
        list.forEach((r) -> System.out.println(r.getClass().getSimpleName()));

    }*/

/*    public static <T> void print(List<T> list) {

        list.forEach((r) -> System.out.println(r.toString()));
        list.forEach((r) -> System.out.println(r.getUuid()));
        list.forEach((r) -> System.out.println(r.getClass().getSimpleName()));

    }*/

    public static <T extends Resume> void print(List<T> list) {

        list.forEach((r) -> System.out.println(r.toString()));
        list.forEach((r) -> System.out.println(r.getUuid()));
        list.forEach((r) -> System.out.println(r.getClass().getSimpleName()));

    }

}
