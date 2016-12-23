package com.goeuro.data;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class RoutesReader {
    private final Path dataSource;

    private final Pattern splitPattern = Pattern.compile("\\W");

    public RoutesReader(String filePath) {
        dataSource = Paths.get(filePath);
        if (Files.notExists(dataSource)) {
            throw new IllegalStateException("Data file doesn't exist: " + filePath);
        }
    }

    public Collection<int[]> loadRoutesData() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(dataSource)) {
            int numberOfRoutes = Integer.valueOf(reader.readLine());
            return reader.lines().limit(numberOfRoutes).map(row ->
                    splitPattern.splitAsStream(row).filter(StringUtils::hasText).mapToInt(Integer::valueOf).toArray()
            ).collect(Collectors.toList());
        }
    }
}
