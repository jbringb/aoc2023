package se.jbringb.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 extends Day {
    @Override
    public String part1() {
        return String.valueOf(getInput().stream().mapToInt(this::getLineIntegers).sum());
    }

    @Override
    public String part2() {
        return String.valueOf(getInput().stream().mapToInt(this::getLineNumbers).sum());
    }

    protected int getLineNumbers(final String line) {
        final String regex = "(?=(\\d|one|two|three|four|five|six|seven|eight|nine))";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(line);
        List<String> matches = new ArrayList<>();
        while (match.find()) {
            matches.add(match.group(1));
        }
        return matches.isEmpty() ? 0 : getFormattedNumbers(
                getNumber(matches.get(0)),
                getNumber(matches.get(matches.size() - 1))
        );
    }

    protected char getNumber(final String number) {
        if (number.matches("[1-9]")) return number.toCharArray()[0];
        return switch (number) {
            case "one" -> '1';
            case "two" -> '2';
            case "three" -> '3';
            case "four" -> '4';
            case "five" -> '5';
            case "six" -> '6';
            case "seven" -> '7';
            case "eight" -> '8';
            case "nine" -> '9';
            default -> throw new IllegalStateException("Unexpected value: " + number);
        };
    }

    protected int getLineIntegers(final String line) {
        String str = line.replaceAll("[^1-9]", "");
        if (str.isEmpty()) return 0;
        return getFormattedNumbers(str.charAt(0), str.charAt(str.length() - 1));
    }

    protected int getFormattedNumbers(final char first, final char last) {
        String numbers = String.format("%s%s", first, last);
        return Integer.parseInt(numbers);
    }
}
