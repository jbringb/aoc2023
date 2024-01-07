package se.jbringb.problem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day11Test {
    static Day11 day11;

    @BeforeAll
    static void setup() {
        day11 = spy(new Day11());
        doReturn(Arrays.asList(
                "...#......",
                ".......#..",
                "#.........",
                "..........",
                "......#...",
                ".#........",
                ".........#",
                "..........",
                ".......#..",
                "#...#....."
        )).when(day11).getInput();

    }

    @Test
    @DisplayName("Day 11 - Part 1")
    void part1() {
        assertEquals("374", day11.part1());
    }

    @Test
    @DisplayName("Day 11 - Part 2")
    void part2() {
        assertEquals("82000210", day11.part2());
    }
}