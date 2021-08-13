/**
 * Aashish Singh
 * UPI : asin819
 * ID : 297467251
 */


package nz.ac.auckland.softeng281.a4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You cannot add new fields.
 */
public class Graph {

    /**
     * Each node maps to a list of all the outgoing edges from that node
     */
    private HashMap<Node, EdgesLinkedList> adjacencyMap;
    /**
     * root of the graph, to know where to start the DFS or BFS
     */
    private Node root;

    /**
     * !!!!!! You cannot change this method !!!!!!!
     */
    public Graph(List<String> edges, List<String> weights) {
        if (edges.isEmpty() || weights.isEmpty()) {
            throw new IllegalArgumentException("edges and weights are empty");
        }
        adjacencyMap = new HashMap<>();
        int i = 0;
        for (String edge : edges) {
            String[] split = edge.split(",");
            Node source = new Node(split[0]);
            Node target = new Node(split[1]);
            Edge edgeObject = new Edge(source, target, Integer.parseInt(weights.get(i)));
            if (!adjacencyMap.containsKey(source)) {
                adjacencyMap.put(source, new EdgesLinkedList());
            }
            adjacencyMap.get(source).append(edgeObject);
            if (i == 0) {
                root = source;
            }
            i++;
        }
    }


    /**
     * find a particular node, note that a node might not have outgoing edges but only in going edges
     * so you need to check also the target nodes of the edges
     *
     * @param node
     * @return true if adjacencyMap contains the node, false otherwise.
     */
    
	public boolean isNodeInGraph(Node node) {
      
		// We use our helper method to get all present nodes in the graph.		
		Set<Node> nodesPresent = this.getAllNodes();
		
		if(nodesPresent.contains(node)) {
			return true;
		}else {
			return false;
		}
				    
    }
	
		
		

    /**
     * This method finds an edge with a specific weight, if there are more
     * than one you need to return the first you encounter.
     * You must use Breath First Search (BFS) strategy starting from the root.
     * <p>
     * You can create a data structure to keep track of the visited nodes
     * Set<Node> visited = new HashSet<>();
     * If you don't keep track of the visited nodes the method will run forever!
     * <p>
     * <p>
     * In addition to the data structure visited you can only create new
     * data structures of type EdgesLinkedList and NodesStackAndQue
     *
     * @param weight
     * @return the Edge with the specific weight, null if no edge with the specific weight exists in the graph
     */
    public Edge searchEdgeByWeight(int weight) {
    	
    	Set<Node> visited = new HashSet<>();
    	
    	// To use this data structure as a Queue, we will append new
    	// elements at the bottom of the data structure, mimicking enqueue, while
    	// popping elements off the top, mimicking dequeue, hence the data structure
    	// behaving like a queue.
    	NodesStackAndQueue queueBFS = new NodesStackAndQueue();
    	Node currentNode;
    	Edge currentEdge;
    	EdgesLinkedList currentEdgeList;
    	
    	queueBFS.append(root);
    	
    	// We run the BFS algorithm till the queue is empty
    	while(queueBFS.isEmpty()==false) {
    		
    		// Getting the EdgeLinkedList of the node on the top of the stack
    		currentEdgeList = adjacencyMap.get(queueBFS.peek());
    		
    		// Popping that node from the queue and adding to visited
    		currentNode = queueBFS.pop();
    		visited.add(currentNode);
    		
    		if(adjacencyMap.containsKey(currentNode)) {
    			currentEdge = currentEdgeList.getHead();
    			while(currentEdge!=null) {
    				
    				if(currentEdge.getWeight()==weight) {
    					return currentEdge;
    				}
    				
    				if(visited.contains(currentEdge.getTarget())==false) {
    					queueBFS.append(currentEdge.getTarget());
    				}
    				
    				currentEdge = currentEdge.getNext();
    			}
    		}
    	}

    	return null;
    	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }


