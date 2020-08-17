/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleharmonysearch;

import java.util.Comparator;

/**
 *
 * @author Sadeem
 */
public class WeightComparator implements Comparator<Knapsackitems> {
    @Override
    public int compare(Knapsackitems o1, Knapsackitems o2) {
        return Double.compare(o1.getWeight(),o2.getWeight());
    }
}
