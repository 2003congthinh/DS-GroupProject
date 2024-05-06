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
    public static X generate_x(int bound) {
        Random rand = new Random();

        int value = rand.nextInt(bound);
        Service[] service = new Service[1];

        X x = new X(value, service);

        return x;
    }

    public static Service generate_service(int bound, String[] serviceType) {
        Random rand = new Random();

        int value = rand.nextInt(2,bound);
        int randomIndex = rand.nextInt(serviceType.length);
        String[] randomService = new String[1];

        randomService[0] = serviceType[randomIndex];

        Service service = new Service(value, randomService);

        return service;
    }
    public static void main(String[] args) {
        Random rand = new Random();
        // Boundary for X and Y coordinates
        int bound = 15;

        // Service types for random selection
        String[] serviceTypes = {"Park", "Cafe", "Library", "Hospital", "Gallery", "Theater", "Hotel", "Gym", "Zoo", "School"};

        // Number of places
        int num_Places = 40; 

        int total_x = rand.nextInt(num_Places) + 1; // remove 0
        int remainder = num_Places - total_x; // number of extra Y need to generate for same X coordinate

        X[] array_x = new X[total_x]; // 15 elements
        
        for (int i = 0; i < total_x; i++) {
            X x_coor = generate_x(bound); // create Object X
            int total_y = rand.nextInt(remainder + 1) + 1; // each X guarantee to have at least 1 Y (+1)
            Service[] serviceList = new Service[remainder+1];
            remainder -= total_y - 1; // -1 to remove the guarantee Y

            // Check if this is the last iteration 
            // if there is still remainder needed to generate
            if (i == total_x - 1 && remainder != 0) {
                total_y += remainder;
            }

            // Generate Y and service
            for (int j = 0; j < total_y; j++) {
                Service service = generate_service(bound, serviceTypes);
                serviceList[j] = service;
            }
            
            x_coor.setService(serviceList);
            x_coor.removeNull();
            quickSortByY(x_coor.getService(), 0, x_coor.getService().length - 1);
            array_x[i] = x_coor;
        }

        quickSortByX(array_x, 0, array_x.length - 1);
        for (int i = 0; i < array_x.length; i++) {
            System.out.println(array_x[i].toString());
        }
    }
}


class X {
    private int value;
    private Service[] service;

    public X(int value, Service[] service) {
        this.value = value;
        this.service = service;
    }

    public void removeNull() {
        Service[] serviceArray = getService();

        int count = 0;
        for (Service service : serviceArray) {
            if (service != null) {
                count++;
            }
        }

        Service[] newArray = new Service[count];

        for (int i = 0; i < serviceArray.length; i++) {
            if (serviceArray[i] != null) {
                newArray[i] = serviceArray[i];
            }
        }

        setService(newArray);
    }

    // Getters
    public int getValue() {
        return value;
    }

    public Service[] getService() {
        return service;
    }

    // Setters
    public void setService(Service[] service) {
        this.service = service;
    }

    @Override
    public String toString() {
        String services = "";
        for (Service service : service) {
            if (service != null) {
                // services += service.getValue() +" ";
                // for (int i = 0; i < service.getService().length; i++) {
                //     services += service.getService()[i]+", ";
                // }

                services += service.toString();
            }
        }
        return "X Value: " + value + " - Service: " + services;
    }
}

class Service {
    private int value;
    private String[] service;

    public Service(int value, String[] service) {
        this.value = value;
        this.service = service;
    }

    // Getters
    public int getValue() {
        return value;
    }

    public String[] getService() {
        return service;
    }

    // Setters
    public void setService(String[] service) {
        this.service = service;
    }

    @Override
    public String toString() {
        String serviceList = "";
        for (String service : service) {
            serviceList += service;
        }

        return "Value Y: " + value + " - Service: " + serviceList;
    }
}