package se.jbringb.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 extends Day {

    @Override
    protected String part1() {
        final List<String> steps = Arrays.stream(getInput().get(0).split(",")).toList();
        long sum = steps.stream().mapToLong(this::getHash).sum();
        return String.valueOf(sum);
    }

    @Override
    protected String part2() {
        final List<String> steps = Arrays.stream(getInput().get(0).split(",")).toList();
        final List<Lens> lenses = getLenses(steps);
        Map<Integer, Box> boxes = getBoxes(lenses);
        int sum = boxes.entrySet().stream().mapToInt(box -> {
            int boxSum = 0;
            for (int i = 0; i < box.getValue().slots.size(); i++) {
                boxSum += (box.getKey() + 1) * (i + 1) * box.getValue().slots.get(i).focalLength;
            }
            return boxSum;
        }).sum();
        return String.valueOf(sum);
    }

    private Map<Integer, Box> getBoxes(final List<Lens> lenses) {
        Map<Integer, Box> boxes = new HashMap<>();
        lenses.forEach(lens -> {
            int lensHash = getHash(lens.label);
            boxes.compute(lensHash, (k, v) -> {
                if (v != null) {
                    v.updateLens(lens);
                } else {
                    v = new Box(new ArrayList<>());
                    v.updateLens(lens);
                }
                return v;
            });
        });
        return boxes;
    }

    private List<Lens> getLenses(final List<String> steps) {
        return steps.stream().map(step -> {
            final String label = step.replaceAll("[^a-z]", "");
            return step.contains("=")
                    ? new Lens(label, '+', Integer.parseInt(step.split("=")[1]))
                    : new Lens(label, '-', null);
        }).toList();
    }

    private int getHash(String step) {
        int currentValue = 0;
        for (int c : step.toCharArray()) {
            currentValue += c;
            currentValue *= 17;
            currentValue %= 256;
        }
        return currentValue;
    }

    protected record Box(List<Lens> slots) {
        public void updateLens(final Lens lens) {
            final Lens oldLens = slots.stream().filter(l -> l.label.equals(lens.label)).findFirst().orElse(null);
            if (lens.operation == '+') {
                if (oldLens != null) {
                    slots.set(slots.indexOf(oldLens), lens);
                } else {
                    slots.add(lens);
                }
            } else {
                if (oldLens != null) slots.remove(oldLens);
            }
        }
    }

    protected record Lens(String label, Character operation, Integer focalLength) {
    }
}
