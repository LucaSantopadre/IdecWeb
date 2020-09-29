/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.ditta;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luca
 */
@Entity
@Table(name = "vendor02_sede",schema="fi001")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendor02Sede.findAll", query = "SELECT v FROM Vendor02Sede v")
    , @NamedQuery(name = "Vendor02Sede.findByVendor02Id", query = "SELECT v FROM Vendor02Sede v WHERE v.vendor02Id = :vendor02Id")
    , @NamedQuery(name = "Vendor02Sede.findByVendor01Id", query = "SELECT v FROM Vendor02Sede v WHERE v.vendor01Id = :vendor01Id")
    , @NamedQuery(name = "Vendor02Sede.findByVendor02Via", query = "SELECT v FROM Vendor02Sede v WHERE v.vendor02Via = :vendor02Via")
    , @NamedQuery(name = "Vendor02Sede.findByVendor02NumCivico", query = "SELECT v FROM Vendor02Sede v WHERE v.vendor02NumCivico = :vendor02NumCivico")
    , @NamedQuery(name = "Vendor02Sede.findByVendor02017coIdComune", query = "SELECT v FROM Vendor02Sede v WHERE v.vendor02017coIdComune = :vendor02017coIdComune")})
public class Vendor02Sede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "vendor02_id")
    private Integer vendor02Id;
    @Column(name = "vendor01_id")
    private Integer vendor01Id;
    @Size(max = 255)
    @Column(name = "vendor02_via")
    private String vendor02Via;
    @Size(max = 255)
    @Column(name = "vendor02_num_civico")
    private String vendor02NumCivico;
    @Column(name = "vendor02_017co_id_comune")
    private Integer vendor02017coIdComune;
    @JoinColumns({
        @JoinColumn(name = "vendor02_conto", referencedColumnName = "vendor01_conto")
        , @JoinColumn(name = "vendor02_codice", referencedColumnName = "vendor01_codice")})
    @ManyToOne
    private Vendor01Base vendor01Base;

    public Vendor02Sede() {
    }

    public Vendor02Sede(Integer vendor02Id) {
        this.vendor02Id = vendor02Id;
    }

    public Integer getVendor02Id() {
        return vendor02Id;
    }

    public void setVendor02Id(Integer vendor02Id) {
        this.vendor02Id = vendor02Id;
    }

    public Integer getVendor01Id() {
        return vendor01Id;
    }

    public void setVendor01Id(Integer vendor01Id) {
        this.vendor01Id = vendor01Id;
    }

    public String getVendor02Via() {
        return vendor02Via;
    }

    public void setVendor02Via(String vendor02Via) {
        this.vendor02Via = vendor02Via;
    }

    public String getVendor02NumCivico() {
        return vendor02NumCivico;
    }

    public void setVendor02NumCivico(String vendor02NumCivico) {
        this.vendor02NumCivico = vendor02NumCivico;
    }

    public Integer getVendor02017coIdComune() {
        return vendor02017coIdComune;
    }

    public void setVendor02017coIdComune(Integer vendor02017coIdComune) {
        this.vendor02017coIdComune = vendor02017coIdComune;
    }

    public Vendor01Base getVendor01Base() {
        return vendor01Base;
    }

    public void setVendor01Base(Vendor01Base vendor01Base) {
        this.vendor01Base = vendor01Base;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendor02Id != null ? vendor02Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendor02Sede)) {
            return false;
        }
        Vendor02Sede other = (Vendor02Sede) object;
        if ((this.vendor02Id == null && other.vendor02Id != null) || (this.vendor02Id != null && !this.vendor02Id.equals(other.vendor02Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.ditta.Vendor02Sede[ vendor02Id=" + vendor02Id + " ]";
    }
    
}
