package data;

/**
 * Thrown if the examples cannot be loaded due to an error during the process.
 */
public class NoDataException extends Exception {

    /**
     * Construct a <code>NoDataException</code> object, with a default string as exception message.
     */
    NoDataException () {
        super("Unable to load dataset.");
    }

    /**
     * Construct a <code>NoDataException</code> object, with a given string argument as exception message.
     * @param str a description of the exception
     */
    NoDataException (String str) {
        super("Unable to load dataset. " + str);
    }

}
