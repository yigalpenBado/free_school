package bf.gov.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Classe utilitaire implémentant des fonctions de sérialisation d'objets, de
 * cryptage (base 64), de compression (zip) et de hashage
 *
 *
 */
public class EncryptUtils {

    /**
     * La fonction de hachage utilis�
     */
    private static final String HASH_ALGO = "SHA-1";

    /**
     * Table de lookup qui traduit les indexes (entiers positifs de 6-bit) en
     * leur equivalent dans l'alphabet "Base64" comme specifier dans la Table 1
     * du RFC 2045.
     */
    private static final char intToAlpha[] = {'A', 'B', 'C', 'D', 'E', 'F',
        'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
        'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
        't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', '+', '/'};

    /**
     * S�rialiser un objet en flot d'octets
     *
     * @param o l'objet � s�rialiser
     * @return Le tableau d'octets contenant l'objet s�rialis�
     */
    public static byte[] encodeByteArray(Object o) throws IOException {
        byte[] bytes;
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(baOut);
        objOut.writeObject(o);
        objOut.flush();
        bytes = baOut.toByteArray();
        objOut.close();

        return bytes;
    }

    /**
     * Reconstruire un object java a partir du flot des octets de l'objet
     *
     * @param buffer Le tableau d'octets contenant l'objet s�rialis�
     * @return L'objet d�s�rialis�
     */
    public static Object decodeByteArray(byte[] buffer) {
        try {
            ByteArrayInputStream baIn = new ByteArrayInputStream(buffer);
            ObjectInputStream objIn = new ObjectInputStream(baIn);
            Object o = objIn.readObject();
            objIn.close();
            return o;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Serialiser et compresser un objet
     *
     * @param buf buffer a compresser
     * @return byte[] le contenu de buffer compresse
     */
    public static byte[] encodeGzip(Object o) throws IOException {
        byte[] bytes;
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        GZIPOutputStream gzOut = new GZIPOutputStream(baOut);
        ObjectOutputStream objOut = new ObjectOutputStream(gzOut);
        objOut.writeObject(o);
        objOut.flush();
        bytes = baOut.toByteArray();
        objOut.close();

        return bytes;
    }

    /**
     * Decompresser et deserialiser un objet
     *
     * @param buffer buffer � d�compresser
     * @return byte[] le contenu du buffer d�compresser
     */
    public static Object decodeGzip(byte[] buffer) throws Exception {
        ByteArrayInputStream baIn = new ByteArrayInputStream(buffer);
        GZIPInputStream gzIn = new GZIPInputStream(baIn);
        ObjectInputStream objIn = new ObjectInputStream(gzIn);
        Object o = objIn.readObject();
        objIn.close();
        return o;
    }

    /**
     * Compresser
     */
    public static byte[] compress(String s) throws IOException {
        return compress(s.getBytes());
    }

    /**
     * Compresser
     */
    public static byte[] compress(byte[] buffer) throws IOException {
        ByteArrayOutputStream baOut = new ByteArrayOutputStream();
        GZIPOutputStream gzOut = new GZIPOutputStream(baOut);
        ByteArrayInputStream baIn = new ByteArrayInputStream(buffer);

        byte[] buf = new byte[1024];
        int len;
        while ((len = baIn.read(buf)) > 0) {
            gzOut.write(buf, 0, len);
        }
        baIn.close();
        //  Finish creation of gzip file
        gzOut.finish();
        byte[] result = baOut.toByteArray();
        gzOut.close();

        return result;
    }

    /**
     * Decompresser
     */
    public static byte[] uncompress(String s) throws IOException {
        return uncompress(s.getBytes());
    }

    /**
     * Decompresser
     */
    public static byte[] uncompress(byte[] buffer) throws IOException {
        ByteArrayInputStream baIn = new ByteArrayInputStream(buffer);
        GZIPInputStream gzIn = new GZIPInputStream(baIn);

        ByteArrayOutputStream baOut = new ByteArrayOutputStream();

        byte[] buf = new byte[1024];
        int len;
        while ((len = gzIn.read(buf)) > 0) {
            baOut.write(buf, 0, len);
        }
        gzIn.close();
        byte[] result = baOut.toByteArray();
        baOut.close();

        return result;
    }

    /**
     * Coder un tableau d'octets en chaine de caracteres en base 64; lorsqu'on
     * calcule le hash d'une chaine, le resultat peut contenir des octets non
     * imprimables et qui ne peuvent pas appartenir a une chaine de caracteres
     * il est necessaire de l'encoder (en hexa ou en base 64) avant de le
     * stocker dans la base de donn�es sous forme de chaine de caracteres
     *
     * @param raw Le tableau d'octets a coder
     * @return Chaine representant le tableau en base 64
     */
    public static String encodeBase64(byte[] raw) {
        int aLen = raw.length;
        int numFullGroups = aLen / 3; // groupes pleins de 3 octets
        int numBytesInPartialGroup = aLen - 3 * numFullGroups;
        int resultLen = 4 * ((aLen + 2) / 3);
        StringBuffer result = new StringBuffer(resultLen);

        // Traduire les groupes de 3 octets en Base64
        int inCursor = 0;
        for (int i = 0; i < numFullGroups; i++) {
            int byte0 = raw[inCursor++] & 0xff;
            int byte1 = raw[inCursor++] & 0xff;
            int byte2 = raw[inCursor++] & 0xff;
            result.append(intToAlpha[byte0 >> 2]);
            result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
            result.append(intToAlpha[(byte1 << 2) & 0x3f | (byte2 >> 6)]);
            result.append(intToAlpha[byte2 & 0x3f]);
        }

        // Traduire le groupe incomplet restant s'il existe
        if (numBytesInPartialGroup != 0) {
            int byte0 = raw[inCursor++] & 0xff;
            result.append(intToAlpha[byte0 >> 2]);
            if (numBytesInPartialGroup == 1) {
                result.append(intToAlpha[(byte0 << 4) & 0x3f]);
                result.append("==");
            } else {
                // assert numBytesInPartialGroup == 2;
                int byte1 = raw[inCursor++] & 0xff;
                result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
                result.append(intToAlpha[(byte1 << 2) & 0x3f]);
                result.append('=');
            }
        }
        // assert inCursor == a.length;
        // assert result.length() == resultLen;
        return result.toString();
    }

    /**
     * Crypter un string (calculer le hash puis coder en base64)
     *
     * @param s Le string a crypter
     * @return String
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(String s) throws NoSuchAlgorithmException {
        return encodeBase64(hashSha1(s));
    }

    // ----------------------------------------
    /**
     * Crypter une chaine de caracteres en utilisant la fonction de hachage
     * SHA-1 Une fonction de hachage est une fonction qui calcule l "emprunte
     * digital" d'un document; cette fonction est non reversible
     *
     * @param x la chaine � crypter
     * @return le hash de la chaine
     * @throws Exception
     */
    private static byte[] hashSha1(String x) throws NoSuchAlgorithmException {
        return hashSha1(x.getBytes());
    }

    /**
     * Crypter une chaine de caracteres en utilisant la fonction de hachage
     * SHA-1 Une fonction de hachage est une fonction qui calcule l "emprunte
     * digital" d'un document; cette fonction est non reversible
     *
     * @param x la chaine � crypter
     * @return le hash de la chaine
     * @throws Exception
     */
    private static byte[] hashSha1(byte[] x) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(HASH_ALGO);
        md.reset();
        md.update(x);
        return md.digest();
    }

    public static byte[] toByte(String str) {
        return str.getBytes();
    }

    public static String toString(byte[] tab) {
        return new String(tab);
    }
}
