package com.finalProject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.ArrayList;

import java.util.Queue;


/**
 *  CS215, Final Programming Project: Knapsack 
 *
 *  Main Class.
 *
 *  DETAILED DESCRIPTION SHOULD BE UPDATED.
 *
 *  @author Revaz Bakuradze
 *  @author Aleksandr Molchagin
 *  @version June 5 2021
 */
public class Main {
    
    static class NodeComparator implements Comparator<Node>{
        /**
         *  Returns difference between node's frequency. (FOR INT)
         **/
        public int compare(Node a, Node b){
            return Integer.compare(a.getWeight(), b.getWeight());
        }
    };


    /**
     *  The main function initiates execution of this program.
     *    @param    String[] args not used in this program
     *              (but main methods always need this parameter)
     **/
    public static void main(String[] args) throws FileNotFoundException {

        //The location of input file with data sample
        File file = new File("input_data//t3.dat");

        Scanner scanner = new Scanner(file);

        int numberOfItems = Integer.parseInt(scanner.nextLine());       //Represents the number of items in the problem set
        int totalWeightLimit = Integer.parseInt(scanner.nextLine());    //Represents the total weight limit
        Item[] items = new Item[numberOfItems];                         //Represents array of all items

        //For loop for inputting values from file to the arrays
        for (int i = 0; i < numberOfItems; i++) {

            String line = scanner.nextLine();               //Getting the next line

            String seperatedWords[] = line.split(" ", 3);   //Breaking up the line with spaces and storing words in array

            
            int number = Integer.parseInt(seperatedWords[0]);
            int profit = Integer.parseInt(seperatedWords[1]);
            int weight = Integer.parseInt(seperatedWords[2]);

            items[i] = new Item(number, profit, weight);    //Creating a new item an put it into the array

        }

        Arrays.sort(items, new Comparator<Item>() {     //Sort all items using a custom comparator (reverse sort)
            @Override
            public int compare(Item a, Item b) {
                return Double.compare(b.getRatio(), a.getRatio());  //Compare based on ratios
            }
        });
    
        for (int i = 0; i < items.length; i++)          //Print all items
            System.out.println("\n" + items[i]);
  
        System.out.println("==========================" + "==========================");
        dynamicProgramming(items, totalWeightLimit);

        System.out.println("==========================" + "==========================");
        BreadthFirst_BranchAndBound(items, totalWeightLimit);
        

    }//main end

