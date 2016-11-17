package lesson06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;


/**
 * denis
 * 16.11.2016.
 */
public class FileMain {

    public static void main(String[] args) throws IOException {

        System.out.println("\nBy stream iterator\n");

        Path path = Paths.get("C:\\java\\idea.txt");

        Iterator<String> it = Files.lines(path, StandardCharsets.UTF_8).iterator();

        while (it.hasNext()) {

            System.out.println(it.next());

        }

        System.out.println("\nBy stream lambde\n");

        Files.lines(Paths.get("C:\\java\\idea.txt"), StandardCharsets.UTF_8).forEach(System.out::println);

        Files.lines(Paths.get("C:\\java\\idea.txt"), StandardCharsets.UTF_8).forEach(line -> System.out.println(line));

        System.out.println("\nBy stream Stream\n");

        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        lines.forEach(System.out::println);

        System.out.println("\nBuffered reader\n");

        BufferedReader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("C:\\java\\idea.txt"),StandardCharsets.UTF_8));

        String str;
        while ((str = reader.readLine())!=null) {
            System.out.println(str);

        }


    }
}
