/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author peter
 */
@Entity
@Table(name = "profil")
@NamedQueries({
    @NamedQuery(name = "Profil.findAll", query = "SELECT p FROM Profil p")})
public class Profil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_profil")
    private Integer codeProfil;
    @Size(max = 254)
    @Column(name = "libelle_profil")
    private String libelleProfil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profil")
    private List<UserProfil> userProfilList;

    public Profil() {
    }

    public Profil(Integer codeProfil) {
        this.codeProfil = codeProfil;
    }

    public Integer getCodeProfil() {
        return codeProfil;
    }

    public void setCodeProfil(Integer codeProfil) {
        this.codeProfil = codeProfil;
    }

    public String getLibelleProfil() {
        return libelleProfil;
    }

    public void setLibelleProfil(String libelleProfil) {
        this.libelleProfil = libelleProfil;
    }

    public List<UserProfil> getUserProfilList() {
        return userProfilList;
    }

    public void setUserProfilList(List<UserProfil> userProfilList) {
        this.userProfilList = userProfilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeProfil != null ? codeProfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profil)) {
            return false;
        }
        Profil other = (Profil) object;
        if ((this.codeProfil == null && other.codeProfil != null) || (this.codeProfil != null && !this.codeProfil.equals(other.codeProfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.Profil[ codeProfil=" + codeProfil + " ]";
    }
    
}
