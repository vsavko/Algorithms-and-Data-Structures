import java.util.Stack;

public class Knapsack {
	public static boolean recursiveSearch(int [] sack, int sum, int candidate) {
		if(sum == 0) {
			return true;
		}
		else if(sum < 0 || candidate > sack.length - 1) {
			return false;
		}
		if (recursiveSearch(sack, sum - sack[candidate], candidate + 1) == true) {
			System.out.printf("%d ",sack[candidate]);
			return true;
		}
		else {
			return recursiveSearch(sack, sum, candidate + 1);
		}
	}
	
	public static void iterativeSearch(int [] sack, int sum) {
		Stack<Integer> stack = new Stack<>(); //0 - new func call, 1- include candidate, 2 - exclude a candidate
		stack.push(0);
		int candidate = 0;
		do {
			if (sum == 0) {
				if (stack.pop() == (Integer) 1) {
					System.out.printf("%d ",sack[candidate]);
				}
				--candidate;
				continue;
			}
			else if(sum < 0 || candidate > sack.length - 1) {
				stack.pop();
				--candidate;
			}
			
			if (stack.peek() == (Integer)0 ){ //new function call
				stack.pop();
				sum -= sack[candidate++];
				stack.push(1);
				stack.push(0);
			}
			else if(stack.peek() == (Integer)2) { //element was already excluded before, move one element back
				stack.pop();
				--candidate;
			}
			else { //==1 have to change the status of the element from included to excluded
				stack.pop();
				stack.push(2);
				stack.push(0);
				sum += sack[candidate++]; //add back the included element to the sum
			}
			//System.out.println(sum + " " + candidate + " " + stack.toString());
				
		}while (!stack.empty());
	}
	
	public static void main(String[] args) {
		int [] sack = {1,2,3,4,5,6,7,8};
		recursiveSearch(sack,24,0);
		System.out.println();
		iterativeSearch(sack,24);
	}
}
