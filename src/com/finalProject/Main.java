package com.finalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //The location of input file with data sample
        File file = new File("input_data//sample.dat");

        Scanner scanner = new Scanner(file);

        //numberOfItems represents the number of items in the problem set
        int numberOfItems = Integer.parseInt(scanner.nextLine());

        //totalWeightLimit represents the total weight limit
        int totalWeightLimit = Integer.parseInt(scanner.nextLine());

        //Three arrays storing number of an item, profit, and weight
        int[] itemNumberArray = new int[numberOfItems];
        int[] profitArray = new int[numberOfItems];
        int[] weightArray = new int[numberOfItems];

        Item[] items = new Item[numberOfItems];
        System.out.println("/n" + Arrays.toString(items));

        //For loop for inputting values from file to the arrays
        for (int i = 0; i < numberOfItems; i++) {

            //Scanning line
            String line = scanner.nextLine();

            //Breaking up the line with spaces and storing words in array
            String seperatedWords[] = line.split(" ", 3);
            
            int number = Integer.parseInt(seperatedWords[0]);
            int profit = Integer.parseInt(seperatedWords[1]);
            int weight = Integer.parseInt(seperatedWords[2]);

            items[i] = new Item(number, profit, weight);
            
            System.out.println("\n\t" + items[i]);

        }

        for (int i = 0; i < items.length; i++)
            System.out.println("\n\t" + items[i]);
        }

        //create 3rd array having profit weight ration. swap and do swap in correspoding array
    //when you sort her sort there too

    //create object and sort by ratio

    //breadth first, depth first

    //6.1 new node for this alorithm implementation

    //root level -1 children would be 0 - first elments.
}
