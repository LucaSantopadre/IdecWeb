/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.ditta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luca
 */
@Entity
@Table(name = "reg03_rigo", schema = "fi001")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reg03Rigo.findAll", query = "SELECT r FROM Reg03Rigo r")
    , @NamedQuery(name = "Reg03Rigo.findByReg03RigoRegId", query = "SELECT r FROM Reg03Rigo r WHERE r.reg03RigoPK.reg03RigoRegId = :reg03RigoRegId")
    , @NamedQuery(name = "Reg03Rigo.findByReg03SubrigoRegId", query = "SELECT r FROM Reg03Rigo r WHERE r.reg03RigoPK.reg03SubrigoRegId = :reg03SubrigoRegId")
    , @NamedQuery(name = "Reg03Rigo.findByReg03NumRegId", query = "SELECT r FROM Reg03Rigo r WHERE r.reg03RigoPK.reg03NumRegId = :reg03NumRegId")
    , @NamedQuery(name = "Reg03Rigo.findByConto", query = "SELECT r FROM Reg03Rigo r WHERE r.conto = :conto")
    , @NamedQuery(name = "Reg03Rigo.findByFunzConto", query = "SELECT r FROM Reg03Rigo r WHERE r.funzConto = :funzConto")
    , @NamedQuery(name = "Reg03Rigo.findByVendor", query = "SELECT r FROM Reg03Rigo r WHERE r.vendor = :vendor")
    , @NamedQuery(name = "Reg03Rigo.findBySubconto", query = "SELECT r FROM Reg03Rigo r WHERE r.subconto = :subconto")
    , @NamedQuery(name = "Reg03Rigo.findByDare", query = "SELECT r FROM Reg03Rigo r WHERE r.dare = :dare")
    , @NamedQuery(name = "Reg03Rigo.findByAvere", query = "SELECT r FROM Reg03Rigo r WHERE r.avere = :avere")
    , @NamedQuery(name = "Reg03Rigo.findByImportoConto", query = "SELECT r FROM Reg03Rigo r WHERE r.importoConto = :importoConto")
    , @NamedQuery(name = "Reg03Rigo.findByOpIva", query = "SELECT r FROM Reg03Rigo r WHERE r.opIva = :opIva")
    , @NamedQuery(name = "Reg03Rigo.findByFunIva", query = "SELECT r FROM Reg03Rigo r WHERE r.funIva = :funIva")
    , @NamedQuery(name = "Reg03Rigo.findByPercIva", query = "SELECT r FROM Reg03Rigo r WHERE r.percIva = :percIva")
    , @NamedQuery(name = "Reg03Rigo.findByPercDetIva", query = "SELECT r FROM Reg03Rigo r WHERE r.percDetIva = :percDetIva")
    , @NamedQuery(name = "Reg03Rigo.findByRipPerc", query = "SELECT r FROM Reg03Rigo r WHERE r.ripPerc = :ripPerc")
    , @NamedQuery(name = "Reg03Rigo.findByIvaDetraibile", query = "SELECT r FROM Reg03Rigo r WHERE r.ivaDetraibile = :ivaDetraibile")
    , @NamedQuery(name = "Reg03Rigo.findByNonDetraibile", query = "SELECT r FROM Reg03Rigo r WHERE r.nonDetraibile = :nonDetraibile")
    , @NamedQuery(name = "Reg03Rigo.findByFunzImmobProdotto", query = "SELECT r FROM Reg03Rigo r WHERE r.funzImmobProdotto = :funzImmobProdotto")
    , @NamedQuery(name = "Reg03Rigo.findByCodAtt", query = "SELECT r FROM Reg03Rigo r WHERE r.codAtt = :codAtt")
    , @NamedQuery(name = "Reg03Rigo.findByPercInerente", query = "SELECT r FROM Reg03Rigo r WHERE r.percInerente = :percInerente")
    , @NamedQuery(name = "Reg03Rigo.findByPercIiDeducibile", query = "SELECT r FROM Reg03Rigo r WHERE r.percIiDeducibile = :percIiDeducibile")
    , @NamedQuery(name = "Reg03Rigo.findByPercIrapDeducibile", query = "SELECT r FROM Reg03Rigo r WHERE r.percIrapDeducibile = :percIrapDeducibile")
    , @NamedQuery(name = "Reg03Rigo.findByRateo", query = "SELECT r FROM Reg03Rigo r WHERE r.rateo = :rateo")
    , @NamedQuery(name = "Reg03Rigo.findByAccertamento", query = "SELECT r FROM Reg03Rigo r WHERE r.accertamento = :accertamento")
    , @NamedQuery(name = "Reg03Rigo.findByFV", query = "SELECT r FROM Reg03Rigo r WHERE r.fV = :fV")
    , @NamedQuery(name = "Reg03Rigo.findByLocalita", query = "SELECT r FROM Reg03Rigo r WHERE r.localita = :localita")
    , @NamedQuery(name = "Reg03Rigo.findByCentroCosto", query = "SELECT r FROM Reg03Rigo r WHERE r.centroCosto = :centroCosto")
    , @NamedQuery(name = "Reg03Rigo.findByPagatoDoc", query = "SELECT r FROM Reg03Rigo r WHERE r.pagatoDoc = :pagatoDoc")
    , @NamedQuery(name = "Reg03Rigo.findByIvaNatura", query = "SELECT r FROM Reg03Rigo r WHERE r.ivaNatura = :ivaNatura")
    , @NamedQuery(name = "Reg03Rigo.findByPercIvaDeducibile", query = "SELECT r FROM Reg03Rigo r WHERE r.percIvaDeducibile = :percIvaDeducibile")})
