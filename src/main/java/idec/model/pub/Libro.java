/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.pub;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luca
 */
@Entity
@Table(name = "libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l")
    , @NamedQuery(name = "Libro.findByLibroId", query = "SELECT l FROM Libro l WHERE l.libroId = :libroId")
    , @NamedQuery(name = "Libro.findByLibroDescr", query = "SELECT l FROM Libro l WHERE l.libroDescr = :libroDescr")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "libro_id")
    private Short libroId;
    @Size(max = 50)
    @Column(name = "libro_descr")
    private String libroDescr;
    @JoinColumn(name = "reparto_id", referencedColumnName = "reparto_id")
    @ManyToOne
    private Reparto repartoId;

    public Libro() {
    }

    public Libro(Short libroId) {
        this.libroId = libroId;
    }

    public Short getLibroId() {
        return libroId;
    }

    public void setLibroId(Short libroId) {
        this.libroId = libroId;
    }

    public String getLibroDescr() {
        return libroDescr;
    }

    public void setLibroDescr(String libroDescr) {
        this.libroDescr = libroDescr;
    }

    public Reparto getRepartoId() {
        return repartoId;
    }

    public void setRepartoId(Reparto repartoId) {
        this.repartoId = repartoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (libroId != null ? libroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.libroId == null && other.libroId != null) || (this.libroId != null && !this.libroId.equals(other.libroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.Libro[ libroId=" + libroId + " ]";
    }
    
}
