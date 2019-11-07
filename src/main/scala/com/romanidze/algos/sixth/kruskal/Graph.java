package com.romanidze.algos.sixth.kruskal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * 07.11.2019
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public class Graph {
    private int vertices;
    private ArrayList<Edge> allEdges = new ArrayList<>();

    public Graph(int vertices) {
        this.vertices = vertices;
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        allEdges.add(edge);
    }

    public void kruskalSolve() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(Edge::getWeight));

        pq.addAll(allEdges);

        int[] parent = new int[vertices];
        makeSet(parent);

        ArrayList<Edge> mst = new ArrayList<>();

        int index = 0;

        while (index < vertices - 1) {
            Edge edge = pq.remove();

            int xSet = find(parent, edge.getSource());
            int ySet = find(parent, edge.getDestination());

            if (xSet != ySet) {
                mst.add(edge);
                index++;
                union(parent, xSet, ySet);
            }
        }
        printGraph(mst);
    }

    private void makeSet(int[] parent) {
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }
    }

    private int find(int[] parent, int vertex) {

        if (parent[vertex] != vertex) {
            return find(parent, parent[vertex]);
        }

        return vertex;
    }

    private void union(int[] parent, int x, int y) {
        int xSet = find(parent, x);
        int ySet = find(parent, y);
        parent[ySet] = xSet;
    }

    private void printGraph(ArrayList<Edge> edgeList) {
        IntStream.range(0, edgeList.size()).forEachOrdered(i -> {
            Edge edge = edgeList.get(i);
            System.out.println("Edge-" + i + " source: " + edge.getSource() +
                    " destination: " + edge.getDestination() +
                    " weight: " + edge.getWeight());
        });
    }
}
