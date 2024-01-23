package se.jbringb.problem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day16Test {
    static Day16 day16;

    @BeforeAll
    static void setup() {
        day16 = spy(new Day16());
        doReturn(Arrays.asList(".|...\\....",
                "|.-.\\.....",
                ".....|-...",
                "........|.",
                "..........",
                ".........\\",
                "..../.\\\\..",
                ".-.-/..|..",
                ".|....-|.\\",
                "..//.|....")).when(day16).getInput();
    }

    @Test
    @DisplayName("Day 16 - Part 1")
    void part1() {
        assertEquals("46", day16.part1());
    }

    @Test
    @DisplayName("Day 16 - Part 2")
    void part2() {
        assertEquals("51", day16.part2());
    }
}