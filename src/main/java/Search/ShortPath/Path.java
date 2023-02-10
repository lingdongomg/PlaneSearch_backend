package Search.ShortPath;

import Search.graph.Edge;

import java.util.LinkedList;

public class Path {
    private final LinkedList<Edge> edges;
    private Integer sumPrice;

    public Path() {
        edges = new LinkedList<>();
        sumPrice = 0;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public Integer getSumPrice() {
        return sumPrice;
    }

    public void add(Edge edge) {
        edges.add(edge);
        sumPrice += edge.getPrice();
    }

    public int size() {
        return edges.size();
    }

    public String getPath() {
        StringBuilder s = new StringBuilder();
        if (this.edges.size() > 0) {
            for (Edge edge : edges) {
                s.append(edge.getStart()).append(",");
            }
            s.append(edges.getLast().getEnd());
        }
        return s.toString();
    }
}
