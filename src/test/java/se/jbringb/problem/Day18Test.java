package se.jbringb.problem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day18Test {
    static Day18 day18;

    @BeforeAll
    static void setup() {
        day18 = spy(new Day18());
        doReturn(Arrays.asList(
                "R 6 (#70c710)",
                "D 5 (#0dc571)",
                "L 2 (#5713f0)",
                "D 2 (#d2c081)",
                "R 2 (#59c680)",
                "D 2 (#411b91)",
                "L 5 (#8ceee2)",
                "U 2 (#caa173)",
                "L 1 (#1b58a2)",
                "U 2 (#caa171)",
                "R 2 (#7807d2)",
                "U 3 (#a77fa3)",
                "L 2 (#015232)",
                "U 2 (#7a21e3)"
        )).when(day18).getInput();
    }

    @Test
    @DisplayName("Day 18 - Part 1")
    void part1() {
        assertEquals("62", day18.part1());
    }

    @Test
    @DisplayName("Day 18 - Part 2")
    void part2() {
        assertEquals("952408144115",  new Day18().part2());
    }
}