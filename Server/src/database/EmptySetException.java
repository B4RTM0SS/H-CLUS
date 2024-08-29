package database;

/**
 * Thrown if a query resulting table is empty.
 */
public class EmptySetException extends Exception {

    /**
     * Construct a <code>EmptySetException</code> object, with a default string as exception message.
     */
    public EmptySetException () {
        super("No value, empty table.");
    }

    /**
     * Construct a <code>EmptySetException</code> object, with a given string argument as exception message.
     * @param str a description of the exception
     */
    public EmptySetException (String str) {
        super(str);
    }

}
