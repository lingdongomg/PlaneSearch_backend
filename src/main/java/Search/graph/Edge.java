package Search.graph;

public class Edge {
    private final String start;
    private final String end;
    private final Integer price;

    public Edge(String start, String end, int price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getPrice() {
        return price;
    }
}
