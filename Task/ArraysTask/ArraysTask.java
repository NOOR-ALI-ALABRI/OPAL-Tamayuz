package ArraysTask;

import java.util.Scanner;

public class ArraysTask {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] numbers = {12, 7, 25, 3, 18, 9, 30, 14};

        // Q1
        System.out.println("Sum: " + sumArray(numbers));
        System.out.println("Average: " + averageArray(numbers));

        // Q2
        System.out.println("Max: " + findMax(numbers));
        System.out.println("Min: " + findMin(numbers));
        System.out.println("Difference: " + (findMax(numbers) - findMin(numbers)));

        // Q3
        System.out.println("Even numbers: " + countEven(numbers));
        for (int k = 0; k < 2; k++) {
            System.out.println("Enter a number to search for");
            int target = scanner.nextInt();
            int index = indexOf(numbers, target);
            if (index != -1) {
                System.out.println(target + " found at index " + index);
            } else {
                System.out.println(target + " not found");
            }
        }

        // Q4
        System.out.print("Original: ");
        printArray(numbers);
        System.out.print("Reversed: ");
        printArray(reverseArray(numbers));

        // Q5
        int[] grades = readGrades(5);
        int passed = countPassed(grades);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + (grades.length - passed));
    }
/////////////////////////////////////
/// 
    // Question 1
    static int sumArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static double averageArray(int[] arr) {
        return (double) sumArray(arr) / arr.length;
    }

    // Question 2
    static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    static int findMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    // Question 3
    static int countEven(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    static int indexOf(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // Question 4
    static int[] reverseArray(int[] arr) {
        int[] reversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversed[i] = arr[arr.length - 1 - i];
        }
        return reversed;
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Question 5
    static int[] readGrades(int size) {
        int[] grades = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Enter grade " + (i + 1));
            grades[i] = scanner.nextInt();
        }
        return grades;
    }

    static int countPassed(int[] grades) {
        int passed = 0;
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] >= 50) {
                passed++;
            }
        }
        return passed;
    }
}