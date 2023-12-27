package se.jbringb.problem;

import lombok.AllArgsConstructor;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day8 extends Day {
    @Override
    protected String part1() {
        final List<String> input = getInput();
        final Set<Node> networkNodes = getNetworkNodes(input);
        final NodeSearchContext ctx = new NodeSearchContext(networkNodes, input.get(0).toCharArray(), "ZZZ");
        final Set<Node> startNodes = ctx.findNodes("AAA");
        long steps = startNodeSearch(ctx, startNodes);
        return String.valueOf(steps);
    }

    @Override
    protected String part2() {
        final List<String> input = getInput();
        final Set<Node> networkNodes = getNetworkNodes(input);
        final NodeSearchContext ctx = new NodeSearchContext(networkNodes, input.get(0).toCharArray(), ".{2}Z");
        final Set<Node> startNodes = ctx.findNodes(".{2}A");
        long steps = startNodeSearch(ctx, startNodes);
        return String.valueOf(steps);
    }

    private long startNodeSearch(final NodeSearchContext ctx, final Set<Node> startNodes) {
        final CountDownLatch latch = new CountDownLatch(startNodes.size());
        final ExecutorService executorService = Executors.newFixedThreadPool(startNodes.size());
        final List<NodeSearcher> nodeSearchers = new ArrayList<>();

        startNodes.stream()
                .map(startNode -> new NodeSearcher(ctx, startNode, latch, 0))
                .forEach(nodeSearcher -> {
                    nodeSearchers.add(nodeSearcher);
                    executorService.execute(nodeSearcher);
                });

        try {
            latch.await();
            executorService.shutdown();
            return nodeSearchers.stream().map(n -> (long) n.steps).collect(Collectors.toSet())
                    .stream()
                    .reduce(1L, ArithmeticUtils::lcm);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Set<Node> getNetworkNodes(final List<String> input) {
        final Set<Node> nodes = new HashSet<>();
        input.subList(2, input.size()).forEach(line -> {
            final Matcher matcher = Pattern.compile("(.{3}) = \\((.{3}),\\s(.{3})\\)").matcher(line);
            while (matcher.find()) {
                nodes.add(new Node(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        });
        return nodes;
    }

    protected record Node(String id, String leftDirection, String rightDirection) {
    }

    protected record NodeSearchContext(Set<Node> networkNodes, char[] directions, String searchPattern) {
        private Set<Node> findNodes(final String pattern) {
            return networkNodes.stream().filter(n -> n.id.matches(pattern)).collect(Collectors.toSet());
        }

        private Node findNode(final String pattern) {
            Node node = networkNodes.stream().filter(n -> n.id.matches(pattern)).findFirst().orElse(null);
            if (node == null) {
                System.out.println(".");
            }
            return node;
        }
    }

    @AllArgsConstructor
    protected class NodeSearcher implements Runnable {
        private final NodeSearchContext ctx;
        private final Node startNode;
        private final CountDownLatch latch;
        private int steps;

        @Override
        public void run() {
            steps = findNode(startNode, 0);
            latch.countDown();
        }

        private int findNode(Node currentNode, int steps) {
            for (final Character direction : ctx.directions) {
                if (currentNode.id.matches(ctx.searchPattern)) break;
                final String nextNodeId = direction == 'L' ? currentNode.leftDirection : currentNode.rightDirection;
                currentNode = ctx.findNode(nextNodeId);
                steps++;
            }
            return !currentNode.id.matches(ctx.searchPattern) ? findNode(currentNode, steps) : steps;
        }
    }
}
