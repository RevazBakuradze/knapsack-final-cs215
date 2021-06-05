package com.finalProject;
/**
 *  CS215, Final Programming Project: Knapsack 
 *
 *  Item class.
 *  
 *  A class to store the data for each item: its original number, profit, weight, profit/weight ratio.
 *
 *  @author Aleksandr Molchagin
 *  @author Revaz Bakuradze
 *  @version June 5 2021
 */
public class Item {
    private int number;     //Represents the original object's number
    private int profit;     //Represents profit
    private int weight;     //Represents weight
    private double ratio;   //Represents profit/weight ratio

     /**
     * Constructs a new object of this class with parameters.
     * 
     *      @param   number    line's number
     *      @param   profit    profit
     *      @param   weight    weight
     */
    public Item(int number, int profit, int weight) {
        this.number = number;
        this.profit = profit;
        this.weight = weight;
        if (weight != 0)
            this.ratio = ((double)profit) / ((double)weight);
        else
            this.ratio = 0;    
        }

     /**
     * Constructs a new object of this class with NO parameters.
     */
    public Item() {
        this.number = 0;
        this.profit = 0;
        this.weight = 0;
        if (weight != 0)
            this.ratio = ((double)profit) / ((double)weight);
        else
            this.ratio = 0;
    }

    /**
     * Gets the value number.
     *      @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets a new the value as the number.
     *      @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Gets the value of profit.
     *      @return profit
     */
    public int getProfit() {
        return profit;
    }

    /**
    * Sets a new the value as the profit and updates the ratio if possible.
    *      @param profit
    */
    public void setProfit(int profit) {
        this.profit = profit;
        if (weight != 0)
            this.ratio = ((double)profit) / ((double)weight);
        else
            this.ratio = 0;
    }

     /**
     * Gets the value of weight.
     *      @return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
    * Sets a new the value as the weight and updates the ratio if possible.
    *      @param weight
    */
    public void setWeight(int weight) {
        this.weight = weight;
        if (weight != 0)
            this.ratio = ((double)profit) / ((double)weight);
        else
            this.ratio = 0;
    }

    /**
     * Gets the value profit/weight ratio.
     *      @return profit/weight ratio
     */
    public double getRatio() {
        return ratio;
    }

    /**
     * Prints an Item including its number, profit, weight and ratio.
     */
    @Override
    public String toString() {
        return "Item " + number + ": \n\tProfit: " + profit + "\n\tWeight: " + weight + "\n\tRatio: " + ratio;
    }
}
