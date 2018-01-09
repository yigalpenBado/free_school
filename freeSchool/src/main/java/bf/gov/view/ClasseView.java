/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.view;

import bf.gov.data.Classe;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author peter
 */
@Named("classeBean")
@SessionScoped
public class ClasseView implements Serializable{
    private Classe classe;
    private List<Classe> listeDesClasses;
    
    @PostConstruct
    public void init(){
        classe=new Classe();
        if (listeDesClasses==null||listeDesClasses.isEmpty()) {
            listeDesClasses=new ArrayList<>();
        }
    }

    public ClasseView() {
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Classe> getListeDesClasses() {
        return listeDesClasses;
    }

    public void setListeDesClasses(List<Classe> listeDesClasses) {
        this.listeDesClasses = listeDesClasses;
    }
    
    public String enregistrer(){
        listeDesClasses.add(classe);
        nouveau();
        return null;
    }
    
    public String nouveau(){
        classe=new Classe();
        return null;
    }
    
    
}
