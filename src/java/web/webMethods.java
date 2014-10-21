package web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import web.DBconnections.Y2050;
import web.DBconnections.Y2050100score;

@WebService
@Stateless(name="webMethods")
public class webMethods {

    @PersistenceContext(unitName = "PolisPU")
    public EntityManager em;

    
    private String equal12(String order) {
        char[] orderelem = order.toCharArray();
        char[] newcheck = new char[orderelem.length];
        for (int i = 0; i < orderelem.length; i++) {
            if (orderelem[i] == '1') {
                newcheck[i] = '1';
            }
            if (orderelem[i] == '2') {
                newcheck[i] = '1';
            }
            if (orderelem[i] == '3') {
                newcheck[i] = '3';
            }
            if (orderelem[i] == '4') {
                newcheck[i] = '4';
            }
        }
        return  String.valueOf(newcheck);
    }

    private String equal123(String order) {
        char[] orderelem = order.toCharArray();
        char[] newcheck = new char[orderelem.length];
        for (int i = 0; i < orderelem.length; i++) {
            if (orderelem[i] == '1') {
                newcheck[i] = '1';
            }
            if (orderelem[i] == '2') {
                newcheck[i] = '1';
            }
            if (orderelem[i] == '3') {
                newcheck[i] = '1';
            }
            if (orderelem[i] == '4') {
                newcheck[i] = '4';
            }
        }
        return  String.valueOf(newcheck);
    }

    private String equal23(String order) {
        char[] orderelem = order.toCharArray();
        char[] newcheck = new char[orderelem.length];
        for (int i = 0; i < orderelem.length; i++) {
            if (orderelem[i] == '1') {
                newcheck[i] = '1';
            }
            if (orderelem[i] == '2') {
                newcheck[i] = '2';
            }
            if (orderelem[i] == '3') {
                newcheck[i] = '2';
            }
            if (orderelem[i] == '4') {
                newcheck[i] = '4';
            }
        }
        return  String.valueOf(newcheck);
    }

    private String equal234(String order) {
        char[] orderelem = order.toCharArray();
        char[] newcheck = new char[orderelem.length];
        for (int i = 0; i < orderelem.length; i++) {
            if (orderelem[i] == '1') {
                newcheck[i] = '1';
            }
            if (orderelem[i] == '2') {
                newcheck[i] = '2';
            }
            if (orderelem[i] == '3') {
                newcheck[i] = '2';
            }
            if (orderelem[i] == '4') {
                newcheck[i] = '2';
            }
        }
        return  String.valueOf(newcheck);
    }

    private String equal34(String order) {
        char[] orderelem = order.toCharArray();
        char[] newcheck = new char[orderelem.length];
        for (int i = 0; i < orderelem.length; i++) {
            if (orderelem[i] == '1') {
                newcheck[i] = '1';
            }
            if (orderelem[i] == '2') {
                newcheck[i] = '2';
               
            }
            if (orderelem[i] == '3') {
                newcheck[i] = '3';
            }
            if (orderelem[i] == '4') {
                newcheck[i] = '3';
            }
        }
        
        return String.valueOf(newcheck);
    }

