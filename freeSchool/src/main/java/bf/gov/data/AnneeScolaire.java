/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author peter
 */
@Entity
@Table(name = "annee_scolaire")
@NamedQueries({
    @NamedQuery(name = "AnneeScolaire.findAll", query = "SELECT a FROM AnneeScolaire a")})
public class AnneeScolaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_annee")
    private Integer numeroAnnee;
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroAnnee")
    private List<SessionAnnee> sessionAnneeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroAnnee")
    private List<ProgrammationCours> programmationCoursList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anneeScolaire")
    private List<Inscription> inscriptionList;

    public AnneeScolaire() {
    }

    public AnneeScolaire(Integer numeroAnnee) {
        this.numeroAnnee = numeroAnnee;
    }

    public Integer getNumeroAnnee() {
        return numeroAnnee;
    }

    public void setNumeroAnnee(Integer numeroAnnee) {
        this.numeroAnnee = numeroAnnee;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public List<SessionAnnee> getSessionAnneeList() {
        return sessionAnneeList;
    }

    public void setSessionAnneeList(List<SessionAnnee> sessionAnneeList) {
        this.sessionAnneeList = sessionAnneeList;
    }

    public List<ProgrammationCours> getProgrammationCoursList() {
        return programmationCoursList;
    }

    public void setProgrammationCoursList(List<ProgrammationCours> programmationCoursList) {
        this.programmationCoursList = programmationCoursList;
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
        hash += (numeroAnnee != null ? numeroAnnee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnneeScolaire)) {
            return false;
        }
        AnneeScolaire other = (AnneeScolaire) object;
        if ((this.numeroAnnee == null && other.numeroAnnee != null) || (this.numeroAnnee != null && !this.numeroAnnee.equals(other.numeroAnnee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.AnneeScolaire[ numeroAnnee=" + numeroAnnee + " ]";
    }
    
}
