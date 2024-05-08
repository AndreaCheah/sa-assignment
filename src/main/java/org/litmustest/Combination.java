package org.litmustest;

public class Combination {
    public static long CalculateCombination(int N, int K) {
        return CalculateCombination(N, K, 1);
    }

    public static long CalculateCombination(int N, int K, int T) {
        if (K == 0) {
            throw new IllegalArgumentException("K cannot be zero");
        }
        if (N > 150) {
            throw new IllegalArgumentException("N cannot be larger than 150");
        }
        if (K > N) {
            throw new IllegalArgumentException("K should be less than or equal to N");
        }
        if (K == N) {
            return 1;
        }
        int totalCapacity = K * T;
        if (totalCapacity >= N) {
            return 1;
        }

        return calculateCombination(N, totalCapacity);
    }

    private static long calculateCombination(int N, int K) {
        long numerator = 1;
        long denominator = 1;

        for (int i = 0; i < K; i++) {
            numerator *= (N - i);   // N * (N - 1) * ... * (N - K + 1)
            denominator *= (i + 1); // K!
        }
        return numerator / denominator;
    }

    public static long CalculateDifference(int N, int K1, int K2) {
        long combo1 = CalculateCombination(N, K1);
        long combo2 = CalculateCombination(N, K2);
        return Math.abs(combo1 - combo2);
    }

    public static void benchmarkCalculateCombination() {
        int N = 100;
        int K = 50;
        int T = 2;
        long startTime = System.nanoTime();
        long combinations = CalculateCombination(N, K, T);
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);
        System.out.printf("Benchmark: Number of combinations when the AGV picks %d boxes out of %d boxes in %d trips: %d (Time: %d ms)\n",
                K, N, T, combinations, elapsedTime);
    }

    public static void benchmarkCalculateDifference() {
        int N = 100;
        int K1 = 50;
        int K2 = 40;
        long startTime = System.nanoTime();
        long difference = CalculateDifference(N, K1, K2);
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);
        System.out.printf("Benchmark: Difference between combinations when the AGV picks %d and %d boxes out of %d boxes: %d (Time: %d ms)\n",
                K1, K2, N, difference, elapsedTime);
    }
}
