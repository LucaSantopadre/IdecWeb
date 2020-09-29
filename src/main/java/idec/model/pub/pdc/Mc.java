/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.pub.pdc;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luca
 */
@Entity
@Table(name = "mc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mc.findAll", query = "SELECT m FROM Mc m")
    , @NamedQuery(name = "Mc.findByMcId", query = "SELECT m FROM Mc m WHERE m.mcId = :mcId")
    , @NamedQuery(name = "Mc.findByCeeContoCod", query = "SELECT m FROM Mc m WHERE m.ceeContoCod = :ceeContoCod")
    , @NamedQuery(name = "Mc.findByCeeContoCodDes", query = "SELECT m FROM Mc m WHERE m.ceeContoCodDes = :ceeContoCodDes")
    , @NamedQuery(name = "Mc.findByCgTipoMasCod", query = "SELECT m FROM Mc m WHERE m.cgTipoMasCod = :cgTipoMasCod")
    , @NamedQuery(name = "Mc.findByCeeMasCod", query = "SELECT m FROM Mc m WHERE m.ceeMasCod = :ceeMasCod")
    , @NamedQuery(name = "Mc.findByCeeMas2Cod", query = "SELECT m FROM Mc m WHERE m.ceeMas2Cod = :ceeMas2Cod")
    , @NamedQuery(name = "Mc.findByCeeMas3Cod", query = "SELECT m FROM Mc m WHERE m.ceeMas3Cod = :ceeMas3Cod")
    , @NamedQuery(name = "Mc.findByCeeMas4Cod", query = "SELECT m FROM Mc m WHERE m.ceeMas4Cod = :ceeMas4Cod")
    , @NamedQuery(name = "Mc.findByCeePadre", query = "SELECT m FROM Mc m WHERE m.ceePadre = :ceePadre")
    , @NamedQuery(name = "Mc.findByCodObbl", query = "SELECT m FROM Mc m WHERE m.codObbl = :codObbl")
    , @NamedQuery(name = "Mc.findBySegno", query = "SELECT m FROM Mc m WHERE m.segno = :segno")
    , @NamedQuery(name = "Mc.findBySezioneF24", query = "SELECT m FROM Mc m WHERE m.sezioneF24 = :sezioneF24")
    , @NamedQuery(name = "Mc.findByAnnoObbligatorio", query = "SELECT m FROM Mc m WHERE m.annoObbligatorio = :annoObbligatorio")
    , @NamedQuery(name = "Mc.findByScadenzario", query = "SELECT m FROM Mc m WHERE m.scadenzario = :scadenzario")
    , @NamedQuery(name = "Mc.findByFix", query = "SELECT m FROM Mc m WHERE m.fix = :fix")
    , @NamedQuery(name = "Mc.findByCodAtt", query = "SELECT m FROM Mc m WHERE m.codAtt = :codAtt")
    , @NamedQuery(name = "Mc.findByDetrazioni", query = "SELECT m FROM Mc m WHERE m.detrazioni = :detrazioni")
    , @NamedQuery(name = "Mc.findByAmmortamento", query = "SELECT m FROM Mc m WHERE m.ammortamento = :ammortamento")
    , @NamedQuery(name = "Mc.findByDescrizioneAggiuntiva", query = "SELECT m FROM Mc m WHERE m.descrizioneAggiuntiva = :descrizioneAggiuntiva")
    , @NamedQuery(name = "Mc.findByCespite", query = "SELECT m FROM Mc m WHERE m.cespite = :cespite")
    , @NamedQuery(name = "Mc.findByVariazioneFiscale", query = "SELECT m FROM Mc m WHERE m.variazioneFiscale = :variazioneFiscale")
    , @NamedQuery(name = "Mc.findByPrivato", query = "SELECT m FROM Mc m WHERE m.privato = :privato")
    , @NamedQuery(name = "Mc.findByDallaDataAdmin", query = "SELECT m FROM Mc m WHERE m.dallaDataAdmin = :dallaDataAdmin")
    , @NamedQuery(name = "Mc.findByAllaDataAdmin", query = "SELECT m FROM Mc m WHERE m.allaDataAdmin = :allaDataAdmin")
    , @NamedQuery(name = "Mc.findByAdmin", query = "SELECT m FROM Mc m WHERE m.admin = :admin")})
