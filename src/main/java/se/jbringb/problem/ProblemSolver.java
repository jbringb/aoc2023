package se.jbringb.problem;

import java.util.List;

public class ProblemSolver {
    public void solve(final int day) {
        final List<DayRecord> days = getDays();
        final DayRecord dayRecord = days.stream()
                .filter(p -> p.number() == day)
                .findFirst()
                .orElse(null);

        if (dayRecord == null) {
            System.out.printf("Problem for day %d does not exist (yet...)!%n", day);
            return;
        }

        dayRecord.showSolution();
    }

    private List<DayRecord> getDays() {
        return List.of(
                new DayRecord(1, "Trebuchet?!", new Day1()),
                new DayRecord(2, "Cube Conundrum", new Day2()),
                new DayRecord(3, "Gear Ratios", new Day3()),
                new DayRecord(4, "Scratchcards", new Day4())
        );
    }

    private record DayRecord(int number, String name, Day day) {
        private void showSolution() {
            System.out.printf("Day %d: %s!%n", number, name);
            System.out.println("-".repeat(80));
            System.out.printf("Part 1: %s%n", day().part1());
            System.out.printf("Part 2: %s%n", day().part2());
        }
    }
}
