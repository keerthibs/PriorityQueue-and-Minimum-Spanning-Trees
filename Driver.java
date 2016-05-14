import java.util.Comparator;
import java.util.Random;

public class Driver {

	public static void main(String[] args) {
		int n = 10000;
		Integer[] input = new Integer[n + 1];
		for (int i = 1; i <= n; i++) {
			input[i] = new Integer(i);
		}
		Shuffle.shuffle(input, 1, n);

		print(input);
		Timer time = new Timer();
		time.start();
		BinaryHeap.heapSort(input, Comparator.naturalOrder());
		time.end();
		System.out.println(time);
		print(input);
	}

	static void print(Integer A[]) {
		for (int i = 1; i < 10; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
}
