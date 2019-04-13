import java.util.Iterator;
import java.util.Set;

public class GenericGraphDFSTest {

	public static void main(String[] args) {
        
		/* Creo un grafo vuoto */
        Graph<NodeDFS, WeighedEdge<Double>> g = new GraphMatrixDirected<NodeDFS, WeighedEdge<Double>>();
        /* Creo i nodi */
        NodeDFS a = new NodeDFS("a");
        NodeDFS b = new NodeDFS("b");
        NodeDFS c = new NodeDFS("c");
        NodeDFS x = new NodeDFS("x");
        NodeDFS y = new NodeDFS("y");
        NodeDFS z = new NodeDFS("z");
        /* Aggiungo i nodi al grafo */
        g.addNode(a);
        g.addNode(b);
        g.addNode(c);
        g.addNode(x);
        g.addNode(y);
        g.addNode(z);
        /* Aggiungo gli archi */
        g.addEdge(a, b, new WeighedEdge<Double>(1.5));
        g.addEdge(a, x, new WeighedEdge<Double>(-2.0));
        g.addEdge(x, b, new WeighedEdge<Double>(null));
        g.addEdge(b, y, new WeighedEdge<Double>(null));
        g.addEdge(y, x, new WeighedEdge<Double>(1.0));
        g.addEdge(z, b, new WeighedEdge<Double>(0.6));
        g.addEdge(z, z, new WeighedEdge<Double>(0.5));
        g.addEdge(c, z, new WeighedEdge<Double>(2.0));

        /* Stampo il grafo */
        System.out.println("Grafo: ");
        System.out.print(g);
        GenericGraphDFS<NodeDFS, WeighedEdge<Double>> dfsAlgorithm = new GenericGraphDFS<NodeDFS, WeighedEdge<Double>>();
        dfsAlgorithm.DFS(g);		/* Eseguo la visita in profondità */
        System.out.println();
        System.out.println("Depth first search: ");
        /* Stampo i nodi con i relativi tempi di scoperta e esplorazione */
        Set<NodeDFS> nodes = g.getNodes();
        Iterator<NodeDFS> it = nodes.iterator();
 	    while(it.hasNext()) {
 		   NodeDFS s = it.next();
 		   System.out.print(s.printNodeTime());
 	    }
	}

}
