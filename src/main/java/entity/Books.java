/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Squash
 */
@Entity
@Table(name = "BOOKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b"),
    @NamedQuery(name = "Books.findByBookId", query = "SELECT b FROM Books b WHERE b.bookId = :bookId"),
    @NamedQuery(name = "Books.findByTitle", query = "SELECT b FROM Books b WHERE b.title = :title"),
    @NamedQuery(name = "Books.findLikeTitle", query = "SELECT b FROM Books b WHERE b.title LIKE :title"),
    @NamedQuery(name = "Books.findLastBook", query = "SELECT b FROM Books b WHERE b.bookId=(SELECT max(b.bookId) FROM Books b)"),
    @NamedQuery(name = "Books.findByEdition", query = "SELECT b FROM Books b WHERE b.edition = :edition"),
    @NamedQuery(name = "Books.findBySalesPrice", query = "SELECT b FROM Books b WHERE b.salesPrice = :salesPrice"),
    @NamedQuery(name = "Books.findByPublishYear", query = "SELECT b FROM Books b WHERE b.publishYear = :publishYear")})
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BOOK_ID")
    private Integer bookId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "EDITION")
    private String edition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALES_PRICE")
    private double salesPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUBLISH_YEAR")
    private int publishYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 37)
    @Column(name = "THUMBNAIL")
    private String thumbnail;
    @ManyToMany(mappedBy = "booksCollection")
    @JsonIgnore
    private Collection<Authors> authorsCollection;
    @ManyToMany(mappedBy = "booksCollection")
    @JsonIgnore
    private Collection<Genres> genresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "books")
    @JsonIgnore
    private Collection<OrderHasBook> orderHasBookCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "books")
    @JsonIgnore
    private Collection<BasketHasBook> basketHasBookCollection;
    @OneToMany(mappedBy = "recentBookId")
    @JsonIgnore
    private Collection<Users> usersCollection;

    public Books() {
    }

    public Books(Integer bookId) {
        this.bookId = bookId;
    }

    public Books(Integer bookId, String title, String edition, double salesPrice, int publishYear, String thumbnail) {
        this.bookId = bookId;
        this.title = title;
        this.edition = edition;
        this.salesPrice = salesPrice;
        this.publishYear = publishYear;
        this.thumbnail = thumbnail;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @XmlTransient
    public Collection<Authors> getAuthorsCollection() {
        return authorsCollection;
    }

    public void setAuthorsCollection(Collection<Authors> authorsCollection) {
        this.authorsCollection = authorsCollection;
    }

    @XmlTransient
    public Collection<Genres> getGenresCollection() {
        return genresCollection;
    }

    public void setGenresCollection(Collection<Genres> genresCollection) {
        this.genresCollection = genresCollection;
    }

    @XmlTransient
    public Collection<OrderHasBook> getOrderHasBookCollection() {
        return orderHasBookCollection;
    }

    public void setOrderHasBookCollection(Collection<OrderHasBook> orderHasBookCollection) {
        this.orderHasBookCollection = orderHasBookCollection;
    }

    @XmlTransient
    public Collection<BasketHasBook> getBasketHasBookCollection() {
        return basketHasBookCollection;
    }

    public void setBasketHasBookCollection(Collection<BasketHasBook> basketHasBookCollection) {
        this.basketHasBookCollection = basketHasBookCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Books[ bookId=" + bookId + " ]";
    }
    
}
