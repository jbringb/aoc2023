package se.jbringb.problem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day10Test {
    @Test
    @DisplayName("Day 10 - Part 1")
    void part1() {
        Day10 day10 = spy(new Day10());
        doReturn(Arrays.asList(
                "..F7.",
                ".FJ|.",
                "SJ.L7",
                "|F--J",
                "LJ..."
        )).when(day10).getInput();
        assertEquals("8", day10.part1());
    }

    @Test
    @DisplayName("Day 10 - Part 2")
    void part2() {
        Day10 day10 = spy(new Day10());
        doReturn(Arrays.asList(
                "FF7FSF7F7F7F7F7F---7",
                "L|LJ||||||||||||F--J",
                "FL-7LJLJ||||||LJL-77",
                "F--JF--7||LJLJ7F7FJ-",
                "L---JF-JLJ.||-FJLJJ7",
                "|F|F-JF---7F7-L7L|7|",
                "|FFJF7L7F-JF7|JL---7",
                "7-L-JL7||F7|L7F-7F7|",
                "L.L7LFJ|||||FJL7||LJ",
                "L7JLJL-JLJLJL--JLJ.L"
        )).when(day10).getInput();
        assertEquals("10", day10.part2());
    }
}