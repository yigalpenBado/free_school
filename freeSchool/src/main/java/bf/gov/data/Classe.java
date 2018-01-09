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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author peter
 */
@Entity
@Table(name = "classe")
public class Classe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)

    @Column(name = "code_classe")
    private String codeClasse;

    @Column(name = "libelle_classe")
    private String libelleClasse;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeClasse")
    private List<ProgrammationCours> programmationCoursList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classe")
    private List<MatiereClasse> matiereClasseList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classe")
    private List<Inscription> inscriptionList;

    public Classe() {
    }

    public Classe(String codeClasse) {
        this.codeClasse = codeClasse;
    }

    public String getCodeClasse() {
        return codeClasse;
    }

    public void setCodeClasse(String codeClasse) {
        this.codeClasse = codeClasse;
    }

    public String getLibelleClasse() {
        return libelleClasse;
    }

    public void setLibelleClasse(String libelleClasse) {
        this.libelleClasse = libelleClasse;
    }

    public List<ProgrammationCours> getProgrammationCoursList() {
        return programmationCoursList;
    }

    public void setProgrammationCoursList(List<ProgrammationCours> programmationCoursList) {
        this.programmationCoursList = programmationCoursList;
    }

    public List<MatiereClasse> getMatiereClasseList() {
        return matiereClasseList;
    }

    public void setMatiereClasseList(List<MatiereClasse> matiereClasseList) {
        this.matiereClasseList = matiereClasseList;
    }

    public List<Inscription> getInscriptionList() {
        return inscriptionList;
    }

    public void setInscriptionList(List<Inscription> inscriptionList) {
        this.inscriptionList = inscriptionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeClasse != null ? codeClasse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classe)) {
            return false;
        }
        Classe other = (Classe) object;
        if ((this.codeClasse == null && other.codeClasse != null) || (this.codeClasse != null && !this.codeClasse.equals(other.codeClasse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.Classe[ codeClasse=" + codeClasse + " ]";
    }
    
}