package clustering;

/**
 * Thrown to indicate that the <code>Dendrogram</code> depth exceeds the number of examples in the data set.
 */
public class InvalidDepthException extends Exception {

    /**
     * Constructs an <code>InvalidDepthException</code> with a default detail message.
     *
     * <p>The default detail message is: <i>"Invalid dendrogram depth"</i></p>
     */
    InvalidDepthException() {
        super("Invalid dendrogram depth");
    }

    /**
     * Constructs an <code>InvalidDepthException</code> indicating the illegal depth.
     *
     * @param depth the illegal depth.
     */
    InvalidDepthException(int depth) {
        super("Invalid dendrogram depth: " + depth);
    }

    /**
     * Constructs an <code>InvalidDepthException</code> indicating the illegal depth and the specified detail message.
     *
     * @param depth the illegal depth.
     *
     * @param str the detail message.
     */
    InvalidDepthException(int depth, String str) {
        super("Invalid dendrogram depth: " + depth + ". " + str);
    }

    /**
     * Constructs an <code>InvalidDepthException</code> with the specified detail message.
     *
     * @param str the detail message.
     */
    InvalidDepthException(String str) {
        super(str);
    }

}
