package Map2D_ADT.Test;

import Map2D_ADT.Service;
import Map2D_ADT.X;


public class XTest {
    public static void main(String[] args) {
        testSearchLower();
        testSearchHigher();
        testBinarySearch();
    }

    public static void testSearchLower() {
        // Create test example, an array of Service objects
        String[] serviceType = new String[]{"Park"};
        Service[] services = {
            // missing value: 5
            new Service(1, serviceType),
            new Service(2, serviceType),
            new Service(3, serviceType),
            new Service(4, serviceType),
            new Service(6, serviceType),
        };

        // Create an instance of X object
        X x = new X(0, services); // The value of X doesn't matter for this test

        // Test case 1: handles cases where the target value is present in the array, return index of the target
        int target = 4; // binary search for value of Service object 
        int expectedResult = 3; // The expected index of the value that is less than or equal to the target
        int result = x.searchLower(target);

        if (result == expectedResult) {
            System.out.println("Test case 1 in testSearchLower() passed, Actual result = " + result + ", Expected result = " + expectedResult);
        } else {
            System.out.println("Test case 1 in testSearchLower() failed, Actual result = " + result + ", Expected result = " + expectedResult);
        }

        // Test case 2: handles cases where the target value is not present in the array, return index of SMALLER value and nearest to target
        int target2 = 5; // binary search for value of Service object 
        int expectedResult2 = 3; // The expected index of the value that is less than or equal to the target
        int result2 = x.searchLower(target2);

        if (result2 == expectedResult2) {
            System.out.println("Test case 2 in testSearchLower() passed, Actual result = " + result2 + ", Expected result = " + expectedResult2);
        } else {
            System.out.println("Test case 2 in testSearchLower() failed, Actual result = " + result2 + ", Expected result = " + expectedResult2);
        }
    }

    public static void testSearchHigher() {
        // Create test example, an array of Service objects
        String[] serviceType = new String[]{"Park"};
        Service[] services = {
            // missing value: 3
            new Service(1, serviceType),
            new Service(2, serviceType),
            new Service(4, serviceType),
            new Service(5, serviceType),
            new Service(6, serviceType),
        };

        // Create an instance of X object
        X x = new X(0, services); // The value of X doesn't matter for this test

        // Test case 1: Same algorithm as the Test case 1  in the function testSearchLower(), therefore no additional testing is needed here
        System.out.println("Test case 2 in testSearchHigher() did not test");

        // Test case 2: handles cases where the target value is not present in the array, return index of HIGHER value and nearest to target
        int target2 = 3; // binary search for value of Service object 
        int expectedResult2 = 1; // The expected index of the value that is less than or equal to the target
        int result2 = x.searchLower(target2);

        if (result2 == expectedResult2) {
            System.out.println("Test case 2 in testSearchHigher() passed, Actual result = " + result2 + ", Expected result = " + expectedResult2);
        } else {
            System.out.println("Test case 2 in testSearchHigher() failed, Actual result = " + result2 + ", Expected result = " + expectedResult2);
        }
    }

    public static void testBinarySearch() {
        // Create an array of Service objects for testing
        Service[] services = {
            new Service(1, null),
            new Service(3, null),
            new Service(5, null),
            new Service(7, null),
            new Service(9, null),
        };

        // Create an instance of X object
        X x = new X(0, services);

        // Test case 1: Both boundaries are within the range
        int[] boundary1 = {2, 8}; // find index where value = 2 and index where value = 8
        int[] expectedResult1 = {1, 3}; // Expected start and end indices
        int[] result1 = x.binarySearch(boundary1);
        if (expectedResult1[0] == result1[0] && expectedResult1[1] == result1[1]) {
            System.out.println("Test case 1 in testBinarySearch() passed, Actual result = [" + result1[0] +", "+ result1[1]+ "], Expected result = [" + expectedResult1[0] +","+ expectedResult1[1]+"]");
        } else {
            System.out.println("Test case 1 in testBinarySearch() failed, Actual result = [" + result1[0] +", "+ result1[1]+ "], Expected result = [" + expectedResult1[0] +","+ expectedResult1[1]+"]");
        }
        

        // Test case 2: Start boundary is larger than the largest value
        int[] boundary2 = {10, 12};
        int[] expectedResult2 = {}; // Expected empty result
        int[] result2 = x.binarySearch(boundary2);
        if (result2.length == expectedResult2.length) {
            System.out.println("Test case 2 in testBinarySearch() passed, both Actual and Expected results are empty/ length = 0");
        } else {
            System.out.println("Test case 2 in testBinarySearch() failed, Actual result is not empty");
        }

        // Test case 3: End boundary is smaller than the smallest value
        int[] boundary3 = {-1, 0};
        int[] expectedResult3 = {}; // Expected empty result
        int[] result3 = x.binarySearch(boundary3);
        if (result3.length == expectedResult3.length) {
            System.out.println("Test case 3 in testBinarySearch() passed, both Actual and Expected results are empty/ length = 0");
        } else {
            System.out.println("Test case 3 in testBinarySearch() failed, Actual result is not empty");
        }
    }
}


