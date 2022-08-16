package java_assignments.beg_assignment5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;


public class BookIndexer {
    public static void main(String[] args) throws IOException {
        Reader br = getReader(args);
        String indexStr = getOccurencesMap(br).toString();
        System.out.println(indexStr);
    }

    private static Reader getReader(String[] args) {
        if (args.length == 0) {
            return new BufferedReader(new InputStreamReader(System.in));
        } else {
            try {
                return new BufferedReader(new FileReader(args[0]));
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("The given file does not exist.", e);
            }
        }
    }

    private static Map<String, List<Integer>> getOccurencesMap(Reader text) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(text)) {
            return reader.lines()
                         .flatMap(Pattern.compile("\\s+")::splitAsStream)
                         .map(w -> w.toLowerCase(Locale.ROOT))
                         .collect(Collectors.groupingBy(
                             w -> w,
                             Collectors.mapping(w -> reader.getLineNumber(), Collectors.toList())
                         ));
        }
    }
}