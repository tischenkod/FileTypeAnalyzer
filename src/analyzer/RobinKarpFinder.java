package analyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

public class RobinKarpFinder  extends Finder{
    final static long M = 1_000_000_000 + 9;
    final static long A = 257;

    private long pow;
    private int hashPos;

    public RobinKarpFinder(ArrayList<SearchPattern> patterns) {
        super(patterns);
        pow = 0;
        hashPos = -1;
    }

    @Override
    boolean search(byte[] data, byte[] pattern) throws Exception {
        if (pattern.length > data.length) return false;
        long patternHash = calculateHash(pattern);
        hashPos = data.length - pattern.length;
        long hash = 0;
        boolean first = true;
        do {
            if (first) {
                hash = calculateHash(Arrays.copyOfRange(data, data.length - pattern.length, data.length));
                first = false;
            } else {
                hash = calculateNext(data, hash, pattern.length);
            }
            if (hash == patternHash && Arrays.compare(data, hashPos, hashPos + pattern.length, pattern, 0, pattern.length) == 0) {
                return true;
            }


        }while (hashPos > 0);
        return false;
    }

//    long[] calculateHashes(byte[] data, int size) {
//        if (size > data.length) return null;
//        long hash = calculateHash(Arrays.copyOfRange(data, data.length - size, data.length));
//        long[] hashes = new long[data.length - size + 1];
//        hashes[hashes.length - 1] = hash;
//        for (int i = data.length - size - 1; i >= 0; i--) {
//            hash = ((hash - data[i + size] * pow) * A + data[i]) % M;
//            hashes[i] = hash;
//        }
//        return hashes;
//    }

    long calculateNext(byte[] data, long prevHash, int size) {
        hashPos--;
        return ((prevHash - data[hashPos + size] * pow % M + M) * A + data[hashPos]) % M;
    }

    long calculateHash(byte[] data) {
        long hash = 0;
        pow = 1;
        for (int i = 0; i < data.length; i++) {
            hash = (hash + data[i] * pow) % M;
            if (i < data.length - 1) {
                pow = pow * A % M;
            }
        }
        return hash;
    }
}
