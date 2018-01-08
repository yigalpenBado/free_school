package bf.gov.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Generation des mots de passes pardéfaut en utilisant un générateur de nombres
 * aléatoires sécurisé.
 * <p>
 * L'alphabet (l'ensemble des caractères utilisables dans les mots de passe)
 * peut être spécifié
 * <p>
 */
public class PasswordUtils {

    /**
     * Default length for passwords
     */
    private static final int DEFAULT_PASSWORD_LENGTH = 6;

    /**
     * Alphabet consisting of upper and lower case letters A-Z and the digits
     * 0-9.
     */
    public static final char[] NUMBERS_AND_LETTERS_ALPHABET = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9',};

    /**
     * Alphabet consisting of upper and lower case letters A-Z and the digits
     * 0-9 but with characters that are often mistaken for each other when typed
     * removed. (I,L,O,U,V,i,l,o,u,v,0,1)
     */
    public static final char[] NONCONFUSING_ALPHABET = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S',
        'T', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c',
        'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
        'n', 'p', 'q', 'r', 's', 't', 'w', 'x',
        'y', 'z', '2', '3', '4', '5', '6', '7',
        '8', '9', '_',};

    /**
     * Random number generator used.
     */
    protected SecureRandom rand;

    /**
     * One less than the maximum number of repeated characters that are allowed
     * in a password. Set to -1 to disable this feature.
     */
    protected int repetition = -1;

    /**
     * Set of characters which may be used in the generated passwords.
     * <p>
     * This value may not be null or have no elements.
     */
    protected char[] alphabet;

    public static PasswordUtils instance = new PasswordUtils();

    /**
     * Create a new random password generator with the default secure random
     * number generator and default NONCONFUSING alphabet for all characters.
     */
    private PasswordUtils() {
        this(new SecureRandom(), NUMBERS_AND_LETTERS_ALPHABET);
    }

    /**
     * Create a new random password generator with the given secure random
     * number generator and given alphabet for all characters.
     *
     * @param rand Secure random number generator to use when generating
     * passwords.
     * @param alphabet Characters allowed in generated passwords.
     */
    private PasswordUtils(SecureRandom rand, char[] alphabet) {
        this.rand = rand;
        this.alphabet = alphabet;
    }

    public static PasswordUtils getInstance() {
        if (instance == null) {
            instance = new PasswordUtils();
        }
        return instance;
    }

    /**
     * Set the alphabet used by this random password generator.
     *
     * @param alphabet Characters allowed in generated passwords.
     * @throws NullPointerException if the alphabet is null.
     * @throws ArrayIndexOutOfBoundsException if the alphabet has no elements.
     */
    public void setAlphabet(char[] alphabet) {
        if (alphabet == null) {
            throw new NullPointerException("Null alphabet");
        }
        if (alphabet.length == 0) {
            throw new ArrayIndexOutOfBoundsException("No characters in alphabet");
        }
        this.alphabet = alphabet;
    }

    /**
     * Set the random number generator used by this random password generator.
     *
     * @param rand Secure random number generator to use when generating
     * passwords.
     */
    public void setRandomGenerator(SecureRandom rand) {
        this.rand = rand;
    }

    /**
     * Set the maximum number of characters that may appear in sequence more
     * than once in a password. Your alphabet must be large enough to handle
     * this option. If your alphabet is {'a', 'b'} and you want 8 character
     * passwords in which no character appears twice (repetition 1) you are out
     * of luck. In such instances your request for no repetition will be
     * ignored.
     * <p>
     * For example setRepetition(3) will allow a password ababab but not allow
     * abcabc.
     * <p>
     * Using this method can greatly reduce the pool of passwords that are
     * generated. For example if only one repetition is allowed then the pool of
     * passwords is the permutation of the alphabet rather than the combination.
     *
     * @param rep Maximum character repetition.
     */
    public void setMaxRepetition(int rep) {
        this.repetition = rep - 1;
    }

    /**
     * Fill the given buffer with random characters.
     * <p>
     * Using this method, the password character array can easily be reused for
     * efficiency, or overwritten with new random characters for security.
     * <p>
     * NOTE: If it is possible for a hacker to examine memory to find passwords,
     * the password should be overwritten in memory as soon as possible after it
     * is no longer in use.
     *
     * @param pass buffer that will hold the password.
     * @return the buffer, filled with random characters.
     */
    public char[] getPassChars(char[] pass) {
        boolean verified = false;
        while (!verified) {
            int length = pass.length;
            for (int i = 0; i < length; i++) {
                char[] useAlph = alphabet;

                int size = avoidRepetition(useAlph, pass, i, repetition, useAlph.length);
                pass[i] = useAlph[rand.nextInt(size)];
            }

            verified = true;
        }
        return (pass);
    }

    /**
     * Avoid repetition (if possible) by moving all characters that would cause
     * repetition to the end of the alphabet and returning the size of the
     * alphabet that may be used.
     */
    private static int avoidRepetition(char[] alph, char[] pass, int passSize, int repetition, int alphSize) {
        if (repetition > -1) {
            // limit the alphabet to those characters that
            // will not cause repeating sequences
            int repPos = 0;
            while ((repPos = findRep(pass, repPos, passSize, repetition)) != -1) {
                // shuffle characters that would cause repetition
                // to the end of the alphabet and adjust the size
                // so that they will not be used.
                alphSize -= moveto(alph, alphSize, pass[repPos + repetition]);
                repPos++;
            }
            if (alphSize == 0) {
                alphSize = alph.length;
            }
        }
        return alphSize;
    }

    /**
     * Find a repetition of the desired length. The characters to search for are
     * located at pass[end-length] to pass[end]
     */
    private static int findRep(char[] pass, int start, int end, int length) {
        for (int i = start; i < end - length; i++) {
            boolean onTrack = true;
            for (int j = 0; onTrack && j < length; j++) {
                if (pass[i + j] != pass[end - length + j]) {
                    onTrack = false;
                }
            }
            if (onTrack) {
                return i;
            }
        }
        return -1;
    }

    /**
     * move all of the given character to the end of the array and return the
     * number of characters moved.
     */
    private static int moveto(char[] alph, int numGood, char c) {
        int count = 0;
        for (int i = 0; i < numGood; i++) {
            if (alph[i] == c) {
                numGood--;
                char temp = alph[numGood];
                alph[numGood] = alph[i];
                alph[i] = temp;
                count++;
            }
        }
        return count;
    }

    /**
     * Generate a random password of the given length.
     * <p>
     * NOTE: If it is possible for a hacker to examine memory to find passwords,
     * the password should be overwritten in memory as soon as possible after i
     * is no longer in use.
     *
     * @param length The desired length of the generated password.
     * @return a random password
     */
    public char[] getPassChars(int length) {
        return (getPassChars(new char[length]));
    }

    /**
     * Generate a random password of the default length (8).
     * <p>
     * NOTE: If it is possible for a hacker to examine memory to find passwords,
     * the password should be overwritten in memory as soon as possible after i
     * is no longer in use.
     *
     * @return a random password
     */
    public char[] getPassChars() {
        return (getPassChars(DEFAULT_PASSWORD_LENGTH));
    }

    /**
     * Generate a random password of the given length.
     * <p>
     * NOTE: Strings can not be modified. If it is possible for a hacker to
     * examine memory to find passwords, getPassChars() should be used so that
     * the password can be zeroed out of memory when no longer in use.
     *
     * @param length The desired length of the generated password.
     * @return a random password
     *
     * @see #getPassChars(int)
     */
    public String getPassword(int length) {
        return (new String(getPassChars(new char[length])));
    }

    /**
     * Generate a random password of the default length (8).
     * <p>
     * NOTE: Strings can not be modified. If it is possible for a hacker to
     * examine memory to find passwords, getPassChars() should be used so that
     * the password can be zeroed out of memory when no longer in use.
     *
     * @return a random password
     *
     * @see #getPassChars()
     */
    public String getPassword() {
        return (getPassword(DEFAULT_PASSWORD_LENGTH));
    }

    public static boolean equals(String motDePasseCrypte, String motDePasseNonCrypte) throws NoSuchAlgorithmException {
        if (StringUtils.isEmpty(motDePasseCrypte) || StringUtils.isEmpty(motDePasseNonCrypte)) {
            return false;
        }
        return motDePasseCrypte.equals(EncryptUtils.encrypt(motDePasseNonCrypte));
    }
}
