package Map2D_ADT;

import java.util.Random;

public class Testing {
    // Initialize PlaceByCo array to hold places categorized by x-coordinate
public static Place[][] PlaceByCo = new Place[100][];                                                        // Mind the number, need to be equal to total number of x coordinates

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

    //     // Sort each subarray by y-coordinate
    //     for (int i = 0; i < 10; i++) {
    //         if (PlaceByCo[i] != null) {
    //             quickSortByY(PlaceByCo[i], 0, PlaceByCo[i].length - 1);
    //         }
    //     }

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

    public static void sortByCoordinates(Place[] places) {
        // Categorize places by x-coordinate
        for (Place place : places) {
            int x = place.getX();
            // Ensure that PlaceByCo[x] is initialized
            if (PlaceByCo[x] == null) {
                PlaceByCo[x] = new Place[1]; // Start with an array of size 1
            } else {
                // Resize the array to accommodate more places
                Place[] newArray = new Place[PlaceByCo[x].length + 1];
                System.arraycopy(PlaceByCo[x], 0, newArray, 0, PlaceByCo[x].length);
                PlaceByCo[x] = newArray;
            }
            // Add place to the end of the array in PlaceByCo[x]
            PlaceByCo[x][PlaceByCo[x].length - 1] = place;
        }
    
        // Sort each subarray by y-coordinate
        for (int i = 0; i < 100; i++) {                                                                     // Mind the number, need to be equal to total number of x coordinates
            if (PlaceByCo[i] != null) {
                quickSortByY(PlaceByCo[i], 0, PlaceByCo[i].length - 1);
            }
        }
    }
    
    // Quick sort by y-coordinate
    private static void quickSortByY(Place[] places, int low, int high) {
        if (low < high) {
            int pi = partitionByY(places, low, high);
            quickSortByY(places, low, pi - 1);
            quickSortByY(places, pi + 1, high);
        }
    }

    // Partition by y-coordinate
    private static int partitionByY(Place[] places, int low, int high) {
        int pivot = places[high].getY();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // Skip null elements
            if (places[j] != null && places[j].getY() < pivot) {
                i++;
                swap(places, i, j);
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

    public static void printAllPlaces() {
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
        Place[] allPlaces = new Place[300];
        // Generate places
        for (int i = 0; i < 300; i++) {
            int x = rand.nextInt(100);
            int y = rand.nextInt(100);
            
            String[] services = {serviceTypes[rand.nextInt(serviceTypes.length)], serviceTypes[rand.nextInt(serviceTypes.length)]};
            Place place = new Place(x, y, services);
            allPlaces[i] = place;
        }
        sortByCoordinates(allPlaces);
        printAllPlaces();
    }
}

