package queue;

public class StaticLinearQueue {
    private int capacity;
    private int size;
    private String[] array;
    private int start;
    private int end;

    public StaticLinearQueue() {
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

        if (this.end < this.capacity - 1) {
            this.array[end + 1] = item;
            this.end++;
            this.size++;
        }
    }

    public String dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        String item = this.array[this.start];
        this.array[this.start] = null;
        this.start++;
        this.size--;

        return item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");

        for (int i = this.start; i <= this.end; i++) {
            sb.append(this.array[i]).append(" ");
        }

        sb.append("}");

        return sb.toString();
    }
}