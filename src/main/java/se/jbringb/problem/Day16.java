package se.jbringb.problem;

import lombok.AllArgsConstructor;
import lombok.Data;
import se.jbringb.common.Direction;
import se.jbringb.common.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static se.jbringb.common.Direction.EAST;
import static se.jbringb.common.Direction.NORTH;
import static se.jbringb.common.Direction.SOUTH;
import static se.jbringb.common.Direction.WEST;

public class Day16 extends Day {
    private final static Set<Position> visitedPositions = new HashSet<>();

    @Override
    protected String part1() {
        Grid grid = getGrid(getInput());
        int energized = moveBeam(grid, 0, 0, EAST);
        return String.valueOf(energized);
    }

    @Override
    protected String part2() {
        List<String> input = getInput();
        List<Integer> scores = new ArrayList<>();
        int length = input.get(0).length();
        int height = input.size();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < length; x++) {
                if (y == 0) {
                    System.out.println("Going SOUTH");
                    scores.add(moveBeam(getGrid(getInput()), y, x, SOUTH));
                    visitedPositions.clear();
                }
                if (y == height - 1) {
                    System.out.println("Going NORTH");
                    scores.add(moveBeam(getGrid(getInput()), y, x, NORTH));
                    visitedPositions.clear();
                }
                if (x == 0) {
                    System.out.println("Going EAST");
                    scores.add(moveBeam(getGrid(getInput()), y, x, EAST));
                    visitedPositions.clear();
                }
                if (x == length - 1) {
                    System.out.println("Going WEST");
                    scores.add(moveBeam(getGrid(getInput()), y, x, WEST));
                    visitedPositions.clear();
                }
            }
        }
        return String.valueOf(scores.stream().max(Integer::compareTo).orElse(null));
    }

    private int moveBeam(Grid grid, int y, int x, Direction direction) {
        Position pos = new Position(y, x, direction);
        int count = 0;
        while (visitedPositions.stream().noneMatch(p -> p.equals(pos))) {

            visitedPositions.add(new Position(pos.getY(), pos.getX(), pos.getDirection()));
            final Tile currentTile = grid.getTiles()[pos.getY()][pos.getX()];
            if (!currentTile.isEnergized()) {
                grid.energizeTile(pos.getY(), pos.getX());
                count += 1;
            }
            if (isVerticalSplitter(currentTile, pos)) {
                pos.setDirection(NORTH);
                count += moveBeam(grid, pos.getY(), pos.getX(), SOUTH);
            } else if (isHorizontalSplitter(currentTile, pos)) {
                pos.setDirection(EAST);
                count += moveBeam(grid, pos.getY(), pos.getX(), WEST);
            } else if (isUpwardMirror(currentTile)) {
                if (pos.getDirection() == EAST) pos.setDirection(NORTH);
                else if (pos.getDirection() == WEST) pos.setDirection(SOUTH);
                else if (pos.getDirection() == SOUTH) pos.setDirection(WEST);
                else if (pos.getDirection() == NORTH) pos.setDirection(EAST);
            } else if (isDownwardMirror(currentTile)) {
                if (pos.getDirection() == EAST) pos.setDirection(SOUTH);
                else if (pos.getDirection() == WEST) pos.setDirection(NORTH);
                else if (pos.getDirection() == SOUTH) pos.setDirection(EAST);
                else if (pos.getDirection() == NORTH) pos.setDirection(WEST);
            }
            move(pos);
            if (pos.getX() < 0 || pos.getX() >= grid.getTiles()[0].length || pos.getY() < 0 || pos.getY() >= grid.getTiles().length)
                break;
        }
        return count;
    }

    private boolean isVerticalSplitter(Tile tile, Position pos) {
        return tile.type == '|' && (pos.getDirection() == EAST || pos.getDirection() == WEST);
    }

    private boolean isHorizontalSplitter(Tile tile, Position pos) {
        return tile.type == '-' && (pos.getDirection() == NORTH || pos.getDirection() == SOUTH);
    }

    private boolean isUpwardMirror(Tile tile) {
        return tile.type == '/';
    }

    private boolean isDownwardMirror(Tile tile) {
        return tile.type == '\\';
    }

    private void move(Position pos) {
        switch (pos.getDirection()) {
            case NORTH -> pos.move(0, -1);
            case EAST -> pos.move(1, 0);
            case SOUTH -> pos.move(0, 1);
            case WEST -> pos.move(-1, 0);
        }
    }

    private Grid getGrid(List<String> input) {
        Tile[][] tiles = new Tile[input.size()][input.get(0).length()];
        for (int x = 0; x < input.get(0).length(); x++) {
            for (int y = 0; y < input.size(); y++) {
                tiles[y][x] = new Tile(y, x, input.get(y).charAt(x), false);
            }
        }
        return new Grid(tiles);
    }

    @AllArgsConstructor
    @Data
    protected class Tile {
        private int y;
        private int x;
        private char type;
        private boolean energized;
    }

    @AllArgsConstructor
    @Data
    protected class Grid {
        private Tile[][] tiles;

        protected void energizeTile(int y, int x) {
            tiles[y][x].setEnergized(true);
        }
    }
}
