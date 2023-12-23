package se.jbringb.problem;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Test {
    public static final List<String> SAMPLE_INPUT = Arrays.asList(
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483"
    );
    final Day7 day7 = new Day7();

    @Test
    void getSum_WithoutJokers() {
        assertEquals(6440, day7.getSum(day7.getHands(SAMPLE_INPUT, false)));
    }

    @Test
    void getSum_WithJokers() {
        assertEquals(5905, day7.getSum(day7.getHands(SAMPLE_INPUT, true)));
    }
}
