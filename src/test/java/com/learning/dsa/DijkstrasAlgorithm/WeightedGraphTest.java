package com.learning.dsa.DijkstrasAlgorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WeightedGraph Test Suite!")
public class WeightedGraphTest {

    private WeightedGraph graph;
    private ArrayList<WeightedNode> nodeList;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        nodeList = new ArrayList<>();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Constructor initializes graph with node list")
    void testConstructor() {
        WeightedNode node1 = new WeightedNode("A", 0);
        WeightedNode node2 = new WeightedNode("B", 2);
        nodeList.add(node1);
        nodeList.add(node2);

        graph = new WeightedGraph(nodeList);

        assertNotNull(graph.nodeList);
        assertEquals(2, graph.nodeList.size());
        assertEquals("A", graph.nodeList.get(0).name);
    }

    @Test
    @DisplayName("Add weighted edge creates proper connection")
    void testAddWeightedEdge() {
        WeightedNode nodeA = new WeightedNode("A", 0);
        WeightedNode nodeB = new WeightedNode("B", 1);
        nodeList.add(nodeA);
        nodeList.add(nodeB);
        graph = new WeightedGraph(nodeList);

        graph.addWeightedEdge(0, 1, 5);

        assertTrue(nodeA.neighbours.contains(nodeB));
        assertEquals(5, nodeA.weightMap.get(nodeB));
    }

    @Test
    @DisplayName("Add multiple weighted edges from single node")
    void testAddMultipleWeightedEdges() {
        WeightedNode nodeA = new WeightedNode("A", 0);
        WeightedNode nodeB = new WeightedNode("B", 1);
        WeightedNode nodeC = new WeightedNode("C", 2);
        nodeList.add(nodeA);
        nodeList.add(nodeB);
        nodeList.add(nodeC);
        graph = new WeightedGraph(nodeList);

        graph.addWeightedEdge(0, 1, 5);
        graph.addWeightedEdge(0, 2, 3);

        assertEquals(2, nodeA.neighbours.size());
        assertTrue(nodeA.neighbours.contains(nodeB));
        assertTrue(nodeA.neighbours.contains(nodeC));
        assertEquals(5, nodeA.weightMap.get(nodeB));
        assertEquals(3, nodeA.weightMap.get(nodeC));
    }

    @Test
    @DisplayName("Dijkstra''s algorithm finds shortest path in simple graph")
    void testDijkstraSimplePath() {
        WeightedNode nodeA = new WeightedNode("A", 0);
        WeightedNode nodeB = new WeightedNode("B", 1);
        WeightedNode nodeC = new WeightedNode("C", 2);
        nodeList.add(nodeA);
        nodeList.add(nodeB);
        nodeList.add(nodeC);
        graph = new WeightedGraph(nodeList);

        graph.addWeightedEdge(0, 1, 2);
        graph.addWeightedEdge(1, 2, 3);

        graph.dijkstra(nodeA);

        assertEquals(0, nodeA.distance);
        assertEquals(2, nodeB.distance);
        assertEquals(5, nodeC.distance);
        assertNull(nodeA.parent);
        assertEquals(nodeA, nodeB.parent);
        assertEquals(nodeB, nodeC.parent);
    }

    @Test
    @DisplayName("Dijkstra's algorithm chooses optimal path")
    void testDijkstraOptimalPath() {
        WeightedNode nodeA = new WeightedNode("A", 0);
        WeightedNode nodeB = new WeightedNode("B", 1);
        WeightedNode nodeC = new WeightedNode("C", 2);
        nodeList.add(nodeA);
        nodeList.add(nodeB);
        nodeList.add(nodeC);
        graph = new WeightedGraph(nodeList);

        graph.addWeightedEdge(0, 1, 5);
        graph.addWeightedEdge(0, 2, 2);
        graph.addWeightedEdge(2, 1, 1);

        graph.dijkstra(nodeA);

        assertEquals(0, nodeA.distance);
        assertEquals(3, nodeB.distance);
        assertEquals(2, nodeC.distance);
        assertEquals(nodeC, nodeB.parent);
    }

