package se.jbringb.problem;

import se.jbringb.common.Direction;
import se.jbringb.common.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static se.jbringb.util.MathUtil.picks;

public class Day18 extends Day {
    @Override
    protected String part1() {
        return String.valueOf(getArea(getInstructions(getInput(), false)));
    }

    @Override
    protected String part2() {
        return String.valueOf(getArea(getInstructions(getInput(), true)));
    }

    private static long getArea(final List<Instruction> instructions) {
        final List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        long boundary = 0;
        for (Instruction instruction : instructions) {
            final Point d = instruction.direction();
            final int steps = instruction.steps();
            final Point lastPoint = points.get(points.size() - 1);
            boundary += steps;
            points.add(new Point(lastPoint.y() + d.y() * steps, lastPoint.x() + d.x() * steps));
        }

        long area = picks(points);
        return (area + boundary) / 2 + 1;
    }

    private static List<Instruction> getInstructions(final List<String> input, final boolean extraction) {
        return input.stream().map(line -> {
            final String[] split = line.replaceAll("[)(#]", "").split("\\s");
            if (extraction) {
                final String color = split[2];
                return new Instruction(
                        switch (color.charAt(color.length() - 1)) {
                            case '0' -> new Point(0, 1);
                            case '1' -> new Point(1, 0);
                            case '2' -> new Point(0, -1);
                            case '3' -> new Point(-1, 0);
                            default -> throw new IllegalStateException("Invalid direction!");
                        },
                        Integer.decode("0x" + color.substring(0, color.length() - 1)));
            } else {
                return new Instruction(Direction.getPoint(split[0]), Integer.parseInt(split[1]));
            }
        }).collect(Collectors.toList());
    }

    protected record Instruction(Point direction, int steps) {

    }
}
