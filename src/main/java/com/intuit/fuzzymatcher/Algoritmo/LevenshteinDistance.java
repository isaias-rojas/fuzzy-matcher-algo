package com.intuit.fuzzymatcher.Algoritmo;

/**
 * Implements the calculation of the Levenshtein distance to measure the difference between two sequences of text.
 * The Levenshtein distance is the minimum number of simple edits (which can be insertions, deletions, or substitutions of a single character)
 * required to change one word into another.
 */
public class LevenshteinDistance {

    /**
     * Calculates the Levenshtein distance between two text strings.
     *
     * @param str1 The first text string.
     * @param str2 The second text string.
     * @return The Levenshtein distance between the two text strings. It's an integer value representing
     * the minimum number of edit operations required to transform one string into the other.
     */
    public static int computeDistance(String str1, String str2) {
        int[][] distanceMatrix = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            distanceMatrix[i][0] = i;
        }
        for (int j = 0; j <= str2.length(); j++) {
            distanceMatrix[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;

                distanceMatrix[i][j] = Math.min(
                        Math.min(distanceMatrix[i - 1][j] + 1, distanceMatrix[i][j - 1] + 1),
                        distanceMatrix[i - 1][j - 1] + cost
                );
            }
        }

        return distanceMatrix[str1.length()][str2.length()];
    }

    /**
     * Compares two words and calculates their similarity based on the Levenshtein distance.
     * The similarity is calculated as the complement of the distance with respect to the length of the longer word,
     * normalized in the range from 0 to 1, where 1 indicates exactly the same string, and 0 indicates completely different strings.
     *
     * @param wordOne The first word to compare.
     * @param wordTwo The second word to compare.
     * @return A Double value representing the similarity between the two words. A higher value indicates greater similarity.
     */
    public Double compare(String wordOne, String wordTwo) {
        int distance = computeDistance(wordOne, wordTwo);
        int maxLength = Math.max(wordOne.length(), wordTwo.length());
        if (maxLength == 0) {
            return 1.0;
        }
        double similarity = (double) (maxLength - distance) / maxLength;

        return similarity;
    }
}
