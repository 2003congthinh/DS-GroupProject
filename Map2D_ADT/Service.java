package Map2D_ADT;

public class Service {
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
        if (service.length > 1) {
            for (String service : service) {
                serviceList += service + ",  ";
            }
        } else {
            serviceList = service[0];
        }
        return "Value Y: " + value + " - Service: " + serviceList +"; ";
    }
}
