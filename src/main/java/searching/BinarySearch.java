package searching;

public class BinarySearch {

    public int binarySearch(int[] a, int start, int end, int key) {
        int low = start;
        int high = end - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int midValue = a[mid];

            if (midValue < key)
                low = mid + 1;
            else if (midValue > key)
                high = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}
