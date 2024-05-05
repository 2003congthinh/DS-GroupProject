package Map2D_ADT;

import java.util.Random;

public class SortAndSearch {
    // Quick sort by X
    private static void quickSortByX(X[] places, int low, int high) {
        if (low < high) {
            int pi = partitionByX(places, low, high);
            quickSortByX(places, low, pi - 1);
            quickSortByX(places, pi + 1, high);
        }
    }

    // Partition
    private static int partitionByX(X[] places, int low, int high) {
        int pivot = places[high].getX();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // Skip null elements
            if (places[j] != null && places[j].getX() < pivot) {
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
    private static void quickSortByY(Y[] places, int low, int high) {
        if (low < high) {
            int pi = partitionByY(places, low, high);
            quickSortByY(places, low, pi - 1);
            quickSortByY(places, pi + 1, high);
        }
    }

    // Partition
    private static int partitionByY(Y[] places, int low, int high) {
        int pivot = places[high].getY();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // Skip null elements
            if (places[j] != null && places[j].getY() < pivot) {
                i++;
                swapY(places, i, j);
            }
        }
        swapY(places, i + 1, high);
        return i + 1;
    }    

    // Swap places
    private static void swapY(Y[] places, int i, int j) {
        Y temp = places[i];
        places[i] = places[j];
        places[j] = temp;
    }
    public static void main(String[] args) {
        // // Sample data
        // String[] services1 = {"Service1", "Service2"};
        // String[] services2 = {"Service3", "Service4"};

        // // Creating Y objects
        // Y y1 = new Y(10, services1);
        // Y y2 = new Y(20, services2);

        // // Creating X object with array of Y objects
        // X x1 = new X(5, new Y[]{y1});
        // X x2 = new X(15, new Y[]{y2});
        // Accessing and printing data

        // System.out.println("X: " + x1.x);
        // for (Y y : x1.y) {
        //     System.out.println("  Y: " + y.y);
        //     for (String service : y.service) {
        //         System.out.println("    Service: " + service);
        //     }
        // }

        // System.out.println("X: " + x2.x);
        // for (Y y : x2.y) {
        //     System.out.println("  Y: " + y.y);
        //     for (String service : y.service) {
        //         System.out.println("    Service: " + service);
        //     }
        // }

        Random rand = new Random();

        String[] serviceTypes = {"Food stall", "Paper desk", "Merchandise", "Drink shop", "Book store", "Gas station", "School", "Restaurant", "Gym", "Arcade"};
        // Generate places
        X[] ObjX = new X[15];
        for (int i = 0; i < 15; i++) {
            int x = rand.nextInt(10);
            Y[] ObjY = new Y[10];
            for (int j = 0; j < 10; j++) {
                int y = rand.nextInt(10);
                String[] services = {serviceTypes[rand.nextInt(serviceTypes.length)], serviceTypes[rand.nextInt(serviceTypes.length)]};
                Y Y = new Y(y, services);
                ObjY[j] = Y;
            }
            X X = new X(x, ObjY);
            ObjX[i] = X;
        }
        System.out.println("\n"+"Before sort"+"\n");
        for (X x : ObjX) {
            System.out.println(x.getX());
        }
        quickSortByX(ObjX, 0, ObjX.length - 1);
        System.out.println("\n"+"After sort"+"\n");
        for (X x : ObjX) {
            System.out.println(x.getX());
        }
    }
}


class X{
    int x;
    Y[] y;

    public X(int x, Y[] y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Y[] getY() {
        return y;
    }

    public void setY(Y[] y) {
        this.y = y;
    }

    @Override
    public String toString() {
        for (Y Y: y) {
            System.out.println(Y);
        }
        return "X [x=" + x + "]";
    }
}

class Y{
    int y;
    String[] services;

    public Y(int y, String[] services) {
        this.y = y;
        this.services = services;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    @Override
    public String toString() {
        for (String service: services) {
            System.out.println(service);
        }
        return "Y [y=" + y + "]";
    }
}