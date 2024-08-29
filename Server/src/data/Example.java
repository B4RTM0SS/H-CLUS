package data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *  Example is the class that allows to store attributes for an element.
 *  <p>This class also provides the method <code>distance</code> which calculates the Euclidean distance between two examples.</p>
 */
public class Example implements Iterable<Double> {

    /**
     * Attributes of the example. Each position in the list represents an attribute.
     */
    private List<Double> example;

    /**
     * Returns an iterator over the attributes of this <code>Example</code>.
     *
     * @return the iterator for the <code>Example</code>.
     */
    public Iterator<Double> iterator() {
        return example.iterator();
    }

    /**
     * Initializes a new <code>Example</code>.
     */
    public Example() {
        example = new LinkedList<>();
    }

    /**
     * Stores the value for the current attribute of the <code>Example</code>.
     *
     * @param value the value of the current attribute.
     */
    public void add(Double value) {
        example.add(value);
    }

    /**
     * Calculates the Euclidean distance between two <code>Example</code>.
     * <p>The Euclidean distance is computed between this <code>Example</code> and the given <code>Example</code>.</p>
     *
     * @param newE the <code>Example</code> with which to calculate the distance.
     *
     * @return the distance between this <code>Example</code> and <code>newE</code>.
     *
     * @throws InvalidSizeException If the number of attributes in this <code>Example</code> is different from the number of attributes in <code>newE</code>.
     */
    public double distance(Example newE) throws InvalidSizeException {
        double sum = 0.0;
        Iterator<Double> exampleIterator = iterator();
        Iterator<Double> newExampleIterator = newE.iterator();
        while (exampleIterator.hasNext() || newExampleIterator.hasNext()) {
            try {
                sum += Math.pow(exampleIterator.next() - newExampleIterator.next(), 2);
            } catch (NoSuchElementException e) {
                throw new InvalidSizeException();
            }
        }
        return sum;
    }

    /**
     * Returns a string representation of the <code>Example</code>.
     * <p>The string shows all attributes of the <code>Example</code>.<br>For instance, the string representation of an example of attributes (1.0, 2.0, 3.0) will be "<i>[1.0,2.0,3.0]</i>".</p>
     * 
     * @return a string representation of the <code>Example</code>.
     */
    public String toString() {
        StringBuilder output = new StringBuilder("[");
        Iterator<Double> exampleIterator = iterator();
        while (exampleIterator.hasNext()) {
            output.append(exampleIterator.next());
            if (exampleIterator.hasNext()) {
                output.append(',');
            }
        }
        return output.append("]").toString();
    }

}
