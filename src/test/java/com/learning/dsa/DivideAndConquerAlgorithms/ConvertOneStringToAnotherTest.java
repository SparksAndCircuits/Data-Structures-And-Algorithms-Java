package com.learning.dsa.DivideAndConquerAlgorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConvertOneStringToAnother Test Suite - Edit Distance Algorithm")
public class ConvertOneStringToAnotherTest {

    private ConvertOneStringToAnother converter;

    @BeforeEach
    void setUp() {
        converter = new ConvertOneStringToAnother();
    }

    @Test
    @DisplayName("Identical strings require zero operations")
    void testIdenticalStrings() {
        assertEquals(0, converter.findMinOperations("test", "test"));
        assertEquals(0, converter.findMinOperations("hello", "hello"));
        assertEquals(0, converter.findMinOperations("a", "a"));
    }

    @Test
    @DisplayName("Empty string to non-empty requires insertions equla to target length")
    void testEmptyToNonEmpty() {
        assertEquals(5, converter.findMinOperations("", "hello"));
        assertEquals(4, converter.findMinOperations("", "test"));
        assertEquals(1, converter.findMinOperations("", "a"));
    }

    @Test
    @DisplayName("Non-empty string to empty requires deletions equals to source length")
    void testNonEmptyToEmpty() {
        assertEquals(4, converter.findMinOperations("test", ""));
        assertEquals(5, converter.findMinOperations("hello", ""));
        assertEquals(1, converter.findMinOperations("a", ""));
    }

    @Test
    @DisplayName("Both strings empty require zero operations")
    void testBothEmpty() {
        assertEquals(0, converter.findMinOperations("", ""));
    }

    @Test
    @DisplayName("Single character operations")
    void testSingleCharacterOperations() {
        assertEquals(1, converter.findMinOperations("a", "b"));
        assertEquals(1, converter.findMinOperations("ab", "a"));
        assertEquals(1, converter.findMinOperations("a", "ab"));
    }

    @Test
    @DisplayName("Classic edit distance example - cat to dog")
    void testCatToDog() {
        assertEquals(3, converter.findMinOperations("cat", "dog"));
    }

    @Test
    @DisplayName("Classic edit distance example - kitten to sitting")
    void testKittenToSitting() {
        assertEquals(3, converter.findMinOperations("kitten", "sitting"));
    }

    @Test
    @DisplayName("Classic edit distance example - saturday to sunday")
    void testSaturdayToSunday() {
        assertEquals(3, converter.findMinOperations("saturday", "sunday"));
    }

    @Test
    @DisplayName("Strings with common prefix")
    void testCommonPrefix() {
        assertEquals(2, converter.findMinOperations("hello", "help"));
        assertEquals(1, converter.findMinOperations("table", "cable"));
    }

    @Test
    @DisplayName("Strings with common suffix")
    void testCommonSuffix() {
        assertEquals(1, converter.findMinOperations("testing", "resting"));
        assertEquals(1, converter.findMinOperations("cart", "part"));
    }

    @Test
    @DisplayName("One string is substring of another")
    void testSubstring() {
        assertEquals(3, converter.findMinOperations("test", "testing"));
        assertEquals(3, converter.findMinOperations("testing", "test"));
    }

    @Test
    @DisplayName("Completely different strings")
    void testCompletelyDifferent() {
        assertEquals(3, converter.findMinOperations("abc", "xyz"));
        assertEquals(4, converter.findMinOperations("abcd", "efgh"));
    }

    @Test
    @DisplayName("Strings with repeated characters")
    void testRepeatedCharacters() {
        assertEquals(1, converter.findMinOperations("aaa", "aaaa"));
        assertEquals(1, converter.findMinOperations("aaa", "aa"));
        assertEquals(3, converter.findMinOperations("aaa", "bbb"));
    }

    @Test
    @DisplayName("Case sensitive operations")
    void testCaseSensitive() {
        assertEquals(1, converter.findMinOperations("Hello", "hello"));
        assertEquals(5, converter.findMinOperations("HELLO", "hello"));
    }

    @Test
    @DisplayName("Longer strings with multiple operations needed")
    void testLongerStrings() {
        assertEquals(5, converter.findMinOperations("intention", "execution"));
        assertEquals(3, converter.findMinOperations("horse", "ros"));
    }

    @Test
    @DisplayName("Reversed strings")
    void testReversedStrings() {
        assertEquals(2, converter.findMinOperations("abc", "cba"));
        assertEquals(4, converter.findMinOperations("abcd", "dcba"));
    }

    @Test
    @DisplayName("Single character to multi-character")
    void testSingleToMultiple() {
        assertEquals(4, converter.findMinOperations("a", "abcde"));
        assertEquals(4, converter.findMinOperations("x", "test"));
    }

    @Test
    @DisplayName("Multi-character to single character")
    void testMultipleToSingle() {
        assertEquals(4, converter.findMinOperations("abcde", "a"));
        assertEquals(3, converter.findMinOperations("test", "t"));
    }

    @ParameterizedTest
    @CsvSource({
            "table, cable, 1",
            "catch, catch, 0",
            "bat, but, 1",
            "game, fame, 1",
            "biting, sitting, 2"
    })
    @DisplayName("Parameterized test for various string pairs")
    void testVariousStringPairs(String s1, String s2, int expected) {
        assertEquals(expected, converter.findMinOperations(s1, s2));
    }

    @Test
    @DisplayName("Strings with spaces")
    void testStringsWithSpaces() {
        assertEquals(1, converter.findMinOperations("hello world", "helloworld"));
        assertEquals(1, converter.findMinOperations("test case", "testcase"));
    }

    @Test
    @DisplayName("Strings with special characters")
    void testSpecialCharacters() {
        assertEquals(1, converter.findMinOperations("hello!", "hello"));
        assertEquals(1, converter.findMinOperations("test@123", "test123"));
    }

    @Test
    @DisplayName("Numeric strings")
    void testNumericStrings() {
        assertEquals(1, converter.findMinOperations("123", "124"));
        assertEquals(1, converter.findMinOperations("12345", "1235"));
    }

    @Test
    @DisplayName("Very short strings")
    void testVeryShortStrings() {
        assertEquals(1, converter.findMinOperations("a", ""));
        assertEquals(1, converter.findMinOperations("", "b"));
        assertEquals(1, converter.findMinOperations("x", "y"));
    }

    @Test
    @DisplayName("Strings requiring only insertions")
    void testOnlyInsertions() {
        assertEquals(2, converter.findMinOperations("cat", "catch"));
        assertEquals(4, converter.findMinOperations("run", "running"));
    }

    @Test
    @DisplayName("Strings requiring only deletions")
    void testOnlyDeletions() {
        assertEquals(2, converter.findMinOperations("catch", "cat"));
        assertEquals(4, converter.findMinOperations("running", "run"));
    }

    @Test
    @DisplayName("Strings requiring only replacements")
    void testOnlyReplacements() {
        assertEquals(3, converter.findMinOperations("cat", "dog"));
        assertEquals(2, converter.findMinOperations("bat", "bit"));
    }

    @Test
    @DisplayName("Symmetric operations - order matters")
    void testSymmetricOperations() {
        int op1 = converter.findMinOperations("abc", "def");
        int op2 = converter.findMinOperations("def", "abc");
        assertEquals(op1, op2);
    }
}
