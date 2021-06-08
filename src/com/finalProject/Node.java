package com.finalProject;

/**
 *  CS215, Final Programming Project: Knapsack 
 *
 *  Node class.
 * 
 *  
 *  @author Aleksandr Molchagin
 *  @author Revaz Bakuradze
 *  @author Duurenbayar Ulziiduuren 
 * 
 *  @version June 5 2021
 */
public class Node {
    private int level;
    private int profit;
    private int weight;
    private double bound;

    /**
     * Constructor of the Node, taking the following parameters:
     *
     * @param level
     * @param profit
     * @param weight
     * */
    public Node(int level, int profit, int weight) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = 0;
    }

    /**
     * Getter of level
     *
     * @return int level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Setter of level
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Getter of profit
     *
     * @return int level
     */
    public int getProfit() {
        return profit;
    }

    /**
     * Setter of profit
     *
     * @param profit
     */
    public void setProfit(int profit) {
        this.profit = profit;
    }

    /**
     * Getter of weight
     *
     * @return int weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Setter of weight
     *
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Getter of bound
     *
     * @return int bound
     */
    public double getBound() {
        return bound;
    }

    /**
     * Setter of bound
     *
     * @param bound
     */
    public void setBound(double bound) {
        this.bound = bound;
    }
}
