/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.ditta;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Luca
 */
@Embeddable
public class Reg03RigoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "reg03_rigo_reg_id")
    private long reg03RigoRegId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reg03_subrigo_reg_id")
    private long reg03SubrigoRegId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reg03_num_reg_id")
    private long reg03NumRegId;

    public Reg03RigoPK() {
    }

    public Reg03RigoPK(long reg03RigoRegId, long reg03SubrigoRegId, long reg03NumRegId) {
        this.reg03RigoRegId = reg03RigoRegId;
        this.reg03SubrigoRegId = reg03SubrigoRegId;
        this.reg03NumRegId = reg03NumRegId;
    }

    public long getReg03RigoRegId() {
        return reg03RigoRegId;
    }

    public void setReg03RigoRegId(long reg03RigoRegId) {
        this.reg03RigoRegId = reg03RigoRegId;
    }

    public long getReg03SubrigoRegId() {
        return reg03SubrigoRegId;
    }

    public void setReg03SubrigoRegId(long reg03SubrigoRegId) {
        this.reg03SubrigoRegId = reg03SubrigoRegId;
    }

    public long getReg03NumRegId() {
        return reg03NumRegId;
    }

    public void setReg03NumRegId(long reg03NumRegId) {
        this.reg03NumRegId = reg03NumRegId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) reg03RigoRegId;
        hash += (int) reg03SubrigoRegId;
        hash += (int) reg03NumRegId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reg03RigoPK)) {
            return false;
        }
        Reg03RigoPK other = (Reg03RigoPK) object;
        if (this.reg03RigoRegId != other.reg03RigoRegId) {
            return false;
        }
        if (this.reg03SubrigoRegId != other.reg03SubrigoRegId) {
            return false;
        }
        if (this.reg03NumRegId != other.reg03NumRegId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.ditta.Reg03RigoPK[ reg03RigoRegId=" + reg03RigoRegId + ", reg03SubrigoRegId=" + reg03SubrigoRegId + ", reg03NumRegId=" + reg03NumRegId + " ]";
    }
    
}
