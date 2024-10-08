package larryGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * Wahida Zaker
 * 
 * LarryGraph represents a generic graph structure where vertices can be added,
 * edges between vertices can be established, and various operations can be
 * performed on the graph such as removing vertices or edges, and querying
 * properties of the graph.
 */

public class LarryGraph<V> {
	// adjacency list
	private Map<V, List<Edge<V>>> map;

	// inner class for the edges of the graph
	private class Edge<V> {
		private V vertex;
		private int weight;

		// edge constructor
		private Edge(V vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		// getter for the vertex
		private V getVertex() {
			return vertex;
		}

		// getter for the weight
		private int getWeight() {
			return weight;
		}
	}
	// constructor to initialize the graph
	public LarryGraph() {
		this.map = new HashMap<>();
	}

	/**
	 * Adds a new vertex to the graph
	 * 
	 * @param vertexInfo the information associated with the new vertex
	 * @return true if the vertex is successfully added, false if the vertex
	 * already exists
	 */
	public boolean newLarryGraphVertex(V vertexInfo) {
		if (vertexInfo == null)
			throw new IllegalArgumentException();

		// check if vertex already exists
		if (isLarryGraphVertex(vertexInfo)) {
			return false;
		} else {
			map.put(vertexInfo, new ArrayList<>());
			return true;
		}
	}

	/**
	 * Checks if a vertex exists in the graph.
	 *
	 * @param vertexInfo the vertex to check
	 * @return true if the vertex exists, false otherwise
	 */
	public boolean isLarryGraphVertex(V vertexInfo) {
		if (vertexInfo == null)
			throw new IllegalArgumentException();

		return (map.containsKey(vertexInfo));
	}

	/**
	 * Gets all vertices in the graph.
	 *
	 * @return a collection of all vertices in the graph
	 */
	public Collection<V> getLarryGraphVertices() {
		Collection<V> vertices = new ArrayList<>();

		// adds all vertices from map to collection
		vertices.addAll(map.keySet());

		return vertices;
	}

	/**
	 * Adds a new edge between two vertices in the graph.
	 *
	 * @param goesFrom the starting vertex of the edge
	 * @param goesTo   the ending vertex of the edge
	 * @param weight   the weight of the edge
	 * @return true if the edge is successfully added, false if the edge already
	 * exists or if the weight is negative
	 */
	public boolean newLarryGraphEdge(V goesFrom, V goesTo, int weight) {
		if (goesFrom == null || goesTo == null)
			throw new IllegalArgumentException();

		if (weight < 0) 
			return false;

		if (goesFrom.equals(goesTo)) {
			if (weight != 0)
				return false;
		}

		// check if edge already exists
		if (map.containsKey(goesFrom)) {
			for (Edge<V> edge : map.get(goesFrom)) {
				if (edge.getVertex().equals(goesTo))
					return false;
			}
		}
		// add the vertex to the map if not already there
		if (!map.containsKey(goesFrom)) 
			map.put(goesFrom, new ArrayList<>());

		if (!map.containsKey(goesTo))
			map.put(goesTo, new ArrayList<>());

		// add with weight
		map.get(goesFrom).add(new Edge<>(goesTo, weight));
		return true;
	}

	/**
	 * Gets the weight of the edge between two vertices.
	 *
	 * @param goesFrom the starting vertex of the edge
	 * @param goesTo   the ending vertex of the edge
	 * @return the weight of the edge if it exists, -1 otherwise
	 */
	public int larryGraphEdgeWeight(V goesFrom, V goesTo) {
		if (goesFrom == null || goesTo == null)
			throw new IllegalArgumentException();

		if (!map.containsKey(goesFrom) || !map.containsKey(goesTo))
			return -1;

		for (Edge<V> edge : map.get(goesFrom)) {
			if (edge.getVertex().equals(goesTo))
				return edge.getWeight();
		}
		// no edge between vertices
		return -1;
	}