    @WebMethod(operationName = "findById")
    public List<Y2050> findById(@WebParam(name = "id") int id) {
        Query query = em.createNamedQuery("Y2050.findById", Y2050.class);
        query.setParameter("id", id);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByPolicy")
    public List<Y2050> findByPolicy(@WebParam(name = "policy") String policy) {
        Query query = em.createNamedQuery("Y2050.findByPolicy", Y2050.class);
        query.setParameter("policy", policy);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByBiodiv")
    public List<Y2050> findByBiodiv(@WebParam(name = "biodiv") double biodiv) {
        Query query = em.createNamedQuery("Y2050.findByBiodiv", Y2050.class);
        query.setParameter("biodiv", biodiv);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByCo2")
    public List<Y2050> findByCo2(@WebParam(name = "co2") double co2) {
        Query query = em.createNamedQuery("Y2050.findByCo2", Y2050.class);
        query.setParameter("co2", co2);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByCostfood")
    public List<Y2050> findByCostfood(@WebParam(name = "costfood") double costfood) {
        Query query = em.createNamedQuery("Y2050.findByCostfood", Y2050.class);
        query.setParameter("costfood", costfood);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByForestland")
    public List<Y2050> findByForestland(@WebParam(name = "forestland") double forestland) {
        Query query = em.createNamedQuery("Y2050.findByForestland", Y2050.class);
        query.setParameter("forestland", forestland);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByDistance")
    public List<Y2050> findByDistance(@WebParam(name = "distance") double distance) {
        Query query = em.createNamedQuery("Y2050.findByDistance", Y2050.class);
        query.setParameter("distance", distance);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByDominatedbycategory")
    public List<Y2050> findByDominatedbycategory(@WebParam(name = "dominatedbycategory") int dominated) {
        Query query = em.createNamedQuery("Y2050.findByDominatedbycategory", Y2050.class);
        query.setParameter("dominatedbycategory", dominated);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByDominatedbypool")
    public List<Y2050> findByDominatedbypool(@WebParam(name = "dominatedbypool") int dominated) {
        Query query = em.createNamedQuery("Y2050.findByDominatedbypool", Y2050.class);
        query.setParameter("dominatedbypool", dominated);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByMyorder")
    public List<Y2050> findByMyorder(@WebParam(name = "myorder") String order) {
        Query query = em.createNamedQuery("Y2050.findByMyorder", Y2050.class);
        query.setParameter("myorder", order);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByChosen")
    public List<Y2050> findByChosen(@WebParam(name = "chosen") int chosen) {
        Query query = em.createNamedQuery("Y2050.findByChosen", Y2050.class);
        query.setParameter("chosen", chosen);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByLiked")
    public List<Y2050> findByLiked(@WebParam(name = "liked") int liked) {
        Query query = em.createNamedQuery("Y2050.findByLiked", Y2050.class);
        query.setParameter("liked", liked);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByScore")
    public List<Y2050> findByScore(@WebParam(name = "score") double score) {
        Query query = em.createNamedQuery("Y2050.findByScore", Y2050.class);
        query.setParameter("score", score);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod
    public List<Y2050> findAll() {
        Query query = em.createNamedQuery("Y2050.findAll", Y2050.class);
        List<Y2050> results = query.getResultList();
        return results;
    }

    @WebMethod
    public double getScorePreset(@WebParam(name = "policy") String pol) {

        Query query = em.createNamedQuery("Y2050.findByPolicy", Y2050.class);
        query.setParameter("policy", pol);
        List<Y2050> results = query.getResultList();
        return results.get(0).getScore();
    }

    @WebMethod
    @javax.jws.WebResult(name = "getPolicyID")
    public int getPolicyID(@WebParam(name = "policy") String pol) {
        Query query = em.createNamedQuery("Y2050.findByPolicy", Y2050.class);
        query.setParameter("policy", pol);
        List<Y2050> results = query.getResultList();
        return results.get(0).getId();
    }

    @WebMethod(operationName = "increaseChosen")
    public void increaseChosen(@WebParam(name = "policy") String pol) {
        try {
            em.createNativeQuery("UPDATE Y2050 SET chosen=chosen+1 WHERE policy ='" + pol + "'").executeUpdate();
        } catch (Exception exception) {
            String foo = "sadf";
        }
    }

    @WebMethod(operationName = "increaseLiked")
    public void increaseLiked(@WebParam(name = "policy") String pol) {
        try {
            em.createNativeQuery("UPDATE Y2050 SET liked=liked+1 WHERE policy ='" + pol + "'").executeUpdate();
        } catch (Exception e) {

        }
    }

    @WebMethod(operationName = "decreaseChosen")
    public void decreaseChosen(@WebParam(name = "policy") String pol) {
        try {
            em.createNativeQuery("UPDATE Y2050 SET chosen=chosen-1 WHERE policy ='" + pol + "'").executeUpdate();
        } catch (Exception e) {
            String foo = "decrease fails";
        }
    }

    @WebMethod(operationName = "decreaseLiked")
    public void decreaseLiked(@WebParam(name = "policy") String pol) {
        try {
            em.createNativeQuery("UPDATE Y2050 SET liked=liked-1 WHERE policy ='" + pol + "'").executeUpdate();
        } catch (Exception e) {
        }
    }

    @WebMethod
    public List<Y2050100score> findAll100() {
        Query query = em.createNamedQuery("Y2050100score.findAll", Y2050100score.class);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "getScorePreset100")
    public double getScorePreset100(@WebParam(name = "policy") String pol) {

        Query query = em.createNamedQuery("Y2050100score.findByPolicy", Y2050100score.class);
        query.setParameter("policy", pol);
        List<Y2050100score> results = query.getResultList();
        return results.get(0).getScore();
    }

    @WebMethod(operationName = "getPolicyID100")
    public int getPolicyID100(@WebParam(name = "policy") String pol) {

        Query query = em.createNamedQuery("Y2050100score.findByPolicy", Y2050100score.class);
        query.setParameter("policy", pol);
        List<Y2050100score> results = query.getResultList();
        return results.get(0).getId();
    }

    @WebMethod(operationName = "findById100")
    public List<Y2050100score> findById100(@WebParam(name = "id") int id) {
        Query query = em.createNamedQuery("Y2050100score.findById", Y2050100score.class);
        query.setParameter("id", id);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByPolicy100")
    public List<Y2050100score> findByPolicy100(@WebParam(name = "policy") String policy) {
        Query query = em.createNamedQuery("Y2050100score.findByPolicy", Y2050100score.class);
        query.setParameter("policy", policy);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByBiodiv100")
    public List<Y2050100score> findByBiodiv100(@WebParam(name = "biodiv") double biodiv) {
        Query query = em.createNamedQuery("Y2050100score.findByBiodiv", Y2050100score.class);
        query.setParameter("biodiv", biodiv);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByCo2100")
    public List<Y2050100score> findByCo2100(@WebParam(name = "co2") double co2) {
        Query query = em.createNamedQuery("Y2050100score.findByCo2", Y2050100score.class);
        query.setParameter("co2", co2);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByCostfood100")
    public List<Y2050100score> findByCostfood100(@WebParam(name = "costfood") double costfood) {
        Query query = em.createNamedQuery("Y2050100score.findByCostfood", Y2050100score.class);
        query.setParameter("costfood", costfood);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByForestland100")
    public List<Y2050100score> findByForestland100(@WebParam(name = "forestland") double forestland) {
        Query query = em.createNamedQuery("Y2050100score.findByForestland", Y2050100score.class);
        query.setParameter("forestland", forestland);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByDistance100")
    public List<Y2050100score> findByDistance100(@WebParam(name = "distance") double distance) {
        Query query = em.createNamedQuery("Y2050100score.findByDistance", Y2050100score.class);
        query.setParameter("distance", distance);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByDominatedbycategory100")
    public List<Y2050100score> findByDominatedbycategory100(@WebParam(name = "dominatedbycategory") int dominated) {
        Query query = em.createNamedQuery("Y2050100score.findByDominatedbycategory", Y2050100score.class);
        query.setParameter("dominatedbycategory", dominated);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByDominatedbypool100")
    public List<Y2050100score> findByDominatedbypool100(@WebParam(name = "dominatedbypool") int dominated) {
        Query query = em.createNamedQuery("Y2050100score.findByDominatedbypool", Y2050100score.class);
        query.setParameter("dominatedbypool", dominated);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByMyorder100")
    public List<Y2050100score> findByMyorder100(@WebParam(name = "myorder") String order) {
        Query query = em.createNamedQuery("Y2050100score.findByMyorder", Y2050100score.class);
        query.setParameter("myorder", order);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByChosen100")
    public List<Y2050100score> findByChosen100(@WebParam(name = "chosen") int chosen) {
        Query query = em.createNamedQuery("Y2050100score.findByChosen", Y2050100score.class);
        query.setParameter("chosen", chosen);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByLiked100")
    public List<Y2050100score> findByLiked100(@WebParam(name = "liked") int liked) {
        Query query = em.createNamedQuery("Y2050100score.findByLiked", Y2050100score.class);
        query.setParameter("liked", liked);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "findByScore100")
    public List<Y2050100score> findByScore100(@WebParam(name = "score") double score) {
        Query query = em.createNamedQuery("Y2050100score.findByScore", Y2050100score.class);
        query.setParameter("score", score);
        List<Y2050100score> results = query.getResultList();
        return results;
    }

    @WebMethod(operationName = "increaseChosen100")
    public void increaseChosen100(@WebParam(name = "policy") String pol) {
        try {
            em.createNativeQuery("UPDATE y2050_100score SET chosen=chosen+1 WHERE policy ='" + pol + "'").executeUpdate();
        } catch (Exception e) {
        }
    }

    @WebMethod(operationName = "increaseLiked100")
    public void increaseLiked100(@WebParam(name = "policy") String pol) {
        try {
            em.createNativeQuery("UPDATE y2050_100score SET liked=liked+1 WHERE policy ='" + pol + "'").executeUpdate();
        } catch (Exception e) {
        }
    }

    @WebMethod(operationName = "decreaseChosen100")
    public void decreaseChosen100(@WebParam(name = "policy") String pol) {
        try {

            em.createNativeQuery("UPDATE y2050_100score SET chosen=chosen-1 WHERE policy ='" + pol + "'").executeUpdate();

        } catch (Exception e) {
        }
    }

    @WebMethod(operationName = "decreaseLiked100")
    public void decreaseLiked100(@WebParam(name = "policy") String pol) {
        try {

            Query query = em.createNativeQuery("UPDATE y2050_100score SET liked=liked-1 WHERE policy ='" + pol + "'");
            query.executeUpdate();
        } catch (Exception e) {
        }
    }
    /* @WebMethod
     public BigDecimal getScore(@WebParam(name = "policy") String pol) {

     Query query = em.createNamedQuery("Y2050.findByPolicy", Y2050.class);
     query.setParameter("policy", pol);
     List<Y2050> results = query.getResultList();
     BigDecimal=results.get(0).getScore()*100.00+
     return ;
     }
     */

    @WebMethod(operationName = "findPreferenceOrder")
    public List<Y2050> findPreferenceOrder(@WebParam(name = "myorder") String order) {
        char[] orderelem = order.toCharArray();
        List<String> myList = new ArrayList<>();
        myList.add(order);
        String newordercheck = "1111";
        myList.add(newordercheck);
        myList.add(equal12(order));
        myList.add(equal123(order));
        myList.add(equal23(order));
        myList.add(equal234(order));
        myList.add(equal34(order));
        List<Y2050> results = new ArrayList<>();
        for (int i = 0; i < myList.size(); i++) {
            Query query = em.createNativeQuery("SELECT * FROM Y2050 WHERE myorder ='" + myList.get(i) + "'", Y2050.class);
            query.setParameter("myorder", order);
            results.addAll(query.getResultList());
        }
        return results;
    }
}
