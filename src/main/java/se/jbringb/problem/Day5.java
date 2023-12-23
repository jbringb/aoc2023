package se.jbringb.problem;

import java.util.ArrayList;
import java.util.List;

import static se.jbringb.util.SplitUtil.splitToLongList;

public class Day5 extends Day {

    @Override
    protected String part1() {
        return String.valueOf(findLowestLocation(getInput(), false));
    }

    @Override
    protected String part2() {
        return String.valueOf(findLowestLocation(getInput(), true));
    }

    private Long findLowestLocation(final List<String> input, final boolean useRange) {
        final List<Long> seeds = splitToLongList(input.get(0).replaceAll("seeds: ", ""));
        final List<Map> maps = createMaps(input.subList(2, input.size()));
        if (useRange) {
            final List<Long> finalLocations = new ArrayList<>();
            for (int i = 0; i < seeds.size(); i += 2) {
                final long fromSeed = seeds.get(i);
                final long toSeed = fromSeed + seeds.get(i + 1);
                long lowestMatch = Long.MAX_VALUE;
                for (long j = fromSeed; j <= toSeed; j++) {
                    long lastMatch = getLastMatch(j, maps);
                    if (lastMatch < lowestMatch) lowestMatch = lastMatch;
                }
                finalLocations.add(lowestMatch);
            }
            return finalLocations.stream()
                    .min(Long::compare)
                    .orElse(0L);
        } else {
            return seeds.stream().mapToLong(seed -> getLastMatch(seed, maps))
                    .boxed().toList()
                    .stream()
                    .min(Long::compare)
                    .orElse(0L);
        }
    }

    private static long getLastMatch(final Long seed, final List<Map> maps) {
        long lastMatch = seed;
        for (Map map : maps) {
            for (int i = 0; i < map.sources.size(); i++) {
                final long minSource = map.sources.get(i);
                final long minDest = map.destinations.get(i);
                final long range = map.ranges.get(i);
                final long maxSource = minSource + range;
                if (lastMatch >= minSource && lastMatch < maxSource) {
                    lastMatch = lastMatch - minSource + minDest;
                    break;
                }
            }
        }
        return lastMatch;
    }

    private List<Map> createMaps(final List<String> input) {
        final List<Map> maps = new ArrayList<>();
        Map currentMap = Map.createMap();
        for (final String line : input) {
            if (line.isBlank()) {
                maps.add(currentMap);
                currentMap = Map.createMap();
            }
            if (line.matches("^[\\d\\s]+$")) {
                currentMap.addLocations(splitToLongList(line));
            }
        }
        maps.add(currentMap);
        return maps;
    }

    protected record Map(List<Long> destinations, List<Long> sources, List<Long> ranges) {
        static Map createMap() {
            return new Map(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }

        public void addLocations(List<Long> locations) {
            destinations.add(locations.get(0));
            sources.add(locations.get(1));
            ranges.add(locations.get(2));
        }
    }
}
