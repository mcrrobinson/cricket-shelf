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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "GENRES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genres.findAll", query = "SELECT g FROM Genres g"),
    @NamedQuery(name = "Genres.findByGenreId", query = "SELECT g FROM Genres g WHERE g.genreId = :genreId"),
    @NamedQuery(name = "Genres.findByName", query = "SELECT g FROM Genres g WHERE g.name = :name")})
public class Genres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GENRE_ID")
    private Integer genreId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "NAME")
    private String name;
    @JoinTable(name = "BOOK_HAS_GENRE", joinColumns = {
        @JoinColumn(name = "GENRE_ID", referencedColumnName = "GENRE_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID")})
    @ManyToMany
    @JsonIgnore
    private Collection<Books> booksCollection;

    public Genres() {
    }

    public Genres(Integer genreId) {
        this.genreId = genreId;
    }

    public Genres(Integer genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Books> getBooksCollection() {
        return booksCollection;
    }

    public void setBooksCollection(Collection<Books> booksCollection) {
        this.booksCollection = booksCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genreId != null ? genreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genres)) {
            return false;
        }
        Genres other = (Genres) object;
        if ((this.genreId == null && other.genreId != null) || (this.genreId != null && !this.genreId.equals(other.genreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Genres[ genreId=" + genreId + " ]";
    }
    
}
