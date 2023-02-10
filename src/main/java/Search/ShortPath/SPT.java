package Search.ShortPath;

import java.util.HashMap;

public class SPT {
    private final HashMap<String, SPTNode> nodes;
    private final String root;

    public SPT(String root) {
        this.nodes = new HashMap<>();
        this.root = root;
    }

    public HashMap<String, SPTNode> getNodes() {
        return nodes;
    }

    public String getRoot() {
        return root;
    }

    public void add(SPTNode newNode) {
        nodes.put(newNode.getNode(),newNode);
    }

    public String getParent(String node) {
        return nodes.containsKey(node) ? nodes.get(node).getParent() : null;
    }
}
