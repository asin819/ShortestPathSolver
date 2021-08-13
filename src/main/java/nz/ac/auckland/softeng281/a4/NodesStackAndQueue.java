/*
 * Author : Aashish Singh
 * UPI: asin819
 * ID: 297467251
 * 
 * SOFTENG 281 Assignment 4
 */



package nz.ac.auckland.softeng281.a4;

import java.util.ArrayList;

public class NodesStackAndQueue {
	
	protected ArrayList<Node> data;	
	protected int front;
	protected int rear;	
	protected Node output;
	

    public NodesStackAndQueue() {
    	data = new ArrayList<>();
    	front = 0;
    	rear = 0;    	      // Instead of implementing count as well,
    						  // I will use the rear variable to return sizes
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Push operation refers to inserting an element on the Top of the stack.
     *
     * @param node
     */
    public void push(Node node) {
        this.rear++;		// Incrementing the rear of the StackAndQueue      
        data.add(node);
    	
    }

    /**
     * pop an element from the top of the stack (removes and returns the top element)
     *
     * @return
     */
    public Node pop() {
    	if(rear == front) {		
    		return null;		// If the front and rear of the StackAndQueue is the same
    	}						// We have an empty arrayList, hence returning null
    	output = data.get(rear-1);
    	data.remove(rear-1);
    	rear = rear - 1;    	// Decrementing the size of the arrayList
    	return output;    	      
    }

    /**
     * get the element from the top of the stack without removing it
     *
     * @return
     */
    public Node peek() {
    	if (rear== front) {
    		return null;	// Again, if our arrayList is empty, we return null
    	}
    	return data.get(rear-1);
        
    }

    /**
     * append an element at the end of the stack
     *
     * @param node
     */
    public void append(Node node) {
    	data.add(front, node);
    	rear++;    	    	        
    }
    
    /**
     * get the number of elements
     * 
     * @return Integer: size of the StackAndQueue
     */
    public int getSize() {
    	return this.rear;		// Returns the size of the list.
    }
}
