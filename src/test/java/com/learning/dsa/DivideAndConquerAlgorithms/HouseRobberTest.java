package com.learning.dsa.DivideAndConquerAlgorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HouseRobber Test Suite - Dynamic programming.")
public class HouseRobberTest {
    
    private HouseRobber robber;

    @BeforeEach
    void setUp(){
        robber = new HouseRobber();
    }

    @Test
    @DisplayName("Single house returns its value")
    void testSingleHouse(){
        assertEquals(5, robber.maxMoney(new int[]{5}));
        assertEquals(100, robber.maxMoney(new int[]{100}));
        assertEquals(1, robber.maxMoney(new int[]{1}));
    }

    @Test
    @DisplayName("Two houses returns maximum of the two")
    void testTwoHouses(){
        assertEquals(10, robber.maxMoney(new int[]{5, 10}));
        assertEquals(20, robber.maxMoney(new int[]{20, 10}));
        assertEquals(15, robber.maxMoney(new int[]{15, 15}));
    }

    @Test
    @DisplayName("Three houses - classic scenario")
    void testThreeHouses(){
        assertEquals(10, robber.maxMoney(new int[]{5, 3, 10}));
        assertEquals(20, robber.maxMoney(new int[]{10, 5, 20}));
        assertEquals(15, robber.maxMoney(new int[]{5, 10, 15}));
    }

    @Test
    @DisplayName("Example from LeetCode - [1,2,3,1]")
    void testLeetCodeExample1(){
        assertEquals(4, robber.maxMoney(new int[]{1, 2, 3, 1}));
    }

    @Test
    @DisplayName("Example from LeetCode - [2, 7, 9, 3, 1]")
    void testLeetCodeExample2(){
        assertEquals(12, robber.maxMoney(new int[]{2, 7, 9, 2, 1}));
    }

    @Test
    @DisplayName("All houses have same value")
    void testAllSameValue(){
        assertEquals(15, robber.maxMoney(new int[]{5, 5, 5, 5, 5}));
        assertEquals(20, robber.maxMoney(new int[]{10, 10, 10, 10, 10}));
    }

    @Test
    @DisplayName("Increasing house values")
    void testIncreasingValues(){
        assertEquals(9, robber.maxMoney(new int[]{1, 2, 3, 4, 5}));
        assertEquals(30, robber.maxMoney(new int[]{5, 10, 15, 20}));
    }

    @Test
    @DisplayName("Decreasing house values")
    void testDecreasingValues(){
        assertEquals(9, robber.maxMoney(new int[]{5, 4, 3, 2, 1}));
        assertEquals(30, robber.maxMoney(new int[]{20, 15, 10, 5}));
    }

    @Test
    @DisplayName("Alternating high and low values")
    void testAlternatingValues(){
        assertEquals(30, robber.maxMoney(new int[]{10, 1, 10, 1, 10}));
        assertEquals(15, robber.maxMoney(new int[]{5, 1, 5, 1, 5, 1}));
    }

    @Test
    @DisplayName("house with zero value")
    void testZeroValues(){
        assertEquals(10, robber.maxMoney(new int[]{0, 10, 0, 5}));
        assertEquals(5, robber.maxMoney(new int[]{5, 0, 0, 0}));
        assertEquals(0, robber.maxMoney(new int[]{0, 0, 0, 0}));
    }

    @Test
    @DisplayName("large values")
    void testLargeValues(){
        assertEquals(2000, robber.maxMoney(new int[]{1000, 500, 1000}));
        assertEquals(10000, robber.maxMoney(new int[]{5000, 3000, 5000, 2000}));
    }

    @Test
    @DisplayName("Empty array returns zero")
    void testEmptyArray() {
        assertEquals(0, robber.maxMoney(new int[]{}));
    }

    @Test
    @DisplayName("Four houses - optimal non-adjacent selection")
    void testFourHouses() {
        assertEquals(11, robber.maxMoney(new int[]{5, 1, 3, 6}));
        assertEquals(20, robber.maxMoney(new int[]{10, 5, 2, 10}));
        assertEquals(15, robber.maxMoney(new int[]{3, 10, 3, 5}));
    }

    @Test
    @DisplayName("Five houses with mixed values")
    void testFiveHouses() {
        assertEquals(16, robber.maxMoney(new int[]{2, 5, 9, 6, 1}));
        assertEquals(19, robber.maxMoney(new int[]{5, 3, 4, 11, 2}));
    }