    /**
     * Returns the weight of the Edge with Node source and Node target if the
     * given Edge is inside the graph.
     * If there is no edge with the specified source and target, the method returns -1
     * You must use Depth First Search (DFS) strategy starting from the root.
     * <p>
     * RULES
     * You can create a data structure to keep track of the visited nodes
     * Set<Node> visited = new HashSet<>();
     * If you don't keep track of the visited nodes the method will run forever!
     * <p>
     * In addition to the data structure visited you can only create new data structures of type
     * <p>
     * NodesStackAndQueue and EdgesLinkedList
     *
     * @param source
     * @param target
     * @return the weight of the first encountered edge with source and target,
     * -1 if no edge with the given source and target exists
     */
    public int searchWeightByEdge(Node source, Node target) {
    	
    	// Setting up objects to be used in this method
    	Set<Node> visited = new HashSet<>();
    	
    	// To use this data structure as a stack, we will push
    	// elements on the top of the data structure and pop off
    	// the top of the data structure, hence behaving like a list
    	NodesStackAndQueue stackDFS = new NodesStackAndQueue();
    	Node currentNode;
    	Edge currentEdge;
    	EdgesLinkedList currentEdgeList;
    	
    	// We start off by pushing the root node to the stack
    	stackDFS.push(root);

    	// We will run the DFS algorithm till the stack is empty
    	while(stackDFS.isEmpty()==false) {
    		
    		// Getting the EdgeLinkedList of the node on the top of the stack
    		currentEdgeList = adjacencyMap.get(stackDFS.peek());
    		
    		// Popping that node from the stack and adding to visited
    		currentNode = stackDFS.pop();
    		visited.add(currentNode);
    		
    		// Checking if the currentNode has any values mapped to it. 
    		// If so, we get the head of the linked list as our edge and
    		// traverse down the list till our edge is null
    		if(adjacencyMap.containsKey(currentNode)) {		// If added to avoid null pointer exception from getHead();
    			currentEdge = currentEdgeList.getHead();
    			
    			// While traversing through the graph, if we get the required 
    			// source node, we search for all its target nodes and see which
    			// ones matches the required target node.
    			if(currentNode.equals(source)) {
        			while(currentEdge!=null) {
        				if(currentEdge.getTarget().equals(target)) {
        					return currentEdge.getWeight();
        				}
        				currentEdge = currentEdge.getNext();
        			}
        			
        			// Since we had our required source node, but could
        			// not find the required target node, it means that 
        			// the edge does not exist, no need to traverse through
        			// the rest of the graph, hence return -1.
        			return -1;
        			
        		}
    			
    		}
    		
    		// If the currentNode does not map to any value, we will set the 
    		// currentEdge as null, hence skipping over the next chunk of 
    		// while loops, then going back to the start, popping it off the
    		// stack and marking it as visited.
    		else {
    			currentEdge = null;
    		}
    		
    		// We will check all the targets for this node and add them to the 
    		// stack if they are not visited already.
    		while(currentEdge!=null) {
    			if(visited.contains(currentEdge.getTarget())==false) {		
    				stackDFS.push(currentEdge.getTarget());
            		
    			}
    			currentEdge = currentEdge.getNext();
    			
    		}

    	}
    	
    	// Since we traversed through the whole graph and could not find
    	// the desired edge, we return -1
    	return -1;

    	
    }


    /**
     * Given a source Node and a target Node it returns the shortest path
     * between source and target
     * <p>
     * A Path is represented as an ordered sequence of nodes, together with the
     * total weight of the path. (see Path.java class)
     *
     * @param source
     * @param target
     * @return the shortest path between source and target
     */
    
	public Path computeShortestPath(Node source, Node target) {
    	
    	
    	// For Dijkstra's shortest path we set all the initial costs to 
    	// infinity (integer.max_Value) and previous nodes to null
    	HashMap<Node, Path> pathFromSourceToNodeMap = setCost(source);
    	HashMap<Node, Node>	previousNodeMap = setPrevious();    	
    	Set<Node> visited = new HashSet<>();
    	
    	// Setting up variables to be used
    	int updatedCost;
    	int previousCost = 0;
    	int edgeCost;
    	int currentCost;
    	Node currentNode = source;
    	
    	// We wish not to evaluate nodes that we have already visited.
    	while(visited.contains(currentNode)==false) {    		
    		for(Node targetNode : pathFromSourceToNodeMap.keySet()) {
    			
    			// If the edge exists between two nodes, it wont give -1
    			edgeCost = this.searchWeightByEdge(currentNode, targetNode);
    			if(edgeCost != -1) {
    				
    				// Getting the previous known cost from the source to the currentNode
    				previousCost = this.getpreviousCost(currentNode, pathFromSourceToNodeMap, previousNodeMap);
    				
    				// Getting the new cost from the source to that node
    				updatedCost = edgeCost + previousCost;
    				
    				// Getting the current known cost to that node from source 
    				currentCost = pathFromSourceToNodeMap.get(targetNode).getTotalCost();
    				
    				// If our new calculated cost is less than the current known cost, we update the cost and the previous nodes 
    				// using the respective helper methods.
    				if(updatedCost < currentCost) {
    					previousNodeMap.replace(targetNode, currentNode);
    					pathFromSourceToNodeMap.replace(targetNode, this.getWholePath(updatedCost, targetNode, previousNodeMap));
    					
    				}
   				
    			}
    			
    		}
    		
    		
    		// We add this evaluated node to the set of visited nodes and wish not to evaluate it again
    		visited.add(currentNode);
    		
    		// We find the next node that is not visited and has the cheapest known cost
    		currentNode = this.findCheapestCost(pathFromSourceToNodeMap, visited);
    	}
    	

    	// We now have a hash map, with keys as target nodes and values are the paths that
    	// need to be taken to get to that target node from the source node.
    	Path output = pathFromSourceToNodeMap.get(target);
    	return output;
	
    }
    
    
    
