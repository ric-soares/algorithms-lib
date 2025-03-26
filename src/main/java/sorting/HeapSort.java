package sorting;

public class HeapSort {

    private int[] heap;
    private int size;

    public void heapSort(int[] arr) {
        this.heap = arr;
        this.size = arr.length - 1;

        // build the heap structure
        for (int i = size/2; i >= 1; i--)
            sink(i);

        // sort by repeatedly removing the max value
        while (this.size > 1) {
            swap(1, this.size);
            this.size--;
            sink(1);
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
        return heap[i] < heap[j];
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
