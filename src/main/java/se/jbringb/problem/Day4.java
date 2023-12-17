package se.jbringb.problem;

import java.util.Arrays;
import java.util.List;

public class Day4 extends Day {
    private final List<String> input = getInput();

    @Override
    protected String part1() {
        long sum = input.stream()
                .map(this::getWins)
                .mapToLong(wins -> wins != 0 ? (long) Math.pow(2, wins - 1) : 0)
                .sum();
        return String.valueOf(sum);
    }

    @Override
    protected String part2() {
        int[] cardCopies = new int[input.size()];
        Arrays.fill(cardCopies, 1);
        int cardCount = 0;
        for (int i = 0; i <= input.size() - 1; i++) {
            for (int j = i + 1; j <= i + getWins(input.get(i)); j++) {
                cardCopies[j] += cardCopies[i];
            }
            cardCount += cardCopies[i];
        }
        return String.valueOf(cardCount);
    }

    protected long getWins(final String line) {
        final List<Integer> winNumbers = Arrays.stream(parseNumbers(line, 0)).map(Integer::parseInt).toList();
        final List<Integer> cardNumbers = Arrays.stream(parseNumbers(line, 1)).map(Integer::parseInt).toList();
        return winNumbers.stream().filter(cardNumbers::contains).count();
    }

    private static String[] parseNumbers(final String line, int index) {
        return line.split(":\\s+")[1].split("\\s+\\|\\s+")[index].split("\\s+");
    }
}
