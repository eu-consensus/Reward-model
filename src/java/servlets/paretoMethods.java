/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author anu
 */
public class paretoMethods {

    //==========================DISTANCE======================================//
    private static double minkowskiDistance(double[] table1, double[] table2, double p) {
        //1<p<2   1=manhattan 2= euclidean oo=chebychev 
        double dist = 0;
        double sum = 0;
        for (int i = 0; i < table1.length && i < table2.length; i++) {
            sum += Math.pow(Math.abs(table1[i] - table2[i]), p);
        }
        dist = Math.pow(sum, 1.0 / p);
        return dist;
    }

    private static double mahalanobisDistance(double[] table1, double[] table2) {
        double dist = 0;

        return dist;
    }

    private static double hammingDistance(double[] table1, double[] table2) {
        double dist = 0;
        for (int i = 0; i < table1.length && i < table2.length; i++) {
            if (table1[i] == table2[i]) {
                dist += 1;
            }
        }
        return dist;
    }

    private static double euclidianDistance(double[] table1, double[] table2) {

        double dist = 0;
        for (int i = 0; i < table1.length && i < table2.length; i++) {
            dist += Math.pow((table2[i] - table1[i]), 2);
        }
        dist = Math.sqrt(dist);
        return dist;
    }

    private static double standardizedEuclideanDistance(double[] table1, double[] table2, double[] s) {
        //euclidean distance with standart deviation removes problem of scale among vector values eg. (1x<5, 1000<y<6000)
        double dist = 0;
        double param = 0;

        for (int i = 0; i < table1.length && i < table2.length && i < s.length; i++) {
            param = table2[i] - table1[i] / s[i];
            dist += Math.pow(param, 2);
        }
        dist = Math.sqrt(dist);
        return dist;
    }

    private static double chebychevDistance(double[] table1, double[] table2) {
        double dist = -1;
        for (int i = 0; i < table1.length && i < table2.length; i++) {
            double dis = Math.abs(table2[i] - table1[i]);
            if (dist < dis) {
                dist = dis;
            }
        }
        return dist;
    }

    private static double manhattanDistance(double[] table1, double[] table2) {

        double dist = 0;
        for (int i = 0; i < table1.length && i < table2.length; i++) {
            dist += Math.abs(table2[i] - table1[i]);
        }
        return dist;
    }

    private static double distanceCosine(double[] table1, double[] table2) {
        double distance = 0;
        for (int i = 0; i < table1.length && i < table2.length; i++) {
            double semiDistance1 = 0;
            double semiDistance2 = 0;
            for (int j = 0; j < table1.length; j++) {
                semiDistance1 += Math.pow(table1[j], 2);
            }
            semiDistance1 = Math.sqrt(semiDistance1);
            for (int j = 0; j < table2.length; j++) {
                semiDistance2 += Math.pow(table2[j], 2);
            }
            semiDistance2 = Math.sqrt(semiDistance2);
            semiDistance1 *= semiDistance2;
            distance += (table1[i] * table2[i]) / semiDistance1;
        }
        return distance * (-1);
    }

