package se.jbringb.problem;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day3 extends Day {
    @Override
    protected String part1() {
        final Set<Symbol> symbols = getSymbols(getInput());
        int sum = symbols.stream()
                .filter(Symbol::isNumeric)
                .mapToInt(number -> getAdjacentNumber(number, symbols))
                .filter(adjacentNumber -> adjacentNumber != -1)
                .sum();
        return String.valueOf(sum);
    }

    @Override
    protected String part2() {
        final Set<Symbol> symbols = getSymbols(getInput());
        int sum = 0;
        for (final Symbol gear : symbols) {
            List<Integer> allAdjacentNumbers = new ArrayList<>();
            if (!gear.isNumeric && gear.s.equals("*")) {
                final List<Integer> adjacentNumbers = symbols.stream()
                        .filter(number -> number.isNumeric && isAdjacent(number, gear))
                        .map(number -> Integer.valueOf(number.s))
                        .toList(); // adjacent number(s) to a gear.
                if (adjacentNumbers.size() > 1) allAdjacentNumbers.addAll(adjacentNumbers);
            }
            sum += allAdjacentNumbers.stream().reduce((a, b) -> a * b).orElse(0);
        }
        return String.valueOf(sum);
    }

    protected int getAdjacentNumber(Symbol number, Set<Symbol> symbols) {
        if (symbols.stream().filter(s -> !s.isNumeric).anyMatch(s -> isAdjacent(number, s))) {
            return Integer.parseInt(number.s);
        }
        return -1;
    }

    protected boolean isAdjacent(final Symbol number, final Symbol symbol) {
        int diffY = Math.abs(number.y - symbol.y);
        for (int x : number.xRange) {
            int diffX = Math.abs(x - symbol.xRange.get(0));
            if (diffX <= 1 && diffY <= 1) return true;
        }
        return false;
    }

    protected Set<Symbol> getSymbols(List<String> input) {
        final Set<Symbol> symbols = new LinkedHashSet<>();
        for (int y = 0; y < input.size(); y++) {
            final String line = input.get(y);
            Matcher matcher = Pattern.compile("(\\d+)|([^0-9.])").matcher(line);
            while (matcher.find()) {
                final String group = matcher.group();
                final List<Integer> xRange = IntStream.range(matcher.start(), matcher.end()).boxed().toList();
                symbols.add(new Symbol(xRange, y, group, group.matches("\\d+")));
            }
        }
        return symbols;
    }

    protected record Symbol(List<Integer> xRange, int y, String s, boolean isNumeric) {
    }
}
