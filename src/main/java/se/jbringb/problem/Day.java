package se.jbringb.problem;

import se.jbringb.util.FileUtil;

import java.util.List;

abstract class Day {
    protected abstract String part1();

    protected abstract String part2();

    protected List<String> getInput() {
        return FileUtil.readFileContent("%s.txt".formatted(this.getClass().getSimpleName()));
    }
}
