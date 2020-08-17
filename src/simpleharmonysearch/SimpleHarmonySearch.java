/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleharmonysearch;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Sadeem
 */
public class SimpleHarmonySearch {

    //parameters
//    int HMS = 5; Harmony Memory Size
//    double HMCR = 0.75; Harmony Memory Consideration
//    int n = 10; Problem dimention
//    int KP_max = 295; KP_max_size
//    int indexCounter; ArrayList item index
//    double HM[][] = new double[n][HMS];
    //Input items
//    ArrayList items = new ArrayList<Knapsackitems>();
//    ArrayList removedItems = new ArrayList<Knapsackitems>();
//    ArrayList S3 = new ArrayList<Knapsackitems>();
//    ArrayList S4 = new ArrayList<Knapsackitems>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // STEP 1: variable initialization
        int n = 23; 
                //15;//KP3
                //23;//KP8
                //10; KP1
        int KP_max = 10000 ; //KP8
                //375 ;//KP5
                //269; //KP1        
        int indexCounter;
        ArrayList<Knapsackitems> KP_solntion = new ArrayList<>();
        int HMS = 6; //extra row added
        double HMCR = 0.75;//for n<100
        double HM[][] = new double[HMS][n];//r by c

        //Input items
        ArrayList<Knapsackitems> items = new ArrayList<>();
        ArrayList<Knapsackitems> removedItems = new ArrayList<>();
        ArrayList<Knapsackitems> S3 = new ArrayList<>();
        ArrayList<Knapsackitems> S4 = new ArrayList<>();
//        ArrayList<Knapsackitems> knapSack = new ArrayList<>();

//        items.add(new Knapsackitems(95, 55));
//        items.add(new Knapsackitems(4, 10));
//        items.add(new Knapsackitems(60, 47));
//        items.add(new Knapsackitems(32, 5));
//        items.add(new Knapsackitems(23, 4));
//        items.add(new Knapsackitems(72, 50));
//        items.add(new Knapsackitems(80, 8));
//        items.add(new Knapsackitems(62, 61));
//        items.add(new Knapsackitems(65, 85));
//        items.add(new Knapsackitems(46, 87)); //KP1

//        System.out.println("size:  "+items.size());

//        items.add(new Knapsackitems(56.358531, 0.125126));
//        items.add(new Knapsackitems(80.874050 , 19.330424 ));
//        items.add(new Knapsackitems(47.987304 , 58.500931 ));
//        items.add(new Knapsackitems(89.596240 , 35.029145 ));
//        items.add(new Knapsackitems(74.660482 , 82.284005 ));
//        items.add(new Knapsackitems(85.894345 , 17.410810 ));
//        items.add(new Knapsackitems(51.353496 , 71.050142 ));
//        items.add(new Knapsackitems(1.498459 , 30.399487 ));
//        items.add(new Knapsackitems(36.445204 , 9.140294 ));
//        items.add(new Knapsackitems(16.589862 , 14.731285 ));
//        items.add(new Knapsackitems(44.569231 , 98.852504 ));
//        items.add(new Knapsackitems(0.466933 , 11.908322 ));
//        items.add(new Knapsackitems(37.788018 , 0.891140 ));
//        items.add(new Knapsackitems(57.118442 , 53.166295 ));
//        items.add(new Knapsackitems(60.716575 , 60.176397 ));
        //KP5
//        n=items.size();
//KP8
        items.add(new Knapsackitems(983, 981));
        items.add(new Knapsackitems(982, 980));
        items.add(new Knapsackitems(981, 979));
        items.add(new Knapsackitems(980, 978));
        items.add(new Knapsackitems(979, 977));
        items.add(new Knapsackitems(978, 976));
        items.add(new Knapsackitems(488, 487));
        items.add(new Knapsackitems(976, 974));
        items.add(new Knapsackitems(972, 970));
        items.add(new Knapsackitems(486, 485));
        items.add(new Knapsackitems(486, 485));
        items.add(new Knapsackitems(958, 970));
        items.add(new Knapsackitems(972, 970));
        items.add(new Knapsackitems(458, 484));
        items.add(new Knapsackitems(485, 484));
        items.add(new Knapsackitems(969, 976));
        items.add(new Knapsackitems(966, 974));
        items.add(new Knapsackitems(483, 482));
        items.add(new Knapsackitems(964, 962));
        items.add(new Knapsackitems(963, 961));       
        items.add(new Knapsackitems(961, 959));
        items.add(new Knapsackitems(958, 958));
        items.add(new Knapsackitems(959, 857));
//        n=items.size();



