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
@Table(name = "iva_funzione")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IvaFunzione.findAll", query = "SELECT i FROM IvaFunzione i")
    , @NamedQuery(name = "IvaFunzione.findByIvaFunzId", query = "SELECT i FROM IvaFunzione i WHERE i.ivaFunzId = :ivaFunzId")
    , @NamedQuery(name = "IvaFunzione.findByAVN", query = "SELECT i FROM IvaFunzione i WHERE i.aVN = :aVN")
    , @NamedQuery(name = "IvaFunzione.findByDesFunzioneIva", query = "SELECT i FROM IvaFunzione i WHERE i.desFunzioneIva = :desFunzioneIva")
    , @NamedQuery(name = "IvaFunzione.findByTipoOperazioneIva", query = "SELECT i FROM IvaFunzione i WHERE i.tipoOperazioneIva = :tipoOperazioneIva")
    , @NamedQuery(name = "IvaFunzione.findBySommaImponibile", query = "SELECT i FROM IvaFunzione i WHERE i.sommaImponibile = :sommaImponibile")
    , @NamedQuery(name = "IvaFunzione.findByLibroTipoStornoIva", query = "SELECT i FROM IvaFunzione i WHERE i.libroTipoStornoIva = :libroTipoStornoIva")
    , @NamedQuery(name = "IvaFunzione.findByLibroStornoIva", query = "SELECT i FROM IvaFunzione i WHERE i.libroStornoIva = :libroStornoIva")
    , @NamedQuery(name = "IvaFunzione.findByCausaleStornoIva", query = "SELECT i FROM IvaFunzione i WHERE i.causaleStornoIva = :causaleStornoIva")
    , @NamedQuery(name = "IvaFunzione.findByContoIva", query = "SELECT i FROM IvaFunzione i WHERE i.contoIva = :contoIva")})
public class IvaFunzione implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva_funz_id")
    private Integer ivaFunzId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a_v_n")
    private Character aVN;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "des_funzione_iva")
    private String desFunzioneIva;
    @Column(name = "tipo_operazione_iva")
    private Integer tipoOperazioneIva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "somma_imponibile")
    private Character sommaImponibile;
    @Basic(optional = false)
    @NotNull
    @Column(name = "libro_tipo_storno_iva")
    private Character libroTipoStornoIva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "libro_storno_iva")
    private short libroStornoIva;
    @Size(max = 6)
    @Column(name = "causale_storno_iva")
    private String causaleStornoIva;
    @Size(max = 10)
    @Column(name = "conto_iva")
    private String contoIva;

    public IvaFunzione() {
    }

    public IvaFunzione(Integer ivaFunzId) {
        this.ivaFunzId = ivaFunzId;
    }

    public IvaFunzione(Integer ivaFunzId, Character aVN, String desFunzioneIva, Character sommaImponibile, Character libroTipoStornoIva, short libroStornoIva) {
        this.ivaFunzId = ivaFunzId;
        this.aVN = aVN;
        this.desFunzioneIva = desFunzioneIva;
        this.sommaImponibile = sommaImponibile;
        this.libroTipoStornoIva = libroTipoStornoIva;
        this.libroStornoIva = libroStornoIva;
    }

    public Integer getIvaFunzId() {
        return ivaFunzId;
    }

    public void setIvaFunzId(Integer ivaFunzId) {
        this.ivaFunzId = ivaFunzId;
    }

    public Character getAVN() {
        return aVN;
    }

    public void setAVN(Character aVN) {
        this.aVN = aVN;
    }

    public String getDesFunzioneIva() {
        return desFunzioneIva;
    }

    public void setDesFunzioneIva(String desFunzioneIva) {
        this.desFunzioneIva = desFunzioneIva;
    }

    public Integer getTipoOperazioneIva() {
        return tipoOperazioneIva;
    }

    public void setTipoOperazioneIva(Integer tipoOperazioneIva) {
        this.tipoOperazioneIva = tipoOperazioneIva;
    }

    public Character getSommaImponibile() {
        return sommaImponibile;
    }

    public void setSommaImponibile(Character sommaImponibile) {
        this.sommaImponibile = sommaImponibile;
    }

    public Character getLibroTipoStornoIva() {
        return libroTipoStornoIva;
    }

    public void setLibroTipoStornoIva(Character libroTipoStornoIva) {
        this.libroTipoStornoIva = libroTipoStornoIva;
    }

    public short getLibroStornoIva() {
        return libroStornoIva;
    }

    public void setLibroStornoIva(short libroStornoIva) {
        this.libroStornoIva = libroStornoIva;
    }

    public String getCausaleStornoIva() {
        return causaleStornoIva;
    }

    public void setCausaleStornoIva(String causaleStornoIva) {
        this.causaleStornoIva = causaleStornoIva;
    }

    public String getContoIva() {
        return contoIva;
    }

    public void setContoIva(String contoIva) {
        this.contoIva = contoIva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ivaFunzId != null ? ivaFunzId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IvaFunzione)) {
            return false;
        }
        IvaFunzione other = (IvaFunzione) object;
        if ((this.ivaFunzId == null && other.ivaFunzId != null) || (this.ivaFunzId != null && !this.ivaFunzId.equals(other.ivaFunzId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.IvaFunzione[ ivaFunzId=" + ivaFunzId + " ]";
    }
    
}
