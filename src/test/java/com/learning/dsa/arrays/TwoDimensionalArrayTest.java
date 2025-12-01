package com.learning.dsa.arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TwoDimensionalArrayTest {
    
    private TwoDimensionalArray twoDArray;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    public void setUp() {
        outContent.reset();
        twoDArray = new TwoDimensionalArray(3, 3);
        System.setOut(new PrintStream(outContent));
    }
    
    @org.junit.jupiter.api.AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }
    
    @Test
    @DisplayName("Test constructor initializes array with correct dimensions")
    public void testConstructor() {
        TwoDimensionalArray testArray = new TwoDimensionalArray(4, 5);
        assertNotNull(testArray.array);
        assertEquals(4, testArray.array.length);
        assertEquals(5, testArray.array[0].length);
    }
    
    @Test
    @DisplayName("Test constructor initializes all cells to Integer.MIN_VALUE")
    public void testConstructorInitialization() {
        for (int row = 0; row < twoDArray.array.length; row++) {
            for (int col = 0; col < twoDArray.array[0].length; col++) {
                assertEquals(Integer.MIN_VALUE, twoDArray.array[row][col]);
            }
        }
    }
    
    @Test
    @DisplayName("Test successful insertion of value")
    public void testInsertSuccess() {
        twoDArray.insert(0, 0, 42);
        assertEquals(42, twoDArray.array[0][0]);
        assertTrue(outContent.toString().contains("The value has been successfully inserted."));
    }
    
    @Test
    @DisplayName("Test insertion into already occupied cell")
    public void testInsertOccupiedCell() {
        twoDArray.insert(1, 1, 10);
        outContent.reset();
        twoDArray.insert(1, 1, 20);
        assertTrue(outContent.toString().contains("The cell is already occupied."));
        assertEquals(10, twoDArray.array[1][1]);
    }
    
    @Test
    @DisplayName("Test insertion with invalid row index")
    public void testInsertInvalidRowIndex() {
        twoDArray.insert(5, 1, 100);
        assertTrue(outContent.toString().contains("Invalid index for the 2D array."));
    }
    
    @Test
    @DisplayName("Test insertion with invalid column index")
    public void testInsertInvalidColumnIndex() {
        twoDArray.insert(1, 5, 100);
        assertTrue(outContent.toString().contains("Invalid index for the 2D array."));
    }
    
    @Test
    @DisplayName("Test insertion with negative indices")
    public void testInsertNegativeIndices() {
        twoDArray.insert(-1, -1, 100);
        assertTrue(outContent.toString().contains("Invalid index for the 2D array."));
    }
    
    @Test
    @DisplayName("Test accessing a cell")
    public void testAccess() {
        twoDArray.array[2][2] = 99;
        twoDArray.access(2, 2);
        String output = outContent.toString();
        assertTrue(output.contains("Accessing row# 2 column # 2"));
        assertTrue(output.contains("The cell value is: 99"));
    }
    
    @Test
    @DisplayName("Test traversal prints all elements")
    public void testTraversal() {
        twoDArray.insert(0, 0, 1);
        twoDArray.insert(1, 1, 5);
        twoDArray.insert(2, 2, 9);
        outContent.reset();
        
        twoDArray.traversal();
        String output = outContent.toString();
        assertTrue(output.contains("The array is as follows: 1"));
        assertTrue(output.contains("The array is as follows: 5"));
        assertTrue(output.contains("The array is as follows: 9"));
    }
    
    @Test
    @DisplayName("Test search finds existing value")
    public void testSearchFound() {
        twoDArray.insert(1, 2, 77);
        outContent.reset();
        
        twoDArray.search(77);
        assertTrue(outContent.toString().contains("Value is found at row: 1 and column: 2"));
    }
    
    @Test
    @DisplayName("Test search does not find non-existing value")
    public void testSearchNotFound() {
        twoDArray.search(999);
        assertTrue(outContent.toString().contains("Value was not found in the array."));
    }
    
    @Test
    @DisplayName("Test search finds first occurrence of duplicate values")
    public void testSearchDuplicateValues() {
        twoDArray.insert(0, 0, 50);
        twoDArray.insert(2, 2, 50);
        outContent.reset();
        
        twoDArray.search(50);
        assertTrue(outContent.toString().contains("Value is found at row: 0 and column: 0"));
    }
    
    @Test
    @DisplayName("Test deletion of a value")
    public void testDelete() {
        twoDArray.insert(1, 1, 123);
        outContent.reset();
        
        twoDArray.delete(1, 1);
        assertTrue(outContent.toString().contains("Successfully deleted the value: 123"));
        assertEquals(Integer.MIN_VALUE, twoDArray.array[1][1]);
    }
    
    @Test
    @DisplayName("Test deletion resets cell to Integer.MIN_VALUE")
    public void testDeleteResetsToMinValue() {
        twoDArray.array[0][0] = 42;
        twoDArray.delete(0, 0);
        assertEquals(Integer.MIN_VALUE, twoDArray.array[0][0]);
    }
    
    @Test
    @DisplayName("Test full workflow: insert, search, delete, search again")
    public void testFullWorkflow() {
        twoDArray.insert(1, 1, 555);
        assertEquals(555, twoDArray.array[1][1]);
        
        outContent.reset();
        twoDArray.search(555);
        assertTrue(outContent.toString().contains("Value is found at row: 1 and column: 1"));
        
        twoDArray.delete(1, 1);
        outContent.reset();
        twoDArray.search(555);
        assertTrue(outContent.toString().contains("Value was not found in the array."));
    }
    
    @Test
    @DisplayName("Test edge case: 1x1 array")
    public void testSingleElementArray() {
        TwoDimensionalArray singleArray = new TwoDimensionalArray(1, 1);
        System.setOut(new PrintStream(outContent));
        
        singleArray.insert(0, 0, 7);
        assertEquals(7, singleArray.array[0][0]);
    }
}
