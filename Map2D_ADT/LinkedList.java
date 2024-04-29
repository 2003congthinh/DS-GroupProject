package Map2D_ADT;

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

public class LinkedList {
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

    // Wrong way to delete Place
    
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

    // public void removeThePlace(int x, int y) {
    //     ListNode current = head;
    //     while (current != null) {
    //         Place place = current.getData();
    //         if (place.getX() == x && place.getY() == y) {
    //             LinkedList list = new LinkedList();
    //             list.remove(place);
    //             System.out.println("Remove successfully");;
    //         }
    //         current = current.getNext();
    //     }
    //     System.out.println("No place found"); // If the place with given coordinates is not found
    // }

    public void removeThePlace(int x, int y) {
        // if (head == null) {
        //     System.out.println("No places in the list");
        //     return;
        // }
    
        if (head.getData().getX() == x && head.getData().getY() == y) {
            head = head.getNext();
            System.out.println("Place removed successfully");
            return;
        }
    
        ListNode prev = head;
        ListNode current = head.getNext();
    
        while (current != null) {
            if (current.getData().getX() == x && current.getData().getY() == y) {
                prev.setNext(current.getNext());
                System.out.println("Place removed successfully");
                return;
            }
            prev = current;
            current = current.getNext();
        }
    
        System.out.println("No place found with the given coordinates");
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
