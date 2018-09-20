package alg2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MSTSimple {

	public Set<Edge> mst(List<Vertex> V, List<Edge> edges) throws Exception {
		
		Set<Vertex> X = new HashSet<>();
		Set<Edge> T = new HashSet<>();
		
		//init X with first vertex
		X.add(V.get(0));
		
		while(X.size() != V.size()) {
			int min = Integer.MAX_VALUE;
			Edge emin = null;
			for(Edge e: edges) {
				if(	( 
					  (X.contains(e.v1) && (!X.contains(e.v2))) || 
					  (X.contains(e.v2) && (!X.contains(e.v1)))
					) && 
				    (e.cost<min) ) 
				{
					min = e.cost;
					emin = e;
				}
			}
			
			Vertex v = X.contains(emin.v1) ? emin.v1 :
					   X.contains(emin.v2) ? emin.v2 : null;	
			
			if(v == null) {
				throw new Exception("Vertex is null");
			}
			
			X.add(emin.pair(v));
			T.add(emin);
		}
		
		return T;
	}
	
	public static void main(String[] args) throws Exception {
		
		int vertexNumber = 6;
		
		//adjacency list: both vertices and edges represent it
		List<Vertex> vertices = new ArrayList<>(); 
		for(int i=0; i < vertexNumber ; i++) {
			vertices.add(new Vertex(i+1));
		}
		
		List<Edge> edges = new ArrayList<>();
		edges.add(new Edge(vertices.get(1-1), vertices.get(2-1), 4));
		edges.add(new Edge(vertices.get(1-1), vertices.get(3-1), 3));
		edges.add(new Edge(vertices.get(1-1), vertices.get(5-1), 4));
		edges.add(new Edge(vertices.get(2-1), vertices.get(3-1), 5));
		edges.add(new Edge(vertices.get(2-1), vertices.get(4-1), 2));
		edges.add(new Edge(vertices.get(2-1), vertices.get(5-1), 4));
		edges.add(new Edge(vertices.get(3-1), vertices.get(4-1), 7));
		edges.add(new Edge(vertices.get(4-1), vertices.get(5-1), 2));
		edges.add(new Edge(vertices.get(5-1), vertices.get(6-1), 6));
		edges.add(new Edge(vertices.get(5-1), vertices.get(6-1), 2));
		
		for(Edge e: edges) {
			vertices.get(e.v1.id-1).es.add(e);
			vertices.get(e.v2.id-1).es.add(e);
		}
		
		new MSTSimple().print(vertices);
		Set<Edge> rEdges = new MSTSimple().mst(vertices, edges);
		new MSTSimple().print(rEdges, vertexNumber);
	}
	
	public void print(List<Vertex> vertices) {
		System.out.println("=== G ===");
		for(Vertex v: vertices) {
			System.out.print(v.id + " -> ");
			for(Edge e: v.es) {
				System.out.print(e.pair(v).id + "(" + e.cost + ") , ");
			}
			System.out.println();
		}
	}
	
	public void print(Set<Edge> edges, int vertexNumber) {
		List<Vertex> list = new ArrayList<>();
		for(int i=0; i < vertexNumber ; i++) {
			list.add(new Vertex(i+1));
		}
		
		for(Edge e: edges) {
			Vertex v1 = list.get(e.v1.id-1);
			Vertex v2 = list.get(e.v2.id-1);

			v1.es.add(e);
			v2.es.add(e);
		}
		
		print(list);
	}
	
	private static class Vertex {
		int id;
		List<Edge> es;
		
		public Vertex(int id) {
			super();
			this.id = id;
			es = new ArrayList<>();
		}
		
		public boolean equals(Vertex v) {
			if(this == v) {
				return true;
			}
			if(this.id == v.id) {
				return true;
			}
			return false;
		}
		
	}
	
	private static class Edge {
		Vertex v1;
		Vertex v2;
		int cost;
		
		public Edge(Vertex v1, Vertex v2, int cost) {
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}
		
		public Vertex pair(Vertex v) {
			if (v1.equals(v)) {
				return v2;
			}
			if (v2.equals(v)) {
				return v1;
			}
			return null;
		}
	}
	
}