    public static void BreadthFirst_BranchAndBound(Item[] items, int totalWeightLimit){
        
        Node u = new Node(0,0,0);
        Node v = new Node(0,0,0);

		ArrayList<Node> q = new ArrayList<Node>();
        
        int maxProfit = 0;

        u.setBound(bound(u, items, totalWeightLimit));

        q.add(u);
        
        while(!q.isEmpty()){
            v = q.get(0);
            q.remove(0);

            u = new Node(0,0,0);
            u.setLevel(v.getLevel() + 1);
            u.setWeight(v.getWeight() + items[u.getLevel()-1].getWeight());
            u.setProfit(v.getProfit() + items[u.getLevel()-1].getProfit());

            if (u.getWeight() <= totalWeightLimit && u.getProfit() > maxProfit)
                maxProfit = u.getProfit();

                u.setBound(bound(u, items, totalWeightLimit));

            if (u.getBound() > maxProfit)
                q.add(u);
            
            u = new Node(0,0,0);
            u.setWeight(v.getWeight());
            u.setProfit(v.getProfit());
            u.setLevel(v.getLevel()+1);
            u.setBound(bound(v, items, totalWeightLimit));

            if (u.getBound() > maxProfit)
                q.add(u);
        } 

        System.out.println("\n" + maxProfit);
        System.out.println("\nDONEEEEEÇEE");


    };
    public static float bound (Node currentNode, Item[] items, int totalWeightLimit){
        int j, k;
        int totalWeight;
        float result;
        if (currentNode.getWeight() >= totalWeightLimit)
            return 0;
        else {
            result = currentNode.getProfit();
            j = currentNode.getLevel();
            totalWeight = currentNode.getWeight();
            while ( j<= items.length && (totalWeight + items[j].getWeight()) <= totalWeight){
                totalWeight = totalWeight + items[j].getWeight();
                result = result + items[j].getProfit();
                j++;
            }
            k = j;
            if (k <= items.length)
                result = result + ((totalWeightLimit - totalWeight) * (items[k].getProfit()/items[k].getWeight()));

            return result;
        }
    };
     /**
     *  The method implements dynamic programming algorithm to
     *  to solve the knapsack problem. The program returns void
     *  and prints the Matrix, Maximized Profit, Total Weight, and
     *  Item Numbers of the Chosen Items
     *
     *    @param    Item[] Array of item objects
     *    @param    int total weight limit
     **/
    public static void dynamicProgramming(Item[] item, int totalWeightLimit){

        //Initializing and filling length and weight arrays
        int[] profit = new int[item.length];
        int[] weight = new int[item.length];

        for (int i = 0; i < item.length; i++){
            profit[i] = item[i].getProfit();
            weight[i] = item[i].getWeight();
        }

        //Making matrix where row number is number of items + 1 (Starting from 0)
        // and the column length is total weight limit + 1 (Starting from 0 too)
        int[][] a = new int[item.length + 1][totalWeightLimit + 1];

        //For each iteration, we will consider the most optimal solutions up to i+1-case
        // and optimize it until we reach the end of the loop.
        for (int i = 0; i <= item.length; i++){
            for (int j = 0; j <= totalWeightLimit; j++) {

                //Setting values at 0 weight and 0 profit to be 0
                if (i == 0 || j == 0){
                    a[i][j] = 0;
                } else {

                    //a[i-1][j] -> Element in the upper row
                    //a[i-1][j - weight[i]] + profit[i] -> if j - weight[i] > 0
                    // it results in the existent element in array
                    // and we add profit to the maximum profit item
                    int b = 0;

                    //Making sure that a[i-1][j - weight[i-1]] coordinate exists on the matrix
                    if ((j - weight[i-1]) >= 0){
                        b = a[i-1][j - weight[i-1]] + profit[i-1];
                    }

                    //Setting a[i][j] to the maximum
                    if (a[i-1][j] <= b){
                        a[i][j] = b;
                    } else {
                        a[i][j] = a[i-1][j];
                    }
                }
            }
        }

        //Printing the matrix
        for (int row = 0; row < a.length; row++){ //Cycles through rows
            for (int col = 0; col < a[row].length; col++){ //Cycles through columns
                System.out.printf("%5d", a[row][col]); //Printing the matrix elements
            }
            System.out.println(); //Makes a new row
        }


        //Maximized total profit
        int optimalProfit = a[item.length][totalWeightLimit];
        System.out.println("Maximized Profit: " + optimalProfit);


        //Maximized total weight
        int optimalWeight = 0;

        //for loop for going through the last row of the matrix and
        // determining
        for (int i = 0; i <= totalWeightLimit; i++) {
            if ((a[item.length][i]) == optimalProfit) {
                optimalWeight = i;
                break;
            }
        }

        System.out.println("Total Weight: " + optimalWeight);

        //Outputting the items to include
/*
        //ArrayList string indexes of the used items
        ArrayList<Integer> usedItemArrayList = new ArrayList<Integer>();

        int temp = 0;
        for (int i = 0; i <= totalWeightLimit; i++){
            for (int j = 0; j <= totalWeightLimit; j++) {
                if (a[totalWeightLimit - j][i] == optimalProfit) {
                    temp = i;

                    int temp2 = totalWeightLimit - temp;


                }
            }
        }

 */

    }
}//class end

/*
    USEFUL NOTES

    //breadth first, depth first

    //6.1 new node for this alorithm implementation

    //root level -1 children would be 0 - first elments.
*/
