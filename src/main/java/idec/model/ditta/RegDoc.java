/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.ditta;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luca
 */
@Entity
@Table(name = "reg_doc",schema="fi001")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegDoc.findAll", query = "SELECT r FROM RegDoc r")
    , @NamedQuery(name = "RegDoc.findByTipoDoc", query = "SELECT r FROM RegDoc r WHERE r.tipoDoc = :tipoDoc")
    , @NamedQuery(name = "RegDoc.findByDataDoc", query = "SELECT r FROM RegDoc r WHERE r.dataDoc = :dataDoc")
    , @NamedQuery(name = "RegDoc.findByNumDoc", query = "SELECT r FROM RegDoc r WHERE r.numDoc = :numDoc")
    , @NamedQuery(name = "RegDoc.findByRifDoc", query = "SELECT r FROM RegDoc r WHERE r.rifDoc = :rifDoc")
    , @NamedQuery(name = "RegDoc.findByAzzeraDoc", query = "SELECT r FROM RegDoc r WHERE r.azzeraDoc = :azzeraDoc")
    , @NamedQuery(name = "RegDoc.findByMonetaDoc", query = "SELECT r FROM RegDoc r WHERE r.monetaDoc = :monetaDoc")
    , @NamedQuery(name = "RegDoc.findByCambioDoc", query = "SELECT r FROM RegDoc r WHERE r.cambioDoc = :cambioDoc")
    , @NamedQuery(name = "RegDoc.findByMonetaContabileDoc", query = "SELECT r FROM RegDoc r WHERE r.monetaContabileDoc = :monetaContabileDoc")
    , @NamedQuery(name = "RegDoc.findByPagatoDoc", query = "SELECT r FROM RegDoc r WHERE r.pagatoDoc = :pagatoDoc")
    , @NamedQuery(name = "RegDoc.findByMezzoDiPagDoc", query = "SELECT r FROM RegDoc r WHERE r.mezzoDiPagDoc = :mezzoDiPagDoc")
    , @NamedQuery(name = "RegDoc.findByGgTraScadDoc", query = "SELECT r FROM RegDoc r WHERE r.ggTraScadDoc = :ggTraScadDoc")
    , @NamedQuery(name = "RegDoc.findByDataScadDoc", query = "SELECT r FROM RegDoc r WHERE r.dataScadDoc = :dataScadDoc")
    , @NamedQuery(name = "RegDoc.findByRegDocId", query = "SELECT r FROM RegDoc r WHERE r.regDocId = :regDocId")})
public class RegDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "tipo_doc")
    private String tipoDoc;
    @Column(name = "data_doc")
    @Temporal(TemporalType.DATE)
    private Date dataDoc;
    @Size(max = 255)
    @Column(name = "num_doc")
    private String numDoc;
    @Size(max = 255)
    @Column(name = "rif_doc")
    private String rifDoc;
    @Size(max = 255)
    @Column(name = "azzera_doc")
    private String azzeraDoc;
    @Size(max = 255)
    @Column(name = "moneta_doc")
    private String monetaDoc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cambio_doc")
    private Float cambioDoc;
    @Size(max = 255)
    @Column(name = "moneta_contabile_doc")
    private String monetaContabileDoc;
    @Size(max = 255)
    @Column(name = "pagato_doc")
    private String pagatoDoc;
    @Size(max = 255)
    @Column(name = "mezzo_di_pag_doc")
    private String mezzoDiPagDoc;
    @Size(max = 255)
    @Column(name = "gg_tra_scad_doc")
    private String ggTraScadDoc;
    @Column(name = "data_scad_doc")
    @Temporal(TemporalType.DATE)
    private Date dataScadDoc;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reg_doc_id")
    private Integer regDocId;
    @JoinColumns({
        @JoinColumn(name = "reg03_rigo_id", referencedColumnName = "reg03_rigo_reg_id")
        , @JoinColumn(name = "reg03_subrigo_id", referencedColumnName = "reg03_subrigo_reg_id")
        , @JoinColumn(name = "reg03_n_reg_id", referencedColumnName = "reg03_num_reg_id")})
    @ManyToOne(optional = true)
    private Reg03Rigo reg03Rigo;
    
    
//    // ***LUCA
//    private String dataDocString;
//    private String dataScadDocString;

    public RegDoc() {
    }

    public RegDoc(Integer regDocId) {
        this.regDocId = regDocId;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public Date getDataDoc() {
        return dataDoc;
    }

    public void setDataDoc(Date dataDoc) {
        this.dataDoc = dataDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getRifDoc() {
        return rifDoc;
    }

    public void setRifDoc(String rifDoc) {
        this.rifDoc = rifDoc;
    }

    public String getAzzeraDoc() {
        return azzeraDoc;
    }

    public void setAzzeraDoc(String azzeraDoc) {
        this.azzeraDoc = azzeraDoc;
    }

    public String getMonetaDoc() {
        return monetaDoc;
    }

    public void setMonetaDoc(String monetaDoc) {
        this.monetaDoc = monetaDoc;
    }

    public Float getCambioDoc() {
        return cambioDoc;
    }

//    public String getDataDocString() {
//        return dataDocString;
//    }
//
//    public void setDataDocString(String dataDocString) {
//        this.dataDocString = dataDocString;
//    }
//
//    public String getDataScadDocString() {
//        return dataScadDocString;
//    }
//
//    public void setDataScadDocString(String dataScadDocString) {
//        this.dataScadDocString = dataScadDocString;
//    }

    public void setCambioDoc(Float cambioDoc) {
        this.cambioDoc = cambioDoc;
    }

    public String getMonetaContabileDoc() {
        return monetaContabileDoc;
    }

    public void setMonetaContabileDoc(String monetaContabileDoc) {
        this.monetaContabileDoc = monetaContabileDoc;
    }

    public String getPagatoDoc() {
        return pagatoDoc;
    }

    public void setPagatoDoc(String pagatoDoc) {
        this.pagatoDoc = pagatoDoc;
    }

    public String getMezzoDiPagDoc() {
        return mezzoDiPagDoc;
    }

    public void setMezzoDiPagDoc(String mezzoDiPagDoc) {
        this.mezzoDiPagDoc = mezzoDiPagDoc;
    }

    public String getGgTraScadDoc() {
        return ggTraScadDoc;
    }

    public void setGgTraScadDoc(String ggTraScadDoc) {
        this.ggTraScadDoc = ggTraScadDoc;
    }

    public Date getDataScadDoc() {
        return dataScadDoc;
    }

    public void setDataScadDoc(Date dataScadDoc) {
        this.dataScadDoc = dataScadDoc;
    }

    public Integer getRegDocId() {
        return regDocId;
    }

    public void setRegDocId(Integer regDocId) {
        this.regDocId = regDocId;
    }

    public Reg03Rigo getReg03Rigo() {
        return reg03Rigo;
    }

    public void setReg03Rigo(Reg03Rigo reg03Rigo) {
        this.reg03Rigo = reg03Rigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regDocId != null ? regDocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegDoc)) {
            return false;
        }
        RegDoc other = (RegDoc) object;
        if ((this.regDocId == null && other.regDocId != null) || (this.regDocId != null && !this.regDocId.equals(other.regDocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.ditta.RegDoc[ regDocId=" + regDocId + " ]";
    }
    
}
