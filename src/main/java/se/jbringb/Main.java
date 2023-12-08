package se.jbringb;

import se.jbringb.problem.ProblemSolver;

public class Main {
    public static void main(String[] args) {
        if (validateArgs(args)) {
            new ProblemSolver().solve(Integer.parseInt(args[0]));
        }
    }

    private static boolean validateArgs(String[] args) {
        if (args.length != 1 || !args[0].matches("\\d+")) {
            System.out.println("Usage: aoc2023 (day)");
            return false;
        }
        return true;
    }
}