        System.out.println("Problem items:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }
        System.out.println("***********************************************");
        // Generate Harmoney Memory
        GenerateHM(HMS, n, HM);
        System.out.println("HM is now generated !");
        //Step2: total items volume
        double Vt = 0; //items total volume
        double Vc = 0; // remaining items volume

        System.out.println("\nSTEP 1 & 2:");
        System.out.println("***********************************************");
        System.out.println("Calculating the total weight of all problem items and the difference between KP_max and Vt (Vc) ");
        Vt = getVt(items);
        System.out.println("Vt = " + Vt);
        Vc = Vt - KP_max; //remainder (if + then it exceeds the KP_max otherwise is less or equal)
        System.out.println("Vc = " + Vc);

        //STEP 3: Create S1
        //Sort by ratio
        System.out.println("\nSTEP 3:");
        System.out.println("***********************************************\n");
        System.out.println("Sorted problem by ratio:");
        Collections.sort(items, new RatioComparator());
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }

        System.out.println("\nSTEP 4:");
        System.out.println("***********************************************\n");

        //STEP 4: remove items from knapsack and check volume
                //eleminating items that with least profit density until total weight doesn't exceed
        System.out.println("Original problem size = " + items.size());
        
        indexCounter = 1;
        while (Vc > 0) {
            Knapsackitems toCheck = (Knapsackitems) items.get(indexCounter);
            if (toCheck.getIsIn() == 1) {  // if item is in knapsack
                {   Vc = Vc - toCheck.getWeight();
                    toCheck.setIsIn(0);
                    removedItems.add(toCheck);
                    items.remove(indexCounter);
               
                }
                System.out.println(indexCounter + " - " + "Vc: " + Vc);
                indexCounter++;
                    // System.out.println("1");
            }
           

        }
        System.out.println("Calculating Vc after getting back items from RemovedItems list:");
        System.out.println(indexCounter + "- " + "Vc: " + Vc);

//        for (int i = 0; i < items.size(); i++) {
//            System.out.println(items.get(i));
//        }
        System.out.println("Removed items:");
        for (int i = 0; i < removedItems.size(); i++) {
            System.out.println(removedItems.get(i));
        }

