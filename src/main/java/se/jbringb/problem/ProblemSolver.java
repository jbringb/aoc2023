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
                new DayRecord(4, "Scratchcards", new Day4()),
                new DayRecord(5, "If You Give A Seed A Fertilizer", new Day5()),
                new DayRecord(6, "Wait For It", new Day6()),
                new DayRecord(7, "Camel Cards", new Day7()),
                new DayRecord(8, "Haunted Wasteland", new Day8()),
                new DayRecord(9, "Mirage Maintenance", new Day9()),
                new DayRecord(10, "Pipe Maze", new Day10()),
                new DayRecord(11, "Cosmic Expansion", new Day11()),
                new DayRecord(14, "Parabolic Reflector Dish", new Day14()),
                new DayRecord(15, "Lens Library", new Day15()),
                new DayRecord(16, "The Floor Will Be Lava", new Day16()),
                new DayRecord(17, "Clumsy Crucible", new Day17()),
                new DayRecord(18, "Lavaduct Lagoon", new Day18())
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
