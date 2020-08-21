package analyzer;

import java.util.ArrayList;

public class KMPFinder extends Finder {
    public KMPFinder(ArrayList<SearchPattern> patterns) {
        super(patterns);
    }

    private int[] prefixFunction(byte[] data) {
        int[] pf = new int[data.length];
        int j = 0;
        for (int i = 1; i < data.length; i++) {
            while (j > 0 && data[j] != data[i]) {
                j = pf[j - 1];
            }
            if (data[j] == data[i]) {
                pf[i] = ++j;
            }
        }
        return pf;
    }

    @Override
    public boolean search(byte[] data, byte[] pattern) {
        int[] prefixFunc = prefixFunction(pattern);
        int j = 0;
        for (int i = 0; i < data.length; i++) {
            while (j > 0 && data[i] != pattern[j]) {
                j = prefixFunc[j-1];
            }
            if (data[i] == pattern[j]) {
                j++;
            }
            if (j == pattern.length) {
                return true;
            }
        }
        return false;
    }
}