        System.out.println("Remaining items:");
        Collections.sort(removedItems, new WeightComparator());
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }

        System.out.println("filtered items size = " + items.size());
        System.out.println("REMOVED items size = " + removedItems.size());

        Vt = getVt(items);
        System.out.print("Total weight (Vt) after eleminaion:     ");
        System.out.println("Vt = " + Vt);

        //STEP 5 cnt.: negate Vc and check wheather the smallest item in remmoved items left is within the range of Vl
        System.out.println("\nSTEP 5:");
        System.out.println("***********************************************\n");

        double Vl = -1 * Vc;

        System.out.println("current Problem Size = " + items.size());

        indexCounter = 0;
        Knapsackitems toCheck = (Knapsackitems) removedItems.get(indexCounter);
        while (indexCounter < removedItems.size() && Vl > toCheck.getWeight()) {
            //S3 contains all the removes items that have weights less than Vl value
            S3.add(toCheck);
            //  knapSack.add(toCheck);//test
            removedItems.remove(indexCounter);
            indexCounter++;
            toCheck = (Knapsackitems) removedItems.get(indexCounter);
        }
        System.out.println(indexCounter + " - " + "Vl: " + Vc);

        //STEP 6&7: sort S3 acs. based on their Weights
        if (!S3.isEmpty()){
        System.out.println("\nSTEP 6 & 7:");
        System.out.println("***********************************************\n");
        System.out.println("sorting S3 items based on their weights:");
        
        Collections.sort(S3, new WeightComparator());
        for (int i = 0; i < S3.size(); i++) {
            System.out.println(S3.get(i));
        }
        
        //STEP 8: getting the corresponding sequence S4 of those items contained in S3 based on their relative profit
        //desity (ratio) in acsending order 
        System.out.println("\nSTEP 8:");
        System.out.println("***********************************************");
        indexCounter = 0;

        Knapsackitems toCheck2 = (Knapsackitems) S3.get(indexCounter);
        while (indexCounter < S3.size() && Vl > toCheck2.getWeight()) {
            S4.add(toCheck2);
            indexCounter++;
//               toCheck2 = (Knapsackitems)S3.get(indexCounter);
        }//adding items to S4

        //sorting S4 items based on their ratio      
        System.out.println("\nsorting S4 items based on their ratio:");
        Collections.sort(S4, new RatioComparator());
        for (int i = 0; i < S4.size(); i++) {
            System.out.println(S4.get(i));
        }

        //STEP 9: Insert the items into the knapsack following the tab sequence S4 until there is no space in the knapsack 
        System.out.println("\nSTEP 9: Inserting the items into the knapsack");
        System.out.println("***********************************************");
        indexCounter = S4.size() - 1;
        Knapsackitems toCheck3;
        while (indexCounter >= 0 && Vl > 0) {
            toCheck3 = (Knapsackitems) S4.get(indexCounter);
            if (toCheck3.getIsIn() == 0 && Vl > toCheck3.getWeight()) {
                toCheck3.setIsIn(1);
                Vl = Vl - toCheck3.getWeight();
                items.add(toCheck3);
            }//end if
            indexCounter--;
        
        }//end while
        System.out.println("Repair Work results:");
        }else {
        System.out.println("***********************************************");
        System.out.println("Repair Work stopped, Empty S3 list, results:");
        }
        
        Vt = getVt(items);
        System.out.println("Vt = " + Vt);
        double final_Vc = Vt - KP_max;
        System.out.println("Vc = " + final_Vc);
        System.out.println("Knapsack items:");

        Collections.sort(items, new WeightComparator());
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }//sorting items by their weights

        //now merging both arrays to get the current feasible solution
        Knapsackitems solutions;
        for (int ind1 = 0; ind1 < items.size(); ind1++) {

            solutions = (Knapsackitems) items.get(ind1);
            KP_solntion.add(solutions);
        }
        for (int ind2 = 0; ind2 < removedItems.size(); ind2++) {

            solutions = (Knapsackitems) removedItems.get(ind2);
            KP_solntion.add(solutions);
        }
        Collections.sort(KP_solntion, new RatioComparator());//sort them by their ratio

        System.out.println("\n\nProblem items:");
        for (int i = 0; i < KP_solntion.size(); i++) {
            System.out.println(KP_solntion.get(i));
        }

        double solution_x[] = new double[n];
        System.out.print("Current X-best (");
        Knapsackitems obj;
        for (int i = 0; i < KP_solntion.size(); i++) {
            obj = (Knapsackitems) KP_solntion.get(i);
            solution_x[i] = obj.getIsIn();
            System.out.print((int) solution_x[i] + " ");
        }
        System.out.println(")");

        int ind = 0; // items index
        double total_weights[] = new double[1000];

        for(int NI=0 ; NI<1000 ; NI++)
        {  for (int i = 0; i < 5; i++) {
            while (ind < n) {

                if (HM[i][ind] <= HMCR) {
                    //x_new = x_r1 + (-1)^(x_r1)*|x_best - x_r2|
                    solution_x[ind] = HM[i][ind] + Math.pow(-1, HM[i][ind]) * Math.abs(HM[i][ind] - HM[i+1][ind]);
                    HM[i][ind] = solution_x[ind];
                } else {
                    if (HM[i][ind] <= 0.5) {
                        solution_x[ind] = 0;
                        HM[i][ind] = solution_x[ind];
                    } else {
                        solution_x[ind] = 1;
                        HM[i][ind] = solution_x[ind];
                    }
                }
//                System.out.print((int) solution_x[ind] + " ,");
                ind++; //next item
            }
            
            ind = 0;
            if (getVt2(KP_solntion,solution_x)<=KP_max &&getVt2(KP_solntion,solution_x)>=Vt ) //only if the provided solution doesn't exceed the KP max. size
            {for (int c = 0; c < KP_solntion.size(); c++) {
                obj = (Knapsackitems) KP_solntion.get(c);
                obj.setIsIn((int) solution_x[c]);
                System.out.print((int) solution_x[c] + " ,");
            }
            total_weights[i] = getVt(KP_solntion);//an array of all solutions improvised by HM
            System.out.println("Vt: " + (int) total_weights[i]+"  ,Pt: "+getProfit(KP_solntion));
            } 
            GenerateHM(HMS, n, HM);}       
    }

//        System.out.println();

        //now we need to find the best solution vector of all given solutions
    }

    public static void GenerateHM(int nf, int HMSf, double[][] HM) {

       // System.out.println("HM is now generated !");
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < HMSf; j++) {
                HM[i][j] = Math.random();
               // System.out.print(HM[i][j] + " ");
            }
           // System.out.println();
        }

    }

    public static double getVt(ArrayList<Knapsackitems> array) {

        double Vt = 0;
        Knapsackitems f;
        for (int i = 0; i < array.size(); i++) {
            f = (Knapsackitems) array.get(i);
            if (f.getIsIn() == 1) {
                //check if the item is in the knapsack the indication is by it's x value (isIn == true)
                Vt = Vt + f.getWeight();
            }
        }
        return Vt;
    }
    
    public static double getVt2(ArrayList<Knapsackitems> array , double ar_x []) {

        double Vt = 0;
        Knapsackitems f;
        for (int i = 0; i < array.size(); i++) {
            f = (Knapsackitems) array.get(i);
            if (ar_x [i] == 1) {
                //check if the item is in the knapsack the indication is by it's x value (isIn == true)
                Vt = Vt + f.getWeight();
            }
        }
        return Vt;
    }

    public static double getProfit(ArrayList<Knapsackitems> array) {
        double Pt = 0;
        Knapsackitems f;
        
        for(int i=0;i< array.size();i++){
            f = (Knapsackitems) array.get(i);
            if (f.getIsIn() == 1)
            {
                Pt=Pt + f.getProfit();
            }
        }
        return Pt;
    }

}
