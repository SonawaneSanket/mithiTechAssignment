package java_assignments.beg_assignment5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Index {
    public Index(Readable text) {
        Scanner sc = new Scanner(text);
        occurences = new HashMap<String, ArrayList<Integer>>();

        int lineNo = 1;
        try {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] words = line.split("\\W+");
                for (String word : words) {
                    word = word.toLowerCase();
                    ArrayList<Integer> list = occurences.get(word);
                    if (list == null) {
                        list = new ArrayList<>();
                        list.add(lineNo);
                    } else {
                        list.add(lineNo);
                    }
                    occurences.put(word, list);
                }
                lineNo++;
            }
        } finally {
            sc.close();
        }
    }

    public String toString() {
        return occurences.toString();
    }

    private Map<String, ArrayList<Integer>> occurences;
}