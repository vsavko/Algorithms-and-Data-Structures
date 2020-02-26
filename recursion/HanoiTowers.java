
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class HanoiTowers {
	LinkedList<String> ans;
	HashMap<Integer, String> map;
	
	public HanoiTowers(Stack<Integer> tower1, Stack<Integer> tower2, Stack<Integer> tower3) {
		ans = new LinkedList<String>();
		int n = tower1.size();
		map = new HashMap<>();
		map.put(System.identityHashCode(tower1), "tower1");
		map.put(System.identityHashCode(tower2), "tower2");
		map.put(System.identityHashCode(tower3), "tower3");
		
		solver(n, tower1, tower2, tower3, ans);
	}
	
	public LinkedList<String> getSolution(){
		return ans;
	}
	
	private void solver(int n, Stack<Integer> tower1, Stack<Integer> tower2, Stack<Integer> tower3,	LinkedList<String> answer){
		if (n == 0) return;
		solver(n-1, tower1, tower3, tower2,answer);
		int A = tower1.pop();
		if(!tower2.isEmpty() &&tower2.peek() < A) 
			throw new IllegalArgumentException("Bigger weight " + A + " is placed on a smaller one! " + tower2.peek());
		tower2.push(A);
		answer.add(new String("\n" + A + " from " + map.get(System.identityHashCode(tower1)) + " to " + map.get(System.identityHashCode(tower2))));
		solver(n-1, tower3, tower2, tower1, answer);
	}
	
	public static void main(String[] args) {
		//tower 1 holds the initial weights, represented by ints
		//move them all to tower 2, moving one weight a time
		//such that no bigger weight is placed on a smaller one
		
		Stack<Integer> tower1 = new Stack<>();
		Stack<Integer> tower2 = new Stack<>();
		Stack<Integer> tower3 = new Stack<>();
		tower1.add(5);
		tower1.add(4);
		tower1.add(3);
		tower1.add(2);
		tower1.add(1);
		
		
		HanoiTowers hTSolver = new HanoiTowers(tower1,tower2,tower3);
		System.out.println(hTSolver.getSolution());
		System.out.println("tower1" + tower1);
		System.out.println("tower2" + tower2);
		System.out.println("tower3" + tower3);
	}
}
