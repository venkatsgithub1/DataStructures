package com.java.binaryTree;

public class BinaryTree {
	
	Node root;
	
	public static void main(String[] args) {
		BinaryTree tree=new BinaryTree();
		tree.addNode(50, "Boss");
		tree.addNode(25, "Vice President");
		tree.addNode(15, "Office Manager");
		tree.addNode(30, "Secretary");
		tree.addNode(75, "Sales Manager");
		tree.addNode(60, "Salesman 2");
		tree.addNode(85, "Salesman 1");
		
		tree.inOrderTraverseTree(tree.root);
		System.out.println();
		tree.preOrderTraverseTree(tree.root);
		System.out.println();
		tree.postOrderTraverseTree(tree.root);
	}
	
	public void inOrderTraverseTree(Node focusNode) {
		if (focusNode!=null) {
			inOrderTraverseTree(focusNode.leftChild);
			System.out.println(focusNode.key+" "+focusNode.name);
			inOrderTraverseTree (focusNode.rightChild);
		}
	}
	
	public void preOrderTraverseTree (Node focusNode) {
		if (focusNode!=null) {
			System.out.println(focusNode.key+" "+focusNode.name);
			preOrderTraverseTree(focusNode.leftChild);
			preOrderTraverseTree (focusNode.rightChild);
		}
	}
	
	public void postOrderTraverseTree (Node focusNode) {
		preOrderTraverseTree(focusNode.leftChild);
		preOrderTraverseTree (focusNode.rightChild);
		System.out.println(focusNode.key+" "+focusNode.name);
	}
	
	public void addNode (int key, String name) {
		Node newNode=new Node(key, name);
		if (root==null) {
			root=newNode;
		} else {
			Node focusNode=root;
			Node parent;
			while (true) {
				parent=focusNode;
				if (key<focusNode.key) {
					focusNode=focusNode.leftChild;
					if (focusNode==null) {
						parent.leftChild=newNode;
						return;
					}
				} else {
					focusNode=focusNode.rightChild;
					if (focusNode==null) {
						parent.rightChild=newNode;
						return;
					}
				}
			}
		}
	}
	
	class Node {
		int key;
		String name;
		Node leftChild;
		Node rightChild;
		
		Node (int key, String name) {
			this.key=key;
			this.name=name;
		}
	}
}
