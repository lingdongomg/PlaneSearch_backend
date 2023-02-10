package Search.ShortPath;

import Search.graph.Node;

import java.util.HashMap;

public class SPTNode extends Node implements Comparable<SPTNode> {
    private Integer dist;
    private Integer depth;

    public SPTNode(String name) {
        super(name);
        this.dist = 0;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setParent(String parent) {
        super.adjancency = new HashMap<>();
        super.adjancency.put(parent,0);
    }

    public String getParent() {
        return super.adjancency.keySet().size() == 1 ? super.adjancency.keySet().iterator().next() : null;
    }

    public int compareTo(SPTNode comparedNode) {
        return Integer.compare(this.dist, comparedNode.getDist());
    }
}
