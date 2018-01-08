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
public class InscriptionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "matricule")
    private String matricule;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "code_classe")
    private String codeClasse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_annee")
    private int numeroAnnee;

    public InscriptionPK() {
    }

    public InscriptionPK(String matricule, String codeClasse, int numeroAnnee) {
        this.matricule = matricule;
        this.codeClasse = codeClasse;
        this.numeroAnnee = numeroAnnee;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCodeClasse() {
        return codeClasse;
    }

    public void setCodeClasse(String codeClasse) {
        this.codeClasse = codeClasse;
    }

    public int getNumeroAnnee() {
        return numeroAnnee;
    }

    public void setNumeroAnnee(int numeroAnnee) {
        this.numeroAnnee = numeroAnnee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricule != null ? matricule.hashCode() : 0);
        hash += (codeClasse != null ? codeClasse.hashCode() : 0);
        hash += (int) numeroAnnee;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InscriptionPK)) {
            return false;
        }
        InscriptionPK other = (InscriptionPK) object;
        if ((this.matricule == null && other.matricule != null) || (this.matricule != null && !this.matricule.equals(other.matricule))) {
            return false;
        }
        if ((this.codeClasse == null && other.codeClasse != null) || (this.codeClasse != null && !this.codeClasse.equals(other.codeClasse))) {
            return false;
        }
        if (this.numeroAnnee != other.numeroAnnee) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.InscriptionPK[ matricule=" + matricule + ", codeClasse=" + codeClasse + ", numeroAnnee=" + numeroAnnee + " ]";
    }
    
}
