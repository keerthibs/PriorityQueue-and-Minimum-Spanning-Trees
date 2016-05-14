import java.security.PermissionCollection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MST {
	static final int Infinity = Integer.MAX_VALUE;
static int IndexedMST(Graph g){
	
	Vertex src=g.verts.get(1);
	int wmst=0;
	IndexedHeap<Vertex,Vertex> iHeap=new IndexedHeap<Vertex, Vertex>(g.numNodes, src, src);
	PQ<Edge> pQueue= new BinaryHeap(g.edges, new Edge(src,src,0)); ;
	
	for(Vertex u:g){
		
		for(Edge e:u.Adj){
			
			pQueue.add(e);
		}
	
		
		Edge temp=pQueue.deleteMin();
		u.distance=temp.Weight;
		iHeap.add(u);
		pQueue.clear();
	}
	
	while(!iHeap.isEmpty()){
		Vertex u=iHeap.remove();
		u.seen=true;
		wmst+=u.distance;
		for(Edge e:u.Adj){
			Vertex v=e.otherEnd(u);
			
			if(!v.seen && e.Weight<v.distance){
				v.distance=e.Weight;
				v.parent=u;
				//iHeap.percolateUp(v.getIndex(v));
				iHeap.decreaseKey(v);
			}
		}
	}
	
	return wmst;
	
}
	static int PrimMST(Graph g) {
		int wmst = 0;
		Vertex src = g.verts.get(1);
		src.seen = true;
		Edge e1 = new Edge(src, src, 0);
		// BinaryHeap<Edge> pQueue=new BinaryHeap<>(g.edges,e1);
		PQ<Edge> pQueue = new BinaryHeap(g.edges, e1);
		for (Edge e : src.Adj) {
			pQueue.add(e);

		}

		while (!pQueue.isEmpty()) {

			Edge e = pQueue.remove();

			Vertex v1 = e.From;
			Vertex v2 = e.To;
			Vertex u;
			if (v1.seen && v2.seen) {
				continue;
			} else {
				if (v1.seen) {
					u = v2;
				} else
					u = v1;
			}
			u.seen = true;
			wmst = wmst + e.Weight;

			for (Edge f : u.Adj) {
				Vertex w = f.otherEnd(u);
				if (!w.seen) {
					pQueue.add(f);
				}
			}

		}

		// Code for Prim's algorithm needs to be written

		return wmst;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readGraph(in, false);
		//System.out.println("Weight of MST: " + PrimMST(g));
		System.out.println(IndexedMST(g));
	}
}
