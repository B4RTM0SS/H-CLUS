package distance;

import clustering.Cluster;
import data.Data;
import data.Example;
import data.InvalidSizeException;

/**
 * SingleLinkDistance is an implementation of <code>ClusterDistance</code>.
 *
 * <p>It provides a method to calculate the single link distance between examples.</p>
 */
public class SingleLinkDistance implements ClusterDistance {

	/**
	 * Calculates the smallest distance between two <code>Cluster</code>.
	 *
	 * @param c1 the first <code>Cluster</code>.
	 *
	 * @param c2 the second <code>Cluster</code>.
	 *
	 * @param d the <code>Data</code> to refer to.
	 *
	 * @return the smallest distance between the first and the second cluster.
	 *
	 * @throws InvalidSizeException If two <code>Example</code> have different sizes.
	 */
	public double distance(Cluster c1, Cluster c2, Data d) throws InvalidSizeException {
		
		double min = Double.MAX_VALUE;

		for (Integer i1 : c1) {
			Example e1 = d.getExample ( i1 );
			for (Integer i2 : c2) {
				double distance = e1.distance ( d.getExample(i2) );
				if (distance < min)
					min = distance;
			}
		}
		return min;
	}
}