public class Mc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "mc_id")
    private Integer mcId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "\"CEE_CONTO_COD\"")
    private String ceeContoCod;
    @Size(max = 200)
    @Column(name = "cee_conto_cod_des")
    private String ceeContoCodDes;
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
    @Column(name = "\"CEE_PADRE\"")
    private Character ceePadre;
    @Column(name = "cod_obbl")
    private Character codObbl;
    @Column(name = "segno")
    private Character segno;
    @Column(name = "\"sezioneF24\"")
    private Integer sezioneF24;
    @Column(name = "\"annoObbligatorio\"")
    private Character annoObbligatorio;
    @Column(name = "scadenzario")
    private Character scadenzario;
    @Column(name = "fix")
    private Character fix;
    @Column(name = "cod_att")
    private Character codAtt;
    @Column(name = "detrazioni")
    private Character detrazioni;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ammortamento")
    private Character ammortamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descrizione_aggiuntiva")
    private String descrizioneAggiuntiva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cespite")
    private Character cespite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "variazione_fiscale")
    private Character variazioneFiscale;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "privato")
    private String privato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dalla_data_admin")
    @Temporal(TemporalType.DATE)
    private Date dallaDataAdmin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "alla_data_admin")
    @Temporal(TemporalType.DATE)
    private Date allaDataAdmin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "admin")
    private String admin;
    @JoinColumn(name = "\"m2_ID\"", referencedColumnName = "m2_id")
    @ManyToOne
    private M2 m2id;
    @JoinColumn(name = "\"m3_ID\"", referencedColumnName = "m3_id")
    @ManyToOne
    private M3 m3id;
    @JoinColumn(name = "\"m4_ID\"", referencedColumnName = "m4_id")
    @ManyToOne
    private M4 m4id;
    @JoinColumn(name = "\"m5_ID\"", referencedColumnName = "m5_id")
    @ManyToOne
    private M5 m5id;

    public Mc() {
    }

    public Mc(Integer mcId) {
        this.mcId = mcId;
    }

    public Mc(Integer mcId, String ceeContoCod, int cgTipoMasCod, int ceeMasCod, int ceeMas2Cod, int ceeMas3Cod, int ceeMas4Cod, Character ammortamento, String descrizioneAggiuntiva, Character cespite, Character variazioneFiscale, String privato, Date dallaDataAdmin, Date allaDataAdmin, String admin) {
        this.mcId = mcId;
        this.ceeContoCod = ceeContoCod;
        this.cgTipoMasCod = cgTipoMasCod;
        this.ceeMasCod = ceeMasCod;
        this.ceeMas2Cod = ceeMas2Cod;
        this.ceeMas3Cod = ceeMas3Cod;
        this.ceeMas4Cod = ceeMas4Cod;
        this.ammortamento = ammortamento;
        this.descrizioneAggiuntiva = descrizioneAggiuntiva;
        this.cespite = cespite;
        this.variazioneFiscale = variazioneFiscale;
        this.privato = privato;
        this.dallaDataAdmin = dallaDataAdmin;
        this.allaDataAdmin = allaDataAdmin;
        this.admin = admin;
    }

    public Integer getMcId() {
        return mcId;
    }

    public void setMcId(Integer mcId) {
        this.mcId = mcId;
    }

    public String getCeeContoCod() {
        return ceeContoCod;
    }

    public void setCeeContoCod(String ceeContoCod) {
        this.ceeContoCod = ceeContoCod;
    }

    public String getCeeContoCodDes() {
        return ceeContoCodDes;
    }

    public void setCeeContoCodDes(String ceeContoCodDes) {
        this.ceeContoCodDes = ceeContoCodDes;
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

    public Character getCeePadre() {
        return ceePadre;
    }

    public void setCeePadre(Character ceePadre) {
        this.ceePadre = ceePadre;
    }

    public Character getCodObbl() {
        return codObbl;
    }

    public void setCodObbl(Character codObbl) {
        this.codObbl = codObbl;
    }

    public Character getSegno() {
        return segno;
    }

    public void setSegno(Character segno) {
        this.segno = segno;
    }

    public Integer getSezioneF24() {
        return sezioneF24;
    }

    public void setSezioneF24(Integer sezioneF24) {
        this.sezioneF24 = sezioneF24;
    }

    public Character getAnnoObbligatorio() {
        return annoObbligatorio;
    }

    public void setAnnoObbligatorio(Character annoObbligatorio) {
        this.annoObbligatorio = annoObbligatorio;
    }

    public Character getScadenzario() {
        return scadenzario;
    }

    public void setScadenzario(Character scadenzario) {
        this.scadenzario = scadenzario;
    }

    public Character getFix() {
        return fix;
    }

    public void setFix(Character fix) {
        this.fix = fix;
    }

    public Character getCodAtt() {
        return codAtt;
    }

    public void setCodAtt(Character codAtt) {
        this.codAtt = codAtt;
    }

    public Character getDetrazioni() {
        return detrazioni;
    }

    public void setDetrazioni(Character detrazioni) {
        this.detrazioni = detrazioni;
    }

    public Character getAmmortamento() {
        return ammortamento;
    }

    public void setAmmortamento(Character ammortamento) {
        this.ammortamento = ammortamento;
    }

    public String getDescrizioneAggiuntiva() {
        return descrizioneAggiuntiva;
    }

    public void setDescrizioneAggiuntiva(String descrizioneAggiuntiva) {
        this.descrizioneAggiuntiva = descrizioneAggiuntiva;
    }

    public Character getCespite() {
        return cespite;
    }

    public void setCespite(Character cespite) {
        this.cespite = cespite;
    }

    public Character getVariazioneFiscale() {
        return variazioneFiscale;
    }

    public void setVariazioneFiscale(Character variazioneFiscale) {
        this.variazioneFiscale = variazioneFiscale;
    }

    public String getPrivato() {
        return privato;
    }

    public void setPrivato(String privato) {
        this.privato = privato;
    }

    public Date getDallaDataAdmin() {
        return dallaDataAdmin;
    }

    public void setDallaDataAdmin(Date dallaDataAdmin) {
        this.dallaDataAdmin = dallaDataAdmin;
    }

    public Date getAllaDataAdmin() {
        return allaDataAdmin;
    }

    public void setAllaDataAdmin(Date allaDataAdmin) {
        this.allaDataAdmin = allaDataAdmin;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public M2 getM2id() {
        return m2id;
    }

    public void setM2id(M2 m2id) {
        this.m2id = m2id;
    }

    public M3 getM3id() {
        return m3id;
    }

    public void setM3id(M3 m3id) {
        this.m3id = m3id;
    }

    public M4 getM4id() {
        return m4id;
    }

    public void setM4id(M4 m4id) {
        this.m4id = m4id;
    }

    public M5 getM5id() {
        return m5id;
    }

    public void setM5id(M5 m5id) {
        this.m5id = m5id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mcId != null ? mcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mc)) {
            return false;
        }
        Mc other = (Mc) object;
        if ((this.mcId == null && other.mcId != null) || (this.mcId != null && !this.mcId.equals(other.mcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.pdc.Mc[ mcId=" + mcId + " ]";
    }
    
}
