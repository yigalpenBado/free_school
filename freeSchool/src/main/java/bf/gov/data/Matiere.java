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
@Table(name = "matiere")
public class Matiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)

    @Column(name = "code_matiere")
    private String codeMatiere;

    @Column(name = "intitule_matiere")
    private String intituleMatiere;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeMatiere")
    private List<ProgrammationCours> programmationCoursList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matiere")
    private List<Evaluation> evaluationList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matiere")
    private List<MatiereClasse> matiereClasseList;

    public Matiere() {
    }

    public Matiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public String getIntituleMatiere() {
        return intituleMatiere;
    }

    public void setIntituleMatiere(String intituleMatiere) {
        this.intituleMatiere = intituleMatiere;
    }

    public List<ProgrammationCours> getProgrammationCoursList() {
        return programmationCoursList;
    }

    public void setProgrammationCoursList(List<ProgrammationCours> programmationCoursList) {
        this.programmationCoursList = programmationCoursList;
    }

    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    public List<MatiereClasse> getMatiereClasseList() {
        return matiereClasseList;
    }

    public void setMatiereClasseList(List<MatiereClasse> matiereClasseList) {
        this.matiereClasseList = matiereClasseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeMatiere != null ? codeMatiere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matiere)) {
            return false;
        }
        Matiere other = (Matiere) object;
        if ((this.codeMatiere == null && other.codeMatiere != null) || (this.codeMatiere != null && !this.codeMatiere.equals(other.codeMatiere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.Matiere[ codeMatiere=" + codeMatiere + " ]";
    }
    
}
