package se.jbringb.problem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14 extends Day {
    final static Map<String, PlatformTiltResult> cache = new HashMap<>();

    @Override
    protected String part1() {
        char[][] grid = getGrid(getInput());
        tiltPlatformNorth(grid);
        return String.valueOf(getLoad(grid, grid.length));
    }

    private char[][] getGrid(List<String> rows) {
        return rows.stream().map(String::toCharArray).toArray(p -> new char[rows.size()][rows.get(0).length()]);
    }

    @Override
    protected String part2() {
        List<String> input = getInput();
        char[][] grid = getGrid(input);
        int score = tiltPlatform(grid);
        return String.valueOf(score);
    }

    private static int getLoad(char[][] grid, int length) {
        int sum = 0;
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 'O') sum += length - y;
            }
        }
        return sum;
    }

    protected int tiltPlatform(char[][] grid) {
        String key = "";
        int cycle;
        for (cycle = 0; cycle < 1000000000; cycle++) {
            if (cycle % (1000000000 / 100) == 0) {
                System.out.printf("Cycle: %d%n", cycle);
            }
            key = getKey(grid);
            if (cache.containsKey(key)) {
                break;
            }
            cache.put(key, new PlatformTiltResult(grid, cycle, getLoad(grid, grid.length)));
            fullCycle(grid);
        }
        if (!cache.containsKey(key)) throw new RuntimeException("Something went wrong during tilting!");

        final PlatformTiltResult result = cache.get(key);
        final int cycleLength = cycle - result.cycle;
        final int index = (result.cycle + (1000000000 - result.cycle) % cycleLength);
        final Map.Entry<String, PlatformTiltResult> entry = cache.entrySet()
                .stream()
                .filter(p -> p.getValue().cycle == index)
                .findFirst()
                .orElse(null);

        return entry != null ? entry.getValue().score : -1;
    }

    private void fullCycle(char[][] grid) {
        tiltPlatformNorth(grid);
        tiltPlatformWest(grid);
        tiltPlatformSouth(grid);
        tiltPlatformEast(grid);
    }

    private void tiltPlatformEast(char[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            int stopIx = grid[0].length;
            for (int x = grid[0].length - 1; x >= 0; x--) {
                if (grid[y][x] == '#') stopIx = x;
                if (grid[y][x] == 'O') {
                    grid[y][x] = '.';
                    grid[y][--stopIx] = 'O';
                }
            }
        }
    }

    private void tiltPlatformSouth(char[][] grid) {
        for (int x = 0; x < grid[0].length; x++) {
            int stopIx = grid.length;
            for (int y = grid.length - 1; y >= 0; y--) {
                if (grid[y][x] == '#') stopIx = y;
                if (grid[y][x] == 'O') {
                    grid[y][x] = '.';
                    grid[--stopIx][x] = 'O';
                }
            }
        }
    }

    private void tiltPlatformWest(char[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            int stopIx = -1;
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == '#') stopIx = x;
                if (grid[y][x] == 'O') {
                    grid[y][x] = '.';
                    grid[y][++stopIx] = 'O';
                }
            }
        }
    }

    private void tiltPlatformNorth(char[][] grid) {
        for (int x = 0; x < grid[0].length; x++) {
            int stopIx = -1;
            for (int y = 0; y < grid.length; y++) {
                if (grid[y][x] == '#') stopIx = y;
                if (grid[y][x] == 'O') {
                    grid[y][x] = '.';
                    grid[++stopIx][x] = 'O';
                }
            }
        }
    }

    private String getKey(final char[][] grid) {
        return Arrays.stream(Arrays.copyOf(grid, grid.length)).map(String::new).collect(Collectors.joining());
    }

    protected record PlatformTiltResult(char[][] grid, int cycle, int score) {
    }
}
