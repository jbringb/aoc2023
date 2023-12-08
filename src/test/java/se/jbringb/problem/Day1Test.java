package se.jbringb.problem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {
    @Test
    @DisplayName("Day 1 - Part 1")
    void part1() {
        assertEquals("55130", new Day1().part1());
    }

    @Test
    void part2() {
        assertEquals("54985", new Day1().part2());
    }

    @Test
    void getLineSum() {
        assertEquals(22, Day1.getLineIntegers("252342"));
        assertEquals(33, Day1.getLineIntegers("35lal23lala43"));
        assertEquals(0, Day1.getLineIntegers("foobar"));
    }

    @Test
    void parseNumberWords() {
        assertEquals(38, Day1.getLineNumbers("three239eightckt"));
        assertEquals(83, Day1.getLineNumbers("eighthree"));
        assertEquals(76, Day1.getLineNumbers("7pqrstsixteen"));
        assertEquals(83, Day1.getLineNumbers("8gstxqdngxzlxvnvphlsznr3kknftvzxcqqbrfteightthree"));
        assertEquals(44, Day1.getLineNumbers("qp4"));
        assertEquals(13, Day1.getLineNumbers("123"));
        assertEquals(0, Day1.getLineNumbers("foobar"));
    }
}