package chapter03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecutingAround {

    private static final String FILE = ExecutingAround.class.getResource("chapter05/data.txt").getFile();

    public static void main(String... args) throws IOException {
        //
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("====");

        String oneLine = processFile((BufferedReader r) -> r.readLine());
        System.out.println(oneLine);
        System.out.println("====");

        String twoLine = processFile((BufferedReader r) -> r.readLine() + r.readLine());
        System.out.println(twoLine);


    }

    public static String processFileLimited() throws IOException {
        //
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            return reader.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        //
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            return p.process(reader);
        }
    }

    public interface BufferedReaderProcessor {
        //
        String process(BufferedReader reader) throws IOException;
    }
}
