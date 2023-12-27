package se.jbringb.problem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class Day9Test {
    static Day9 day9;

    @BeforeAll
    static void setup() {
        day9 = spy(new Day9());
        doReturn(List.of("10 13 16 21 30 45")).when(day9).getInput();
    }

    @Test
    @DisplayName("Day 9 - Part 1")
    void part1() {
        assertEquals("68", day9.part1());
    }

    @Test
    @DisplayName("Day 9 - Part 2")
    void part2() {
        assertEquals("5", day9.part2());
    }
}
