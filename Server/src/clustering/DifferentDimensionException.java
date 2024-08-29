package clustering;

/**
 * Thrown if trying to get a string representation of a dendrogram whose depth is greater than the current dataset
 * dimension.
 */
public class DifferentDimensionException extends Exception {

    /**
     * Construct a <code>DifferentDimensionException</code> object, with a default string as exception message.
     */
    DifferentDimensionException () {
        super("The examples for this miner can't be loaded with current dataset.");
    }

    /**
     * Construct a <code>DifferentDimensionException</code> object, with a given string argument as exception message.
     * @param str a description of the exception
     */
    DifferentDimensionException (String str) {
        super(str);
    }

}
