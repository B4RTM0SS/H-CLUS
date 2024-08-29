package distance;

import clustering.Cluster;
import data.Data;
import data.InvalidSizeException;

/**
 * ClusterDistance is an interface that allows to create different distance methods.
 */
public interface ClusterDistance {

	/**
	 * Calculates the distance between two <code>Cluster</code>.
	 *
	 * @param c1 the first <code>Cluster</code>.
	 *
	 * @param c2 the second <code>Cluster</code>.
	 *
	 * @param d the <code>Data</code> to refer to.
	 *
	 * @return the distance between the first and the second cluster.
	 *
	 * @throws InvalidSizeException If two <code>Example</code> have different sizes.
	 */
	double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException;
}
