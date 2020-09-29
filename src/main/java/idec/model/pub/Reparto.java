/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.pub;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luca
 */
@Entity
@Table(name = "reparto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reparto.findAll", query = "SELECT r FROM Reparto r")
    , @NamedQuery(name = "Reparto.findByRepartoId", query = "SELECT r FROM Reparto r WHERE r.repartoId = :repartoId")
    , @NamedQuery(name = "Reparto.findByRepartoDescr", query = "SELECT r FROM Reparto r WHERE r.repartoDescr = :repartoDescr")})
public class Reparto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reparto_id")
    private Short repartoId;
    @Size(max = 255)
    @Column(name = "reparto_descr")
    private String repartoDescr;
    @OneToMany(mappedBy = "repartoId")
    private List<Libro> libroList;

    public Reparto() {
    }

    public Reparto(Short repartoId) {
        this.repartoId = repartoId;
    }

    public Short getRepartoId() {
        return repartoId;
    }

    public void setRepartoId(Short repartoId) {
        this.repartoId = repartoId;
    }

    public String getRepartoDescr() {
        return repartoDescr;
    }

    public void setRepartoDescr(String repartoDescr) {
        this.repartoDescr = repartoDescr;
    }

    @XmlTransient
    public List<Libro> getLibroList() {
        return libroList;
    }

    public void setLibroList(List<Libro> libroList) {
        this.libroList = libroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (repartoId != null ? repartoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reparto)) {
            return false;
        }
        Reparto other = (Reparto) object;
        if ((this.repartoId == null && other.repartoId != null) || (this.repartoId != null && !this.repartoId.equals(other.repartoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.Reparto[ repartoId=" + repartoId + " ]";
    }
    
}
