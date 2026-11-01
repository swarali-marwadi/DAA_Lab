package prjDijkstra;

import java.util.Comparator;
import java.util.Scanner;
import java.util.PriorityQueue;

class Pair<K, V> {
    K key;
    V value;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

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
                if(edge>=0)
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

        int[] distArr = new int[n];
        for(int i=0;i<n;i++)
        {
            distArr[i]=Integer.MAX_VALUE;
        }

        System.out.println("Enter source vertex:");
        int src = sc.nextInt();

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparing(Pair::getKey));

        distArr[src]=0;
        pq.add(new Pair<>(0,src));

        while(!pq.isEmpty())
        {
            int wt = pq.peek().key;
            int u = pq.poll().value;

            if(wt >distArr[u])
                continue;

            for(int v=0;v<n;v++)
            {
                if(v==u)
                    continue;

                if(adjMatrix[u][v]!=Integer.MAX_VALUE && distArr[u]!=Integer.MAX_VALUE && wt + adjMatrix[u][v] < distArr[v])
                {
                    distArr[v] = wt + adjMatrix[u][v];
                    pq.add(new Pair<>(distArr[v],v));
                }
            }
        }

        System.out.println("Distances from source to ");
        for(int i=0;i<n;i++)
        {
            if(distArr[i]!=Integer.MAX_VALUE)
            {
                System.out.println(i + " : " + distArr[i]);
            }
            else
            {
                System.out.println(i + " : INF (Unreachable)");
            }
        }

        sc.close();
    }
}
