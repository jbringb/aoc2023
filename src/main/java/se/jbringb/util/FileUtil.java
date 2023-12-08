package se.jbringb.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {
    public static List<String> readFileContent(final String fileName) {
        try {
            final Path path = Paths.get("src/main/resources/" + fileName);
            if (!path.toFile().exists()) {
                throw new FileNotFoundException("file not found: %s".formatted(fileName));
            }
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
