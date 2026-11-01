package prjFractionalKnapsack;

import java.util.Scanner;
import java.util.Arrays;

class Item
{
    int weight;
    int profit;
    double ratio;

    Item(int weight, int profit)
    {
        this.weight = weight;
        this.profit = profit;
        this.ratio = (double)profit/weight;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of items :");
        int n = sc.nextInt();

        Item[] items = new Item[n];

        for(int i=0;i<n;i++)
        {
            System.out.println("For item "+i+" :");
            System.out.println("Enter weight :");
            int weight = sc.nextInt();
            System.out.println("Enter profit :");
            int profit = sc.nextInt();

            items[i] = new Item(weight,profit);
        }

        Arrays.sort(items, (a,b)->Double.compare(b.ratio,a.ratio));

        System.out.println("Enter capacity :");
        int capacity = sc.nextInt();

        double fractWeight = 0;
        double fractProfit = 0;
        double remaining;

        for(int i=0;i<n;i++)
        {
            if(fractWeight+items[i].weight<=capacity)
            {
                fractWeight += items[i].weight;
                fractProfit += items[i].profit;
            }
            else
            {
                remaining = capacity - fractWeight;

                if(remaining>0)
                {
                    fractProfit += remaining * items[i].ratio;
                    fractWeight += remaining ;
                }

                break;
            }
        }

        System.out.println("\nMaximum Profit : "+fractProfit);
        System.out.println("Remaining Capacity : "+(capacity-fractWeight));

        sc.close();
    }
}