    @Test
    @DisplayName("Six houses - longer sequence")
    void testSixHouses() {
        assertEquals(21, robber.maxMoney(new int[]{6, 7, 1, 3, 8, 2}));
        assertEquals(25, robber.maxMoney(new int[]{10, 1, 1, 10, 1, 1, 10}));
    }

    @Test
    @DisplayName("First house has maximum value")
    void testFirstHouseMaximum() {
        assertEquals(100, robber.maxMoney(new int[]{100, 1, 1, 1}));
        assertEquals(51, robber.maxMoney(new int[]{50, 1, 1, 1, 1}));
    }

    @Test
    @DisplayName("Last house has maximum value")
    void testLastHouseMaximum() {
        assertEquals(100, robber.maxMoney(new int[]{1, 1, 1, 100}));
        assertEquals(51, robber.maxMoney(new int[]{1, 1, 1, 1, 50}));
    }

    @Test
    @DisplayName("Middle house has maximum value")
    void testMiddleHouseMaximum() {
        assertEquals(100, robber.maxMoney(new int[]{1, 1, 100, 1, 1}));
        assertEquals(50, robber.maxMoney(new int[]{10, 5, 50, 5, 10}));
    }

    @Test
    @DisplayName("Two adjacent high value houses")
    void testTwoAdjacentHighValues() {
        assertEquals(20, robber.maxMoney(new int[]{1, 10, 10, 1}));
        assertEquals(25, robber.maxMoney(new int[]{5, 15, 15, 5}));
    }

    @Test
    @DisplayName("Pattern: High-Low-High-Low")
    void testHighLowPattern() {
        assertEquals(60, robber.maxMoney(new int[]{20, 5, 20, 5, 20}));
        assertEquals(90, robber.maxMoney(new int[]{30, 10, 30, 10, 30}));
    }

    @Test
    @DisplayName("Long sequence with optimal solution")
    void testLongSequence() {
        assertEquals(22, robber.maxMoney(new int[]{2, 1, 4, 9, 1, 1, 5, 8}));
        assertEquals(44, robber.maxMoney(new int[]{8, 2, 8, 9, 2, 3, 7, 9, 3}));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    @DisplayName("Parameterized tests for various scenarios")
    void testParameterized(int[] houses, int expected) {
        assertEquals(expected, robber.maxMoney(houses));
    }

    static Stream<Arguments> provideTestCases() {
        return Stream.of(
            Arguments.of(new int[]{5}, 5),
            Arguments.of(new int[]{1, 2}, 2),
            Arguments.of(new int[]{2, 1, 1, 2}, 4),
            Arguments.of(new int[]{5, 3, 4, 11, 2}, 16),
            Arguments.of(new int[]{1, 3, 1, 3, 100}, 103),
            Arguments.of(new int[]{10, 1, 1, 10}, 20)
        );
    }

    @Test
    @DisplayName("Rob every other house starting from first")
    void testRobEveryOther() {
        assertEquals(15, robber.maxMoney(new int[]{5, 1, 5, 1, 5}));
        assertEquals(30, robber.maxMoney(new int[]{10, 5, 10, 5, 10}));
    }

    @Test
    @DisplayName("Single high value among low values")
    void testSingleHighValue() {
        assertEquals(50, robber.maxMoney(new int[]{1, 2, 50, 2, 1}));
        assertEquals(100, robber.maxMoney(new int[]{5, 10, 100, 10, 5}));
    }

    @Test
    @DisplayName("Boundary case with maximum integer value")
    void testMaxIntValue() {
        assertEquals(Integer.MAX_VALUE, robber.maxMoney(new int[]{Integer.MAX_VALUE}));
        assertEquals(Integer.MAX_VALUE, robber.maxMoney(new int[]{Integer.MAX_VALUE, 1, 1}));
    }

    @Test
    @DisplayName("All ones")
    void testAllOnes() {
        assertEquals(3, robber.maxMoney(new int[]{1, 1, 1, 1, 1}));
        assertEquals(5, robber.maxMoney(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    @DisplayName("Three equal high values separated by low values")
    void testThreeEqualHighValues() {
        assertEquals(30, robber.maxMoney(new int[]{10, 1, 10, 1, 10}));
        assertEquals(60, robber.maxMoney(new int[]{20, 5, 20, 5, 20}));
    }
}
