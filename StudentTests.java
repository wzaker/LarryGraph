package tests;

import org.junit.*;

import larryGraph.LarryGraph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * Wahida Zaker
 */

public class StudentTests {


	@Test
	public void testNewLarryGraphVertex() {
		LarryGraph<Integer> graph = new LarryGraph<>();
		assertTrue(graph.newLarryGraphVertex(1));
		assertTrue(graph.isLarryGraphVertex(1));
		assertFalse(graph.newLarryGraphVertex(1));
	}

	@Test
	public void testNewLarryGraphEdge() {
		LarryGraph<Integer> graph = new LarryGraph<>();
		graph.newLarryGraphVertex(1);
		graph.newLarryGraphVertex(2);

		assertTrue(graph.newLarryGraphEdge(1, 2, 5));
		assertEquals(5, graph.larryGraphEdgeWeight(1, 2));
		assertFalse(graph.newLarryGraphEdge(1, 2, -3));
		assertFalse(graph.newLarryGraphEdge(1, 1, 3));
	}

	@Test
	public void testRemoveLarryGraphEdge() {
		LarryGraph<Integer> graph = new LarryGraph<>();
		graph.newLarryGraphVertex(1);
		graph.newLarryGraphVertex(2);
		graph.newLarryGraphEdge(1, 2, 5);

		assertTrue(graph.removeLarryGraphEdge(1, 2));
		assertEquals(-1, graph.larryGraphEdgeWeight(1, 2));
		assertFalse(graph.removeLarryGraphEdge(1, 2));
	}

	@Test
	public void testRemoveLarryGraphVertex() {
		LarryGraph<Integer> graph = new LarryGraph<>();
		graph.newLarryGraphVertex(1);
		graph.newLarryGraphVertex(2);
		graph.newLarryGraphEdge(1, 2, 5);

		assertTrue(graph.removeLarryGraphVertex(1));
		assertFalse(graph.isLarryGraphVertex(1));
		assertEquals(-1, graph.larryGraphEdgeWeight(1, 2));
	}

	@Test
	public void testChangeLarryGraphEdge() {
		LarryGraph<Integer> graph = new LarryGraph<>();
		graph.newLarryGraphVertex(1);
		graph.newLarryGraphVertex(2);
		graph.newLarryGraphEdge(1, 2, 5);

		assertTrue(graph.changeLarryGraphEdge(1, 2, 10));
		assertEquals(10, graph.larryGraphEdgeWeight(1, 2));
		assertFalse(graph.changeLarryGraphEdge(1, 2, -3));
	}

	@Test
	public void testGetNeighbors() {
		LarryGraph<Integer> graph = new LarryGraph<>();
		graph.newLarryGraphVertex(1);
		graph.newLarryGraphVertex(2);
		graph.newLarryGraphEdge(1, 2, 5);

		Collection<Integer> neighbors = graph.getNeighbors(1);
		List<Integer> expectedNeighbors = new ArrayList<>();
		expectedNeighbors.add(2);

		assertEquals(expectedNeighbors, neighbors);
	}

	@Test
	public void testCostOfPath() {
		LarryGraph<Integer> graph = new LarryGraph<>();
		graph.newLarryGraphVertex(1);
		graph.newLarryGraphVertex(2);
		graph.newLarryGraphVertex(3);
		graph.newLarryGraphEdge(1, 2, 5);
		graph.newLarryGraphEdge(2, 3, 10);

		List<Integer> path = new ArrayList<>();
		path.add(1);
		path.add(2);
		path.add(3);

		assertEquals(15, graph.costOfPath(path));
	}

	@Test
    public void testCostOfPath_NoVertices() {
        LarryGraph<Integer> graph = new LarryGraph<>();
        List<Integer> path = new ArrayList<>();
        assertEquals(-1, graph.costOfPath(path));
    }

    @Test
    public void testCostOfPath_InvalidPath() {
        LarryGraph<Integer> graph = new LarryGraph<>();
        graph.newLarryGraphVertex(1);
        graph.newLarryGraphVertex(2);
        graph.newLarryGraphEdge(1, 2, 5);
        List<Integer> path = new ArrayList<>();
        path.add(1);
        path.add(3); // Vertex 3 does not exist
        assertEquals(-1, graph.costOfPath(path));
    }

}