	/**
	 * Removes an edge between two vertices in the graph.
	 *
	 * @param goesFrom the starting vertex of the edge
	 * @param goesTo   the ending vertex of the edge
	 * @return true if the edge is successfully removed, false if the edge does
	 * not exist
	 */
	public boolean removeLarryGraphEdge(V goesFrom, V goesTo) {
		if (goesFrom == null || goesTo == null)
			throw new IllegalArgumentException();

		if (!map.containsKey(goesFrom) || !map.containsKey(goesTo))
			return false;

		// get the list of edges at the goesFrom vertex
		List<Edge<V>> edges = map.get(goesFrom);

		// loop through the list of edges 
		for (int i = 0; i < edges.size(); i++) {
			// gets a single edge in that list
			Edge<V> edge = edges.get(i);

			// removes edge
			if (edge.getVertex().equals(goesTo)) {
				edges.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes a vertex from the graph along with all its incident edges.
	 *
	 * @param vertexInfo the vertex to remove
	 * @return true if the vertex is successfully removed, false if the vertex
	 * does not exist
	 */
	public boolean removeLarryGraphVertex(V vertexInfo) {
		if (vertexInfo == null)
			throw new IllegalArgumentException();

		if (!map.containsKey(vertexInfo)) 
			return false;

		// removes all edges to the source vertex first
		for (V sourceVertex : map.keySet()) {
			List<Edge<V>> edges = map.get(sourceVertex);

			Iterator<Edge<V>> iterator = edges.iterator();

			while (iterator.hasNext()) {
				Edge<V> edge = iterator.next();

				if (edge.getVertex().equals(vertexInfo)) {
					iterator.remove();
				}
			}
		}
		// remove the vertex from the graph
		map.remove(vertexInfo);
		return true;
	}

	/**
	 * Changes the weight of an existing edge between two vertices.
	 *
	 * @param goesFrom  the starting vertex of the edge
	 * @param goesTo    the ending vertex of the edge
	 * @param newWeight the new weight of the edge
	 * @return true if the edge weight is successfully changed, false if the
	 * edge does not exist or if the new weight is negative
	 */
	public boolean changeLarryGraphEdge(V goesFrom, V goesTo, int newWeight) {
		if (goesFrom == null || goesTo == null)
			throw new IllegalArgumentException();

		if (newWeight < 0)
			return false;

		if (goesFrom.equals(goesTo) && newWeight != 0)
			return false;

		if (!map.containsKey(goesFrom) || !map.containsKey(goesTo))
			return false;

		// get the list of edges corresponding to the goesFrom vertex
		List<Edge<V>> edges = map.get(goesFrom);
		boolean foundEdge = false; // tag to track if the edge is found

		// Iterate through the edges and update the weight
		for (Edge<V> edge : edges) {
			if (edge.getVertex().equals(goesTo)) {
				edge.weight = newWeight;
				foundEdge = true; // mark the edge as found
			}
		}

		return foundEdge;
	}

	/**
	 * Gets the neighbors of a vertex in the graph.
	 *
	 * @param vertexInfo the vertex to get the neighbors for
	 * @return a collection of neighbors of the specified vertex
	 */
	public Collection<V> getNeighbors(V vertexInfo) {
		if (vertexInfo == null)
			throw new IllegalArgumentException();

		// Check if vertexInfo is a vertex in the graph
		if (!map.containsKey(vertexInfo))
			return null;

		// create new list to store the neighbors
		List<V> neighbors = new ArrayList<>();

		// get list of edges corresponding to that vertex
		List<Edge<V>> edges = map.get(vertexInfo);

		// add vertex of the edges to the neighbors list
		for (Edge<V> edge : edges) {
			neighbors.add(edge.getVertex());
		}

		return neighbors;
	}

	/**
	 * Gets the predecessors of a vertex in the graph.
	 *
	 * @param destVertex the vertex to get the predecessors for
	 * @return a collection of predecessors of the specified vertex
	 */
	public Collection<V> predecessorsOfVertex(V destVertex) {
		if (destVertex == null)
			throw new IllegalArgumentException();

		// create list to store the predecessors
		List<V> predecessors = new ArrayList<>();

		for (V sourceVertex : map.keySet()) {
			// get list of edges for the source vertex
			List<Edge<V>> edges = map.get(sourceVertex);
			boolean foundDestVertex = false; 

			// loop through edges 
			for (Edge<V> edge : edges) {
				// if that edge contains the destVertex
				if (edge.getVertex().equals(destVertex)) {
					// add the source vertex to the list
					predecessors.add(sourceVertex);
					foundDestVertex = true; 
				}
			}
			if (foundDestVertex) {
				break;
			}
		}
		return predecessors;	
	}

	/**
	 * Calculates the total cost of a path represented by a list of vertices.
	 *
	 * @param candidatePath a list of vertices representing a path
	 * @return the total cost of the path if it is valid, -1 otherwise
	 */
	public int costOfPath(List<V> candidatePath) {
		if (candidatePath == null || candidatePath.size() < 2)
			return -1;

		int cost = 0;
		for (int i = 0; i < candidatePath.size() - 1; i++) {
			V fromVertex = candidatePath.get(i);
			V toVertex = candidatePath.get(i + 1);

			// check if the vertices are in the graph
			if (!map.containsKey(fromVertex) || !map.containsKey(toVertex))
				return -1;

			// check if there is an edge between the vertices
			int edgeWeight = larryGraphEdgeWeight(fromVertex, toVertex);
			if (edgeWeight == -1)
				return -1;

			// update cost
			cost += edgeWeight;
		}
		return cost;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (V vertex : map.keySet()) {
			sb.append(vertex).append(" -> ");
			List<Edge<V>> edges = map.get(vertex);
			for (Edge<V> edge : edges) {
				sb.append("(").append(edge.getVertex()).append(", ").
				append(edge.getWeight()).append(") ");
			}
			sb.append("\n");
		}
		return sb.toString();	
	}

}
