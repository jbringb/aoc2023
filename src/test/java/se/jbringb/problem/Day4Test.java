package se.jbringb.problem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Test {
    final Day4 day4 = new Day4();

    @Test
    @DisplayName("Day 4 - Part 1")
    void part1() {
        assertEquals("21485", day4.part1());
    }

    @Test
    @DisplayName("Day 4 - Part 2")
    void part2() {
        assertEquals("11024379", day4.part2());
    }

    @Test
    void getWins() {
        assertEquals(4, day4.getWins("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"));
        assertEquals(1, day4.getWins("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83"));
        assertEquals(0, day4.getWins("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"));
    }
}
