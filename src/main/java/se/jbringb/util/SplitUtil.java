package se.jbringb.util;

import java.util.Arrays;
import java.util.List;

public class SplitUtil {
    public static List<Long> splitToLongList(final String s) {
        return Arrays.stream(s.split("\\s+")).mapToLong(Long::parseLong).boxed().toList();
    }
}
