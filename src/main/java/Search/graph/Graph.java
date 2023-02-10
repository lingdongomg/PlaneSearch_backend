package Search.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Graph {
    private final HashMap<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public Graph(String filename) {
        this();
        buildGraph(filename);
    }

    public Graph(HashMap<String, Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(String node) {
        if (!nodes.containsKey(node))
            nodes.put(node, new Node(node));
    }


    public void addEdge(String node1, String node2, Integer price) {
        if (!nodes.containsKey(node1))
            addNode(node1);
        if (!nodes.containsKey(node2))
            addNode(node2);
        nodes.get(node1).addEdge(node2, price);
    }

    public HashMap<String, Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdgeList() {
        List<Edge> list = new LinkedList<>();

        for (Node node : nodes.values()) {
            list.addAll(node.getEdges());
        }

        return list;
    }

    public Node getNode(String label) {
        return nodes.get(label);
    }


    public Graph reverse() {
        HashMap<String, Node> reverseGraph = new HashMap<>();

        for (String s : nodes.keySet()) {
            reverseGraph.put(s, new Node(s));
        }

        for (String nodename : nodes.keySet()) {
            Node node = nodes.get(nodename);
            Set<String> adjacencyList = node.getAdjacencyList();
            HashMap<String, Integer> neighbors = node.getAdjancency();
            for (String nodename2 : adjacencyList) {
                reverseGraph.get(nodename2).addEdge(nodename, neighbors.get(nodename2));
            }
        }

        return new Graph(reverseGraph);
    }

    public void buildGraph(String fileName) {
        String[] split = fileName.split("\n");
        for (String s : split) {
            String[] split1 = s.split("\\s");
            if (split1.length == 3) {
                addEdge(split1[0], split1[1], Integer.parseInt(split1[2]));
            }
        }
    }
}
