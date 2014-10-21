package web;

import backend_functions.maj;
import backend_functions.orderel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.print.attribute.DocAttribute;
import web.DBconnections.Preference;
import web.DBconnections.Preference100;
import web.DBconnections.PreferenceOrder;
import web.DBconnections.PreferenceOrder100;
import web.DBconnections.Y2050;
import web.DBconnections.Y2050100score;

@WebService()
@Stateless(name="PreferenceDeduction")
public class PreferenceDeduction {

    @PersistenceContext(unitName = "PolisPU")
    public EntityManager em;

    //an oi times einai sunexeis tupou 0.1-0.2-0.3 ktl tote to diastima tha antistoixei sto 20% tou diastimatos 
    //an den einai tote to diastima tha antistoixei sto 20% twn sinolikwn timwn pou emfanizontai :)
    public static class MajComparator implements Comparator<maj> {

        @Override
        public int compare(maj p1, maj p2) {
            return Double.compare(p1.getValue(), p2.getValue());
        }
    }

    public static double[] find_space(List<maj> test, int total) {

        double[] space = new double[3];
        for (int u = 0; u < space.length; u++) {
            space[u] = 0.0;
        }

        int k = (int) Math.round(test.size() * 0.2);
        if (k < 1) {
            k = 1;
        }
        double threshold = 30;//the threshold we use to verify preference

        for (int w = 0; w < test.size(); w++) {
            int temp_amount = 0;
            for (int i = 0; i < k; i++) {

                if (w + i < test.size()) {//make certain we dont get out of bounds 
                    if (test.get(w).getCount() == 0) {//if the previous value is chosen 0 times then we better start from the next one in creating the spaces in order not to create a space with 100-0-0-0-50
                        break;
                    }
                    temp_amount += test.get(w + i).getCount();
                    if (Double.compare((double) temp_amount * 100 / total, threshold) > 0) {

                        if (space[2] < temp_amount * 100 / total) {
                            space[2] = temp_amount * 100 / total;
                            space[0] = test.get(w).getValue(); //start value 
                            space[1] = test.get(w + i).getValue(); // end value if no one else added then i=0 and it gives the start value
                        }
                    }
                }
            }
            temp_amount = 0;
        }
        return space;
    }

    public static List<maj> merge(List<maj> majList) {
        List<maj> merged = new ArrayList<maj>();
        Hashtable<Double, Integer> hashList = new Hashtable<Double, Integer>();
        for (maj temp : majList) {
            if (hashList.containsKey(temp.getValue())) {
                hashList.put(temp.getValue(), hashList.get(temp.getValue()) + temp.getCount());
            } else {
                hashList.put(temp.getValue(), temp.getCount());
            }
        }
        Enumeration<Double> e = hashList.keys();
        while (e.hasMoreElements()) {
            Double key = e.nextElement();
            maj temp1 = new maj();
            temp1.setValue(key);
            temp1.setCount(hashList.get(key));
            merged.add(temp1);
        }

        return merged;
    }