    private static double[] simpleDistance(double[] a, double[] b) {
        double[] dist = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            dist[i] = Math.abs(a[i] - b[i]);
        }
        return dist;
    }
    //------------------------------------------------------------------------------------------//

    public static class polComparator implements Comparator<policy> {

        public int compare(policy p1, policy p2) {
            return Double.compare(p1.getDistance(), p2.getDistance());
        }
    }

    public static class polComparator2 implements Comparator<policy> {

        public int compare(policy p1, policy p2) {
            return Integer.compare(p1.getDominated(), p2.getDominated());

        }
    }

    public static void paretoL(List<policy> theList) {

        for (int i = 0; i < theList.size(); i++) {
            double[] data = theList.get(i).getObjectives();

            for (int j = i + 1; j < theList.size(); j++) {
                double[] element = theList.get(j).getObjectives();
                boolean bigger = true;
                boolean smaller = true;
                boolean equal = true;

                for (int w = 0; w < data.length; w++) {

                    if (data[w] > element[w]) {
                        bigger = true && bigger;
                        smaller = false;
                        equal = false;

                    } else if (data[w] == element[w]) {
                        equal = true && equal;
                    } else {
                        bigger = false;
                        smaller = true && smaller;
                        equal = false;
                    }

                }
                if (!equal) {
                    if (bigger) {
                        theList.get(j).setDominated(theList.get(j).getDominated() + 1);

                        /*  System.out.println("bigger");
                         for (int w = 0; w < theList.get(i).getData().length; w++) { 
                         System.out.print(theList.get(i).getData()[w]);
                         System.out.print(theList.get(j).getData()[w]);
                         System.out.println();
                         }*/
                    }
                    if (smaller) {
                        theList.get(i).setDominated(theList.get(i).getDominated() + 1);
                        /* System.out.println("smaller");
                         for (int w = 0; w < theList.get(i).getData().length; w++) {
                         System.out.print(theList.get(i).getData()[w]);
                         System.out.print(theList.get(j).getData()[w]);
                         System.out.println();
                         }
                         */
                    }
                }
            }
        }

    }

    public static void putProfits(List<policy> myList) {
        int size = myList.size();
        // int opt19percent = (int) Math.round(size * 0.19);
        int opt5percent = (int) Math.round(size * 0.05);
        int count = 0;
        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i).getDominated() == 0.0) {
                myList.get(i).setScore(3.0);
                count = i;
            }
            if (i < opt5percent + count && i > count) {
                myList.get(i).setScore(2.5);
            }
            if (i < (opt5percent * 2) + count && i >= opt5percent + count) {
                myList.get(i).setScore(2.0);
            }
            if (i < (opt5percent * 3) + count && i >= (opt5percent * 2) + count) {
                myList.get(i).setScore(1.5);
            }
            if (i < (opt5percent * 4) + count && i >= (opt5percent * 3) + count) {
                myList.get(i).setScore(1.0);
            }
            if (i < (opt5percent * 5) + count && i >= (opt5percent * 4) + count) {
                myList.get(i).setScore(0.5);
            }
            // if(i>=((opt5percent*5)+count))
            // myList.get(i).setprofit(0.0);

        }
    }

    public static void putProfits2(List<policy> myList) {

        //in this function we rank solutions according to their domination count 
        //we seperate dominant solutions in the solution pool and mark them with 3.0 points 
        // for all the rest we evaluate the domination count in the pool of solutions and start distributing points starting from 2.5 points 
        //we only rank 55% of solutions the rest 45% of bottom solutions are marked with 0 
        //manage how many points they get by setting step=score_points/solutions_left_to_score score_points=score_points-step
        int size = myList.size();
        // int opt19percent = (int) Math.round(size * 0.19);
        int unranked = (int) Math.round(size * 0.55);

        double pdom = 1;
        double opt_score_points = 300;
        double score_points = opt_score_points - 50;
        for (int i = 0; i < myList.size(); i++) {

            if (myList.get(i).getDominated() == 0.0) {
                myList.get(i).setScore(opt_score_points);
                unranked--;
            }
            if (pdom == myList.get(i).getDominated() && unranked != 0) {
                myList.get(i).setScore(Math.round(score_points));
                unranked--;
            }
            if (pdom < myList.get(i).getDominated() && unranked != 0) {
                pdom = myList.get(i).getDominated();
                score_points -= score_points / unranked;
                myList.get(i).setScore(Math.round(score_points));
                unranked--;
            }
        }
    }

    public static List<policy> dominationBYcategory(List<policy> theList) {
        Hashtable<String, List<policy>> myhash = new Hashtable<>();
        List<policy> temp = new ArrayList<>();
        List<policy> gatherList = new ArrayList<>();
        Iterator<policy> iterator = theList.iterator();
        while (iterator.hasNext()) {
            policy obj = iterator.next();
            String keyName = obj.getOrder();
            //an uparxei key prosthese to object sti lista alliws 
            //ftiaxe nea lista me key kai prosthese to prwto stoixeio
            if (myhash.containsKey(keyName)) {
                temp = myhash.get(keyName);
                temp.add(obj);
            } else {
                List<policy> tempList = new ArrayList<>();
                tempList.add(obj);
                myhash.put(keyName, tempList);
            }
        }
        Enumeration<String> e = myhash.keys();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            temp = myhash.get(key);
            for (int i = 0; i < temp.size(); i++) {
                double[] data = temp.get(i).getObjectives();

                for (int j = i + 1; j < temp.size(); j++) {
                    double[] element = temp.get(j).getObjectives();
                    boolean bigger = true;
                    boolean smaller = true;
                    boolean equal = true;

                    for (int w = 0; w < data.length; w++) {

                        if (data[w] > element[w]) {
                            bigger = true && bigger;
                            smaller = false;
                            equal = false;

                        } else if (data[w] == element[w]) {
                            equal = true && equal;
                        } else {
                            bigger = false;
                            smaller = true && smaller;
                            equal = false;
                        }

                    }
                    if (!equal) {
                        if (bigger) {
                            temp.get(j).setDominatedbycategory(temp.get(j).getDominatedbycategory() + 1);

                            /*  System.out.println("bigger");
                             for (int w = 0; w < theList.get(i).getData().length; w++) { 
                             System.out.print(theList.get(i).getData()[w]);
                             System.out.print(theList.get(j).getData()[w]);
                             System.out.println();
                             }*/
                        }
                        if (smaller) {
                            temp.get(i).setDominatedbycategory(temp.get(i).getDominatedbycategory() + 1);
                            /* System.out.println("smaller");
                             for (int w = 0; w < theList.get(i).getData().length; w++) {
                             System.out.print(theList.get(i).getData()[w]);
                             System.out.print(theList.get(j).getData()[w]);
                             System.out.println();
                             }
                             */
                        }
                    }
                }

            }
            gatherList.addAll(temp);
        }
        return gatherList;
    }

    //returning 1 if it does what the function implies. 
    //eg. if a is smaller than b then a is minimizing the objective in comparison to b 
    public static int minimizationofObjective(double a, double b) {
        if (a > b) {
            return -1;
        } else if (a == b) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int maximizationofObjective(double a, double b) {
        if (a > b) {
            return 1;
        } else if (a == b) {
            return 0;
        } else {
            return -1;
        }
    }

    //create an array with what to do with each objective criteria (either maximize or minimize)
    public static boolean[] minmax(String minmaxstr) {
        char[] minmaxc = minmaxstr.toCharArray();
        boolean[] minmax = new boolean[minmaxc.length];
        for (int i = 0; i < minmax.length; i++) {
            minmax[i] = false;
        }
        for (int i = 0; i < minmaxc.length; i++) {
            if (minmaxc[i] == '+') {
                minmax[i] = true;
            }
        }
        return minmax;
    }

//Finding pareto frontiers provided the minimization or maximization criteria of each objective O(n^2)
    public static List<policy> paretoM(List<policy> theList, boolean[] minmax) {

        for (int i = 0; i < theList.size(); i++) {
            double[] data = theList.get(i).getObjectives();

            for (int j = i + 1; j < theList.size(); j++) {
                double[] element = theList.get(j).getObjectives();
                boolean bigger = true;
                boolean smaller = true;
                boolean equal = true;

                for (int w = 0; w < data.length; w++) {
                    if (minmax[w]) {
                        int result = maximizationofObjective(data[w], element[w]);
                        if (result == 1) {
                            bigger = true && bigger;
                            smaller = false;
                            equal = false;
                        } else if (result == 0) {
                            equal = true && equal;
                        } else {
                            bigger = false;
                            smaller = true && smaller;
                            equal = false;
                        }
                    } else {
                        int result = minimizationofObjective(data[w], element[w]);
                        if (result == 1) {
                            bigger = true && bigger;
                            smaller = false;
                            equal = false;
                        } else if (result == 0) {
                            equal = true && equal;
                        } else {
                            bigger = false;
                            smaller = true && smaller;
                            equal = false;
                        }
                    }
                }
                if (!equal) {
                    if (bigger) {
                        theList.get(j).setDominated(theList.get(j).getDominated() + 1);
                        theList.get(j).setSi(theList.get(i).getPolicyName());
                        /*  System.out.println("bigger");
                         for (int w = 0; w < theList.get(i).getData().length; w++) { 
                         System.out.print(theList.get(i).getData()[w]);
                         System.out.print(theList.get(j).getData()[w]);
                         System.out.println();
                         }*/
                    }
                    if (smaller) {
                        theList.get(i).setDominated(theList.get(i).getDominated() + 1);
                        theList.get(i).setSi(theList.get(j).getPolicyName());
                        /* System.out.println("smaller");
                         for (int w = 0; w < theList.get(i).getData().length; w++) {
                         System.out.print(theList.get(i).getData()[w]);
                         System.out.print(theList.get(j).getData()[w]);
                         System.out.println();
                         }
                         */
                    }
                }
            }
        }
        return theList;
    }

    public static Hashtable<String, List<policy>> createLists(List<policy> theList) {

        Hashtable<String, List<policy>> finalL = new Hashtable<>();
        List<policy> temp = new ArrayList<>();

        Iterator<policy> iterator = theList.iterator();
        while (iterator.hasNext()) {
            policy obj = iterator.next();
            String keyName = obj.getOrder();
            //an uparxei key prosthese to object sti lista alliws 
            //ftiaxe nea lista me key kai prosthese to prwto stoixeio
            if (finalL.containsKey(keyName)) {
                temp = finalL.get(keyName);
                temp.add(obj);
            } else {
                List<policy> myList = new ArrayList<>();
                myList.add(obj);
                finalL.put(keyName, myList);
            }
        }
        return finalL;
    }

    public static List<policy> paretoPref(List<policy> temp, boolean[] minmax) {
        List<policy> finalL = new ArrayList<>();
        Collections.sort(temp, new polComparator());
        Iterator<policy> ex;
        while (!temp.isEmpty()) {
            double[] data = temp.get(0).getObjectives();
            policy keeper = temp.get(0);
            ex = temp.iterator();
            while (ex.hasNext()) {
                policy temp2 = ex.next();
                double[] element = temp2.getObjectives();
                boolean bigger = true;
                boolean smaller = true;
                boolean equal = true;
                for (int w = 0; w < data.length; w++) {
                    if (minmax[w]) {
                        int result = maximizationofObjective(data[w], element[w]);
                        if (result == 1) {
                            bigger = true && bigger;
                            smaller = false;
                            equal = false;
                        } else if (result == 0) {
                            equal = true && equal;
                        } else {
                            bigger = false;
                            smaller = true && smaller;
                            equal = false;
                        }
                    } else {
                        int result = minimizationofObjective(data[w], element[w]);
                        if (result == 1) {
                            bigger = true && bigger;
                            smaller = false;
                            equal = false;
                        } else if (result == 0) {
                            equal = true && equal;
                        } else {
                            bigger = false;
                            smaller = true && smaller;
                            equal = false;
                        }
                    }
                }// an to keeper einai megalitero  apo to ex.next se oles tis parametrous tote to ex.next einai dominated kai prepei na fygei apo ti lista
                //an to keeper einai iso se oles tis parametrous me to ex.next tote den exw logo na kratisw 2 teleiws idia stoixeia kai to afairw
                if (bigger || equal) {
                    ex.remove();
                }
//sugkrinw ta upoloipa tis listas me to kainourgio data (kai menoun ta prwta misa tis listas pou exoun idi mpei sto temp 
                //an teleiwsei i diadikasia koitazw sto upoloipo 1o miso an meinei kai apo ekei tote einai pareto kai to prosthetw sti teliki lista 
                if (smaller) {
                    data = element;
                    keeper = temp2;
                    ex = temp.iterator();
                }
            }//an exei teleiwsei i lista kai auto den einai mikrotero apo kapoio allo tote einai dominant kai to prosthetw sti lista!
            finalL.add(keeper);
        }
        return finalL;
    }

    public static List<policy> putProf(List<policy> theList, boolean[] minmax) {
        Hashtable<String, List<policy>> mylist = createLists(theList);
        Enumeration<String> e = mylist.keys();
        List<policy> rList = new ArrayList<>();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            rList.addAll(paretoPref(mylist.get(key), minmax));
        }
        return paretoPref(rList, minmax);
    }

    public static List<policy> nsga2(List<policy> theList, boolean[] minmax) {

        for (int i = 0; i < theList.size(); i++) {
            double[] data = theList.get(i).getObjectives();

            for (int j = i + 1; j < theList.size(); j++) {
                double[] element = theList.get(j).getObjectives();
                boolean bigger = true;
                boolean smaller = true;
                boolean equal = true;

                for (int w = 0; w < data.length; w++) {
                    if (minmax[w]) {
                        int result = maximizationofObjective(data[w], element[w]);
                        if (result == 1) {
                            bigger = true && bigger;
                            smaller = false;
                            equal = false;
                        } else if (result == 0) {
                            equal = true && equal;
                        } else {
                            bigger = false;
                            smaller = true && smaller;
                            equal = false;
                        }
                    } else {
                        int result = minimizationofObjective(data[w], element[w]);
                        if (result == 1) {
                            bigger = true && bigger;
                            smaller = false;
                            equal = false;
                        } else if (result == 0) {
                            equal = true && equal;
                        } else {
                            bigger = false;
                            smaller = true && smaller;
                            equal = false;
                        }
                    }
                }
                if (!equal) {
                    if (bigger) {
                        theList.get(j).setDominated(theList.get(j).getDominated() + 1);
                        theList.get(i).setSi(theList.get(j).getPolicyName());
                        /*  System.out.println("bigger");
                         for (int w = 0; w < theList.get(i).getData().length; w++) { 
                         System.out.print(theList.get(i).getData()[w]);
                         System.out.print(theList.get(j).getData()[w]);
                         System.out.println();
                         }*/
                    }
                    if (smaller) {
                        theList.get(i).setDominated(theList.get(i).getDominated() + 1);
                        theList.get(j).setSi(theList.get(i).getPolicyName());
                        /* System.out.println("smaller");
                         for (int w = 0; w < theList.get(i).getData().length; w++) {
                         System.out.print(theList.get(i).getData()[w]);
                         System.out.print(theList.get(j).getData()[w]);
                         System.out.println();
                         }
                         */
                    }
                }
            }
        }
        return theList;
    }

    public static List<policy> nsga2FH(List<policy> theList, boolean[] minmax) {

        int rank = 1;
        List<policy> fList = new ArrayList<>();
        List<policy> retList = new ArrayList<>();
        Hashtable<String, policy> myList = new Hashtable<>();
        Iterator<policy> iterator = theList.iterator();
        int number = theList.get(0).getObjectives().length;
        //keep in Ni the domination count from the previous stage

        while (iterator.hasNext()) {
            policy pol = new policy(number, true);
            pol = iterator.next();
            pol.setNi(pol.getDominated());
            String keyName = pol.getPolicyName();
            myList.put(keyName, pol);
        }

        while (!myList.isEmpty()) {
            Enumeration<String> e = myList.keys();
            while (e.hasMoreElements()) {
                String key = e.nextElement();
                policy temp = myList.get(key);
                if (temp.getNi() == 0) {
                    temp.setRank(rank);
                    System.out.print(rank);
                    fList.add(temp);
                    myList.remove(key);
                }
            }
            //myList now contains only dominated values
            retList.addAll(fList);
            for (policy myp : fList) {
                for (String mystr : myp.getSi().split(" , ")) {
                    if (myList.containsKey(mystr)) {
                        policy temp11 = myList.get(mystr);
                        temp11.setNi(temp11.getNi() - 1);
                    }
                }
            }
        //removed non dominated values from domination count 
            //visit j in Si and reduce nj count by one
            rank++;
            fList.clear();
        }
        return retList;
    }

    public static List<policy> spea2(List<policy> theList, boolean[] minmax) {

        return theList;
    }

    public static List<policy> fonflem(List<policy> theList, boolean[] minmax) {

        return theList;
    }

    public static List<policy> greenw(List<policy> theList, boolean[] minmax, double[] weights) {

        return theList;
    }

}

