package prjNQueensNew;

import java.util.Scanner;

class NQueens
{
    int n;
    int[][] board;
    int solutions;

    NQueens(int n)
    {
        this.n = n;
        this.board = new int[n][n];
    }

    void printBoard()
    {
        System.out.println("\t");
        for(int i=0;i<n;i++)
            System.out.print("\t"+i);

        for(int i=0;i<n;i++)
        {
            System.out.print("\n"+i);
            for(int j=0;j<n;j++)
            {
                if(board[i][j]==1)
                    System.out.print("\tQ");
                else
                    System.out.print("\t.");
            }
        }

        System.out.println();
    }

    boolean isSafe(int row, int col)
    {
        int duprow=row;
        int dupcol=col;

        while(row>=0 && col>=0)
        {
            if(board[row][col]==1)
                return false;
            row--;
            col--;
        }

        row=duprow;
        col=dupcol;
        while(col>=0)
        {
            if(board[row][col]==1)
                return false;
            col--;
        }

        row=duprow;
        col=dupcol;
        while(row<n && col>=0)
        {
            if(board[row][col]==1)
                return false;
            row++;
            col--;
        }

        return true;
    }

    void solveAll(int col)
    {
        if(col==n)
        {
            printBoard();
            solutions++;
            return;
        }

        for(int row=0;row<n;row++)
        {
            if(isSafe(row,col))
            {
                board[row][col]=1;
                solveAll(col+1);
                board[row][col]=0;
            }
        }
    }

    void findAllSolution()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                board[i][j]=0;
            }
        }

        solveAll(0);

        if(solutions>0)
        {
            System.out.println("\n\nTotal Solutions Found : "+solutions);
        }
        else
        {
            System.out.println("No solution found");
        }
    }

    boolean solve(int col)
    {
        if(col==n)
        {
            System.out.println("\nFirst found solution:");
            printBoard();
            return true;
        }

        for(int row=0;row<n;row++)
        {
            if(isSafe(row,col))
            {
                board[row][col]=1;
                if(solve(col+1))
                    return true;
                board[row][col]=0;
            }
        }

        return false;
    }

    boolean findSolution()
    {
        boolean found = solve(0);

        if(!found)
        {
            System.out.println("Solution not found!");
        }

        return found;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter no. of elements in the set:");
        int n = sc.nextInt();

        NQueens t1 = new NQueens(n);

        boolean found = t1.findSolution();

        if(found)
        {
            System.out.println("\n\nAll found solutions:");

            t1.findAllSolution();
        }

        System.out.println();

        sc.close();
    }
}