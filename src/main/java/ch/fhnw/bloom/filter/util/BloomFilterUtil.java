package ch.fhnw.bloom.filter.util;

public class BloomFilterUtil {
    /**
     * Calculates Filter size 'm'
     *
     * @param n Number of elements
     * @param p Error probability
     * @return Calculated 'm' filter size
     */
    public static int calculateM(int n, double p) {
        // https://wikimedia.org/api/rest_v1/media/math/render/svg/5a90ab21c84c30f655ae6b0b9ea78a407738a487
        return (int) Math.ceil((-n * Math.log(p)) / (Math.pow(Math.log(2), 2)));
    }

    /**
     * Calculates optimal number of hash functions 'k'
     *
     * @param m Filter size
     * @param n Count of elements
     * @return Calculated 'k' number of hash functions
     */
    public static int calculateK(int m, int n) {
        // https://wikimedia.org/api/rest_v1/media/math/render/svg/fabc2770225ac59fe42a78f75ea89de650f0130c
        return (int) Math.ceil((m / (double) n) * Math.log(2));
    }

    /**
     * Calculates the probability of false positivity
     *
     * @param m Filter size
     * @param n Count of elements
     * @return Calculated probability of false positivity
     */
    public static double probabilityOfFalsePositivity(int m, int n) {
        // https://en.wikipedia.org/wiki/Bloom_filter#Probability_of_false_positives
        int k = calculateK(m, n);
        return Math.pow(1 - Math.pow((1 - 1 / (double) m), k * n), k);
    }

}
