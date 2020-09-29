/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.ditta;

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
@Table(name = "vendor01_base",schema="fi001")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendor01Base.findAll", query = "SELECT v FROM Vendor01Base v")
    , @NamedQuery(name = "Vendor01Base.findByVendor01Id", query = "SELECT v FROM Vendor01Base v WHERE v.vendor01Id = :vendor01Id")
    , @NamedQuery(name = "Vendor01Base.findByVendor01Conto", query = "SELECT v FROM Vendor01Base v WHERE v.vendor01Conto = :vendor01Conto")
    , @NamedQuery(name = "Vendor01Base.findByVendor01Codice", query = "SELECT v FROM Vendor01Base v WHERE v.vendor01Codice = :vendor01Codice")
    , @NamedQuery(name = "Vendor01Base.findByVendor01Denominazione", query = "SELECT v FROM Vendor01Base v WHERE v.vendor01Denominazione = :vendor01Denominazione")
    , @NamedQuery(name = "Vendor01Base.findByVendor01Cognome", query = "SELECT v FROM Vendor01Base v WHERE v.vendor01Cognome = :vendor01Cognome")
    , @NamedQuery(name = "Vendor01Base.findByVendor01Nome", query = "SELECT v FROM Vendor01Base v WHERE v.vendor01Nome = :vendor01Nome")
    , @NamedQuery(name = "Vendor01Base.findByVendor01PartIva", query = "SELECT v FROM Vendor01Base v WHERE v.vendor01PartIva = :vendor01PartIva")
    , @NamedQuery(name = "Vendor01Base.findByVendor01CodFisc", query = "SELECT v FROM Vendor01Base v WHERE v.vendor01CodFisc = :vendor01CodFisc")})
public class Vendor01Base implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vendor01_id")
    private Integer vendor01Id;
    @Size(max = 10)
    @Column(name = "vendor01_conto")
    private String vendor01Conto;
    @Size(max = 10)
    @Column(name = "vendor01_codice")
    private String vendor01Codice;
    @Size(max = 80)
    @Column(name = "vendor01_denominazione")
    private String vendor01Denominazione;
    @Size(max = 80)
    @Column(name = "vendor01_cognome")
    private String vendor01Cognome;
    @Size(max = 80)
    @Column(name = "vendor01_nome")
    private String vendor01Nome;
    @Size(max = 20)
    @Column(name = "vendor01_part_iva")
    private String vendor01PartIva;
    @Size(max = 20)
    @Column(name = "vendor01_cod_fisc")
    private String vendor01CodFisc;
    @OneToMany(mappedBy = "vendor01Base")
    private List<Vendor02Sede> vendor02SedeList;
    
    

    public Vendor01Base() {
    }

    public Vendor01Base(Integer vendor01Id) {
        this.vendor01Id = vendor01Id;
    }

    public Integer getVendor01Id() {
        return vendor01Id;
    }

    public void setVendor01Id(Integer vendor01Id) {
        this.vendor01Id = vendor01Id;
    }

    public String getVendor01Conto() {
        return vendor01Conto;
    }

    public void setVendor01Conto(String vendor01Conto) {
        this.vendor01Conto = vendor01Conto;
    }

    public String getVendor01Codice() {
        return vendor01Codice;
    }

    public void setVendor01Codice(String vendor01Codice) {
        this.vendor01Codice = vendor01Codice;
    }

    public String getVendor01Denominazione() {
        return vendor01Denominazione;
    }

    public void setVendor01Denominazione(String vendor01Denominazione) {
        this.vendor01Denominazione = vendor01Denominazione;
    }

    public String getVendor01Cognome() {
        return vendor01Cognome;
    }

    public void setVendor01Cognome(String vendor01Cognome) {
        this.vendor01Cognome = vendor01Cognome;
    }

    public String getVendor01Nome() {
        return vendor01Nome;
    }

    public void setVendor01Nome(String vendor01Nome) {
        this.vendor01Nome = vendor01Nome;
    }

    public String getVendor01PartIva() {
        return vendor01PartIva;
    }

    public void setVendor01PartIva(String vendor01PartIva) {
        this.vendor01PartIva = vendor01PartIva;
    }

    public String getVendor01CodFisc() {
        return vendor01CodFisc;
    }

    public void setVendor01CodFisc(String vendor01CodFisc) {
        this.vendor01CodFisc = vendor01CodFisc;
    }

    @XmlTransient
    public List<Vendor02Sede> getVendor02SedeList() {
        return vendor02SedeList;
    }

    public void setVendor02SedeList(List<Vendor02Sede> vendor02SedeList) {
        this.vendor02SedeList = vendor02SedeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendor01Id != null ? vendor01Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendor01Base)) {
            return false;
        }
        Vendor01Base other = (Vendor01Base) object;
        if ((this.vendor01Id == null && other.vendor01Id != null) || (this.vendor01Id != null && !this.vendor01Id.equals(other.vendor01Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.ditta.Vendor01Base[ vendor01Id=" + vendor01Id + " ]";
    }
    
}
