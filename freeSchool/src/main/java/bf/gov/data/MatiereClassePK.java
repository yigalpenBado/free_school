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
import javax.validation.constraints.Size;

/**
 *
 * @author peter
 */
@Embeddable
public class MatiereClassePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "code_matiere")
    private String codeMatiere;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "code_classe")
    private String codeClasse;

    public MatiereClassePK() {
    }

    public MatiereClassePK(String codeMatiere, String codeClasse) {
        this.codeMatiere = codeMatiere;
        this.codeClasse = codeClasse;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public String getCodeClasse() {
        return codeClasse;
    }

    public void setCodeClasse(String codeClasse) {
        this.codeClasse = codeClasse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeMatiere != null ? codeMatiere.hashCode() : 0);
        hash += (codeClasse != null ? codeClasse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatiereClassePK)) {
            return false;
        }
        MatiereClassePK other = (MatiereClassePK) object;
        if ((this.codeMatiere == null && other.codeMatiere != null) || (this.codeMatiere != null && !this.codeMatiere.equals(other.codeMatiere))) {
            return false;
        }
        if ((this.codeClasse == null && other.codeClasse != null) || (this.codeClasse != null && !this.codeClasse.equals(other.codeClasse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.MatiereClassePK[ codeMatiere=" + codeMatiere + ", codeClasse=" + codeClasse + " ]";
    }
    
}
