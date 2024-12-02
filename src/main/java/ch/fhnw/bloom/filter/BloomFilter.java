package ch.fhnw.bloom.filter;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.stream.IntStream;

public class BloomFilter {
    private final BitSet bitSet;
    private final int size;
    private final int numHashFunctions;

    public BloomFilter(int size, int numHashFunctions) {
        this.size = size;
        this.numHashFunctions = numHashFunctions;
        this.bitSet = new BitSet(size);
    }

    /*
     * Hashes a value using multiple hash functions
     * Seed is used to generate different hashes for the same value
     * Seed is the index of the hash function
     *
     * @param value to be hashed
     * @return array of hashes
     */
    private int[] hash(String value) {
        int[] hashes = new int[numHashFunctions];

        // use index as a seed of the hash
        for (int i = 0; i < numHashFunctions; i++) {
            // add the calculate
            hashes[i] = Math.abs(Hashing.murmur3_128(i)
                    .hashString(value, StandardCharsets.UTF_8)
                    .asInt()) % size;
        }

        return hashes;
    }

    /**
     * Checks if the filter contains a word
     *
     * @param string word to be checked
     * @return true if the word is in the filter, false otherwise
     */
    public boolean contains(String string) {
        return IntStream.of(hash(string))
                .allMatch(bitSet::get);
    }

    /**
     * Adds a word to the filter
     *
     * @param value word to be added
     */
    public void add(String value) {
        for (int hash : hash(value)) {
            // set bit to 1, also true when it goes to the same hash position
            bitSet.set(hash);
        }
    }
}
