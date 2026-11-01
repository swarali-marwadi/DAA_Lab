package prj01KnapsackDP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter total no. of items :");
        int n = sc.nextInt();

        System.out.println("Enter wt of n items :");
        int[] wt = new int[n];
        for(int i=0;i<n;i++)
        {
            System.out.println("Item "+i+" :");
            wt[i] = sc.nextInt();
        }

        System.out.println("Enter val of n items :");
        int[] val = new int[n];
        for(int i=0;i<n;i++)
        {
            System.out.println("Item "+i+" :");
            val[i] = sc.nextInt();
        }

        System.out.println("Enter max capacity of the Knapsack :");
        int cap = sc.nextInt();

        int[][] dp = new int[n+1][cap+1];

        for(int i=0;i<=n;i++)
        {
            for(int j=0;j<=cap;j++)
            {
                if(i==0||j==0)
                {
                    dp[i][j]=0;
                }
                else
                {
                    int pick = 0;
                    int notPick = dp[i-1][j];

                    if(wt[i-1]<=j)
                        pick = val[i-1] + dp[i-1][j-wt[i-1]];

                    dp[i][j] = Math.max(notPick,pick);
                }
            }
        }

        System.out.println("Maximum Profit = "+dp[n][cap]);

        int remainingCap = cap;
        boolean selected = false;

        System.out.println("Selected Items:");

        for(int i=n;i>0;i--)
        {
            if(dp[i][remainingCap]!=dp[i-1][remainingCap])
            {
                int index = i-1;

                System.out.println("Item "+index+" (w = "+wt[index]+", v = "+val[index]+")");

                remainingCap -= wt[index];
                selected = true;
            }
        }

        if(!selected)
        {
            System.out.println("None");
        }

        sc.close();
    }
}
