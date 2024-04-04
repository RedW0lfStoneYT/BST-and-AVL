public class Main {
    
    public static void main(String[] args) {
        BST<String> strBST = new BST<String>("D", "C", "G", "F", "A", "I", "H", "B", "J", "E");
        BST<Integer> intBST = new BST<Integer>();
        AVL<Integer> intAvl = new AVL<Integer>();
        strBST.printBST();

        System.out.println("Does tree contain K? " + strBST.search("K")); // False
        System.out.println("Does tree contain A? " + strBST.search("A")); // True
        strBST.print();
        strBST.remove("G");
        strBST.printBST();
        strBST.print();

        intBST.insert(5);
        intBST.insert(7);
        intBST.insert(3);
        intBST.insert(1);
        intBST.insert(2);
        intBST.insert(6);
        intBST.insert(8);
        intBST.printBST();
        intBST.remove(7);
        intBST.printBST();
        intBST.print();
        
        for (int i = 1; i <= 15; i++) {
            intAvl.insert(i);
        }
        intAvl.printBST();
    }

}
