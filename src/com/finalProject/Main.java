package com.finalProject;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;


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

    //A global variable for debugging
    public static boolean DEBUG = false;

    /**
     *  The main function initiates execution of this program.
     *    @param    args args not used in this program
     *              (but main methods always need this parameter)
     **/
    public static void main(String[] args) throws FileNotFoundException {

        //The location of input file with data sample
        File file = new File("input_data//t7.dat");

        Scanner scanner = new Scanner((new BufferedReader(new FileReader(file))));

        int numberOfItems = 0; //Represents the number of items in the problem set
        if(scanner.hasNext()) numberOfItems = scanner.nextInt();
		
        int totalWeightLimit = 0;  //Represents the total weight limit
        if(scanner.hasNext()) totalWeightLimit = scanner.nextInt();

        Item[] items = new Item[numberOfItems]; //Represents array of all items

        //For loop for inputting values from file to the array of items
        for(int i=0; i < numberOfItems; i++) {
            int number = scanner.nextInt();
            int profit = scanner.nextInt() * 100;
            int weight = scanner.nextInt();
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
  
        System.out.println("=============================================" + "=============================================");
        dynamicProgramming(items, totalWeightLimit);

        System.out.println("=============================================" + "=============================================");
        int[] returnArray1 = BreadthFirst_BranchAndBound(items, totalWeightLimit);
        
        System.out.print("\n\t\tBreadth-First Search with Branch-and-Bound algorithm\n");
        System.out.print("\n\tProfit: " + returnArray1[0] + "\n\tWeight: " + returnArray1[1] + "\n\tItems: ");
        for (int i = 2; i < returnArray1.length; i++)
            System.out.print(" " + returnArray1[i]);

        System.out.println("\n\n============================================" + "=============================================");
        int[] returnArray2 = BestFirst_BranchAndBound(items, totalWeightLimit);

        System.out.print("\n\t\tBest-First Search with Branch-and-Bound algorithm\n");
        System.out.print("\n\tProfit: " + returnArray2[0] + "\n\tWeight: " + returnArray2[1] + "\n\tItems: ");
        for (int i = 2; i < returnArray2.length; i++)
            System.out.print(" " + returnArray2[i]);

        System.out.println("\n\n============================================" + "=============================================");

    }

    /**
     *  The method to implement breadth-first branch and bound algorithm to
     *  to solve the knapsack problem. Return an array of integers, where
     *  the 1st element is the profit, 2nd element is the total weight, and
     *  everything else - all collected items and "trash" (Integer.MAX_VALUE elements)
     *
     *    @param    items Array of item objects
     *    @param    totalWeightLimit total weight limit
     *     
     *    @return   int[] array with the result: [0] - profit, [1] - weight, [2..n] - items and "trash"
     **/
    public static int[] BreadthFirst_BranchAndBound(Item[] items, int totalWeightLimit){
        int counter = 0; //A counter to measure performance

        ArrayList<Item> list = new ArrayList<Item>();
        
		Queue<Node> q = new LinkedList<Node>(); //Initialize a Queue (LinkedList)

        Node u = new Node(0,0,0);  // Root Node
        Node v = new Node(0,0,0);  // Temp Node

        int maxProfit = 0;   // Initialize profit
        int finalWeight = 0; // Initialize profit


        q.add(u);  // Add the root
    
        //Loops until the queue is empty
        while(!q.isEmpty()){

            if (DEBUG){
                counter++;
                System.out.println("\n\tCounter 1: " + counter);
            }

            v = q.poll();

            u = new Node(0,0,0);   
            u.setLevel(v.getLevel() + 1);           // Set u to a child of v

            if((u.getLevel()-1) == items.length)    // Check if the level doesn't exceed the max possible 
                break;                              // index to avoid "Index out of bounds" error

            u.setWeight(v.getWeight() + items[u.getLevel()-1].getWeight()); // Set u's weights to (u's weight + v's weight)
            u.setProfit(v.getProfit() + items[u.getLevel()-1].getProfit()); // Set u's profit to (u's profit + v's profit)

            if (u.getWeight() <= totalWeightLimit && u.getProfit() > maxProfit) {

                maxProfit = u.getProfit();
                finalWeight = u.getWeight();

                list.add(items[u.getLevel()-1]);

                int sum = 0;
                for (int i = 0; i < list.size(); i++){
                    sum += list.get(i).getProfit();
                }
                if (sum != maxProfit)
                    list.remove(list.size()-2);

            }

            if (bound(u, items, totalWeightLimit, counter) > maxProfit)    // Adds child to queue if the bound of the child node is better than the maximum profit
                q.add(u);
            
            u = new Node(0,0,0);         // Reset head node
            u.setLevel(v.getLevel()+1);  // Assign the level of child's node to the level of new head node
            u.setWeight(v.getWeight());  // Assign the weight of child's node to the weight of new head node
            u.setProfit(v.getProfit());  // Assign the profit of child's node to the profit of new head node

            if (bound(v, items, totalWeightLimit, counter) > maxProfit)    // Adds child to queue if the bound of the child is better than the maximum profit
                q.add(u);
        }

        int[] Profit_Weight_Items = new int[list.size() + 2]; // An array of integers to store the result
        Arrays.fill(Profit_Weight_Items, 0); // Fill array with max values to ignore them when print

        for (int i = 0; i < list.size(); i++){
            Profit_Weight_Items[i+2] = list.get(i).getNumber();
        }

        Arrays.sort(Profit_Weight_Items); // Sort an array of items

        Profit_Weight_Items[0] = maxProfit; // Add profit as the first value (it was 0 before)
        Profit_Weight_Items [1] = finalWeight; // Add weight as the second value (it was 0 before)

        return Profit_Weight_Items; // Return the answer
    }

    /**
     *  The method to implement best-first branch and bound algorithm to
     *  to solve the knapsack problem. Return an array of integers, where
     *  the 1st element is the profit, 2nd element is the total weight, and
     *  everything else - all collected items.
     *
     *    @param    items Array of item objects
     *    @param    totalWeightLimit total weight limit
     *     
     *    @return   int[] array with the result: [0] - profit, [1] - weight, [2..n] - items.
     **/
    public static int[] BestFirst_BranchAndBound(Item[] items, int totalWeightLimit){
        int counter = 0; //A counter to measure performance

        ArrayList<Item> list = new ArrayList<Item>();

		PriorityQueue<Node> q = new PriorityQueue<Node>(new NodeComparator());; // Initialize PriorityQueue
        
        Node u = new Node(0,0,0);  // Root Node
        Node v = new Node(0,0,0);  // Temp Node

        int maxProfit = 0;   // Initialize profit
        int finalWeight = 0; // Initialize profit

        v.setBound(bound(u, items, totalWeightLimit, counter));

        q.add(u);  // Add the root

        //Loops until the queue is empty
        while(!q.isEmpty()){
            if (DEBUG== true){
                counter++;
                System.out.println("\n\tCounter 2: " + counter);
            }

            v = q.poll();

            u = new Node(0,0,0);
            u.setLevel(v.getLevel() + 1);           // Set u to a child of v

            if((u.getLevel()-1) == items.length)    // Check if the level doesn't exceed the max possible 
                break;                              // index to avoid "Index out of bounds" error

            u.setWeight(v.getWeight() + items[u.getLevel()-1].getWeight()); // Set u's weights to (u's weight + v's weight)
            u.setProfit(v.getProfit() + items[u.getLevel()-1].getProfit()); // Set u's profit to (u's profit + v's profit)

            if (u.getWeight() <= totalWeightLimit && u.getProfit() > maxProfit){
                maxProfit = u.getProfit();
                finalWeight = u.getWeight();

                list.add(items[u.getLevel()-1]);

                int sum = 0;
                for (int i = 0; i < list.size(); i++){
                    sum += list.get(i).getProfit();
                }
                if (sum != maxProfit)
                    list.remove(list.size()-2);    
                    
                int sumW = 0;
                for (int i = 0; i < list.size(); i++){
                    sumW += list.get(i).getWeight();
                }
                if (sumW != finalWeight){
                    list.remove(list.size()-1); 
                    int minNode = Integer.MAX_VALUE;
                    int k = 0;
                    for(int j = 0; j < items.length; j++){
                        if(minNode > items[j].getWeight() && !list.contains(items[j])){
                            minNode = items[j].getWeight();
                            k = j;
                        }
                    }
                    list.add((items[k]));
                }   
            }

            u.setBound(bound(u, items, totalWeightLimit, counter));
            
            if (u.getBound() > maxProfit)   // Adds child to queue if the bound of the child is better than the maximum profit
                q.add(u);
            
            u = new Node(0,0,0);         // Reset head node
            u.setLevel(v.getLevel()+1);  // Assign the level of child's node to the level of new head node
            u.setWeight(v.getWeight());  // Assign the weight of child's node to the weight of new head node
            u.setProfit(v.getProfit());  // Assign the profit of child's node to the profit of new head node
            u.setBound(bound(v, items, totalWeightLimit, counter));

            if (u.getBound() > maxProfit)    // Adds child to queue if the bound of the child is better than the maximum profit
                q.add(u);
        } 

        int[] Profit_Weight_Items = new int[list.size() + 2]; // An array of integers to store the result
        Arrays.fill(Profit_Weight_Items, 0); // Fill array with max values to ignore them when print

        for (int i = 0; i < list.size(); i++){
            Profit_Weight_Items[i+2] = list.get(i).getNumber();
        }

        Arrays.sort(Profit_Weight_Items); // Sort an array of items

        Profit_Weight_Items[0] = maxProfit; // Add profit as the first value (it was 0 before)
        Profit_Weight_Items [1] = finalWeight; // Add weight as the second value (it was 0 before)

        return Profit_Weight_Items; // Return the answer
    }

    /**
     *    The method that calculates the best case profit at a particular node. It calculated the bound by taking the profit
     *    of the current note and adding the profit of all other items that can fit in the knapsack.
     * 
     *    Essential for Best-First Search with Branch-and-Bound algorithm and Breadth-First Search with Branch-and-Bound algorithm.
     *
     *    @param    currentNode the current node
     *    @param    items the list of items
     *    @param    totalWeightLimit the weight limit
     *    @param    counter counter (for debug purposes)
     *
     *    @return   float - the current node's bound
     **/
    public static double bound (Node currentNode, Item[] items, int totalWeightLimit, int counter){
                
        if (DEBUG) {
            counter++;
            System.out.println("\n\t\tCounter:" + counter);
        };

        int j, k; // Variables for indexes
        int totalWeight; //A variable for total weight
        float result; // A variable to contain the bound

        if (currentNode.getWeight() >= totalWeightLimit)    //Return 0 if the weight of the node is more than weight limit
            return 0;
        else {
            result = currentNode.getProfit(); 
            j = currentNode.getLevel() + 1;
            totalWeight = currentNode.getWeight();
            
            while ( j <= items.length-1 && (totalWeight + items[j-1].getWeight()) <= totalWeight){  // Grab as many items a possible
                totalWeight = totalWeight + items[j-1].getWeight(); 
                result = result + items[j-1].getProfit();
                j++;
            }

            k = j-1;                                //Use the original formula to get the current bound
            if (k <= items.length-1 ) 
                result = result + ((totalWeightLimit - totalWeight) * ((items[k].getProfit())/(items[k].getWeight())));
            return result;

        }
    }

     /**
     *  The method implements dynamic programming algorithm to
     *  to solve the knapsack problem. The program returns void
     *  and prints the Matrix, Maximized Profit, Total Weight, and
     *  Item Numbers of the Chosen Items
     *
     *    @param    item Array of item objects
     *    @param    totalWeightLimit total weight limit
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
        int[][] matrix = new int[item.length + 1][totalWeightLimit + 1];

        //For each iteration, we will consider the most optimal solutions up to i+1-case
        // and optimize it until we reach the end of the loop.
        for (int i = 0; i <= item.length; i++){
            for (int j = 0; j <= totalWeightLimit; j++) {

                //Setting values at 0 weight and 0 profit to be 0
                if (i == 0 || j == 0){
                    matrix[i][j] = 0;
                } else {

                    //a[i-1][j] -> Element in the upper row
                    //a[i-1][j - weight[i]] + profit[i] -> if j - weight[i] > 0
                    // it results in the existent element in array
                    // and we add profit to the maximum profit item
                    int optimalValue = 0;

                    //Making sure that a[i-1][j - weight[i-1]] coordinate exists on the matrix
                    if ((j - weight[i-1]) >= 0){
                        optimalValue = matrix[i-1][j - weight[i-1]] + profit[i-1];
                    }

                    //Setting a[i][j] to the maximum
                    if (matrix[i-1][j] <= optimalValue){
                        matrix[i][j] = optimalValue;
                    } else {
                        matrix[i][j] = matrix[i-1][j];
                    }
                }
            }
        }

        //Printing the matrix
        for (int row = 0; row < matrix.length; row++){ //Cycles through rows
            for (int col = 0; col < matrix[row].length; col++){ //Cycles through columns
                System.out.printf("%5d", matrix[row][col]); //Printing the matrix elements
            }
            System.out.println(); //Makes a new row
        }


        //Maximized total profit
        int optimalProfit = matrix[item.length][totalWeightLimit];
        System.out.println("Maximized Profit: " + optimalProfit);


        //Maximized total weight
        int optimalWeight = 0;

        //for loop for going through the last row of the matrix and
        // determining
        for (int i = 0; i <= totalWeightLimit; i++) {
            if ((matrix[item.length][i]) == optimalProfit) {
                optimalWeight = i;
                break;
            }
        }

        System.out.println("Total Weight: " + optimalWeight);

        //Outputting the items to include
        int i = item.length;
        int j = optimalWeight;

        while (i > 0 && j > 0){
            if (matrix[i][j] == matrix[i - 1][j]){
                i--;
            }
            //If there is not same value of profit at the row above, then the item
            // should be chosen
            else {
                System.out.println("Item " + i + " is selected");
                i--;            //decrement i
                j -= weight[i]; //decrement weight
            }
        }

    }

    /**
     *  The class to store NodeComparator to compare Nodes in PriorityQueues.
     *
     **/
    static class NodeComparator implements Comparator<Node>{
        /**
         *  Returns difference between node's frequency.
         *  Required for PriorityQueue in the best-first branch and bound algorithm.
         *
         **/
        public int compare(Node a, Node b){
            return Double.compare(a.getBound(), b.getBound());
        }
    }
}
