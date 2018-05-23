package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CloneGraph {

	public static void main(String[] args) {
		String inputG = "0,1,2#1,0,2,3#2,0,2,3#3,1,2";
		
		UndirectedGraphNode n = new CloneGraph().build(inputG);
		UndirectedGraphNode n1 = new CloneGraph().cloneGraph(n);
		System.out.println(n1);
	}
	
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		UndirectedGraphNode copy = new UndirectedGraphNode(node.label); 
		
		Map<Integer, UndirectedGraphNode> nodes = new HashMap<>();
		nodes.put(copy.label, copy);
		
		//visit Set
		Set<UndirectedGraphNode> visitedNodes = new HashSet<>();
		//visitedNodes.add(copy);
		
		//nodes candidates to visit
		Queue<UndirectedGraphNode> q = new LinkedList<>();
		q.add(node);
		
		while(!q.isEmpty()) {
			UndirectedGraphNode currentOriginal = q.poll();
			UndirectedGraphNode current = nodes.get(currentOriginal.label);
			if(current == null) {
				current = new UndirectedGraphNode(currentOriginal.label);
				nodes.put(current.label, current);
			}
			
			//only for unvisited vertices
			if(!visitedNodes.contains(current)) {
				visitedNodes.add(current);
				
				for(UndirectedGraphNode n: currentOriginal.neighbors) {
					UndirectedGraphNode copyNeighbour = nodes.get(n.label); 
					if(copyNeighbour == null) {
						copyNeighbour = new UndirectedGraphNode(n.label);
						nodes.put(copyNeighbour.label, copyNeighbour);
					}
					current.neighbors.add(copyNeighbour);
					q.add(n);
				}
			}
		}
		
		return copy;
	}
	
	public UndirectedGraphNode build(String inputG) {
		String[] edges = inputG.split("#");
		String[] verteces = null;
		
		Map<Integer, UndirectedGraphNode> nodes = new HashMap<>();
		
		for(String edge: edges) {
			verteces = edge.split(",");
			
			UndirectedGraphNode n = nodes.get(Integer.parseInt(verteces[0]));
			if(n == null) {
				n = new UndirectedGraphNode(Integer.parseInt(verteces[0]));
				nodes.put(Integer.parseInt(verteces[0]), n);
			}
			
			for(int i=1; i<verteces.length; i++) {
				UndirectedGraphNode neighbour = nodes.get(Integer.parseInt(verteces[i]));
				
				if(neighbour == null) {
					neighbour = new UndirectedGraphNode(Integer.parseInt(verteces[i]));
					nodes.put(Integer.parseInt(verteces[i]), neighbour);
				}
				
				n.neighbors.add(neighbour);
			}
		}
		
		return nodes.get(0);
	}
	
	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
		
	};
	
}
