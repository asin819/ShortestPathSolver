package nz.ac.auckland.softeng281.a4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotEquals;

@RunWith(Enclosed.class)
public class GraphTest {

    public static class GraphUnitTest {
        Graph graph;
        Graph graph2;

        @Before
        public void setUp() throws Exception {
            List<String> edges = Arrays.asList("1,2", "2,3", "2,4", "4,2");
            List<String> weights = Arrays.asList("10", "20", "30", "20");
            graph = new Graph(edges, weights);
            
            List<String> edges2 = Arrays.asList("A,B", "B,C", "B,D", "D,B");
            List<String> weights2 = Arrays.asList("10", "20", "30", "20");
            graph2 = new Graph(edges2, weights2);
        }


        @Test
        public void testFindNode() {
            assertTrue(graph.isNodeInGraph(new Node("1")));
            assertTrue(graph.isNodeInGraph(new Node("2")));
            assertTrue(graph.isNodeInGraph(new Node("3")));
            assertTrue(graph.isNodeInGraph(new Node("4")));
            assertFalse(graph.isNodeInGraph(new Node("5")));
            assertFalse(graph.isNodeInGraph(new Node("45")));
            assertFalse(graph.isNodeInGraph(new Node("455")));
        }
        
        @Test
        public void testFindNode2() {
        	assertTrue(graph2.isNodeInGraph(new Node("B")));
            assertFalse(graph2.isNodeInGraph(new Node("b")));
        }

        @Test
        public void testShortestPath() {
            List<String> edges = Arrays.asList("1,2", "2,3", "3,4", "4,5");
            List<String> weights = Arrays.asList("10", "20", "30", "20");
            graph = new Graph(edges, weights);
            Path path = new Path(80, new Node("1"), new Node("2"), new Node("3"), new Node("4"), new Node("5"));
            assertEquals(path, graph.computeShortestPath(new Node("1"), new Node("5")));
        }
        
        
        @Test
        public void testShortestPath2() {
        	List<String> edges = Arrays.asList("A,B" , "A,C" , "B,C" , "B,D", "C,B", "C,D" , "C,E", "D,E", "E,D");
        	List<String> weights = Arrays.asList("10","3","1","2","4","8","2","7","9");
        	graph = new Graph(edges,weights);
        	Path path = new Path(7,new Node("A"), new Node("C"), new Node("B"));
        	assertEquals(path, graph.computeShortestPath(new Node("A"), new Node("B")));
        }
        
        @Test
        public void testShortestPath3() {
        	List<String> edges = Arrays.asList("A,B" , "A,C" , "B,C" , "B,D", "C,B", "C,D" , "C,E", "D,E", "E,D");
        	List<String> weights = Arrays.asList("10","3","1","2","4","8","2","7","9");
        	graph = new Graph(edges,weights);
        	Path path = new Path(6,new Node("C"), new Node("B"), new Node("D"));
        	assertEquals(path, graph.computeShortestPath(new Node("C"), new Node("D")));
        }
        
        @Test
        public void testShortestPath4() {
        	List<String> edges = Arrays.asList("A,B" , "A,C" , "B,C" , "B,D", "C,B", "C,D" , "C,E", "D,E", "E,D");
        	List<String> weights = Arrays.asList("10","3","1","2","4","8","2","7","9");
        	graph = new Graph(edges,weights);
        	Path path = new Path(9,new Node("A"), new Node("C"), new Node("B"), new Node("D"));
        	assertEquals(path, graph.computeShortestPath(new Node("A"), new Node("D")));
        }
        
        @Test
        public void testDFS_searchWeightByEdge() {
        	assertEquals(graph.searchWeightByEdge(new Node("1"), new Node("2")), 10);
        	assertEquals(graph.searchWeightByEdge(new Node("2"), new Node("3")), 20);
        	assertEquals(graph.searchWeightByEdge(new Node("2"), new Node("4")), 30);
        	assertEquals(graph.searchWeightByEdge(new Node("4"), new Node("2")), 20);
        	assertEquals(graph.searchWeightByEdge(new Node("2"), new Node("7")), -1);
        	assertEquals(graph.searchWeightByEdge(new Node("5"), new Node("6")), -1);
        	
        }
        
        @Test
        public void testDFS_searchWeightByEdge2() {
        	assertEquals(graph2.searchWeightByEdge(new Node("A"), new Node("B")), 10);
        	assertEquals(graph2.searchWeightByEdge(new Node("B"), new Node("C")), 20);
        	assertEquals(graph2.searchWeightByEdge(new Node("B"), new Node("D")), 30);
        	assertEquals(graph2.searchWeightByEdge(new Node("D"), new Node("B")), 20);
        	assertEquals(graph2.searchWeightByEdge(new Node("B"), new Node("c")), -1);
        	assertEquals(graph2.searchWeightByEdge(new Node("a"), new Node("d")), -1);
        	
        }
        
        @Test
        public void testBFS_searchEdgeByWeight() {
        	assertEquals(graph.searchEdgeByWeight(10),new Edge(new Node("1"), new Node("2"), 10));
        	assertEquals(graph.searchEdgeByWeight(20),new Edge(new Node("2"), new Node("3"), 20));
        	assertEquals(graph.searchEdgeByWeight(30),new Edge(new Node("2"), new Node("4"), 30));
        	assertEquals(graph.searchEdgeByWeight(40),null);
        	
        	
        }
        
        @Test
        public void testBFS_searchEdgeByWeight2() {
        	assertEquals(graph2.searchEdgeByWeight(10),new Edge(new Node("A"), new Node("B"), 10));
        	assertEquals(graph2.searchEdgeByWeight(20),new Edge(new Node("B"), new Node("C"), 20));
        	assertEquals(graph2.searchEdgeByWeight(30),new Edge(new Node("B"), new Node("D"), 30));
        	assertEquals(graph2.searchEdgeByWeight(40),null);
        	
        	
        }

    }
    
    public static class GraphSystemTest {

        ByteArrayOutputStream myOut;
        PrintStream origOut;

        @Before
        public void setUp() {
            origOut = System.out;
            myOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(myOut));
        }

        @After
        public void tearDown() {
            System.setOut(origOut);
            if (myOut.toString().length() > 1) {
                System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator()
                        + myOut.toString());
            }
        }

        private void runTest(String fileName, String command) {
            GraphUI.scanner = new Scanner("open " + fileName + System.getProperty("line.separator") + command
                    + System.getProperty("line.separator") + "exit" + System.getProperty("line.separator"));
            GraphControl controller = new GraphControl();
            controller.execute();
        }

        @Test
        public void testSearchWeightA() {
            runTest("a.txt", "search 1 3");
            assertTrue(myOut.toString().contains("Given the edge from source 1 target 3 has weight: 5"));
        }

        @Test
        public void testSearchEdgeByWeightA() {
            runTest("a.txt", "search 5");
            assertTrue(myOut.toString().contains("The edge searched having weight 5 is: 1-->3"));
        }

        @Test
        public void testShortestPathA() {
            runTest("a.txt", "path 5 1");
            assertTrue(myOut.toString().contains("The shortest path is: 5 -> 4 -> 1 cost: 4"));
        }


    }
	

}
    
    