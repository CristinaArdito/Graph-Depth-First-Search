
public class NodeDFS implements GenericDFSNode {
	
	/* Etichetta del nodo */
    private final String id;

    /* Padre del nodo */
    private NodeDFS father;
    
    /* Tempo in cui il nodo è stato scoperto */
    private int timeDiscovered;
    
    /* Tempo in cui il nodo è stato esplorato */
    private int timeExplored;

    /**
     * Costruttore di un generico nodo con etichetta passata come parametro.
     * @param id		etichetta del nodo
     */
    public NodeDFS(String id) {
        this.id = id;		/* Imposto l'etichetta del nodo */
        this.father = null;		/* Imposto il padre a null */
    }
    
    /**
     * Ritorna l'etichetta di un nodo.
     * @return id		etichetta del nodo
     */
	public String getId() {
		return id;
	}

	/**
	 * Imposta il tempo discreto in cui viene scoperto un nodo.
	 * @param time			tempo di scoperta del nodo
	 */
	@Override
	public void setTimeDiscovered(int time) {
		this.timeDiscovered = time;
	}

	   /**
     * Ritorna il tempo discreto in cui è stato scoperto un nodo.
     * @return time			tempo di scoperta del nodo
     */
	@Override
	public int getTimeDiscovered() {
		return this.timeDiscovered;
	}

	/**
	 * Imposta il tempo discreto in cui viene esplorato un nodo.
	 * @param time		tempo di esplorazione del nodo
	 */
	@Override
	public void setTimeExplored(int time) {
		this.timeExplored = time;
	}

	/**
     * Ritorna il tempo discreto in cui è stato esplorato un nodo.
     * @return time			tempo di esplorazione del nodo
     */
	@Override
	public int getTimeExplored() {
		return this.timeExplored;
	}

    /**
     * Ritorna il padre di un nodo
     * @return padre 		padre di un nodo
     */
	@Override
	public GenericDFSNode getFather() {
		return this.father;
	}
	
    /**
     * Imposta il padre di un nodo
     * @param father		padre del nodo
     */
	@Override
	public void setFather(GenericDFSNode father) {
        if (father == null) {		/* Controllo se il padre è null */
            this.father = null;
            return;
        }
        if (father instanceof NodeDFS)		/* Se non è null verifico che appartenga alla classe NodeDFS */
            this.father = (NodeDFS) father;		/* In tal caso, lo imposto */
        else		/* Altrimenti lancio un'eccezione */
            throw new IllegalArgumentException("Inserimento di un nodo padre non del tipo NodeDFS");
	}
	
	/**
	 * Override del metodo toString().
	 * Ritorna l'etichetta del nodo.
	 */
    @Override
    public String toString() {
        return this.id;
    }
    
    
    /**
     * Override del metodo hashCode().
     */
    @Override
	public int hashCode() {
		final int prime = 31;		/* Numero primo */
		int result = 1;			/* Risultato */
		/* Calcolo il risultato */
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

    /** 
     * Override del metodo equals().
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;		/* Controllo se gli oggetti sono uguali */
		if (obj == null) return false;		/* Controllo se obj è null */
		if(!(obj instanceof NodeDFS)) return false;		/* Controllo se obj è un'istanza di NodeDFS */
		NodeDFS other = (NodeDFS) obj;
		/* Verifico se uno dei due id è null mentre l'altro no */
		if (id == null) {
			if (other.id != null) return false;
		/* Verifico se gli id sono uguali */
		} else if (!id.equals(other.id)) return false;
		return true;
	}

	/**
	 * Ritorna una stringa contenente l'etichetta del nodo ed il tempo in cui
	 * tale nodo è stato scoperto e successivamente esplorato.
	 * @return app		stringa contenete l'etichetta del nodo ed i tempi in cui 
	 * 					è stato scoperto ed esplorato completamente
	 */
	public String printNodeTime() {
    	String app = new String();		/* Stringa vuota */
    	/* Inserisco nella stringa l'etichetta del nodo, il tempo in cui è stato
    	 * scoperto e quello in cui è stato completamente esplorato
    	 */
    	app += "Node: " +this.id + "\t\t Discovered at:" + this.getTimeDiscovered() +"\t Explored at: " + this.getTimeExplored();
    	app += "\n";
    	return app;
    }

}
