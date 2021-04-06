package AVLTree;

public class AVLTree2 {
	Node mainroot = null;
	int smallerElements;
	
	public int insert(int val) {
		smallerElements = 0;
		mainroot = insert2(val, mainroot, 0);
		return smallerElements;
	}
	
	//returns the number of elements smaller
	//numelements is the ammount of smaller elements
	private Node insert2(int val, Node root, int numelements) {
		if (root == null) {
			root = new Node(val, 1, 1);
			smallerElements = numelements;
			return root; 
		}
		
		//go to the right
		if (val > root.val) {
			numelements += getLessElements(root);
			root.right = insert2(val, root.right, numelements);
		}
		else {
			root.left = insert2(val, root.left, numelements);
		}
		
		root.num_values++;
		root.height = getHeight(root);
		
		//
		int balance = getBalance(root);
		//System.out.println("root" + root.val + " balance " + balance);
		
        if (balance == 2){ //right heavy
            if (getBalance(root.right) == 1 ){ //left rotation
                root = leftRotation(root,root.right);
            }
            else{ //first right rotation then left
                root.right = rightRotation(root.right.left, root.right);
                root = leftRotation(root,root.right);
            }
        }

        if (balance == -2){ //left heavy
            if (getBalance(root.left) == -1 ){ //left rotation
                root = rightRotation(root.left, root);
            }
            else{
                root.left = leftRotation(root.left, root.left.right);
                root = rightRotation(root.left,root);
            }
        }

		return root;
	}
	
	private int getLessElements(Node root) {
		int ans = 0;
		if (root.left != null) {
			ans += root.left.num_values;
		}
		return ans +1; //include the root as one of the lements less
	}
	
    private int getHeight(Node root) {
        int height = 0;
        if (root.right != null) 
        	height += root.right.height;
        if (root.left != null) 
            height = Math.max(height, root.left.height);
        return height + 1;
    }
    

    public void printTree(){
        printTree2(mainroot, 0); 
    }
    
    public void printTree2(Node root, int lvl){
        if (root == null) return;
        printTree2(root.left, lvl +1);
        System.out.println(root.val+ " lvl: " + lvl  + " height " 
        + root.height + " root numvalues" + root.num_values );
        printTree2(root.right, lvl + 1);
    }
    
    
    private Node rightRotation(Node leftN, Node rightN){
        //left goes up, right goes down
        rightN.left = leftN.right;
        rightN.num_values = getNumvalues(rightN);
        rightN.height = getHeight(rightN);
        leftN.right =  rightN;
        leftN.num_values = getNumvalues(leftN);
        leftN.height = getHeight(leftN);
        return leftN;
    }
    
    private Node leftRotation(Node leftN, Node rightN){
        //left goes down, right goes up
        leftN.right = rightN.left;
        leftN.num_values = getNumvalues(leftN);
        leftN.height = getHeight(leftN);
        rightN.left = leftN;
        rightN.num_values = getNumvalues(rightN);
        rightN.height = getHeight(rightN);
        return rightN;
    }
    
    
    private int getBalance(Node root){
        int rightBalance = 0;
        int leftBalance = 0;
        
        if(root.right != null)
            rightBalance = root.right.height;
        if(root.left != null)
            leftBalance = root.left.height;
            
        int balance = rightBalance - leftBalance;
        return balance;
    }
    
    
    private int getNumvalues(Node root){
        int num_values = 1;
        if (root.right != null) 
            num_values += root.right.num_values;
            
        if (root.left != null) 
            num_values += root.left.num_values;
        return num_values;
    }
    
    
   /* public static void main(String[] args) {
		//test rotation
    	AVLTree2 avl = new AVLTree2();
    	// 1, 2, 3, 0, 11, 4
    	avl.insert(4);
    	avl.insert(11);
    	avl.insert(0);    	
    	avl.insert(3);
    	avl.printTree();
    	avl.insert(2);
    	avl.printTree();
    	System.out.println();
    	avl.insert(1);
    	avl.printTree();
	}*/
}
