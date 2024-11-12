package Project.Dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import Project.Graphs.Bag;
import Project.Graphs.Edge;
import Project.Graphs.EdgeWeightedGraph;

public class LocalDijkstra4 {
    private HashMap<Integer, Integer> distTo;          // distTo[v] = distance  of shortest s->v path
    private IndexMinPQ<Integer> pq;    // priority queue of vertices
    private EdgeWeightedGraph G;
    private HashSet<Integer> visitedNodes;
    private int s;

    public LocalDijkstra4(EdgeWeightedGraph G){
        this.G = G;
        distTo = new HashMap<>();
        pq = new IndexMinPQ<>(G.V());
    }

    public int computeEdgeDifference(int s,boolean insertEdges){
        this.s = s;
        int counter = 0;

        //Neighbouring nodes to s (start node)
        Bag<Edge> initialBag = G.adjacentEdges(s);

        HashSet<Integer> visitedEndNodes = new HashSet<>();
        visitedNodes = new HashSet<>();

        for(Edge edge : initialBag){
            int startNode = edge.other(s);
            distTo.put(startNode, 0);
            int nodeCounter = 0;

            //The end nodes that has been visited
            visitedEndNodes.add(startNode);

            //Clear visited nodes and add startNode
            visitedNodes.clear();
            visitedNodes.add(startNode);

            //Find the highest value between s and node we are searching for
            int highestValue = getHighestValue(initialBag,edge);

            HashSet<Integer> endNodes = initializeSet(initialBag, startNode, visitedEndNodes);
            fillMinPq(startNode);



            while(nodeCounter <50 && !endNodes.isEmpty() && !pq.isEmpty()){
                //Find least node by deleting from Min pq
                int leastNode = pq.delMin();
                
                //Add to visited nodes
                visitedNodes.add(leastNode);

                //Check if the path just found is more expensive than highest value and break if so
                if(distTo.get(leastNode) > highestValue){
                    int plus = endNodes.size();

                        if (insertEdges) {
                            Iterator<Integer> iterator = endNodes.iterator();
                            while (iterator.hasNext()) {

                                Integer neighbor = iterator.next();

                                // Remove the current element using the iterator's remove method
                                iterator.remove();

                                // Calculate the shortcut weight
                                int shortcutWeight = edge.weight() + findEdge(initialBag, neighbor).weight();

                                Edge shortCut = new Edge(startNode, neighbor, shortcutWeight);

                                // Add the shortcut edge to the graph
                                G.addEdge(shortCut); // 's' as the contracted node label
                                String edgeString = startNode + " " + neighbor + " " + shortcutWeight;
                                G.writeEdge(edgeString);

                            }
                            
                        }
                


                    counter = counter + plus;
                    break;
                }

                //Check if node found is an endnode
                if(endNodes.contains(leastNode)){
                    endNodes.remove(leastNode);
                    //If path is greater than weight path going through s increment counter
                    if(distTo.get(leastNode) > edge.weight() + findEdge(initialBag, leastNode).weight()){
                        
                        
                        // i edit between these two 1)
                        if(insertEdges){
                            //PHYSICALLY ADD EDGE HERE, but between what?
                            int shortcutWeight = edge.weight() + findEdge(initialBag, leastNode).weight();
                            Edge shortCut = new Edge(startNode, leastNode, shortcutWeight);
                            G.addEdge(shortCut);
                            String edgeString = startNode + " " + leastNode + " " + shortcutWeight;
                            G.writeEdge(edgeString);

                        }

                        // i edit between these two 2)//

                    counter++;
                }
                    nodeCounter++;
                }
                //Fill minpq from least node
                fillMinPq(leastNode);
            }
            reset();
        }
        //return
        return counter-initialBag.size(); 
    }

    //finds edge based on node n
    private Edge findEdge(Bag<Edge> bag , int n){
        for(Edge edge : bag){
            if(edge.other(s) == n){
                return edge;
            }
        }
        return null;
    }

    private int getHighestValue(Bag<Edge> bag, Edge edge){
        int value = 0;
        for(Edge edge2 : bag){
            if(!edge2.equals(edge)){
                int potentialHighestValue = edge.weight() + edge2.weight();
                if(value < potentialHighestValue){
                    value = potentialHighestValue;
                }
            }
        }
        return value;
    }

     //This will reset all collections
     private void reset(){
        distTo.clear();  
        while(!pq.isEmpty()){
            pq.delMin();
        } 
        //Maybe change to java util pq
        visitedNodes.clear();
        }

    private HashSet<Integer> initializeSet(Bag<Edge> bag, int node, HashSet<Integer> visitedEndNodes){
        HashSet<Integer> set = new HashSet<>();
        for(Edge edge : bag){
            int otherNode = edge.other(s);
            if(!visitedEndNodes.contains(otherNode) && otherNode != node){
                set.add(otherNode);
            }
        }
        return set;
    }

    private void fillMinPq(int node){
        Bag<Edge> bag = G.adjacentEdges(node);
            for(Edge edge2 : bag){
                if(!visitedNodes.contains(edge2.other(node)) && edge2.other(node) != s){
                //If value is contained in the distTo we only decrease it if a new shorter path is found
                if(pq.contains(edge2.other(node))){
                    int weight = distTo.get(node) + edge2.weight();
                    if(weight < distTo.get(edge2.other(node))){
                        distTo.put(edge2.other(node), weight);
                        pq.decreaseKey(edge2.other(node), weight);
                    }
                }
                    else{
                        int weight = distTo.get(node) + edge2.weight();
                        distTo.put(edge2.other(node), weight);
                        pq.insert(edge2.other(node), weight);
                    }
                }
                
            }
    }
}
