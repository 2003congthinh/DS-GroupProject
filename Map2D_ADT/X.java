package Map2D_ADT;

class X {
    private int value;
    private Service[] service;

    public X(int value, Service[] service) {
        this.value = value;
        this.service = service;
    }

    // Remove null elements from array of object Service
    public void removeNull() {
        Service[] serviceArray = getService();

        // count will be the length of the new array with no nulls
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

    // Remove duplicated Service object(Y)
    public void removeDupService() {
        // Unique will be the new length 
        int unique = 1; // the first element will alway be unique
        for (int i = 1; i < service.length; i++) {
            if (service[i].getValue() != service[i - 1].getValue()) {
                unique++;
            }
        }

        Service[] newArray = new Service[unique];
        newArray[0] = service[0];
        int index = 0;

        for (int i = 0; i < service.length - 1; i++) {
            if (service[i + 1].getValue() != newArray[index].getValue()) {
                newArray[++index] = service[i + 1];
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
        String result = "";
        for (Service service : service) {
            if (service != null) {
                result += "X Value: " + value + " - Service Object: " + service.toString() + "\n";
            }
        }
        return result;
    }
}
