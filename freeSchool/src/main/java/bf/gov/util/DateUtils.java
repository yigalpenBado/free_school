package bf.gov.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitaire implémentant des fonctions de manipulation de dates 
 * 
 * 
 */
public class DateUtils {

    /**
     * Tableau contenant le nombre de jours de chaque mois dans une année non
     * bisextile
     */
    static final int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
        30, 31};

    public static String toString(Date date) {
        if (date == null) {
            return (null);
        }
        SimpleDateFormat form = new SimpleDateFormat("ddMMyyyy");
        return (form.format(date).toString());
    }

     public static String toStringF(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        return (form.format(date).toString());
    }

    // Pour empecher cette classe d'etre instanciee
    private DateUtils() {
    }

    public static String getDate(){
    	Calendar c = Calendar.getInstance();
    	SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
    	return (form.format(c.getTime()).toString()); 
    }

    public static String getFileDate(){
    	Calendar c = Calendar.getInstance();
    	SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy_HH-mm");
    	return (form.format(c.getTime()).toString()); 
    }
		   
    /**
     * Convertit la date donnée en parametre en string représentation de la
     * forme jj/mm/aaaa
     */
    public static String toStringFr(Date date) {
        if (date == null) {
            return "&nbsp;";
        }
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        return (form.format(date).toString());
    }

    public static String heureToStringFr(Date date) {
        if (date == null) {
            return "&nbsp;";
        }
        SimpleDateFormat form = new SimpleDateFormat("HH:mm");
        return (form.format(date).toString());
    }

    /**
     * Convertit la date donnée en parametre en string représentation de la
     * forme jj/mm/aaaa hh:mm:ss
     */
    public static String toStringFrLong(Date date) {
        if (date == null) {
            return (null);
        }
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return (form.format(date).toString());
    }

    /**
     * Convertit la date donnée en parametre en string représentation de la
     * forme jj/mm/aaaa hh:mm:ss
     */
    public static String toTime(Date date) {
        if (date == null) {
            return (null);
        }
        SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
        return (form.format(date).toString());
    }

    /**
     * Met le temps de la date à sa valeur max (heure=23, minutes=59,
     * secondes=59)
     * 
     * @param date:
     *            la date à modifier
     * @return la date avec temps max
     */
    public static java.util.Date setMaxTime(Date date) {
        if (date == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return (cal.getTime());
    }

    /**
     * Met le temps de la date à sa valeur min (heure=0, minutes=0, secondes=0)
     * 
     * @param date:
     *            la date à modifier
     * @return la date avec temps min
     */
    public static java.util.Date setMinTime(Date date) {
        if (date == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return (cal.getTime());
    }

    /**
     * Met le temps de la date à sa valeur min (heure=0, minutes=0, secondes=0)
     *
     * @param date:
     *            la date à modifier
     * @return la date avec temps min
     */
    public static java.util.Date setTime(Date date, int h, int m, int s) {
        if (date == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, h);
        cal.set(Calendar.MINUTE, m);
        cal.set(Calendar.SECOND, s);
        return (cal.getTime());
    }
    
    public static java.util.Date getTime(int j, int m, int a) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, j);
        cal.set(Calendar.MONTH, m);
        cal.set(Calendar.YEAR, a);
        return (cal.getTime());
    }
    
    /**
     * Verifier si une date a atteint la limite d'age au 31 decembre de l'annee en cours
     * @param date
     * @param nbAnnee
     * @return 
     */
    public static boolean isDateNaissanceDepassee(Date date, int nbAnnee) {
        Calendar calDateNais = Calendar.getInstance();
        calDateNais.setTime(date);
        int year = calDateNais.get(Calendar.YEAR);
        calDateNais.set(Calendar.YEAR, year + nbAnnee);
        calDateNais.set(Calendar.MONTH, 11);
        calDateNais.set(Calendar.HOUR, 0);
        calDateNais.set(Calendar.MINUTE, 0);
        calDateNais.set(Calendar.SECOND, 0);
        
        Calendar calDuJour = Calendar.getInstance();
        calDuJour.set(Calendar.DAY_OF_MONTH, 31);
        calDuJour.set(Calendar.MONTH, 11);
        calDuJour.set(Calendar.HOUR, 0);
        calDuJour.set(Calendar.MINUTE, 0);
        calDuJour.set(Calendar.SECOND, 0);
        
        return calDateNais.getTime().before(calDuJour.getTime());
    }
    
    /**
     * Verifier l'expiration d'une date
     * @param date
     * @param nbAnnee
     * @return 
     */
    public static boolean isDateExpiree(Date date, int nbAnnee) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.YEAR, year + nbAnnee);
        cal.set(Calendar.DAY_OF_MONTH, day- 1);
        
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        
        return cal.getTime().before(cal1.getTime());
    }

    /**
     * Convertir un string en date. Le format du string peut être: jj/mm/aaaa,
     * jj/m/aaaa, j/mm/aaaa, j/m/aaaa a = année, m=mois, j=jour dans le mois
     */
    public static Date parseDate(String dateString) {
        try {
            if (dateString == null) {
                return null;
            }
            SimpleDateFormat dateFormat = createFormater(dateString.trim());
            // ceci empeche java d'ajouter automatiquement 1 au mois si
            // le nombre de jours du mois est en trop
            // ie.. si on donne le nbre de jours = 31 et le mois =4 (Avril) qui a 30
            // jours
            // la date sera convertie en 1er Mai.
            if (dateFormat != null) {
                dateFormat.setLenient(false);
                Date myDate = dateFormat.parse(dateString);
                return myDate;
            }else{
                return null;
            }
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // helper class
    private static SimpleDateFormat createFormater(String dateString) {
        SimpleDateFormat dateFormat = null;

        if (dateString.length() == 10) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        } else if (dateString.length() == 8) {
            dateFormat = new SimpleDateFormat("d/M/yyyy");
        } else if (dateString.length() == 9) // dd/M/yyyy ou d/MM/yyyy
        {
            if (dateString.charAt(2) == '/') {
                dateFormat = new SimpleDateFormat("dd/M/yyyy");
            } else {
                dateFormat = new SimpleDateFormat("d/MM/yyyy");
            }
        }

        return (dateFormat);
    }

    /**
     * Retourne le nombre de jours dans un mois donné. Les mois sont: Janvier=0,
     * Fevrier=1, etc.
     */
    public static int daysInMonth(int month, int year) {
        if ((month < 0) || (month > 11)) {
            return -1;
        } else if (month == 1) { // handle February
            return isLeapYear(year) ? 29 : 28;
        } else { // use array of days for other months
            return monthDays[month];
        }
    }

    /**
     * Ajouter un nombre de jours à une date et retourner la date
     * correspondante.
     */
    public static Date addToDate(Date date, Integer nbJours) {
        if (date == null) {
            return (null);
        }
        if(nbJours == null){
            nbJours = 0;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, nbJours);

        return (c.getTime());
    }

    /**
     * Soustraire un nombre de jours à une date et retourner la date
     * correspondante.
     */
    public static Date subFromDate(Date date, Integer nbJours) {
        if (date == null) {
            return (null);
        }
        if(nbJours == null){
            nbJours = 0;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -nbJours);

        return (c.getTime());
    }

    /**
     * Retourne l'annee en cours
     */
    public static int anneeEnCours() {
        Calendar rightNow = Calendar.getInstance();
        return (rightNow.get(Calendar.YEAR));
    }

    /**
     * Retourne l'annee d'une date donnée
     */
    public static int annee(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return (cal.get(Calendar.YEAR));
    }

    /**
     * Retourne le mois du jour
     */
    public static int ceMois() {
        Calendar rightNow = Calendar.getInstance();
        return (rightNow.get(Calendar.MONTH));
    }

    /**
     * Retourne le jour d'aujourd'hui: 1..30
     */
    public static int ceJour() {
        Calendar rightNow = Calendar.getInstance();
        return (rightNow.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Vérifie si une date est entre deux dates données
     * @param date
     * @param dateDebut
     * @param dateFin
     */
    public static boolean isBetween(Date date, Date dateDebut, Date dateFin) {
        boolean apresDebut = (date.compareTo(dateDebut) >= 0);
        boolean avantFin = (date.compareTo(dateFin) <= 0);

        if (apresDebut && avantFin) {
            return true;
        }
        return false;
    }

    /**
     * Retourne le nombre de jours entre deux dates; 
     * La méthode convertit le nombre de jour de la façcon suivante:
     * 0,x jours avec x > 5 = 1 jour
     * 0,5 = 0,5 jour
     * 0,x jours avec x < 5 = 0 jour
     */
    public static long ecart(Date debut, Date fin) {
        if(debut == null || fin == null){
            return 0;
        }
        Date dateDebut = debut, dateFin = fin;
        boolean b = false;
        if (debut.after(fin)) {
            dateDebut = fin;
            dateFin = debut;
            b = true;
        }

        Calendar cDebut = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cDebut.setTime(dateDebut);
        Calendar cFin = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cFin.setTime(dateFin);

        long msDebut = cDebut.getTimeInMillis();
        long msFin = cFin.getTimeInMillis();
        long diff = msFin - msDebut;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long ecart = BigDecimal.valueOf(diffDays).setScale(1, BigDecimal.ROUND_HALF_UP).longValue();
        if(b)
         return -ecart;
        return ecart;
    }
    
    public static int getAnneeEnCours(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    // -----------------------
    /**
     * Vérifie si l'année donnée en paramètre est une année bisextile Une année
     * est bisextile si elle est divisible par 4 et soit non divisible par 100 soit
     * divisible par 400.
     */
    public static boolean isLeapYear(int year) {
        if ((year & 3) == 0) // year is multiple of four
        {
            return (((year % 400) == 0) || ((year % 100) != 0));
        }
        return false;
    }
}
