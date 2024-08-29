package data;

/**
 * Thrown to indicate that two examples have different sizes. The first <code>Example</code> has either less or more attributes than the second <code>Example</code>.
 */
public class InvalidSizeException extends Exception {

    /**
     * Constructs an <code>InvalidSizeException</code> with a default detail message.
     *
     * <p>The default detail message is: <i>"Invalid example size"</i>.</p>
     */
    InvalidSizeException() {
        super("Invalid example size");
    }

    /**
     * Constructs an <code>InvalidSizeException</code> with the specified detail message.
     *
     * @param str the detail message.
     */
    InvalidSizeException(String str) {
        super(str);
    }

}
