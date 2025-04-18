
/*
 * This source file was generated by the Gradle 'init' task
 */
package Testing;



import Project.Graphs.EdgeWeightedGraph;
import Project.Graphs.GraphBuilder;

import Project.Dijkstra.BidirectionalDijkstra;
import Project.Dijkstra.DijkstraUndirectedSP;
import Project.Graphs.*;


import org.junit.Assert.*;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.net.URL;

import static org.junit.Assert.*;


public class SmallGraphTesting {

    private static EdgeWeightedGraph graph;


    

    @BeforeClass // Use @BeforeClass if you want to initialize the graph once for all tests
    public static void graphInit() throws FileNotFoundException {
        InputStream inputStream = SmallGraphTesting.class.getResourceAsStream("/Small_graph_for_test.graph");
        if (inputStream == null) {
            throw new FileNotFoundException("Resource 'Small_graph_for_test.graph' not found in classpath");
        }
        graph = GraphBuilder.buildGraphFromInputStream(inputStream);
    }

    @Test
    public void checkSmallGraphVertexes() {

        // Check correct number of vertexes
        assertEquals("Graph should have 20 vertexes", 20, graph.V());

        // Check whether vertex 1000 is not null and has the right longitude/latitude
        float coordinateX = graph.getVertexById(1000L).getCoordinateX();
        float coordinateY = graph.getVertexById(1000L).getCoordinateY();
        assertEquals("Longitude of vertex 1000 should be 1.0", 1.0, coordinateX, 0.01);
        assertEquals("Latitude of vertex 1000 should be 2.0", 2.0, coordinateY, 0.01);

        // Check whether vertex 1018 is not null and has the right longitude/latitude
        float coordinateX2 = graph.getVertexById(1018).getCoordinateX();
        float coordinateY2 = graph.getVertexById(1018).getCoordinateY();
        
        assertEquals("Longitude of vertex 1018 should be 19.1", 19.1, coordinateX2, 0.01);
        assertEquals("Latitude of vertex 1018 should be 20.0", 20.0, coordinateY2, 0.01);
    }

    /* 
     * This tests checks whether the small graph from Small_graph_for_test
     * is generated correctly with:
     * 25 edges and their weights.
     * We furthermore check 4 edges if they're instantiated correctly
     */
    @Test
    public void checkSmallGraphEdges(){
        

        // Check correct number of edges
        assertEquals(graph.E(),25);

        // Check degree for arbitrarily chosen vertex
        


        // Check for edge between vertex 1000 (id 0) and vertex 1001 (id 1) with weight 1.0
        boolean edge1000_1001_Found = false;
        for (Edge e : graph.edges()) {
            Vertex vertex = e.either();
            int v = vertex.getVertexIndex();
            int w = e.other(v);
            if ((v == 0 && w == 1 && e.weight() == 1) || (v == 1 && w == 0 && e.weight() == 1)) {
                edge1000_1001_Found = true;
                break;
            }
        }
        assertTrue("Edge between vertex 1000 and vertex 1001 with weight 1 should exist",edge1000_1001_Found);

        // Check for edge between vertex 1000 (id 0) and vertex 1002 (id 2) with weight 2.0
        boolean edge1000_1002_Found = false;
        for (Edge e : graph.edges()) {
            Vertex vertex = e.either();
            int v = vertex.getVertexIndex();
            int w = e.other(v);
            if ((v == 0 && w == 2 && e.weight() == 2) || (v == 2 && w == 0 && e.weight() == 2)) {
                edge1000_1002_Found = true;
                break;
            }
        }
        assertTrue("Edge between vertex 1000 and vertex 1002 with weight 2 should exist",edge1000_1002_Found);

        // Check for edge between vertex 1001 (id 1) and vertex 1003 (id 3) with weight 1.0
        boolean edge1001_1003_Found = false;
        for (Edge e : graph.edges()) {
            Vertex vertex = e.either();
            int v = vertex.getVertexIndex();
            int w = e.other(v);
            if ((v == 1 && w == 3 && e.weight() == 1) || (v == 3 && w == 1 && e.weight() == 1)) {
                edge1001_1003_Found = true;
                break;
            }
        }
        assertTrue("Edge between vertex 1001 and vertex 1003 with weight 1 should exist",edge1001_1003_Found);

        // Check for edge between vertex 1003 (id 3) and vertex 1005 (id 5) with weight 2.0
        boolean edge1003_1005_Found = false;
        for (Edge e : graph.edges()) {
            Vertex vertex = e.either();
            int v = vertex.getVertexIndex();
            int w = e.other(v);
            if ((v == 3 && w == 5 && e.weight() == 2) || (v == 5 && w == 3 && e.weight() == 2)) {
                edge1003_1005_Found = true;
                break;
            }
        }
        assertTrue("Edge between vertex 1003 and vertex 1005 with weight 2 should exist", edge1003_1005_Found);
    }

    @Test
    public void testEarlyStoppingCriteria(){
        assertNotNull(graph);
        DijkstraUndirectedSP dijkstrastoppingPoint = new DijkstraUndirectedSP(graph);
        double distance = dijkstrastoppingPoint.computeShortestPath(0, 10);
        assertEquals(9.0, distance,0.0001);
    }

    // Test for random vertexes that our bidirectional dijkstra works as intended.
    // @Test
    // public void testBidirectionalDijkstra(){
    //     EdgeWeightedGraph graph = smallGraph.getGraph();
    //     BidirectionalDijkstra dijkstrastoppingPoint = new BidirectionalDijkstra(graph);
    //     double distance = dijkstrastoppingPoint.computeShortestPath(0, 10);
    //     assertEquals(9.0, distance,0.0001);

    // }



}
