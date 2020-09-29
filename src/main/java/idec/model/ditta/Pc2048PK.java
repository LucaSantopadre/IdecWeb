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
import javax.validation.constraints.Size;

/**
 *
 * @author Luca
 */
@Embeddable
public class Pc2048PK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cg_tipo_mas_cod")
    private int cgTipoMasCod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cee_mas_cod")
    private int ceeMasCod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cee_mas2_cod")
    private int ceeMas2Cod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cee_mas3_cod")
    private int ceeMas3Cod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cee_mas4_cod")
    private int ceeMas4Cod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "cee_conto_cod")
    private String ceeContoCod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "cee_codice_cod")
    private String ceeCodiceCod;

    public Pc2048PK() {
    }

    public Pc2048PK(int cgTipoMasCod, int ceeMasCod, int ceeMas2Cod, int ceeMas3Cod, int ceeMas4Cod, String ceeContoCod, String ceeCodiceCod) {
        this.cgTipoMasCod = cgTipoMasCod;
        this.ceeMasCod = ceeMasCod;
        this.ceeMas2Cod = ceeMas2Cod;
        this.ceeMas3Cod = ceeMas3Cod;
        this.ceeMas4Cod = ceeMas4Cod;
        this.ceeContoCod = ceeContoCod;
        this.ceeCodiceCod = ceeCodiceCod;
    }

    public int getCgTipoMasCod() {
        return cgTipoMasCod;
    }

    public void setCgTipoMasCod(int cgTipoMasCod) {
        this.cgTipoMasCod = cgTipoMasCod;
    }

    public int getCeeMasCod() {
        return ceeMasCod;
    }

    public void setCeeMasCod(int ceeMasCod) {
        this.ceeMasCod = ceeMasCod;
    }

    public int getCeeMas2Cod() {
        return ceeMas2Cod;
    }

    public void setCeeMas2Cod(int ceeMas2Cod) {
        this.ceeMas2Cod = ceeMas2Cod;
    }

    public int getCeeMas3Cod() {
        return ceeMas3Cod;
    }

    public void setCeeMas3Cod(int ceeMas3Cod) {
        this.ceeMas3Cod = ceeMas3Cod;
    }

    public int getCeeMas4Cod() {
        return ceeMas4Cod;
    }

    public void setCeeMas4Cod(int ceeMas4Cod) {
        this.ceeMas4Cod = ceeMas4Cod;
    }

    public String getCeeContoCod() {
        return ceeContoCod;
    }

    public void setCeeContoCod(String ceeContoCod) {
        this.ceeContoCod = ceeContoCod;
    }

    public String getCeeCodiceCod() {
        return ceeCodiceCod;
    }

    public void setCeeCodiceCod(String ceeCodiceCod) {
        this.ceeCodiceCod = ceeCodiceCod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cgTipoMasCod;
        hash += (int) ceeMasCod;
        hash += (int) ceeMas2Cod;
        hash += (int) ceeMas3Cod;
        hash += (int) ceeMas4Cod;
        hash += (ceeContoCod != null ? ceeContoCod.hashCode() : 0);
        hash += (ceeCodiceCod != null ? ceeCodiceCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pc2048PK)) {
            return false;
        }
        Pc2048PK other = (Pc2048PK) object;
        if (this.cgTipoMasCod != other.cgTipoMasCod) {
            return false;
        }
        if (this.ceeMasCod != other.ceeMasCod) {
            return false;
        }
        if (this.ceeMas2Cod != other.ceeMas2Cod) {
            return false;
        }
        if (this.ceeMas3Cod != other.ceeMas3Cod) {
            return false;
        }
        if (this.ceeMas4Cod != other.ceeMas4Cod) {
            return false;
        }
        if ((this.ceeContoCod == null && other.ceeContoCod != null) || (this.ceeContoCod != null && !this.ceeContoCod.equals(other.ceeContoCod))) {
            return false;
        }
        if ((this.ceeCodiceCod == null && other.ceeCodiceCod != null) || (this.ceeCodiceCod != null && !this.ceeCodiceCod.equals(other.ceeCodiceCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.ditta.Pc2048PK[ cgTipoMasCod=" + cgTipoMasCod + ", ceeMasCod=" + ceeMasCod + ", ceeMas2Cod=" + ceeMas2Cod + ", ceeMas3Cod=" + ceeMas3Cod + ", ceeMas4Cod=" + ceeMas4Cod + ", ceeContoCod=" + ceeContoCod + ", ceeCodiceCod=" + ceeCodiceCod + " ]";
    }
    
}
