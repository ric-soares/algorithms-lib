package arrayutils;

import java.util.Random;

public class ArrayUtils {

    public static void printArray(int[] a) {
        System.out.print("[");

        for (int i = 0; i < a.length ; i++) {
            System.out.printf(" %d ", a[i]);
        }

        System.out.printf("]%n");
    }

    public static void printArrayWithIndex(int[] a) {
        int nChar = 0;

        for (int i = 0; i < a.length; i++) {
            int c = Integer.toString(a[i]).length();
            if( c > nChar) nChar = c;
        }

        nChar = nChar + 1;
        for (int i = 0; i < a.length; i++) System.out.printf("+" + "-".repeat(nChar +1), i);
        System.out.printf("%n");

        for (int i = 0; i < a.length ; i++) {
            System.out.printf("|%" + nChar + "d ", a[i]);
        }
        System.out.printf("%n");

        for (int i = 0; i < a.length; i++) System.out.printf("|%" + nChar + "d ", i);
        System.out.printf("%n");

        for (int i = 0; i < a.length; i++) System.out.printf("+" + "-".repeat(nChar +1), i);
    }

    public static void fillArrayWithRandomValues(int[] a, int upperBound, boolean allowDuplicates) {
        Random generator = new Random();

        for (int i = 0; i < a.length; i++) {
            int randomValue = generator.nextInt(upperBound);

            if (allowDuplicates) {
                a[i] = randomValue;
            } else {
                while (valueExistsInArray(a, randomValue)) {
                    randomValue = generator.nextInt(upperBound);
                }
                a[i] = randomValue;
            }
        }
    }

    public static boolean valueExistsInArray(int[] a, int value) {
        boolean isFound = false;
        int i = 0;

        while (!isFound && i < a.length) {
            if (a[i] == value) isFound = true;
            i++;
        }

        return isFound;
    }

    public static void cloneArray(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; i++) {
            a2[i] = a1[i];
        }
    }
}