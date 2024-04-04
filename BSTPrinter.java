import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 * https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
 * (Altered)
 */
class BSTPrinter {
  public static void printNode(Node<?> root) {
    int maxLevel = maxLevel(root);
    printNodeInternal(Collections.singletonList(root), 1, maxLevel);
  }
  
  private static void printNodeInternal(List<Node<?>> nodes, int level, int maxLevel) {
    if (nodes.isEmpty() || isAllElementsNull(nodes))
      return; 
    int floor = maxLevel - level;
    int endgeLines = (int)Math.pow(2.0D, Math.max(floor - 1, 0));
    int firstSpaces = (int)Math.pow(2.0D, floor) - 1;
    int betweenSpaces = (int)Math.pow(2.0D, (floor + 1)) - 1;
    printWhitespaces(firstSpaces);
    ArrayList<Node<?>> newNodes = new ArrayList<Node<?>>();
    for (Node<?> node : nodes) {
      if (node != null) {
        System.out.print(node.value);
        newNodes.add(node.left);
        newNodes.add(node.right);
      } else {
        newNodes.add(null);
        newNodes.add(null);
        System.out.print(" ");
      } 
      printWhitespaces(betweenSpaces);
    } 
    System.out.println("");
    for (byte i = 1; i <= endgeLines; i++) {
      for (byte j = 0; j < nodes.size(); j++) {
        printWhitespaces(firstSpaces - i);
        if (nodes.get(j) == null) {
          printWhitespaces(endgeLines + endgeLines + i + 1);
        } else {
          if (((Node<?>)nodes.get(j)).left != null) {
            System.out.print("/");
          } else {
            printWhitespaces(1);
          } 
          printWhitespaces(i + i - 1);
          printWhitespaces(nodes.get(j).value.toString().length() - 1);
          if ((nodes.get(j)).right != null) {
            System.out.print("\\");
          } else {
            printWhitespaces(1);
          } 
          printWhitespaces(endgeLines + endgeLines - i);
        } 
      } 
      System.out.println("");
    } 
    printNodeInternal(newNodes, level + 1, maxLevel);
  }
  
  private static void printWhitespaces(int count) {
    for (int i = 0; i < count; i++)
      System.out.print(" "); 
  }
  
  private static int maxLevel(Node<?> node) {
    if (node == null)
      return 0; 
    return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
  }
  
  private static boolean isAllElementsNull(List<Node<?>> nodes) {
    for (Node<?> node : nodes) {
      if (node != null)
        return false; 
    } 
    return true;
  }
}
