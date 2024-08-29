package clustering;

import data.Data;
import data.InvalidSizeException;
import distance.ClusterDistance;

import java.io.Serializable;

/**
 * ClusterSet is a class that allows to encapsulate multiple <code>Cluster</code>.
 */
class ClusterSet implements Serializable {

	/**
	 * Clusters of the cluster set.
	 */
	private Cluster[] C;

	/**
	 * The position of the last <code>Cluster</code> added to the <code>ClusterSet</code>.
	 */
	private int lastClusterIndex=0;

	/**
	 * Constructs a new <code>ClusterSet</code> of given lenght.
	 *
	 * @param k the maximum number of <code>Cluster</code> in <code>ClusterSet</code>.
	 */
	ClusterSet(int k){
		C=new Cluster[k];
	}

	/**
	 * Adds the specified <code>Cluster</code> to the <code>ClusterSet</code>.
	 * This method provides also to increment the <code>lastClusterIndex</code>.
	 *
	 * @param c the <code>Cluster</code> to add to the <code>ClusterSet</code>.
	 */
	void add(Cluster c){
		for(int j=0;j<lastClusterIndex;j++)
			if(c==C[j]) // to avoid duplicates
				return;
		C[lastClusterIndex]=c;
		lastClusterIndex++;
	}

	/**
	 * Gets the <code>Cluster</code> in the specified position from <code>ClusterSet</code>.
	 *
	 * @param i the position in which to look for the <code>Cluster</code>.
	 *
	 * @return the <code>Cluster</code> in the specified position of the <code>ClusterSet</code>.
	 */
	private Cluster get(int i) {
		return C[i];
	}

	/**
	 * Returns a string representation of <code>ClusterSet</code>.
	 *
	 * <p>The string represent all the cluster in the <code>ClusterSet</code> showing the indexes of the examples in each <code>Cluster</code>.
	 *
	 * @return a string representation of <code>Data</code>.
	 */
	public String toString(){
		String str="";
		for(int i=0;i<C.length;i++){
			if (C[i]!=null){
				str+="cluster"+i+":"+C[i]+"\n";
		
			}
		}
		return str;
		
	}

	/**
	 * Returns a string representation of <code>ClusterSet</code> referred to a <code>Data</code>.
	 *
	 * <p>The string represent all the cluster in the <code>ClusterSet</code> showing the examples in each <code>Cluster</code> referred to the specified data set.
	 *
	 * @param data the <code>Data</code> to refer to.
	 *
	 * @return a string representation of <code>ClusterSet</code>.
	 */
	String toString(Data data){
		String str="";
		for(int i=0;i<C.length;i++){
			if (C[i]!=null){
				str+="cluster"+i+":"+C[i].toString(data)+"\n";
		
			}
		}
		return str;
		
	}

	/**
	 * Creates a new <code>ClusterSet</code> created by merging the clusters in this <code>ClusterSet</code>.
	 *
	 * <p>The cluster to merge is determined utilizing the specified distance method. The examples are compared by referring to those in the specified data set.</p>
	 *
	 * @param distance the <code>ClusterDistance</code> method that will be used for comparison.
	 *
	 * @param data the <code>Data</code> to refer to.
	 *
	 * @return a new <code>ClusterSet</code> created by merging the clusters in this <code>ClusterSet</code>.
	 *
	 * @throws InvalidSizeException if two <code>Example</code> in the <code>ClusterSet</code> have different sizes.
	 *
	 * @throws InvalidDepthException if the <code>Dendrogram</code> depth exceeds the number of examples in the data set.
	 */
	ClusterSet mergeClosestClusters (ClusterDistance distance, Data data)
			throws InvalidDepthException, InvalidSizeException {
		double min = Double.MAX_VALUE;
		Cluster min1 = get(0), min2 = get(0);

		for (int i = 0; i < C.length; i++) {
			Cluster tempCluster = get(i);
			for (int j = i + 1; j < C.length; j++) {
				double tempDistance = distance.distance(tempCluster, get(j), data);
				if (min > tempDistance) {
					min1 = tempCluster; min2 = get(j);
					min = tempDistance;
				}
			}
		}

		Cluster merged = min1.mergeCluster(min2);

		ClusterSet newClusterSet = new ClusterSet(lastClusterIndex - 1);
		for (int i = 0; i < lastClusterIndex; i++) {
			Cluster temp = get(i);
			try {
				if (temp == min1)
					newClusterSet.add(merged);
				else if (temp != min2)
					newClusterSet.add(temp);
			} catch (ArrayIndexOutOfBoundsException exception) {
				throw new InvalidDepthException();
			}
		}

		return newClusterSet;
	}

}
