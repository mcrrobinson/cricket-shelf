/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.OrderHasBook;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Squash
 */
@Stateless
public class OrderHasBookFacade extends AbstractFacade<OrderHasBook> {

    @PersistenceContext(unitName = "com.up2022742_cricket-store_war_0.1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderHasBookFacade() {
        super(OrderHasBook.class);
    }
    
}
