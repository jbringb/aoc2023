package se.jbringb.problem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {
    final Day2 day2 = new Day2();

    @Test
    @DisplayName("Day 2 - Part 1")
    void part1() {
        assertEquals("2439", day2.part1());
    }

    @Test
    @DisplayName("Day 2 - Part 2")
    void part2() {
        assertEquals("63711", day2.part2());
    }

    @Test
    void getPossibleGameId() {
        assertEquals(0, day2.getPossibleGameId("Game 1: 10 red, 20 blue"));
        assertEquals(1, day2.getPossibleGameId("Game 1: 1 red, 1 blue"));
    }

    @Test
    void getDrawList() {
        List<String[]> drawList = day2.getDrawList("10 red,20 blue,30 green");
        assertEquals(3, drawList.size());
        assertAll(
                () -> assertEquals("10", drawList.get(0)[0]),
                () -> assertEquals("red", drawList.get(0)[1]),
                () -> assertEquals("20", drawList.get(1)[0]),
                () -> assertEquals("blue", drawList.get(1)[1]),
                () -> assertEquals("30", drawList.get(2)[0]),
                () -> assertEquals("green", drawList.get(2)[1])
        );
    }
}
