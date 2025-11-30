package com.learning.dsa.arrays;

public class SingleDimensionalArray {
    int array[] = null;

    // Creation of a single dimensional array
    public SingleDimensionalArray(int sizeOfArray) {
        array = new int[sizeOfArray];

        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.MIN_VALUE;
        }
    }

    // Insert a element into an array
    public void insert(int location, int valueToBeInserted) {
        try {
            if (array[location] == Integer.MIN_VALUE) {
                array[location] = valueToBeInserted;
                System.out.println("Successfully inserted");
            } else {
                System.out.println("This cell is already occupied");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index to access array!");
        }
    }

    // Traversal through an array
    public void traverseArray() {
        try {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
        } catch (Exception e) {
            System.out.println("Array no longer exists!");
        }
    }

    // Search for an element in an array
    public void searchInArray(int valueToSearch) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valueToSearch) {
                System.out.println();
                System.out.println("Value is found at the index of: " + i);
            }
        }

        System.out.println(valueToSearch + " was not found in the array.");
    }

    // Deletion of an element in an array
    public void deleteIndex(int valueToBeDeleted) {
        try {
            array[valueToBeDeleted] = Integer.MIN_VALUE;
            System.out.println("The value has been sucessfully deleted from the array");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The value that was provided was not in the range of the array");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Testing Single Dimensional Array ---");
        // Test 1: Basic array creation and traversal
        System.out.println("Test 1: Creating array of size 5");
        SingleDimensionalArray sda = new SingleDimensionalArray(5);
        System.out.print("Initial array: ");
        sda.traverseArray();

        // Test 2: Inserting valid elements
        System.out.println("\nTest 2: Inserting elements");
        sda.insert(0, 10);
        sda.insert(2, 30);
        sda.insert(4, 50);
        System.out.print("Array after insertions: ");
        sda.traverseArray();

        // Test 3: Try to insert in occupied cell
        System.out.println("\nTest 3: Trying to insert in occupied cell");
        sda.insert(0, 15); // Should fail

        // Test 4: Insert at invalid index
        System.out.println("\nTest 4: Inserting at invalid index");
        sda.insert(10, 100); // Should handle exception
        sda.insert(-1, 100); // Should handle exception

        // Test 5: Searching for elements
        System.out.println("\nTest 5: Searching for elements");
        sda.searchInArray(30); // Should find at index 2
        sda.searchInArray(25); // Should not find
        sda.searchInArray(Integer.MIN_VALUE); // Should find at index 1 (empty cell)

        // Test 6: Deleting elements
        System.out.println("\nTest 6: Deleting elements");
        sda.deleteIndex(2); // Delete element at index 2
        System.out.print("Array after deletion: ");
        sda.traverseArray();

        // Test 7: Delete at invalid index
        System.out.println("\nTest 7: Deleting at invalid index");
        sda.deleteIndex(10); // Should handle exception
        sda.deleteIndex(-1); // Should handle exception

        // Test 8: Edge case - array of size 1
        System.out.println("\nTest 8: Edge case - array of size 1");
        SingleDimensionalArray smallArray = new SingleDimensionalArray(1);
        smallArray.insert(0, 99);
        System.out.print("Small array: ");
        smallArray.traverseArray();
        smallArray.searchInArray(99);

        // Test 9: Edge case - array of size 0
        System.out.println("\nTest 9: Edge case - array of size 0");
        try {
            SingleDimensionalArray emptyArray = new SingleDimensionalArray(0);
            System.out.print("Empty array: ");
            emptyArray.traverseArray();
            emptyArray.insert(0, 1); // Should fail
            emptyArray.searchInArray(1);
        } catch (Exception e) {
            System.out.println("Exception with size 0 array: " + e.getMessage());
        }

        // Test 10: Testing with Integer.MIN_VALUE (special case)
        System.out.println("\nTest 10: Testing with Integer.MIN_VALUE");
        SingleDimensionalArray specialArray = new SingleDimensionalArray(3);
        specialArray.insert(0, Integer.MIN_VALUE); // This will appear as "occupied"
        specialArray.insert(1, 100);
        System.out.print("Special array: ");
        specialArray.traverseArray();

        // Test 11: Fill entire array
        System.out.println("\nTest 11: Filling entire array");
        SingleDimensionalArray fullArray = new SingleDimensionalArray(4);
        for (int i = 0; i < 4; i++) {
            fullArray.insert(i, i * 10);
        }
        System.out.print("Full array: ");
        fullArray.traverseArray();

        System.out.println("\n=== All tests completed ===");
    }
}
