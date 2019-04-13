import java.util.Set;

/**
 * Interfaccia per un generico grafo i cui vertici o nodi sono etichettati con
 * elementi della classe V ed i cui archi sono etichettati con elementi della
 * classe E.
 * 
 * Il grafo può essere diretto o non diretto, la classe che implementa questa
 * interfaccia specifica questo aspetto.
 * 
 * Le etichette dei vertici o nodi sono obbligatorie ed uniche, cioè un nodo non
 * può avere etichetta nulla e due nodi con la stessa etichetta sono lo stesso
 * nodo.
 * 
 * Le etichette degli archi sono facoltative.
 * 
 * A nodi può essere associato un colore che può
 * essere usato dagli algoritmi generici che lavorano su grafi.
 * 
 * @author Luca Tesei
 *
 */
public interface Graph<V, E> {

    /**
     * Colore bianco di un nodo o di un arco del grafo.
     */
    public static int COLOR_WHITE = 0;

    /**
     * Colore grigio di un nodo o di un arco del grafo.
     */
    public static int COLOR_GREY = 1;

    /**
     * Colore nero di un nodo o di un arco del grafo.
     */
    public static int COLOR_BLACK = 2;

    /**
     * Restituisce il numero di nodi di questo grafo.
     * 
     * @return in numero di nodi di questo grafo.
     */
    public int size();

    /**
     * Determina se questo grafo è vuoto.
     * 
     * @return se questo grafo è vuoto, false altrimenti.
     */
    public boolean isEmpty();

    /**
     * Dice se questo grafo è diretto oppure no.
     * 
     * @return true se questo grafo è diretto, false altrimenti.
     */
    public boolean isDirected();

    /**
     * Aggiunge un nodo a questo grafo.
     * 
     * @param label
     *            l'etichetta del nuovo nodo.
     * @return true se il nodo è stato aggiunto, false altrimenti cioè se
     *         l'etichetta è già presente.
     * @throws NullPointerException
     *             se l'oggetto passato è null.
     */
    public boolean addNode(V label);

    /**
     * Remove a node from this graph.
     * 
     * @param label
     *            l'etichetta del nodo da rimuovere.
     * @return true se il nodo è stato rimosso, false se il nodo non era
     *         presente.
     * @throws NullPointerException
     *             se l'oggetto passato è null.
     */
    public boolean removeNode(V label);

    /**
     * Determina se c'è un nodo in questo grafo con una certa etichetta data.
     * 
     * @param label
     *            l'etichetta cercata.
     * @return true se esiste un nodo di questo grafo etichettato con label.
     * @throws NullPointerException
     *             se l'oggetto passato è null.
     */
    public boolean containsNode(V label);

    /**
     * Restituisce un indice unico attualmente associato a un certo nodo
     * nell'intervallo <code>[0, this.size() - 1]</code>. Tale indice può essere
     * usato per identificare i nodi in strutture dati esterne come array o
     * liste che contengono informazioni aggiuntive calcolate, ad esempio, da un
     * algoritmo sul grafo.
     * 
     * @param label
     *            l'etichetta del nodo.
     * 
     * @return un indice unico nell'intervallo <code>[0, this.size() - 1]</code>
     *         attualmente associato al nodo con etichetta label. L'indice può
     *         cambiare se il grafo viene modificato togliendo dei nodi.
     * 
     * @throws NullPointerException
     *             se l'oggetto passato è null.
     * @throws IllegalArgumentException
     *             se il nodo non esiste.
     * 
     */
    public int getNodeIndex(V label);

    /**
     * Restituisce l'etichetta del nodo attualmente associato a un certo indice
     * nell'intervallo <code>[0, this.size() - 1]</code>.
     * 
     * @param i
     *            l'indice del nodo.
     * @return l'etichetta del nodo correntemente associato all'indice i.
     * 
     * @throws IndexOutOfBoundsException
     *             se l'indice passato non corrisponde a nessun nodo o è fuori
     *             dai limiti dell'intervallo <code>[0, this.size() - 1]</code>.
     */
    public V getNodeAtIndex(int i);