    @Test
    @DisplayName("Dijkstra's algorithm handles disconnected nodes")
    void testDijkstraDisconnectedNodes() {
        WeightedNode nodeA = new WeightedNode("A", 0);
        WeightedNode nodeB = new WeightedNode("B", 1);
        WeightedNode nodeC = new WeightedNode("C", 2);
        nodeList.add(nodeA);
        nodeList.add(nodeB);
        nodeList.add(nodeC);
        graph = new WeightedGraph(nodeList);

        graph.addWeightedEdge(0, 1, 5);

        graph.dijkstra(nodeA);

        assertEquals(0, nodeA.distance);
        assertEquals(5, nodeB.distance);
        assertEquals(Integer.MAX_VALUE, nodeC.distance);
        assertNull(nodeC.parent);
    }

    @Test
    @DisplayName("Dijkstra's algorithm on complex graph")
    void testDijkstraComplexGraph() {
        WeightedNode nodeA = new WeightedNode("A", 0);
        WeightedNode nodeB = new WeightedNode("B", 1);
        WeightedNode nodeC = new WeightedNode("C", 2);
        WeightedNode nodeD = new WeightedNode("D", 3);
        WeightedNode nodeE = new WeightedNode("E", 4);
        WeightedNode nodeF = new WeightedNode("F", 5);

        nodeList.add(nodeA);
        nodeList.add(nodeB);
        nodeList.add(nodeC);
        nodeList.add(nodeD);
        nodeList.add(nodeE);
        nodeList.add(nodeF);
        graph = new WeightedGraph(nodeList);

        graph.addWeightedEdge(0, 1, 6);
        graph.addWeightedEdge(0, 2, 3);
        graph.addWeightedEdge(1, 2, 2);
        graph.addWeightedEdge(1, 3, 5);
        graph.addWeightedEdge(2, 3, 3);
        graph.addWeightedEdge(2, 4, 4);
        graph.addWeightedEdge(3, 4, 2);
        graph.addWeightedEdge(4, 1, 1);

        graph.dijkstra(nodeA);

        assertEquals(0, nodeA.distance);
        assertEquals(6, nodeB.distance);
        assertEquals(3, nodeC.distance);
        assertEquals(6, nodeD.distance);
        assertEquals(7, nodeE.distance);
    }

    @Test
    @DisplayName("PathPrint displays correct path for node with parents")
    void testPathPrint() {
        WeightedNode nodeA = new WeightedNode("A", 0);
        WeightedNode nodeB = new WeightedNode("B", 1);
        WeightedNode nodeC = new WeightedNode("C", 2);
        nodeList.add(nodeA);
        nodeList.add(nodeB);
        nodeList.add(nodeC);
        graph = new WeightedGraph(nodeList);
        
        graph.addWeightedEdge(0, 1, 2);
        graph.addWeightedEdge(1, 2, 3);
        graph.dijkstra(nodeA);
        
        outputStreamCaptor.reset();
        WeightedGraph.pathPrint(nodeC);
        
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("A"));
        assertTrue(output.contains("B"));
        assertTrue(output.contains("C"));
    }

    @Test
    @DisplayName("Single node graph")
    void testSingleNodeGraph() {
        WeightedNode nodeA = new WeightedNode("A", 0);
        nodeList.add(nodeA);
        graph = new WeightedGraph(nodeList);
        
        graph.dijkstra(nodeA);
        
        assertEquals(0, nodeA.distance);
        assertNull(nodeA.parent);
    }

    @Test
    @DisplayName("Bidirectional edges work correctly")
    void testBidirectionalEdges() {
        WeightedNode nodeA = new WeightedNode("A", 0);
        WeightedNode nodeB = new WeightedNode("B", 1);
        nodeList.add(nodeA);
        nodeList.add(nodeB);
        graph = new WeightedGraph(nodeList);
        
        graph.addWeightedEdge(0, 1, 5);
        graph.addWeightedEdge(1, 0, 3);
        
        assertTrue(nodeA.neighbours.contains(nodeB));
        assertTrue(nodeB.neighbours.contains(nodeA));
        assertEquals(5, nodeA.weightMap.get(nodeB));
        assertEquals(3, nodeB.weightMap.get(nodeA));
    }
    
    void tearDown() {
        System.setOut(originalOut);
    }
}
