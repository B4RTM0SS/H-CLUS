package clustering;

/**
 * Thrown to indicate that the <code>Dendrogram</code> depth is a number of examples in the data set.
 */
public class NegativeDepthException extends Exception {

    /**
     * Constructs an <code>NegativeDepthException</code> with a default detail message.
     * <p>The default detail message is: <i>"Unable to build a dendrogram with negative depth."</i></p>
     */
    NegativeDepthException () {
        super("Unable to build a dendrogram with negative depth.");
    }

    /**
     * Constructs an <code>NegativeDepthException</code> with the specified detail message.
     *
     * @param str the detail message.
     */
    NegativeDepthException (String str) {
        super(str);
    }

}