    /**
     * Restituisce il colore correntemente associato a un nodo di questo grafo.
     * 
     * @param label
     *            l'etichetta del nodo.
     * @return il colore correntemente associato al nodo indicato.
     * 
     * @throws IllegalArgumentException
     *             se il nodo non esiste.
     * @throws NullPointerException
     *             se il nodo passato è nullo.
     */
    public int getColor(V label);

    /**
     * Assegna un colore a un certo nodo di questo grfo.
     * 
     * @param label
     *            l'etichetta del nodo.
     * @param color
     *            il colore da assegnare.
     * @throws IllegalArgumentException
     *             se il nodo non esiste.
     * @throws NullPointerException
     *             se il nodo passato è nullo.
     */
    public void setColor(V label, int color);

    /**
     * Restituisce il grado di un nodo. Nel caso di grafo diretto è la somma del
     * grado in entrata e del grado in uscita.
     * 
     * @param label
     *            il nodo.
     * @return il grado del nodo in questo grafo.
     * 
     * @throws IllegalArgumentException
     *             se il nodo non esiste.
     * @throws NullPointerException
     *             se il nodo passato è nullo.
     */
    public int getDegree(V label);

    /**
     * Restituisce l'insieme di tutti i nodi adiacenti a un certo nodo in un
     * grafo non diretto.
     * 
     * @param label
     *            l'etichetta del nodo.
     * @return l'insieme di tutti i nodi adiacenti al nodo label in un grafo non
     *         diretto.
     * @throws UnsupportedOperationException
     *             se il grafo su cui il metodo è chiamato è diretto.
     * @throws IllegalArgumentException
     *             se il nodo non esiste.
     * @throws NullPointerException
     *             se il nodo passato è nullo.
     */
    public Set<V> neighbors(V label);

    /**
     * Restituisce i nodi collegati a un certo nodo sorgente in un grafo
     * diretto.
     * 
     * @param label
     *            il nodo sorgente.
     * @return l'insieme dei nodi destinazione collegati al nodo sorgente dato
     *         in un grafo diretto.
     * @throws UnsupportedOperationException
     *             se il grafo su cui il metodo è chiamato non è diretto.
     * @throws IllegalArgumentException
     *             se il nodo non esiste.
     * @throws NullPointerException
     *             se il nodo passato è nullo.
     * 
     */
    public Set<V> successors(V label);

    /**
     * Restituisce i nodi collegati a un certo nodo destinazione in un grafo
     * diretto.
     * 
     * @param label
     *            il nodo destinazione.
     * @return l'insieme dei nodi sorgente collegati al nodo destinazione dato
     *         in un grafo diretto.
     * @throws UnsupportedOperationException
     *             se il grafo su cui il metodo è chiamato non è diretto.
     * @throws IllegalArgumentException
     *             se il nodo non esiste.
     * @throws NullPointerException
     *             se il nodo passato è nullo.
     * 
     */
    public Set<V> predecessors(V label);

    /**
     * Restituisce l'insieme dei nodi di questo grafo.
     * 
     * @return l'insieme dei nodi di questo grafo.
     */
    public Set<V> getNodes();

    /**
     * Aggiunge un arco a questo grafo.
     * 
     * @param label1
     *            etichetta del primo nodo (nodo sorgente in caso di grafo
     *            diretto).
     * @param label2
     *            etichetta del secondo nodo (nodo destinazione in caso di grafo
     *            diretto).
     * @param label
     *            etichetta del nuovo arco.
     * @return true se l'arco è stato inserito, false se un arco esattamente
     *         uguale già esiste.
     * @throws NullPointerException
     *             se le etichette passate dei nodi sono nulle.
     * @throws IllegalArgumentException
     *             se almeno uno dei due nodi interessati non esiste.
     */
    public boolean addEdge(V label1, V label2, E label);

    /**
     * Rimuove un arco da questo grafo.
     * 
     * @param label1
     *            etichetta del primo nodo (nodo sorgente in caso di grafo
     *            diretto).
     * @param label2
     *            etichetta del secondo nodo (nodo destinazione in caso di grafo
     *            diretto).
     * @param label
     *            etichetta dell'arco.
     * @return true se l'arco è stato rimosso, false se l'arco non era presente.
     * @throws IllegalArgumentException
     *             se i nodi passati non esistono.
     * @throws NullPointerException
     *             se le etichette passate dei nodi sono nulle.
     */
    public boolean removeEdge(V label1, V label2, E label);

