package se.jbringb.problem;

import se.jbringb.util.SplitUtil;

import java.util.List;
import java.util.stream.IntStream;

public class Day9 extends Day {

    @Override
    protected String part1() {
        final long sum = getInput().stream()
                .map(SplitUtil::splitToLongList)
                .mapToLong(nums -> nums.get(nums.size() - 1) + getExtrapolatedSum(nums, false))
                .sum();
        return String.valueOf(sum);
    }

    @Override
    protected String part2() {
        final long sum = getInput().stream()
                .map(SplitUtil::splitToLongList)
                .mapToLong(nums -> nums.get(0) - getExtrapolatedSum(nums, true))
                .sum();
        return String.valueOf(sum);
    }

    private long getExtrapolatedSum(final List<Long> nums, final boolean backwards) {
        if (nums.stream().allMatch(n -> n == 0L)) {
            return 0L;
        }
        final List<Long> diffs = IntStream.range(1, nums.size()).mapToObj(i -> nums.get(i) - nums.get(i - 1)).toList();
        return backwards
                ? diffs.get(0) - getExtrapolatedSum(diffs, true)
                : diffs.get(diffs.size() - 1) + getExtrapolatedSum(diffs, false);
    }
}
