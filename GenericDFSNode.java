
public interface GenericDFSNode {

	/**
	 * Imposta il tempo discreto in cui viene scoperto un nodo.
	 * @param time			tempo di scoperta del nodo
	 */
    public void setTimeDiscovered(int time);
    
    /**
     * Ritorna il tempo discreto in cui è stato scoperto un nodo.
     * @return time			tempo di scoperta del nodo
     */
    public int getTimeDiscovered();
    
	/**
	 * Imposta il tempo discreto in cui viene esplorato un nodo.
	 * @param time		tempo di esplorazione del nodo
	 */
    public void setTimeExplored(int time);
    
    /**
     * Ritorna il tempo discreto in cui è stato esplorato un nodo.
     * @return time			tempo di esplorazione del nodo
     */
    public int getTimeExplored();
    
    /**
     * Ritorna il padre di un nodo
     * @return padre 		padre di un nodo
     */
    public GenericDFSNode getFather();
    
    /**
     * Imposta il padre di un nodo
     * @param father		padre del nodo
     */
    public void setFather(GenericDFSNode father);
}
