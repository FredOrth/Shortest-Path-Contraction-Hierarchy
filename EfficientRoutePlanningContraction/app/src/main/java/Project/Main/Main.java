package Project.Main;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import Project.Graphs.GraphBuilderResult;
import Project.Benchmarking.Benchmark;
import Project.Dijkstra.BidirectionalDijkstra;
import Project.Dijkstra.DijkstraUndirectedSP;
import Project.Graphs.GraphBuilder;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

    //     if(args[0].equals("Test")){
    //         InputStream inputStream = Main.class.getResourceAsStream("/denmark.graph");
    //         if (inputStream == null) {
    //             throw new FileNotFoundException("Resource 'denmark.graph' not found in classpath");
    //         }
    //         GraphBuilderResult graphResult = GraphBuilder.buildGraphFromInputStream(inputStream);
    //         Scanner scanner = new Scanner(System.in);
    //         System.out.println("nu");
    //         int startNode = scanner.nextInt();
    //         int endNode = scanner.nextInt();
    //         DijkstraUndirectedSP dijkstra = new DijkstraUndirectedSP(graphResult.getGraph());
    //         dijkstra.computeShortestPath(startNode,endNode);
    //         System.out.println("Test done");
    //         scanner.close();
    //     }

    //     if(args[0].equals("Dijkstra")){
    //         InputStream inputStream = Main.class.getResourceAsStream("/denmark.graph");
    //     if (inputStream == null) {
    //         throw new FileNotFoundException("Resource 'denmark.graph' not found in classpath");
    //     }
    //     GraphBuilderResult graphResult = GraphBuilder.buildGraphFromInputStream(inputStream);
    //     //Take start- and endnode
    //     Scanner scanner = new Scanner(System.in);
    //     for(int i = 0; i<1000; i++){
    //         int startNode = scanner.nextInt();
    //         int endNode = scanner.nextInt();
    //         //start nanoTime
    //         long startTime = System.nanoTime();
            
    //         //Make graph with start and end node
    //         DijkstraUndirectedSP dijkstra = new DijkstraUndirectedSP(graphResult.getGraph());
    //         dijkstra.computeShortestPath(startNode,endNode);
    //         //end nanotime
    //         long endTime = System.nanoTime();

    //         float totalTime = (endTime-startTime)/1_000_000_000.0f;
    //         System.out.println("Djikstra " + dijkstra.getCounterRelaxed() + " " + totalTime);
    //     }
    //     scanner.close();
    //     }
    //     if(args[0].equals("BiDijkstra")){
    //         InputStream inputStream = Main.class.getResourceAsStream("/denmark.graph");
    //     if (inputStream == null) {
    //         throw new FileNotFoundException("Resource 'denmark.graph' not found in classpath");
    //     }
    //     GraphBuilderResult graphResult = GraphBuilder.buildGraphFromInputStream(inputStream);


    //     Scanner scanner = new Scanner(System.in);
    //     for(int i = 0; i<1000; i++){
    //         BidirectionalDijkstra biDjikstra = new BidirectionalDijkstra(graphResult.getGraph());

    //         int startNode = scanner.nextInt();
    //         int endNode = scanner.nextInt();

    //         long startTimeSecond = System.nanoTime();

    //         biDjikstra.computeShortestPath(startNode, endNode);

    //         long endTimeSecond = System.nanoTime();

    //         float totalTimeSecond = (endTimeSecond-startTimeSecond)/1_000_000_000.0f;

    //         System.out.println("DjikstraBirectional " + biDjikstra.getCounterRelaxed() + " " + totalTimeSecond);
    //     }
    //     scanner.close();
    // }

    // if(args[0].equals("BiDijkstraBenchmark")){

        InputStream inputStream = Main.class.getResourceAsStream("/denmark.graph");
        if (inputStream == null) {
            throw new FileNotFoundException("Resource 'denmark.graph' not found in classpath");
        }
        GraphBuilderResult graphResult = GraphBuilder.buildGraphFromInputStream(inputStream);
        
        // Benchmark.Mark8Setup("Bidirectional Dijkstra Benchmark", new BidirectionalDijkstra(graphResult.getGraph()));
        int n = 5;           // Increase the number of repetitions
        double minTime = 10.0; // Increase the minimum time to 1 second

        Benchmark.Mark8Setup("Bidirectional Dijkstra Benchmark", "", new BidirectionalDijkstra(graphResult.getGraph()), n, minTime);
        Benchmark.Mark8Setup("Normal Dijkstra Benchmark", "", new DijkstraUndirectedSP(graphResult.getGraph()), n, minTime);

    // }

        // String filePath = "Efficient Route Planning/app/src/main/resources/denmark.graph";
        // GraphBuilderResult result = GraphBuilder.buildGraphFromFile(filePath);
        // InputStream inputStream = Main.class.getResourceAsStream("/denmark.graph");
        // if (inputStream == null) {
        //     throw new FileNotFoundException("Resource 'denmark.graph' not found in classpath");
        // }
        // GraphBuilderResult graphResult = GraphBuilder.buildGraphFromInputStream(inputStream);
        // System.out.println(graphResult.getLongIntegerMap().size());
        // int startNode = 10;
        // int endNode = 20;
        // DijkstraUndirectedSP dijkstra = new DijkstraUndirectedSP(graphResult.getGraph(),startNode,endNode);
        // System.out.println(dijkstra.getCounterRelaxed());
    }


}
