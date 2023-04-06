/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Squash
 */
@Entity
@Table(name = "ORDER_HAS_BOOK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderHasBook.findAll", query = "SELECT o FROM OrderHasBook o"),
    @NamedQuery(name = "OrderHasBook.findByOrderId", query = "SELECT o FROM OrderHasBook o WHERE o.orderHasBookPK.orderId = :orderId"),
    @NamedQuery(name = "OrderHasBook.findByBookId", query = "SELECT o FROM OrderHasBook o WHERE o.orderHasBookPK.bookId = :bookId"),
    @NamedQuery(name = "OrderHasBook.findByQuantity", query = "SELECT o FROM OrderHasBook o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrderHasBook.findByItemCostOnPurchase", query = "SELECT o FROM OrderHasBook o WHERE o.itemCostOnPurchase = :itemCostOnPurchase")})
public class OrderHasBook implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderHasBookPK orderHasBookPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ITEM_COST_ON_PURCHASE")
    private double itemCostOnPurchase;
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Books books;
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orders orders;

    public OrderHasBook() {
    }

    public OrderHasBook(OrderHasBookPK orderHasBookPK) {
        this.orderHasBookPK = orderHasBookPK;
    }

    public OrderHasBook(OrderHasBookPK orderHasBookPK, int quantity, double itemCostOnPurchase) {
        this.orderHasBookPK = orderHasBookPK;
        this.quantity = quantity;
        this.itemCostOnPurchase = itemCostOnPurchase;
    }

    public OrderHasBook(int orderId, int bookId) {
        this.orderHasBookPK = new OrderHasBookPK(orderId, bookId);
    }

    public OrderHasBookPK getOrderHasBookPK() {
        return orderHasBookPK;
    }

    public void setOrderHasBookPK(OrderHasBookPK orderHasBookPK) {
        this.orderHasBookPK = orderHasBookPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemCostOnPurchase() {
        return itemCostOnPurchase;
    }

    public void setItemCostOnPurchase(double itemCostOnPurchase) {
        this.itemCostOnPurchase = itemCostOnPurchase;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderHasBookPK != null ? orderHasBookPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderHasBook)) {
            return false;
        }
        OrderHasBook other = (OrderHasBook) object;
        if ((this.orderHasBookPK == null && other.orderHasBookPK != null) || (this.orderHasBookPK != null && !this.orderHasBookPK.equals(other.orderHasBookPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderHasBook[ orderHasBookPK=" + orderHasBookPK + " ]";
    }
    
}
