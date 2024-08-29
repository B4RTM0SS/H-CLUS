package clustering;

import data.Data;
import data.InvalidSizeException;
import distance.ClusterDistance;

import java.io.*;

/**
 * HierarchicalClusterMiner is the class that allows to cluster examples.
 */
public class HierarchicalClusterMiner implements Serializable {

	/**
	 * Tree (or graph), that contains the steps of examples grouping process.
	 */
	private Dendrogram dendrogram;

	/**
	 * Constructs a new <code>HierarchicalClusterMiner</code> with a new dendrogram, with the depth argument.
	 *
	 * @param depth depth of the dendrogram
	 *
	 * @throws NegativeDepthException if the depth argument is negative.
	 */
	public HierarchicalClusterMiner (int depth) throws NegativeDepthException {
		try {
			dendrogram = new Dendrogram(depth);
		} catch (NegativeArraySizeException exception) {
			throw new NegativeDepthException();
		}
	}

	/**
	 * Returns a string representation of this <code>HierarchicalClusterMiner</code> instance.
	 *
	 * <p>The string contains the string representation of dendrogram, contained in this hierarchicalClusterMiner.</p>
	 *
	 * @return a string representation of this <code>HierarchicalClusterMiner</code> instance
	 */
	public String toString() {
		return dendrogram.toString();
	}

	/**
	 * Returns a string representation of this <code>HierarchicalClusterMiner</code> instance.
	 *
	 * <p>The string representations of the examples, whose indexes are stored in every level of dendrogram, contained
	 * in this hierarchicalClusterMiner.</p>
	 *
	 * @param data the dataset containing the examples
	 *
	 * @return a string representation of this <code>HierarchicalClusterMiner</code> instance
	 *
	 * @throws DifferentDimensionException if the dendrogram, contained in this
	 * <code>HierarchicalClusterMiner</code> instance, has a depth greater than the current dataset size.
	 */
	public String toString(Data data) throws DifferentDimensionException {
		return dendrogram.toString(data);
	}

	/**
	 * Clusters the clusterSets stored in the dendrogram deepest level, using the dataset argument and the specified
	 * distance method, until only one remains.
	 *
	 * @param data the dataset containing the examples
	 *
	 * @param distance the desired type of distance to use
	 *
	 * @throws InvalidSizeException if two example in the dataset have different sizes.
	 *
	 * @throws InvalidDepthException if the dendrogram depth exceeds the number of examples in the data set.
	 */
	public void mine(Data data, ClusterDistance distance) throws InvalidSizeException, InvalidDepthException {

		ClusterSet clusterSet = new ClusterSet(data.getNumberOfExamples());
		for (int i = 0; i < data.getNumberOfExamples(); i++) {
			Cluster cluster = new Cluster();
			cluster.addData(i);
			clusterSet.add(cluster);
		}

		try {
			dendrogram.setClusterSet(clusterSet, 0);
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new InvalidDepthException(0);
		}


		for (int levelIndex = 1; levelIndex < dendrogram.getDepth(); levelIndex++) {
			clusterSet = clusterSet.mergeClosestClusters(distance, data);
			dendrogram.setClusterSet(clusterSet, levelIndex);
		}

	}

	/**
	 * Returns an instance of <code>HierarchicalClusterMiner</code>, stored in a file named like the string argument.
	 *
	 * @param fileName the file name which contains the <code>HierarchicalClusterMiner</code> object
	 *
	 * @return an instance of <code>HierarchicalClusterMiner</code> class
	 *
	 * @throws FileNotFoundException if an attempt to open the file denoted by the specified name has failed.
	 *
	 * @throws IOException if an I/O operations is interrupted or fails.
	 *
	 * @throws ClassNotFoundException if an application tries to load in a class through its string name but no
	 * definition could be found.
	 */
	public static HierarchicalClusterMiner loadHierarchicalClusterMiner (String fileName)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream input = new FileInputStream (fileName);
		ObjectInputStream objIn = new ObjectInputStream (input);
		HierarchicalClusterMiner temp = (HierarchicalClusterMiner) objIn.readObject();
		objIn.close();
		input.close();
		return temp;
	}

	/** Save this <code>HierarchicalClusterMiner</code> instance on a file named like the string argument.
	 *
	 * @param fileName name of the file to save this <code>HierarchicalClusterMiner</code> instance to
	 *
	 * @throws FileNotFoundException thrown if the file does exist but for some reason is inaccessible, for example,
	 * open a read-only file for writing.
	 *
	 * @throws IOException if an I/ O operations is interrupted or fails.
	 */
	public void salva (String fileName) throws FileNotFoundException, IOException {
		FileOutputStream output = new FileOutputStream (fileName);
		ObjectOutputStream objOut = new ObjectOutputStream (output);
		objOut.writeObject(this);
		objOut.close();
		output.close();
	}

}
