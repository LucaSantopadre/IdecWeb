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
@Table(name = "natura_iva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NaturaIva.findAll", query = "SELECT n FROM NaturaIva n")
    , @NamedQuery(name = "NaturaIva.findByNaturaIvaId", query = "SELECT n FROM NaturaIva n WHERE n.naturaIvaId = :naturaIvaId")
    , @NamedQuery(name = "NaturaIva.findByCodNaturaIva", query = "SELECT n FROM NaturaIva n WHERE n.codNaturaIva = :codNaturaIva")
    , @NamedQuery(name = "NaturaIva.findByNaturaIvaDescr", query = "SELECT n FROM NaturaIva n WHERE n.naturaIvaDescr = :naturaIvaDescr")
    , @NamedQuery(name = "NaturaIva.findByIdecCod", query = "SELECT n FROM NaturaIva n WHERE n.idecCod = :idecCod")})
public class NaturaIva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "natura_iva_id")
    private Integer naturaIvaId;
    @Size(max = 50)
    @Column(name = "cod_natura_iva")
    private String codNaturaIva;
    @Size(max = 255)
    @Column(name = "natura_iva_descr")
    private String naturaIvaDescr;
    @Size(max = 255)
    @Column(name = "idec_cod")
    private String idecCod;

    public NaturaIva() {
    }

    public NaturaIva(Integer naturaIvaId) {
        this.naturaIvaId = naturaIvaId;
    }

    public Integer getNaturaIvaId() {
        return naturaIvaId;
    }

    public void setNaturaIvaId(Integer naturaIvaId) {
        this.naturaIvaId = naturaIvaId;
    }

    public String getCodNaturaIva() {
        return codNaturaIva;
    }

    public void setCodNaturaIva(String codNaturaIva) {
        this.codNaturaIva = codNaturaIva;
    }

    public String getNaturaIvaDescr() {
        return naturaIvaDescr;
    }

    public void setNaturaIvaDescr(String naturaIvaDescr) {
        this.naturaIvaDescr = naturaIvaDescr;
    }

    public String getIdecCod() {
        return idecCod;
    }

    public void setIdecCod(String idecCod) {
        this.idecCod = idecCod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (naturaIvaId != null ? naturaIvaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NaturaIva)) {
            return false;
        }
        NaturaIva other = (NaturaIva) object;
        if ((this.naturaIvaId == null && other.naturaIvaId != null) || (this.naturaIvaId != null && !this.naturaIvaId.equals(other.naturaIvaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.NaturaIva[ naturaIvaId=" + naturaIvaId + " ]";
    }
    
}
