package Map2D_ADT;

import java.util.Random;

public class SortAndSearch {
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
                int index = size;
                for (int i = 0; i < size; i++) {
                    int valueY = placeSameX.getService()[i].getValue();
                    if (valueY > y) {
                        index = i;
                        break;
                    }
                };
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
        int index = places.length;
        for (int i = 0; i < places.length; i++) {
            if (places[i].getValue() > x) {
                index = i;
                break;
            }
        };
        places = addPlacesToNullX(index, newPlacesX, places);
        return places;
    };

    public static X[] removePlace(int x, int y, X[] places) {
        for (X placeSameX : places) {
            if (placeSameX.getValue() == x) {
                // Find index of the Y object with the specified y value
                int index = -1;
                for (int i = 0; i < placeSameX.getService().length; i++) {
                    if (placeSameX.getService()[i].getValue() == y) {
                        index = i;
                        break;
                    }
                }
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
                // Return the modified places array
                return places;
            }
        }
    
        // If the X object with the specified x value is not found, return the original places array
        return places;
    }

    // Client
    public static void main(String[] args) {
        Random rand = new Random();
        // Boundary for X and Y coordinates
        int bound = 15;

        // Service types for random selection
        String[] serviceTypes = {"Park", "Cafe", "Library", "Hospital", "Gallery", "Theater", "Hotel", "Gym", "Zoo", "School"};

        // Number of place to generate
        int num_Places = 40; 

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

            // Add new place manually for testing purposes
            // if (i == 0){
            //     Service[] y = new Service[] {
            //         new Service(100, new String[]{"Test"}),
            //         new Service(200, new String[]{"Test"}),
            //         new Service(300, new String[]{"Test"}),
            //     };
            //     x_coor.setService(y);
            //     array_x[i] = x_coor;
            // }

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

        System.out.println("Before remove dups");
        for (int i = 0; i < array_x.length; i++) {
            System.out.println(array_x[i].toString());
        }

        array_x = removeDupX(array_x); // remove duplicates from array of object X

        System.out.println("After remove dups");
        for (int i = 0; i < array_x.length; i++) {
            System.out.println(array_x[i].toString());
        }

        // remove a place
        // removePlace(100, 100, array_x);
        // for (int i = 0; i < array_x.length; i++) {
        //     System.out.println(array_x[i].toString());
        // }

    }
}
