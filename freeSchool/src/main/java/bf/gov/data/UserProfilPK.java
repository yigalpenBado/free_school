/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author peter
 */
@Embeddable
public class UserProfilPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "code_user")
    private int codeUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "code_profil")
    private int codeProfil;

    public UserProfilPK() {
    }

    public UserProfilPK(int codeUser, int codeProfil) {
        this.codeUser = codeUser;
        this.codeProfil = codeProfil;
    }

    public int getCodeUser() {
        return codeUser;
    }

    public void setCodeUser(int codeUser) {
        this.codeUser = codeUser;
    }

    public int getCodeProfil() {
        return codeProfil;
    }

    public void setCodeProfil(int codeProfil) {
        this.codeProfil = codeProfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codeUser;
        hash += (int) codeProfil;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserProfilPK)) {
            return false;
        }
        UserProfilPK other = (UserProfilPK) object;
        if (this.codeUser != other.codeUser) {
            return false;
        }
        if (this.codeProfil != other.codeProfil) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.UserProfilPK[ codeUser=" + codeUser + ", codeProfil=" + codeProfil + " ]";
    }
    
}
