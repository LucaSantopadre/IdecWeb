/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.pub.pdc;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "m3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "M3.findAll", query = "SELECT m FROM M3 m")
    , @NamedQuery(name = "M3.findByM3Id", query = "SELECT m FROM M3 m WHERE m.m3Id = :m3Id")
    , @NamedQuery(name = "M3.findByCgTipoMasCod", query = "SELECT m FROM M3 m WHERE m.cgTipoMasCod = :cgTipoMasCod")
    , @NamedQuery(name = "M3.findByCeeMasCod", query = "SELECT m FROM M3 m WHERE m.ceeMasCod = :ceeMasCod")
    , @NamedQuery(name = "M3.findByCeeMas2Cod", query = "SELECT m FROM M3 m WHERE m.ceeMas2Cod = :ceeMas2Cod")
    , @NamedQuery(name = "M3.findByCeeMas2CodDes", query = "SELECT m FROM M3 m WHERE m.ceeMas2CodDes = :ceeMas2CodDes")
    , @NamedQuery(name = "M3.findByCee", query = "SELECT m FROM M3 m WHERE m.cee = :cee")
    , @NamedQuery(name = "M3.findByPrivato", query = "SELECT m FROM M3 m WHERE m.privato = :privato")
    , @NamedQuery(name = "M3.findByChkEntroAnno", query = "SELECT m FROM M3 m WHERE m.chkEntroAnno = :chkEntroAnno")
    , @NamedQuery(name = "M3.findByChkOltreAnno", query = "SELECT m FROM M3 m WHERE m.chkOltreAnno = :chkOltreAnno")
    , @NamedQuery(name = "M3.findByChkEntro5Anni", query = "SELECT m FROM M3 m WHERE m.chkEntro5Anni = :chkEntro5Anni")
    , @NamedQuery(name = "M3.findByChkOltre5Anni", query = "SELECT m FROM M3 m WHERE m.chkOltre5Anni = :chkOltre5Anni")
    , @NamedQuery(name = "M3.findByDallaDataAdmin", query = "SELECT m FROM M3 m WHERE m.dallaDataAdmin = :dallaDataAdmin")
    , @NamedQuery(name = "M3.findByAllaDataAdmin", query = "SELECT m FROM M3 m WHERE m.allaDataAdmin = :allaDataAdmin")
    , @NamedQuery(name = "M3.findByAdmin", query = "SELECT m FROM M3 m WHERE m.admin = :admin")})
public class M3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "m3_id")
    private Integer m3Id;
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
    @Size(max = 100)
    @Column(name = "cee_mas2_cod_des")
    private String ceeMas2CodDes;
    @Size(max = 10)
    @Column(name = "cee")
    private String cee;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "privato")
    private String privato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"chkEntroAnno\"")
    private Character chkEntroAnno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"chkOltreAnno\"")
    private Character chkOltreAnno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"chkEntro5Anni\"")
    private Character chkEntro5Anni;
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"chkOltre5Anni\"")
    private Character chkOltre5Anni;
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
    @OneToMany(mappedBy = "m3id")
    private List<Mc> mcList;
    @OneToMany(mappedBy = "m3id")
    private List<M4> m4List;

    public M3() {
    }

    public M3(Integer m3Id) {
        this.m3Id = m3Id;
    }

    public M3(Integer m3Id, int cgTipoMasCod, int ceeMasCod, int ceeMas2Cod, String privato, Character chkEntroAnno, Character chkOltreAnno, Character chkEntro5Anni, Character chkOltre5Anni, Date dallaDataAdmin, Date allaDataAdmin, String admin) {
        this.m3Id = m3Id;
        this.cgTipoMasCod = cgTipoMasCod;
        this.ceeMasCod = ceeMasCod;
        this.ceeMas2Cod = ceeMas2Cod;
        this.privato = privato;
        this.chkEntroAnno = chkEntroAnno;
        this.chkOltreAnno = chkOltreAnno;
        this.chkEntro5Anni = chkEntro5Anni;
        this.chkOltre5Anni = chkOltre5Anni;
        this.dallaDataAdmin = dallaDataAdmin;
        this.allaDataAdmin = allaDataAdmin;
        this.admin = admin;
    }

    public Integer getM3Id() {
        return m3Id;
    }

    public void setM3Id(Integer m3Id) {
        this.m3Id = m3Id;
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

    public String getCeeMas2CodDes() {
        return ceeMas2CodDes;
    }

    public void setCeeMas2CodDes(String ceeMas2CodDes) {
        this.ceeMas2CodDes = ceeMas2CodDes;
    }

    public String getCee() {
        return cee;
    }

    public void setCee(String cee) {
        this.cee = cee;
    }

    public String getPrivato() {
        return privato;
    }

    public void setPrivato(String privato) {
        this.privato = privato;
    }

    public Character getChkEntroAnno() {
        return chkEntroAnno;
    }

    public void setChkEntroAnno(Character chkEntroAnno) {
        this.chkEntroAnno = chkEntroAnno;
    }

    public Character getChkOltreAnno() {
        return chkOltreAnno;
    }

    public void setChkOltreAnno(Character chkOltreAnno) {
        this.chkOltreAnno = chkOltreAnno;
    }

    public Character getChkEntro5Anni() {
        return chkEntro5Anni;
    }

    public void setChkEntro5Anni(Character chkEntro5Anni) {
        this.chkEntro5Anni = chkEntro5Anni;
    }

    public Character getChkOltre5Anni() {
        return chkOltre5Anni;
    }

    public void setChkOltre5Anni(Character chkOltre5Anni) {
        this.chkOltre5Anni = chkOltre5Anni;
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

    @XmlTransient
    public List<Mc> getMcList() {
        return mcList;
    }

    public void setMcList(List<Mc> mcList) {
        this.mcList = mcList;
    }

    @XmlTransient
    public List<M4> getM4List() {
        return m4List;
    }

    public void setM4List(List<M4> m4List) {
        this.m4List = m4List;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (m3Id != null ? m3Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof M3)) {
            return false;
        }
        M3 other = (M3) object;
        if ((this.m3Id == null && other.m3Id != null) || (this.m3Id != null && !this.m3Id.equals(other.m3Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.pdc.M3[ m3Id=" + m3Id + " ]";
    }
    
}
