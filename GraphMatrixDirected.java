import java.util.*;

public class GraphMatrixDirected<V, E> implements Graph<V, E>{
	
    /*
     * L'insieme dei nodi del grafo. Si usa una list invece che un set per poter
     * avere un indice automaticamente associato ad ogni nodo. Si controlla che
     * non vengano inseriti duplicati o elementi nulli. Si usa la classe interna
     * Node per associare al nodo informazioni aggiuntive come un indice
     * numerico univoco e crescente o come il colore.
     */
    private ArrayList<Node> nodes;

    /*
     * Rappresentazione con matrice di adiacenza.
     */
    private ArrayList<ArrayList<AdjacentMatrixElement>> adjmatrix;

    /*
     * Classe privata che serve per rappresentare un nodo del grafo, con
     * informazioni aggiuntive, ad esempio il colore corrente del nodo.
     */
    private class Node {
        /* Puntatore all'etichetta del nodo del grafo */
        private final V el;

        /* Colore associato al nodo */
        private int color;
        
        /**
         *  Costruttore del nodo senza colore.
         *  @param el	etichetta del nodo
         */
        private Node(V el) {
            this.el = el;
        }

        /**
         *  Costruttore del nodo con colore.
         *  @param el		etichetta nodo
         *  @param color	colore del nodo
         */
        private Node(V el, int color) {
            this.el = el;
            this.color = color;
        }
        
        /**
         * Override del metodo hashCode().
         */
		@Override
		public int hashCode() {
			final int prime = 31;		/* Numero primo */
			int result = 1;		/* Risultato */
			/* Calcolo il risultato */
			result = prime * result + color;
			result = prime * result + ((el == null) ? 0 : el.hashCode());
			return result;		/* Ritorno il risultato */
		}

