package Map2D_ADT;

import java.util.Random;

public class Testing {
    // Initialize PlaceByCo array to hold places categorized by x-coordinate
    // public static Place[][] PlaceByCo = new Place[0][];                                                        // Mind the number, need to be equal to total number of x coordinates

// public static void sortByCoordinates(Place[] places) {
//     // Categorize places by x-coordinate
    // for (Place place : places) {
    //     int x = place.getX();
    //     if (PlaceByCo[x] == null) {
    //         PlaceByCo[x] = new Place[places.length]; // Assuming worst case
    //     }
    //     // Add place to the corresponding array in PlaceByCo
    //     for (int i = 0; i < places.length; i++) {
    //         if (PlaceByCo[x][i] == null) {
    //             PlaceByCo[x][i] = place;
    //             break;
    //         }
    //     }
    // }

    //     // Print sorted places
    //     for (Place[] coArray : PlaceByCo) {
    //         if (coArray != null) {
    //             for (Place place : coArray) {
    //                 if (place != null) {
    //                     // System.out.println("x: " + place.getX() + ", y: " + place.getY());
    //                     System.out.println(place);
    //                 }
    //             }
    //         }
    //     }
    // }

    // public static void sortByCoordinates(Place[] places) {
    //     // Categorize places by x-coordinate
    //     for (Place place : places) {
    //         int x = place.getX();
    //         // Ensure that PlaceByCo[x] is initialized
    //         if (PlaceByCo[x] == null) {
    //             PlaceByCo[x] = new Place[1]; // Start with an array of size 1
    //         } else {
    //             // Resize the array to accommodate more places
    //             Place[] newArray = new Place[PlaceByCo[x].length + 1];
    //             System.arraycopy(PlaceByCo[x], 0, newArray, 0, PlaceByCo[x].length);
    //             PlaceByCo[x] = newArray;
    //         }
    //         // Add place to the end of the array in PlaceByCo[x]
    //         PlaceByCo[x][PlaceByCo[x].length - 1] = place;
    //     }
    
    //     // Sort each subarray by y-coordinate
    //     for (int i = 0; i < 100; i++) {                                                                     // Mind the number, need to be equal to total number of x coordinates
    //         if (PlaceByCo[i] != null) {
    //             quickSort(PlaceByCo[i], 0, PlaceByCo[i].length - 1, "y");
    //         }
    //     }
    // }
    
    // Group objects by their x-coordinate
    public static Place[][] groupByXCoordinate(Place[] allPlaces) {
        int maxCoordinate = 0;
        // Find the maximum x-coordinate
        for (Place place : allPlaces) {
            if (place.getX() > maxCoordinate) {
                maxCoordinate = place.getX();
            }
        }
        // Create an array to hold the groups
        Place[][] groupedArr = new Place[maxCoordinate + 1][];
        // Initialize each group as an empty array
        for (int i = 0; i <= maxCoordinate; i++) {
            groupedArr[i] = new Place[0];
        }
        // Add each place to its corresponding group
        for (Place place : allPlaces) {
            int x = place.getX();
            Place[] group = groupedArr[x];
            Place[] newGroup = new Place[group.length + 1];
            System.arraycopy(group, 0, newGroup, 0, group.length);
            newGroup[group.length] = place;
            groupedArr[x] = newGroup;
        }

        // Sort each subarray by y-coordinate
        for (int i = 0; i < groupedArr.length; i++) {
            if (groupedArr[i] != null) {
                quickSort(groupedArr[i], 0, groupedArr[i].length - 1, "y");
            }
        }

        return groupedArr;
    }

    // Quick sort
    private static void quickSort(Place[] places, int low, int high, String co) {
        if (low < high) {
            int pi = partition(places, low, high, co);
            quickSort(places, low, pi - 1, co);
            quickSort(places, pi + 1, high, co);
        }
    }

    // Partition
    private static int partition(Place[] places, int low, int high, String co) {
        int pivot = 0;
        if (co.equals("x")){                                             // Use .equals() instead of "==" when used with string
            pivot = places[high].getX();
        } else if (co.equals("y")){
            pivot = places[high].getY();
        }
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (co.equals("x")){                                             // Use .equals() instead of "==" when used with string
                // Skip null elements
                if (places[j] != null && places[j].getX() < pivot) {
                    i++;
                    swap(places, i, j);
            }
            } else if (co.equals("y")){
                // Skip null elements
                if (places[j] != null && places[j].getY() < pivot) {
                    i++;
                    swap(places, i, j);
                }
            }
        }
        swap(places, i + 1, high);
        return i + 1;
    }    

    // Swap places
    private static void swap(Place[] places, int i, int j) {
        Place temp = places[i];
        places[i] = places[j];
        places[j] = temp;
    }

    public static void printAllPlaces(Place[][] PlaceByCo) {
        // Print sorted places
        for (Place[] coArray : PlaceByCo) {
            if (coArray != null) {
                for (Place place : coArray) {
                    if (place != null) {
                        System.out.println("x: " + place.getX() + ", y: " + place.getY());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();

        String[] serviceTypes = {"Food stall", "Paper desk", "Merchandise", "Drink shop", "Book store", "Gas station", "School", "Restaurant", "Gym", "Arcade"};
        Place[] allPlaces = new Place[50];
        // Generate places
        for (int i = 0; i < 50; i++) {
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            
            String[] services = {serviceTypes[rand.nextInt(serviceTypes.length)], serviceTypes[rand.nextInt(serviceTypes.length)]};
            Place place = new Place(x, y, services);
            allPlaces[i] = place;
        }

        // System.out.println("\n" + "Before sorting" + "\n");
        // for (Place place : allPlaces) {
        //     System.out.println(place);
        // }

        quickSort(allPlaces, 0, allPlaces.length - 1, "x");

        // System.out.println("\n" + "After sorting" + "\n");
        // for (Place place : allPlaces) {
        //     System.out.println(place);
        // }

        Place[][] groupedByX = groupByXCoordinate(allPlaces);
        printAllPlaces(groupedByX);
    }
}



