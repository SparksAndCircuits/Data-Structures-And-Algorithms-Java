package com.learning.dsa.BellmanFordAlgorithm;

import java.util.*;

public class GraphNode implements Comparable<GraphNode> {
    public String name;
    public ArrayList<GraphNode> neighbours = new ArrayList<GraphNode>();
    public HashMap<GraphNode, Integer> weightMap = new HashMap<>();
    public boolean isVisited = false;
    public int distance;
    public int index;
    public Object parent;

    public GraphNode(String name, int index) {
        this.name = name;
        distance = Integer.MAX_VALUE;
        this.index = index;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(GraphNode other) {
        return Integer.compare(this.distance, other.distance);
    }
}
