package distance;

import clustering.Cluster;
import data.Data;
import data.Example;
import data.InvalidSizeException;

/**
 * AverageLinkDistance is an implementation of <code>ClusterDistance</code>.
 *
 * <p>It provides a method to calculate the average link distance between two clusters.</p>
 */
public class AverageLinkDistance implements ClusterDistance {

    /**
     * Calculates the smallest average distance between two <code>Cluster</code>.
     *
     * @param c1 the first <code>Cluster</code>.
     *
     * @param c2 the second <code>Cluster</code>.
     *
     * @param d the <code>Data</code> to refer to.
     *
     * @return the smallest average distance between the first and the second cluster.
     *
     * @throws InvalidSizeException If two <code>Example</code> have different sizes.
     */
    public double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException {
        double distance = 0.0;
        for (Integer i1 : c1) {
            Example exTemp = d.getExample(i1);
            for (Integer i2 : c2) {
                distance += exTemp.distance(d.getExample(i2));
            }
        }
        return distance / (c1.getSize() * c2.getSize());
    }

}
