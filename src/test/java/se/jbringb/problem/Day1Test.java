package se.jbringb.problem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {
    final Day1 day1 = new Day1();

    @Test
    @DisplayName("Day 1 - Part 1")
    void part1() {
        assertEquals("55130", day1.part1());
    }

    @Test
    @DisplayName("Day 1 - Part 2")
    void part2() {
        assertEquals("54985", day1.part2());
    }

    @Test
    void getLineSum() {
        assertEquals(22, day1.getLineIntegers("252342"));
        assertEquals(33, day1.getLineIntegers("35lal23lala43"));
        assertEquals(0, day1.getLineIntegers("foobar"));
    }

    @Test
    void parseNumberWords() {
        assertEquals(38, day1.getLineNumbers("three239eightckt"));
        assertEquals(83, day1.getLineNumbers("eighthree"));
        assertEquals(76, day1.getLineNumbers("7pqrstsixteen"));
        assertEquals(83, day1.getLineNumbers("8gstxqdngxzlxvnvphlsznr3kknftvzxcqqbrfteightthree"));
        assertEquals(44, day1.getLineNumbers("qp4"));
        assertEquals(13, day1.getLineNumbers("123"));
        assertEquals(0, day1.getLineNumbers("foobar"));
    }
}