/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.pub;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "moneta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moneta.findAll", query = "SELECT m FROM Moneta m")
    , @NamedQuery(name = "Moneta.findByDivCod", query = "SELECT m FROM Moneta m WHERE m.divCod = :divCod")
    , @NamedQuery(name = "Moneta.findByDivDes", query = "SELECT m FROM Moneta m WHERE m.divDes = :divDes")
    , @NamedQuery(name = "Moneta.findByCodMoneta", query = "SELECT m FROM Moneta m WHERE m.codMoneta = :codMoneta")
    , @NamedQuery(name = "Moneta.findByCodStato", query = "SELECT m FROM Moneta m WHERE m.codStato = :codStato")
    , @NamedQuery(name = "Moneta.findBySiglaStato", query = "SELECT m FROM Moneta m WHERE m.siglaStato = :siglaStato")
    , @NamedQuery(name = "Moneta.findByDesStato", query = "SELECT m FROM Moneta m WHERE m.desStato = :desStato")
    , @NamedQuery(name = "Moneta.findByCambio", query = "SELECT m FROM Moneta m WHERE m.cambio = :cambio")})
public class Moneta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "div_cod")
    private String divCod;
    @Size(max = 50)
    @Column(name = "div_des")
    private String divDes;
    @Column(name = "cod_moneta")
    private Integer codMoneta;
    @Size(max = 4)
    @Column(name = "cod_stato")
    private String codStato;
    @Size(max = 4)
    @Column(name = "sigla_stato")
    private String siglaStato;
    @Size(max = 40)
    @Column(name = "des_stato")
    private String desStato;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cambio")
    private BigDecimal cambio;

    public Moneta() {
    }

    public Moneta(String divCod) {
        this.divCod = divCod;
    }

    public String getDivCod() {
        return divCod;
    }

    public void setDivCod(String divCod) {
        this.divCod = divCod;
    }

    public String getDivDes() {
        return divDes;
    }

    public void setDivDes(String divDes) {
        this.divDes = divDes;
    }

    public Integer getCodMoneta() {
        return codMoneta;
    }

    public void setCodMoneta(Integer codMoneta) {
        this.codMoneta = codMoneta;
    }

    public String getCodStato() {
        return codStato;
    }

    public void setCodStato(String codStato) {
        this.codStato = codStato;
    }

    public String getSiglaStato() {
        return siglaStato;
    }

    public void setSiglaStato(String siglaStato) {
        this.siglaStato = siglaStato;
    }

    public String getDesStato() {
        return desStato;
    }

    public void setDesStato(String desStato) {
        this.desStato = desStato;
    }

    public BigDecimal getCambio() {
        return cambio;
    }

    public void setCambio(BigDecimal cambio) {
        this.cambio = cambio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (divCod != null ? divCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moneta)) {
            return false;
        }
        Moneta other = (Moneta) object;
        if ((this.divCod == null && other.divCod != null) || (this.divCod != null && !this.divCod.equals(other.divCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.Moneta[ divCod=" + divCod + " ]";
    }
    
}
