package Map2D_ADT;

import java.util.Random;

class Place {
    int x;
    int y;
    String[] services; // Set to store services offered at this place
    
    public Place(int x, int y, String[] services) {
        this.x = x;
        this.y = y;
        this.services = services;
    }
    
    // Getters for coordinates and services
    public int getX() {
        return x;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    public int getY() {
        return y;
    }
    
    public String[] getServices() {
        return services;
    }

    @Override
    public String toString() {
        for (String service: services) {
            System.out.println(service);
        }
        return "Place [x=" + x + ", y=" + y + "]";
    }
}

class ListNode {
    private Place data;
    private ListNode next;

    public ListNode(Place data) {
        this.data = data;
        this.next = null;
    }

    public Place getData() {
        return data;
    }
    
    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}

class LinkedList {
    private ListNode head;

    public LinkedList() {
        this.head = null;
    }

    public void add(Place data) {
        ListNode newNode = new ListNode(data);
        if (head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void remove(Place data) {
        if (head == null) {
            return;
        }
        if (head.getData().equals(data)) {
            head = head.getNext();
            return;
        }
        ListNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                current.setNext(current.getNext().getNext());
                return;
            }
            current = current.getNext();
        }
    }

    public Place getByCoordinates(int x, int y) {
        ListNode current = head;
        while (current != null) {
            Place place = current.getData();
            if (place.getX() == x && place.getY() == y) {
                return place;
            }
            current = current.getNext();
        }
        return null; // If the place with given coordinates is not found
    }

    public Place editThePlace(int x, int y, String[] newServices) {
        ListNode current = head;
        while (current != null) {
            Place place = current.getData();
            if (place.getX() == x && place.getY() == y) {
                place.setServices(newServices);
                return place;
            }
            current = current.getNext();
        }
        return null; // If the place with given coordinates is not found
    }

    public void removeThePlace(int x, int y) {
        ListNode current = head;
        while (current != null) {
            Place place = current.getData();
            if (place.getX() == x && place.getY() == y) {
                LinkedList list = new LinkedList();
                list.remove(place);
                System.out.println("Remove successfully");;
            }
            current = current.getNext();
        }
        System.out.println("No place found");; // If the place with given coordinates is not found
    }

    public void display() {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            System.out.println("\n");
            current = current.getNext();
        }
        System.out.println();
    }
}

public class Map2D {
    public static void main(String[] args) {

        Random rand = new Random();

        String[] serviceTypes = {"Food stall", "Paper desk", "Merchandise", "Drink shop", "Book store", "Gas station", "School", "Restaurant", "Gym", "Arcade"};

        // Generate place
        LinkedList list = new LinkedList();
        Place placeTest = new Place(100, 100, serviceTypes);
        list.add(placeTest);
        for (int i = 0; i <= 15; i++) {
            int x = rand.nextInt(10000000);
            int y = rand.nextInt(10000000);
            
            String[] services = {serviceTypes[rand.nextInt(serviceTypes.length)], serviceTypes[rand.nextInt(serviceTypes.length)]};
            Place place = new Place(x, y, services);
            list.add(place);
        }

        list.display();

        // Pick a specific place
        Place specificPlace = list.getByCoordinates(100, 100);
        if (specificPlace != null) {
            System.out.println("Found place: " + specificPlace);
        } else {
            System.out.println("Place not found.");
        }

        // Edit the fuck out of it
        String[] newServices = {"Merchandise"};
        Place editedPlace = list.editThePlace(100, 100, newServices);
        if (editedPlace != null) {
            System.out.println("Newly edited place: " + editedPlace);
        } else {
            System.out.println("Place not found.");
        }

        // Remove it
        list.removeThePlace(100, 100);
        // try to find it
        if (specificPlace != null) {
            System.out.println("Found place: " + specificPlace);
        } else {
            System.out.println("Place not found.");
        }

        // String[] services1 = {"Food stall", "Merchandise"};
        // Place place1 = new Place(1000,100,services1);

        // String[] services2 = {"City hall", "Clerk desk"};
        // Place place2 = new Place(2000,21000,services2);

        // LinkedList list = new LinkedList();
        // list.add(place1);
        // list.add(place2);
        // list.display();
    }
}

