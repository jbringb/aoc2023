package se.jbringb.problem;

import se.jbringb.common.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Day17 extends Day {
    static short[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    @Override
    protected String part1() {
        int[][] grid = getGrid(getInput());
        return String.valueOf(findLeastHeatlossPath(grid, false));
    }

    @Override
    protected String part2() {
        int[][] grid = getGrid(getInput());
        return String.valueOf(findLeastHeatlossPath(grid, true));
    }

    private int findLeastHeatlossPath(final int[][] grid, final boolean ultraCrucibles) {
        Comparator<Node> comparator = Comparator.comparingInt(Node::hl);
        Queue<Node> q = new PriorityQueue<>(comparator);
        Set<Node> visited = new HashSet<>();
        q.add(new Node(0, 0, 0, 0, 1, 1));
        q.add(new Node(0, 0, 0, 1, 0, 1));
        while (!q.isEmpty()) {
            final Node n = q.poll();
            if (n.y() == grid.length - 1 && n.x() == grid[0].length - 1 && (!ultraCrucibles || n.moves() >= 4)) {
                return n.hl();
            }

            if (visited.contains(n))
                continue;

            visited.add(n);

            q.addAll(getNextNodes(grid, n, ultraCrucibles));
        }
        return -1;
    }

    private List<Node> getNextNodes(final int[][] grid, final Node n, final boolean ultraCrucibles) {
        List<Node> nodes = new ArrayList<>();
        if (n.moves() < (ultraCrucibles ? 10 : 3) && (n.dy() != 0 || n.dx() != 0)) { // Continue in the same direction.
            int newY = n.y() + n.dy();
            int newX = n.x() + n.dx();

            if (newY >= 0 && newY < grid.length && newX >= 0 && newX < grid[0].length) {
                nodes.add(new Node(n.hl() + grid[newY][newX], newY, newX, n.dy(), n.dx(), 1 + n.moves()));
            }
        }

        if (!ultraCrucibles || n.moves() >= 4) {
            for (short[] direction : directions) {
                short newDy = direction[0];
                short newDx = direction[1];

                if ((newDy != n.dy() || newDx != n.dx()) && (newDy != -n.dy() || newDx != -n.dx())) {
                    int newY = n.y() + newDy;
                    int newX = n.x() + newDx;

                    if (newY >= 0 && newY < grid.length && newX >= 0 && newX < grid[0].length) {
                        nodes.add(new Node(n.hl() + grid[newY][newX], newY, newX, newDy, newDx, 1));
                    }
                }
            }
        }
        return nodes;
    }

    private int[][] getGrid(List<String> input) {
        final int height = input.size();
        final int length = input.get(0).length();
        final int[][] grid = new int[height][length];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                grid[y][x] = Character.getNumericValue(input.get(y).charAt(x));
            }
        }
        return grid;
    }

}
