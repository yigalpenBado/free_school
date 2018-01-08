/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.util;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

/**
 *
 * @author icomg
 */
public class MessagesJsf {
    
    private static ResourceBundle msgJsf;

    static {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            msgJsf = context.getApplication().getResourceBundle(context, "msgJsf");
        } catch (Exception e) {
        }

    }

    public static String getMessage(String idMsg) {
        return (msgJsf == null ? null : msgJsf.getString(idMsg));
    }
    
    public static void addInfoMessage(String idMsg) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage(idMsg), null));
        }
    }

    public static void addErrorMessage(String idMsg) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage(idMsg), null));
        }
    }

    public static void addInfoMessageTexte(String msg) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        }
    }

    public static void addErrorMessageTexte(String msg) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        }
    }
    
    public static void addErrorMessageTexte(String msg, String clienId) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            facesContext.addMessage(clienId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        }
    }

    public static void addException(Throwable e) {
        String exceptionMsg;
        if (e instanceof NoResultException) {
            exceptionMsg = "Aucun résultat trouvé";
        } else {
            exceptionMsg = "Une erreur s'est produite lors de l'exécution de l'opération. Bien vouloir contacter l'administrateur.";
        }

        FacesContext context = FacesContext.getCurrentInstance();
        if (context != null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, exceptionMsg, null));
        }

    }
}
