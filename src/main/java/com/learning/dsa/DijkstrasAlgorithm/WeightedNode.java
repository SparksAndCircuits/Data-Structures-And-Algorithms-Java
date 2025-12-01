package com.learning.dsa.DijkstrasAlgorithm;

import java.util.*;

public class WeightedNode implements Comparable<WeightedNode> {
    public String name;
    public ArrayList<WeightedNode> neighbours = new ArrayList<>();
    public HashMap<WeightedNode, Integer> weightMap = new HashMap<>();
    public boolean isVisited = false;
    public WeightedNode parents;
    public int distance;
    public int index;
    public Object parent;

    public WeightedNode(String name, int index) {
        this.name = name;
        distance = Integer.MAX_VALUE;
        this.index = index;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(WeightedNode other) {
        return this.distance = other.distance;
    }
}
