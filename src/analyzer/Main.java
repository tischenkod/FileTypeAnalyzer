package analyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws InterruptedException {
//        args = new String[]{"tests-folder", "patterns.db"};
        if (args.length <2) {
            System.out.println("Invalid arguments");
            return;
        }

        File baseDir = new File(args[0]);
        File patternsDB = new File(args[1]);

        if (!baseDir.exists()) {
            System.out.println("Folder does not exists");
            return;
        }
        if (!patternsDB.exists()) {
            System.out.println("Patterns do not exist");
            return;
        }
        ArrayList<SearchPattern> patterns = new ArrayList<>();
        try(Scanner scanner = new Scanner(patternsDB)) {
            while (scanner.hasNext()) {
                patterns.add(new SearchPattern(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        Collections.sort(patterns, Collections.reverseOrder());
        File[] files = baseDir.listFiles();
        if (files == null) {
            System.out.println("Not a folder");
            return;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


        for (File file :
                files) {
            executorService.submit(() -> {
                Finder finder = new RobinKarpFinder(patterns);
                try {
                    finder.search(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
