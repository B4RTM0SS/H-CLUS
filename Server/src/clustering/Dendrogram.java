package clustering;

import data.Data;

import java.io.Serializable;

/**
 * Dendrogram is the class that allows to manage a tree of <code>ClusterSet</code>.
 */
class Dendrogram implements Serializable {

    /**
     * ClusterSet of the Dendrogram. Each position of the array refers to a different level of depth of the <code>Dendrogram</code>.
     */
    private ClusterSet[] tree;

    /**
     * Constructs a new <code>Dendrogram</code> of given depth.
     *
     * @param depth the maximum depth of the <code>Dendrogram</code>.
     */
    Dendrogram(int depth) { tree = new ClusterSet [depth]; }

    /**
     * Sets the specified <code>ClusterSet</code> on the specified level of the <code>Dendrogram</code>.
     *
     * @param c the <code>ClusterSet</code> to store.
     *
     * @param level the level in which to store the <code>ClusterSet</code>.
     */
    void setClusterSet(ClusterSet c, int level) {
        tree [level] = c;
    }

    /**
     * Gets the maximum depth of the <code>Dendrogram</code>.
     *
     * @return the maximum depth of the <code>Dendrogram</code>.
     */
    int getDepth() { return tree.length; }

    /**
     * Returns a string representation of <code>Dendrogram</code>.
     *
     * <p>The string shows all the <code>ClusterSet</code> in each level of <code>Dendrogram</code> showing for each one the indexes of the examples in each <code>Cluster</code>.
     *
     * @return a string representation of <code>Dendrogram</code>.
     */
    public String toString() {
        String v="";
        for (int i=0;i<tree.length;i++)
            v+=("level"+i+":\n"+tree[i]+"\n");
        return v;
    }

    /**
     * Returns a string representation of <code>Dendrogram</code> referred to a <code>Data</code>.
     *
     * <p>The string shows all the <code>ClusterSet</code> in each level of <code>Dendrogram</code> showing for each one
     * the examples in each <code>Cluster</code> referred to the specified data set.
     *
     * @param data the <code>Data</code> to refer to.
     *
     * @return a string representation of <code>Dendrogram</code> referred to a <code>Data</code>.
     *
     * @throws DifferentDimensionException if trying to get a string representation of a dendrogram whose depth is
     * greater than the current dataset dimension.
     */
    String toString(Data data) throws DifferentDimensionException {
        String v="";
        for (int i=0;i<tree.length;i++)
            try {
                v += ("level" + i + ":\n" + tree[i].toString(data) + "\n");
            } catch (IndexOutOfBoundsException e) {
                throw new DifferentDimensionException();
            }
        return v;
    }

}
