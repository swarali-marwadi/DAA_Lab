package prjMergeSortNew;

import java.util.*;

public class Main {
    int cnt = 0;
    int pass = 1;

    public void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int left[] = new int[n1];
        int right[] = new int[n2];

        for (int i = 0; i < n1; i++)
        {
            left[i] = arr[l + i];
        }

        for (int j = 0; j < n2; j++)
        {
            right[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2)
        {
            cnt++;

            if (left[i] <= right[j])
            {
                arr[k] = left[i];
                i++;
            }
            else
            {
                arr[k] = right[j];
                j++;
            }

            k++;
        }

        while (i < n1)
        {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < n2)
        {
            arr[k] = right[j];
            j++;
            k++;
        }

        System.out.print("Pass " + pass + " : ");

        for(int a = 0; a < arr.length; a++)
        {
            System.out.print(arr[a] + " ");
        }

        pass++;
        System.out.println();
    }

    public void divide(int arr[], int l, int r)
    {
        if (l < r)
        {
            int m = l + (r - l) / 2;

            divide(arr, l, m);
            divide(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static void main(String args[])
    {
        Main obj = new Main();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter no. of elements in your array to sort:");
        int n = sc.nextInt();

        if (n < 0)
        {
            System.out.println("Array size cannot be negative.");
            sc.close();
            return;
        }

        int arr[] = new int[n];

        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++)
        {
            arr[i] = sc.nextInt();
        }

        obj.divide(arr, 0, n - 1);

        System.out.println("Sorted array is:");

        for (int i = 0; i < n; i++)
        {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
        System.out.println("Total no. of comparisons are: " + obj.cnt);
        System.out.println("Time Complexity = O(n log n)");

        sc.close();
    }
}