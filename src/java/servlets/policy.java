/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.util.Arrays;
import java.util.UUID;

/**
 *
 * @author anu
 */
public class policy {

    private String policyName;
    private double[] objectives;
    private double score;
    private int dominated;
    private double distance;
    private int dominatedbycategory;
    private String order;

    public policy(int number, boolean hasname) {
        objectives = new double[number];
        this.score = 0;
        this.dominated = 0;
        this.distance = 0;
        this.dominatedbycategory=0;
        this.order="";
        
        if (!hasname) {
            policyName = UUID.randomUUID().toString();
        } else {
            policyName = "";
        }
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getDominated() {
        return dominated;
    }

    public void setDominated(int dominated) {
        this.dominated = dominated;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
        
    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public double[] getObjectives() {
        return objectives;
    }

    public void setObjectives(double[] objectives) {
        this.objectives = objectives;
    }
 

   public void setDistance() {
          
        //euclidean 
        double sum = 0;
        for (int i = 0; i < objectives.length; i++) {
            sum += Math.pow(objectives[i], 2);
        }
        this.distance = Math.sqrt(sum);
    }
    
    public int getDominatedbycategory() {
        return dominatedbycategory;
    }

    public void setDominatedbycategory(int dominatedbycategory) {
        this.dominatedbycategory = dominatedbycategory;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(int objectives_number) {
       //in order to create the right order we need to substract from the total number of objectives
        int[] thisorder = new int[objectives.length];
        double[] sorted = objectives.clone();
        Arrays.sort(sorted);
        String myorder="";
        //TODO fix O() add equals in same order value
        for (int j = 0; j < objectives.length; j++) {
            for (int i = 0; i < objectives.length; i++) {
                
                if (objectives[j]==sorted[i]) {
                    thisorder[j] = objectives_number-i;
                }
            }
        }
    
        for(int u=0; u<thisorder.length;u++){
            myorder+=thisorder[u];
        }
        this.order = myorder;
    }
    
}
