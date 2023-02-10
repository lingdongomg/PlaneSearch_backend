package Search.KShortPath;

import Search.ShortPath.Path;
import Search.ShortPath.SPT;
import Search.graph.Edge;

import java.util.LinkedList;
import java.util.List;


public class ImplicitPath implements Comparable<ImplicitPath> {
    private final Edge sidetrackEdge;
    private final Integer parentPath;
    private final Integer price;

    public ImplicitPath(Edge sidetrackEdge, int parentPath, Integer price) {
        this.sidetrackEdge = sidetrackEdge;
        this.parentPath = parentPath;
        this.price = price;
    }

    public Edge getSidetrackEdge() {
        return sidetrackEdge;
    }

    public Integer getPrice() {
        return price;
    }

    // 将路径的隐式表示转换为显示，路径被分为Start->B，B->C,C->End两部分
    public Path explicitPath(List<Path> ksp, SPT tree) {
        Path path = new Path();

        // 如果路径不是图中的最短路径
        if (parentPath >= 0) {
            // 确定路径的Start->B部分
            LinkedList<Edge> edges = ksp.get(parentPath).getEdges();
            int lastEdgeNum = -1;
            for (int i = edges.size() - 1; i >= 0; i--) {
                if (edges.get(i).getEnd().equals(sidetrackEdge.getStart())) {
                    lastEdgeNum = i;
                    break;
                }
            }

            // 将路径的Start->B添加到最后的结果中
            for (int i = 0; i <= lastEdgeNum; i++) {
                path.add(edges.get(i));
            }

            // 将路径的B->C部分添加到最后结果中
            path.add(sidetrackEdge);
        }

        // 以C为起点在tree中寻找能够到达终点的最短路径，然后将其添加到结果中
        String current = sidetrackEdge.getEnd();
        while (!current.equals(tree.getRoot())) {
            String next = tree.getParent(current);
            int edgeWeight = tree.getNodes().get(current).getDist() - tree.getNodes().get(next).getDist();
            path.add(new Edge(current, next, edgeWeight));
            current = next;
        }

        return path;
    }

    public int compareTo(ImplicitPath comparedNode) {
        return Integer.compare(this.price, comparedNode.getPrice());
    }
}
