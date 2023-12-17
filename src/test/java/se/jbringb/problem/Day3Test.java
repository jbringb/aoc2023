package se.jbringb.problem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day3Test {
    final Day3 day3 = new Day3();

    @Test
    @DisplayName("Day 3 - Part 1")
    void part1() {
        assertEquals("520019", day3.part1());
    }

    @Test
    @DisplayName("Day 3 - Part 2")
    void part2() {
        assertEquals("75519888", day3.part2());
    }

    @Test
    void isAdjacent() {
        assertTrue(day3.isAdjacent(
                new Day3.Symbol(List.of(1, 2, 3), 0, "123", true),
                new Day3.Symbol(List.of(4), 1, "#", false)
        ));
        assertTrue(day3.isAdjacent(
                new Day3.Symbol(List.of(1, 2, 3), 0, "123", true),
                new Day3.Symbol(List.of(4), 0, "#", false)
        ));
        assertTrue(day3.isAdjacent(
                new Day3.Symbol(List.of(1, 2, 3), 0, "123", true),
                new Day3.Symbol(List.of(0), 0, "#", false)
        ));
        assertTrue(day3.isAdjacent(
                new Day3.Symbol(List.of(1, 2, 3), 1, "123", true),
                new Day3.Symbol(List.of(0), 0, "#", false)
        ));
        assertFalse(day3.isAdjacent(
                new Day3.Symbol(List.of(2, 3, 4), 1, "123", true),
                new Day3.Symbol(List.of(0), 0, "#", false)
        ));
    }
}
