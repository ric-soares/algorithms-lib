package sorting;

public class MergeSort {

    public MergeSort() {}

    private int[] array;
    private int[] helper;
    private int length;

    public void sort(int[] values) {
        this.array = values;
        length = values.length;
        this.helper = new int[length];

        mergeSort(0, length - 1);
    }

    private void mergeSort(int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            mergeSort(start, middle);
            mergeSort(middle + 1, end);
            merge(start, middle, end);
        }
    }

    private void merge(int start, int middle, int end) {
        for (int i = start; i <= end; i++) {
            helper[i] = array[i];
        }

        int left = start;
        int right = middle + 1;
        int current = start;

        while (left <= middle && right <= end) {
            if (helper[left] <= helper[right]) {
                array[current] = helper[left];
                left++;
            } else {
                array[current] = helper[right];
                right++;
            }
            current++;
        }

        while (left <= middle) {
            array[current] = helper[left];
            current++;
            left++;
        }
    }
}
