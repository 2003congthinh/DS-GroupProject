package Map2D_ADT;

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

    // public void remove(Place data) {
    //     if (head == null) {
    //         return;
    //     }
    //     if (head.getData().equals(data)) {
    //         head = head.getNext();
    //         return;
    //     }
    //     ListNode current = head;
    //     while (current.getNext() != null) {
    //         if (current.getNext().getData().equals(data)) {
    //             current.setNext(current.getNext().getNext());
    //             return;
    //         }
    //         current = current.getNext();
    //     }
    // }

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

        String[] serviceTypes = {"Food stall", "Paper desk", "Merchandise", "Drink shop", "Book store", "Gas station", "School", "Restaurant", "Gym", "Arcade"};

        LinkedList list = new LinkedList();

        int serviceCount = serviceTypes.length - 1;
        for (int i = 0; i <= 15; i++) {
            int x = 10 * i;
            int y = 20 * i + 5; // Adjust y to increment with i
            
            String[] services = {serviceTypes[i % serviceCount + 1], serviceTypes[i % serviceCount]};
            Place place = new Place(x, y, services);
            list.add(place);
        }

        list.display();

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

