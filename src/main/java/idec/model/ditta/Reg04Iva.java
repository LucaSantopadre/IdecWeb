/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.ditta;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luca
 */
@Entity
@Table(name = "reg04_iva",schema="fi001")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reg04Iva.findAll", query = "SELECT r FROM Reg04Iva r")
    , @NamedQuery(name = "Reg04Iva.findByIvaOp", query = "SELECT r FROM Reg04Iva r WHERE r.ivaOp = :ivaOp")
    , @NamedQuery(name = "Reg04Iva.findByIvaFun", query = "SELECT r FROM Reg04Iva r WHERE r.ivaFun = :ivaFun")
    , @NamedQuery(name = "Reg04Iva.findByIvaPerc", query = "SELECT r FROM Reg04Iva r WHERE r.ivaPerc = :ivaPerc")
    , @NamedQuery(name = "Reg04Iva.findByIvaPercDet", query = "SELECT r FROM Reg04Iva r WHERE r.ivaPercDet = :ivaPercDet")
    , @NamedQuery(name = "Reg04Iva.findByIvaRipartoAcq", query = "SELECT r FROM Reg04Iva r WHERE r.ivaRipartoAcq = :ivaRipartoAcq")
    , @NamedQuery(name = "Reg04Iva.findByIvaImposta", query = "SELECT r FROM Reg04Iva r WHERE r.ivaImposta = :ivaImposta")
    , @NamedQuery(name = "Reg04Iva.findByIvaImportoNonDet", query = "SELECT r FROM Reg04Iva r WHERE r.ivaImportoNonDet = :ivaImportoNonDet")
    , @NamedQuery(name = "Reg04Iva.findByIvaNatura", query = "SELECT r FROM Reg04Iva r WHERE r.ivaNatura = :ivaNatura")
    , @NamedQuery(name = "Reg04Iva.findByIvaPercDed", query = "SELECT r FROM Reg04Iva r WHERE r.ivaPercDed = :ivaPercDed")
    , @NamedQuery(name = "Reg04Iva.findByIvaImportoDed", query = "SELECT r FROM Reg04Iva r WHERE r.ivaImportoDed = :ivaImportoDed")
    , @NamedQuery(name = "Reg04Iva.findByIvaPercNonDet", query = "SELECT r FROM Reg04Iva r WHERE r.ivaPercNonDet = :ivaPercNonDet")
    , @NamedQuery(name = "Reg04Iva.findByIvaImportoDet", query = "SELECT r FROM Reg04Iva r WHERE r.ivaImportoDet = :ivaImportoDet")
    , @NamedQuery(name = "Reg04Iva.findByReg04IvaId", query = "SELECT r FROM Reg04Iva r WHERE r.reg04IvaId = :reg04IvaId")})
public class Reg04Iva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "iva_op")
    private String ivaOp;
    @Size(max = 255)
    @Column(name = "iva_fun")
    private String ivaFun;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iva_perc")
    private BigDecimal ivaPerc;
    @Column(name = "iva_perc_det")
    private BigDecimal ivaPercDet;
    @Column(name = "iva_riparto_acq")
    private BigDecimal ivaRipartoAcq;
    @Column(name = "iva_imposta")
    private BigDecimal ivaImposta;
    @Column(name = "iva_importo_non_det")
    private BigDecimal ivaImportoNonDet;
    @Size(max = 10)
    @Column(name = "iva_natura")
    private String ivaNatura;
    @Column(name = "iva_perc_ded")
    private BigDecimal ivaPercDed;
    @Column(name = "iva_importo_ded")
    private BigDecimal ivaImportoDed;
    @Column(name = "iva_perc_non_det")
    private BigDecimal ivaPercNonDet;
    @Column(name = "iva_importo_det")
    private BigDecimal ivaImportoDet;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reg04_iva_id")
    private Long reg04IvaId;
    @JoinColumns({
        @JoinColumn(name = "reg03_rigo_reg_id", referencedColumnName = "reg03_rigo_reg_id")
        , @JoinColumn(name = "reg03_subrigo_reg_id", referencedColumnName = "reg03_subrigo_reg_id")
        , @JoinColumn(name = "reg01_num_reg_id", referencedColumnName = "reg03_num_reg_id")})
    @ManyToOne(optional = true)
    private Reg03Rigo reg03Rigo;

    public Reg04Iva() {
    }

    public Reg04Iva(Long reg04IvaId) {
        this.reg04IvaId = reg04IvaId;
    }

    public String getIvaOp() {
        return ivaOp;
    }

    public void setIvaOp(String ivaOp) {
        this.ivaOp = ivaOp;
    }

    public String getIvaFun() {
        return ivaFun;
    }

    public void setIvaFun(String ivaFun) {
        this.ivaFun = ivaFun;
    }

    public BigDecimal getIvaPerc() {
        return ivaPerc;
    }

    public void setIvaPerc(BigDecimal ivaPerc) {
        this.ivaPerc = ivaPerc;
    }

    public BigDecimal getIvaPercDet() {
        return ivaPercDet;
    }

    public void setIvaPercDet(BigDecimal ivaPercDet) {
        this.ivaPercDet = ivaPercDet;
    }

    public BigDecimal getIvaRipartoAcq() {
        return ivaRipartoAcq;
    }

    public void setIvaRipartoAcq(BigDecimal ivaRipartoAcq) {
        this.ivaRipartoAcq = ivaRipartoAcq;
    }

    public BigDecimal getIvaImposta() {
        return ivaImposta;
    }

    public void setIvaImposta(BigDecimal ivaImposta) {
        this.ivaImposta = ivaImposta;
    }

    public BigDecimal getIvaImportoNonDet() {
        return ivaImportoNonDet;
    }

    public void setIvaImportoNonDet(BigDecimal ivaImportoNonDet) {
        this.ivaImportoNonDet = ivaImportoNonDet;
    }

    public String getIvaNatura() {
        return ivaNatura;
    }

    public void setIvaNatura(String ivaNatura) {
        this.ivaNatura = ivaNatura;
    }

    public BigDecimal getIvaPercDed() {
        return ivaPercDed;
    }

    public void setIvaPercDed(BigDecimal ivaPercDed) {
        this.ivaPercDed = ivaPercDed;
    }

    public BigDecimal getIvaImportoDed() {
        return ivaImportoDed;
    }

    public void setIvaImportoDed(BigDecimal ivaImportoDed) {
        this.ivaImportoDed = ivaImportoDed;
    }

    public BigDecimal getIvaPercNonDet() {
        return ivaPercNonDet;
    }

    public void setIvaPercNonDet(BigDecimal ivaPercNonDet) {
        this.ivaPercNonDet = ivaPercNonDet;
    }

    public BigDecimal getIvaImportoDet() {
        return ivaImportoDet;
    }

    public void setIvaImportoDet(BigDecimal ivaImportoDet) {
        this.ivaImportoDet = ivaImportoDet;
    }

    public Long getReg04IvaId() {
        return reg04IvaId;
    }

    public void setReg04IvaId(Long reg04IvaId) {
        this.reg04IvaId = reg04IvaId;
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
        hash += (reg04IvaId != null ? reg04IvaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reg04Iva)) {
            return false;
        }
        Reg04Iva other = (Reg04Iva) object;
        if ((this.reg04IvaId == null && other.reg04IvaId != null) || (this.reg04IvaId != null && !this.reg04IvaId.equals(other.reg04IvaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.ditta.Reg04Iva[ reg04IvaId=" + reg04IvaId + " ]";
    }
    
}
