package Map2D_ADT;

import java.util.Random;

public class Testing {
    public static void sortByCoordinates(Place[] places) {
        // Initialize PlaceByCo array to hold places categorized by x-coordinate
        Place[][] PlaceByCo = new Place[10][];

        // Categorize places by x-coordinate
        for (Place place : places) {
            int x = place.getX();
            if (PlaceByCo[x] == null) {
                PlaceByCo[x] = new Place[places.length]; // Assuming worst case
            }
            // Add place to the corresponding array in PlaceByCo
            for (int i = 0; i < places.length; i++) {
                if (PlaceByCo[x][i] == null) {
                    PlaceByCo[x][i] = place;
                    break;
                }
            }
        }

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
        sortByCoordinates(allPlaces);
    }
}