    //----------------------------Helper methods below this point----------------------//
    
    // Gets all possible nodes. This method is re-factored to be used in both
    // isNodeInGraph() method and computeShortestPath() method
    private Set<Node> getAllNodes(){
    	
    	
    	// Get all the nodes that are keys
    	Set<Node> tempSet = adjacencyMap.keySet();

    	// Empty set to put all the nodes in
    	// Another set was created because we cannot 
    	// .add() to a keySet, here it is tempSet
    	Set<Node> output = new HashSet<Node>();    	
   
    	// Variables to be used in this method
    	Edge currentEdge;
    	Node currentSource;
    	Node currentTarget;

    	// We find all the keys in the hashMap and iterate over each one of them
    	for(Node nodeKey : tempSet) {
			
    		// Getting the value associated with that key, the returned value will be an 
    		// object of Type : EdgesLinkedList
			EdgesLinkedList hashMapValue = adjacencyMap.get(nodeKey);
			
			// We will go through all the edges in the EdgesLinkedList
			int SizeOfList = hashMapValue.size();
			for(int i = 0; i<SizeOfList; i++) {
				currentEdge = hashMapValue.get(i);
					
				// The edge will have both a source and a target 
				currentSource = currentEdge.getSource();
				currentTarget = currentEdge.getTarget();
				
				// If the source or target do not exist in the output
				// set, we will add them.
				if (output.contains(currentSource)==false) {
					output.add(currentSource);
				}
				if(output.contains(currentTarget)==false) {
					output.add(currentTarget);
				}
			}
		}
    	
    	// Returning all the nodes in this graph.
    	return output;
    }

    // For Dijsktra's shortest path algorithm, we require to find the next node that has the least
    // cost and that has not been visited. This helper method is used to find that and invoked in
    // computeShortestPath()
    private Node findCheapestCost(HashMap<Node, Path> Map, Set<Node> visited) {
    	
    	Node currentCheapestNode = null;
    	int cheapestCost = Integer.MAX_VALUE;
    	
    	// It finds the cheapestNode by selection sort.
    	for(Node currentNode : Map.keySet()) {
    		if(Map.get(currentNode).getTotalCost() < cheapestCost) {
    			if(visited.contains(currentNode)==false) {
    				currentCheapestNode = currentNode;
    				cheapestCost = Map.get(currentNode).getTotalCost();
    			}
    		}
    	}
    	
    	
    	
    	return currentCheapestNode;
    }

    // We use this to set up the initial conditions for Dijkstra's shortest path
    // Here we will set all costs from source to that node as infinity
    private HashMap<Node, Path> setCost(Node source){
    	
    	HashMap<Node, Path> output = new HashMap<>();
    	int inf = Integer.MAX_VALUE;
    	
    	for(Node node : this.getAllNodes()) {
    		
    		// If the node and source is same, we set cost as 0 as'
    		// the cost from going source to source is 0
    		if(source.equals(node)) {
    			output.put(node, new Path(0,source,source));
    		} else {
    			output.put(node, new Path(inf,source,node));
    			
    		}
    		
    	}	
    	return output;
    }

    // We use this to set up the initial conditions for Dijkstra's shortest path
    // Here we set all the previous nodes for the node as null
    private HashMap<Node, Node> setPrevious(){
    	
    	HashMap<Node, Node> output = new HashMap<>();
    	for(Node node : this.getAllNodes()) {
    		output.put(node, null);
    	}
    	
    	return output;
    }

    // Traversing through all the costs of the previous nodes that need to be gone through to get to this node.
    private int getpreviousCost(Node currentNode, HashMap<Node, Path> costMap, HashMap<Node, Node> prevMap ) {
    	
    	int cost = 0;
    	if(currentNode!=null) {
    		cost = costMap.get(currentNode).getTotalCost();
    	}
    	return cost;
    }
    
    
    // This helper method gets the whole set of nodes from the source to that target node.
    private Path getWholePath(int updatedCost, Node targetNode, HashMap<Node, Node> prevMap) {
    	NodesStackAndQueue data = new NodesStackAndQueue();
    	Node prevNode = prevMap.get(targetNode);
    	List<Node> sortedData = new ArrayList<>();
    	
    	// When going through the nodes, we are kind of traversing through them in reverse. To
    	// correct the order, that is starting from source to target node, we will first push on
    	// stack then pop to get a sorted path.
    	while(prevNode!=null) {
    		data.push(prevNode);
    		prevNode = prevMap.get(prevNode);
    	}
    	
    	while(data.isEmpty()==false) {
    		sortedData.add(data.pop());
    	}
    	
    	// Finally adding the target node as it was not in the stack
    	sortedData.add(targetNode);
    	
    	// Creating a path for all these nodes and returning it.
    	Path output = new Path(updatedCost, sortedData);    	
    	return output;
    }

}
