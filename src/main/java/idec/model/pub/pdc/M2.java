/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.model.pub.pdc;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "m2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "M2.findAll", query = "SELECT m FROM M2 m")
    , @NamedQuery(name = "M2.findByM2Id", query = "SELECT m FROM M2 m WHERE m.m2Id = :m2Id")
    , @NamedQuery(name = "M2.findByCeeMasCodIdex", query = "SELECT m FROM M2 m WHERE m.ceeMasCodIdex = :ceeMasCodIdex")
    , @NamedQuery(name = "M2.findByCeeMasCodDes", query = "SELECT m FROM M2 m WHERE m.m2Descr = :m2Descr")
    , @NamedQuery(name = "M2.findByCee", query = "SELECT m FROM M2 m WHERE m.cee = :cee")
    , @NamedQuery(name = "M2.findByPrivato", query = "SELECT m FROM M2 m WHERE m.privato = :privato")
    , @NamedQuery(name = "M2.findByChkEntroAnno", query = "SELECT m FROM M2 m WHERE m.chkEntroAnno = :chkEntroAnno")
    , @NamedQuery(name = "M2.findByChkOltreAnno", query = "SELECT m FROM M2 m WHERE m.chkOltreAnno = :chkOltreAnno")
    , @NamedQuery(name = "M2.findByChkEntro5Anni", query = "SELECT m FROM M2 m WHERE m.chkEntro5Anni = :chkEntro5Anni")
    , @NamedQuery(name = "M2.findByChkOltre5Anni", query = "SELECT m FROM M2 m WHERE m.chkOltre5Anni = :chkOltre5Anni")
    , @NamedQuery(name = "M2.findByDallaDataAdmin", query = "SELECT m FROM M2 m WHERE m.dallaDataAdmin = :dallaDataAdmin")
    , @NamedQuery(name = "M2.findByAllaDataAdmin", query = "SELECT m FROM M2 m WHERE m.allaDataAdmin = :allaDataAdmin")
    , @NamedQuery(name = "M2.findByAdmin", query = "SELECT m FROM M2 m WHERE m.admin = :admin")})
public class M2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "m2_id")
    private Integer m2Id;
    @Column(name = "cee_mas_cod_idex")
    private int ceeMasCodIdex;
    @Size(max = 100)
    @Column(name = "m2_descr")
    private String m2Descr;
    @Size(max = 10)
    @Column(name = "cee")
    private String cee;
    @Size(min = 1, max = 30)
    @Column(name = "privato")
    private String privato;
    @Column(name = "\"chkEntroAnno\"")
    private Character chkEntroAnno;
    @Column(name = "\"chkOltreAnno\"")
    private Character chkOltreAnno;
    @Column(name = "\"chkEntro5Anni\"")
    private Character chkEntro5Anni;
    @NotNull
    @Column(name = "\"chkOltre5Anni\"")
    private Character chkOltre5Anni;
    @Column(name = "dalla_data_admin")
    @Temporal(TemporalType.DATE)
    private Date dallaDataAdmin;
    @Column(name = "alla_data_admin")
    @Temporal(TemporalType.DATE)
    private Date allaDataAdmin;
    @Size(min = 1, max = 10)
    @Column(name = "admin")
    private String admin;

    @Column(name = "m2_id_visual")
    private BigDecimal m2IdVisual;
    @Column(name = "m2_totale")
    private String m2Totale;

    @JoinColumn(name = "m1_id", referencedColumnName = "m1_id")
    @ManyToOne(optional = false)
    private M1 m1Id;
    @OneToMany(mappedBy = "m2id")
    private List<M3> m3List;
    @OneToMany(mappedBy = "m2id")
    private List<Mc> mcList;

    public M2() {
    }

    public M2(Integer m2Id) {
        this.m2Id = m2Id;
    }

    public M2(Integer m2Id, int ceeMasCodIdex, String privato, Character chkEntroAnno, Character chkOltreAnno, Character chkEntro5Anni, Character chkOltre5Anni, Date dallaDataAdmin, Date allaDataAdmin, String admin) {
        this.m2Id = m2Id;
        this.ceeMasCodIdex = ceeMasCodIdex;
        this.privato = privato;
        this.chkEntroAnno = chkEntroAnno;
        this.chkOltreAnno = chkOltreAnno;
        this.chkEntro5Anni = chkEntro5Anni;
        this.chkOltre5Anni = chkOltre5Anni;
        this.dallaDataAdmin = dallaDataAdmin;
        this.allaDataAdmin = allaDataAdmin;
        this.admin = admin;
    }

    public Integer getM2Id() {
        return m2Id;
    }

    public void setM2Id(Integer m2Id) {
        this.m2Id = m2Id;
    }

    public int getCeeMasCodIdex() {
        return ceeMasCodIdex;
    }

    public void setCeeMasCodIdex(int ceeMasCodIdex) {
        this.ceeMasCodIdex = ceeMasCodIdex;
    }

    public String getM2Descr() {
        return m2Descr;
    }

    public void setM2Descr(String m2Descr) {
        this.m2Descr = m2Descr;
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

    public BigDecimal getM2IdVisual() {
        return m2IdVisual;
    }

    public void setM2IdVisual(BigDecimal m2IdVisual) {
        this.m2IdVisual = m2IdVisual;
    }

    public String getM2Totale() {
        return m2Totale;
    }

    public void setM2Totale(String m2Totale) {
        this.m2Totale = m2Totale;
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

    public M1 getM1Id() {
        return m1Id;
    }

    public void setM1Id(M1 m1Id) {
        this.m1Id = m1Id;
    }

    @XmlTransient
    public List<M3> getM3List() {
        return m3List;
    }

    public void setM3List(List<M3> m3List) {
        this.m3List = m3List;
    }

    @XmlTransient
    public List<Mc> getMcList() {
        return mcList;
    }

    public void setMcList(List<Mc> mcList) {
        this.mcList = mcList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (m2Id != null ? m2Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof M2)) {
            return false;
        }
        M2 other = (M2) object;
        if ((this.m2Id == null && other.m2Id != null) || (this.m2Id != null && !this.m2Id.equals(other.m2Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idec.model.pub.pdc.M2[ m2Id=" + m2Id + " ]";
    }

}
