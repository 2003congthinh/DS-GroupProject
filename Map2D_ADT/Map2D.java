package Map2D_ADT;

import java.util.Random;
import java.util.Scanner;

public class Map2D {
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

        boolean exit = false;
        while (!exit) {
            System.out.println("\n\n-------------------------------------Menu-------------------------------------");
            System.out.println("1. Add place to the map.");
            System.out.println("2. Edit service or Remove a place.");
            System.out.println("3. Search for a place.");
            System.out.println("4. Exit.");
            System.out.println("------------------------------------------------------------------------------");
            System.out.print("Select option: ");

            Scanner scanner1 = new Scanner(System.in);
            String menuInput = scanner1.nextLine();

            switch (menuInput) {
                case "1": {
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("The x-coordinate of the place: ");
                    int xInput = scanner2.nextInt();
                    System.out.println("The y-coordinate of the place: ");
                    int yInput = scanner2.nextInt();
                    String[] services = new String[0];
                    Place place = new Place(xInput, yInput, services);
                    System.out.println("Choose some of services in the below types:");
                    for (String service : serviceTypes) {
                        System.out.printf("%s\n", service);
                    }
                    System.out.println("Types of service (Press q to quit adding new service):");
                    scanner2.nextLine(); // to consume the new line
                    while (true) {
                        String serviceInput = scanner2.nextLine();// to quit the loop
                        if (serviceInput.equals("q")) {
                            break;
                        }
                        // create a new list of services
                        services = addService(serviceInput, services);
                    };
                    place.setServices(services); // set new services to the place
                    list.add(place);
                    list.display();
                    break;
            
                }
                case "2": {
                    System.out.println("All places:\n");
                    list.display();
                    Scanner scanner3 = new Scanner(System.in);
                    System.out.println("The x-coordinate of the place: ");
                    int xInput = scanner3.nextInt();
                    System.out.println("The y-coordinate of the place: ");
                    int yInput = scanner3.nextInt();
                    System.out.println("You want to REMOVE(1) or EDIT(2) place:");
                    int choice = scanner3.nextInt();
                    if (choice == 1) {
                        list.removeThePlace(xInput, yInput);
                    } else {
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
                        list.editThePlace(xInput, yInput, services);
                    }
                    list.display();
                    break;
                }
                case "3": {
                    
                }
                case "4": {
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

