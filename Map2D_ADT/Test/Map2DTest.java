package Map2D_ADT.Test;

import Map2D_ADT.Map2D;
import Map2D_ADT.Service;
import Map2D_ADT.X;


public class Map2DTest {
    public static void main(String[] args) {
        testGenerateX();
        testGenerateService();
        testRemoveDupX();
        testCopyArrayObject();
        testFindDistance();
        testFindBound();
        testQuickSortByX();
        testQuickSortByY();
        testContainServiceType();
        testFindNearbyService();
    }

    public static void testGenerateX() {
        // Define the bound for generating the X value
        int bound = 10;

        // Generate an X object
        // x.getValue() within the bound range, and x.getService().length is 1
        X x = Map2D.generate_x(bound);


        // Check if the generated X object is not null
        if (x == null) {
            System.out.println("Test case in testGenerateX() failed, Generated X object is null");
            return;
        }

        // Check if the value of the generated X object is within the bound
        if (x.getValue() < 0 || x.getValue() > bound) {
            System.out.println("Test case in testGenerateX() failed, Generated X value is out of bound.");
            return;
        }

        // Check if the array of Service objects in the generated X object is empty
        if (x.getService().length == 0) {
            System.out.println("Test case in testGenerateX() failed, Array of Service objects in generated X is empty.");
            return;
        }

        int length = x.getService().length;
        System.out.println("Test case in testGenerateX() passed, X value: "+x.getValue()+" (within the range from 0 to 10), Service array length: "+ length);
    }

    public static void testGenerateService() {
        // Define the bound for generating the Service value
        int bound = 10;

        // Define an array of service types
        String[] serviceTypes = {"gym", "hotel", "park", "school", "coffee"};

        // Generate a Service object
        Service service = Map2D.generate_service(bound, serviceTypes);

        // Check if the generated Service object is not null
        if (service == null) {
            System.out.println("Test case in testGenerateService() failed, generated Service object is null");
            return;
        }

        // Check if the value of the generated Service object is within the specified bound
        if (service.getValue() < 2 || service.getValue() > bound) {
            System.out.println("Test case in testGenerateService() failed, generated Service value is out of bound");
            return;
        }

        // Check if the array of service types in the generated Service object is not empty
        if (service.getService() == null || service.getService().length != 1) {
            System.out.println("Test case in testGenerateService() failed, Array of service types in generated Service is empty or does not have one element");
            return;
        }

        // Check if the generated service type is one of the serviceTypes array
        boolean typeFound = false;
        for (String type : serviceTypes) {
            if (type.equals(service.getService()[0])) {
                typeFound = true;
                break;
            }
        }

        if (!typeFound) {
            System.out.println("Test case in testGenerateService() failed, generated service type is not one of the serviceTypes array");
            return;
        }

        int length = service.getService().length;
        System.out.println("Test case in testGenerateService() passed, Y Yalue: "+service.getValue()+" (within the range from 0 to 10), Service array length: "+ length);
    }

    public static void testRemoveDupX() {
        // Create an array of X objects with potential duplicates
        X[] array_x = {
            new X(1, null),
            new X(2, null),
            new X(2, null),
            new X(3, null),
            new X(4, null),
            new X(4, null),
            new X(4, null),
            new X(5, null)
        };
         
        // Array with no duplicates should be: 1,2,3,4,5, length = 5
        int[] expectedArray = {1,2,3,4,5};
        // Call the removeDupX method to remove duplicates
        X[] newArray = Map2D.removeDupX(array_x);

        // Check if each element in the newArray is unique
        for (int i = 0; i < newArray.length - 1; i++) {
            if (newArray[i].getValue() == newArray[i + 1].getValue()) {
                System.out.println("Test case in testRemoveDupX() failed, duplicates found in the newArray.");
                return;
            }
        }

        System.out.print("Test case in testRemoveDupX() passed, all elements in the Actual/Test array: ");
        for (X x : newArray) {
            System.out.print(x.getValue() + ", ");
        }
        System.out.print("Expected Array with no duplicates: ");
        for (int num : expectedArray) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }

