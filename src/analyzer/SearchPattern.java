package analyzer;

import java.util.Scanner;

public class SearchPattern implements Comparable<SearchPattern>{
    Integer priority;
    String pattern;
    String description;

    public SearchPattern(String inputStr) {
        Scanner scanner = new Scanner(inputStr).useDelimiter(";");
        this.priority = scanner.nextInt();
        this.pattern = scanner.next().replaceAll("^\"+|\"+$", "");
        this.description = scanner.next().replaceAll("^\"+|\"+$", "");
    }

    @Override
    public int compareTo(SearchPattern o) {
        return this.priority.compareTo(o.priority);
    }
}
