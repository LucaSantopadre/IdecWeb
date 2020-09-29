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
 * 
 */
@Entity
@Table(name = "dit00")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dit00.findAll", query = "SELECT d FROM Dit00 d")
    , @NamedQuery(name = "Dit00.findById", query = "SELECT d FROM Dit00 d WHERE d.id = :id")
    , @NamedQuery(name = "Dit00.findByIdPaeseIva", query = "SELECT d FROM Dit00 d WHERE d.idPaeseIva = :idPaeseIva")
    , @NamedQuery(name = "Dit00.findByIdCodiceIva", query = "SELECT d FROM Dit00 d WHERE d.idCodiceIva = :idCodiceIva")
    , @NamedQuery(name = "Dit00.findByCodiceFiscale", query = "SELECT d FROM Dit00 d WHERE d.codiceFiscale = :codiceFiscale")
    , @NamedQuery(name = "Dit00.findByDenominazione", query = "SELECT d FROM Dit00 d WHERE d.denominazione = :denominazione")
    , @NamedQuery(name = "Dit00.findByNome", query = "SELECT d FROM Dit00 d WHERE d.nome = :nome")
    , @NamedQuery(name = "Dit00.findByCognome", query = "SELECT d FROM Dit00 d WHERE d.cognome = :cognome")
    , @NamedQuery(name = "Dit00.findByCodiceDitta", query = "SELECT d FROM Dit00 d WHERE d.codiceDitta = :codiceDitta")})
public class Dit00 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2)
    @Column(name = "id_paese_iva")
    private String idPaeseIva;
    @Size(max = 16)
    @Column(name = "id_codice_iva")
    private String idCodiceIva;
    @Size(max = 16)
    @Column(name = "codice_fiscale")
    private String codiceFiscale;
    @Size(max = 255)
    @Column(name = "denominazione")
    private String denominazione;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;
    @Size(max = 255)
    @Column(name = "cognome")
    private String cognome;
    // *luca 24/10/2017
    @Size(max = 6)
    @Column(name = "codice_ditta")
    private String codiceDitta;

    public Dit00() {
    }

    public Dit00(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdPaeseIva() {
        return idPaeseIva;
    }

    public void setIdPaeseIva(String idPaeseIva) {
        this.idPaeseIva = idPaeseIva;
    }

    public String getIdCodiceIva() {
        return idCodiceIva;
    }

    public void setIdCodiceIva(String idCodiceIva) {
        this.idCodiceIva = idCodiceIva;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceDitta() {
        return codiceDitta;
    }

    public void setCodiceDitta(String codiceDitta) {
        this.codiceDitta = codiceDitta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dit00)) {
            return false;
        }
        Dit00 other = (Dit00) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.Dit00[ id=" + id + " ]";
    }
    
}
