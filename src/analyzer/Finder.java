package analyzer;

import java.io.*;
import java.util.ArrayList;

public abstract class Finder {

    ArrayList<SearchPattern> patterns;

    public Finder(ArrayList<SearchPattern> patterns) {
        this.patterns = patterns;
    }

    abstract boolean search(byte[] data, byte[] pattern) throws Exception;

    public void search(File file) throws Exception {
        byte[] buff;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
            buff = bis.readAllBytes();

        } catch (FileNotFoundException e) {
            System.out.printf("%s: File not found%n", file.getName());
            return;
        } catch (IOException e) {
            System.out.printf("%s: IO error%n", file.getName());
            return;
        }
        int i;
        for (i = 0; i < patterns.size(); i++) {
            if (search(buff, patterns.get(i).pattern.getBytes())) {
                System.out.printf("%s: %s%n", file.getName(), patterns.get(i).description);
                break;
            }
        }
        if (i == patterns.size()) {
            System.out.printf("%s: Unknown file type%n", file.getName());
        }
    }
}
