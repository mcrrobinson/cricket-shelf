/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "BASKET_HAS_BOOK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BasketHasBook.findAll", query = "SELECT b FROM BasketHasBook b"),
    @NamedQuery(name = "BasketHasBook.findByUserId", query = "SELECT b FROM BasketHasBook b WHERE b.basketHasBookPK.userId = :userId"),
    @NamedQuery(name = "BasketHasBook.findByBookId", query = "SELECT b FROM BasketHasBook b WHERE b.basketHasBookPK.bookId = :bookId"),
    @NamedQuery(name = "BasketHasBook.findByQuantity", query = "SELECT b FROM BasketHasBook b WHERE b.quantity = :quantity")})
public class BasketHasBook implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BasketHasBookPK basketHasBookPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Books books;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    private Users users;

    public BasketHasBook() {
    }

    public BasketHasBook(BasketHasBookPK basketHasBookPK) {
        this.basketHasBookPK = basketHasBookPK;
    }

    public BasketHasBook(BasketHasBookPK basketHasBookPK, int quantity) {
        this.basketHasBookPK = basketHasBookPK;
        this.quantity = quantity;
    }

    public BasketHasBook(int userId, int bookId) {
        this.basketHasBookPK = new BasketHasBookPK(userId, bookId);
    }

    public BasketHasBookPK getBasketHasBookPK() {
        return basketHasBookPK;
    }

    public void setBasketHasBookPK(BasketHasBookPK basketHasBookPK) {
        this.basketHasBookPK = basketHasBookPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (basketHasBookPK != null ? basketHasBookPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BasketHasBook)) {
            return false;
        }
        BasketHasBook other = (BasketHasBook) object;
        if ((this.basketHasBookPK == null && other.basketHasBookPK != null) || (this.basketHasBookPK != null && !this.basketHasBookPK.equals(other.basketHasBookPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BasketHasBook[ basketHasBookPK=" + basketHasBookPK + " ]";
    }
    
}
