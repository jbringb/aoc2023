package se.jbringb.problem;

import java.util.Arrays;
import java.util.List;

public class Day2 extends Day {
    @Override
    protected String part1() {
        return String.valueOf(getInput().stream().mapToInt(this::getPossibleGameId).sum());
    }

    @Override
    protected String part2() {
        int sum = getInput().stream()
                .map(this::getBestDrawResult)
                .mapToInt(drawResult -> drawResult.red() * drawResult.green() * drawResult.blue())
                .sum();
        return String.valueOf(sum);
    }

    protected DrawResult getBestDrawResult(final String line) {
        int red = 0, green = 0, blue = 0;
        final List<String[]> draws = getDrawList(line.split(": ")[1]);
        for (String[] draw : draws) {
            switch (draw[1]) {
                case "red" -> red = Math.max(Integer.parseInt(draw[0]), red);
                case "green" -> green = Math.max(Integer.parseInt(draw[0]), green);
                case "blue" -> blue = Math.max(Integer.parseInt(draw[0]), blue);
            }
        }
        return new DrawResult(red, green, blue);
    }

    protected int getPossibleGameId(final String line) {
        final String gameId = line.split(": ")[0].replaceAll("[^\\d+]", "");
        final String allDraws = line.split(": ")[1];
        if (getDrawList(allDraws).stream().anyMatch(this::isImpossibleGame)) {
            return 0;
        }

        return Integer.parseInt(gameId);
    }

    protected List<String[]> getDrawList(final String sets) {
        return Arrays.stream(sets.split("[,;]\\s*")).map(cube -> cube.split(" ")).toList();
    }

    protected boolean isImpossibleGame(final String[] draws) {
        return draws[1].equals("red") && Integer.parseInt(draws[0]) > 12
                || draws[1].equals("green") && Integer.parseInt(draws[0]) > 13
                || draws[1].equals("blue") && Integer.parseInt(draws[0]) > 14;
    }

    protected record DrawResult(int red, int green, int blue) {
    }
}
