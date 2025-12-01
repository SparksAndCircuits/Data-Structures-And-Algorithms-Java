package com.learning.dsa.BellmanFordAlgorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class GraphTest {

    private Graph graph;
    private ArrayList<GraphNode> nodeList;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        nodeList = new ArrayList<>();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Test Bellman-ford with positive weights")
    void testBellmanFordPositiveWeights() {

        for (int i = 0; i < 5; i++) {
            nodeList.add(new GraphNode("Node" + i, i));
        }

        graph = new Graph(nodeList);

        graph.addWeightedEdge(0, 1, 6);
        graph.addWeightedEdge(0, 3, 7);
        graph.addWeightedEdge(1, 2, 5);
        graph.addWeightedEdge(1, 3, 8);
        graph.addWeightedEdge(1, 4, -4);
        graph.addWeightedEdge(2, 1, -2);
        graph.addWeightedEdge(3, 2, -3);
        graph.addWeightedEdge(3, 4, 9);
        graph.addWeightedEdge(4, 0, 2);
        graph.addWeightedEdge(4, 2, 7);

        graph.bellmanFord(nodeList.get(0));

        assertEquals(0, nodeList.get(0).distance, "Source node should have distance 0");
        assertEquals(2, nodeList.get(1).distance, "Node1 should have distance 2");
        assertEquals(4, nodeList.get(2).distance, "Node2 should have distance 4");
        assertEquals(7, nodeList.get(3).distance, "Node3 should have distance 7");
        assertEquals(-2, nodeList.get(4).distance, "Node4 should have distance -2");

        String output = outputStream.toString();
        assertTrue(output.contains("Negative cycle not found"), "Should confirm no negative cycle exists");
    }

    @Test
    @DisplayName("Test Bellman-Ford with simple linear path")
    void testBellmanFordSimplePath() {
        // Create 3 nodes in a line
        for (int i = 0; i < 3; i++) {
            nodeList.add(new GraphNode("Node" + i, i));
        }
        
        graph = new Graph(nodeList);
        
        graph.addWeightedEdge(0, 1, 5);
        graph.addWeightedEdge(1, 2, 3);
        
        graph.bellmanFord(nodeList.get(0));
        
        assertEquals(0, nodeList.get(0).distance);
        assertEquals(5, nodeList.get(1).distance);
        assertEquals(8, nodeList.get(2).distance);
    }
    
    @Test
    @DisplayName("Test Bellman-Ford detects negative cycle")
    void testBellmanFordNegativeCycle() {
        // Create nodes with a negative cycle
        for (int i = 0; i < 3; i++) {
            nodeList.add(new GraphNode("Node" + i, i));
        }
        
        graph = new Graph(nodeList);
        
        graph.addWeightedEdge(0, 1, 1);
        graph.addWeightedEdge(1, 2, -3);
        graph.addWeightedEdge(2, 0, 1);
        
        graph.bellmanFord(nodeList.get(0));
        
        String output = outputStream.toString();
        assertTrue(output.contains("Negative cycles found"), 
            "Should detect negative cycle");
    }
    
    @Test
    @DisplayName("Test BFS traversal")
    void testBFS() {
        for (int i = 0; i < 4; i++) {
            nodeList.add(new GraphNode("Node" + i, i));
        }
        
        graph = new Graph(nodeList);
        
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(1, 3);
        
        graph.bfs();
        
        // Verify all nodes are visited
        for (GraphNode node : nodeList) {
            assertTrue(node.isVisited, "Node " + node.name + " should be visited");
        }
    }
    
    @Test
    @DisplayName("Test DFS traversal")
    void testDFS() {
        for (int i = 0; i < 4; i++) {
            nodeList.add(new GraphNode("Node" + i, i));
        }
        
        graph = new Graph(nodeList);
        
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(1, 3);
        
        graph.dfs();
        
        // Verify all nodes are visited
        for (GraphNode node : nodeList) {
            assertTrue(node.isVisited, "Node " + node.name + " should be visited");
        }
    }
    
    @Test
    @DisplayName("Test Dijkstra's algorithm")
    void testDijkstra() {
        for (int i = 0; i < 5; i++) {
            nodeList.add(new GraphNode("Node" + i, i));
        }
        
        graph = new Graph(nodeList);
        
        graph.addWeightedEdge(0, 1, 4);
        graph.addWeightedEdge(0, 2, 1);
        graph.addWeightedEdge(2, 1, 2);
        graph.addWeightedEdge(1, 3, 1);
        graph.addWeightedEdge(2, 3, 5);
        graph.addWeightedEdge(3, 4, 3);
        
        graph.dijkstra(nodeList.get(0));
        
        assertEquals(0, nodeList.get(0).distance);
        assertEquals(3, nodeList.get(1).distance);
        assertEquals(1, nodeList.get(2).distance);
        assertEquals(4, nodeList.get(3).distance);
        assertEquals(7, nodeList.get(4).distance);
    }
    
    @Test
    @DisplayName("Test graph toString method")
    void testGraphToString() {
        for (int i = 0; i < 3; i++) {
            nodeList.add(new GraphNode("Node" + i, i));
        }
        
        graph = new Graph(nodeList);
        
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(1, 2);
        
        String graphString = graph.toString();
        
        assertNotNull(graphString);
        assertTrue(graphString.contains("Node0:"));
        assertTrue(graphString.contains("Node1:"));
        assertTrue(graphString.contains("Node2:"));
    }
    
    @Test
    @DisplayName("Test single node graph")
    void testSingleNodeGraph() {
        nodeList.add(new GraphNode("SingleNode", 0));
        graph = new Graph(nodeList);
        
        graph.bellmanFord(nodeList.get(0));
        
        assertEquals(0, nodeList.get(0).distance);
        assertNull(nodeList.get(0).parent);
    }
    
    @Test
    @DisplayName("Test disconnected graph components")
    void testDisconnectedGraph() {
        for (int i = 0; i < 4; i++) {
            nodeList.add(new GraphNode("Node" + i, i));
        }
        
        graph = new Graph(nodeList);
        
        // Create two separate components
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(2, 3);
        
        graph.bfs();
        
        // All nodes should still be visited
        for (GraphNode node : nodeList) {
            assertTrue(node.isVisited);
        }
    }
    
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
