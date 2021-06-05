package com.finalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //File location might change depending on the address of the file
        File file = new File("input_data//sample.dat");

        Scanner scanner = new Scanner(file);

        //n represents the number of items in the problem set
        int numberOfItems = Integer.parseInt(scanner.nextLine());

        //w represents the total weight limit
        int totalWeightLimit = Integer.parseInt(scanner.nextLine());

        //Three arrays storing number of an item, profit, and weight
        int[] itemNumberArray = new int[numberOfItems];
        int[] profitArray = new int[numberOfItems];
        int[] weightArray = new int[numberOfItems];


        //For loop for inputting values from file to the arrays
        for (int i = 0; i < numberOfItems; i++) {

            //Scanning line
            String line = scanner.nextLine();

            //Breaking up the line with spaces and storing words in array
            String seperatedWords[] = line.split(" ", 3);

            itemNumberArray[i] = Integer.parseInt(seperatedWords[0]);

            profitArray[i] = Integer.parseInt(seperatedWords[1]);
            weightArray[i] = Integer.parseInt(seperatedWords[2]);
        }

        System.out.println(Arrays.toString(itemNumberArray));
        System.out.println(Arrays.toString(profitArray));
        System.out.println(Arrays.toString(weightArray));

        }

        //create 3rd array having profit weight ration. swap and do swap in correspoding array
    //when you sort her sort there too

    //create object and sort by ratio

    //breadth first, depth first

    //6.1 new node for this alorithm implementation

    //root level -1 children would be 0 - first elments.
}
