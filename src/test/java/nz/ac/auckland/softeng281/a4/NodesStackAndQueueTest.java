package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;



import java.util.ArrayList;

public class NodesStackAndQueueTest {

    NodesStackAndQueue stack;   

    @Before
    public void setUp() {
        stack = new NodesStackAndQueue();        
    }

    @Test
    public void isStackEmptyTrue() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void isStackEmptyFalse() {
        stack.append(new Node("4"));
        assertFalse(stack.isEmpty());
    }
    @Test
    public void testPushWithSize() {
    	stack.push(new Node("4"));
    	stack.push(new Node("5"));
    	stack.push(new Node("6"));
    	stack.push(new Node("1"));
    	
    	assertTrue(stack.getSize()==4);
    }
    
    @Test
    public void testPopWithSize() {
    	stack.push(new Node("4"));
    	stack.push(new Node("5"));
    	stack.push(new Node("6"));
    	stack.push(new Node("1"));
    	stack.push(new Node("4"));
    	stack.push(new Node("5"));
    	stack.push(new Node("6"));
    	stack.push(new Node("1"));
    	stack.pop();
    	stack.pop();
    	
    	assertTrue(stack.getSize()==6);
    }
    
    @Test
    public void testPop() {
    	stack.push(new Node("4"));
    	stack.push(new Node("5"));
    	stack.push(new Node("6"));
    	stack.push(new Node("1"));
    	
    	assertEquals(stack.pop(), new Node("1"));
    }
    @Test
    public void testPushAndPeek() {
    	stack.push(new Node("4"));
    	stack.push(new Node("5"));
    	stack.push(new Node("6"));
    	stack.push(new Node("1"));

    	assertEquals(stack.peek(), new Node("1"));
    }
    @Test
    public void testPushPopAndPeek() {
    	stack.push(new Node("4"));
    	stack.push(new Node("5"));
    	stack.push(new Node("6"));
    	stack.push(new Node("1"));
    	stack.push(new Node("4"));
    	stack.push(new Node("5"));
    	stack.push(new Node("6"));
    	stack.push(new Node("1"));
    	stack.pop();
    	stack.pop();
    	
    	assertEquals(stack.peek(), new Node("5"));
    }
    
    @Test
    public void testPushPopAppendAndPeek() {
    	stack.push(new Node("4"));
    	stack.push(new Node("5"));
    	stack.push(new Node("6"));
    	stack.push(new Node("1"));
    	stack.append(new Node("3"));
    	stack.append(new Node("10"));
    	

    	assertEquals(stack.peek(), new Node("1"));
    	assertTrue(stack.getSize()==6);
    	
    	stack.pop();
    	stack.pop();
    	stack.pop();
    	stack.pop();
    	
    	assertEquals(stack.peek(),new Node("3"));
    	assertTrue(stack.getSize()==2);    	    
    }
    
    @Test
    public void testPopEmpty() {
    	assertNull(stack.pop());
    }
    
    @Test
    public void testPopEmpty2() {
    	stack.push(new Node("4"));
    	stack.pop();
    	assertNull(stack.pop());
    }
    
    @Test 
    public void testAppend1(){
    	stack.append(new Node("3"));
    	stack.append(new Node("5"));
    	
    	assertEquals(stack.peek(),new Node("3"));
    }
    
    @Test
    public void testAppend2() {
    	stack.push(new Node("1"));
    	stack.append(new Node("2"));
    	stack.append(new Node("3"));
    	stack.push(new Node("4"));
    	
    	assertEquals(stack.peek(),new Node("4"));
    	stack.pop();
    	stack.pop();
    	stack.pop();
    	assertEquals(stack.peek(),new Node("3"));
    	
    }
}