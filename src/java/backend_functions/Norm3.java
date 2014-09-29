/* PREFERENCE DEDUCTION
 *take data by objective and sort them by value add all equal (reduce size of matrix) then try to create pairs that form %of total amount
 *stObjective holds the structure of the data
 */
package backend_functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author anu
 */
public class Norm3 {

    public static int totalsample = 0;

    public static class MajComparator implements Comparator<maj> {

        public int compare(maj p1, maj p2) {
            return Double.compare(p1.getValue(), p2.getValue());
        }
    }
//unconclusive -BUG 

    public static double[] find_space2(List<maj> test, double boundry, int total) {
        int temp_amount = 0;
        double[] space = new double[3];

        double threshold = 0;//the threshold we use to verify preference

        double k = Math.round(boundry * 0.2);
        System.out.print("this a k");
        System.out.println(k);

        for (int w = 0; w < test.size(); w++) {
            int i = 0;
            double solution_space = 0.0;
            while (Double.compare(solution_space, k) < 0) {
                if (w + i < test.size()) {//make certain we dont get out of bounds 
                    temp_amount += test.get(w + i).getCount();
                    //  System.out.print((double)temp_amount*100/total);
                    //  System.out.println();
                    if (Double.compare((double) temp_amount * 100 / total, threshold) > 0) {
                        if (space[2] < temp_amount) {
                            space[2] = temp_amount;
                            space[0] = test.get(w).getValue();
                            space[1] = test.get(w + i).getValue();
                        }
                    }
                    System.out.println(test.get(w + i).getValue() - test.get(w).getValue());
                    solution_space = Math.round(test.get(w + i).getValue() - test.get(w).getValue());
                    i++;
                }

            }
            temp_amount = 0;
        }
        return space;
    }
//an oi times einai sunexeis tupou 0.1-0.2-0.3 ktl tote to diastima tha antistoixei sto 20% tou diastimatos 
    //an den einai tote to diastima tha antistoixei sto 20% twn sinolikwn timwn pou emfanizontai :)

    public static double[] find_space(List<maj> test, int total) {
        int temp_amount = 0;
        double[] space = new double[3];

        int k = (int) Math.round(test.size() * 0.2);
        if (k<1) k=1;
        double threshold = 30;//the threshold we use to verify preference

        for (int w = 0; w < test.size(); w++) {

            for (int i = 0; i < k; i++) {
                if (w + i < test.size()) {//make certain we dont get out of bounds 
                    temp_amount += test.get(w + i).getCount();
                    //  System.out.print((double)temp_amount*100/total);
                    //  System.out.println();
                    if (Double.compare((double) temp_amount * 100 / total, threshold) > 0) {
                        if (space[2] < temp_amount) {
                            space[2] = temp_amount;
                            space[0] = test.get(w).getValue();
                            space[1] = test.get(w + i).getValue();
                        }
                    }
                }
            }
            temp_amount = 0;
        }
        return space;
    }

    public static void print_spaces(double[] spaces) {

        System.out.print(spaces[0]);
        System.out.print("-");
        System.out.print(spaces[1]);
        System.out.print(" value ");
        System.out.printf("%.2f",spaces[2] / totalsample);

    }

    public static void split_by_objective2(Hashtable<String, List<maj>> hashList, double[] boundspace, int total) {
        Enumeration<String> e = hashList.keys();
        List<maj> temp = new ArrayList<maj>();
        double[] spaces = new double[3];
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            temp = hashList.get(key);
            Collections.sort(temp, new MajComparator());
            int element = Integer.parseInt(key);

            spaces = find_space2(temp, boundspace[element], total);
            System.out.print(key);
            System.out.print(" ");
            print_spaces(spaces);
            System.out.println();

        }
    }

    public static void split_by_objective(Hashtable<String, List<maj>> hashList, int total) {
        Enumeration<String> e = hashList.keys();
        List<maj> temp = new ArrayList<maj>();
        double[] spaces = new double[3];
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            temp = hashList.get(key);
            temp = merge(temp);
            Collections.sort(temp, new MajComparator());
            spaces = find_space(temp, total);
            System.out.print(key);
            System.out.print(" ");
            print_spaces(spaces);
            System.out.println();

        }
    }

    public static List<maj> merge(List<maj> majList) {
        List<maj> merged = new ArrayList<maj>();
        Hashtable<Double, Integer> hashList = new Hashtable<Double, Integer>();
        for (maj temp : majList) {
            if (hashList.containsKey(temp.value)) {
                hashList.put(temp.value, hashList.get(temp.value) + temp.count);
            } else {
                hashList.put(temp.value, temp.count);
            }
        }
        Enumeration<Double> e = hashList.keys();
        while (e.hasMoreElements()) {
            Double key = e.nextElement();
            maj temp1 = new maj();
            temp1.value = key;
            temp1.count = hashList.get(key);
            merged.add(temp1);
        }

        return merged;
    }

    public static void print_by_objective(Hashtable<String, List<maj>> hashList) {
        Enumeration<String> e = hashList.keys();
        List<maj> temp = new ArrayList<maj>();

        while (e.hasMoreElements()) {
            String key = e.nextElement();
            temp = hashList.get(key);
            for (maj temp2 : temp) {
                System.out.print(temp2.value);
                System.out.print(" ");
                System.out.printf("%.2f",temp2.count);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        List<maj> element = new ArrayList<maj>();
        Hashtable<String, List<maj>> majList = new Hashtable<String, List<maj>>();

        int objectives = 6;
        double[] boundries = new double[objectives * 2];
        double[] boundspace = new double[objectives];

        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/anu/Documents/NetBeansProjects/abc.txt"))) {

            String sCurrentLine;
            sCurrentLine = br.readLine();
            //           String[] strtemp = sCurrentLine.split(" ");
/* ADD MIN MAX VALUE  APPEARING
             for (int i = 0; i < strtemp.length; i++) {
             boundries[i] = Double.parseDouble(strtemp[i]);

             }
             */
            while ((sCurrentLine = br.readLine()) != null) {
                String[] splited = sCurrentLine.split(" ");

                for (int i = 0; i < splited.length - 1; i++) {
                    maj temp = new maj();
                    temp.value = Double.parseDouble(splited[i]);
                    temp.count = Integer.parseInt(splited[splited.length - 1]);
                    if (majList.containsKey(Integer.toString(i))) {
                        majList.get(Integer.toString(i)).add(temp);
                    } else {
                        majList.put(Integer.toString(i), new ArrayList<maj>());
                        majList.get(Integer.toString(i)).add(temp);
                    }
                }
                totalsample += Integer.parseInt(splited[splited.length - 1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*   for (int i = 0; i < objectives; i++) {
         boundspace[i] = Math.round(boundries[(i * 2) + 1] - boundries[2 * i]);
         }
         */ split_by_objective(majList, totalsample);
        //  split_by_objective2(majList, boundspace, totalsample);

    }
}
