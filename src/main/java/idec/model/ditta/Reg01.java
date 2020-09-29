/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.ditta;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "reg01",schema="fi001")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reg01.findAll", query = "SELECT r FROM Reg01 r")
    , @NamedQuery(name = "Reg01.findByNumRegId", query = "SELECT r FROM Reg01 r WHERE r.numRegId = :numRegId")
    , @NamedQuery(name = "Reg01.findByDataReg", query = "SELECT r FROM Reg01 r WHERE r.dataReg = :dataReg")
    , @NamedQuery(name = "Reg01.findByReparto", query = "SELECT r FROM Reg01 r WHERE r.reparto = :reparto")
    , @NamedQuery(name = "Reg01.findByLibro", query = "SELECT r FROM Reg01 r WHERE r.libro = :libro")
    , @NamedQuery(name = "Reg01.findByProtocolloLibro", query = "SELECT r FROM Reg01 r WHERE r.protocolloLibro = :protocolloLibro")})
public class Reg01 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_reg_id")
    private Long numRegId;
    @Column(name = "data_reg")
    @Temporal(TemporalType.DATE)
    private Date dataReg;
    @Size(max = 3)
    @Column(name = "reparto")
    private String reparto;
    @Column(name = "libro")
    private Integer libro;
    @Column(name = "protocollo_libro")
    private BigInteger protocolloLibro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reg01NumRegId")
    private List<Reg03Rigo> reg03RigoList;
    
    
    
    // ***LUCA
    //private String dataRegString;

    public Reg01() {
    }

    public Reg01(Long numRegId) {
        this.numRegId = numRegId;
    }

    public Long getNumRegId() {
        return numRegId;
    }

    public void setNumRegId(Long numRegId) {
        this.numRegId = numRegId;
    }

    public Date getDataReg() {
        return dataReg;
    }

    public void setDataReg(Date dataReg) {
        this.dataReg = dataReg;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public Integer getLibro() {
        return libro;
    }

    public void setLibro(Integer libro) {
        this.libro = libro;
    }

    public BigInteger getProtocolloLibro() {
        return protocolloLibro;
    }

    public void setProtocolloLibro(BigInteger protocolloLibro) {
        this.protocolloLibro = protocolloLibro;
    }

    @XmlTransient
    public List<Reg03Rigo> getReg03RigoList() {
        return reg03RigoList;
    }

    public void setReg03RigoList(List<Reg03Rigo> reg03RigoList) {
        this.reg03RigoList = reg03RigoList;
    }

//    public String getDataRegString() {
//        return dataRegString;
//    }
//
//    public void setDataRegString(String dataRegString) {
//        this.dataRegString = dataRegString;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numRegId != null ? numRegId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reg01)) {
            return false;
        }
        Reg01 other = (Reg01) object;
        if ((this.numRegId == null && other.numRegId != null) || (this.numRegId != null && !this.numRegId.equals(other.numRegId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.ditta.Reg01[ numRegId=" + numRegId + " ]";
    }
    
}
