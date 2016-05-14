
// Ver 1.0:  Wec, Feb 3.  Initial description.

import java.util.Comparator;

public class BinaryHeap<T> implements PQ<T> {
	T[] pq;
	Comparator<T> c;
	int length = 0;

	/** Build a priority queue with a given array q */
	BinaryHeap(T[] q, Comparator<T> comp) {
		pq = q;
		length = pq.length - 1;
		c = comp;
		buildHeap();
	}

	/** Create an empty priority queue of given maximum size */
	BinaryHeap(int n, Comparator<T> comp) { /* to be implemented */
		pq = (T[]) new Object[n * 10];
		c = comp;
		length = 0;
	}

	public void insert(T x) {
		add(x);
	}

	public T deleteMin() {
		return remove();
	}

	public T min() {
		return peek();
	}

	public void resize(int length) {
		T[] pq1 = (T[]) new Object[length * 2];
		for (int i = 0; i < pq.length; i++) {
			pq1[i] = pq[i];
		}
		pq = null;
		pq = pq1;

	}

	public void add(T x) { /* to be implemented */

		if (length == pq.length - 1) {
			resize(length);
			System.out.println("L is" + length + "PL is " + pq.length);
		}
		length++;
		// System.out.println("L"+length);

		updateIndex(x, length);
		pq[length] = x;
		percolateUp(length);
		
	}

	public T remove() { /* to be implemented */
		T temp = pq[1];
		pq[1] = pq[length--];
		updateIndex(pq[1], 1);
		percolateDown(1);
		return temp;
	}

	public void updateIndex(T x, int index) {
		if (x.getClass().getName().equals("Vertex")) {
			Vertex temp = (Vertex) x;
			temp.putIndex(temp, index);
			System.out.println("ind"+temp.getIndex(temp));
		}
		
		
	}

	public T peek() { /* to be implemented */
		return pq[1];
	}

	/** pq[i] may violate heap order with parent */
	void percolateUp(int i) { /* to be implemented */

		pq[0] = pq[i];

		while (c.compare(pq[i / 2], pq[0]) > 0) {
			pq[i] = pq[i / 2];
			updateIndex(pq[i],i);
			i = i / 2;
		}
		pq[i] = pq[0];
		updateIndex(pq[i],i);

	}

	/** pq[i] may violate heap order with children */
	void percolateDown(int i) { /* to be implemented */
		int sChild = 0;
		T temp = pq[i];
		while (2 * i <= length) {
			if (2 * i == length) {
				if (c.compare(pq[length], temp) < 0) {
					pq[i] = pq[length];
					updateIndex(pq[i],i);
					i = length;
				} else
					return;
			} else {
				if (c.compare(pq[2 * i], pq[2 * i + 1]) <= 0) {
					sChild = 2 * i;
				} else {
					sChild = 2 * i + 1;
				}

				if (c.compare(pq[sChild], temp) < 0) {
					pq[i] = pq[sChild];
					updateIndex(pq[i],i);
					i = sChild;

				} else
					return;
			}
			pq[i] = temp;
			updateIndex(pq[i],i);
		}
	}

	/** Create a heap. Precondition: none. */
	void buildHeap() {

		for (int i = length / 2; i >= 1; i--) {
			percolateDown(i);
		}
		/*
		 * for(int i=1;i<pq.length;i++){ System.out.print(pq[i]+ " ");
		 * 
		 * }
		 */
	}

	/*
	 * sort array A[1..n]. A[0] is not used. Sorted order depends on comparator
	 * used to build heap. min heap ==> descending order max heap ==> ascending
	 * order
	 */
	public static <T> void heapSort(T[] A,
			Comparator<T> comp) { /* to be implemented */
		BinaryHeap<T> binaryHeap = new BinaryHeap<>(A, comp);
		int length = binaryHeap.length;
		while (length > 0) {
			T temp = binaryHeap.deleteMin();
			A[length--] = temp;
		}

	}

	@Override
	public boolean isEmpty() {
		if (length == 0)
			return true;

		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		while (length == 0) {
			remove();
		}

	}

}
