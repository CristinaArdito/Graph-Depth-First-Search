import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GraphMatrixDirectedTest {

	 public static <V, E> void main(String[] args) {
		 
		 /* Creo il grafo */
		 GraphMatrixDirected<String, Double> g = new GraphMatrixDirected<String, Double>();
		 /* Aggiungo i nodi */
	     g.addNode("r");
         g.addNode("s");
         g.addNode("t");
         g.addNode("u");
         g.addNode("v");
         g.addNode("w");
         g.addNode("x");
         g.addNode("y");
         
         /* Aggiungo gli archi */
         g.addEdge("r","s", 1.0);
         g.addEdge("r", "v", 2.0);
         g.addEdge("w", "v", 0.5);
         g.addEdge("x", "y", 8.0);
         g.addEdge("x", "v", null);
         g.addEdge("x", "t", 9.0);
         g.addEdge("w", "x", 0.2);
         g.addEdge("u", "r", -3.0);
         g.addEdge("y", "s", 0.4);
         g.addEdge("v", "r", 0.1);
         g.addEdge("s", "u", 1.5);
         g.addEdge("s", "s", 0.4);
         g.addEdge("t", "u", null);
	     
         /* Stampo la matrice di adiacenza */
	     System.out.println("Matrice di adiacenza: ");
	     System.out.println(g.printMatrix());
	     /* Stampo il grafo */
	     System.out.println("Grafo: ");
	     System.out.println(g);
	     /* Test delle operazione sul grafo */
	     System.out.println(" ----------------------------------------------------------");
	     System.out.println(" |    Operazioni sul grafo.                               |");
	     System.out.println(" ----------------------------------------------------------");
	     System.out.println("Verifica della presenza del nodo "+"r"+" : "+ g.containsNode("r"));
	     System.out.println("Successori di x : "+g.successors("x"));
	     System.out.println("Predecessori di v : "+g.predecessors("v"));;
	     System.out.println("Indice del nodo x : "+ g.getNodeIndex("x"));
	     System.out.print("Nodi presenti nel grafo: "); 
	     Set <String> n = g.getNodes();
	     Iterator<String> it = n.iterator();
	     while(it.hasNext()) {
	      	 String s = it.next();
           	 System.out.print(s + " ");
	     }
	     System.out.println();
	     System.out.println("Numero di archi del grafo : " +g.edgeCount());
	     System.out.println("Verifica della presenza dell'arco w(r,s)= 1.0: " + g.containsEdge("r", "s", 1.0));
	     System.out.println("Eliminazione dell'arco w(r,s)= 1.0: " + g.removeEdge("r", "s", 1.0));
	     System.out.println("Verifica della presenza dell'arco w(r,s)= 1.0: " + g.containsEdge("r", "s", 1.0));
	     System.out.print("Arco compreso tra x ed y: ");
	     Set<Edge<String,Double>> edge = g.getEdges("x", "y");
	     Iterator<Edge<String,Double>> it2 = edge.iterator();
	     while(it2.hasNext()) {
	       	 Edge<String, Double> s = it2.next();
	         System.out.println(s.getLabel());
	     }
	     System.out.println("Grado di t: "+ g.getDegree("t"));
	     System.out.println("Archi entranti nel nodo v:");
	     Set<Edge<String, Double>> ris = new HashSet<Edge<String, Double>>();
	     ris = g.ingoingEdges("v");
	     it2 = ris.iterator();
	     while(it2.hasNext()) {
	       	 Edge<String, Double> am = it2.next();
	         System.out.println("("+am.getLabel1() +") --> ("+ am.getLabel2()+")\t w("+am.getLabel1()+","+am.getLabel2()+")= "+am.getLabel());
	     }
	     System.out.println("Archi uscenti dal nodo s:");
		 ris = g.outgoingEdges("s");
		 it2 = ris.iterator();
		 while(it2.hasNext()) {
		   	 Edge<String, Double> am = it2.next();
		     System.out.println("("+am.getLabel1() +") --> ("+ am.getLabel2()+")");
		 }
		 System.out.println("Eliminazione del nodo s: " + g.removeNode("s"));    
	     System.out.println("Verifico se s è ancora presente nel grafo: " + g.containsNode("s"));
	 }
}
