package com.easypost.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class Files {
    /**
     * Get the directory where the program is currently executing.
     *
     * @return The directory where the program is currently executing
     */
    public static String getSourceFileDirectory() {
        try {
            return Paths.get("").toAbsolutePath().toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Read the contents of a file.
     *
     * @param path The path to the file
     * @return The contents of the file
     */
    public static String readFile(Path path) {
        List<String> data;
        try {
            data = java.nio.file.Files.readAllLines(path);
        } catch (IOException ignored) {
            return null;
        }
        if (data.isEmpty()) {
            return null;
        }
        StringBuilder contents = new StringBuilder();
        for (String line : data) {
            contents.append(line);
        }
        return contents.toString();
    }
}
