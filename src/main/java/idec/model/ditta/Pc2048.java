/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.ditta;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "\"2048pc\"", schema = "test02")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pc2048.findAll", query = "SELECT p FROM Pc2048 p")
    , @NamedQuery(name = "Pc2048.findByCgTipoMasCod", query = "SELECT p FROM Pc2048 p WHERE p.pc2048PK.cgTipoMasCod = :cgTipoMasCod")
    , @NamedQuery(name = "Pc2048.findByCeeMasCod", query = "SELECT p FROM Pc2048 p WHERE p.pc2048PK.ceeMasCod = :ceeMasCod")
    , @NamedQuery(name = "Pc2048.findByCeeMas2Cod", query = "SELECT p FROM Pc2048 p WHERE p.pc2048PK.ceeMas2Cod = :ceeMas2Cod")
    , @NamedQuery(name = "Pc2048.findByCeeMas3Cod", query = "SELECT p FROM Pc2048 p WHERE p.pc2048PK.ceeMas3Cod = :ceeMas3Cod")
    , @NamedQuery(name = "Pc2048.findByCeeMas4Cod", query = "SELECT p FROM Pc2048 p WHERE p.pc2048PK.ceeMas4Cod = :ceeMas4Cod")
    , @NamedQuery(name = "Pc2048.findByCeeContoCod", query = "SELECT p FROM Pc2048 p WHERE p.pc2048PK.ceeContoCod = :ceeContoCod")
    , @NamedQuery(name = "Pc2048.findByCeeCodiceCod", query = "SELECT p FROM Pc2048 p WHERE p.pc2048PK.ceeCodiceCod = :ceeCodiceCod")
    , @NamedQuery(name = "Pc2048.findByCeeCodiceCognome", query = "SELECT p FROM Pc2048 p WHERE p.ceeCodiceCognome = :ceeCodiceCognome")
    , @NamedQuery(name = "Pc2048.findByCeeCodiceNome", query = "SELECT p FROM Pc2048 p WHERE p.ceeCodiceNome = :ceeCodiceNome")
    , @NamedQuery(name = "Pc2048.findByPartIva", query = "SELECT p FROM Pc2048 p WHERE p.partIva = :partIva")
    , @NamedQuery(name = "Pc2048.findByComune", query = "SELECT p FROM Pc2048 p WHERE p.comune = :comune")
    , @NamedQuery(name = "Pc2048.findByFiscCod", query = "SELECT p FROM Pc2048 p WHERE p.fiscCod = :fiscCod")
    , @NamedQuery(name = "Pc2048.findByAnnoObbligatorio", query = "SELECT p FROM Pc2048 p WHERE p.annoObbligatorio = :annoObbligatorio")
    , @NamedQuery(name = "Pc2048.findByAssociaCodice", query = "SELECT p FROM Pc2048 p WHERE p.associaCodice = :associaCodice")
    , @NamedQuery(name = "Pc2048.findBySiglaNazione", query = "SELECT p FROM Pc2048 p WHERE p.siglaNazione = :siglaNazione")
    , @NamedQuery(name = "Pc2048.findByPrivato", query = "SELECT p FROM Pc2048 p WHERE p.privato = :privato")
    , @NamedQuery(name = "Pc2048.findByRagioneSociale", query = "SELECT p FROM Pc2048 p WHERE p.ragioneSociale = :ragioneSociale")})
public class Pc2048 implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Pc2048PK pc2048PK;
    @Size(max = 200)
    @Column(name = "cee_codice_cognome")
    private String ceeCodiceCognome;
    @Size(max = 20)
    @Column(name = "cee_codice_nome")
    private String ceeCodiceNome;
    @Column(name = "part_iva")
    private Long partIva;
    @Column(name = "comune")
    private Integer comune;
    @Size(max = 16)
    @Column(name = "fisc_cod")
    private String fiscCod;
    @Column(name = "\"annoObbligatorio\"")
    private Character annoObbligatorio;
    @Column(name = "associa_codice")
    private Integer associaCodice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "sigla_nazione")
    private String siglaNazione;
    @Basic(optional = false)
    @NotNull
    @Column(name = "privato")
    private Character privato;
    @Size(max = 200)
    @Column(name = "ragione_sociale")
    private String ragioneSociale;

    public Pc2048() {
    }

    public Pc2048(Pc2048PK pc2048PK) {
        this.pc2048PK = pc2048PK;
    }

    public Pc2048(Pc2048PK pc2048PK, String siglaNazione, Character privato) {
        this.pc2048PK = pc2048PK;
        this.siglaNazione = siglaNazione;
        this.privato = privato;
    }

    public Pc2048(int cgTipoMasCod, int ceeMasCod, int ceeMas2Cod, int ceeMas3Cod, int ceeMas4Cod, String ceeContoCod, String ceeCodiceCod) {
        this.pc2048PK = new Pc2048PK(cgTipoMasCod, ceeMasCod, ceeMas2Cod, ceeMas3Cod, ceeMas4Cod, ceeContoCod, ceeCodiceCod);
    }

    public Pc2048PK getPc2048PK() {
        return pc2048PK;
    }

    public void setPc2048PK(Pc2048PK pc2048PK) {
        this.pc2048PK = pc2048PK;
    }

    public String getCeeCodiceCognome() {
        return ceeCodiceCognome;
    }

    public void setCeeCodiceCognome(String ceeCodiceCognome) {
        this.ceeCodiceCognome = ceeCodiceCognome;
    }

    public String getCeeCodiceNome() {
        return ceeCodiceNome;
    }

    public void setCeeCodiceNome(String ceeCodiceNome) {
        this.ceeCodiceNome = ceeCodiceNome;
    }

    public Long getPartIva() {
        return partIva;
    }

    public void setPartIva(Long partIva) {
        this.partIva = partIva;
    }

    public Integer getComune() {
        return comune;
    }

    public void setComune(Integer comune) {
        this.comune = comune;
    }

    public String getFiscCod() {
        return fiscCod;
    }

    public void setFiscCod(String fiscCod) {
        this.fiscCod = fiscCod;
    }

    public Character getAnnoObbligatorio() {
        return annoObbligatorio;
    }

    public void setAnnoObbligatorio(Character annoObbligatorio) {
        this.annoObbligatorio = annoObbligatorio;
    }

    public Integer getAssociaCodice() {
        return associaCodice;
    }

    public void setAssociaCodice(Integer associaCodice) {
        this.associaCodice = associaCodice;
    }

    public String getSiglaNazione() {
        return siglaNazione;
    }

    public void setSiglaNazione(String siglaNazione) {
        this.siglaNazione = siglaNazione;
    }

    public Character getPrivato() {
        return privato;
    }

    public void setPrivato(Character privato) {
        this.privato = privato;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pc2048PK != null ? pc2048PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pc2048)) {
            return false;
        }
        Pc2048 other = (Pc2048) object;
        if ((this.pc2048PK == null && other.pc2048PK != null) || (this.pc2048PK != null && !this.pc2048PK.equals(other.pc2048PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.ditta.Pc2048[ pc2048PK=" + pc2048PK + " ]";
    }
    
}
