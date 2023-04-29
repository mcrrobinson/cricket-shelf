/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Books;
import entity.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Squash
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "com.up2022742_cricket-store_war_0.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
//    public List<Users> findByEmailAndPassword(String title){
//        Query query = em.createNamedQuery("Users.findByEmailAndPassword");
//        query.setParameter("emailAddress", title);
//        query.setParameter("password", title);
//        return query.getResultList();
//    }
    
    public List<Users> findByEmailAddress(String title){
        Query query = em.createNamedQuery("Users.findByEmailAddress");
        query.setParameter("emailAddress", title);
        return query.getResultList();
    }

    public UsersFacade() {
        super(Users.class);
    }
    
}
