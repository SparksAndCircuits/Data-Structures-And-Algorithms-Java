package com.learning.dsa.arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SingleDimensionalArrayTest {
private SingleDimensionalArray sda;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Test array creation with valid size")
    public void testArrayCreation() {
        sda = new SingleDimensionalArray(5);
        assertNotNull(sda.array);
        assertEquals(5, sda.array.length);
        
        // Verify all elements are initialized to Integer.MIN_VALUE
        for (int i = 0; i < sda.array.length; i++) {
            assertEquals(Integer.MIN_VALUE, sda.array[i]);
        }
    }

    @Test
    @DisplayName("Test array creation with size zero")
    public void testArrayCreationWithZeroSize() {
        sda = new SingleDimensionalArray(0);
        assertNotNull(sda.array);
        assertEquals(0, sda.array.length);
    }

    @Test
    @DisplayName("Test array creation with size one")
    public void testArrayCreationWithSizeOne() {
        sda = new SingleDimensionalArray(1);
        assertNotNull(sda.array);
        assertEquals(1, sda.array.length);
        assertEquals(Integer.MIN_VALUE, sda.array[0]);
    }

    @Test
    @DisplayName("Test successful insertion at valid index")
    public void testInsertAtValidIndex() {
        sda = new SingleDimensionalArray(5);
        sda.insert(0, 10);
        
        assertEquals(10, sda.array[0]);
        assertTrue(outContent.toString().contains("Successfully inserted"));
    }

    @Test
    @DisplayName("Test insertion at multiple valid indices")
    public void testMultipleInsertions() {
        sda = new SingleDimensionalArray(5);
        sda.insert(0, 10);
        sda.insert(2, 30);
        sda.insert(4, 50);
        
        assertEquals(10, sda.array[0]);
        assertEquals(30, sda.array[2]);
        assertEquals(50, sda.array[4]);
        assertEquals(Integer.MIN_VALUE, sda.array[1]);
        assertEquals(Integer.MIN_VALUE, sda.array[3]);
    }

    @Test
    @DisplayName("Test insertion in already occupied cell")
    public void testInsertInOccupiedCell() {
        sda = new SingleDimensionalArray(5);
        sda.insert(0, 10);
        outContent.reset();
        sda.insert(0, 20);
        
        assertEquals(10, sda.array[0]); // Value should not change
        assertTrue(outContent.toString().contains("This cell is already occupied"));
    }

    @Test
    @DisplayName("Test insertion at invalid positive index")
    public void testInsertAtInvalidPositiveIndex() {
        sda = new SingleDimensionalArray(5);
        sda.insert(10, 100);
        
        assertTrue(outContent.toString().contains("Invalid index to access array!"));
    }

    @Test
    @DisplayName("Test insertion at negative index")
    public void testInsertAtNegativeIndex() {
        sda = new SingleDimensionalArray(5);
        sda.insert(-1, 100);
        
        assertTrue(outContent.toString().contains("Invalid index to access array!"));
    }

    @Test
    @DisplayName("Test traversal of array")
    public void testTraverseArray() {
        sda = new SingleDimensionalArray(3);
        sda.insert(0, 10);
        sda.insert(1, 20);
        sda.insert(2, 30);
        
        outContent.reset();
        sda.traverseArray();
        
        String output = outContent.toString();
        assertTrue(output.contains("10"));
        assertTrue(output.contains("20"));
        assertTrue(output.contains("30"));
    }

    @Test
    @DisplayName("Test traversal of empty array")
    public void testTraverseEmptyArray() {
        sda = new SingleDimensionalArray(3);
        outContent.reset();
        sda.traverseArray();
        
        String output = outContent.toString();
        assertTrue(output.contains(String.valueOf(Integer.MIN_VALUE)));
    }

    @Test
    @DisplayName("Test search for existing element")
    public void testSearchExistingElement() {
        sda = new SingleDimensionalArray(5);
        sda.insert(2, 30);
        
        outContent.reset();
        sda.searchInArray(30);
        
        String output = outContent.toString();
        assertTrue(output.contains("Value is found at the index of: 2"));
    }

    @Test
    @DisplayName("Test search for non-existing element")
    public void testSearchNonExistingElement() {
        sda = new SingleDimensionalArray(5);
        sda.insert(0, 10);
        
        outContent.reset();
        sda.searchInArray(25);
        
        String output = outContent.toString();
        assertTrue(output.contains("25 was not found in the array"));
    }

    @Test
    @DisplayName("Test search for Integer.MIN_VALUE")
    public void testSearchForMinValue() {
        sda = new SingleDimensionalArray(5);
        
        outContent.reset();
        sda.searchInArray(Integer.MIN_VALUE);
        
        String output = outContent.toString();
        // Should find multiple occurrences at indices 0-4
        assertTrue(output.contains("Value is found at the index of:"));
    }

    @Test
    @DisplayName("Test search in array with multiple matching values")
    public void testSearchMultipleMatches() {
        sda = new SingleDimensionalArray(5);
        sda.insert(0, 10);
        sda.insert(2, 10);
        sda.insert(4, 10);
        
        outContent.reset();
        sda.searchInArray(10);
        
        String output = outContent.toString();
        assertTrue(output.contains("Value is found at the index of: 0"));
        assertTrue(output.contains("Value is found at the index of: 2"));
        assertTrue(output.contains("Value is found at the index of: 4"));
    }

    @Test
    @DisplayName("Test delete at valid index")
    public void testDeleteAtValidIndex() {
        sda = new SingleDimensionalArray(5);
        sda.insert(2, 30);
        
        outContent.reset();
        sda.deleteIndex(2);
        
        assertEquals(Integer.MIN_VALUE, sda.array[2]);
        assertTrue(outContent.toString().contains("The value has been sucessfully deleted"));
    }

    @Test
    @DisplayName("Test delete at invalid positive index")
    public void testDeleteAtInvalidPositiveIndex() {
        sda = new SingleDimensionalArray(5);
        sda.deleteIndex(10);
        
        assertTrue(outContent.toString().contains("The value that was provided was not in the range of the array"));
    }

    @Test
    @DisplayName("Test delete at negative index")
    public void testDeleteAtNegativeIndex() {
        sda = new SingleDimensionalArray(5);
        sda.deleteIndex(-1);
        
        assertTrue(outContent.toString().contains("The value that was provided was not in the range of the array"));
    }

    @Test
    @DisplayName("Test delete already empty cell")
    public void testDeleteEmptyCell() {
        sda = new SingleDimensionalArray(5);
        outContent.reset();
        sda.deleteIndex(0);
        
        assertEquals(Integer.MIN_VALUE, sda.array[0]);
        assertTrue(outContent.toString().contains("The value has been sucessfully deleted"));
    }

    @Test
    @DisplayName("Test insertion of Integer.MIN_VALUE")
    public void testInsertMinValue() {
        sda = new SingleDimensionalArray(3);
        outContent.reset();
        sda.insert(0, Integer.MIN_VALUE);
        String output = outContent.toString();
        
        assertTrue(output.contains("Successfully inserted"), 
                   "Expected 'Successfully inserted' message but got: " + output);
        assertEquals(Integer.MIN_VALUE, sda.array[0]);
    }

    @Test
    @DisplayName("Test filling entire array")
    public void testFillEntireArray() {
        sda = new SingleDimensionalArray(4);
        for (int i = 0; i < 4; i++) {
            sda.insert(i, i * 10);
        }
        
        for (int i = 0; i < 4; i++) {
            assertEquals(i * 10, sda.array[i]);
        }
    }

    @Test
    @DisplayName("Test operations on array after deletion")
    public void testOperationsAfterDeletion() {
        sda = new SingleDimensionalArray(5);
        sda.insert(2, 30);
        sda.deleteIndex(2);
        
        // Should be able to insert again after deletion
        outContent.reset();
        sda.insert(2, 40);
        assertEquals(40, sda.array[2]);
        assertTrue(outContent.toString().contains("Successfully inserted"));
    }
}
