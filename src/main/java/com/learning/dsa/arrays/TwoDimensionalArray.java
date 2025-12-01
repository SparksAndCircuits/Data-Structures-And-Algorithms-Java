package com.learning.dsa.arrays;

public class TwoDimensionalArray {
    int array[][];

    // Creation of a two dimensional array (This is a constructor)
    public TwoDimensionalArray(int numberOfRows, int numberOfColumns) {
        this.array = new int[numberOfRows][numberOfColumns];

        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                array[row][col] = Integer.MIN_VALUE;
            }
        }
    }

    // Integer a value into a two dimensional array
    public void insert(int row, int col, int value) {
        try {
            if (array[row][col] == Integer.MIN_VALUE) {
                array[row][col] = value;
                System.out.println("The value has been successfully inserted.");
            } else {
                System.out.println("The cell is already occupied.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index for the 2D array.");
        }
    }

    // Accessing an element in two dimensional array
    public void access(int row, int col) {
        System.out.println("\nAccessing row# " + row + " column # " + col);
        System.out.println("The cell value is: " + array[row][col]);
    }

    // Traversal through a two dimensional array
    public void traversal() {
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                System.out.println("The array is as follows: " + array[row][col] + " ");
            }

            System.out.println();
        }
    }

    // Search for a value in a two dimensional array
    public void search(int value) {
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                if (array[row][col] == value) {
                    System.out.println("Value is found at row: " + row + " and column: " + col);
                    return;
                }
            }
        }

        System.out.println("Value was not found in the array.");
    }

    // Deletion a value from a two dimensional array
    public void delete(int row, int col) {
        System.out.println("Successfully deleted the value: " + array[row][col]);
        array[row][col] = Integer.MIN_VALUE;
    }
}
