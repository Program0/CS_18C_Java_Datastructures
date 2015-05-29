package edu.rcc.datastructures;

public class BinaryTree<E extends Comparable<? super E>> {

	// Default constructor. Creates an empty tree.
	BinaryTree() {
		root = null;
	}

	// Main constructor. Creates a one node tree.
	BinaryTree(E entry) {
		root.data = entry;
		root.leftTree = null;
		root.rightTree = null;
	}

	/**
	 * Inserts an entry into the tree.
	 * 
	 * @param entry
	 */

	public void insert(E entry) {
		// Insert an entry into the tree. Call the recursive method insert for
		// nodes
		// root = insert(root, entry);
		// If we have an empty tree just add a root
		System.out.println("Adding " + entry.toString());
		if (root == null) {
			System.out.println("Adding to the root");
			root = new Node<E>(entry);
		} else {
			// If it is not empty go through the tree to find a place to put the
			// entry
			Node<E> node;
			node = root;
			boolean added = false;
			System.out.println("adding a new node. Root is null? "+(root==null?true:false));
			while (!added) {
				// If the node is less than, go through the left tree and its
				// nodes
				if (entry.compareTo(node.data) < 0) {

					// Find if the left node is empty
					if (node.leftTree == null) {
						System.out.println("Adding to the left side");
						// If it is we found a place to add it in the left tree
						node.leftTree = new Node<E>(entry);
						added = true;
					} else {
						System.out.println("Going to the next node on the left");
						// If not go to the next left node
						node = node.leftTree;
					}
				}
				// Otherwise go through the right tree and its nodes to find a
				// place to add it to the tree
				else {
					if (node.rightTree == null) {
						System.out.println("Adding to the right side");
						// If it is we found a place to add in the right tree
						node.rightTree = new Node<E>(entry);
						added = true;
					} else {
						System.out.println("Going to the next node on the right");
						// If not go to the next right node
						node = node.rightTree;
					}
				} 
			} // end while
		} // end else
	} // end insert

	// Recursively inserts a node into the tree
	private Node<E> insert(Node<E> node, E entry) {
		// If we reach a leaf or the tree is empty
		if (node == null) {
			System.out.println("creating a new node with " + entry.toString());
			node = new Node<E>(entry);
		}
		// If there are subtrees check each node recursively
		else {
			if (entry.compareTo(node.data) <= 0) {
				System.out.println("Adding to the left side");
				node = insert(node.leftTree, entry);
			} else {
				System.out.println("Adding to the right side");
				node = insert(node.rightTree, entry);
			}
		}
		// Return the node
		return node;

	}

	public void inorderTraverse() {
		inorderTraverse(root);
	}

	private void inorderTraverse(Node<E> node) {
		// If the node is null it will print the parent node
		if (node != null) {			
			// First get the left node
			inorderTraverse(node.leftTree);
			System.out.println(node.data);
			// Get the right node
			inorderTraverse(node.rightTree);		
		}
	}

	// Tree root
	private Node<E> root;

	private static class Node<E> {
		// Main constructor
		Node(E entry) {
			this.data = entry;
			leftTree = null;
			rightTree = null;
		}
		
		public int getHeight(){
			return getHeight(this);
		}
		
		// Helper method to get the height at of a tree at at a given node.
		// It does not need to be the root node. It recursively gets the height
		// by getting the height of the nodes below it.
		private int getHeight(Node<E> node){
			int height = 0;
			if(node!=null){
				height = 1 + Math.max(getHeight(leftTree), getHeight(rightTree));
			}
			return height;
		}

		// Left and right tree reference nodes
		Node<E> leftTree;
		Node<E> rightTree;
		// Node data
		E data;
	}

	public static void main(String[] args) {
		BinaryTree<String> myTree = new BinaryTree<String>();
		myTree.insert("canteloupe");
		myTree.insert("orange");
		myTree.insert("banana");
		myTree.insert("pear");
		myTree.insert("apple");
		myTree.inorderTraverse();

	}

}
