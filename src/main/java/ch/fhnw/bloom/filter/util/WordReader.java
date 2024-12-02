package ch.fhnw.bloom.filter.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class WordReader {
    /**
     * Reads words from a file
     *
     * @param file File to read words from
     * @return List of words
     */
    public List<String> readWords(File file) {
        List<String> words = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return words;
    }
}
