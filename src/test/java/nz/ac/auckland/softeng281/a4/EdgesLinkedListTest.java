package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class EdgesLinkedListTest {

    EdgesLinkedList list;

    @Before
    public void setUp() {
        list = new EdgesLinkedList();
    }

    @Test
    public void testPrependEmptyList() {
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
    }
    
    @Test
    public void testPrependWithSize() {
    	list.prepend(new Edge(new Node("1"), new Node("2"), 1));
    	list.prepend(new Edge(new Node("2"), new Node("3"), 1));
    	list.prepend(new Edge(new Node("3"), new Node("4"), 1));
    	assertTrue(list.size()==3);
    }
    
    @Test
    public void testAppendWithSize() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
    	list.append(new Edge(new Node("2"), new Node("3"), 1));
    	list.append(new Edge(new Node("3"), new Node("4"), 1));
    	list.append(new Edge(new Node("4"), new Node("5"), 1));
    	assertTrue(list.size()==4);
    }
    
    @Test
    public void testGetEdge() {
    	list.prepend(new Edge(new Node("1"), new Node("2"), 1));
    	list.prepend(new Edge(new Node("2"), new Node("3"), 1));
    	list.prepend(new Edge(new Node("3"), new Node("4"), 1));
    	assertEquals(list.get(1),new Edge(new Node("2"), new Node("3"), 1));
    }
    
    @Test 
    public void testPrependAppendGetEdgeAndSize() {
    	list.prepend(new Edge(new Node("3"), new Node("4"), 1));
    	list.prepend(new Edge(new Node("2"), new Node("3"), 1));
    	list.prepend(new Edge(new Node("1"), new Node("2"), 1));
    	list.append(new Edge(new Node("4"), new Node("5"), 1));
    	list.append(new Edge(new Node("5"), new Node("6"), 1));
    	
    	assertTrue(list.size()==5);
    	
    	assertEquals(list.get(0),new Edge(new Node("1"), new Node("2"), 1));
    	assertEquals(list.get(1),new Edge(new Node("2"), new Node("3"), 1));
    	assertEquals(list.get(2),new Edge(new Node("3"), new Node("4"), 1));
    	assertEquals(list.get(3),new Edge(new Node("4"), new Node("5"), 1));
    	assertEquals(list.get(4),new Edge(new Node("5"), new Node("6"), 1));
    }
    
    @Test
    public void testGettingInvalidEdge() {
    	
    	try {
    		list.prepend(new Edge(new Node("3"), new Node("4"), 1));
        	list.prepend(new Edge(new Node("2"), new Node("3"), 1));
    		assertEquals(list.get(3),new Edge(new Node("3"), new Node("4"), 1));
    	}
    	catch(InvalidPositionException e) {
    		fail();
    	}
    }
    
    @Test
    public void testInsertEdge() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
    	list.append(new Edge(new Node("2"), new Node("3"), 1));
    	list.append(new Edge(new Node("3"), new Node("4"), 1));
    	
    	assertEquals(list.get(1),new Edge(new Node("2"), new Node("3"), 1));
    	assertTrue(list.size()==3);
    	
    	list.insert(1, new Edge(new Node("70"),new Node("80"),1));
    	assertEquals(list.get(1),new Edge(new Node("70"), new Node("80"), 1));
    	assertEquals(list.get(2),new Edge(new Node("2"), new Node("3"), 1));
    	assertTrue(list.size()==4);
    }
    
    @Test
    public void testInsertEdge2() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
    	list.append(new Edge(new Node("2"), new Node("3"), 1));
    	list.append(new Edge(new Node("3"), new Node("4"), 1));
    	
    	assertEquals(list.get(1),new Edge(new Node("2"), new Node("3"), 1));
    	assertTrue(list.size()==3);
    	
    	list.insert(0, new Edge(new Node("70"),new Node("80"),1));
    	assertEquals(list.get(0),new Edge(new Node("70"), new Node("80"), 1));
    	assertEquals(list.get(1),new Edge(new Node("1"), new Node("2"), 1));
    	assertTrue(list.size()==4);
    }

    @Test
    public void testDeleteEdge() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
    	list.append(new Edge(new Node("2"), new Node("3"), 1));
    	list.append(new Edge(new Node("3"), new Node("4"), 1));
    	assertTrue(list.size()==3);
    	assertEquals(list.get(1),new Edge(new Node("2"), new Node("3"), 1));
    	
    	list.remove(1);
    	
    	assertTrue(list.size()==2);
    	assertEquals(list.get(1),new Edge(new Node("3"), new Node("4"), 1));
    	
    	
    }
    
    @Test
    public void testDeleteEdge2() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
    	list.append(new Edge(new Node("2"), new Node("3"), 1));
    	list.append(new Edge(new Node("3"), new Node("4"), 1));
    	assertTrue(list.size()==3);
    	assertEquals(list.get(1),new Edge(new Node("2"), new Node("3"), 1));
    	
    	list.remove(0);
    	
    	assertTrue(list.size()==2);
    	assertEquals(list.get(0),new Edge(new Node("2"), new Node("3"), 1));
    	
    	
    }
}