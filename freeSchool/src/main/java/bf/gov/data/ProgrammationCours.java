/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author peter
 */
@Entity
@Table(name = "programmation_cours")
@NamedQueries({
    @NamedQuery(name = "ProgrammationCours.findAll", query = "SELECT p FROM ProgrammationCours p")})
public class ProgrammationCours implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "num_program")
    private Integer numProgram;
    @Size(max = 15)
    @Column(name = "jour")
    private String jour;
    @Column(name = "heure_debut")
    @Temporal(TemporalType.TIME)
    private Date heureDebut;
    @Column(name = "heure_fin")
    @Temporal(TemporalType.TIME)
    private Date heureFin;
    @JoinColumn(name = "numero_annee", referencedColumnName = "numero_annee")
    @ManyToOne(optional = false)
    private AnneeScolaire numeroAnnee;
    @JoinColumn(name = "code_classe", referencedColumnName = "code_classe")
    @ManyToOne(optional = false)
    private Classe codeClasse;
    @JoinColumn(name = "matricule", referencedColumnName = "matricule")
    @ManyToOne
    private Enseignant matricule;
    @JoinColumn(name = "code_matiere", referencedColumnName = "code_matiere")
    @ManyToOne(optional = false)
    private Matiere codeMatiere;

    public ProgrammationCours() {
    }

    public ProgrammationCours(Integer numProgram) {
        this.numProgram = numProgram;
    }

    public Integer getNumProgram() {
        return numProgram;
    }

    public void setNumProgram(Integer numProgram) {
        this.numProgram = numProgram;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public AnneeScolaire getNumeroAnnee() {
        return numeroAnnee;
    }

    public void setNumeroAnnee(AnneeScolaire numeroAnnee) {
        this.numeroAnnee = numeroAnnee;
    }

    public Classe getCodeClasse() {
        return codeClasse;
    }

    public void setCodeClasse(Classe codeClasse) {
        this.codeClasse = codeClasse;
    }

    public Enseignant getMatricule() {
        return matricule;
    }

    public void setMatricule(Enseignant matricule) {
        this.matricule = matricule;
    }

    public Matiere getCodeMatiere() {
        return codeMatiere;
    }

    public void setCodeMatiere(Matiere codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numProgram != null ? numProgram.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgrammationCours)) {
            return false;
        }
        ProgrammationCours other = (ProgrammationCours) object;
        if ((this.numProgram == null && other.numProgram != null) || (this.numProgram != null && !this.numProgram.equals(other.numProgram))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.ProgrammationCours[ numProgram=" + numProgram + " ]";
    }
    
}
