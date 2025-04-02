class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class Validate_Binary_Search_Tree {

    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true; // An empty tree is a valid BST
        }

        int val = node.val;

        // Check if the node's value violates the BST constraints
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        // Recursively validate the left and right subtrees
        if (!validate(node.right, val, upper)) {
            return false;
        }
        if (!validate(node.left, lower, val)) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Validate_Binary_Search_Tree bstValidator = new Validate_Binary_Search_Tree();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        System.out.println(bstValidator.isValidBST(root)); // Output: true

        TreeNode invalidRoot = new TreeNode(5);
        invalidRoot.left = new TreeNode(1);
        invalidRoot.right = new TreeNode(4);
        invalidRoot.right.left = new TreeNode(3);
        invalidRoot.right.right = new TreeNode(6);

        System.out.println(bstValidator.isValidBST(invalidRoot)); // Output: false
    }
}
