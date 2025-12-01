package com.learning.dsa.BellmanFordAlgorithm;

import java.util.*;

public class Graph {
    ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();

    public Graph(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
    }

    public void addUndirectedEdge(int i, int j) {
        GraphNode first = nodeList.get(i);
        GraphNode second = nodeList.get(j);

        first.neighbours.add(second);
        second.neighbours.add(first);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + ":");

            for (int j = 0; j < nodeList.get(i).neighbours.size(); j++) {
                if (j == nodeList.get(i).neighbours.size() - 1) {
                    s.append((nodeList.get(i).neighbours.get(j).name));
                } else {
                    s.append((nodeList.get(i).neighbours.get(j).name));
                }
            }

            s.append("\n");
        }

        return s.toString();
    }

    void bfsVisit(GraphNode node) {
        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            GraphNode currentNode = queue.remove();
            currentNode.isVisited = true;
            System.out.println(currentNode.name + " ");

            for (GraphNode neighbour : currentNode.neighbours) {
                if (!neighbour.isVisited) {
                    queue.add(neighbour);
                    neighbour.isVisited = true;
                }
            }
        }
    }

    void bfs() {
        for (GraphNode node : nodeList) {
            if (!node.isVisited) {
                bfsVisit(node);
            }
        }
    }

    void dfsVisit(GraphNode node) {
        Stack<GraphNode> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            GraphNode currentNode = stack.pop();
            currentNode.isVisited = true;
            System.out.println(currentNode.name + " ");

            for (GraphNode neighbour : currentNode.neighbours) {
                if (!neighbour.isVisited) {
                    stack.push(neighbour);
                    neighbour.isVisited = true;
                }
            }
        }
    }

    void dfs() {
        for (GraphNode node : nodeList) {
            if (!node.isVisited) {
                dfsVisit(node);
            }
        }
    }

    void dijkstra(GraphNode node) {
        PriorityQueue<GraphNode> queue = new PriorityQueue<>();
        node.distance = 0;
        queue.addAll(nodeList);

        while (!queue.isEmpty()) {
            GraphNode currentNode = queue.remove();

            for (GraphNode neighbour : currentNode.neighbours) {
                if (queue.contains(neighbour)) {
                    if (neighbour.distance > currentNode.distance + currentNode.weightMap.get(neighbour)) {
                        neighbour.distance = (currentNode.distance + currentNode.weightMap.get(neighbour));
                        neighbour.parent = currentNode;
                        queue.remove(neighbour);
                        queue.add(neighbour);
                    }
                }
            }
        }

        for (GraphNode nodeToCheck : nodeList) {
            System.out.println("Node " + nodeToCheck + ", distance: " + nodeToCheck.distance + ", Path: ");
            printPath(nodeToCheck);
            System.out.println();
        }
    }

    public static void printPath(GraphNode node) {
        if (node.parent != null) {
            printPath((GraphNode) node.parent);
        }

        System.out.println(node.name + " ");
    }

    public void addWeightedEdge(int i, int j, int d) {
        GraphNode first = nodeList.get(i);
        GraphNode second = nodeList.get(j);

        first.neighbours.add(second);
        first.weightMap.put(second, d);
    }

    void bellmanFord(GraphNode sourceNode) {
        sourceNode.distance = 0;

        for (int i = 0; i < nodeList.size(); i++) {
            for (GraphNode currentNode : nodeList) {
                for (GraphNode neighbour : currentNode.neighbours) {
                    if (neighbour.distance > currentNode.distance + currentNode.weightMap.get(neighbour)) {
                        neighbour.distance = (currentNode.distance + currentNode.weightMap.get(neighbour));
                        neighbour.parent = currentNode;
                    }
                }
            }
        }

        System.out.println("Checking for negative cycles.");

        for (GraphNode currentNode : nodeList) {
            for (GraphNode neighbour : currentNode.neighbours) {
                if (neighbour.distance > currentNode.distance + currentNode.weightMap.get(neighbour)) {
                    System.out.println("Negative cycles found: \n");
                    System.out.println("Vertex name: 9 neighbour.name");
                    System.out.println("Old cost: " + neighbour.distance);
                    int newDistance = currentNode.distance + currentNode.weightMap.get(neighbour);
                    System.out.println("New cost: " + newDistance);
                    return;
                }
            }
        }

        System.out.println("Negative cycle not found");

        for (GraphNode nodeToCheck : nodeList) {
            System.out.println("Node " + nodeToCheck + " , distance" + nodeToCheck.distance + ", Path: ");
            printPath(nodeToCheck);
            System.out.println();
        }
    }
}