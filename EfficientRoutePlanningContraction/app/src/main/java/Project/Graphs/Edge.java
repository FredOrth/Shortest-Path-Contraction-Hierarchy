package Project.Graphs;
/******************************************************************************
 *  Compilation:  javac Edge.java
 *  Execution:    java Edge
 *  Dependencies: StdOut.java
 *
 *  Immutable weighted edge.
 *
 ******************************************************************************/

/**
 *  The {@code Edge} class represents a weighted edge in an
 *  {@link EdgeWeightedGraph}. Each edge consists of two integers
 *  (naming the two vertices) and a real-value weight. The data type
 *  provides methods for accessing the two endpoints of the edge and
 *  the weight. The natural order for this data type is by
 *  ascending order of weight.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/43mst">Section 4.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Edge implements Comparable<Edge> {

    private final Vertex vertex1;
    private final Vertex vertex2;
    private double weight;
    private double backupWeight;

    /**
     * Initializes an edge between vertices {@code v} and {@code w} of
     * the given {@code weight}.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @param  weight the weight of this edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *         is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public Edge(Vertex vertex1, Vertex vertex2, double weight) {
        if (vertex1.getVertexIndex() < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (vertex2.getVertexIndex() < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
        this.backupWeight=weight;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double weight() {
        return weight;
    }

    public Vertex either(){
        return vertex1;
    }

    public int eitherInt(){
        return vertex1.getVertexIndex();
    }

    /**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public Vertex other(Vertex vertex) {
        if (vertex.equals(vertex1)) {
            return vertex2;
        } else if (vertex.equals(vertex2)) {
            return vertex1;
        } else {
            throw new IllegalArgumentException("Vertex is not part of this edge");
        }
    }

    // same but for integer index and not vertex object
    public int other(int index) {
        if (vertex1.getVertexIndex() == index) return vertex2.getVertexIndex();
        if (vertex2.getVertexIndex() == index) return vertex1.getVertexIndex();
        throw new IllegalArgumentException("Vertex index not part of this edge");
    }

    /**
     * Returns the endpoint of this edge that is different from the given vertex.
     *
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         endpoints of this edge
     */
    // public int other(int vertex) {
    //     if      (vertex == v) return w;
    //     else if (vertex == w) return v;
    //     else throw new IllegalArgumentException("Illegal endpoint");
    // }

    /**
     * Compares two edges by weight.
     * Note that {@code compareTo()} is not consistent with {@code equals()},
     * which uses the reference equality implementation inherited from {@code Object}.
     *
     * @param  that the other edge
     * @return a negative integer, zero, or positive integer depending on whether
     *         the weight of this is less than, equal to, or greater than the
     *         argument edge
     */
    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("%d-%d %.5f", vertex1, vertex2, weight);
    }

    public void setWeight(double weight){
        this.weight = weight;

    }
    public void restoreWeight(){
        this.weight=backupWeight;
    }

    /**
     * Unit tests the {@code Edge} data type.
     *
     * @param args the command-line arguments
     */
    /* public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67);
        StdOut.println(e);
    } */
}