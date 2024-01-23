package se.jbringb.common;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Point getPoint(final Direction direction) {
        return switch (direction) {
            case NORTH -> new Point(-1, 0);
            case EAST -> new Point(0, 1);
            case SOUTH -> new Point(1, 0);
            case WEST -> new Point(0, -1);
        };
    }

    public static Point getPoint(final String direction) {
        return switch (direction) {
            case "N", "U" -> new Point(-1, 0);
            case "E", "R" -> new Point(0, 1);
            case "S", "D" -> new Point(1, 0);
            case "W", "L" -> new Point(0, -1);
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }
}

