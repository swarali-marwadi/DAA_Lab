package prjKruskalsNew;

import java.util.PriorityQueue;
import java.util.Scanner;

class Task implements Comparable<Task> {
    int u;
    int v;
    int edge;

    public Task(int u, int v, int edge) {
        this.u = u;
        this.v = v;
        this.edge = edge;
    }

    public int compareTo(Task other)
    {
        return Integer.compare(this.edge, other.edge);
    }
}

public class Main {
    int find(int x, int[] parent)
    {
        if(parent[x]==x)
            return x;
        return find(parent[x], parent);
    }

    void union(int a, int b, int[] parent, int[] rank)
    {
        int pa = find(a,parent);
        int pb = find(b,parent);
        if(rank[pa]<rank[pb])
            parent[pa]=pb;
        else if(rank[pa]>rank[pb])
            parent[pb]=pa;
        else
        {
            parent[pb]=pa;
            rank[pa]++;
        }
    }

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
                if(i==j)
                    adjMatrix[i][j]=0;

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

        PriorityQueue<Task> pq = new PriorityQueue<>();

        for(int i=0; i<n; i++)
        {
            for(int j=i+1; j<n; j++)
            {
                if(adjMatrix[i][j]!=Integer.MAX_VALUE)
                {
                    pq.add(new Task(i,j,adjMatrix[i][j]));
                }
            }
        }

        int[] parent = new int[n];
        int[] rank = new int[n];
        for(int i=0;i<n;i++)
        {
            parent[i]=i;
            rank[i]=0;
        }

        int edge = 0;
        int cost = 0;
        Main t1 = new Main();

        while(edge<n-1 && !pq.isEmpty())
        {
            int u = pq.peek().u;
            int v = pq.peek().v;
            int wt = pq.peek().edge;
            pq.poll();

            if(t1.find(u,parent)!=t1.find(v,parent))
            {
                t1.union(u,v,parent,rank);
                cost+=wt;
                edge++;
                System.out.println("Edge selected: "+u+"-"+v+": "+wt);
            }
        }

        if(edge!=n-1)
        {
            System.out.println("Error: Graph is disconnected. An MST spanning all vertices cannot be formed.");
        }
        else
            System.out.println("Total MST weight = "+cost);

        sc.close();
    }
}