		/**
		 * Override metodo equals().
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;		/* Controllo se gli oggetti sono uguali */
			if (obj == null) return false;		/* Controllo se obj è null */
			@SuppressWarnings("unchecked")
			Node other = (Node) obj;
			/* Controllo se il colore dei nodi è differente */
			if (color != other.color) return false;
			/* Controllo se uno dei due nodi è null mentre l'altro no */
			if (el == null) {
				if (other.el != null) return false;
			/* Controllo se i due nodi sono uguali */	
			} else if (!el.equals(other.el)) return false;
			return true;
		}
        
    }
    
    /* Classe privata per la matrice di adiacenza */
    private class AdjacentMatrixElement {
    	
    	/* Etichetta del primo nodo */
        private final V nodeLabel1;
        
        /* Etichetta del secondo nodo */
        private final V nodeLabel2;
        
        /* Etichetta dell'arco */
		private final E edgeLabel;
		
		/* Ulteriore bit che specifica la presenza dell'arco anche nel
		 * caso in cui la sua etichetta sia null
		 */
		private final boolean edge;
		
		/**
		 * Costruttore di un elemento della matrice di adiacenza.
		 * @param nodeLabel1	etichetta nodo da cui esce l'arco
		 * @param nodeLabel2	etichetta nodo in cui entra l'arco
		 * @param edgeLabel		etichetta arco
		 * @param edge			bit presenza arco
		 */
        public AdjacentMatrixElement(V nodeLabel1, V nodeLabel2, E edgeLabel, boolean edge) {
			this.nodeLabel1 = nodeLabel1;		
			this.nodeLabel2 = nodeLabel2;
			this.edgeLabel = edgeLabel;
			this.edge = edge;
		}
        
        /**
         * Ritorna l'etichetta dell'arco.
         * @return edgeLabel	etichetta dell'arco
         */
        public E getEdge() {
        	return this.edgeLabel;
        }
        
        /**
         * Ritorna l'etichetta del nodo da cui esce l'arco.
         * @return nodeLabel1	etichetta nodo
         */
		public V getNodeLabel1() {
			return nodeLabel1;
		}
		
		/**
		 * Ritorna l'etichetta del nodo in cui entra l'arco.
		 * @return nodeLabel2	etichetta nodo
		 */
		public V getNodeLabel2() {
			return nodeLabel2;
		}
        
		/**
		 * Ritorna il bit che indica la presenza dell'arco.
		 * @return edge		presenza/assenza arco
		 */
		public boolean edgeIsPresent() {
			return edge;
		}

		/**
		 * Override metodo hashCode().
		 */
		@Override
		public int hashCode() {
			final int prime = 31;		/* Numero primo */
			int result = 1;		/* Risultato */
			/* Calcolo il risultato */
			result = prime * result + (edge ? 1231 : 1237);
			result = prime * result + ((edgeLabel == null) ? 0 : edgeLabel.hashCode());
			result = prime * result + ((nodeLabel1 == null) ? 0 : nodeLabel1.hashCode());
			result = prime * result + ((nodeLabel2 == null) ? 0 : nodeLabel2.hashCode());
			return result;
		}

		/**
		 * Override metodo equals().
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;		/* Controllo se i due oggetti sono uguali */
			if (obj == null) return false;		/* Controllo se obj è null */
			@SuppressWarnings("unchecked")
			AdjacentMatrixElement other = (AdjacentMatrixElement) obj;
			/* Controllo se i due bit che indicano la presenza dell'arco sono diversi */
			if (edge != other.edge) return false;
			/* Controllo se una delle due etichette è null mentre l'altra no */
			if (edgeLabel == null) {
				if (other.edgeLabel != null) return false;
			/* Verifico se le due etichette sono uguali */	
			} else if (!edgeLabel.equals(other.edgeLabel)) return false;
			/* Verifico se uno dei due nodi sorgente è null mentre l'altro no */
			if (nodeLabel1 == null) {
				if (other.nodeLabel1 != null) return false;
			/* Verifico se i nodi sorgente sono uguali */	
			} else if (!nodeLabel1.equals(other.nodeLabel1)) return false;
			/* Verifico se uno dei due nodi di destinazione è null mentre l'altro no */
			if (nodeLabel2 == null) {
				if (other.nodeLabel2 != null) return false;
			/* Verifico se i due nodi di destinazione sono uguali */
			} else if (!nodeLabel2.equals(other.nodeLabel2)) return false;
			return true;
		}

    }
    
    /**
     *  Costruttore grafo orientato vuoto.
     */
    public GraphMatrixDirected() {
    	this.nodes = new ArrayList<Node>();		/* Creazione della lista dei nodi vuota */
    	this.adjmatrix = new ArrayList<ArrayList<AdjacentMatrixElement>>();	/* Creazione della matrice di adiacenza vuota */
    }

    /**
     *  Creazione grafo orientato non vuoto.
     *  @param nodes	set di nodi da inserire nella lista di nodi
     *  @param edges	set di archi da inserire nella matrice di adiacenza
     */
    public GraphMatrixDirected(Set<V> nodes, Set<Edge<V, E>> edges) {
    	/* Controllo che i set dei nodi e degli archi non siano vuoti */
        if (nodes == null || edges == null) throw new NullPointerException("Tentativo di creazione di grafo con insieme dei nodi o degli archi nullo");
        /* Creo la lista dei nodi vuoti */
        this.nodes = new ArrayList<Node>();
        /* Creo la matrice di adiacenza vuota */
        this.adjmatrix = new ArrayList<ArrayList<AdjacentMatrixElement>>();
        /* Aggiungo i nodi del set */
        for (V n : nodes) {
            this.addNode(n);
        }
        /* Aggiungo gli archi del set */
        for (Edge<V, E> e : edges) {
            this.addEdge(e.getLabel1(), e.getLabel2(), e.getLabel());
        }
    }
    
    /**
     * Stampa della matrice di adiacenza.
     * @return app	stringa contenente la matrice di adiacenza
     */
    public String printMatrix() {
    	String app = new String();		/* Stringa contenente la matrice di adiacenza */
    	String sign = new String();		/* Stringa di appoggio per verificare la presenza di etichette negative */
    	AdjacentMatrixElement tmp;		/* Variabile d'appoggio */
    	app += "    ";			/* Angolo della matrice */
    	for(int i = 0; i < adjmatrix.size(); i++) app += nodes.get(i).el + "    ";		/* Inserimento dei nodi nell'intestazione */
        app += "\n";	
        for (int i = 0; i < adjmatrix.size(); i++)
        {
           app += nodes.get(i).el + "   ";		/* Inserimento ogni nodo per riga */
           sign += " ";
           for (int j = 0; j < adjmatrix.size(); j++)
           {
        	  
        	  tmp = adjmatrix.get(i).get(j);
        	  if(tmp == null) app += "0";		/* Se l'arco non esiste tra due nodi inserisco 0 */
        	  else if(tmp.getEdge() == null && tmp.edgeIsPresent()) app += tmp.getEdge();		/* Se l'arco esiste ma ha etichetta nulla lo inserisco */
        	  else {	/* Se l'arco esiste inserisco l'etichetta associata all'arco nella matrice */
        		  app += adjmatrix.get(i).get(j).getEdge();
        		  sign += tmp.getEdge();
        	  }
        	  /* Inserisco lo spazio tra ogni colonna */
              if(tmp == null) app += "    ";	  
              else if(tmp.getEdge() == null && tmp.edgeIsPresent()) app += " ";
              else if(sign.contains("-")) app += " ";
              else app += "  ";
           }

           app += "\n";		
           sign = new String();		/* Azzero la stringa d'appoggio */
        }
    	return app;		/* Ritorno la stringa contenente la matrice di adiacenza */
    }
    
    /**
     * Ritorna la dimensione della matrice di adiacenza
     */
	@Override
	public int size() {
		return adjmatrix.size();
	}

	/**
	 * Ritorna true nel caso in cui il grafo sia vuoto.
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Ritorna true se il grafo è orientato.
	 */
	@Override
	public boolean isDirected() {
		return true;
	}

	/**
	 * Aggiunge un nuovo nodo al grafo.
	 * @param label		nodo da inserire nel grafo
	 * @return true 	se il nodo è stato inserito correttamente
	 */
    @Override
    public boolean addNode(V label) {
    	/* Verifico che il nodo non sia null */
        if (label == null) throw new NullPointerException("Tentativo di inserimento di nodo null");
        /* Verifico che il nodo non sia già presente nel grafo */
        if (this.containsNode(label)) {
            return false;
        } else {
            /* Aggiungo il nodo nella lista dei nodi */
            nodes.add(new Node(label));
            /* Creo una nuova riga della matrice di adiacenza */
            ArrayList<AdjacentMatrixElement> row = new ArrayList<AdjacentMatrixElement>(nodes.size()-1);
            for (int i = 0; i < nodes.size(); i++) {
                  row.add(null);	/* Inizializzo tutti gli elementi della riga */
            }
            adjmatrix.add(row);		/* Aggiungo la riga alla matrice di adiacenza */
            resizeMatrix();		/* Ridimensiono la matrice */
            return true;		/* Il nodo è stato inserito correttamente per cui ritorno true */
        }
    }

    /**
     * Metodo privato utilizzato per ridimensionare la matrice dopo l'aggiunta di un 
     * nuovo nodo nel grafo.
     */
    private void resizeMatrix() {
    	/* Utilizzo due indici per scorrere righe e colonne */
    	for (int i = 0; i < nodes.size(); i++) {
           for (int j = 0; j < nodes.size(); j++) {
        	   int size = adjmatrix.get(i).size();	  /* Trovo la dimensione delle righe */
        	   /* Se la dimensione delle righe è maggiore delle colonne, aggiungo elementi null alle
        	    * colonne in quanto la matrice di adiacenza dev'essere quadrata
        	    */
        	   if(size < nodes.size())  adjmatrix.get(i).add(null);
           }
        }
    }
    
    /**
     * Rimuove un nodo dal grafo.
     * @param label		nodo da cancellare dal grafo
     * @return true 	se il nodo viene cancellato correttamente
     */
	@Override
	public boolean removeNode(V label) {
		/* Controllo che il nodo da cancellare non sia null */
        if (label == null) throw new NullPointerException("Tentativo di cancellazione di nodo null.");
        /* Controllo che il nodo sia presente nel grafo */
	    if(!this.containsNode(label)) throw new NoSuchElementException("Il nodo non è presente nel grafo.");
	    else {
	    	int index = this.getNodeIndex(label);		/* Trovo l'indice in cui è memorizzato il nodo */
	    	this.nodes.remove(this.getNodeIndex(label));		/* Rimuovo il nodo dalla lista dei nodi */
	    	/* Rimuovo la colonna del nodo nella matrice */
			for(int i=0; i<nodes.size(); i++)   adjmatrix.get(i).remove(index); 		
			    adjmatrix.remove(index);		/* Rimuovo la riga del nodo */
	    	return true;		/* Il nodo è stato rimosso correttamente per cui ritorno true */
	    }
	}

	/**
	 * Verifico se un nodo è presente nel grafo.
	 * @param label		nodo da cercare nel grafo
	 * @return true 	se il nodo viene trovato nel grafo
	 */
    @Override
    public boolean containsNode(V label) {
    	/* Verifico che label non sia null */
        if (label == null) throw new NullPointerException("Tentativo di ricerca di nodo null");
        return this.nodes.contains(new Node(label));
    }

    /**
     * Ritorna la posizione del nodo passato come parametro.
	 * @param label		nodo da cercare nel grafo
	 * @return l'indice del nodo nel caso in cui sia presente nel grafo
     */
	@Override
    public int getNodeIndex(V label) {
		/* Controllo che label non sia null */
        if (label == null) throw new NullPointerException("Tentativo di ricerca dell'indice di un nodo nullo.");
        int i = 0;		/* Indice di appoggio */
        boolean trovato = false;		/* Variabile boolean di appoggio per verificare se viene trovato il nodo */
        V nodeLabel = null;			/* Creo un nodo di appoggio */
        /* Scorro la lista dei nodi */
        while (i < this.nodes.size() && !trovato) {
            nodeLabel = this.nodes.get(i).el;
            if (nodeLabel.equals(label))
                trovato = true;    /* Quando trovo il nodo imposto trovato a true */
            else
                i++; /* Altrimenti incremento l'indice */
        }
        if (trovato)
            return i;		/* Ritorno l'indice corrispondente al nodo */
        else return -1;		/* Altrimenti ritorno -1 */
    }

	/**
	 * Restituisce il nodo all'indice i.
	 * @param i		indice del nodo da ritornare
	 * @return nodo corrispondente all'indice i
	 */
	@Override
	public V getNodeAtIndex(int i) {
		/* Controllo che i non sia inferiore a 0 o superiore al numero di nodi presenti nel grafo */
		if(i<0 || i >= this.size()) throw new IndexOutOfBoundsException("L'indice non corrisponde a nessun nodo.");
		return this.nodes.get(i).el;
	}

	/**
	 * Ritorna il colore corrispondente a label.
	 * @param label 	nodo
	 * @return colore del nodo
	 */
	@Override
	public int getColor(V label) {
        /* Controllo che label non sia null */
        if (label == null) throw new NullPointerException("Il nodo passato è null.");
        /* Cerco l'indice corrispondente a label */
        int i = this.getNodeIndex(label);
        /* Restituisco il colore di label */
        return this.nodes.get(i).color;
	}

	/**
	 * Imposta il colore del nodo passato come parametro.
	 * @param label		nodo 
	 * @param color		colore da impostare al nodo
	 */
	@Override
	public void setColor(V label, int color) {
        /* Controllo che label non sia null */
        if (label == null) throw new NullPointerException("Tentativo di assegnare un colore ad un nodo null.");
        /* Cerco l'indice corrispondente a label */
        int i = this.getNodeIndex(label);
        /* Assegno il colore al nodo */
        this.nodes.get(i).color = color;		
	}

	/**
	 * Restituisce il grado di un nodo. Nel caso di grafo diretto è la somma del
     * grado in entrata e del grado in uscita.
     * @param label		nodo di cui si vuole sapere il grado
     * @return il grado del nodo 
	 */
	@Override
	public int getDegree(V label) {
		/* Controllo che label non sia null */
		if (label == null) throw new NullPointerException("Il nodo di cui si vuole sapere il grado è null.");
		/* Cerco l'indice del nodo */
		int index = this.getNodeIndex(label);
		int outgoingedges = 0;		/* Grado uscente */ 
		int ingoingedges = 0;		/* Grado entrante */
		int degree = 0;				/* Grado del nodo */
		
		/* Scorro la colonna corrispondente al nodo per contare gli archi uscenti */
		for(int i=0; i<adjmatrix.size(); i++) {
			 if(adjmatrix.get(index).get(i) != null) {
				 outgoingedges++;		
			 }
		}	 
		/* Scorro la riga corrispondente al nodo per contare gli archi entranti */
		for(int i=0; i<adjmatrix.size(); i++) {
			 if(adjmatrix.get(i).get(index) != null) {
				 ingoingedges++;
			 }
		}
		degree = ingoingedges + outgoingedges;		/* Sommo il grado uscente con quello entrante per sapere il grado del nodo */
		return degree;		/* Ritorno il grado */
	}

	/**
	 * Metodo non supportato per grafi orientati.
	 */
	@Override
	public Set<V> neighbors(V label) {
        /*
         * Questo grafo è diretto quindi lancio l'eccezione di operazione
         * non supportata.
         */
        throw new UnsupportedOperationException("Non è possibile restituire l'insieme dei nodi adiacenti in quanto il grafo è orientato.");
	}

	/**
	 * Ritorna un set contenente i nodi collegati al nodo sorgente
	 * con etichetta label
	 * @param label		nodo di cui si vogliono conoscere le adiacenze
	 * @return set di nodi adiacenti a label
	 */
	@Override
	public Set<V> successors(V label) {
		/* Creo un set di nodi vuoto */
		HashSet<V> nodes = new HashSet<V>();
		/* Creo un set contenente gli archi uscenti da label */
		Set<Edge<V, E>> outgoingEdges = this.outgoingEdges(label);
        Iterator<Edge<V, E>> it = outgoingEdges.iterator();		/* Iteratore per gli archi */
        /* Scorro la lista di archi */
        while(it.hasNext()) {
        	Edge<V, E> am = it.next();
        	nodes.add(am.getLabel2());		/* Ottengo i nodi collegati agli archi uscenti di label */
        }
		return nodes;	/* Ritorno il set */
	}

	/**
	 * Ritorna un set contenente i nodi collegati al nodo di
	 * destinazione con etichetta label
	 * @param label		nodo di cui si vogliono conoscere le adiacenze
	 * @return set di nodi adiacenti a label
	 */	
	@Override
	public Set<V> predecessors(V label) {
		/* Creo un set di nodi vuoto */
		Set<V> nodes = new HashSet<V>();
		/* Invoco ingoingEdges per ottenere gli archi entranti in label */
		Set<Edge<V, E>> ingoingEdges = this.ingoingEdges(label);
		/* Creo un iteratore per gli archi */
        Iterator<Edge<V, E>> it = ingoingEdges.iterator();
        while(it.hasNext()) {
        	Edge<V, E> am = it.next();
        	/* Aggiungo al set di nodi tutti i nodi da cui escono
        	 * gli archi che entrano in label
        	 */
        	nodes.add(am.getLabel1());
        }
		return nodes;		/* Ritorno il set */
	}

	/**
	 * Ritorna l'insieme dei nodi presenti nel grafo.
	 * @return set		nodi del grafo
	 */
	@Override
	public Set<V> getNodes() {
        /* Creo un set che conterrà i nodi presenti nel grafo */
        Set<V> ret = new HashSet<V>();
        /* Aggiungo tutti i nodi del grafo al set */
	    for(int i=0; i<this.size(); i++) {
	    	 ret.add(this.getNodeAtIndex(i));
	    }
        return ret;		/* Ritorno il set di nodi */
	}

	/**
	 * Aggiunge un arco con etichetta label tra due nodi passati come
	 * parametri.
	 * @param label1		etichetta primo nodo
	 * @param label2 		etichetta secondo nodo
	 * @param label			etichetta arco
	 * @return true			se l'arco viene aggiunto correttamente
	 */
    @Override
    public boolean addEdge(V label1, V label2, E label) {
    	/* Controllo che le etichette dei nodi non siano null */
        if (label1 == null || label2 == null) throw new NullPointerException("Tentativo di inserire un arco tra uno o entrambi nodi nulli.");
        /* Controllo che i nodi siano effettivamente presenti nel grafo */
        if (this.getNodeIndex(label1) == -1 || this.getNodeIndex(label2) == -1) 
        	throw new IllegalArgumentException("Tentativo di inserire un arco tra uno o entrambi nodi non esistenti");
        /* Controllo che l'arco non sia già presente nel grafo */
        if (this.containsEdge(label1, label2, label)) return false;
        boolean edge = true;		/* Variabile booleana che specifica la presenza dell'arco */
        int index = this.getNodeIndex(label1);		/* Ottengo l'indice del nodo da cui uscirà l'arco */
        int index2 = this.getNodeIndex(label2);		/* Ottengo l'indice del nodo in cui entrerà l'arco */
        /* Creo un nuovo elemento della matrice di adiacenza */
        AdjacentMatrixElement m = new AdjacentMatrixElement(label1, label2, label, edge);
        /* Inserisco l'elemento appena creato nella giusta posizione nella matrice di adiacenza */
        adjmatrix.get(index).set(index2, m);
        return true;
    }

    /**
     * Rimuove l'arco che collega i due nodi specificati.
	 * @param label1		etichetta del primo nodo
	 * @param label2		etichetta del secondo nodo
	 * @param label			etichetta dell'arco
	 * @return true se l'arco è presente e viene rimosso correttamente
     */
	@Override
	public boolean removeEdge(V label1, V label2, E label) {
        /* Controllo che i due nodi non siano null entrambi o uno dei due */
        if (label1 == null || label2 == null) throw new NullPointerException("Tentativo di rimuovere un arco tra uno o entrambi nodi nulli.");
        /* Controllo che i nodi siano effettivamente presenti nel grafo */
        if (this.getNodeIndex(label1) == -1 || this.getNodeIndex(label2) == -1)
            throw new IllegalArgumentException("Tentativo di inserire un arco tra uno o entrambi nodi non esistenti.");
        /* Controllo che tra i due nodi ci sia un arco con etichetta label che li collega */
        if (!this.containsEdge(label1, label2, label)) return false;
        int index = this.getNodeIndex(label1);		/* Ottengo l'indice del primo nodo */	
		int index2 = this.getNodeIndex(label2);		/* Ottengo l'indice del secondo nodo */
        adjmatrix.get(index).set(index2, null);		/* Cancello l'arco */
		return true;
	}

	/**
	 * Verifica la presenza di un arco tra label1 e label2.
	 * @param label1		etichetta del primo nodo
	 * @param label2		etichetta del secondo nodo
	 * @param label			etichetta dell'arco
	 * @return true se l'arco è presente
	 */
	@Override
	public boolean containsEdge(V label1, V label2, E label) {
		int index = this.getNodeIndex(label1);		/* Ottengo l'indice del primo nodo */
		int index2 = this.getNodeIndex(label2);		/* Ottengo l'indice del secondo nodo */
		if(adjmatrix.get(index).get(index2) == null) return false;		/* Cerco nella matrice se l'arco è presente */
		/* Verifico che l'etichetta dell'arco corrisponda */
		else if(adjmatrix.get(index).get(index2) != null && adjmatrix.get(index).get(index2).getEdge().equals(label)) return true;
		else return false;
	}

	/**
	 *  Restituisce l'insieme degli archi tra due nodi specificati.
	 *  @param label1		etichetta del primo nodo
	 *  @param label2 		etichetta del secondo nodo
	 *  @return l'insieme degli archi che collegano label1 e label2
	 */
	@Override
	public Set<Edge<V, E>> getEdges(V label1, V label2) {
		/* Creo un set vuoto per gli archi */
	    Set<Edge<V, E>> archi = new HashSet<Edge<V, E>>();
	    int index = this.getNodeIndex(label1);		/* Ottengo l'indice del primo nodo */
		int index2 = this.getNodeIndex(label2);		/* Ottengo l'indice del secondo nodo */
		E tmp = adjmatrix.get(index).get(index2).getEdge();		/* Ottengo l'arco che li collega */
        archi.add(new Edge<V, E>(label1, label2, tmp, true));		/* Aggiungo i nodi e l'arco nel set */
		return archi;
	}

	/**
	 * Restituisce l'arco uscente da label1 ed entrante in label2.
	 * @param label1		etichetta del nodo sorgente
	 * @param label2		etichetta del nodo adiacente 
	 * @return arco			arco che collega i due nodi
	 */
	private E getEdge(V label1, V label2) {
	    int index = this.getNodeIndex(label1);		/* Indice del primo nodo */
		int index2 = this.getNodeIndex(label2);		/* Indice del secondo nodo */
		/* Ottengo l'arco che collega i due nodi */
		E tmp = adjmatrix.get(index).get(index2).getEdge();
		return tmp;
	}
	
	/**
	 * Operazione non supportata per grafo diretto.
	 */
	@Override
	public Set<Edge<V, E>> getEdges(V label) {
        /*
         * Questo grafo è diretto quindi lancio l'eccezione di operazione
         * non supportata.
         */
        throw new UnsupportedOperationException("Non è possibile restituire l'insieme di tutti gli archi connessi a un certo nodo in " +
         "quanto il grafo è orientato.");
	}
	
    /**
     * Restituisce l'insieme di tutti gli archi del grafo
     * @return un insieme contenente tutti gli archi del grafo
     */
	@Override
	public Set<Edge<V, E>> getEdges() {
		/* Creo un set di archi vuoto */
		Set<Edge<V, E>> archi = new HashSet<Edge<V, E>>();
		/* Scorro la matrice di adiacenza */
	    for (int i = 0; i < nodes.size(); i++) {
	        for (int j = 0; j < nodes.size(); j++) {
	  			if(adjmatrix.get(i).get(j) != null) {
	  				/* Ottengo i nodi tra loro adiacenti */
	  				V label = adjmatrix.get(i).get(j).getNodeLabel1();
					V label2 = adjmatrix.get(i).get(j).getNodeLabel2();
					/* Inserisco nel set i nodi ed i relativi archi che li collegano */
			        archi.add(new Edge<V, E>(label,label2 , adjmatrix.get(i).get(j).getEdge(),true));
				 }
	        }
	    }
		return archi;		/* Ritorno il set */
	}

	/**
	 * Restituisce l'insieme di tutti gli archi uscenti dal nodo label.
	 * @param label		nodo di cui si vogliono conoscere gli archi uscenti
	 * @return set 		contenente gli archi uscenti
	 */
	@Override
	public Set<Edge<V, E>> outgoingEdges(V label) {
		/* Creo un set vuoto per gli archi uscenti */
		Set<Edge<V, E>> archi = new HashSet<Edge<V, E>>();
		/* Ottengo l'indice corrispondente a label */
	    int index = this.getNodeIndex(label);
	       for (int i = 0; i < nodes.size(); i++) {
	    	   /* Scorro la colonna corrispondente a label nella matrice di adiancenza
	    	    * per determinare gli archi uscenti
	    	    */
	  		   if(adjmatrix.get(index).get(i) != null) {
	  			   /* Ottengo il nodo in cui entra l'arco uscente da label */
	  			   V label2 = adjmatrix.get(index).get(i).getNodeLabel2();
				   /* Aggiungo al set il nodo in cui l'arco entra, label ovvero
				    * il nodo in cui l'arco esce, l'etichetta del nodo ed il
				    * bit che indica la presenza dell'arco
				    */
			       archi.add(new Edge<V, E>(label,label2 , adjmatrix.get(index).get(i).getEdge(),true));
				 }
	        }
		return archi;		/* Ritorno il set */
	}

	/**
	 * Restituisce l'insieme di tutti gli archi entranti nel nodo label.
	 * @param label		etichetta del nodo di cui si vogliono conoscere gli archi entranti
	 * @return set 		contenente gli archi entranti
	 */
	@Override
	public Set<Edge<V, E>> ingoingEdges(V label) {
		/* Creo un set vuoto per gli archi entranti */
		Set<Edge<V, E>> archi = new HashSet<Edge<V, E>>();
		/* Ottengo l'indice di label */
	    int index = this.getNodeIndex(label);
	    /* Scorro le righe della matrice di adiacenza */
		for(int i=0; i<adjmatrix.size(); i++) {
			 if(adjmatrix.get(i).get(index) != null) {
				 /* Ottengo il nodo da cui esce l'arco entrante in label */
				 V label2 = adjmatrix.get(i).get(index).getNodeLabel1();	
				 /* Aggiungo al set il nodo da cui l'arco esce, label ovvero
				  * il nodo in cui l'arco entra, l'etichetta del nodo ed il
				  * bit che indica la presenza dell'arco
				  */
		         archi.add(new Edge<V, E>(label2,label , adjmatrix.get(i).get(index).getEdge(),true));
			 }
		}
		return archi;		/* Ritorno il set */
	}


	/**
	 * Conta il numero di archi del grafo.
	 * @return il numero di archi del grafo
	 */
	@Override
	public int edgeCount() {
		int count = 0;	/* Contatore */
		/* Scorro le righe e le colonne della matrice di adiancenza
		 * nel caso in cui trovo un valore diverso da null, significa
		 * è presente un arco in quella posizione per cui incremento il
		 * contatore
		 */
	    for (int i = 0; i < adjmatrix.size(); i++) {
	        for (int j = 0; j < adjmatrix.size(); j++) {
	    		if(adjmatrix.get(i).get(j) != null){
	    			count++;		
	    		}
	           }
	        }
		return count;    /* Ritorno il numero di archi */
	}

    /**
     * Cancella tutti i nodi e gli archi di del grafo.
     */
	@Override
	public void clear() {
	       this.adjmatrix.clear();		/* Azzeramento matrice adiacenza */
	       this.nodes.clear();			/* Azzeramento lista di nodi */
	}
  
	/**
	 * Rappresentazione dei nodi del grafo con i relativi costi associati agli archi
	 * che li collegano.
	 */
	public String toString() {
	    String app = new String();		/* Stringa che conterrà il grafo */
	    /* Per ogni nodo del grafo cerco i successori */
        for(int i=0; i<nodes.size(); i++) {		
        	V node = this.getNodeAtIndex(i);
        	Set<V> n = this.successors(node);
     	    Iterator<V> it = n.iterator();
     	    /* Scorro i successori di ogni nodo */
    	    while(it.hasNext()) {
    		   V s = it.next();
    		   /* Aggiungo alla stringa il nodo e gli archi i suoi successori */
    		   app += "("+node+") --> ("+s+")\t";	
    		   /* Ottengo gli archi che collegano ogni nodo ai successori */
    		   E edge = this.getEdge(node, s);
    		   /* Aggiungo alla stringa la coppia di nodi ed il peso dell'arco che li collega */
    		   app += "w("+node+","+s+") = " + edge;		
        	   app += "\n";
	        }
		}
        return app;
	}

	/**
	 * Override metodo hashCode().
	 */
	@Override
	public int hashCode() {
		final int prime = 31;	 /* Numero primo */
		int result = 1;		/* Risultato */
		/* Calcolo il risultato */
		result = prime * result + ((adjmatrix == null) ? 0 : adjmatrix.hashCode());
		result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
		return result;
	}

	/**
	 * Override del metodo equals().
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;		/* Controllo se gli oggetti sono uguali */
		if (obj == null) return false;		/* Controllo se obj è null */
		 /* Verifico che obj sia un'istanza di GraphMatrixDirected */
		if(!(obj instanceof GraphMatrixDirected)) return false;   
		@SuppressWarnings("unchecked")
		GraphMatrixDirected<V,E> other = (GraphMatrixDirected<V,E>) obj;
		/* Verifico se una delle due matrici di adiacenza è null mentre l'altra no */
		if (adjmatrix == null) {
			if (other.adjmatrix != null) return false;
	    /* Verifico se le matrici di adiacenza sono diverse */
		} else if (!adjmatrix.equals(other.adjmatrix)) return false;
		/* Verifico i nodi */
		if (nodes == null) {
			if (other.nodes != null) return false;
	    /* Verifico che i nodi dei grafi siano uguali */
		} else if (!nodes.equals(other.nodes)) return false;
		return true;
	}
	
	
}
