package AVLTree;
class Node{
    public int val;
    public Node left;
    public Node right;
    public int num_values;
    public int height;
    
    
    public Node(int val, int num_values, int height){
        this.val = val;
        left = null;
        right = null;
        this.num_values = num_values;
        this.height = height;
    }
}