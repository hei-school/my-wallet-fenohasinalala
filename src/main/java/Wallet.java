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

    public void getSpaceAvailable() {
        for (Item i: items) {
            i
        }
    }

    public void take(Item item) {
        // Implement logic to take an item from the wallet
    }

    public void add(Item item) {
        Size itemSize = item.getSize();
        int itemCount = 1;

        if (itemSize == Size.SMALL_AND_THICK || itemSize == Size.MEDIUM_AND_THICK || itemSize == Size.LARGE_AND_THICK) {
            itemCount = 2;
        }

        if (isSpaceAvailable(itemSize, itemCount)) {
            items.add(item);
            updateCapacity(itemSize, itemCount);
            System.out.println("Item added successfully.");
        } else {
            System.out.println("Space not available for the item size. Please check the available space.");
        }
    }

    private boolean isSpaceAvailable(Size size, int count) {
        return capacity.getOrDefault(size, 0) >= count;
    }

    private void updateCapacity(Size size, int count) {
        capacity.put(size, capacity.get(size) - count);
    }
}
