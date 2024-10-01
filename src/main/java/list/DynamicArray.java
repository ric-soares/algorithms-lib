package list;

public class DynamicArray {
    private int capacity;
    private int size;
    private String[] items;

    public DynamicArray() {
        this.capacity = 5;
        this.items = new String[this.capacity];
        this.size = 0;
    }

    public void add(String item) {
        if (this.size == this.capacity) {
            int newCapacity = this.capacity * 2;

            String[] helper = new String[newCapacity];

            int index = 0;

            for (int i = 0; i < this.capacity; i++) {
                if (items[i] != null) {
                    helper[index] = items[i];
                    index++;
                }
            }

            this.capacity = newCapacity;
            this.items = helper;
        }

        this.items[this.size] = item;
        this.size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            sb.append(items[i]).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
