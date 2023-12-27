package se.jbringb.problem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day10 extends Day {
    private static final List<Character> RIGHT_OPEN_PIPES = Arrays.asList('-', 'J', '7', 'S');
    private static final List<Character> LEFT_OPEN_PIPES = Arrays.asList('-', 'L', 'F', 'S');
    private static final List<Character> DOWN_OPEN_PIPES = Arrays.asList('|', '7', 'F', 'S');
    private static final List<Character> UP_OPEN_PIPES = Arrays.asList('|', 'L', 'J', 'S');

    @Override
    protected String part1() {
        final Set<Tile> grid = getGrid(getInput());
        final Set<Tile> loopTiles = getLoopTiles(grid);
        return String.valueOf(loopTiles.size() / 2);
    }

    @Override
    protected String part2() {
        final Set<Tile> grid = getGrid(getInput());
        final Set<Tile> loopTiles = getLoopTiles(grid);
        int insideTileCount = 0;
        for (int y = 0; y < getInput().size(); y++) {
            final StringBuilder s = new StringBuilder();
            final String line = getInput().get(y);
            boolean isInside = false;
            Character previousConnection = null;
            for (int x = 0; x < line.length(); x++) {
                boolean isLoopTile = getTile(loopTiles, x, y) != null;
                char connection = line.charAt(x);

                isInside = isInside ? !isLoopTile || !isWall(connection, previousConnection) : isLoopTile && isWall(connection, previousConnection);

                if (isLoopTile && (Arrays.asList('L', '7', 'J', 'F').contains(connection))) {
                    previousConnection = connection;
                }

                if (isInside && !isLoopTile) {
                    insideTileCount++;
                    s.append('@');
                } else if (isLoopTile) {
                    s.append('#');
                } else {
                    s.append(connection);
                }
            }
            System.out.println(s);
        }
        return String.valueOf(insideTileCount);
    }

    private boolean isWall(final char connection, final Character previousConnection) {
        if (connection == '|') {
            return true;
        }

        if (previousConnection != null) {
            return (previousConnection == 'L' && connection == '7') || (previousConnection == 'F' && connection == 'J');
        }
        return false;
    }

    private Tile getTile(final Set<Tile> tiles, final int x, final int y) {
        return tiles.stream().filter(t -> t.x == x && t.y == y).findFirst().orElse(null);
    }

    private Set<Tile> getLoopTiles(Set<Tile> grid) {
        final Set<Tile> loopTiles = new LinkedHashSet<>();
        Tile currentTile = grid.stream().filter(t -> t.connection == 'S').findFirst().orElseThrow();
        do {
            final Set<Tile> adjacentTiles = getAdjacentOpenTiles(grid, currentTile);
            loopTiles.add(currentTile);
            currentTile = adjacentTiles.stream().filter(t -> !loopTiles.contains(t)).findFirst().orElse(null);
        } while (currentTile != null && currentTile.connection != 'S');
        return loopTiles;
    }

    private Set<Tile> getGrid(final List<String> input) {
        final Set<Tile> tiles = new HashSet<>();
        for (int y = 0; y < input.size(); y++) {
            final String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                tiles.add(new Tile(x, y, line.charAt(x)));
            }
        }
        return tiles;
    }

    private Set<Tile> getAdjacentOpenTiles(final Set<Tile> tiles, final Tile currentTile) {
        return tiles.stream().filter(t ->
                isRightOpen(currentTile, t) || isLeftOpen(currentTile, t) || isDownOpen(currentTile, t) || isUpOpen(currentTile, t)
        ).collect(Collectors.toSet());
    }

    private boolean isRightOpen(final Tile currentTile, final Tile nextTile) {
        return nextTile.x == currentTile.x + 1 && nextTile.y == currentTile.y
                && LEFT_OPEN_PIPES.contains(currentTile.connection)
                && RIGHT_OPEN_PIPES.contains(nextTile.connection);
    }

    private boolean isLeftOpen(final Tile currentTile, final Tile nextTile) {
        return nextTile.x == currentTile.x - 1 && nextTile.y == currentTile.y
                && RIGHT_OPEN_PIPES.contains(currentTile.connection)
                && LEFT_OPEN_PIPES.contains(nextTile.connection);
    }

    private boolean isDownOpen(final Tile currentTile, final Tile nextTile) {
        return nextTile.x == currentTile.x && nextTile.y == currentTile.y - 1
                && UP_OPEN_PIPES.contains(currentTile.connection)
                && DOWN_OPEN_PIPES.contains(nextTile.connection);
    }

    private boolean isUpOpen(final Tile currentTile, final Tile nextTile) {
        return nextTile.x == currentTile.x && nextTile.y == currentTile.y + 1
                && DOWN_OPEN_PIPES.contains(currentTile.connection)
                && UP_OPEN_PIPES.contains(nextTile.connection);
    }

    protected record Tile(int x, int y, char connection) {
    }
}
