
public class WeighedEdge<E> implements Weight<E> {
	
	/* Peso associato all'arco */
    private final E weight;

    
    /**
     * Costruttore di un arco pesato.
     * @param weight		peso dell'arco
     */
    public WeighedEdge(E weight) {
        this.weight = weight;
    }
    
    /**
     * Override del metodo toString().
     * Ritorna una stringa contenente il peso dell'arco.
     */
    @Override
    public String toString() {
    	String app = new String();		/* Creo una stringa vuota */
    	app += weight;		/* Ci aggiungo il peso dell'arco */
        return app;
        
    }

	/**
	 * Ritorna il peso di un generico arco.
	 * @return peso			peso dell'arco
	 */
    @Override
    public E getWeight() {
        return weight;
    }

    /**
     * Override del metodo hashCode().
     */
	@Override
	public int hashCode() {
		final int prime = 31;		/* Numero primo */
		int result = 1;		/* Risultato */
		/* Calcolo il risultato */
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

    /** 
     * Override del metodo equals().
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;		/* Controllo se gli oggetti sono uguali */
		if (obj == null) return false;		/* Controllo se obj è null */
		if(!(obj instanceof WeighedEdge)) return false;		/* Controllo se obj è un'istanza di WeighedEdge */
		@SuppressWarnings("unchecked")
		WeighedEdge<E> other = (WeighedEdge<E>) obj;
		/* Controllo se uno dei due archi ha peso nullo mentre l'altro no */
		if (weight == null) {
			if (other.weight != null) return false;
	    /* Controllo se i due archi hanno lo stesso peso */
		} else if (!weight.equals(other.weight)) return false;
		return true;
	}

}
