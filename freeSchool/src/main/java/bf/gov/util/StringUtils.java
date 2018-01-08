/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.gov.util;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author satehin
 */
public class StringUtils {

    public static boolean isEmpty(String chaine) {
        return chaine == null || chaine.isEmpty();
    }

    public static boolean isEmptyTrim(String chaine) {
        return chaine == null || chaine.isEmpty() || chaine.trim().isEmpty();
    }

    public static boolean isEmail(String email) {
        if (isEmptyTrim(email)) {
            return false;
        }
        Pattern p = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$");
        Matcher m = p.matcher(email.trim());
        return (m.matches());
    }

    public static boolean isNumeroTelephonePortable(String tel) {
        if (isEmptyTrim(tel)) {
            return false;
        }
        Pattern p = Pattern.compile("^(7|6|5)(\\d){7}");
        Matcher m = p.matcher(tel.trim());
        return (m.matches());
    }

    public static boolean isNumeroTelephone(String tel) {
        if (isEmptyTrim(tel)) {
            return false;
        }
        Pattern p = Pattern.compile("^(7|6|5|2)(\\d){7}");
        Matcher m = p.matcher(tel.trim());
        return (m.matches());
    }

    public static String toFirsLetterUpperCase(String chaine) {
        String st = chaine.substring(0, 1).toUpperCase();
        String rest = chaine.substring(1);
        return st + rest;
    }

    /**
     * Mettre la chaine en minuscule et enlever les accents
     *
     * @param chaine
     * @return
     */
    public static String toLowerCase(String chaine) {
        if (chaine == null) {
            return null;
        }
        char[] charsData = new char[chaine.length()];
        chaine.getChars(0, charsData.length, charsData, 0);

        char c;
        for (int i = 0; i < charsData.length; i++) {
            if ((c = charsData[i]) >= 'A'
                    && c <= 'Z') {
                charsData[i] = (char) (c - 'A' + 'a');
            } else {
                switch (c) {
                    case '\u00e0':
                    case '\u00e2':
                    case '\u00e4':
                    case '\u00c0':
                    case '\u00c2':
                    case '\u00c4':
                        charsData[i] = 'a';
                        break;
                    case '\u00e7':
                    case '\u00c7':
                        charsData[i] = 'c';
                        break;
                    case '\u00e8':
                    case '\u00e9':
                    case '\u00ea':
                    case '\u00eb':
                    case '\u00c8':
                    case '\u00c9':
                    case '\u00ca':
                    case '\u00cb':
                        charsData[i] = 'e';
                        break;
                    case '\u00ee':
                    case '\u00ef':
                    case '\u00ce':
                    case '\u00cf':
                        charsData[i] = 'i';
                        break;
                    case '\u00f4':
                    case '\u00f6':
                    case '\u00d4':
                    case '\u00d6':
                        charsData[i] = 'o';
                        break;
                    case '\u00f9':
                    case '\u00fb':
                    case '\u00fc':
                    case '\u00d9':
                    case '\u00db':
                    case '\u00dc':
                        charsData[i] = 'u';
                        break;
                }
            }
        }

        return new String(charsData);
    }

    public static boolean estArticle(String chaine) {
        // Pattern pattern1Char=Pattern.compile("l'|d'|L'");
        Pattern pattern2Char = Pattern.compile("|L'|D'|LE|LA|DE|DU|AU|UN");
        Pattern pattern3Char = Pattern.compile("LES|DES|AUX|UNE");
        Pattern pattern4Char = Pattern.compile("DE LA|DE L'|A LA");
        if (isEmpty(chaine)) {
            return false;
        }
        if ((chaine.trim().length() == 1)) {
            return true;
        }
        if (chaine.trim().length() == 2) {
            return pattern2Char.matcher(chaine.trim().toUpperCase()).matches();
        }
        if (chaine.trim().length() == 3) {
            return pattern3Char.matcher(chaine.trim().toUpperCase()).matches();
        }
        return false;
    }

    public static String[] splitMail(String mail) {

        return mail.split(";");
    }

    /**
     * Genarateur de chaine de caractere pour le captcha 
     *
     * @param captchaLength longueur de la chaine de caractere du captcha
     * @return
     */
    public static String generateCaptchaText(int captchaLength) {

        String saltChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer captchaStrBuffer = new StringBuffer();
        java.util.Random rnd = new java.util.Random();

        // build a random captchaLength chars salt
        while (captchaStrBuffer.length() < captchaLength) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            captchaStrBuffer.append(saltChars.substring(index, index + 1));
        }

        return captchaStrBuffer.toString();

    }

}
