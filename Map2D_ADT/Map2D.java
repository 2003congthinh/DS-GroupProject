package Map2D_ADT;

class Place {
    int x;
    int y;
    String services; // Set to store services offered at this place
    
    public Place(int x, int y, String services) {
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
    
    public String getServices() {
        return services;
    }

    @Override
    public String toString() {
        return "Place [x=" + x + ", y=" + y + ", services=" + services + "]";
    }
}

class Map2D {
    public static void main(String[] args) {
        Place place = new Place(1000,1000,"Food stall");
        System.out.println(place);
    }
}

