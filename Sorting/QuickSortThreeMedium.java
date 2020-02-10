import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSortThreeMedium {
	

	public static <T extends Comparable<T>> void sort(T[] a)	{
	StdRandom.shuffle(a); // Eliminate dependence on input.
	sort(a, 0, a.length - 1);
	}
	private static <T extends Comparable<T>>  void sort(T[] a, int lo, int hi){
		if (hi <= lo) return;
		int j = partition(a, lo, hi); // Partition (see page 291).
		sort(a, lo, j-1); // Sort left part a[lo .. j-1].
		sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
	}
	
	
	private static <T extends Comparable<T>>  int partition(T[] a, int lo, int hi)	{ // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi+1; // left and right scan indices
		int median = medianOfThree(a, lo, hi);
		exch(a,lo,median);
		T v = a[lo]; // partitioning item
		while (true){ // Scan right, scan left, check for scan complete, and exchange.
			while (less(a[++i], v)) if (i == hi) break;
			while (less(v, a[--j])) if (j == lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}
	
	private static <T extends Comparable<T>>  void exch(T [] arr, int i ,int j) {
		T temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j]= temp;
	}
	
	private static <T extends Comparable<T>>  boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}
	
	private static <T extends Comparable<T>> int medianOfThree(T [] array, int i, int j) {
		int randomIndexA = i;
		int randomIndexB = i/2 +j/2;
		int randomIndexC = j;
		if (less(array[randomIndexB],array[randomIndexA])) {
			  if (less(array[randomIndexC], array[randomIndexB])) {
			    return randomIndexB;
			  } else if (less(array[randomIndexC],array[randomIndexA])) {
			    return randomIndexC;
			  } else {
			    return randomIndexA;
			  }
			} else {
			  if (less(array[randomIndexC],array[randomIndexA])) {
			    return randomIndexA;
			  } else if (less(array[randomIndexC], array[randomIndexB])) {
			    return randomIndexC;
			  } else {
			    return randomIndexB;
			  }
			}
	}
	
	public static void main(String[] args) {
		Integer [] arr = {4,2,4,84,4,4};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	

}
