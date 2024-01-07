package se.jbringb.problem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day15Test {
    static Day15 day15;

    @BeforeAll
    static void setup() {
        day15 = spy(new Day15());
        doReturn(List.of("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7")).when(day15).getInput();
    }

    @Test
    @DisplayName("Day 15 - Part 1")
    void part1() {
        assertEquals("1320", day15.part1());
    }

    @Test
    @DisplayName("Day 15 - Part 2")
    void part2() {
        assertEquals("145", day15.part2());
    }
}