package Project.Dijkstra;

import Project.Graphs.EdgeWeightedGraph;

public class ContractionHierarchy {
    private IndexMinPQ<Integer> PQ;
    private EdgeWeightedGraph graph;
    private int lazyCounter;
    private LocalDijkstra3 ld;

    public ContractionHierarchy(EdgeWeightedGraph graph){
        this.graph = graph;
        this.PQ = new IndexMinPQ<>(graph.V());
        this.lazyCounter = 0;
        ld = new LocalDijkstra3(graph);
        createContractionHierarchy();
        lazyUpdate();
    }

    private void createContractionHierarchy(){
        for(int i = 0; i<graph.V(); i++){
            PQ.insert(i, ld.computeEdgeDifference(i));
        }
    }

    private void lazyUpdate(){
        int counter = 0;
        while(!PQ.isEmpty()){
            if(counter == 50){
                //reset PQ
                IndexMinPQ<Integer> newPq = new IndexMinPQ<>(graph.V());
                PQ = newPq;
                createContractionHierarchy();
            }
            int leastNode = PQ.delMin();
            int nodeDifference = ld.computeEdgeDifference(leastNode);
            if(nodeDifference <= PQ.minKey()){
                //Write method with with leastNode and nodeDifference
            }else{
                PQ.insert(leastNode, nodeDifference);
                counter++;
            }
        }
    }
}

