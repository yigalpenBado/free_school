/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.util;

/**
 *
 * @author trasmiro
 */
public class Params {

    public static String ACCUEIL_ADMIN = "admin";
    public static String DOC_TEMP_DIR = "/gv/doc/temp/";
    public static final String AUTH_KEY = "app.user.name";
    public static final String TEMP_DIR = "/gv/tmp/";

    public static final String PUB_DIR = "/gv/pub/";
    
    

    public static Long NB_VISITEURS;
    public static Long NB_USERS;


    public static class Flag{
        public static final String OUI = "O";
        public static final String NON = "N";
    }

    public static class TypeEtat {

        public static final String EN_SAISIE = "N";
        public static final String ENVOYEE = "E";
        public static final String REJETEE = "R";
        public static final String PUBLIEE = "P";
    }

    public static class TypeStrucutre {

        public static final Short MINISTERE_INSTITUTION = 1;
        public static final Short DIRECTION = 2;
    }

    public static class NatureDoc {

        public static final Short FORMULAIRE = 1;
    }

    public static class ProfilUtilisateur {
        public static final Short ADMIN = 2;
        public static final Short VAL = 3;
        public static final Short OPE = 1;
    } 
}