public class Reg03Rigo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Reg03RigoPK reg03RigoPK;
    @Size(max = 255)
    @Column(name = "conto")
    private String conto;
    @Size(max = 255)
    @Column(name = "funz_conto")
    private String funzConto;
    @Size(max = 255)
    @Column(name = "vendor")
    private String vendor;
    @Size(max = 255)
    @Column(name = "subconto")
    private String subconto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dare")
    private BigDecimal dare;
    @Column(name = "avere")
    private BigDecimal avere;
    @Column(name = "importo_conto")
    private BigDecimal importoConto;
    @Size(max = 255)
    @Column(name = "op_iva")
    private String opIva;
    @Size(max = 255)
    @Column(name = "fun_iva")
    private String funIva;
    @Column(name = "perc_iva")
    private BigDecimal percIva;
    @Column(name = "perc_det_iva")
    private BigDecimal percDetIva;
    @Column(name = "rip_perc")
    private BigDecimal ripPerc;
    @Column(name = "iva_detraibile")
    private BigDecimal ivaDetraibile;
    @Column(name = "non_detraibile")
    private BigDecimal nonDetraibile;
    @Size(max = 2)
    @Column(name = "funz_immob_prodotto")
    private String funzImmobProdotto;
    @Size(max = 10)
    @Column(name = "cod_att")
    private String codAtt;
    @Column(name = "perc_inerente")
    private BigDecimal percInerente;
    @Column(name = "perc_ii_deducibile")
    private BigDecimal percIiDeducibile;
    @Column(name = "perc_irap_deducibile")
    private BigDecimal percIrapDeducibile;
    @Size(max = 2)
    @Column(name = "rateo")
    private String rateo;
    @Size(max = 2)
    @Column(name = "accertamento")
    private String accertamento;
    @Size(max = 2)
    @Column(name = "f_v")
    private String fV;
    @Size(max = 25)
    @Column(name = "localita")
    private String localita;
    @Size(max = 25)
    @Column(name = "centro_costo")
    private String centroCosto;
    @Column(name = "pagato_doc")
    private Boolean pagatoDoc;
    @Size(max = 10)
    @Column(name = "iva_natura")
    private String ivaNatura;
    @Column(name = "perc_iva_deducibile")
    private BigDecimal percIvaDeducibile;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reg03Rigo", fetch = FetchType.EAGER)
//    private List<RegDoc> regDocList;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reg03Rigo", fetch = FetchType.EAGER)
//    private List<Reg04Iva> reg04IvaList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reg03Rigo", fetch = FetchType.EAGER)
    private RegDoc regDoc;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reg03Rigo", fetch = FetchType.EAGER)
    private Reg04Iva reg04Iva;
    
    
    
    @JoinColumn(name = "reg01_num_reg_id", referencedColumnName = "num_reg_id")
    @ManyToOne(optional = true)
    private Reg01 reg01NumRegId;

