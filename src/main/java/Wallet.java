import java.util.HashMap;
import java.util.List;

public class Wallet {
    private String id;
    private List<Item> items;
    private HashMap<Size, Integer> capacity;

    public Wallet(String id, List<Item> items, HashMap<Size, Integer> capacity) {
        this.id = id;
        this.items = items;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public HashMap<Size, Integer> getCapacity() {
        return capacity;
    }

    public void setCapacity(HashMap<Size, Integer> capacity) {
        this.capacity = capacity;
    }

    public int getSpaceAvailable(Size size) {
        int itemCount = 0;

        // Count the number of items for the specified size based on the rules
        for (Item item : items) {
            Size itemSize = item.getSize();

            // Skip Money items for now
            if (item instanceof Money) {
                System.out.println("ERROR: NOT IMPLEMENTED YET for Money items.");
                continue;
            }

            Size convertedSize = convertSize(itemSize);
            itemCount += (size == convertedSize) ? getCapacityNumber(itemSize) : 0;
        }

        // Return the space available for the specified size
        return capacity.getOrDefault(size, 0) - itemCount;
    }

    private Size convertSize(Size itemSize) {
        return switch (itemSize) {
            case SMALL_AND_THICK -> Size.SMALL;
            case MEDIUM_AND_THICK -> Size.MEDIUM;
            case LARGE_AND_THICK -> Size.LARGE;
            default -> itemSize;
        };
    }

    private int getCapacityNumber(Size size) {
        // Number corresponding to the size based on the rules
        return switch (size) {
            case SMALL, MEDIUM, LARGE -> 1;
            case SMALL_AND_THICK, MEDIUM_AND_THICK, LARGE_AND_THICK -> 2;
            default -> 0;
        };
    }



    public void take(Item item) {
        // Implement logic to take an item from the wallet
    }

    public void add(Item item) {
        if (item instanceof Money) {
            System.out.println("ERROR: NOT IMPLEMENTED YET for Money items.");
            return;
        }


        Size convertedSize = convertSize(item.getSize());
        int itemCount = getCapacityNumber(item.getSize());
        if (getSpaceAvailable(convertedSize)>=itemCount) {
            items.add(item);
            System.out.println("Item added successfully.");
        } else {
            System.out.println("Space not available for the item size. Please check the available space.");
        }
    }

    private boolean isSpaceAvailable(Size size, int itemCount) {
        return capacity.getOrDefault(size, 0) >= itemCount;
    }





    private void displaySpaceAvailable(Size size, int itemCount) {
        int remainingCapacity = capacity.getOrDefault(size, 0) - itemCount;
        System.out.println("Space available for " + size + ": " + remainingCapacity);
    }
}