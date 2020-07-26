package Lesson_7;

public class MainGraph {
    public static void main(String[] args) {
        int size=11;
        Graph graph = new Graph(size);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,4);
        graph.addEdge(4,5);
        graph.addEdge(3,5);
        graph.addEdge(3,6);
        graph.addEdge(6,7);
        graph.addEdge(6,10);
        graph.addEdge(7,8);
        graph.addEdge(8,9);
        System.out.println("Просмотр в глубину");
        DepthFirstPath dfp = new DepthFirstPath(graph,5);
        System.out.println(dfp.pathTo(1));
        System.out.println("Просмотр в ширину");
        BreadthFirstPath bfp = new BreadthFirstPath(graph,5);
        System.out.println(bfp.pathTo(1));
        System.out.println("Длина кратчайшего пути: "+bfp.pathTo(1).size());
    }
}
