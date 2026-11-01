package prjSumOfSubsets;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    boolean found = false;

    void subsetSum(int i, int n, int[] set, int targetSum, ArrayList<Integer> subset)
    {
        if(i==n)
        {
            if(targetSum==0)
            {
                System.out.println(subset);
                found=true;
            }
            return;
        }

        //exclude
        subsetSum(i+1,n,set,targetSum,subset);

        //include
        if(set[i]<=targetSum)
        {
            subset.add(set[i]);
            subsetSum(i+1,n,set,targetSum-set[i],subset);

            //backtrack
            subset.remove(subset.size() - 1);
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter no. of elements in the set :");
        int n = sc.nextInt();

        int[] set = new int[n];

        System.out.println("Enter elements in the set (Positive and Unique) :");
        for(int i=0;i<n;i++)
        {
            System.out.println("Element "+i+" :");
            int value = sc.nextInt();

            if(value<=0)
            {
                System.out.println("Error: Non-Positive Element!");
                sc.close();
                return;
            }

            int j=i-1;
            while(j>=0)
            {
                if(value==set[j])
                {
                    System.out.println("Error: Duplicate Element!");
                    sc.close();
                    return;
                }
                j--;
            }

            set[i]=value;
        }

        System.out.println("Enter required sum :");
        int targetSum = sc.nextInt();

        ArrayList<Integer> subset = new ArrayList<>();

        Main t1 = new Main();
        t1.subsetSum(0,n,set,targetSum,subset);

        if(!t1.found)
        {
            System.out.println("No subset matches target sum");
        }

        sc.close();
    }
}