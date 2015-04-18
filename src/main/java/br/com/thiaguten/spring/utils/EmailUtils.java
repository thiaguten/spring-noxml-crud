package br.com.thiaguten.spring.utils;

import java.net.IDN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

/**
 * Lógica copiada do Hibernate Validator (EmailValidator).
 * <p>
 * Eu não sei porque a mensagem da anotação
 * {@link org.hibernate.validator.constraints.Email} não está sendo mostrada a
 * partir do arquivo message.properties
 */
public abstract class EmailUtils {

    private static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    private static final String DOMAIN = ATOM + "+(\\." + ATOM + "+)*";
    private static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

    /**
     * Regular expression for the local part of an email address (everything before '@')
     */
    private static final Pattern localPattern = java.util.regex.Pattern.compile(
            ATOM + "+(\\." + ATOM + "+)*", CASE_INSENSITIVE
    );

    /**
     * Regular expression for the domain part of an email address (everything after '@')
     */
    private static final Pattern domainPattern = java.util.regex.Pattern.compile(
            DOMAIN + "|" + IP_DOMAIN, CASE_INSENSITIVE
    );

    public static boolean isValid(CharSequence value) {
        if (value == null || value.length() == 0) {
            return true;
        }

        // split email at '@' and consider local and domain part separately;
        // note a split limit of 3 is used as it causes all characters following to an (illegal) second @ character to
        // be put into a separate array element, avoiding the regex application in this case since the resulting array
        // has more than 2 elements
        String[] emailParts = value.toString().split("@", 3);
        if (emailParts.length != 2) {
            return false;
        }

        // if we have a trailing dot in local or domain part we have an invalid email address.
        // the regular expression match would take care of this, but IDN.toASCII drops trailing the trailing '.'
        // (imo a bug in the implementation)
        return !(emailParts[0].endsWith(".") || emailParts[1].endsWith(".")) && matchPart(emailParts[0], localPattern) && matchPart(emailParts[1], domainPattern);

    }

    private static boolean matchPart(String part, Pattern pattern) {
        try {
            part = IDN.toASCII(part);
        } catch (IllegalArgumentException e) {
            // occurs when the label is too long (>63, even though it should probably be 64 - see http://www.rfc-editor.org/errata_search.php?rfc=3696,
            // practically that should not be a problem)
            return false;
        }
        Matcher matcher = pattern.matcher(part);
        return matcher.matches();
    }


}
