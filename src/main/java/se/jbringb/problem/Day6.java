package se.jbringb.problem;

import se.jbringb.util.SplitUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day6 extends Day {

    @Override
    protected String part1() {
        final List<Race> races = getRaces(getInput(), false);
        final List<Long> winCounts = getWinCounts(races);
        final long sum = getSum(winCounts);
        return String.valueOf(sum);
    }

    private static long getSum(List<Long> winCounts) {
        final long sum = winCounts.stream().reduce(1L, (a, b) -> a * b);
        return sum;
    }

    @Override
    protected String part2() {
        final List<Race> races = getRaces(getInput(), true);
        final List<Long> winCounts = getWinCounts(races);
        assert winCounts.size() == 1;
        return String.valueOf(winCounts.get(0));
    }

    private static List<Long> getWinCounts(List<Race> races) {
        final List<Long> winCounts = new ArrayList<>(Stream.generate(() -> 0L).limit(races.size()).toList());
        races.forEach(race -> {
            int raceIndex = races.indexOf(race);
            for (int i = 1; i < race.time(); i++) {
                long raceDistance = (race.time - i) * i;
                if (raceDistance > race.distance) {
                    winCounts.set(raceIndex, winCounts.get(raceIndex) + 1);
                }
            }
        });
        return winCounts;
    }

    private List<Race> getRaces(List<String> input, final boolean fixKerning) {
        if (fixKerning) {
            input = input.stream().map(p -> p.replaceAll("(?<=\\d)\\s+(?=\\d)", "")).toList();
        }
        final List<Long> times = SplitUtil.splitToLongList(input.get(0).replaceAll("Time:\\s+", ""));
        final List<Long> distances = SplitUtil.splitToLongList(input.get(1).replaceAll("Distance:\\s+", ""));
        assert times.size() == distances.size();
        return IntStream.range(0, times.size())
                .mapToObj(i -> new Race(times.get(i), distances.get(i)))
                .collect(Collectors.toList());
    }

    protected record Race(long time, long distance) {
    }
}
