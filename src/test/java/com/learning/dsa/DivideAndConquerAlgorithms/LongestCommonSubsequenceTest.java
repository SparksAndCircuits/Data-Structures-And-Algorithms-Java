package com.learning.dsa.DivideAndConquerAlgorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class LongestCommonSubsequenceTest {

    private LongestCommonSubsequence lcs;

    @BeforeEach
    public void setUp() {
        lcs = new LongestCommonSubsequence();
    }

    @Test
    @DisplayName("Test with identical strings")
    public void testIdenticalStrings(){
        assertEquals(5, lcs.findLCSLength("abdca", "abdca"));
    }

    @Test
    @DisplayName("Test with completely different strings")
    public void testCompletelyDifferentStrings(){
        assertEquals(0, lcs.findLCSLength("abc", "def"));
    }

    @Test
    @DisplayName("Test with one empty string")
    public void testOneEmptyString(){
        assertEquals(0, lcs.findLCSLength("", "abc"));
        assertEquals(0, lcs.findLCSLength("abc", ""));
    }

    @Test
    @DisplayName("Test with both empty strings")
    public void testPartialOverlap(){
        assertEquals(0, lcs.findLCSLength("", ""));
    }

    @Test
    @DisplayName("Test with single character character match")
    public void testSingleCharacterMatch(){
        assertEquals(1, lcs.findLCSLength("a", "a"));
    }

    @Test
    @DisplayName("Test with single character no match")
    public void testSingleCharacterNoMatch(){
        assertEquals(0, lcs.findLCSLength("a", "b"));
    }

    @Test
    @DisplayName("Test with substring relationship")
    public void testSubstring(){
        assertEquals(3, lcs.findLCSLength("abc", "aXbYcZ"));
    }

    @Test
    @DisplayName("Test with reversed strings")
    public void testReversedStrings(){
        assertEquals(1, lcs.findLCSLength("abc", "cba"));
    }

    @Test
    @DisplayName("Test with repeated characters")
    public void testRepeatedCharacters() {
        assertEquals(3, lcs.findLCSLength("aaa", "aa"));
    }
    
    @Test
    @DisplayName("Test classic example")
    public void testClassicExample() {
        assertEquals(4, lcs.findLCSLength("passport", "ppsspt"));
    }
    
    @Test
    @DisplayName("Test with longer strings")
    public void testLongerStrings() {
        assertEquals(4, lcs.findLCSLength("AGGTAB", "GXTXAYB"));
    }
    
    @Test
    @DisplayName("Test where first string is longer")
    public void testFirstStringLonger() {
        assertEquals(2, lcs.findLCSLength("abcdef", "bf"));
    }
    
    @Test
    @DisplayName("Test where second string is longer")
    public void testSecondStringLonger() {
        assertEquals(2, lcs.findLCSLength("bf", "abcdef"));
    }
}
