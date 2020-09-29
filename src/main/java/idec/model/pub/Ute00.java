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
 * @author Luca *** classe entità ute00 del db public ( tabella utenti)
 *
 */
@Entity
@Table(name = "ute00")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ute00.findAll", query = "SELECT u FROM Ute00 u")
    , @NamedQuery(name = "Ute00.findById", query = "SELECT u FROM Ute00 u WHERE u.id = :id")
    , @NamedQuery(name = "Ute00.findByEmail", query = "SELECT u FROM Ute00 u WHERE u.email = :email")
    , @NamedQuery(name = "Ute00.findByPassword", query = "SELECT u FROM Ute00 u WHERE u.password = :password")})
public class Ute00 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email", unique = true)
    private String email;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    // *luca 24/10/2017
    @Size(max = 1000)
    @Column(name = "ditte_visibili")
    private String ditteVisibili;

    public Ute00() {
    }

    public Ute00(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDitteVisibili() {
        return ditteVisibili;
    }

    public void setDitteVisibili(String ditteVisibili) {
        this.ditteVisibili = ditteVisibili;
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
        if (!(object instanceof Ute00)) {
            return false;
        }
        Ute00 other = (Ute00) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "idec.model.pub.Ute00[ id=" + id + " ]";
    }

}
