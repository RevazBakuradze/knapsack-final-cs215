package com.finalProject;
import java.util.ArrayList;

/**
 *  CS215, Final Programming Project: Knapsack 
 *
 *  Node class.
 * 
 *  
 *  @author Aleksandr Molchagin
 *  @author Revaz Bakuradze
 *  @version June 5 2021
 */

public class Node {
    private int level;
    private int profit;
    private int weight;
    private double bound;
    public ArrayList<Item> items;

    public Node(int level, int profit, int weight, ArrayList<Item> items) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = 0;
        this.items = items;
    }

    public Node(int level, int profit, int weight, double bound) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = bound;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item i) {
        this.items.add(i);
    }

    public void removeItem() {
        this.items.remove(items.size()-1);
    }

    public void setItems(ArrayList<Item> list) {
        this.items = list;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getBound() {
        return bound;
    }

    public void setBound(double bound) {
        this.bound = bound;
    }
}
