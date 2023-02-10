package Search.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class Node {
    protected String node;
    protected HashMap<String,Integer> adjancency;

    public Node(String node) {
        this.node = node;
        adjancency = new HashMap<>();
    }

    public String getNode() {
        return node;
    }

    public HashMap<String, Integer> getAdjancency() {
        return adjancency;
    }

    public void addEdge(String endNamenode,Integer price) {
        adjancency.put(endNamenode, price);
    }

    public Set<String> getAdjacencyList() {
        return adjancency.keySet();
    }

    public LinkedList<Edge> getEdges() {
        LinkedList<Edge> edges = new LinkedList<>();
        for (String endnode : adjancency.keySet()) {
            edges.add(new Edge(node,endnode, adjancency.get(endnode)));
        }

        return edges;
    }
}
