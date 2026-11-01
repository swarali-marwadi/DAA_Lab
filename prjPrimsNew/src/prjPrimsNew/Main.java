package prjPrimsNew;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int n;
        System.out.println("Enter no of vertices:");
        n=sc.nextInt();

        int[][] adjMatrix = new int[n][n];

        for(int i=0; i<n; i++)
        {
            for(int j=i; j<n; j++)
            {
                if(i==j) {
                    adjMatrix[i][j] = 0;
                    continue;
                }

                System.out.println("Enter weight of edge "+i+"-"+j+" (if exists) / Enter -1 if edge "+i+"-"+j+" doesn't exist :");
                int edge=sc.nextInt();
                if(edge>0)
                {
                    adjMatrix[i][j]=edge;
                    adjMatrix[j][i]=edge;
                }
                else
                {
                    adjMatrix[i][j]=Integer.MAX_VALUE;
                    adjMatrix[j][i]=Integer.MAX_VALUE;
                }
            }
        }

        int[] visited = new int[n];

        int edge=0;
        int cost=0;
        System.out.println("Enter start vertex:");
        int src= sc.nextInt();
        visited[src]=1;

        while(edge < n-1)
        {
            int min = Integer.MAX_VALUE;
            int u=-1;
            int v=-1;

            for(int i=0;i<n;i++)
            {
                if(visited[i]==1)
                {
                    for(int j=0;j<n;j++)
                    {
                        if(visited[j]==0 && adjMatrix[i][j]>0 && adjMatrix[i][j] < min)
                        {
                            min=adjMatrix[i][j];
                            u=i;
                            v=j;
                        }
                    }
                }
            }

            if(v==-1)
            {
                System.out.println("Error: Graph is disconnected. An MST spanning all vertices cannot be formed.");
                sc.close();
                return;
            }

            visited[v]=1;
            System.out.println("Edge selected : "+u+"-"+v+": "+min);
            cost+=min;
            edge++;
        }

        System.out.println("Total MST Weight = "+cost);

        sc.close();
    }
}
