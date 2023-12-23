package se.jbringb.problem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class Day6Test {
    static Day6 day6;

    @BeforeAll
    static void setup() {
        day6 = spy(new Day6());
        doReturn(Arrays.asList(
                "Time:      7  15   30",
                "Distance:  9  40  200"
        )).when(day6).getInput();
    }

    @Test
    @DisplayName("Day 6 - Part 1")
    void part1() {
        assertEquals("288", day6.part1());
    }

    @Test
    @DisplayName("Day 6 - Part 2")
    void part2() {
        assertEquals("71503", day6.part2());
    }
}
