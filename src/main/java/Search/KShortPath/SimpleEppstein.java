package Search.KShortPath;

import Bean.ServiceBean;
import Bean.ServicePlane;
import Search.ShortPath.BuildSPT;
import Search.ShortPath.Path;
import Search.ShortPath.SPT;
import Search.graph.Edge;
import Search.graph.Graph;

import java.util.*;


public class SimpleEppstein {
    /**
     * 计算k短路
     *
     * @param graph     图
     * @param startnode 起点
     * @param endnode   终点
     * @param K         k
     * @return k短路列表
     */
    public List<ServiceBean> ksp(Graph graph, String startnode, String endnode, int K) {
        // 构造返图，建立最短路树
        SPT SPT = BuildSPT.buildSPT(graph.reverse(), endnode);

        // 计算不在SPTtree中的边的代价
        HashMap<String, Integer> sidetrackEdgeCostMap = sidetrackCost(graph, SPT);

        // 记录结果
        ArrayList<Path> anspath = new ArrayList<>();
        PriorityQueue<ImplicitPath> path = new PriorityQueue<>();
        List<ServiceBean> planes = new ArrayList<>();
        Map<String, Integer> planemap = new HashMap<>();

        // 将最短路作为初始条件
        path.add(new ImplicitPath(new Edge(null, startnode, 0), -1, SPT.getNodes().get(startnode).getDist()));

        // 循环k次找到k条路径
        for (int i = 0; planes.size() <= K && path.size() > 0; i++) {
            // 队头出队
            ImplicitPath kpathImplicit = path.poll();

            Path path1 = kpathImplicit.explicitPath(anspath, SPT);
            // 将隐式路径转换为显示
            anspath.add(path1);
            pushQueue(graph, sidetrackEdgeCostMap, kpathImplicit, i, path);

            if (path1.getSumPrice() > 99999) continue;

            String[] split = path1.getPath().split(",");
            StringBuilder stringBuilder = new StringBuilder();
            String agencies = null;
            for (String s : split) {
                if (s.length() < 10) continue;
                agencies = s.substring(s.length() - 6);
                stringBuilder.append(s, 0, 31);
            }
            Integer orDefault = planemap.getOrDefault(stringBuilder.toString(), -1);
            if (orDefault == -1) {
                planemap.put(stringBuilder.toString(), planes.size());
                ServiceBean serviceBean = new ServiceBean(path1.getSumPrice());
                serviceBean.addAgencies(agencies);
                for (String s : split) {
                    if (s.length() < 10) continue;
                    ServicePlane plane = new ServicePlane(
                            s.substring(1, 3),
                            Integer.parseInt(s.substring(3, 7)),
                            Long.parseLong(s.substring(7, 19)),
                            Long.parseLong(s.substring(19, 31)),
                            s.substring(31, s.length() - 6));
                    serviceBean.addPlane(plane);
                }
                planes.add(serviceBean);
            } else {
                ServiceBean userPlane = planes.get(orDefault);
                userPlane.addAgencies(agencies);
                planes.set(orDefault, userPlane);
            }
        }

        // 返回k条最短路径的集合
        return planes;
    }

    /**
     * 计算所有没在tree中出现的边的代价
     * <p>
     * 任意一条边(u,v)的代价S(u,v) = price(u,v) + dist(v) - dist(u)
     * price(u,v)为边 u->v 的权重
     * d(v)为数中以v为出边的边从权重
     *
     * @param graph 图
     * @param tree  最短路径树
     */
    private HashMap<String, Integer> sidetrackCost(Graph graph, SPT tree) {
        HashMap<String, Integer> costmap = new HashMap<>();
        // 获取图中所有的边
        List<Edge> edgelist = graph.getEdgeList();
        // 遍历所有边
        for (Edge edge : edgelist) {
            String treeparent = tree.getParent(edge.getStart());
            // 如果从当前边出发不能到终点，则更新代价S(u,v) = w(u,v) + d(v) - d(u)
            if (treeparent == null || !treeparent.equals(edge.getEnd())) {
                int cost = edge.getPrice()
                        + tree.getNodes().get(edge.getEnd()).getDist()
                        - tree.getNodes().get(edge.getStart()).getDist();
                costmap.put(edge.getStart() + "," + edge.getEnd(), cost);
            }
        }

        return costmap;
    }

    /**
     * 将路径的子节点入队
     *
     * @param sidetrackMap 所有不在tree中的边的代价的字典
     * @param path         路径的隐式表示
     * @param k            路径是第几个
     * @param queue        优先队列
     */
    private void pushQueue(Graph graph, HashMap<String, Integer> sidetrackMap, ImplicitPath path, int k, PriorityQueue<ImplicitPath> queue) {
        // 初始化DFS的堆栈
        Stack<Edge> stack = new Stack<>();

        // 将传入的KpathImplicit的所有end为start的所有边加入到栈中
        // 例如传入的是A->B，则将所有以B为出边的边加入到栈中
        for (Edge edge : graph.getNode(path.getSidetrackEdge().getEnd()).getEdges()) {
            stack.push(edge);
        }

        // 当栈非空
        while (!stack.empty()) {
            Edge edge = stack.pop();
            String s = edge.getStart() + "," + edge.getEnd();

            // 如果当前sidetrackMap中有这条边
            if (sidetrackMap.containsKey(s)) {
                queue.add(new ImplicitPath(edge, k, path.getPrice() + sidetrackMap.get(s)));
            } else {
                for (Edge edge1 : graph.getNode(edge.getEnd()).getEdges()) {
                    stack.push(edge1);
                }
            }
        }
    }
}

