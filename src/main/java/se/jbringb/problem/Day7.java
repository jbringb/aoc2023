package se.jbringb.problem;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 extends Day {
    private static final List<Character> cardRankJokerLess = Arrays.asList(
            '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'
    );

    private static final List<Character> cardRankWithJokers = Arrays.asList(
            'J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'
    );

    @Override
    protected String part1() {
        return String.valueOf(getSum(getHands(getInput(), false)));
    }

    @Override
    protected String part2() {
        return String.valueOf(getSum(getHands(getInput(), true)));
    }

    protected long getSum(final List<Hand> hands) {
        return hands.stream().mapToLong(p -> {
            int ix = (hands.indexOf(p) + 1);
            return (ix * p.bid);
        }).sum();
    }

    protected List<Hand> getHands(final List<String> input, final boolean allowJokers) {
        return input.stream().map(str -> {
            final String hand = str.split("\\s")[0];
            final int bid = Integer.parseInt(str.split("\\s")[1]);
            final Map<Character, Integer> cardCounts = getCardCounts(hand, allowJokers);
            return new Hand(hand, bid, getHandType(cardCounts), allowJokers);
        }).sorted().toList();
    }

    private static Map<Character, Integer> getCardCounts(String hand, boolean allowJokers) {
        final Map<Character, Integer> cardCounts = new HashMap<>();
        for (final Character c : hand.toCharArray()) {
            cardCounts.putIfAbsent(c, 0);
            cardCounts.put(c, cardCounts.get(c) + 1);
        }
        if (allowJokers && !hand.equals("JJJJJ")) {
            final int jokers = Math.toIntExact(hand.chars().filter(p -> p == 'J').count());
            cardCounts.replaceAll((k, v) -> v + jokers);
            cardCounts.remove('J');
        }
        return cardCounts;
    }

    private Hand.HandType getHandType(Map<Character, Integer> cardCounts) {
        if (cardCounts.keySet().size() == 1) {
            return Hand.HandType.FIVE_OF_A_KIND;
        } else if (cardCounts.keySet().size() == 2) {
            if (cardCounts.values().stream().anyMatch(p -> p == 4)) return Hand.HandType.FOUR_OF_A_KIND;
            else return Hand.HandType.FULL_HOUSE;
        } else if (cardCounts.keySet().size() == 3) {
            if (cardCounts.values().stream().anyMatch(p -> p == 3)) return Hand.HandType.THREE_OF_A_KIND;
            else return Hand.HandType.TWO_PAIR;
        } else if (cardCounts.keySet().size() == 4) {
            return Hand.HandType.ONE_PAIR;
        } else {
            return Hand.HandType.HIGH_CARD;
        }
    }

    private int compareCardRank(char card1, char card2, final boolean allowJokers) {
        final List<Character> cardRank = allowJokers ? cardRankWithJokers : cardRankJokerLess;
        return cardRank.indexOf(card1) - cardRank.indexOf(card2);
    }

    @Data
    @AllArgsConstructor
    protected class Hand implements Comparable<Hand> {
        private final String hand;
        private final long bid;
        private HandType type;
        private boolean allowJokers;

        @Override
        public int compareTo(final Hand other) {
            int compareHand = this.getType().compareTo(other.getType());
            if (compareHand != 0) return compareHand;
            for (int i = 0; i <= 4; i++) {
                int compareCard = compareCardRank(this.getHand().charAt(i), other.getHand().charAt(i), allowJokers);
                if (compareCard != 0) return compareCard;
            }
            return 0;
        }

        private enum HandType {
            HIGH_CARD,
            ONE_PAIR,
            TWO_PAIR,
            THREE_OF_A_KIND,
            FULL_HOUSE,
            FOUR_OF_A_KIND,
            FIVE_OF_A_KIND
        }
    }
}
