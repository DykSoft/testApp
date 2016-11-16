package lesson06;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;


/**
 * denis
 * 16.11.2016.
 */
public class FileMain {

    public static void main(String[] args) throws IOException {

      Path path = Paths.get("C:\\java\\idea.txt");

        Iterator<String> it = Files.lines(path,StandardCharsets.UTF_8).iterator();

        while(it.hasNext()) {

            System.out.println(it.next());

        }

        System.out.println("**************************");

        Files.lines(Paths.get("C:\\java\\idea.txt"),StandardCharsets.UTF_8).forEach(System.out::println);

        System.out.println("**************************");

        Files.lines(Paths.get("C:\\java\\idea.txt"),StandardCharsets.UTF_8).forEach(line -> System.out.println(line));

    }
}
