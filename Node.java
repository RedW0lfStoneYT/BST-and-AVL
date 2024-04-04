public class Node<E extends Comparable<E>> {
    E value;
    Node<E> left, right;

    /**
     * Used for creating a node for your BST
     * @param value The value you wanna set for the BST node
     */
    public Node(E value) {
        this.value = value;
        left = null;
        right = null;
    }
}