    public static orderel mergeor(List<orderel> orderList) {
        orderel merged = new orderel();
        Hashtable<String, Integer> hashList = new Hashtable<>();

        for (orderel temp : orderList) {
            if (hashList.containsKey(temp.getValue())) {
                hashList.put(temp.getValue(), hashList.get(temp.getValue()) + temp.getCount());
            } else {
                hashList.put(temp.getValue(), temp.getCount());
            }
        }
        Enumeration<String> e = hashList.keys();
        orderel temp1 = new orderel();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            int count = 0;
            if (count < hashList.get(key)) {
                temp1.setValue(key);
                temp1.setCount(hashList.get(key));
                count = hashList.get(key);
              }
        }
        return temp1;
    }

    @WebMethod(operationName = "setPreferece100")
    public List<Preference100> setPreference100() {
        Query query = em.createNamedQuery("Y2050100score.findAll", Y2050100score.class);
        List<Y2050100score> results = query.getResultList();
        Hashtable<String, List<maj>> majList = new Hashtable<>();
        int totalsample = 0;
        double biodiv_start = 0.0000, biodiv_end = 0.0000, biodiv_value = 0.0000, co2_start = 0.0000, co2_end = 0.0000, co2_value = 0.0000, costfood_start = 0.00, costfood_end = 0.00, costfood_value = 0.00, forestland_start = 0.00, forestland_end = 0.00, forestland_value = 0.00;

        for (int i = 0; i < results.size(); i++) {

            totalsample += results.get(i).getChosen();
            //TODO change obj1,2,3,4 to autogenerated number of objective lists
            if (majList.containsKey("biodiv")) {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(results.get(i).getBiodiv().doubleValue());
                majList.get("biodiv").add(temp);
            } else {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                majList.put("biodiv", new ArrayList<maj>());
                temp.setValue(results.get(i).getBiodiv().doubleValue());
                majList.get("biodiv").add(temp);
            }
            if (majList.containsKey("co2")) {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(results.get(i).getCo2().doubleValue());
                majList.get("co2").add(temp);
            } else {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                majList.put("co2", new ArrayList<>());
                temp.setValue(results.get(i).getCo2().doubleValue());
                majList.get("co2").add(temp);
            }
            if (majList.containsKey("costfood")) {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(results.get(i).getCostfood().doubleValue());
                majList.get("costfood").add(temp);
            } else {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                majList.put("costfood", new ArrayList<>());
                temp.setValue(results.get(i).getCostfood().doubleValue());
                majList.get("costfood").add(temp);
            }
            if (majList.containsKey("forestland")) {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(results.get(i).getForestland().doubleValue());
                majList.get("forestland").add(temp);
            } else {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                majList.put("forestland", new ArrayList<>());
                temp.setValue(results.get(i).getForestland().doubleValue());
                majList.get("forestland").add(temp);
            }
        }

        Enumeration<String> e = majList.keys();
        List<maj> temp2 = new ArrayList<>();

        while (e.hasMoreElements()) {
            String key = e.nextElement();
            temp2 = majList.get(key);
            temp2 = merge(temp2);
            Collections.sort(temp2, new MajComparator());
            if ("biodiv".equals(key)) {
                double[] spaces = new double[3];
                spaces = find_space(temp2, totalsample);
                biodiv_start = spaces[0];
                biodiv_end = spaces[1];
                biodiv_value = spaces[2];
            }
            if ("co2".equals(key)) {
                double[] spaces = new double[3];
                spaces = find_space(temp2, totalsample);
                co2_start = spaces[0];
                co2_end = spaces[1];
                co2_value = spaces[2];
            }
            if ("costfood".equals(key)) {
                double[] spaces = new double[3];
                spaces = find_space(temp2, totalsample);
                costfood_start = spaces[0];
                costfood_end = spaces[1];
                costfood_value = spaces[2];
            }
            if ("forestland".equals(key)) {
                double[] spaces = new double[3];
                spaces = find_space(temp2, totalsample);
                forestland_start = spaces[0];
                forestland_end = spaces[1];
                forestland_value = spaces[2];
            }
        }
        try {

            Query query1 = em.createNativeQuery("INSERT INTO preference100 (biodiv_start,biodiv_end,biodiv_value,co2_start,co2_end,co2_value,costfood_start,costfood_end,costfood_value,forestland_start,forestland_end,forestland_value)VALUES("
                    + biodiv_start + "," + biodiv_end + "," + biodiv_value + "," + co2_start + "," + co2_end + "," + co2_value + "," + costfood_start + "," + costfood_end + "," + costfood_value + "," + forestland_start + "," + forestland_end + "," + forestland_value + ")");
            query1.executeUpdate();
        } catch (Exception exc) {
        }
        Query query2 = em.createNamedQuery("Preference100.findAll", Preference100.class);
        List<Preference100> results2 = query2.getResultList();
        return results2;

    }

    @WebMethod(operationName = "scorePreference100")
    public double scorePreference100(Y2050100score elem) {
        Query query = em.createNamedQuery("Preference100.findAll", Preference100.class);
        List<Preference100> results = query.getResultList();
        double score = 0;
        if (!results.isEmpty()) {
            if (elem.getBiodiv() <= results.get(results.size() - 1).getBiodivEnd() && elem.getBiodiv() >= results.get(results.size() - 1).getBiodivStart()) {
                score += 0.25;
            }
            if (elem.getCo2() <= results.get(results.size() - 1).getCo2End() && elem.getCo2() >= results.get(results.size() - 1).getCo2Start()) {
                score += 0.25;
            }
            if (elem.getCostfood() <= results.get(results.size() - 1).getCostfoodEnd() && elem.getCostfood() >= results.get(results.size() - 1).getCostfoodStart()) {
                score += 0.25;
            }
            if (elem.getForestland() <= results.get(results.size() - 1).getForestlandEnd() && elem.getForestland() >= results.get(results.size() - 1).getForestlandStart()) {
                score += 0.25;
            }
        }
        return score * 25 + elem.getScore();
    }

    @WebMethod(operationName = "setPreferece")
    public List<Preference> setPreference() {
        Query query = em.createNamedQuery("Y2050.findAll", Y2050.class);
        List<Y2050> results = query.getResultList();
        Hashtable<String, List<maj>> majList = new Hashtable<>();
        int totalsample = 0;
        double biodiv_start = 0.0000, biodiv_end = 0.0000, biodiv_value = 0.0000, co2_start = 0.0000, co2_end = 0.0000, co2_value = 0.0000, costfood_start = 0.00, costfood_end = 0.00, costfood_value = 0.00, forestland_start = 0.00, forestland_end = 0.00, forestland_value = 0.00;

        for (int i = 0; i < results.size(); i++) {

            totalsample += results.get(i).getChosen();
            //TODO change obj1,2,3,4 to autogenerated number of objective lists
            if (majList.containsKey("biodiv")) {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(results.get(i).getBiodiv().doubleValue());
                majList.get("biodiv").add(temp);
            } else {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                majList.put("biodiv", new ArrayList<maj>());
                temp.setValue(results.get(i).getBiodiv().doubleValue());
                majList.get("biodiv").add(temp);
            }
            if (majList.containsKey("co2")) {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(results.get(i).getCo2().doubleValue());
                majList.get("co2").add(temp);
            } else {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                majList.put("co2", new ArrayList<>());
                temp.setValue(results.get(i).getCo2().doubleValue());
                majList.get("co2").add(temp);
            }
            if (majList.containsKey("costfood")) {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(results.get(i).getCostfood().doubleValue());
                majList.get("costfood").add(temp);
            } else {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                majList.put("costfood", new ArrayList<>());
                temp.setValue(results.get(i).getCostfood().doubleValue());
                majList.get("costfood").add(temp);
            }
            if (majList.containsKey("forestland")) {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(results.get(i).getForestland().doubleValue());
                majList.get("forestland").add(temp);
            } else {
                maj temp = new maj();
                temp.setCount(results.get(i).getChosen());
                majList.put("forestland", new ArrayList<>());
                temp.setValue(results.get(i).getForestland().doubleValue());
                majList.get("forestland").add(temp);
            }
        }

        Enumeration<String> e = majList.keys();
        List<maj> temp2 = new ArrayList<>();

        while (e.hasMoreElements()) {
            String key = e.nextElement();
            temp2 = majList.get(key);
            temp2 = merge(temp2);
            Collections.sort(temp2, new MajComparator());
            if ("biodiv".equals(key)) {
                double[] spaces = new double[3];
                spaces = find_space(temp2, totalsample);
                biodiv_start = spaces[0];
                biodiv_end = spaces[1];
                biodiv_value = spaces[2];
            }
            if ("co2".equals(key)) {
                double[] spaces = new double[3];
                spaces = find_space(temp2, totalsample);
                co2_start = spaces[0];
                co2_end = spaces[1];
                co2_value = spaces[2];
            }
            if ("costfood".equals(key)) {
                double[] spaces = new double[3];
                spaces = find_space(temp2, totalsample);
                costfood_start = spaces[0];
                costfood_end = spaces[1];
                costfood_value = spaces[2];
            }
            if ("forestland".equals(key)) {
                double[] spaces = new double[3];
                spaces = find_space(temp2, totalsample);
                forestland_start = spaces[0];
                forestland_end = spaces[1];
                forestland_value = spaces[2];
            }
        }
        try {

            Query query1 = em.createNativeQuery("INSERT INTO preference (biodiv_start,biodiv_end,biodiv_value,co2_start,co2_end,co2_value,costfood_start,costfood_end,costfood_value,forestland_start,forestland_end,forestland_value)VALUES("
                    + biodiv_start + "," + biodiv_end + "," + biodiv_value + "," + co2_start + "," + co2_end + "," + co2_value + "," + costfood_start + "," + costfood_end + "," + costfood_value + "," + forestland_start + "," + forestland_end + "," + forestland_value + ")");
            query1.executeUpdate();
        } catch (Exception exc) {
        }
        Query query2 = em.createNamedQuery("Preference.findAll", Preference.class);
        List<Preference> results2 = query2.getResultList();
        return results2;

    }

    @WebMethod(operationName = "scorePreference")
    public double scorePreference(Y2050 elem) {
        Query query = em.createNamedQuery("Preference.findAll", Preference.class);
        List<Preference> results = query.getResultList();
        double score = 0;
        if (!results.isEmpty()) {
            if (elem.getBiodiv() <= results.get(results.size() - 1).getBiodivEnd() && elem.getBiodiv() >= results.get(results.size() - 1).getBiodivStart()&& results.get(results.size() - 1).getBiodivValue()>=0.30) {
                score += 0.25;
            }
            if (elem.getCo2() <= results.get(results.size() - 1).getCo2End() && elem.getCo2() >= results.get(results.size() - 1).getCo2Start()&& results.get(results.size() - 1).getCo2Value()>=0.30) {
                score += 0.25;
            }
            if (elem.getCostfood() <= results.get(results.size() - 1).getCostfoodEnd() && elem.getCostfood() >= results.get(results.size() - 1).getCostfoodStart()&& results.get(results.size() - 1).getCostfoodValue()>=0.30) {
                score += 0.25;
            }
            if (elem.getForestland() <= results.get(results.size() - 1).getForestlandEnd() && elem.getForestland() >= results.get(results.size() - 1).getForestlandStart()&& results.get(results.size() - 1).getForestlandValue()>=0.30) {
                score += 0.25;
            }
        }
        return score * 25 + elem.getScore() * 100;
    }

    @WebMethod(operationName = "setPrefereceByOrder")
    public PreferenceOrder setPreferenceByOrder() {
        Query query = em.createNamedQuery("Y2050.findAll", Y2050.class);
        List<Y2050> results = query.getResultList();
        Hashtable<String, List<orderel>> orderList = new Hashtable<>();
        int totalsample = 0;
        String top="";
        int count = 0;
        char[] arr = new char[4];
        for (int i = 0; i < results.size(); i++) {
            
//get the majority in preference order
            if (count < results.get(i).getChosen()) {
                count = results.get(i).getChosen();

                top = results.get(i).getMyorder();
            }

            arr = results.get(i).getMyorder().toCharArray();
            //TODO change obj1,2,3,4 to autogenerated number of objective lists

            if (orderList.containsKey("pref1")) {
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[0] + "");
                orderList.get("pref1").add(temp);
            } else {
                orderList.put("pref1", new ArrayList<orderel>());
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[0] + "");
                orderList.get("pref1").add(temp);
            }
            if (orderList.containsKey("pref2")) {
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[1] + "");
                orderList.get("pref2").add(temp);
            } else {
                orderList.put("pref2", new ArrayList<>());
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[1] + "");
                orderList.get("pref2").add(temp);
            }
            if (orderList.containsKey("pref3")) {
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[2] + "");
                orderList.get("pref3").add(temp);
            } else {
                orderList.put("pref3", new ArrayList<>());
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[2] + "");
                orderList.get("pref3").add(temp);
            }
            if (orderList.containsKey("pref4")) {
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[3] + "");
                orderList.get("pref4").add(temp);
            } else {
                orderList.put("pref4", new ArrayList<>());
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[3] + "");
                orderList.get("pref4").add(temp);
            }
        }
        Enumeration<String> e = orderList.keys();
        List<orderel> temp = new ArrayList<>();
        orderel temp2 = new orderel();
        String finals = "";
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            temp = orderList.get(key);
            temp2 = mergeor(temp);
            finals = temp2.getValue() + finals;
        }
        try {

            Query query1 = em.createNativeQuery("INSERT INTO preference_order (pref1,places) VALUES(" + top + "," + finals + ")");
            query1.executeUpdate();
        } catch (Exception exc) {
        }
        Query query2 = em.createNamedQuery("PreferenceOrder.findAll", PreferenceOrder.class);
        List<PreferenceOrder> results2 = query2.getResultList();
        return results2.get(results2.size() - 1);

    }

    @WebMethod(operationName = "scorePreferenceByOrder")
    public double scorePreferenceByOrder(Y2050 elem) {
        double score = 0;
        double total_points_assigned = 40.0;
        Query query2 = em.createNamedQuery("PreferenceOrder.findAll", PreferenceOrder.class);
        List<PreferenceOrder> results = query2.getResultList();
        PreferenceOrder lastpref = results.get(results.size() - 1);
        if (elem.getMyorder().equals(lastpref.getPref1())) {
            score += 5;
        }

        if (elem.getMyorder().matches(lastpref.getPlaces().toCharArray()[0] + "." + "." + ".")) {
            score += total_points_assigned * 0.5;
        }
        if (elem.getMyorder().matches("." + lastpref.getPlaces().toCharArray()[1] + "." + ".")) {
            score += total_points_assigned * 0.25;
        }
        if (elem.getMyorder().matches("." + "." + lastpref.getPlaces().toCharArray()[2] + ".")) {
            score += total_points_assigned * 0.2;
        }
        if (elem.getMyorder().matches("." + "." + "." + lastpref.getPlaces().toCharArray()[3])) {
            score += total_points_assigned * 0.05;
        }
        return score+elem.getScore() * 100;
    }

    @WebMethod(operationName = "setPrefereceByOrder100")
    public PreferenceOrder setPreferenceByOrder100() {
        Query query = em.createNamedQuery("Y2050100score.findAll", Y2050100score.class);
        List<Y2050100score> results = query.getResultList();
        Hashtable<String, List<orderel>> orderList = new Hashtable<>();
        String top = "";
        int count = 0;
        char[] arr = new char[4];
        for (int i = 0; i < results.size(); i++) {

//get the majority in preference order
            if (count < results.get(i).getChosen()) {
                count = results.get(i).getChosen();
                top = results.get(i).getMyorder();
            }

            arr = results.get(i).getMyorder().toCharArray();
            //TODO change obj1,2,3,4 to autogenerated number of objective lists

            if (orderList.containsKey("pref1")) {
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[0] + "");
                orderList.get("pref1").add(temp);
            } else {
                orderList.put("pref1", new ArrayList<orderel>());
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[0] + "");
                orderList.get("pref1").add(temp);
            }
            if (orderList.containsKey("pref2")) {
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[1] + "");
                orderList.get("pref2").add(temp);
            } else {
                orderList.put("pref2", new ArrayList<>());
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[1] + "");
                orderList.get("pref2").add(temp);
            }
            if (orderList.containsKey("pref3")) {
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[2] + "");
                orderList.get("pref3").add(temp);
            } else {
                orderList.put("pref3", new ArrayList<>());
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[2] + "");
                orderList.get("pref3").add(temp);
            }
            if (orderList.containsKey("pref4")) {
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[3] + "");
                orderList.get("pref4").add(temp);
            } else {
                orderList.put("pref4", new ArrayList<>());
                orderel temp = new orderel();
                temp.setCount(results.get(i).getChosen());
                temp.setValue(arr[3] + "");
                orderList.get("pref4").add(temp);
            }
        }
        Enumeration<String> e = orderList.keys();
        List<orderel> temp = new ArrayList<>();
        orderel temp2 = new orderel();
        String finals = "";
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            temp = orderList.get(key);
            temp2 = mergeor(temp);
            finals = temp2.getValue() + finals;
        }
        try {

            Query query1 = em.createNativeQuery("INSERT INTO preference_order100 (pref1,places) VALUES(" + top + "," + finals + ")");
            query1.executeUpdate();
        } catch (Exception exc) {
        }
        Query query2 = em.createNamedQuery("PreferenceOrder100.findAll", PreferenceOrder.class);
        List<PreferenceOrder> results2 = query2.getResultList();
        return results2.get(results2.size() - 1);

    }

    @WebMethod(operationName = "scorePreferenceByOrder100")
    public double scorePreferenceByOrder100(Y2050100score elem) {
        double score = 0;
        double total_points_assigned = 40.0;
        Query query2 = em.createNamedQuery("PreferenceOrder100.findAll", PreferenceOrder100.class);
        List<PreferenceOrder100> results = query2.getResultList();
        PreferenceOrder100 lastpref = results.get(results.size() - 1);
        if (elem.getMyorder().equals(lastpref.getPref1())) {
            score += 5;
        }

        if (elem.getMyorder().matches(lastpref.getPlaces().toCharArray()[0] + "." + "." + ".")) {
            score += total_points_assigned * 0.5;
        }
        if (elem.getMyorder().matches("." + lastpref.getPlaces().toCharArray()[1] + "." + ".")) {
            score += total_points_assigned * 0.25;
        }
        if (elem.getMyorder().matches("." + "." + lastpref.getPlaces().toCharArray()[2] + ".")) {
            score += total_points_assigned * 0.2;
        }
        if (elem.getMyorder().matches("." + "." + "." + lastpref.getPlaces().toCharArray()[3])) {
            score += total_points_assigned * 0.05;
        }
        return score+elem.getScore();
    }
}
