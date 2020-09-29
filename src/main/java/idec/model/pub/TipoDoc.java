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
@Table(name = "tipo_doc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDoc.findAll", query = "SELECT t FROM TipoDoc t")
    , @NamedQuery(name = "TipoDoc.findByTipoDocId", query = "SELECT t FROM TipoDoc t WHERE t.tipoDocId = :tipoDocId")
    , @NamedQuery(name = "TipoDoc.findByCodDocIva", query = "SELECT t FROM TipoDoc t WHERE t.codDocIva = :codDocIva")
    , @NamedQuery(name = "TipoDoc.findByCodDocIvaDescr", query = "SELECT t FROM TipoDoc t WHERE t.codDocIvaDescr = :codDocIvaDescr")})
public class TipoDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_doc_id")
    private Integer tipoDocId;
    @Size(max = 50)
    @Column(name = "cod_doc_iva")
    private String codDocIva;
    @Size(max = 255)
    @Column(name = "cod_doc_iva_descr")
    private String codDocIvaDescr;

    public TipoDoc() {
    }

    public TipoDoc(Integer tipoDocId) {
        this.tipoDocId = tipoDocId;
    }

    public Integer getTipoDocId() {
        return tipoDocId;
    }

    public void setTipoDocId(Integer tipoDocId) {
        this.tipoDocId = tipoDocId;
    }

    public String getCodDocIva() {
        return codDocIva;
    }

    public void setCodDocIva(String codDocIva) {
        this.codDocIva = codDocIva;
    }

    public String getCodDocIvaDescr() {
        return codDocIvaDescr;
    }

    public void setCodDocIvaDescr(String codDocIvaDescr) {
        this.codDocIvaDescr = codDocIvaDescr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoDocId != null ? tipoDocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDoc)) {
            return false;
        }
        TipoDoc other = (TipoDoc) object;
        if ((this.tipoDocId == null && other.tipoDocId != null) || (this.tipoDocId != null && !this.tipoDocId.equals(other.tipoDocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.TipoDoc[ tipoDocId=" + tipoDocId + " ]";
    }
    
}