    /**
     * Cerca se c'è un certo arco in questo grafo.
     * 
     * @param label1
     *            etichetta del primo nodo (nodo sorgente in caso di grafo
     *            diretto).
     * @param label2
     *            etichetta del secondo nodo (nodo destinazione in caso di grafo
     *            diretto).
     * @param label
     *            etichetta dell'arco.
     * @return true se in questo grafo c'è un arco fra label1 e label2, false
     *         altrimenti.
     * 
     * @throws NullPointerException
     *             se le etichette passate dei nodi sono nulle.
     * @throws IllegalArgumentException
     *             se i nodi passati non esistono.
     * 
     */
    public boolean containsEdge(V label1, V label2, E label);

    /**
     * Restituisce l'insieme degli archi tra due nodi specificati.
     * 
     * @param label1
     *            etichetta del primo nodo (nodo sorgente in caso di grafo
     *            diretto).
     * @param label2
     *            etichetta del secondo nodo (nodo destinazione in caso di grafo
     *            diretto).
     * @return l'insieme degli archi, con etichette differenti, fra label1 e
     *         label2 in questo grafo.
     * @throws NullPointerException
     *             se le etichette passate dei nodi sono nulle.
     * @throws IllegalArgumentException
     *             se i nodi passati non esistono.
     */
    public Set<Edge<V, E>> getEdges(V label1, V label2);

    /**
     * Restituisce l'insieme di tutti gli archi connessi a un certo nodo in un
     * grafo non diretto.
     * 
     * @param label
     *            l'etichetta del nodo.
     * 
     * @return un insieme contenente tutti gli archi connessi al nodo con
     *         etichetta label in questo grafo.
     * 
     * @throws UnsupportedOperationException
     *             se il grafo su cui il metodo è chiamato è diretto.
     * @throws IllegalArgumentException
     *             se il nodo non esiste.
     * @throws NullPointerException
     *             se il nodo passato è nullo.
     */
    public Set<Edge<V, E>> getEdges(V label);

    /**
     * Restituisce l'insieme di tutti gli archi uscenti da un certo nodo in un
     * grafo diretto.
     * 
     * @param label
     *            l'etichetta del nodo.
     * 
     * @return un insieme contenente tutti gli archi uscenti dal nodo con
     *         etichetta label in questo grafo diretto.
     * 
     * @throws UnsupportedOperationException
     *             se il grafo su cui il metodo è chiamato non è diretto.
     * @throws IllegalArgumentException
     *             se il nodo non esiste.
     * @throws NullPointerException
     *             se il nodo passato è nullo.
     */
    public Set<Edge<V, E>> outgoingEdges(V label);

    /**
     * Restituisce l'insieme di tutti gli archi entranti in un certo nodo in un
     * grafo diretto.
     * 
     * @param label
     *            l'etichetta del nodo.
     * 
     * @return un insieme contenente tutti gli archi entranti nel nodo con
     *         etichetta label in questo grafo diretto.
     * 
     * @throws UnsupportedOperationException
     *             se il grafo su cui il metodo è chiamato non è diretto.
     * @throws IllegalArgumentException
     *             se il nodo non esiste.
     * @throws NullPointerException
     *             se il nodo passato è nullo.
     */
    public Set<Edge<V, E>> ingoingEdges(V label);

    /**
     * Restituisce l'insieme di tutti gli archi in questo grafo.
     * 
     * @return un insieme contenente tutti gli archi di questo grafo.
     */
    public Set<Edge<V, E>> getEdges();
    
    /**
     * Restituisce il numero di archi in questo grafo.
     * 
     * @return il numero di archi in questo grafo.
     */
    public int edgeCount();

    /**
     * Cancella tutti i nodi e gli archi di questo grafo portandolo ad essere un
     * grafo vuoto.
     */
    public void clear();
}
