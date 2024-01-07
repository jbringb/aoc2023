package se.jbringb.util;

import java.util.Arrays;
import java.util.List;

public class SplitUtil {
    public static List<Integer> splitToIntList(final String s, String regex) {
        return Arrays.stream(s.split(regex)).mapToInt(Integer::parseInt).boxed().toList();
    }

    public static List<Long> splitToLongList(final String s) {
        return splitToLongList(s, "\\s+");
    }

    public static List<Long> splitToLongList(final String s, String regex) {
        return Arrays.stream(s.split(regex)).mapToLong(Long::parseLong).boxed().toList();
    }
}
