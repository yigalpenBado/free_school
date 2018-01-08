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
public class EvaluationPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "code_matiere")
    private String codeMatiere;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "matricule")
    private String matricule;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_session")
    private int numSession;

    public EvaluationPK() {
    }

    public EvaluationPK(String codeMatiere, String matricule, int numSession) {
        this.codeMatiere = codeMatiere;
        this.matricule = matricule;
        this.numSession = numSession;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getNumSession() {
        return numSession;
    }

    public void setNumSession(int numSession) {
        this.numSession = numSession;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeMatiere != null ? codeMatiere.hashCode() : 0);
        hash += (matricule != null ? matricule.hashCode() : 0);
        hash += (int) numSession;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluationPK)) {
            return false;
        }
        EvaluationPK other = (EvaluationPK) object;
        if ((this.codeMatiere == null && other.codeMatiere != null) || (this.codeMatiere != null && !this.codeMatiere.equals(other.codeMatiere))) {
            return false;
        }
        if ((this.matricule == null && other.matricule != null) || (this.matricule != null && !this.matricule.equals(other.matricule))) {
            return false;
        }
        if (this.numSession != other.numSession) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.EvaluationPK[ codeMatiere=" + codeMatiere + ", matricule=" + matricule + ", numSession=" + numSession + " ]";
    }
    
}
