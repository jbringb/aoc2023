package se.jbringb.util;

import se.jbringb.common.Point;

import java.util.List;

public class MathUtil {
    public static long picks(final List<Point> points) {
        long area = 0;
        for (int j = 0; j < points.size() - 1; j++) {
            area += determinant(points.get(j).x(), points.get(j).y(), points.get(j + 1).x(), points.get(j + 1).y());
        }
        return Math.abs(area);
    }

    public static long determinant(long x1, long y1, long x2, long y2) {
        return x1 * y2 - x2 * y1;
    }
}
