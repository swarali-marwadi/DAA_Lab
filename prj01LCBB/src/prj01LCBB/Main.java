package prj01LCBB;

import java.util.Arrays;
import java.util.Scanner;

class Item
{
    int weight;
    int profit;
    int index;
    double ratio;

    Item(int weight, int profit, int index)
    {
        this.weight = weight;
        this.profit = profit;
        this.index = index;
        this.ratio = (double) profit / weight;
    }
}

class Node
{
    Node left, right;

    int[] x;
    double[] y;

    double UB1, LB1, UB2, LB2;
    double X, Y;

    int level;
    int weight;
    int profit;

    double UB, LB;

    Node(int n)
    {
        x = new int[n];
        y = new double[n];

        level = 0;
        weight = 0;
        profit = 0;
    }
}

public class Main
{
    static int capacity;
    static int n;
    static int maxProfit = 0;

    static int[] bestSelection;
    static Item[] items;

    static void calculateBounds(Node node)
    {
        int totalWeight = node.weight;
        int totalProfit = node.profit;

        // Calculate feasible integer profit
        for (int i = node.level; i < n; i++)
        {
            if (totalWeight + items[i].weight <= capacity)
            {
                totalWeight += items[i].weight;
                totalProfit += items[i].profit;
            }
        }

        node.UB = -totalProfit;

        // Calculate fractional profit bound
        double fractionalProfit = node.profit;
        totalWeight = node.weight;

        for (int i = node.level; i < n; i++)
        {
            if (totalWeight + items[i].weight <= capacity)
            {
                totalWeight += items[i].weight;
                fractionalProfit += items[i].profit;
            }
            else
            {
                int remaining = capacity - totalWeight;

                if (remaining > 0)
                {
                    fractionalProfit += remaining * items[i].ratio;
                }

                break;
            }
        }

        node.LB = -fractionalProfit;
    }

    static void knapsack(Node current)
    {
        if (current.weight > capacity)
        {
            return;
        }

        if (current.profit > maxProfit)
        {
            maxProfit = current.profit;
            bestSelection = current.x.clone();
        }

        calculateBounds(current);

        // Prune if the fractional upper bound cannot improve maxProfit
        if (-current.LB <= maxProfit)
        {
            return;
        }

        if (current.level >= n)
        {
            return;
        }

        int i = current.level;

        // Left child: include item
        Node left = new Node(n);

        left.x = current.x.clone();
        left.y = current.y.clone();

        left.x[i] = 1;
        left.level = i + 1;
        left.weight = current.weight + items[i].weight;
        left.profit = current.profit + items[i].profit;

        // Right child: exclude item
        Node right = new Node(n);

        right.x = current.x.clone();
        right.y = current.y.clone();

        right.x[i] = 0;
        right.level = i + 1;
        right.weight = current.weight;
        right.profit = current.profit;

        // Calculate bounds of both children
        calculateBounds(left);
        calculateBounds(right);

        current.left = left;
        current.right = right;

        current.UB1 = left.UB;
        current.LB1 = left.LB;

        current.UB2 = right.UB;
        current.LB2 = right.LB;

        current.X = current.UB1 - current.LB1;
        current.Y = current.UB2 - current.LB2;

        Node first;
        Node second;

        // Select child according to the instructor's rule
        if (current.X < current.Y)
        {
            first = left;
            second = right;
        }
        else if (current.X > current.Y)
        {
            first = right;
            second = left;
        }
        else if (current.UB1 < current.UB2)
        {
            first = left;
            second = right;
        }
        else
        {
            first = right;
            second = left;
        }

        // Explore first child
        if (first.weight <= capacity)
        {
            knapsack(first);
        }

        // Explore second child
        if (second.weight <= capacity)
        {
            knapsack(second);
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        n = sc.nextInt();

        System.out.print("Enter knapsack capacity: ");
        capacity = sc.nextInt();

        items = new Item[n];
        bestSelection = new int[n];

        for (int i = 0; i < n; i++)
        {
            System.out.print("Enter weight and profit of item " + (i + 1) + ": ");

            int weight = sc.nextInt();
            int profit = sc.nextInt();

            items[i] = new Item(weight, profit, i);
        }

        // Sort items by decreasing profit-to-weight ratio
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        Node root = new Node(n);

        knapsack(root);

        System.out.println("\nMaximum profit = " + maxProfit);
        System.out.println("Selected items:");

        for (int i = 0; i < n; i++)
        {
            if (bestSelection[i] == 1)
            {
                System.out.println("Item " + (items[i].index + 1) + " (Weight = " + items[i].weight + ", Profit = " + items[i].profit + ")");
            }
        }

        sc.close();
    }
}
