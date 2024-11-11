package Project.Main;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class SecondMain {

    public static void main(String[] args)throws FileNotFoundException {
        
    HashMap<Double,Integer> map = new HashMap<>();

    Graph<Integer, DefaultWeightedEdge> graph = 
                new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

                InputStream inputStreamTest = Main.class.getResourceAsStream("/denmark.graph"); ///Small_graph_for_test.graph
                if (inputStreamTest == null) {
                    throw new FileNotFoundException("Resource 'Small_graph_for_test.graph' not found in classpath");
                }
                Scanner scanner = new Scanner(new InputStreamReader(inputStreamTest));
                int nodes = scanner.nextInt();
                int edges = scanner.nextInt();

                for(int i = 0; i<nodes; i++){
                    graph.addVertex(i);
                    String[] array = scanner.nextLine().split(" ");
                    map.put(Double.parseDouble(array[0]), i);
                    
                }

                for(int i = 0; i<edges; i++){
                    String[] array = scanner.nextLine().split(" ");
                    int source = Integer.parseInt(array[0]);
                    int target = Integer.parseInt(array[1]);
                    int weight = Integer.parseInt(array[2]);

                    DefaultWeightedEdge edge = graph.addEdge(source, target);
                    graph.setEdgeWeight(edge, weight);
                }




            }
}
