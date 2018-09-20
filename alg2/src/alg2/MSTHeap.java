package alg2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MSTHeap {

	public Set<Edge> mst(List<Vertex> vertices, List<Edge> edges) {
		
		Set<Edge> mst = new HashSet<>();
		
		////////////////////////////////////////////////
		///// Step1: heap initialization
		////////////////////////////////////////////////
		Vertex s = vertices.get(0); 
		
		Set<Vertex> X = new HashSet<>();
		X.add(s);
		s.isInX = true;

		//init vertex costs
		//O(m)
		for(Edge e: edges) {
			Vertex v1 = e.v1;
			Vertex v2 = e.v2;
			
			if(s.equals(v1) && v2.currentCost > e.cost) {
				v2.currentCost = e.cost;
				v2.bestEdge = e;
			}
			
			if(s.equals(v2) && v1.currentCost > e.cost) {
				v1.currentCost = e.cost;
				v1.bestEdge = e;
			}
		}
		
		//init heap
		Heap h = new Heap();
		//O(m)
		for(Vertex v: vertices) {
			//O(logn)
			if(!v.equals(s)) {
				h.insert(v);
			}
		}
		////////////////////////////////////////////////
		
		////////////////////////////////////////////////
		///// Step2: Main loop(n-vertex, m-edge)
		///// top operation   delete operation   insert operation
		///// O(nlogn)      + O(mlogn)            + O(mlogn)       =  O(mlogn) 
		////////////////////////////////////////////////
		while(X.size() != vertices.size()) {
			
			//O(logn)
			Vertex v = h.top();
			X.add(v);
			v.isInX = true;
			//avoid null for s case
			if(v.bestEdge != null) {
				mst.add(v.bestEdge);
			}
			
			//This for is vertex local. This means that outer while with inner for 
			//gives only O(m) time complexity
			for(Edge e: v.es) {
				Vertex pair = e.pair(v);
				
				if(!pair.isInX && pair.currentCost > e.cost) {
					//O(logn)
					h.delete(pair);
					pair.currentCost = e.cost;
					pair.bestEdge = e;
					//O(logn)
					h.insert(pair);
				}
			}
		}
		
		return mst;
	}
	
	public static void main(String[] args) throws Exception {
		//test1();
		//test2();
		test3();
	}
	
	private static void test1() {
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
		
		Set<Edge> es = new MSTHeap().mst(vertices, edges);
		print(es, vertexNumber);
	}
	
	private static void test2() {
		int vertexNumber = 9;
		
		//adjacency list: both vertices and edges represent it
		List<Vertex> vertices = new ArrayList<>(); 
		for(int i=0; i < vertexNumber ; i++) {
			vertices.add(new Vertex(i+1));
		}
		
		List<Edge> edges = new ArrayList<>();
		edges.add(new Edge(vertices.get(1-1), vertices.get(2-1), 10));
		edges.add(new Edge(vertices.get(1-1), vertices.get(3-1), 12));

		edges.add(new Edge(vertices.get(2-1), vertices.get(3-1), 9));
		edges.add(new Edge(vertices.get(2-1), vertices.get(5-1), 8));
		
		edges.add(new Edge(vertices.get(3-1), vertices.get(4-1), 3));
		edges.add(new Edge(vertices.get(3-1), vertices.get(6-1), 1));
		
		edges.add(new Edge(vertices.get(4-1), vertices.get(5-1), 7));
		edges.add(new Edge(vertices.get(4-1), vertices.get(6-1), 3));
		
		edges.add(new Edge(vertices.get(5-1), vertices.get(6-1), 7));
		edges.add(new Edge(vertices.get(5-1), vertices.get(7-1), 8));
		edges.add(new Edge(vertices.get(5-1), vertices.get(8-1), 5));
		
		edges.add(new Edge(vertices.get(6-1), vertices.get(8-1), 6));
		
		edges.add(new Edge(vertices.get(7-1), vertices.get(8-1), 9));
		
		edges.add(new Edge(vertices.get(9-1), vertices.get(7-1), 2));
		edges.add(new Edge(vertices.get(9-1), vertices.get(8-1), 11));
		
		for(Edge e: edges) {
			vertices.get(e.v1.id-1).es.add(e);
			vertices.get(e.v2.id-1).es.add(e);
		}
		
		print(vertices);
		Set<Edge> es = new MSTHeap().mst(vertices, edges);
		print(es, vertexNumber);
	}
	
	private static void test3() {
		int vertexNumber = 10;
		
		//adjacency list: both vertices and edges represent it
		List<Vertex> vertices = new ArrayList<>(); 
		for(int i=0; i < vertexNumber ; i++) {
			vertices.add(new Vertex(i+1));
		}
		
		List<Edge> edges = new ArrayList<>();
		edges.add(new Edge(vertices.get(1-1), vertices.get(2-1), 6));
		edges.add(new Edge(vertices.get(1-1), vertices.get(3-1), 3));
		edges.add(new Edge(vertices.get(1-1), vertices.get(5-1), 9));

		edges.add(new Edge(vertices.get(2-1), vertices.get(3-1), 4));
		edges.add(new Edge(vertices.get(2-1), vertices.get(4-1), 2));
		edges.add(new Edge(vertices.get(2-1), vertices.get(7-1), 9));
		
		edges.add(new Edge(vertices.get(3-1), vertices.get(4-1), 2));
		edges.add(new Edge(vertices.get(3-1), vertices.get(5-1), 9));
		edges.add(new Edge(vertices.get(3-1), vertices.get(6-1), 9));
		
		edges.add(new Edge(vertices.get(4-1), vertices.get(6-1), 8));
		edges.add(new Edge(vertices.get(4-1), vertices.get(7-1), 9));
		
		edges.add(new Edge(vertices.get(5-1), vertices.get(6-1), 8));
		edges.add(new Edge(vertices.get(5-1), vertices.get(10-1),18));
		
		edges.add(new Edge(vertices.get(6-1), vertices.get(7-1), 7));
		edges.add(new Edge(vertices.get(6-1), vertices.get(9-1), 9));
		edges.add(new Edge(vertices.get(6-1), vertices.get(10-1),10));
		
		edges.add(new Edge(vertices.get(7-1), vertices.get(8-1), 4));
		edges.add(new Edge(vertices.get(7-1), vertices.get(9-1), 5));
		
		edges.add(new Edge(vertices.get(8-1), vertices.get(9-1), 1));
		edges.add(new Edge(vertices.get(8-1), vertices.get(10-1),4));
		
		edges.add(new Edge(vertices.get(9-1), vertices.get(10-1),3));
		
		for(Edge e: edges) {
			vertices.get(e.v1.id-1).es.add(e);
			vertices.get(e.v2.id-1).es.add(e);
		}
		
		print(vertices);
		Set<Edge> es = new MSTHeap().mst(vertices, edges);
		print(es, vertexNumber);
	}
	
	/*****************************************************************
	 *
	 * Heap
	 *
	 *****************************************************************/
	private static class Heap {
	
		Vertex[] vertices = new Vertex[100];
		int iStart = 0;
		int iEnd = 0;
		
		public void insert(Vertex v) {
			if(iEnd == 0) {
				vertices[iEnd] = v;
				v.pos = iEnd;
				iEnd++;
				return;
			}
			else{
				vertices[iEnd] = v;
				v.pos = iEnd;
				iEnd++;
				siftUp(iEnd-1);
			}
		}
		
		private void siftUp(int pos) {
			if(pos == iStart) {
				return;
			}
			int parentPos = pos%2 == 0 ? pos/2-1 : (pos-1)/2;
			
			if(vertices[pos].currentCost < vertices[parentPos].currentCost) {
				//swap
				swap(pos, parentPos);
				siftUp(parentPos);
			}
		}
		
		public Vertex top() {
			Vertex result = vertices[0];
			
			vertices[0] = vertices[iEnd-1];
			vertices[0].pos = 0;
			vertices[iEnd-1] = null;
			iEnd--;
			
			siftDown(0);
			
			return result;
		}
		
		private void siftDown(int pos) {
			int iLeft = (pos+1)*2-1;
			int iRight = (pos+1)*2;
			
			if(iLeft >= iEnd && iRight >= iEnd) {
				return;
			}
			
			if(iLeft < iEnd && iRight >= iEnd) {
				if(vertices[pos].currentCost > vertices[iLeft].currentCost) {
					//swap
					swap(pos, iLeft);
					siftUp(iLeft);
					return;
				}
			} else {
				int i = vertices[iLeft].currentCost <= vertices[iRight].currentCost ? 
						iLeft : iRight;
				if(vertices[i].currentCost < vertices[pos].currentCost) {
					//swap
					swap(i, pos);
					siftDown(i);
				}
			}
		}
		
		private void swap(int i1, int i2) {
			vertices[i1].pos = i2;
			vertices[i2].pos = i1;
			
			Vertex temp = vertices[i1];
			vertices[i1] = vertices[i2];
			vertices[i2] = temp;
		}
		
		public void delete(Vertex v) {
			vertices[v.pos].currentCost = Integer.MIN_VALUE;
			siftUp(v.pos);
			top();
		}
		
	}
	
	public static void print(Set<Edge> edges, int vertexNumber) {
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
	
	public static void print(List<Vertex> vertices) {
		System.out.println("=== G ===");
		for(Vertex v: vertices) {
			System.out.print(v.id + " -> ");
			for(Edge e: v.es) {
				System.out.print(e.pair(v).id + "(" + e.cost + ") , ");
			}
			System.out.println();
		}
	}
	
	/*****************************************************************
	 *
	 * Vertex
	 *
	 *****************************************************************/
	private static class Vertex {
		int id;
		List<Edge> es;
		
		int currentCost = Integer.MAX_VALUE;
		int pos = -1;
		Edge bestEdge = null;
		
		boolean isInX = false;
		
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
	
	/*****************************************************************
	 * 
	 * Edge
	 *
	 *****************************************************************/
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
