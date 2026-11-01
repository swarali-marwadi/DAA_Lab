package prjBellmanFordNew;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter no of vertices :");
        int n = sc.nextInt();

        System.out.println("Enter no of edges in the graph :");
        int noe = sc.nextInt();

        int[][] edges = new int[noe][3];
        for(int i=0;i<n;i++)
        {
            System.out.println("For edge "+(i+1)+",");

            System.out.println("Enter source :");
            edges[i][0] = sc.nextInt();

            System.out.println("Enter destination :");
            edges[i][1] = sc.nextInt();

            System.out.println("Enter edge wt :");
            edges[i][2] = sc.nextInt();
        }

        System.out.println("Enter source vertex :");
        int src = sc.nextInt();

        int[] distArr = new int[n];
        for(int i=0;i<n;i++)
        {
            distArr[i]=Integer.MAX_VALUE;
        }

        distArr[src]=0;

        for(int i=0;i<n-1;i++)
        {
            boolean updated = false;
            for(int j=0;j<noe;j++)
            {
                int u = edges[j][0];
                int v = edges[j][1];
                int wt = edges[j][2];

                if(distArr[u]!=Integer.MAX_VALUE && distArr[u] + wt < distArr[v])
                {
                    updated = true;
                    distArr[v] = distArr[u] + wt;
                }
            }

            if(!updated)
                break;
        }

        for(int j=0;j<noe;j++)
        {
            int u = edges[j][0];
            int v = edges[j][1];
            int wt = edges[j][2];

            if(distArr[u]!=Integer.MAX_VALUE && distArr[u] + wt < distArr[v])
            {
                System.out.println("Negative-weight cycle detected!");
                sc.close();
                return;
            }
        }

        System.out.println("Distances from source to:");
        for (int i = 0; i < n; i++) {
            if (distArr[i] == Integer.MAX_VALUE) {
                System.out.println(i + " : INF (Unreachable)");
            } else {
                System.out.println(i + " : " + distArr[i]);
            }
        }

        sc.close();
    }
}
