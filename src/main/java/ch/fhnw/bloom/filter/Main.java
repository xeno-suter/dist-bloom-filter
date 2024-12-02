package ch.fhnw.bloom.filter;

import ch.fhnw.bloom.filter.util.BloomFilterUtil;
import ch.fhnw.bloom.filter.util.WordReader;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // input p
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie die Wahrscheinlichkeit ]0..1] an:");
        double p = scanner.nextDouble();

        while (0 == p) {
            System.out.println("Die Wahrscheinlichkeit muss grösser als 0 sein! Bitte genau lesen...");
            System.out.println("Bitte geben Sie die Wahrscheinlichkeit ]0..1] an:");
            p = scanner.nextDouble();
        }

        // read words
        WordReader wordReader = new WordReader();
        List<String> words = wordReader.readWords(new File("src/main/resources/words.txt"));

        // initialize n, m, k
        int n = words.size();
        int m = BloomFilterUtil.calculateM(n, p);
        int k = BloomFilterUtil.calculateK(m, n);

        // print out calculated values
        System.out.println("p: " + p);
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        System.out.println("k: " + k);

        // add words to filter "to train"
        BloomFilter bloomFilter = new BloomFilter(m, k);
        words.stream().forEach(bloomFilter::add);

        // Test probability with test words
        int testSize = 100_000;
        int falsePositives = 0;
        Random random = new Random();

        for (int i = 0; i < testSize; i++) {
            String testWord = "test_" + random.nextInt();
            if (bloomFilter.contains(testWord)) {
                falsePositives++;
            }
        }

        // print experimented p
        double experimentalP = (double) falsePositives / testSize;
        System.out.println("Experimentelle (generierte Testwörter) Fehlerwahrscheinlichkeit: " + experimentalP);
        System.out.println("Berechnete Fehlerwahrscheinlichkeit: " + BloomFilterUtil.probabilityOfFalsePositivity(m, n));
    }
}
