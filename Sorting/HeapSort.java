import java.util.Arrays;

public class HeapSort {

	public static <T extends Comparable<T>> T [] sort(T [] arr) {
		T [] heap = makeHeap(arr);
		T [] ansArr = (T[]) new Comparable[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			ansArr[i] = heap[0]; //smallest element is taken out from the heap
			heap[0] = heap[arr.length - i - 1]; //take  the last element, put in on top of the tree, then push it down
			int n = 1;
			while(n*2 <= arr.length - i) {
				if (n*2 == arr.length - i) { //left child is the end of the tree
					if (heap[n-1].compareTo(heap[n*2-1]) > 0)
						swap(n-1, n*2-1, heap);
				}
				else { //also check the right child
					//left smaller than right child and parent > child
					if (heap[n*2-1].compareTo(heap[n*2+1-1]) < 0 && heap[n-1].compareTo(heap[n*2-1]) > 0) 
							swap(n-1, n*2-1, heap);
					//left bigger or greater than right child and parent > child
					else if (heap[n*2-1].compareTo(heap[n*2 + 1-1]) >= 0 && heap[n-1].compareTo(heap[n*2+1-1]) > 0)
							swap(n-1, n*2+1-1, heap);
					else //parent <= both children, tree is in order
						break;
					}
				n *= 2;
			}
		}	
		return ansArr;
	}
	
	public static <T extends Comparable<T>> T [] makeHeap(T [] arr) {
		T[] heap = (T[]) new Comparable[arr.length];
		for(int i = 0 ; i < arr.length; i++) {
			heap[i] = arr[i];
			//move the child up
			int n = i;
			while( n  > 0 ) {
				if (heap[n/2].compareTo(heap[n]) > 0) { //if parent is larger than the child node swap
					swap(n, n/2, heap);
				}
				else 
					break; //parent is <= child, stop
				n /= 2;
			}
		}
		return heap;
	}
	
	public static <T extends Comparable<T>> void swap (int element1, int element2, T [] heap) {
		T tmp = heap[element1];
		heap[element1] = heap[element2];
		heap[element2] = tmp;
	}

	public static void main(String[] args) {
		Integer [] arr = {7,4,3,6,10,1,8,5};
		System.out.println(Arrays.toString(makeHeap(arr)));
		System.out.println(Arrays.toString(sort(arr)));
	}
}
