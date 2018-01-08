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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "session_annee")
@NamedQueries({
    @NamedQuery(name = "SessionAnnee.findAll", query = "SELECT s FROM SessionAnnee s")})
public class SessionAnnee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "num_session")
    private Integer numSession;
    @Size(max = 100)
    @Column(name = "libelle_session")
    private String libelleSession;
    @JoinColumn(name = "numero_annee", referencedColumnName = "numero_annee")
    @ManyToOne(optional = false)
    private AnneeScolaire numeroAnnee;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionAnnee")
    private List<Evaluation> evaluationList;

    public SessionAnnee() {
    }

    public SessionAnnee(Integer numSession) {
        this.numSession = numSession;
    }

    public Integer getNumSession() {
        return numSession;
    }

    public void setNumSession(Integer numSession) {
        this.numSession = numSession;
    }

    public String getLibelleSession() {
        return libelleSession;
    }

    public void setLibelleSession(String libelleSession) {
        this.libelleSession = libelleSession;
    }

    public AnneeScolaire getNumeroAnnee() {
        return numeroAnnee;
    }

    public void setNumeroAnnee(AnneeScolaire numeroAnnee) {
        this.numeroAnnee = numeroAnnee;
    }

    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numSession != null ? numSession.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SessionAnnee)) {
            return false;
        }
        SessionAnnee other = (SessionAnnee) object;
        if ((this.numSession == null && other.numSession != null) || (this.numSession != null && !this.numSession.equals(other.numSession))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.SessionAnnee[ numSession=" + numSession + " ]";
    }
    
}
