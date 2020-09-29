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
@Table(name = "m5")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "M5.findAll", query = "SELECT m FROM M5 m")
    , @NamedQuery(name = "M5.findByM5Id", query = "SELECT m FROM M5 m WHERE m.m5Id = :m5Id")
    , @NamedQuery(name = "M5.findByCgTipoMasCod", query = "SELECT m FROM M5 m WHERE m.cgTipoMasCod = :cgTipoMasCod")
    , @NamedQuery(name = "M5.findByCeeMasCod", query = "SELECT m FROM M5 m WHERE m.ceeMasCod = :ceeMasCod")
    , @NamedQuery(name = "M5.findByCeeMas2Cod", query = "SELECT m FROM M5 m WHERE m.ceeMas2Cod = :ceeMas2Cod")
    , @NamedQuery(name = "M5.findByCeeMas3Cod", query = "SELECT m FROM M5 m WHERE m.ceeMas3Cod = :ceeMas3Cod")
    , @NamedQuery(name = "M5.findByCeeMas4Cod", query = "SELECT m FROM M5 m WHERE m.ceeMas4Cod = :ceeMas4Cod")
    , @NamedQuery(name = "M5.findByCeeMas4CodDes", query = "SELECT m FROM M5 m WHERE m.ceeMas4CodDes = :ceeMas4CodDes")
    , @NamedQuery(name = "M5.findByCee", query = "SELECT m FROM M5 m WHERE m.cee = :cee")
    , @NamedQuery(name = "M5.findByPrivato", query = "SELECT m FROM M5 m WHERE m.privato = :privato")
    , @NamedQuery(name = "M5.findByChkEntroAnno", query = "SELECT m FROM M5 m WHERE m.chkEntroAnno = :chkEntroAnno")
    , @NamedQuery(name = "M5.findByChkOltreAnno", query = "SELECT m FROM M5 m WHERE m.chkOltreAnno = :chkOltreAnno")
    , @NamedQuery(name = "M5.findByChkEntro5Anni", query = "SELECT m FROM M5 m WHERE m.chkEntro5Anni = :chkEntro5Anni")
    , @NamedQuery(name = "M5.findByChkOltre5Anni", query = "SELECT m FROM M5 m WHERE m.chkOltre5Anni = :chkOltre5Anni")
    , @NamedQuery(name = "M5.findByDallaDataAdmin", query = "SELECT m FROM M5 m WHERE m.dallaDataAdmin = :dallaDataAdmin")
    , @NamedQuery(name = "M5.findByAllaDataAdmin", query = "SELECT m FROM M5 m WHERE m.allaDataAdmin = :allaDataAdmin")
    , @NamedQuery(name = "M5.findByAdmin", query = "SELECT m FROM M5 m WHERE m.admin = :admin")})
public class M5 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "m5_id")
    private Integer m5Id;
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
    @Size(max = 100)
    @Column(name = "cee_mas4_cod_des")
    private String ceeMas4CodDes;
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
    @OneToMany(mappedBy = "m5id")
    private List<Mc> mcList;
    @JoinColumn(name = "\"m4_ID\"", referencedColumnName = "m4_id")
    @ManyToOne
    private M4 m4id;

    public M5() {
    }

    public M5(Integer m5Id) {
        this.m5Id = m5Id;
    }

    public M5(Integer m5Id, int cgTipoMasCod, int ceeMasCod, int ceeMas2Cod, int ceeMas3Cod, int ceeMas4Cod, String privato, Character chkEntroAnno, Character chkOltreAnno, Character chkEntro5Anni, Character chkOltre5Anni, Date dallaDataAdmin, Date allaDataAdmin, String admin) {
        this.m5Id = m5Id;
        this.cgTipoMasCod = cgTipoMasCod;
        this.ceeMasCod = ceeMasCod;
        this.ceeMas2Cod = ceeMas2Cod;
        this.ceeMas3Cod = ceeMas3Cod;
        this.ceeMas4Cod = ceeMas4Cod;
        this.privato = privato;
        this.chkEntroAnno = chkEntroAnno;
        this.chkOltreAnno = chkOltreAnno;
        this.chkEntro5Anni = chkEntro5Anni;
        this.chkOltre5Anni = chkOltre5Anni;
        this.dallaDataAdmin = dallaDataAdmin;
        this.allaDataAdmin = allaDataAdmin;
        this.admin = admin;
    }

    public Integer getM5Id() {
        return m5Id;
    }

    public void setM5Id(Integer m5Id) {
        this.m5Id = m5Id;
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

    public String getCeeMas4CodDes() {
        return ceeMas4CodDes;
    }

    public void setCeeMas4CodDes(String ceeMas4CodDes) {
        this.ceeMas4CodDes = ceeMas4CodDes;
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

    public M4 getM4id() {
        return m4id;
    }

    public void setM4id(M4 m4id) {
        this.m4id = m4id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (m5Id != null ? m5Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof M5)) {
            return false;
        }
        M5 other = (M5) object;
        if ((this.m5Id == null && other.m5Id != null) || (this.m5Id != null && !this.m5Id.equals(other.m5Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.pdc.M5[ m5Id=" + m5Id + " ]";
    }
    
}
