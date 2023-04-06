/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Squash
 */
@Embeddable
public class OrderHasBookPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDER_ID")
    private int orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BOOK_ID")
    private int bookId;

    public OrderHasBookPK() {
    }

    public OrderHasBookPK(int orderId, int bookId) {
        this.orderId = orderId;
        this.bookId = bookId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderId;
        hash += (int) bookId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderHasBookPK)) {
            return false;
        }
        OrderHasBookPK other = (OrderHasBookPK) object;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.bookId != other.bookId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderHasBookPK[ orderId=" + orderId + ", bookId=" + bookId + " ]";
    }
    
}
