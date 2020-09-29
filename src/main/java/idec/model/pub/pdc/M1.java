/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.pub.pdc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Luca
 */
@Entity
@Table(name = "m1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "M1.findAll", query = "SELECT m FROM M1 m")
    , @NamedQuery(name = "M1.findBym1Id", query = "SELECT m FROM M1 m WHERE m.m1Id = :m1Id")
    , @NamedQuery(name = "M1.findByCgTipoMasCodDes", query = "SELECT m FROM M1 m WHERE m.m1Descr = :m1Descr")
    , @NamedQuery(name = "M1.findByDallaDataAdmin", query = "SELECT m FROM M1 m WHERE m.dallaDataAdmin = :dallaDataAdmin")
    , @NamedQuery(name = "M1.findByAllaDataAdmin", query = "SELECT m FROM M1 m WHERE m.allaDataAdmin = :allaDataAdmin")
    , @NamedQuery(name = "M1.findByAdmin", query = "SELECT m FROM M1 m WHERE m.admin = :admin")})
public class M1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "m1_id")
    private Integer m1Id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "m1_descr")
    private String m1Descr;
    @Basic(optional = false)
    @Column(name = "dalla_data_admin")
    @Temporal(TemporalType.DATE)
    private Date dallaDataAdmin;
    @Basic(optional = false)
    @Column(name = "alla_data_admin")
    @Temporal(TemporalType.DATE)
    private Date allaDataAdmin;
    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(name = "admin")
    private String admin;
    
    @Column(name = "m1_id_visual")
    private BigDecimal m1IdVisual;
    @Column(name = "m1_totale")
    private String m1Totale;
    
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "m1Id")
    private List<M2> m2List;

    public M1() {
    }

    public M1(Integer cgTipoMasCod) {
        this.m1Id = cgTipoMasCod;
    }

    public M1(Integer cgTipoMasCod, String cgTipoMasCodDes, Date dallaDataAdmin, Date allaDataAdmin, String admin) {
        this.m1Id = cgTipoMasCod;
        this.m1Descr = cgTipoMasCodDes;
        this.dallaDataAdmin = dallaDataAdmin;
        this.allaDataAdmin = allaDataAdmin;
        this.admin = admin;
    }

    public Integer getM1Id() {
        return m1Id;
    }

    public void setM1Id(Integer m1Id) {
        this.m1Id = m1Id;
    }

    public String getM1Descr() {
        return m1Descr;
    }

    public void setM1Descr(String m1Descr) {
        this.m1Descr = m1Descr;
    }

    public Date getDallaDataAdmin() {
        return dallaDataAdmin;
    }

    public void setDallaDataAdmin(Date dallaDataAdmin) {
        this.dallaDataAdmin = dallaDataAdmin;
    }

    public Date getAllaDataAdmin() {
        return allaDataAdmin;
    }

    public void setAllaDataAdmin(Date allaDataAdmin) {
        this.allaDataAdmin = allaDataAdmin;
    }

    public String getAdmin() {
        return admin;
    }

    public BigDecimal getM1IdVisual() {
        return m1IdVisual;
    }

    public void setM1IdVisual(BigDecimal m1IdVisual) {
        this.m1IdVisual = m1IdVisual;
    }

    public String getM1Totale() {
        return m1Totale;
    }

    public void setM1Totale(String m1Totale) {
        this.m1Totale = m1Totale;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @XmlTransient
    public List<M2> getM2List() {
        return m2List;
    }

    public void setM2List(List<M2> m2List) {
        this.m2List = m2List;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (m1Id != null ? m1Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof M1)) {
            return false;
        }
        M1 other = (M1) object;
        if ((this.m1Id == null && other.m1Id != null) || (this.m1Id != null && !this.m1Id.equals(other.m1Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.pdc.M1[ m1Id=" + m1Id + " ]";
    }
    
}
