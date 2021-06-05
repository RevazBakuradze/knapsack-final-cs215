package com.finalProject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;
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

    /**
     *  The main function initiates execution of this program.
     *    @param    String[] args not used in this program
     *              (but main methods always need this parameter)
     **/
    public static void main(String[] args) throws FileNotFoundException {

        //The location of input file with data sample
        File file = new File("input_data//sample.dat");

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

    }//main end
    
}//class end

/*
    USEFUL NOTES

    //create 3rd array having profit weight ration. swap and do swap in correspoding array
    
    //when you sort her sort there too

    //create object and sort by ratio

    //breadth first, depth first

    //6.1 new node for this alorithm implementation

    //root level -1 children would be 0 - first elments.
*/