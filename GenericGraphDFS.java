import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GenericGraphDFS<V extends GenericDFSNode, E> {

	/*
	 * Tempo discreto utilizzato per determinare il tempo in cui
	 * un nodo viene scoperto e completamente esplorato. 
	 */
	private int time = 0;
	
	/**
	 * La strategia adottata da questo algoritmo di visita consiste nel visitare il
	 * grafo in profondità.
	 * Si comincia con il visitare un nodo qualsiasi che viene marcato come
	 * scoperto, ovvero con il colore grigio.
	 * La visita prosegue ispezionando tutti gli archi uscenti dal nodo corrente 
	 * alla ricerca di un nodo collegato al precedente e non ancora visitato.
	 * Se tale nodo esiste, diventa il prossimo nodo corrente e viene marcato
     * come scoperto.
	 * Prima o poi, si arriva ad un punto in cui tutti gli archi incidenti il nodo
	 * corrente portano a nodi visitati.
	 * @param g grafo
	 */
	 public void DFS(Graph<V, E> g) {
		 	/* Creo un set contenente i nodi del grafo */
			Set<V> nodes = g.getNodes();
			/* Creo un iteratore per scorrere i nodi */
	        Iterator<V> it = nodes.iterator();
	        /* Pongo tutti i nodi come non scoperti, ovvero di colore bianco */
	        while(it.hasNext()) {
	        	V n = it.next();
	        	g.setColor(n, Graph.COLOR_WHITE);
	        	n.setFather(null);		/* Pongo il padre di ogni nodo a null */
	        }
     
		   it = nodes.iterator();		/* Azzero l'iteratore */
		   while(it.hasNext()) {		/* Scorro nuovamente i nodi */
			   V app = it.next();
			   /* Se il colore del nodo è bianco lo visito */
			   if(g.getColor(app) == Graph.COLOR_WHITE) {	
				   DFSVisit(g, app);
			   }
		   }
	 }
	 
	/**
	 * Visita tutti i nodi raggiungibili da s.
	 * @param g		grafo
	 * @param s		nodo da visitare
	 */
	public void DFSVisit(Graph<V, E> g, V s) {
		/* Creo un set per i nodi successori di s */
		Set<V> succ = new HashSet<V>();
		g.setColor(s, Graph.COLOR_GREY);	/* Coloro s di grigio in quanto è stato scoperto */
	    time = time + 1;		/* Incremento il tempo discreto */
	    s.setTimeDiscovered(time); 		/* Imposto il tempo in cui è stato scoperto s */
		if(g.isDirected())  {
			 succ = g.successors(s);	/* Se il grafo è orientato cerco i successori di s */
		}
		else {
			 succ = g.neighbors(s);		/* Se invece non è orientato i suoi adiacenti */
		}
		for(V successor : succ) {
			if(g.getColor(successor) == Graph.COLOR_WHITE) {		/* Controllo che il colore dei successori sia bianco */
				 successor.setFather(s);		/* Imposto il padre */
				 DFSVisit(g,successor);			/* Visito i successori */
		    }
		}
		g.setColor(s, Graph.COLOR_BLACK); 		/* Imposto il colore di s come nero */
		time = time + 1;        /* Incremento il tempo discreto */
		s.setTimeExplored(time);	 		/* Imposto il tempo in cui è stato esplorato */
	}
	
}