//    // ***LUCA
//    private RegDoc regDoc;
//
//    public RegDoc getRegDoc() {
//        return regDoc;
//    }
//
//    public void setRegDoc(RegDoc regDoc) {
//        this.regDoc = regDoc;
//    }
//    // --------------------------
//    
    public Reg03Rigo() {
    }

    public Reg03Rigo(Reg03RigoPK reg03RigoPK) {
        this.reg03RigoPK = reg03RigoPK;
    }

    public Reg03Rigo(long reg03RigoRegId, long reg03SubrigoRegId, long reg03NumRegId) {
        this.reg03RigoPK = new Reg03RigoPK(reg03RigoRegId, reg03SubrigoRegId, reg03NumRegId);
    }

    public Reg03RigoPK getReg03RigoPK() {
        return reg03RigoPK;
    }

    public void setReg03RigoPK(Reg03RigoPK reg03RigoPK) {
        this.reg03RigoPK = reg03RigoPK;
    }

    public String getConto() {
        return conto;
    }

    public void setConto(String conto) {
        this.conto = conto;
    }

    public String getFunzConto() {
        return funzConto;
    }

    public void setFunzConto(String funzConto) {
        this.funzConto = funzConto;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getSubconto() {
        return subconto;
    }

    public void setSubconto(String subconto) {
        this.subconto = subconto;
    }

    public BigDecimal getDare() {
        return dare;
    }

    public void setDare(BigDecimal dare) {
        this.dare = dare;
    }

    public BigDecimal getAvere() {
        return avere;
    }

    public void setAvere(BigDecimal avere) {
        this.avere = avere;
    }

    public BigDecimal getImportoConto() {
        return importoConto;
    }

    public void setImportoConto(BigDecimal importoConto) {
        this.importoConto = importoConto;
    }

    public String getOpIva() {
        return opIva;
    }

    public void setOpIva(String opIva) {
        this.opIva = opIva;
    }

    public String getFunIva() {
        return funIva;
    }

    public void setFunIva(String funIva) {
        this.funIva = funIva;
    }

    public BigDecimal getPercIva() {
        return percIva;
    }

    public void setPercIva(BigDecimal percIva) {
        this.percIva = percIva;
    }

    public BigDecimal getPercDetIva() {
        return percDetIva;
    }

    public void setPercDetIva(BigDecimal percDetIva) {
        this.percDetIva = percDetIva;
    }

    public BigDecimal getRipPerc() {
        return ripPerc;
    }

    public void setRipPerc(BigDecimal ripPerc) {
        this.ripPerc = ripPerc;
    }

    public BigDecimal getIvaDetraibile() {
        return ivaDetraibile;
    }

    public void setIvaDetraibile(BigDecimal ivaDetraibile) {
        this.ivaDetraibile = ivaDetraibile;
    }

    public BigDecimal getNonDetraibile() {
        return nonDetraibile;
    }

    public void setNonDetraibile(BigDecimal nonDetraibile) {
        this.nonDetraibile = nonDetraibile;
    }

    public String getFunzImmobProdotto() {
        return funzImmobProdotto;
    }

    public void setFunzImmobProdotto(String funzImmobProdotto) {
        this.funzImmobProdotto = funzImmobProdotto;
    }

    public String getCodAtt() {
        return codAtt;
    }

    public void setCodAtt(String codAtt) {
        this.codAtt = codAtt;
    }

    public BigDecimal getPercInerente() {
        return percInerente;
    }

    public void setPercInerente(BigDecimal percInerente) {
        this.percInerente = percInerente;
    }

    public BigDecimal getPercIiDeducibile() {
        return percIiDeducibile;
    }

    public void setPercIiDeducibile(BigDecimal percIiDeducibile) {
        this.percIiDeducibile = percIiDeducibile;
    }

    public BigDecimal getPercIrapDeducibile() {
        return percIrapDeducibile;
    }

    public void setPercIrapDeducibile(BigDecimal percIrapDeducibile) {
        this.percIrapDeducibile = percIrapDeducibile;
    }

    public String getRateo() {
        return rateo;
    }

    public void setRateo(String rateo) {
        this.rateo = rateo;
    }

    public String getAccertamento() {
        return accertamento;
    }

    public void setAccertamento(String accertamento) {
        this.accertamento = accertamento;
    }

    public String getFV() {
        return fV;
    }

    public void setFV(String fV) {
        this.fV = fV;
    }

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public Boolean getPagatoDoc() {
        return pagatoDoc;
    }

    public void setPagatoDoc(Boolean pagatoDoc) {
        this.pagatoDoc = pagatoDoc;
    }

    public String getIvaNatura() {
        return ivaNatura;
    }

    public void setIvaNatura(String ivaNatura) {
        this.ivaNatura = ivaNatura;
    }

    public BigDecimal getPercIvaDeducibile() {
        return percIvaDeducibile;
    }

    public void setPercIvaDeducibile(BigDecimal percIvaDeducibile) {
        this.percIvaDeducibile = percIvaDeducibile;
    }

//    @XmlTransient
//    public List<RegDoc> getRegDocList() {
//        return regDocList;
//    }
//
//    public void setRegDocList(List<RegDoc> regDocList) {
//        this.regDocList = regDocList;
//    }
//
//    @XmlTransient
//    public List<Reg04Iva> getReg04IvaList() {
//        return reg04IvaList;
//    }
//
//    public void setReg04IvaList(List<Reg04Iva> reg04IvaList) {
//        this.reg04IvaList = reg04IvaList;
//    }

    public RegDoc getRegDoc() {
        return regDoc;
    }

    public void setRegDoc(RegDoc regDoc) {
        this.regDoc = regDoc;
    }

    public Reg04Iva getReg04Iva() {
        return reg04Iva;
    }

    public void setReg04Iva(Reg04Iva reg04Iva) {
        this.reg04Iva = reg04Iva;
    }
    
    
    
    public Reg01 getReg01NumRegId() {
        return reg01NumRegId;
    }

    public void setReg01NumRegId(Reg01 reg01NumRegId) {
        this.reg01NumRegId = reg01NumRegId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reg03RigoPK != null ? reg03RigoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reg03Rigo)) {
            return false;
        }
        Reg03Rigo other = (Reg03Rigo) object;
        if ((this.reg03RigoPK == null && other.reg03RigoPK != null) || (this.reg03RigoPK != null && !this.reg03RigoPK.equals(other.reg03RigoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.ditta.Reg03Rigo[ reg03RigoPK=" + reg03RigoPK + " ]";
    }

}
