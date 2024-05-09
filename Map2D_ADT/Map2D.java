package Map2D_ADT;

import java.util.Random;
import java.util.Scanner;

public class Map2D {
    // Quick sort array_x
    private static void quickSortByX(X[] places, int low, int high) {
        if (low < high) {
            int pi = partitionByX(places, low, high);
            quickSortByX(places, low, pi - 1);
            quickSortByX(places, pi + 1, high);
        }
    }

    // Partition
    private static int partitionByX(X[] places, int low, int high) {
        int pivot = places[high].getValue();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // Skip null elements
            if (places[j] != null && places[j].getValue() < pivot) {
                i++;
                swapX(places, i, j);
            }
        }
        swapX(places, i + 1, high);
        return i + 1;
    }    

    // Swap places
    private static void swapX(X[] places, int i, int j) {
        X temp = places[i];
        places[i] = places[j];
        places[j] = temp;
    }

    // Quick sort by Y
    private static void quickSortByY(Service[] places, int low, int high) {
        if (low < high) {
            int pi = partitionByY(places, low, high);
            quickSortByY(places, low, pi - 1);
            quickSortByY(places, pi + 1, high);
        }
    }

    // Partition
    private static int partitionByY(Service[] places, int low, int high) {
        // int pivot = places[high].getValue();
        Service pivotItem = places[high];
        if (pivotItem == null) {
            return low;
        }
    
        int pivot = pivotItem.getValue();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // Skip null elements
            if (places[j] != null && places[j].getValue() < pivot) {
                i++;
                swapY(places, i, j);
            }
        }
        swapY(places, i + 1, high);
        return i + 1;
    }    

    // Swap places
    private static void swapY(Service[] places, int i, int j) {
        Service temp = places[i];
        places[i] = places[j];
        places[j] = temp;
    }

    // Generating place
    // Random X object
    public static X generate_x(int bound) {
        Random rand = new Random();

        int value = rand.nextInt(bound);
        Service[] service = new Service[1];

        X x = new X(value, service);

        return x;
    }

    // Random Service object
    public static Service generate_service(int bound, String[] serviceType) {
        Random rand = new Random();

        int value = rand.nextInt(2,bound);
        int randomIndex = rand.nextInt(serviceType.length);
        String[] randomService = new String[1];

        randomService[0] = serviceType[randomIndex];

        Service service = new Service(value, randomService);

        return service;
    }

    // Return array with no duplicates
    public static X[] removeDupX(X[] array_x) {
        // Unique will be the new length 
        int unique = 1; // the first element will alway be unique
        for (int i = 1; i < array_x.length; i++) {
            if (array_x[i].getValue() != array_x[i - 1].getValue()) {
                unique++;
            }
        }

        X[] newArray = new X[unique];
        newArray[0] = array_x[0];
        int index = 0;

        for (int i = 0; i < array_x.length - 1; i++) {
            if (array_x[i + 1].getValue() != newArray[index].getValue()) {
                newArray[++index] = array_x[i + 1];
            }
        }
        return newArray;
    }

    public static Object[] copyArrayObject(Object[] source, int startSource, Object[] destination, int startDes, int length) {
        for (int i = 0; i < length; i++) {
            Object copyObject = source[startSource + i];
            destination[startDes + i] = copyObject;
        }

        return destination;
    }

    public static double findDistance(int[] coor1, int[] coor2) {
        int x1 = coor1[0];
        int y1 = coor1[1];
        int x2 = coor2[0];
        int y2 = coor2[1];
    
        double squareX = (double)(x2 - x1) * (x2 - x1);
        double squareY = (double)(y2 - y1) * (y2 - y1);
    
        double distance = Math.sqrt(squareX + squareY);
    
        return distance;
    }

    // Return Start and End boundary index
    public static int[] findBound(int center, int length) {
        int[] target = new int[2];
        if (length % 2 != 0) {
            length--;
        }

        length = length / 2;
        target[0] = center - length;
        target[1] = center + length;

        return target;  
    }
    
    // Return the Start and End index of search range for array_x
    public static int[] binarySearch(int[] boundary, X[] arrayX) {
        int[] notFound = new int[0];
        // If start boundary is larger than the largest value of the arrayX, return empty array - notFound
        if (boundary[0] > arrayX[arrayX.length-1].getValue()) {
            return notFound;
        }
        // If end boundary is smaller than the smallest value of the arrayX, return empty array - notFound
        if (boundary[1] < arrayX[0].getValue()) {
            return notFound;
        }

        int[] result = new int[2];

        // Search for start index
        int startIndex = searchHigher(boundary[0], arrayX);

        // Search for end index
        int endIndex = searchLower(boundary[1], arrayX);

        result[0] = startIndex;
        result[1] = endIndex;

        return result;
    }

    // Return the index that value is >= target X boundary (Start index)
    public static int searchHigher(int target, X[] arrayX) {
        int low = 0;
        int high = arrayX.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = arrayX[mid].getValue();

            if (target == midValue) {
                return mid;
            } else if (midValue - target > 0) {
                high = mid - 1;
            } else if (midValue - target < 0) {
                low = mid + 1;
            }   
        }
        
         /*
          * When come down here, that means there is no value = target
          but we need to find the value is >= target, so
          increment low until the value of index low is greater than the target
          */
        while (arrayX[low].getValue() < target) {
            low++;
        }
        return low;
    }

    // Return the index that value is <= target X boundary (End index)
    public static int searchLower(int target, X[] arrayX) {
        int low = 0;
        int high = arrayX.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = arrayX[mid].getValue();

            if (target == midValue) {
                return mid;
            } else if (midValue - target > 0) {
                high = mid - 1;
            } else if (midValue - target < 0) {
                low = mid + 1;
            }   
        }
        
         /*
          * When come down here, that means there is no value = target
          but we need to find the value is <= target, so
          Decrement high until the value of index high is lower than the target
          */
        while (arrayX[high].getValue() > target) {
            high--;
        }
        return high;
    }

    // return true if the coordinate is unique among the foundCoordinate array
    public static boolean uniqueCoor(double[][] foundCoordinate, int coordinateX, int coordinateY) {
        for (double[] coor : foundCoordinate) {
            if (coor[0] == coordinateX && coor[1] == coordinateY) {
                return false;
            }
        }
        return true;
    }

    // return true if serviceType is equals to targetService
    public static boolean containServiceType(Service serviceObject, String targetService) {
        for (String serviceType : serviceObject.getService()) {
            // get coordinate with targetService servie type only
            if (serviceType.equals(targetService)) {
                return true;
            }
        }
        return false;
    }

    // Return 2d array contains 50 arrays with X, Y and Distance value, with the shortest Distance to the centerCoordinate
    public static double[][] findNearbyService(X[] targetArrayX, int[] centerCoordinate, String targetService, int[] boundaryY, int[] farthestCoor) {
        double[][] foundCoordinate = new double[50][3]; // Format : [X_Coordinate, Y_Coordinate, Distance]
        // Find all coordinates with the same targetService type
        for (X objectX : targetArrayX) {
            int[] rangeY = objectX.binarySearch(boundaryY);
            if (rangeY.length == 0) {
                continue; // skip this one because there is no available Y
            }
            int copyY = rangeY[1] - rangeY[0] + 1; // end index - start index 
            Object[] targetArrayObject = new Service[copyY];

            targetArrayObject = copyArrayObject(objectX.getService(), rangeY[0], targetArrayObject, 0, copyY);
            Service[] targetArrayService = (Service[]) targetArrayObject; // all valid Service object store here
            
            for (Service serviceObject : targetArrayService) {
                if (!containServiceType(serviceObject, targetService)) {
                    continue;
                }
                int[] coor = new int[]{objectX.getValue(), serviceObject.getValue()};
                double distance = findDistance(coor, centerCoordinate); // calculate distance between the current coor and centerCoordinate
                double[] farthestDistance = new double[]{0, 0}; // Store the index and the distance value of the farthest coordinate from the foundCoordinate array
                double[] coordinate = new double[]{objectX.getValue(), serviceObject.getValue(), distance};
                for (int i = 0; i < foundCoordinate.length; i++) {
                    if (foundCoordinate[i][0] == 0 && foundCoordinate[i][1] == 0) {
                        if (uniqueCoor(foundCoordinate, objectX.getValue(), serviceObject.getValue())) {
                            foundCoordinate[i] = coordinate;
                        }
                    }

                    if (foundCoordinate[i][2] > farthestDistance[1]) {
                        farthestDistance[0] = i;
                        farthestDistance[1] = foundCoordinate[i][2];
                    }

                }
                // if 
                if (distance < farthestDistance[1]) {
                    if (uniqueCoor(foundCoordinate, objectX.getValue(), serviceObject.getValue())) {
                        int index = (int) farthestDistance[0];
                        foundCoordinate[index] = coordinate;
                    }
                }
            }
        }
        return foundCoordinate;
    }

    // Return back what function findNearbyService() return
    public static double[][] search(int x_coor, int y_coor, int width, int height, String targetService, X[] arrayX) {
        double[][] foundCoordinate = new double[50][];
        // Get the boundary index for X and Y coordinates
        int[] boundaryX = findBound(x_coor, width);
        int[] boundaryY = findBound(y_coor, height);

        // Get the target index of X coordinate, the Start and End index for our array_x
        int[] rangeX = binarySearch(boundaryX, arrayX);
        if (rangeX.length == 0) {
            System.out.println("No service found in the given boundary.");
            return foundCoordinate;
        }

        int copyX = rangeX[1] - rangeX[0] + 1;
        Object[] targetArrayObject = new X[copyX];
        targetArrayObject = copyArrayObject(arrayX, rangeX[0], targetArrayObject, 0, copyX);
        X[] targetArrayX = (X[]) targetArrayObject;

        int farthestX = x_coor + width + 1;
        int farthestY = y_coor + height + 1;
        int[] farthestCoor = new int[]{farthestX, farthestY};
        int[] centerCoordinate = new int[]{x_coor, y_coor};

        foundCoordinate = findNearbyService(targetArrayX, centerCoordinate, targetService, boundaryY, farthestCoor);

        return foundCoordinate;
    }

    //add service to list of services
    public static String[] addService(String service, String[] services) {
        int size = services.length;
        String[] newServices = new String[size + 1];
        if (size == 0) {
            newServices[0] = service;
        } else {
            for (int i = 0; i < size; i++) {
                newServices[i] = services[i];
            };
            newServices[size] = service;
        };
        return newServices;
    }

    public static X[] addPlacesToNullX(int index, X placesSameX, X[] places) {
        int size = places.length;
        X[] newPlaces = new X[size + 1];
        int count = 0;
        if (size == 0) {
            newPlaces[0] = placesSameX;
        } else {
            for (int i = 0; i < newPlaces.length; i++) {
                if (i == index) {
                    newPlaces[i] = placesSameX;
                    continue;
                }
                newPlaces[i] = places[count];
                count++;
            };
        };
        return newPlaces;
    };

    // [objx, objx, objx,...]. objx = x + [objy, objy, objy,...]. objy = y + String[]service
    public static X[] addPlace(int x, int y, String[] services, X[] places) {
        // If x is not null
        for (X placeSameX : places) {
            if (placeSameX.getValue() == x) {
                // Find index of place
                int size = placeSameX.getService().length;
                int index = placeSameX.searchHigher(y);

                // Create a new array
                Service[] newPlacesY = new Service[size+1];
                // Insert elements after the index
                for (int j = size; j > index; j--) { 
                    newPlacesY[j] = placeSameX.getService()[j-1];
                };
                newPlacesY[index] = new Service(y, services);
                // Insert elements before the index
                for (int j = 0; j < index; j++) { 
                    newPlacesY[j] = placeSameX.getService()[j];
                };
                placeSameX.setService(newPlacesY);
                return places;
            };
        };

        // If x is null
        Service[] place = {new Service(y, services)};
        X newPlacesX = new X(x, null);
        newPlacesX.setService(place);
        int index = searchLower(x, places);
        places = addPlacesToNullX(index, newPlacesX, places);
        return places;
    };

    public static X[] removePlace(int x, int y, X[] places) {
        for (X placeSameX : places) {
            if (placeSameX.getValue() == x) {
                // Delete x if x only have 1 place
                if (placeSameX.getService().length == 1 && placeSameX.getService()[0].getValue() == y){
                    int indexX = searchHigher(x, places);
                    // Create a new array with size - 1
                    X[] newArray = new X[places.length - 1];
                    // Copy elements before the index
                    for (int j = 0; j < indexX; j++) {
                        newArray[j] = places[j];
                    }
                    // Copy elements after the index
                    for (int j = indexX + 1; j < places.length; j++) {
                        newArray[j - 1] = places[j];
                    }
                    // Set the modified Y array to the X object
                    places = newArray; 
                } else {
                    // Find index of the Y object with the specified y value
                    int indexOfX = searchHigher(x, places);
                    int index = places[indexOfX].searchHigher(y);
                    // If the Y object with the specified y value is found
                    if (index != -1) {
                        // Create a new array with size - 1
                        Service[] newPlacesY = new Service[placeSameX.getService().length - 1];
                        // Copy elements before the index
                        for (int j = 0; j < index; j++) {
                            newPlacesY[j] = placeSameX.getService()[j];
                        }
                        // Copy elements after the index
                        for (int j = index + 1; j < placeSameX.getService().length; j++) {
                            newPlacesY[j - 1] = placeSameX.getService()[j];
                        }
                        // Set the modified Y array to the X object
                        placeSameX.setService(newPlacesY);
                    }
                }
                // Return the modified places array
                return places;
            }
        }
        // If the X object with the specified x value is not found, return the original places array
        return places;
    }

    public static void editPlace(int x, int y, String[] services, X[] places) {
        int indexOfX = searchHigher(x, places);
        int indexOfY = places[indexOfX].searchHigher(y);
        places[indexOfX].getService()[indexOfY].setService(services);
    };

    public static void main(String[] args) {
        Random rand = new Random();
        // Boundary for X and Y coordinates
        int bound = 10000000;

        // Service types for random selection
        String[] serviceTypes = {"Park", "Cafe", "Library", "Hospital", "Gallery", "Theater", "Hotel", "Gym", "Zoo", "School"};

        // Number of place to generate
        int num_Places = 100000000; 

        int total_x = rand.nextInt(num_Places) + 1; // remove 0
        int remainder = num_Places - total_x; // number of extra Y need to generate for same X coordinate

        X[] array_x = new X[total_x];
        
        for (int i = 0; i < total_x; i++) {
            X x_coor = generate_x(bound); // create Object X
            int total_y = rand.nextInt(remainder + 1) + 1; // each X guarantee to have at least 1 Y, so remove 0 (+1)
            Service[] serviceList = new Service[remainder+1];
            remainder -= total_y - 1; // -1 to remove the guarantee Y

            // Check if this is the last iteration 
            // if there is still remainder needed to generate
            if (i == total_x - 1 && remainder != 0) {
                total_y += remainder;
            }

            // Create Object Service
            for (int j = 0; j < total_y; j++) {
                Service service = generate_service(bound, serviceTypes);
                serviceList[j] = service;
            }
            
            x_coor.setService(serviceList);

            x_coor.removeNull(); // Remove null elements from array of object Service
            quickSortByY(x_coor.getService(), 0, x_coor.getService().length - 1); // sort Service objects by value (Y)
            x_coor.removeDupService(); // remove duplicates Service object
            array_x[i] = x_coor;
        }

        quickSortByX(array_x, 0, array_x.length - 1); // sort X objects by value (X)
        array_x = removeDupX(array_x); // remove duplicates from array of object X

        boolean exit = false;
        while (!exit) {
            System.out.println("\n\n-------------------------------------Menu-------------------------------------");
            System.out.println("1. Search for a place.");
            System.out.println("2. Exit.");
            System.out.println("------------------------------------------------------------------------------");
            System.out.print("Select option: ");

            Scanner scanner1 = new Scanner(System.in);
            String menuInput = scanner1.nextLine();

            switch (menuInput) {
                case "1": {
                    try {
                        Scanner scanner3 = new Scanner(System.in);
                        System.out.println("The x-coordinate of the searching point: ");
                        int xInput = scanner3.nextInt();
                        System.out.println("The y-coordinate of the searching point: ");
                        int yInput = scanner3.nextInt();

                        // Official search

                        System.out.println("The width of the bounding rectangle: ");
                        int wInput = scanner3.nextInt();
                        System.out.println("The height of the bounding rectangle: ");
                        int hInput = scanner3.nextInt();
                        // Consume newline character
                        scanner3.nextLine();

                        System.out.println("Choose some of services in the below types:");
                        for (String service : serviceTypes) {
                            System.out.printf("%s\n", service);
                        }
                        System.out.println("The service of the places you want to find: ");
                        String sInput = scanner3.nextLine();

                        boolean exitLoop = false;
                        while (!exitLoop) {
                            double[][] test12 = search(xInput, yInput, wInput, hInput, sInput, array_x); // example search
                            boolean found = false;
                            for (int i = 0; i < test12.length; i++) {
                                if (test12[i] != null) {
                                    System.out.println("Place number:" + i + " - X = " + test12[i][0] + " , Y = " + test12[i][1] + " - Dis = " + test12[i][2]);
                                    found = true;
                                }
                            }
                    
                            if (!found) {
                                System.out.println("There is no Service Type:"+"near the center");
                            }
                            // Testing REMOVE and ADD and EDIT

                            System.out.println("You want to REMOVE(1) or EDIT(2) or ADD(3) place (to quit, enter \"4\"):");
                            int choice = scanner3.nextInt();
                            if (choice == 1) {
                                System.out.println("The x-coordinate of the place: ");
                                int xRemove = scanner3.nextInt();
                                System.out.println("The y-coordinate of the place: ");
                                int yRemove = scanner3.nextInt();
                                array_x = removePlace(xRemove, yRemove, array_x);
                            } else if (choice == 2) {
                                System.out.println("The x-coordinate of the place: ");
                                int xEdit = scanner3.nextInt();
                                System.out.println("The y-coordinate of the place: ");
                                int yEdit = scanner3.nextInt();
                                String[] services = new String[0];
                                System.out.println("Choose some of services in the below types:");
                                for (String service : serviceTypes) {
                                    System.out.printf("%s\n", service);
                                };
                                System.out.println("Types of service (Press q to quit adding new service):");
                                scanner3.nextLine(); // to consume the new line
                                // create a list of services
                                while (true) {
                                    String serviceInput = scanner3.nextLine();// to quit the loop
                                    if (serviceInput.equals("q")) {
                                        break;
                                    }
                                    // add a new service to list
                                    services = addService(serviceInput, services);
                                };
                                editPlace(xEdit, yEdit, services, array_x);
                            } else if (choice == 3) {
                                System.out.println("The x-coordinate of the place: ");
                                int xAdd = scanner3.nextInt();
                                System.out.println("The y-coordinate of the place: ");
                                int yAdd = scanner3.nextInt();
                                String[] services = new String[0];
                                System.out.println("Choose some of services in the below types:");
                                for (String service : serviceTypes) {
                                    System.out.printf("%s\n", service);
                                }
                                System.out.println("Types of service (Press q to quit adding new service):");
                                scanner3.nextLine(); // to consume the new line
                                while (true) {
                                    String serviceInput = scanner3.nextLine();// to quit the loop
                                    if (serviceInput.equals("q")) {
                                        break;
                                    }
                                    // create a new list of services
                                    services = addService(serviceInput, services);
                                };
                                array_x = addPlace(xAdd, yAdd, services, array_x);
                            } else if (choice == 4) {
                                break;
                            } 
                        }
                    } catch (Exception e) {
                        System.out.println("Please enter again. Error log: " + e.getMessage());
                    }
                    break;
                }
                case "2": {
                    exit = true;
                    System.out.println("Exiting the program.");
                    break;
                }
                default:
                    System.out.println("Invalid option. Please select again.");
                    break;
            }
        }
    }
}

