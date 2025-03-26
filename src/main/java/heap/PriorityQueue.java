package heap;

public class PriorityQueue {
    private int[] queue;
    private int size;

    public PriorityQueue(int initialCapacity) {
        this.queue = new int[initialCapacity + 1];
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int max() {
        if (isEmpty()) return -1;
        return queue[1];
    }

    public int size() {
        return this.size;
    }

    public void insert(int num) {
        if (this.size == queue.length - 1) resize(2 * queue.length);
        this.size++;
        this.queue[this.size] = num;
        swim(this.size);
    }

    public int delMax() {
        if (isEmpty()) return -1;

        int maxValue = queue[1];
        swap(1, this.size);
        this.size--;
        sink(1);

        if (this.size > 0 && this.size <= (queue.length - 1) / 4) {
            resize(queue.length / 2);
        }

        return maxValue;
    }

    private void swim(int i) {
        while (i > 1 && less(i/2, i)) {
            swap(i, i/2);
            i = i/2;
        }
    }

    private void sink(int i) {
        while (2 * i <= this.size) {
            int j = 2 * i;
            if (j < this.size && less(j, j + 1)) j++;

            if (!less(i, j)) break;

            swap(i, j);
            i = j;
        }
    }

    private boolean less(int i, int j) {
        return queue[i] < queue[j];
    }

    private void swap(int i, int j) {
        int temp = queue[i];
        queue[i] = queue[j];
        queue[j] = temp;
    }

    private void resize(int capacity) {
        int[] tempArray = new int[capacity];
        for (int i = 1; i <= this.size; i++) {
            tempArray[i] = queue[i];
        }
        this.queue = tempArray;
    }
}
