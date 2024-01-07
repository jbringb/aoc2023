package se.jbringb.problem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day14Test {
    static Day14 day14;

    @BeforeAll
    static void setup() {
        day14 = spy(new Day14());
        doReturn(List.of(
                "O....#....",
                "O.OO#....#",
                ".....##...",
                "OO.#O....O",
                ".O.....O#.",
                "O.#..O.#.#",
                "..O..#O..O",
                ".......O..",
                "#....###..",
                "#OO..#...."
        )).when(day14).getInput();

    }

    @Test
    @DisplayName("Day 14 - Part 1")
    void part1() {
        assertEquals("136", day14.part1());
    }

    @Test
    @DisplayName("Day 14 - Part 2")
    void part2() {
        assertEquals("64", day14.part2());
    }
}