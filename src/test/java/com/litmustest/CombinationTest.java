package com.litmustest;

import org.junit.jupiter.api.Test;
import org.litmustest.Combination;

import static org.junit.jupiter.api.Assertions.*;

public class CombinationTest {
    @Test
    void testCalculateCombination_KLessThanN() {
        int N = 10;
        int K = 3;
        long expected = 120; // 10!/(3!(10-3)!) = 120
        long result = Combination.CalculateCombination(N, K);
        assertEquals(expected, result);
    }

    @Test
    void testCalculateCombination_NGreaterThan150() {
        int N = 151;
        int K = 5;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Combination.CalculateCombination(N, K)
        );
        assertEquals("N cannot be larger than 150", exception.getMessage());
    }

    @Test
    void testCalculateCombination_KEqualsZero() {
        int N = 10;
        int K = 0;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Combination.CalculateCombination(N, K)
        );
        assertEquals("K cannot be zero", exception.getMessage());
    }

    @Test
    void testCalculateCombination_KEqualsN() {
        int N = 10;
        int K = 10;
        long expected = 1; // 10!/(10!(10-10)!) = 1
        long result = Combination.CalculateCombination(N, K);
        assertEquals(expected, result);
    }

    @Test
    void testCalculateCombination_KGreaterThanN() {
        int N = 10;
        int K = 20;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Combination.CalculateCombination(N, K)
        );
        assertEquals("K should be less than or equal to N", exception.getMessage());
    }

    @Test
    void testCalculateCombination_WithTrips() {
        int N = 10;
        int K = 3;
        int T = 2;
        long expected = 210; // Calculating for K=3 and T=2 trips
        long result = Combination.CalculateCombination(N, K, T);
        System.out.printf("DEBUG: Calculated result for N = %d, K = %d, T = %d: %d%n", N, K, T, result);
        assertEquals(expected, result);
    }

    @Test
    void testCalculateDifference_K1GreaterThanK2() {
        int N = 10;
        int K1 = 3;
        int K2 = 2;
        long expectedK1 = 120; // 10!/(3!(10-3)!) = 120
        long expectedK2 = 45; // 10!/(2!(10-2)!) = 45
        long expected = Math.abs(expectedK1 - expectedK2); // |120 - 45| = 75
        long result = Combination.CalculateDifference(N, K1, K2);
        assertEquals(expected, result);
    }

    @Test
    void testCalculateDifference_K1EqualsK2() {
        int N = 10;
        int K1 = 3;
        int K2 = 3;
        long expected = 0; // |Combinations(10, 3) - Combinations(10, 3)| = 0
        long result = Combination.CalculateDifference(N, K1, K2);
        assertEquals(expected, result);
    }

    @Test
    void testCalculateDifference_K1LessThanK2() {
        int N = 10;
        int K1 = 2;
        int K2 = 3;
        long expectedK1 = 45; // 10!/(2!(10-2)!) = 45
        long expectedK2 = 120; // 10!/(3!(10-3)!) = 120
        long expected = Math.abs(expectedK1 - expectedK2); // |45 - 120| = 75
        long result = Combination.CalculateDifference(N, K1, K2);
        assertEquals(expected, result);
    }

    @Test
    void benchmarkCalculateCombination() {
        int N = 100;
        int K = 50;
        int T = 2;
        long startTime = System.nanoTime();
        long combinations = Combination.CalculateCombination(N, K, T);
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);
        System.out.printf("Benchmark: Number of combinations when the AGV picks %d boxes out of %d boxes in %d trips: %d (Time: %d ms)\n",
                K, N, T, combinations, elapsedTime);
    }

    @Test
    void benchmarkCalculateDifference() {
        int N = 100;
        int K1 = 50;
        int K2 = 40;
        long startTime = System.nanoTime();
        long difference = Combination.CalculateDifference(N, K1, K2);
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);
        System.out.printf("Benchmark: Difference between combinations when the AGV picks %d and %d boxes out of %d boxes: %d (Time: %d ms)\n",
                K1, K2, N, difference, elapsedTime);
    }
}


