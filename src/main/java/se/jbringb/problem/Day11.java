package se.jbringb.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day11 extends Day {
    @Override
    protected String part1() {
        final long totalDistance = getTotalDistance(getInput(), 2);
        return String.valueOf(totalDistance);
    }

    @Override
    protected String part2() {
        final long totalDistance = getTotalDistance(getInput(), 1000000);
        return String.valueOf(totalDistance);
    }

    private long getTotalDistance(final List<String> input, final int coefficient) {
        final List<List<Character>> sky = getSky(input);
        final List<Integer> emptyRows = getEmptyRows(sky);
        final List<Integer> emptyColumns = getEmptyColumns(sky);
        final List<int[]> galaxyCoords = getGalaxyCoords(sky);

        return getTotalDistance(galaxyCoords, emptyRows, emptyColumns, coefficient);
    }

    private static long getTotalDistance(final List<int[]> galaxyCoords, final List<Integer> emptyRows,
                                         final List<Integer> emptyColumns, final int coefficient) {
        long totalDistance = 0;
        for (int i = 0; i < galaxyCoords.size(); i++) {
            int[] coords1 = galaxyCoords.get(i);
            for (int j = 0; j < i; j++) {
                int[] coords2 = galaxyCoords.get(j);
                totalDistance = updateTotalDistance(totalDistance, coords1, coords2, 0, emptyRows, coefficient);
                totalDistance = updateTotalDistance(totalDistance, coords1, coords2, 1, emptyColumns, coefficient);
            }
        }

        return totalDistance;
    }

    private static List<int[]> getGalaxyCoords(final List<List<Character>> sky) {
        final List<int[]> galaxyCoords = new ArrayList<>();
        for (int y = 0; y < sky.size(); y++) {
            for (int x = 0; x < sky.get(y).size(); x++) {
                if (sky.get(y).get(x) == '#') {
                    galaxyCoords.add(new int[]{y, x});
                }
            }
        }
        return galaxyCoords;
    }

    private List<List<Character>> getSky(final List<String> input) {
        return input.stream()
                .map(l -> l.chars()
                        .mapToObj(c -> (char) c)
                        .toList())
                .toList();
    }

    private static List<Integer> getEmptyRows(final List<List<Character>> sky) {
        return IntStream.range(0, sky.size())
                .filter(i -> sky.get(i)
                        .stream()
                        .allMatch(c -> c == '.'))
                .boxed()
                .toList();
    }

    private static List<Integer> getEmptyColumns(final List<List<Character>> sky) {
        final List<Integer> emptyCols = new ArrayList<>();
        for (int i = 0; i < sky.get(0).size(); i++) {
            int emptyCount = 0;
            for (List<Character> row : sky) {
                if (row.get(i) == '.') {
                    emptyCount++;
                }
            }
            if (emptyCount == sky.size()) {
                emptyCols.add(i);
            }
        }
        return emptyCols;
    }

    private static long updateTotalDistance(long totalDistance, final int[] coords1, final int[] coords2, final int index,
                                            final List<Integer> emptyIndexes, final int coefficient) {
        totalDistance += IntStream
                .range(Math.min(coords2[index], coords1[index]), Math.max(coords2[index], coords1[index]))
                .map(row -> (emptyIndexes.contains(row)) ? coefficient : 1)
                .sum();
        return totalDistance;
    }
}
