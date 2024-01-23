package se.jbringb.problem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day17Test {
    static Day17 day17;

    @BeforeAll
    static void setup() {
        day17 = spy(new Day17());
        doReturn(Arrays.asList(
                "2413432311323",
                "3215453535623",
                "3255245654254",
                "3446585845452",
                "4546657867536",
                "1438598798454",
                "4457876987766",
                "3637877979653",
                "4654967986887",
                "4564679986453",
                "1224686865563",
                "2546548887735",
                "4322674655533"
        )).when(day17).getInput();
    }

    @Test
    @DisplayName("Day 17 - Part 1")
    void part1() {
        assertEquals("102", day17.part1());
    }

    @Test
    @DisplayName("Day 17 - Part 2")
    void part2() {
        assertEquals("94", day17.part2());
    }
}