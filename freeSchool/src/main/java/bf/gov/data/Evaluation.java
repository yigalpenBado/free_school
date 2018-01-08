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
@Table(name = "evaluation")
@NamedQueries({
    @NamedQuery(name = "Evaluation.findAll", query = "SELECT e FROM Evaluation e")})
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EvaluationPK evaluationPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "note")
    private Float note;
    @Column(name = "date_evaluation")
    @Temporal(TemporalType.DATE)
    private Date dateEvaluation;
    @JoinColumn(name = "matricule", referencedColumnName = "matricule", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Eleve eleve;
    @JoinColumn(name = "code_matiere", referencedColumnName = "code_matiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matiere matiere;
    @JoinColumn(name = "num_session", referencedColumnName = "num_session", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SessionAnnee sessionAnnee;

    public Evaluation() {
    }

    public Evaluation(EvaluationPK evaluationPK) {
        this.evaluationPK = evaluationPK;
    }

    public Evaluation(String codeMatiere, String matricule, int numSession) {
        this.evaluationPK = new EvaluationPK(codeMatiere, matricule, numSession);
    }

    public EvaluationPK getEvaluationPK() {
        return evaluationPK;
    }

    public void setEvaluationPK(EvaluationPK evaluationPK) {
        this.evaluationPK = evaluationPK;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public Date getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(Date dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public SessionAnnee getSessionAnnee() {
        return sessionAnnee;
    }

    public void setSessionAnnee(SessionAnnee sessionAnnee) {
        this.sessionAnnee = sessionAnnee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationPK != null ? evaluationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluation)) {
            return false;
        }
        Evaluation other = (Evaluation) object;
        if ((this.evaluationPK == null && other.evaluationPK != null) || (this.evaluationPK != null && !this.evaluationPK.equals(other.evaluationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.Evaluation[ evaluationPK=" + evaluationPK + " ]";
    }
    
}
