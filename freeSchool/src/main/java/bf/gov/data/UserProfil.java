/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author peter
 */
@Entity
@Table(name = "user_profil")
@NamedQueries({
    @NamedQuery(name = "UserProfil.findAll", query = "SELECT u FROM UserProfil u")})
public class UserProfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserProfilPK userProfilPK;
    @Column(name = "date_attribution")
    @Temporal(TemporalType.DATE)
    private Date dateAttribution;
    @JoinColumn(name = "code_profil", referencedColumnName = "code_profil", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profil profil;
    @JoinColumn(name = "code_user", referencedColumnName = "code_user", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Utilisateur utilisateur;

    public UserProfil() {
    }

    public UserProfil(UserProfilPK userProfilPK) {
        this.userProfilPK = userProfilPK;
    }

    public UserProfil(int codeUser, int codeProfil) {
        this.userProfilPK = new UserProfilPK(codeUser, codeProfil);
    }

    public UserProfilPK getUserProfilPK() {
        return userProfilPK;
    }

    public void setUserProfilPK(UserProfilPK userProfilPK) {
        this.userProfilPK = userProfilPK;
    }

    public Date getDateAttribution() {
        return dateAttribution;
    }

    public void setDateAttribution(Date dateAttribution) {
        this.dateAttribution = dateAttribution;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userProfilPK != null ? userProfilPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserProfil)) {
            return false;
        }
        UserProfil other = (UserProfil) object;
        if ((this.userProfilPK == null && other.userProfilPK != null) || (this.userProfilPK != null && !this.userProfilPK.equals(other.userProfilPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.UserProfil[ userProfilPK=" + userProfilPK + " ]";
    }
    
}
