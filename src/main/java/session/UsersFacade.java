/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

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
    
    /**
     * Queries the database for a user with the input email.
     * 
     * @param email User email
     * @return A list of entities that matched the query, this should never be
     * more than one.
     */
    public List<Users> findByEmailAddress(String email){
        Query query = em.createNamedQuery("Users.findByEmailAddress");
        query.setParameter("emailAddress", email);
        return query.getResultList();
     }

    /**
     * Queries the database, returns the user entity if one exists with the 
     * input email and password
     * 
     * @param email User email
     * @param password User password
     * @return A list of entities that matched the query, this should never be
     * more than one.
     */
     public List<Users> findByEmailAndPassword(String email, String password){
        Query query = em.createNamedQuery("Users.findByEmailAndPassword");
        query.setParameter("emailAddress", email);
        query.setParameter("password", password);
        return query.getResultList();
     }
    
    public UsersFacade() {
        super(Users.class);
    }

}
