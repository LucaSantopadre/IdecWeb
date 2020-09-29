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
@Table(name = "m4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "M4.findAll", query = "SELECT m FROM M4 m")
    , @NamedQuery(name = "M4.findByM4Id", query = "SELECT m FROM M4 m WHERE m.m4Id = :m4Id")
    , @NamedQuery(name = "M4.findByCgTipoMasCod", query = "SELECT m FROM M4 m WHERE m.cgTipoMasCod = :cgTipoMasCod")
    , @NamedQuery(name = "M4.findByCeeMasCod", query = "SELECT m FROM M4 m WHERE m.ceeMasCod = :ceeMasCod")
    , @NamedQuery(name = "M4.findByCeeMas2Cod", query = "SELECT m FROM M4 m WHERE m.ceeMas2Cod = :ceeMas2Cod")
    , @NamedQuery(name = "M4.findByCeeMas3Cod", query = "SELECT m FROM M4 m WHERE m.ceeMas3Cod = :ceeMas3Cod")
    , @NamedQuery(name = "M4.findByCeeMas3CodDes", query = "SELECT m FROM M4 m WHERE m.ceeMas3CodDes = :ceeMas3CodDes")
    , @NamedQuery(name = "M4.findByCee", query = "SELECT m FROM M4 m WHERE m.cee = :cee")
    , @NamedQuery(name = "M4.findByPrivato", query = "SELECT m FROM M4 m WHERE m.privato = :privato")
    , @NamedQuery(name = "M4.findByChkEntroAnno", query = "SELECT m FROM M4 m WHERE m.chkEntroAnno = :chkEntroAnno")
    , @NamedQuery(name = "M4.findByChkOltreAnno", query = "SELECT m FROM M4 m WHERE m.chkOltreAnno = :chkOltreAnno")
    , @NamedQuery(name = "M4.findByChkEntro5Anni", query = "SELECT m FROM M4 m WHERE m.chkEntro5Anni = :chkEntro5Anni")
    , @NamedQuery(name = "M4.findByChkOltre5Anni", query = "SELECT m FROM M4 m WHERE m.chkOltre5Anni = :chkOltre5Anni")
    , @NamedQuery(name = "M4.findByDallaDataAdmin", query = "SELECT m FROM M4 m WHERE m.dallaDataAdmin = :dallaDataAdmin")
    , @NamedQuery(name = "M4.findByAllaDataAdmin", query = "SELECT m FROM M4 m WHERE m.allaDataAdmin = :allaDataAdmin")
    , @NamedQuery(name = "M4.findByAdmin", query = "SELECT m FROM M4 m WHERE m.admin = :admin")})
public class M4 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "m4_id")
    private Integer m4Id;
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
    @Size(max = 100)
    @Column(name = "cee_mas3_cod_des")
    private String ceeMas3CodDes;
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
    @OneToMany(mappedBy = "m4id")
    private List<Mc> mcList;
    @JoinColumn(name = "\"m3_ID\"", referencedColumnName = "m3_id")
    @ManyToOne
    private M3 m3id;
    @OneToMany(mappedBy = "m4id")
    private List<M5> m5List;

    public M4() {
    }

    public M4(Integer m4Id) {
        this.m4Id = m4Id;
    }

    public M4(Integer m4Id, int cgTipoMasCod, int ceeMasCod, int ceeMas2Cod, int ceeMas3Cod, String privato, Character chkEntroAnno, Character chkOltreAnno, Character chkEntro5Anni, Character chkOltre5Anni, Date dallaDataAdmin, Date allaDataAdmin, String admin) {
        this.m4Id = m4Id;
        this.cgTipoMasCod = cgTipoMasCod;
        this.ceeMasCod = ceeMasCod;
        this.ceeMas2Cod = ceeMas2Cod;
        this.ceeMas3Cod = ceeMas3Cod;
        this.privato = privato;
        this.chkEntroAnno = chkEntroAnno;
        this.chkOltreAnno = chkOltreAnno;
        this.chkEntro5Anni = chkEntro5Anni;
        this.chkOltre5Anni = chkOltre5Anni;
        this.dallaDataAdmin = dallaDataAdmin;
        this.allaDataAdmin = allaDataAdmin;
        this.admin = admin;
    }

    public Integer getM4Id() {
        return m4Id;
    }

    public void setM4Id(Integer m4Id) {
        this.m4Id = m4Id;
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

    public String getCeeMas3CodDes() {
        return ceeMas3CodDes;
    }

    public void setCeeMas3CodDes(String ceeMas3CodDes) {
        this.ceeMas3CodDes = ceeMas3CodDes;
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

    @XmlTransient
    public List<Mc> getMcList() {
        return mcList;
    }

    public void setMcList(List<Mc> mcList) {
        this.mcList = mcList;
    }

    public M3 getM3id() {
        return m3id;
    }

    public void setM3id(M3 m3id) {
        this.m3id = m3id;
    }

    @XmlTransient
    public List<M5> getM5List() {
        return m5List;
    }

    public void setM5List(List<M5> m5List) {
        this.m5List = m5List;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (m4Id != null ? m4Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof M4)) {
            return false;
        }
        M4 other = (M4) object;
        if ((this.m4Id == null && other.m4Id != null) || (this.m4Id != null && !this.m4Id.equals(other.m4Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.pdc.M4[ m4Id=" + m4Id + " ]";
    }
    
}
