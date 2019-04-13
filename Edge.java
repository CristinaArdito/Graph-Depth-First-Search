/**
 * Un oggetto di questa classe rappresenta un arco di un grafo i cui nodi o
 * vertici sono etichettati con oggetti della classe V e i cui archi sono
 * etichettati con oggetti della classe E.
 * 
 * L'arco può essere diretto o non diretto. L'etichetta dell'arco può essere
 * nulla.
 * 
 * @author Luca Tesei
 *
 */
public class Edge<V, E> {
    private final V label1;

    private final V label2;

    private final E label;

    private final boolean directed;

    /**
     * Costruisce un arco di un grafo senza etichetta.
     * 
     * @param label1
     *            etichetta del primo nodo (nodo sorgente in caso di grafo
     *            diretto).
     * @param label2
     *            etichetta del secondo nodo (nodo destinazione in caso di grafo
     *            diretto).
     * @param directed
     *            true se l'arco è diretto, false altrimenti.
     * @throws NullPointerException
     *             se le etichette label1 o label2 sono nulle.
     */
    public Edge(V label1, V label2, boolean directed) {
        if (label1 == null || label2 == null)
            throw new NullPointerException(
                    "Tentativo di creare un arco con etichette dei nodi nulle");
        this.label1 = label1;
        this.label2 = label2;
        this.label = null;
        this.directed = directed;
    }

    /**
     * Costruisce un arco di un grafo con etichetta.
     * 
     * @param label1
     *            etichetta del primo nodo (nodo sorgente in caso di grafo
     *            diretto).
     * @param label2
     *            etichetta del secondo nodo (nodo destinazione in caso di grafo
     *            diretto).
     * @param label
     *            etichetta dell'arco.
     * @param directed
     *            true se l'arco è diretto, false altrimenti.
     * @throws NullPointerException
     *             se le etichette label1 o label2 sono nulle.
     */
    public Edge(V label1, V label2, E label, boolean directed) {
        if (label1 == null || label2 == null)
            throw new NullPointerException(
                    "Tentativo di creare un arco con etichette dei nodi nulle");
        this.label1 = label1;
        this.label2 = label2;
        this.label = label;
        this.directed = directed;
    }

    /**
     * Restituisce il primo nodo di questo arco, la sorgente in caso di arco
     * diretto.
     * 
     * @return il primo nodo di questo arco, la sorgente in caso di arco
     *         diretto.
     */
    public V getLabel1() {
        return this.label1;
    }

    /**
     * Restituisce il secondo nodo di questo arco, la destinazione in caso di
     * arco diretto.
     * 
     * @return il secondo nodo di questo arco, la destinazione in caso di arco
     *         diretto.
     */
    public V getLabel2() {
        return this.label2;
    }

    /**
     * Restituisce l'etichetta di questo arco.
     * 
     * @return l'etichetta correntemente associata a questo arco.
     */
    public E getLabel() {
        return this.label;
    }

    /**
     * Indica se questo arco è diretto o no.
     * 
     * @return true se questo arco è diretto, false altrimenti.
     */
    public boolean isDirected() {
        return this.directed;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (directed ? 1231 : 1237);
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((label1 == null) ? 0 : label1.hashCode());
        result = prime * result + ((label2 == null) ? 0 : label2.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object) Uguaglianza tra tutte le
     * componenti.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Edge))
            return false;
        Edge<?, ?> other = (Edge<?, ?>) obj;
        if (directed != other.directed)
            return false;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        if (label1 == null) {
            if (other.label1 != null)
                return false;
        } else if (!label1.equals(other.label1))
            return false;
        if (label2 == null) {
            if (other.label2 != null)
                return false;
        } else if (!label2.equals(other.label2))
            return false;
        return true;
    }

}
