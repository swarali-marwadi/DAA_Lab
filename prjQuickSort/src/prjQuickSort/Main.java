package prjQuickSort;

import java.util.Scanner;

public class Main {

    int findpivot(int n, int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low + 1;
        int j = high;

        while (i <= j)
        {

            while (i <= high && arr[i] <= pivot)
            {
                i++;
            }

            while (j > low && arr[j] > pivot)
            {
                j--;
            }

            if (i < j)
            {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            else
            {
                break;
            }
        }

        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j;
    }

    void quicksort(int n, int[] arr, int low, int high) {
        if (low < high) {
            int part = findpivot(n, arr, low, high);

            quicksort(n, arr, low, part - 1);
            quicksort(n, arr, part + 1, high);
        }
    }

    int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high)
        {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key)
            {
                return mid;
            }
            else if (arr[mid] < key)
            {
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter length of array to sort:");
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Array size cannot be negative.");
            sc.close();
            return;
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter element " + i + ":");
            arr[i] = sc.nextInt();
        }

        Main t1 = new Main();

        // Sort the array using Quick Sort
        t1.quicksort(n, arr, 0, n - 1);

        System.out.println("Sorted Array:");
        for (int i = 0; i < n; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Search for multiple keys using Binary Search
        System.out.println("Enter number of search keys:");
        int k = sc.nextInt();

        for (int i = 0; i < k; i++)
        {
            System.out.println("Enter search key:");
            int key = sc.nextInt();

            int result = t1.binarySearch(arr, key);

            if (result != -1)
            {
                System.out.println("Search " + key + " -> Found at index " + result);
            }
            else
            {
                System.out.println("Search " + key + " -> Key not found (-1)");
            }
        }

        sc.close();
    }
}