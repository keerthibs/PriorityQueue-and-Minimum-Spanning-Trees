

/**


 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex implements Comparator<Vertex>,Index<Vertex> {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    private int topologicalOrder;
    int index;
    
    protected int inDegree;

    public int getInDegree() {
		return inDegree;
	}

	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	public int getTopologicalOrder() {
		return topologicalOrder;
	}

	public void setTopologicalOrder(int topologicalOrder) {
		this.topologicalOrder = topologicalOrder;
	}

	/**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
    	inDegree=0;
	name = n;
	seen = false;
	parent = null;
	Adj = new ArrayList<Edge>();
	revAdj = new ArrayList<Edge>();
	distance=MST.Infinity;/* only for directed graphs */
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
	
    }
    
    @Override
	public int compare(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
    	if(v1.distance-v2.distance<0){
    		return -1;
    	}
    	else if(v1.distance==v2.distance){
    		return 0;
    	}
    	
		return 1;
	}

	@Override
	public void putIndex(Vertex e, int index) {
		// TODO Auto-generated method stub
		e.index=index;
		
	}

	@Override
	public int getIndex(Vertex e) {
		// TODO Auto-generated method stub
		return e.index;
	}
}
