package clustering;

import data.Data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Cluster is a class that allows to manage examples from a data set.
 * <p>Cluster class only stores the indexes of the examples. The indexes refers to the position of an example in a data set.
 * This class does not store the considered data set.</p>
 */
public class Cluster implements Iterable<Integer>, Cloneable, Serializable {

	/**
	 * Indexes of the examples. Each element clusteredData represent a different example index.
	 */
	private Set<Integer> clusteredData = new TreeSet<>();

	/**
	 * Returns an iterator over the elements in this cluster.
	 *
	 * @return an iterator over the elements in this cluster
	 */
	public Iterator<Integer> iterator () {
		return clusteredData.iterator();
	}

	/**
	 * Adds the index of an example to this cluster.
	 *
	 * @param id index of an example
	 */
	void addData (int id) {
		clusteredData.add(id);
	}

	/**
	 * Returns the number of indexes in this cluster.
	 *
	 * @return the number of indexes in this cluster
	 */
	public int getSize() {
		return clusteredData.size();
	}

	/**
	 * Returns a copy of this <code>Cluster</code> instance.
	 *
	 * @return a copy of this cluster
	 *
	 * @throws CloneNotSupportedException if the attribute classes does not support the Cloneable interface.
	 */
	@Override
	public Object clone () throws CloneNotSupportedException {
		Cluster temp;
		try {
			temp = (Cluster) super.clone();
			temp.clusteredData = (Set<Integer>) ( (TreeSet<Integer>) clusteredData).clone();
		} catch (CloneNotSupportedException exception) {
			throw new CloneNotSupportedException("Cluster can't clone.");
		}
		return temp;
	}

	/**
	 * Returns a cluster, result of the merge operation between this <code>Cluster</code> instance and the cluster
	 * argument.
	 * <p>Resulting cluster contains the indexes present in both clusters to be merged.</p>
	 *
	 * @param c one of the clusters to merge
	 *
	 * @return a cluster, result of the merge operation.
	 */
	Cluster mergeCluster (Cluster c) {
		Cluster newC;
		try {
			newC = (Cluster) this.clone();
		} catch (CloneNotSupportedException exception) {
			// Se il clone non è supportato, copio manualmente
			newC = new Cluster();
			for (Integer i : this)
				newC.addData(i);
		}
		for (Integer i : c)
			newC.addData(i);
		return newC;
		
	}

	/**
	 * Returns a string representation of the cluster.
	 *
	 * <p>The string contains all the indexes in this <code>Cluster</code> instance.</p>
	 *
	 * @return a string representation of the cluster
	 */
	public String toString () {
		StringBuilder str = new StringBuilder();
		Iterator<Integer> i = iterator();
		while (i.hasNext()) {
			str.append(i.next());
			if (i.hasNext())
				str.append(",");
		}
		return str.toString();
	}

	/**
	 * Returns a string representation of the cluster.
	 *
	 * <p>The string contains all string representations of the examples, whose indices are present in this
	 * <code>Cluster</code> instance.</p>
	 *
	 * @param data the dataset containing the examples
	 *
	 * @return a string representation of the examples, whose indices are contained in this cluster
	 */
	String toString (Data data) {
		StringBuilder str = new StringBuilder();
		for (Integer i : clusteredData)
			str.append("<").append(data.getExample(i)).append(">");
		return str.toString();
		
	}

}
