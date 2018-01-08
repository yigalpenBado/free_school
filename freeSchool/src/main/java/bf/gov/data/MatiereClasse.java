/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author peter
 */
@Entity
@Table(name = "matiere_classe")
@NamedQueries({
    @NamedQuery(name = "MatiereClasse.findAll", query = "SELECT m FROM MatiereClasse m")})
public class MatiereClasse implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MatiereClassePK matiereClassePK;
    @Column(name = "coefficient")
    private Integer coefficient;
    @JoinColumn(name = "code_classe", referencedColumnName = "code_classe", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Classe classe;
    @JoinColumn(name = "code_matiere", referencedColumnName = "code_matiere", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matiere matiere;

    public MatiereClasse() {
    }

    public MatiereClasse(MatiereClassePK matiereClassePK) {
        this.matiereClassePK = matiereClassePK;
    }

    public MatiereClasse(String codeMatiere, String codeClasse) {
        this.matiereClassePK = new MatiereClassePK(codeMatiere, codeClasse);
    }

    public MatiereClassePK getMatiereClassePK() {
        return matiereClassePK;
    }

    public void setMatiereClassePK(MatiereClassePK matiereClassePK) {
        this.matiereClassePK = matiereClassePK;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matiereClassePK != null ? matiereClassePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatiereClasse)) {
            return false;
        }
        MatiereClasse other = (MatiereClasse) object;
        if ((this.matiereClassePK == null && other.matiereClassePK != null) || (this.matiereClassePK != null && !this.matiereClassePK.equals(other.matiereClassePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bf.gov.data.MatiereClasse[ matiereClassePK=" + matiereClassePK + " ]";
    }
    
}