    public static void testCopyArrayObject() {
        // Create source and destination arrays
        Object[] source = {
            new X(1, null),
            new X(2, null),
            new X(3, null),
            new X(4, null),
            new X(5, null),
            new X(6, null),
            new X(7, null),
            new X(8, null)
        };
        Object[] destination = new Object[5];
    
        // Call the copyArrayObject method
        Object[] newArray = Map2D.copyArrayObject(source, 2, destination, 0, 5);
    
        // Define the expected array
        int[] expectedArray = {3, 4, 5, 6, 7}; 
    
        // Check if each element in the newArray matches the corresponding element in the expectedArray
        for (int i = 0; i < newArray.length; i++) {
            X x = (X)newArray[i]; // Cast each element to X
            if (x.getValue() != expectedArray[i]) {
                System.out.println("Test case in testCopyArrayObject() failed, elements in newArray do not match the elements in expectedArray");
                return;
            }
        }
    
        System.out.print("Test case in testCopyArrayObject() passed, Actual array: ");
        for (Object obj : newArray) {
            X x = (X)obj; // Cast each element to X
            System.out.print(x.getValue() + ", ");
        }
        System.out.print("Expected Array: ");
        for (int num : expectedArray) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }
    
    public static void testFindDistance() {
        // Define coordinates for two points
        int[] coor1 = {0, 0};
        int[] coor2 = {3, 4};
    
        // Call the findDistance method
        double result = Map2D.findDistance(coor1, coor2);
    
        // Define the expected result
        double expectedDistance = 5;
    
        // Check if the result matches the expected distance with a small tolerance
        if (result - expectedDistance != 0) {
            System.out.println("Test case in testFindDistance() failed, calculated distance does not match Expected Distance.");
            return;
        }
    
        System.out.println("Test case in testFindDistance() passed, Calculated distance: " + result + ", Expected Distance: " + expectedDistance);
    }

    public static void testFindBound() {
        // Define center and length
        int center = 5;
        int length = 6;
    
        int[] result = Map2D.findBound(center, length);
    
        // Define the expected start and end boundary indices
        int expectedStart = 2;
        int expectedEnd = 8;
    
        // Check if the result matches the expected boundary indices
        if (result[0] != expectedStart || result[1] != expectedEnd) {
            System.out.println("Test case in testFindBound() failed, calculated boundary does not matches expected boundary");
            return;
        }
    
        System.out.println("Test case in testFindBound() passed, Calculated boundary: [" + result[0] + ", " + result[1] + "], Expected boundary: [" + expectedStart + ", " + expectedEnd + "]");
    }
    
    public static void testQuickSortByX() {
        // Create an array of X objects to be sorted
        X[] places = new X[]{
            new X(5, null),
            new X(2, null),
            new X(7, null),
            new X(3, null),
            new X(9, null)
        };

        // Print the array before sorting
        System.out.println("Test case for testQuickSortByX()");
        System.out.println("Array before sorting by X:");
        printArray(places);

        // Sort the array by X values
        quickSortByX(places, 0, places.length - 1);

        // Print the array after sorting
        System.out.println("Array after sorting by X:");
        printArray(places);
    }

    public static void testQuickSortByY() {
        // Create an array of Service objects to be sorted
        Service[] services = new Service[]{
            new Service(8, null),
            new Service(4, null),
            new Service(6, null),
            new Service(2, null),
            new Service(10, null)
        };

        // Print the array before sorting
        System.out.println("Test case for testQuickSortByY()");
        System.out.println("Array before sorting by Y:");
        printArray(services);

        // Sort the array by Y values
        quickSortByY(services, 0, services.length - 1);

        // Print the array after sorting
        System.out.println("Array after sorting by Y:");
        printArray(services);
    }

    // Helper method to print array of X objects
    private static void printArray(X[] arr) {
        for (X x : arr) {
            System.out.print(x.getValue() + " ");
        }
        System.out.println();
    }

    // Helper method to print array of Service objects
    private static void printArray(Service[] arr) {
        for (Service service : arr) {
            System.out.print(service.getValue() + " ");
        }
        System.out.println();
    }

    // Quick sort by X values
    private static void quickSortByX(X[] places, int low, int high) {
        if (low < high) {
            int pi = partitionByX(places, low, high);
            quickSortByX(places, low, pi - 1);
            quickSortByX(places, pi + 1, high);
        }
    }

