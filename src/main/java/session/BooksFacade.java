/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Books;
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
public class BooksFacade extends AbstractFacade<Books> {

    @PersistenceContext(unitName = "com.up2022742_cricket-store_war_0.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Books> findLikeTitle(String title){
        Query query = em.createNamedQuery("Books.findLikeTitle");
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }
    
    public Books findLastBook(){
        return (Books) em.createNamedQuery("Books.findLastBook").getSingleResult();
    }

    public BooksFacade() {
        super(Books.class);
    }
    
}
