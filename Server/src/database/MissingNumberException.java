package database;

/**
 * Thrown if a non-numeric value is read from a table, while a numeric one is expected.
 */
public class MissingNumberException extends Exception {

    /**
     * Construct a <code>MissingNumberException</code> object, with a default string as exception message.
     */
    public MissingNumberException () {
        super("There is a non-numeric attribute.");
    }

    /**
     * Construct a <code>MissingNumberException</code> object, with a given string argument as exception message.
     * @param str a description of the exception
     */
    public MissingNumberException (String str) {
        super(str);
    }

}