    // Partition by X value
    private static int partitionByX(X[] places, int low, int high) {
        int pivot = places[high].getValue();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (places[j] != null && places[j].getValue() < pivot) {
                i++;
                swapX(places, i, j);
            }
        }
        swapX(places, i + 1, high);
        return i + 1;
    }

    // Swap X objects
    private static void swapX(X[] places, int i, int j) {
        X temp = places[i];
        places[i] = places[j];
        places[j] = temp;
    }

    // Quick sort by Y values
    private static void quickSortByY(Service[] places, int low, int high) {
        if (low < high) {
            int pi = partitionByY(places, low, high);
            quickSortByY(places, low, pi - 1);
            quickSortByY(places, pi + 1, high);
        }
    }

    // Partition by Y value
    private static int partitionByY(Service[] places, int low, int high) {
        Service pivotItem = places[high];
        if (pivotItem == null) {
            return low;
        }
    
        int pivot = pivotItem.getValue();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (places[j] != null && places[j].getValue() < pivot) {
                i++;
                swapY(places, i, j);
            }
        }
        swapY(places, i + 1, high);
        return i + 1;
    }    

    // Swap Service objects
    private static void swapY(Service[] places, int i, int j) {
        Service temp = places[i];
        places[i] = places[j];
        places[j] = temp;
    }

    public static void testUniqueCoor() {
        // Test case 1: Coordinate is unique
        double[][] foundCoordinate1 = {{1, 2}, {3, 4}, {5, 6}};
        int coordinateX1 = 7;
        int coordinateY1 = 8;
        boolean result1 = Map2D.uniqueCoor(foundCoordinate1, coordinateX1, coordinateY1);
        if (!result1) {
            System.out.println("Test case 1 in testUniqueCoor() failed, the coordinate is unique, but method returned false");
            return;
        }
    
        // Test case 2: Coordinate is not unique
        double[][] foundCoordinate2 = {{1, 2}, {3, 4}, {5, 6}};
        int coordinateX2 = 3;
        int coordinateY2 = 4;
        boolean result2 = Map2D.uniqueCoor(foundCoordinate2, coordinateX2, coordinateY2);
        if (result2) {
            System.out.println("Test case 2 in testUniqueCoor() failed, the coordinate is not unique, but method returned true");
            return;
        }
    
        System.out.println("All test cases in testUniqueCoor() passed");
    }

    public static void testContainServiceType() {
        // Test case 1: Target service is present
        Service serviceObject1 = new Service(0, new String[]{"hotel", "park", "school"});
        String targetService1 = "park";
        boolean result1 = Map2D.containServiceType(serviceObject1, targetService1);
        if (!result1) {
            System.out.println("Test case 1 in testContainServiceType() failed, the target service is present, but method returned false");
            return;
        }
    
        // Test case 2: Target service is not present
        Service serviceObject2 = new Service(0, new String[]{"hotel", "park", "school"});
        String targetService2 = "gym";
        boolean result2 = Map2D.containServiceType(serviceObject2, targetService2);
        if (result2) {
            System.out.println("Test case 2 in testContainServiceType() failed, the target service is not present, but method returned true");
            return;
        }
    
        System.out.println("All test cases in testContainServiceType() passed");
    }
    
    public static void testFindNearbyService() {
        // Test data
        // Test should result in found service type the target service type will be "park"
        String[] typeTest1 = new String[]{"gym", "park"};
        String[] typeTest2 = new String[]{"hotel", "park"};

        Service serviceTest1 = new Service(0, typeTest1);
        Service serviceTest2 = new Service(0, typeTest2);


        X[] targetArrayX = {
            new X(1, new Service[]{serviceTest1}),
            new X(2, new Service[]{serviceTest2}),
        };
        int[] centerCoordinate = {0, 0};
        String targetService = "park";
        int[] boundaryY = {0, 10}; // Example boundary
        int[] farthestCoor = {0, 0}; // Example farthest coordinate
    
        // Call the findNearbyService method
        double[][] result = Map2D.findNearbyService(targetArrayX, centerCoordinate, targetService, boundaryY, farthestCoor);
    
        // Test case 1: Check dimensions of the result
        if (result.length != 50 || result[0].length != 3) {
            System.out.println("Test case 1 in testFindNearbyService() failed, incorrect format of the result array");
            return;
        }
    
        // Test case 2: Check if coordinates with the specified service type are included
        boolean containsTargetService = false;
        for (double[] coordinate : result) {
            if (coordinate[2] != 0) {
                for (X x : targetArrayX) {
                    for (Service service : x.getService()) {
                        if (Map2D.containServiceType(service, targetService)) {
                            containsTargetService = true;
                            break;
                        }
                    }
                }
            }
        }
        if (!containsTargetService) {
            System.out.println("Test case 2 in testFindNearbyService() failed, coordinates with the target service are missing");
            return;
        }

        System.out.println("All test cases in testFindNearbyService() passed");
    }

}



