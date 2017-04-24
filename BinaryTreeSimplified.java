public class BinaryTreeSimplified {
  public static void main (String... args) {
    BinarySearchTree bst=new BinarySearchTree();
    bst.insert(20);
    bst.insert(10);
    bst.insert(6);
    bst.insert(15);
    bst.insert(12);
    bst.display(bst.root);
  }
}

class Node  {
  int data;
  Node left;
  Node right;
  
  public Node (int data) {
    this.data=data;
  }
}

class BinarySearchTree {
  public static Node root;
  public BinarySearchTree() {
    this.root=null;
  }
  
  public void insert (int data) {
    Node newNode = new Node (data);
    if (root==null) {
      root=newNode;
      return;
    }
    Node current=root;
    Node parent=null;
    while (true) {
      parent=current;
      if (data<current.data) {
        current=current.left;
        if (current==null) {
          parent.left=newNode;
          return;
        }
      } else {
        current=current.right;
        if (current==null) {
          parent.right=newNode;
          return;
        }
      }
    }
  }
  
  public void display (Node root) {
    if (root!=null) {
      display (root.left);
      System.out.println(" "+root.data);
      display(root.right);
    }
  }
}
