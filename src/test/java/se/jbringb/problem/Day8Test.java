package se.jbringb.problem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class Day8Test {
    @Test
    @DisplayName("Day 8 - Part 1")
    void part1() {
        Day8 day8 = spy(new Day8());
        doReturn(Arrays.asList(
                "LLR",
                "",
                "AAA = (BBB, BBB)",
                "BBB = (AAA, ZZZ)",
                "ZZZ = (ZZZ, ZZZ)"
        )).when(day8).getInput();
        assertEquals("6", day8.part1());
    }

    @Test
    @DisplayName("Day 8 - Part 2")
    void part2() {
        Day8 day8 = spy(new Day8());
        doReturn(Arrays.asList(
                "LR",
                "",
                "11A = (11B, XXX)",
                "11B = (XXX, 11Z)",
                "11Z = (11B, XXX)",
                "22A = (22B, XXX)",
                "22B = (22C, 22C)",
                "22C = (22Z, 22Z)",
                "22Z = (22B, 22B)",
                "XXX = (XXX, XXX)"
        )).when(day8).getInput();
        assertEquals("6", day8.part2());
    }
}
