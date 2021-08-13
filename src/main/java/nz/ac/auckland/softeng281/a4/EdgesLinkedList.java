package nz.ac.auckland.softeng281.a4;

/**
 * Aashish Singh
 * UPI : asin819
 * ID: 297467251 
 * 
 * 
 */



/**
 * The Linked List Class has only one head pointer to the start edge (head) Edges are
 * indexed starting from 0. The list goes from 0 to size-1. Note that the List does not
 * have a maximum size.
 *
 * @author Partha Roop
 */
public class EdgesLinkedList {
    // the head of the linked list
    private Edge head;
    private Edge currentEdge;
    private Edge previousEdge;
    private Edge nextEdge;
    
    private int counter;
    private int size;

    public EdgesLinkedList() {
        head = null;
    }


    /**
     * This method adds an edge as the start edge of the list
     *
     * @param edge to prepend
     */
    public void prepend(Edge edge) {    	
    	edge.setNext(head);		// Here we set the current head as the next edge
    	head = edge;			// Setting the current edge as the current edge
    	    	
    	// This code also works if the linkedList is empty, as the head will be null
    	// it will assign the next edge as null and the head as the new edge we have 
    	// put in as an argument.
    }

    /**
     * This method adds an edge as the end edge of the list
     *
     * @param edge to append
     */
    public void append(Edge edge) {
    	// Re-factored code from prepend, as we are adding an edge to an empty list.
    	if(head == null) {
    		edge.setNext(head);		 
    		head = edge;			 
    	}
    	else {
    		// We will traverse to the end of the linkedList by checking if the next
    		// element is null or not. When we reach null, we make the last element point
    		// to the edge we want to append and the edge we appended point to null.
    		currentEdge = head;				
    		while(currentEdge.getNext()!= null) {	
    			currentEdge = currentEdge.getNext();	
    		}
    		edge.setNext(currentEdge.getNext());	// Making the appended edge point to null.
    		currentEdge.setNext(edge);    		
    	}    	       
    }

    /**
     * This method gets the edge at a given position
     *
     * @param pos: an integer, which is the position
     * @return the Edge at the position pos
     */
    public Edge get(int pos) throws InvalidPositionException {
        
    	// If the position is out of bounds, we throw an error.
    	if (pos < 0 || pos > size() - 1) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
        } 
        
        // We traverse from the start to the index position we want, and when we reach that position
        // we return the edge at that position.
        counter = 0;
        currentEdge = head;
        while(counter != pos) {
        	currentEdge = currentEdge.getNext();
        	counter++;
        }
        return currentEdge;       
    }

    /**
     * This method adds an edge at a given position in the List
     *
     * @param pos:  an integer, which is the position
     * @param edge: the edge to add
     * @throws InvalidPositionException
     */
    public void insert(int pos, Edge edge) throws InvalidPositionException {
    	
    	// If the position is out of bounds, we throw an error.
    	if (pos < 0 || pos > size() - 1) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
        }
    	
    	// We get the values of the previous and current edges at the specified positions and then link
    	// the inserting edge accordingly.
    	
    	// If we are inserting at position 0, that means we are updating the head.
    	if(pos == 0) {
    		currentEdge = this.get(pos);
    		edge.setNext(currentEdge);
    		this.head = edge;    		
    	}else {
    		previousEdge = this.get(pos-1);
            currentEdge = this.get(pos);
            edge.setNext(currentEdge);
            previousEdge.setNext(edge);
    	}
        
        
    }

    /**
     * This method removes an edge at a given position
     *
     * @param pos: an integer, which is the position
     */
    public void remove(int pos) throws InvalidPositionException {
        
    	// If the position is out of bounds, we throw an error.
    	if (pos < 0 || pos > size() - 1) {
            throw new InvalidPositionException("Position " + pos + " outside the list boundary");
        }
        
    	
    	// To remove the edge, we point the edge before it to the edge after it,
    	// hence no edge will point to it and it wont point to any edge.
    	
    	// If pos is 0, we simply set the next edge as the head.
    	if(pos ==0) {
    		this.head = this.get(pos+1);
    	}else {
    		previousEdge = this.get(pos-1);
        	nextEdge = this.get(pos+1);
        	previousEdge.setNext(nextEdge);
    	}
    	
    	
    }

    /**
     * This method returns the size of the Linked list
     *
     * @return the size of the list
     */

    public int size() {
        size = 0;
        
        currentEdge = head;
        while(currentEdge != null) {
        	size++;
        	currentEdge = currentEdge.getNext();
        }
        
        return size;

    }

    /**
     * This method is used for printing the data in the list from head till the last
     * node
     */
    public void print() {
        Edge edge = head;
        while (edge != null) {
            System.out.println(edge);
            edge = edge.getNext();
        }
    }
    
    // Helper
    
    
    public Edge getHead() {
    	return this.head;
    }
    

}
