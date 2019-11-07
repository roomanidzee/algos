package com.romanidze.algos.sixth;

import com.romanidze.algos.sixth.kruskal.Edge;
import com.romanidze.algos.sixth.kruskal.Graph;

import java.util.List;
/**
 * 07.11.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class KruskalLauncher {

    public static void main(String[] args) {

        int vertices = 6;
        Graph graph = new Graph(vertices);

        List<Edge> edges = List.of(
                new Edge(0, 1, 4),
                new Edge(0, 2, 3),
                new Edge(1, 2, 1),
                new Edge(1, 3, 2),
                new Edge(2, 3, 4),
                new Edge(3, 4, 2),
                new Edge(4, 5, 6)
        );

        edges.forEach(edge -> graph.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight()));

        graph.kruskalSolve();
    }
}
