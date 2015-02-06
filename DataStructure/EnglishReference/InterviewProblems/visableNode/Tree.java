public class Tree {
    public Tree left;
    public Tree right;
    public int value;

    public Tree(int value) {
        this.value = value;    
    }

    /* In a binary tree, if in the path from root to the node A, there is no node with greater value than A’s, this node A is visible. 
       We need to count the number of visible nodes in a binary tree. For example, in the following tree:
    */
    
    //Count how many nodes are visible nodes
    public static int count(Tree root, int max) {
        
        //int count = root.visibleCount;
        int count = 0;
        if (root.value >= max) {
            count = 1;
        }
        
        if (root.left != null) {
            if (root.left.value < max) {
                count += count(root.left, max);
            } else {
                count += count(root.left, root.left.value);
            }
        }

        if (root.right != null) {
            if (root.right.value < max) {
                count += count(root.right, max);
            } else {
                count += count(root.right, root.right.value);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        //Construct a tree
        Tree[] nodes = new Tree[6];
        nodes[0] = new Tree(5);
        nodes[1] = new Tree(3);
        nodes[2] = new Tree(10);
        nodes[3] = new Tree(20);
        nodes[4] = new Tree(21);
        nodes[5] = new Tree(1);
    
        nodes[0].left = nodes[1];
        nodes[0].right = nodes[2];

        nodes[1].left = nodes[3];
        nodes[1].right = nodes[4];

        nodes[2].left = nodes[5];

        System.out.println(Tree.count(nodes[0], nodes[0].value));
    }
}
