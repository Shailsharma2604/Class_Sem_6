import java.util.Scanner;

class BTree {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    Node root;

    BTree() {
        root = null;
    }

    void insert(int data) {
        root = insertRec(root, data);
    }

    Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    void delete(int data) {
        root = deleteRec(root, data);
    }

    Node deleteRec(Node root, int data) {
        if (root == null) {
            return root;
        }

        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            root = root.left;
            minValue = root.data;
        }
        return minValue;
    }

    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    public static void main(String[] args) {
        BTree tree = new BTree();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements to insert in the BTree:");
        int n = scanner.nextInt();

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            int data = scanner.nextInt();
            tree.insert(data);
        }

        System.out.println("Inorder traversal of the BTree:");
        tree.inorder();

        System.out.println("\nEnter the element to delete:");
        int deleteData = scanner.nextInt();
        tree.delete(deleteData);

        System.out.println("Inorder traversal after deletion:");
        tree.inorder();

        scanner.close();
    }
}