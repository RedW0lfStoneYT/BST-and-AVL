

public class BST<E extends Comparable<E>> {
    
    Node<E> root;

    /**
     * Used for creating a new BST instance
     * @param values The optional values for setting up the BST 
     * @see StrBST.insert(String)
     */
    public BST(E ... values) {
        if (values == null || values.length == 0 || values[0] == null) {
            root = null;
            return;
        }
        for (E value : values) {
            insert(value);
        }
    }

    /**
     * Insert value to the BST
     * @param value The value you want to add
     */
    public void insert(E value) {
        root = insertR(value, root);        
    }

    /**
     * Used for inserting a value into the BST recursively going through
     * till it finds the correct node to append it to
     * @param value The value being inserted
     * @param currentNode The node you are wanting to add to
     * @return After recursion takes place, this will return the new BST
     */
    private Node<E> insertR(E value, Node<E> currentNode) {

        if (currentNode == null) {
            currentNode = new Node<E>(value);
        }

        if (value.compareTo(currentNode.value) < 0) {
            // new value is smaller than current value (left)
            currentNode.left = insertR(value, currentNode.left);
        }

        else if (value.compareTo(currentNode.value) > 0) {
            // new value is larger than current value (right)
            currentNode.right = insertR(value, currentNode.right);
        }

        return currentNode;
    }

    /**
     * Used for searching to check if a value exists
     * @param value The value you want to check (Case sensitive)
     * @return True if it exists
     */
    public boolean search(E value) {
        return searchR(value, root);
    }

    /**
     * Used for searching for a value in the BST recursively going through
     * till it finds a matching if it exists
     * @param value The value you want to check for
     * @param currentNode The Node you want to search
     * @return True if it exists
     */
    private boolean searchR(E value, Node<E> currentNode) {

        if (currentNode == null) {
            return false;
        }
            if (currentNode.value.equals(value)) {
            return true; 
        }

        if (value.compareTo(currentNode.value) < 0) {
            // new value is smaller than current value (left)
            return searchR(value, currentNode.left);
        }

        if (value.compareTo(currentNode.value) > 0) {
            // new value is larger than current value (right)
            return searchR(value, currentNode.right);
        }
        // Not really sure where this will happen but its needed for functions with returns obviously
        return false;
    }

    /**
     * Used for printing the BST in a formatted sense
     */
    public void printBST() {
        BSTPrinter.printNode(root);
    }

    /**
     * Used for printing the BST in the format required for the assignment
     */
    public void print() {
        printR(root);
    }

    /**
     * Recursively prints nodes in the BST going down the left side first then the second
     * @param currentNode The node you want to print
     */
    private void printR(Node<E> currentNode) {
        if (currentNode == null) {
            return;
        }

        System.out.print("Root: " + currentNode.value);
        Node<E> left = currentNode.left;
        Node<E> right = currentNode.right;
        System.out.print(" | Left: " + (left == null ? "null" : left.value)); // Checks if the left node is null and prints " | Left: null" if it is. 
        System.out.print(" | Right: " + (right == null ? "null" : right.value)); // Checks if the right node is null and prints " | Right: null" if it is. 
        System.out.println();
        printR(currentNode.left);
        printR(currentNode.right);
    }

    /**
     * Used to remove a value from the BST
     * @param value The value you want to remove
     */
    public void remove(E value) {
        root = removeR(value, root);
    }

    /**
     * Recursively goes through the BST to find the node you are wanting to remove then well... removes it
     * @param value The value you are wanting to remove
     * @param currentNode The node you are wanting to remove it from
     * @return the new node with the required node removed
     */
    private Node<E> removeR(E value, Node<E> currentNode) {
        if (currentNode == null) {
            return currentNode;
        }
        
        Node<E> newNode = currentNode;
        if (value.equals(currentNode.value)) {
            // is equal to the value
            if (currentNode.left == null && currentNode.right == null) {
                // Current node is leaf node
                return null;
            }

            // Checking if the right node is not null so we can replace the current node with the left most of the right sub tree
            if (currentNode.right != null) {
                // If there is no left nodes for the right tree then we skip the other stuff trying to find the left most
                if (currentNode.right.left == null) {
                    newNode = currentNode.right;
                    newNode.left = currentNode.left;
                    return newNode;
                }
                newNode = findLeftMostNode(currentNode.right);
                // Removing the left most node of the right sub tree from the new right tree to avoid stack overflow
                newNode.right = removeR(newNode.value, currentNode.right);
                newNode.left = currentNode.left;

                return newNode;
            }

            if (currentNode.left != null) {
                return currentNode.left;
            }

            return newNode;

        }

        if (value.compareTo(currentNode.value) < 0) {
            // new is less than so left
            currentNode.left = removeR(value, currentNode.left);
        }

        if (value.compareTo(currentNode.value) > 0) {
            // new is more than so right
            currentNode.right = removeR(value, currentNode.right);
        }
        
        return currentNode;
        
    }

    /**
     * Used to find the left most node usually of the right sub tree 
     * This way I can avoid a bunch of junk in the code block for removeR
     * @param current the node you want to remove from
     * @return The left most node
     */
    private Node<E> findLeftMostNode(Node<E> current) {
        if (current.left.left == null) {
            Node<E> returnNode = current.left;
            return returnNode;
        }
        return findLeftMostNode(current.left);
    }
    
}
