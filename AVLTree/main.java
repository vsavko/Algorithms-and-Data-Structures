package AVLTree;
import java.util.Arrays;

public class main {
	public static void main(String[] args) {
		
		int N = 7;
		int []  arr = {12, 1, 2, 3, 0, 11, 4};
		System.out.println(Arrays.toString(constructLowerArray(arr, 7)));
		int [] arr2 = {3,3,2,2,3};
		System.out.println(Arrays.toString(constructLowerArray(arr2, 5)));
		int [] arr3 = {3,3,3,3,3};
		System.out.println(Arrays.toString(constructLowerArray(arr3, 5)));
	}
	
	 public static int[] constructLowerArray(int[] arr, int n) {
	        int [] ans = new int[n];
	        AVLTree2 avl = new AVLTree2();
	        
	        for(int i = arr.length - 1; i >=0 ; i-- ){
	            ans[i] = avl.insert(arr[i]);
	        }
	        
	        return ans;
	  }
}
