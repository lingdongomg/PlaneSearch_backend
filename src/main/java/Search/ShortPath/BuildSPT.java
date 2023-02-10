package Search.ShortPath;

import Search.graph.Graph;
import Search.graph.Node;

import java.util.HashMap;
import java.util.PriorityQueue;

public final class BuildSPT {

    public static SPT buildSPT(Graph graph, String start) {
        HashMap<String, Node> nodes = graph.getNodes();
        SPT predecessorTree = new SPT(start);
        PriorityQueue<SPTNode> pq = new PriorityQueue<>();
        for (String nodename:nodes.keySet()) {
            SPTNode newNode = new SPTNode(nodename);
            newNode.setDist(Integer.MAX_VALUE);
            newNode.setDepth(Integer.MAX_VALUE);
            predecessorTree.add(newNode);
        }
        SPTNode rootnode = predecessorTree.getNodes().get(predecessorTree.getRoot());
        rootnode.setDist(0);
        rootnode.setDepth(0);
        pq.add(rootnode);

        while (!pq.isEmpty()) {
            SPTNode current = pq.poll();
            String node = current.getNode();
            HashMap<String, Integer> neighbors = nodes.get(node).getAdjancency();
            for (String s:neighbors.keySet()) {
                if (current.getDist() + nodes.get(node).getAdjancency().get(s) < predecessorTree.getNodes().get(s).getDist()) {
                    SPTNode dijkstraNode = predecessorTree.getNodes().get(s);

                    pq.remove(dijkstraNode);
                    dijkstraNode.setDist(current.getDist() + nodes.get(node).getAdjancency().get(s));
                    dijkstraNode.setDepth(current.getDepth() + 1);
                    dijkstraNode.setParent(node);
                    pq.add(dijkstraNode);
                }
            }
        }

        return predecessorTree;
    }
}
