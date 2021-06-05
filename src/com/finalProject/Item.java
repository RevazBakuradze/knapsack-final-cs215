package com.finalProject;

public class Item {
    private int number;
    private int profit;
    private int weight;
    private double ratio;

    public Item(int number, int profit, int weight) {
        this.number = number;
        this.profit = profit;
        this.weight = weight;
        if (weight != 0)
            this.ratio = ((double)profit) / ((double)weight);
        else
            this.ratio = 0;
    }

    public Item() {
        this.number = 0;
        this.profit = 0;
        this.weight = 0;
        if (weight != 0)
            this.ratio = ((double)profit) / ((double)weight);
        else
            this.ratio = 0;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
        if (weight != 0)
            this.ratio = ((double)profit) / ((double)weight);
        else
            this.ratio = 0;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
        if (weight != 0)
            this.ratio = ((double)profit) / ((double)weight);
        else
            this.ratio = 0;
    }

    public double getRatio() {
        return ratio;
    }

    @Override
    public String toString() {
        return "Item " + number + ": Profit - " + profit + ", Weight - " + weight + ", Ratio " + ratio;
    }
}


