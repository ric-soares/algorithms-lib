package queue;

public class StaticCircularQueue {
    private final int capacity;
    private int size;
    private final String[] array;
    private int start;
    private int end;

    public StaticCircularQueue() {
        this.capacity = 10;
        this.size = 0;
        this.start = 0;
        this.end = -1;
        this.array = new String[capacity];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }

    public void enqueue(String item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }

        this.end = (this.end + 1) % this.capacity;
        this.array[this.end] = item;
        this.size++;
    }

    public String dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        String item = this.array[this.start];
        this.array[this.start] = null;

        this.start = (this.start + 1) % this.capacity;
        this.size--;

        return item;
    }

    public int getSize() {
        return this.size;
    }
}
