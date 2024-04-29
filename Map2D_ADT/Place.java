package Map2D_ADT;

public class Place {
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
