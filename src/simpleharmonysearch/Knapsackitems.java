/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleharmonysearch;

import java.text.DecimalFormat;

/**
 *
 * @author Sadeem
 * this class is created to organize the inner structure of each item of the knapsack problem
 * each item is composed of:
 * -Weight(volume) value (v)
 * -Profit value (p)
 * -ratio = profit/weight (u)
 * -related x (isIn=>(0 or 1))
 */
    public class Knapsackitems {

    private double weight, profit, ratio;
    private int IsIn;
    final private DecimalFormat df2 = new DecimalFormat(".##");

    public Knapsackitems(double weight, double profit) {
        this.weight = weight;
        this.profit = profit;
        this.ratio = profit/weight;
        this.IsIn = 1;
        
    }

    public int getIsIn() {
        return IsIn;//x
    }

    public void setIsIn(int IsIn) {
        this.IsIn = IsIn;//x
    }

    public double getWeight() {
        return weight;//v
    }

    public void setWeight(double weight) {
        this.weight = weight; //v
    }

    public double getProfit() {
        return profit; //p
    }

    public void setProfit(double profit) {
        this.profit = profit; //p
    }

    public double getRatio() {
        
        df2.format(ratio);
        return ratio; //u
    }

    @Override
   public String toString() {
        return ("v: "+this.getWeight()+
                    " ,p: "+ this.getProfit() +
                    " ,u: " + df2.format(this.getRatio()));
   }


}
