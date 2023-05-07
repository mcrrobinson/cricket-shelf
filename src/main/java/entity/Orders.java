/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Squash
 */
@Entity
@Table(name = "ORDERS")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
        @NamedQuery(name = "Orders.findByOrderId", query = "SELECT o FROM Orders o WHERE o.orderId = :orderId"),
        @NamedQuery(name = "Orders.findByTotal", query = "SELECT o FROM Orders o WHERE o.total = :total"),
        @NamedQuery(name = "Orders.findByStatus", query = "SELECT o FROM Orders o WHERE o.status = :status"),
        @NamedQuery(name = "Orders.findByOrdered", query = "SELECT o FROM Orders o WHERE o.ordered = :ordered"),
        @NamedQuery(name = "Orders.findByOutForDelivery", query = "SELECT o FROM Orders o WHERE o.outForDelivery = :outForDelivery"),
        @NamedQuery(name = "Orders.findByDelivered", query = "SELECT o FROM Orders o WHERE o.delivered = :delivered") })
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDER_ID")
    private Integer orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private double total;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ordered;
    @Column(name = "OUT_FOR_DELIVERY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date outForDelivery;
    @Column(name = "DELIVERED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date delivered;
    @Column(name = "CANCELLED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private Collection<OrderHasBook> orderHasBookCollection;
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Addresses addressId;
    @JoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID")
    @ManyToOne(optional = false)
    private Cards cardId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Users userId;

    public Orders() {
    }

    public Orders(Integer orderId) {
        this.orderId = orderId;
    }

    public Orders(Integer orderId, double total, String status, Date ordered) {
        this.orderId = orderId;
        this.total = total;
        this.status = status;
        this.ordered = ordered;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrdered() {
        return ordered;
    }

    public void setOrdered(Date ordered) {
        this.ordered = ordered;
    }

    public Date getOutForDelivery() {
        return outForDelivery;
    }

    public void setOutForDelivery(Date outForDelivery) {
        this.outForDelivery = outForDelivery;
    }

    public Date getDelivered() {
        return delivered;
    }

    public void setDelivered(Date delivered) {
        this.delivered = delivered;
    }

    public Date getCancelled() {
        return cancelled;
    }

    public void setCancelled(Date cancelled) {
        this.cancelled = cancelled;
    }

    @XmlTransient
    public Collection<OrderHasBook> getOrderHasBookCollection() {
        return orderHasBookCollection;
    }

    public void setOrderHasBookCollection(Collection<OrderHasBook> orderHasBookCollection) {
        this.orderHasBookCollection = orderHasBookCollection;
    }

    public Addresses getAddressId() {
        return addressId;
    }

    public void setAddressId(Addresses addressId) {
        this.addressId = addressId;
    }

    public Cards getCardId() {
        return cardId;
    }

    public void setCardId(Cards cardId) {
        this.cardId = cardId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderId == null && other.orderId != null)
                || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Orders[ orderId=" + orderId + " ]";
    }

}
