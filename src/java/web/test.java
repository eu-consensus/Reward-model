
package web;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@WebService
@Stateless
public class test {

    @PersistenceContext(unitName = "PolisPU")
    public EntityManager em;

    public String foo() {

        String foo = "INSERT INTO test VALUES (13, 'foo')";
        Query qq = em.createNativeQuery(foo);        
        qq.executeUpdate();
        return "hello";
    }

}
