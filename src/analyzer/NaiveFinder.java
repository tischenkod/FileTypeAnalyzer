//package analyzer;
//
//import java.util.Arrays;
//
//public class NaiveFinder extends Finder{
//    @Override
//    public boolean search(byte[] data, byte[] pattern) {
//        for (int i = 0; i < data.length - pattern.length + 1; i++) {
//            if (Arrays.equals(data, i, i + pattern.length, pattern, 0, pattern.length)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
