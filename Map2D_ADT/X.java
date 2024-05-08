package Map2D_ADT;

class X {
    private int value;
    private Service[] service;

    public X(int value, Service[] service) {
        this.value = value;
        this.service = service;
    }

    // Return the Start and End index of search range for service array
    public int[] binarySearch(int[] boundary) {
        int[] notFound = new int[0];
        // If start boundary is larger than the largest value of the arrayX, return empty array - notFound
        if (boundary[0] > service[service.length-1].getValue()) {
            return notFound;
        }
        // If end boundary is smaller than the smallest value of the arrayX, return empty array - notFound
        if (boundary[1] < service[0].getValue()) {
            return notFound;
        }

        int[] result = new int[2];

        // Search for start index
        int startIndex = searchHigher(boundary[0]);

        // Search for end index
        int endIndex = searchLower(boundary[1]);

        result[0] = startIndex;
        result[1] = endIndex;

        return result;
    }

    // Return the index that value is <= target Y boundary (End index)
    public int searchLower(int target) {
        int low = 0;
        int high = service.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = service[mid].getValue();

            if (target == midValue) {
                return mid;
            } else if (midValue - target > 0) {
                high = mid - 1;
            } else if (midValue - target < 0) {
                low = mid + 1;
            }   
        }
        
         /*
          * When come down here, that means there is no value = target
          but we need to find the value is <= target, so
          Decrement high until the value of index high is lower than the target
          */
        while (service[high].getValue() > target) {
            high--;
        }
        return high; 
    }

    // Return the index that value is >= target X boundary (Start index)
    public int searchHigher(int target) {
        int low = 0;
        int high = service.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = service[mid].getValue();

            if (target == midValue) {
                return mid;
            } else if (midValue - target > 0) {
                high = mid - 1;
            } else if (midValue - target < 0) {
                low = mid + 1;
            }   
        }
        
         /*
          * When come down here, that means there is no value = target
          but we need to find the value is >= target, so
          increment low until the value of index low is greater than the target
          */
        while (service[low].getValue() < target) {
            low++;
        }
        return low;
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
