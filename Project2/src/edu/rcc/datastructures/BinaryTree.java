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
		// Insert an entry into the tree. Iterate through the nodes until a
		// place is found for the new node. Tree is re-balanced after insertion.

		// If we have an empty tree just add a root
		if (root == null) {
			root = new Node<E>(entry);
		} else {
			// If it is not empty go through the tree to find a place to put the
			// entry
			Node<E> node;
			node = root;
			boolean added = false;
			while (!added) {
				// If the node is less than, go through the left tree and its
				// nodes
				if (entry.compareTo(node.data) <= 0) {

					// Find if the left node is empty
					if (node.leftTree == null) {
						// If it is we found a place to add it in the left tree
						node.leftTree = new Node<E>(entry);
						added = true;
					} else {
						// If not go to the next left node
						node = node.leftTree;
					}
				}
				// Otherwise go through the right tree and its nodes to find a
				// place to add it to the tree
				else {
					if (node.rightTree == null) {
						// If it is we found a place to add in the right tree
						node.rightTree = new Node<E>(entry);
						added = true;
					} else {
						// If not go to the next right node
						node = node.rightTree;
					}
				}
			} // end while
			root = rebalance(root);
		} // end else
	} // end insert

	/**
	 * Recursively enters an entry into the tree.
	 * 
	 * @param entry
	 */
	public void insertRecursive(E entry) {
		// Insert the new entry
		root = insert(root, entry);
		// Re-balance the tree
		root = rebalance(root);
	}

	/**
	 * Iteratively deletes an entry from the tree. It re-balances the tree
	 * immediately after deletion.
	 * 
	 * @param entry
	 * @return
	 */
	public E delete(E entry) {
		// Object to return after delete is concludes=d
		E result = null;
		// Find the entry and its parent that matches the entry
		NodePair<E> pair = findNode(entry);

		Node<E> currentNode = pair.childNode;
		Node<E> parentNode = pair.parentNode;

		// (currentNode==null?true:false));
		if (currentNode != null) {
			result = currentNode.data;
			// If this node has two children find one child that itself has
			// only one child. We can delete this node
			if (currentNode.leftTree != null && currentNode.rightTree != null) {
				pair = predecessor(currentNode);
				Node<E> nodeToRemove = pair.childNode;
				parentNode = pair.parentNode;
				currentNode.data = nodeToRemove.data;
				currentNode = nodeToRemove;
			}
			// Remove the in-order predecessor
			removeNode(currentNode, parentNode);
		}
		// Rebalance the tree
		root = rebalance(root);
		return result;
	}

	public boolean contains(E entry) {
		Node<E> node = location(root, entry);
		return (node != null ? true : false);
	}

	/**
	 * Performs an in-order tree traversal
	 */
	public void printInOrder() {
		inorderTraverse(root);
	}

	/**
	 * Performs a pre-order tree traversal
	 */
	public void printPreOrder() {
		preorder(root);
	}

	/**
	 * Performs a post-order tree traversal
	 */
	public void printPostOrder() {
		postOrder(root);
	}

	/**
	 * Returns the data portion of the root of the tree
	 * 
	 * @return root
	 */

	public E getRoot() {
		return root.data;
	}

	/**
	 * Returns the size of the tree
	 * 
	 * @return size
	 */
	public int size() {
		// Return the total nodes under root
		return totalNodes(root);
	}

	/**
	 * Returns true if the tree is not balanced.
	 * 
	 * @return
	 */
	public boolean isBalanced() {

		// Get left and tree subtree heights
		int left = getHeight(root.leftTree);
		int right = getHeight(root.rightTree);
		// Return true if the difference between
		return ((left - right) > 1 || (left - right < -1) ? false : true);
	}

	/**
	 * Return the height of the tree starting at the root
	 * 
	 * @return
	 */
	public int height() {
		return getHeight(root);
	}

	/**
	 * Returns a reference to a new root by rotating nodes right
	 * 
	 * @param node
	 * @return
	 */
	private Node<E> rotateRight(Node<E> node) {
		// Code based on Carrano's algorithm in book
		Node<E> newRoot = node.leftTree;
		node.leftTree = newRoot.rightTree;
		newRoot.rightTree = node;
		return newRoot;
	}

	/**
	 * Returns a reference to a new root after rotating left
	 * 
	 * @param node
	 * @return
	 */
	private Node<E> rotateLeft(Node<E> node) {
		// Code based on Carrano's book algorithm
		Node<E> newRoot = node.rightTree;
		node.rightTree = newRoot.leftTree;
		newRoot.leftTree = node;
		return newRoot;
	}

	/**
	 * Double rotates a node left then right
	 * 
	 * @param node
	 * @return
	 */
	private Node<E> rotateLeftRight(Node<E> node) {
		// Code based on Carrano's algorithm
		Node<E> newRoot = node.leftTree;
		node.leftTree = rotateLeft(newRoot);
		return rotateRight(node);
	}

	/**
	 * Double rotates a node right then left
	 * 
	 * @param node
	 * @return
	 */
	private Node<E> rotateRightLeft(Node<E> node) {
		// Code based on Carrano's algorithm
		Node<E> newRoot = node.rightTree;
		node.rightTree = rotateRight(newRoot);
		return rotateLeft(node);
	}

	/**
	 * Returns the height difference between the left and right subtrees
	 * 
	 * @param node
	 * @return heightDifference
	 */
	private int heightDifference(Node<E> node) {
		return (getHeight(node.leftTree) - getHeight(node.rightTree));
	}

	// Code is an implementation from Carrano's algorithm in data structures
	// book
	private Node<E> rebalance(Node<E> node) {
		// Get the height difference between left and right subtrees
		int heightDifference = heightDifference(node);

		if (heightDifference > 1) {
			// Now go through the subtrees and get difference with its subtrees
			// Rotate as necessary
			if (heightDifference(node.leftTree) > 0) {
				node = rotateRight(node);
			} else {
				node = rotateLeftRight(node);
			}
		} else if (heightDifference(node.rightTree) < -1) {
			if (heightDifference(node.rightTree) < 0) {
				node = rotateLeft(node);
			} else {
				node = rotateRightLeft(node);
			}
		}
		return node;
	}

	private void removeNode(Node<E> nodeToRemove, Node<E> parentNode) {
		Node<E> childNode;
		if (nodeToRemove.leftTree != null) {
			childNode = nodeToRemove.leftTree;
		} else {
			childNode = nodeToRemove.rightTree;
		}
		assert (nodeToRemove.isLeaf() && childNode == null)
				|| !nodeToRemove.isLeaf();
		// If the node to delete is the root, just change it with its child
		if (nodeToRemove == root) {
			root = childNode;
		}
		//
		else if (parentNode.leftTree == nodeToRemove) {
			parentNode.leftTree = childNode;

		} else {
			parentNode.rightTree = childNode;

		}

	}

	private NodePair<E> predecessor(Node<E> node) {

		// First go left
		Node<E> leftSubTree = node.leftTree;
		// Now we need to go as far to the right as possible
		Node<E> rightChild = leftSubTree;
		Node<E> parentNode = node;

		// Keep going right until we reach null
		while (rightChild.rightTree != null) {
			parentNode = rightChild;
			rightChild = rightChild.rightTree;
		}
		// Return the right subtree and its parent node
		return new NodePair<E>(rightChild, parentNode);
	}

	private NodePair<E> findNode(E entry) {

		// Now we need to go as far to the right as possible
		NodePair<E> result = new NodePair<E>();
		Node<E> childNode = root;
		Node<E> parentNode = null;

		// Keep going right until we reach null
		while (childNode != null) {

			if (childNode.data.compareTo(entry) == 0) {
				result = new NodePair<E>(childNode, parentNode);
				return result;
			}
			// Search the left subtree if entry is less than current node data
			else if (entry.compareTo(childNode.data) < 0) {
				parentNode = childNode;
				childNode = childNode.leftTree;
			}
			// Otherwise continue searching in the right subtree
			else {
				parentNode = childNode;
				childNode = childNode.rightTree;
			}
		}
		// Return the right subtree and its parent node
		return result;

	}

	/**
	 * Recursively inserts a node into the tree
	 * 
	 * @param node
	 * @param entry
	 * @return
	 */
	private Node<E> insert(Node<E> node, E entry) {
		// If we reach a leaf or the tree is empty
		if (node == null) {
			// System.out.println("creating a new node with " +
			// entry.toString());
			// Make a new leaf
			node = new Node<E>(entry);
		}
		// If there are subtrees check each node recursively
		else {
			if (entry.compareTo(node.data) <= 0) {
				// System.out.println("Adding to the left side. Root is "
				// +root.data + " tree heigh is " + getHeight(root));
				// Add it to the left tree
				node.leftTree = insert(node.leftTree, entry);
			} else {
				// System.out.println("Adding to the right side. Root is " +
				// root.data+ " tree heigh is " + getHeight(root));
				// Add it to the right tree
				node.rightTree = insert(node.rightTree, entry);
			}
		}
		// Return the node
		return node;

	}

	/**
	 * utility function to traverse a tree in-order
	 * 
	 * @param node
	 */
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

	/**
	 * Return the reference of a node containing the entry. If not in the tree
	 * returns null.
	 * 
	 * @param node
	 * @param entry
	 *            Parameter entry to search
	 * @return node A reference to the location of the entry in the tree. It
	 *         returns null if the entry is not in the tree.
	 */
	private Node<E> location(Node<E> node, E entry) {

		if (node != null) {
			// If we find it we exit the recursive call
			if (node.data.compareTo(entry) == 0) {
				return node;
			}
			// If we don't find it in current node go to the subtrees
			if (node.data.compareTo(entry) > 0) {
				return location(node.leftTree, entry);
			}
			// TODO remove if condition from else if
			// Can remove the if condition. Not necessary
			else if (node.data.compareTo(entry) < 0) {
				return location(node.rightTree, entry);
			}
		}
		// We never found it
		return null;
	}

	/**
	 * Utility function to traverse the tree pre-order
	 * 
	 * @param node
	 */
	private void preorder(Node<E> node) {
		if (node != null) {
			System.out.println(node.data);
			preorder(node.leftTree);
			preorder(node.rightTree);
		}
	}

	/**
	 * Utility function to traverse a tree in post-order
	 * 
	 * @param node
	 */
	private void postOrder(Node<E> node) {
		if (node != null) {
			postOrder(node.leftTree);
			postOrder(node.rightTree);
			System.out.println(node.data);
		}
	}

	/**
	 * Returns the total leafs under a parent
	 * 
	 * @param node
	 * @return totalNodes
	 */
	private int totalNodes(Node<E> node) {
		if (node == null) {
			return 0;
		}
		// Get the nodes under the left and right subtrees
		int leftTree = totalNodes(node.leftTree);
		int rightTree = totalNodes(node.rightTree);
		// Add them and return the total
		return (1 + leftTree + rightTree);
	}

	/**
	 * Returns the height of a tree. It may be a subtree depending if the node
	 * passed is the root or one of its left or right subtrees
	 * 
	 * @param node
	 * @return height
	 */
	private int getHeight(Node<E> node) {
		// If the tree is empty return 0
		if (node == null) {
			return 0;
		}
		// Get the height of the left and right subtrees
		int leftHeight = getHeight(node.leftTree);
		int rightHeight = getHeight(node.rightTree);
		// Now return the maximum between the left tree and right tree heights.
		return (leftHeight > rightHeight ? (1 + leftHeight) : (1 + rightHeight));
	}

	// Tree root
	private Node<E> root;

	// Nested class
	private static class Node<E> {
		// Main constructor
		Node(E entry) {
			this.data = entry;
			leftTree = null;
			rightTree = null;
		}

		private boolean isLeaf() {
			// If both the left and right subtrees are null, we have a leaf
			return ((leftTree == null && rightTree == null) ? true : false);
		}

		// Left and right tree reference nodes
		private Node<E> leftTree;
		private Node<E> rightTree;
		// Node data
		E data;
	}

	private static class NodePair<E> {
		NodePair(Node<E> childNode, Node<E> parentNode) {
			this.childNode = childNode;
			this.parentNode = parentNode;
		}

		public NodePair() {
			this(null, null);
		}

		private Node<E> childNode;
		private Node<E> parentNode;
	}

	public static void main(String[] args) {
		BinaryTree<String> myTree = new BinaryTree<String>();
		// Testing insertion and re-balancing
		System.out.println("Creating an empty tree. Height of tree is "
				+ myTree.height());
		myTree.insert("canteloupe");
		System.out.println("Adding 1 entry. Height of tree is "
				+ myTree.height() + " the tree is size " + myTree.size());
		System.out.println("The root is " + myTree.getRoot());
		myTree.insert("orange");
		myTree.insert("banana");
		myTree.insert("pear");
		myTree.insert("apple");
		System.out.println("After adding 5 entries. Height of tree is "
				+ myTree.height() + " the tree is size " + myTree.size());
		System.out.println("The root is " + myTree.getRoot());
		System.out.println("Tree contains the following items in order");
		myTree.printInOrder();
		myTree.insertRecursive("tomato");
		myTree.insertRecursive("cucumber");
		myTree.insertRecursive("zuchini");
		myTree.insertRecursive("brocoli");
		System.out.println("adding 4 more entries. Height of tree is "
				+ myTree.height() + " the tree is size is " + myTree.size());
		System.out.println("The root is " + myTree.getRoot());
		System.out.println("Tree contains the following items in order");
		myTree.printInOrder();

		// Testing the inorder, preorder, and postorder functions
		System.out.println("\nTraverse in in-order");
		myTree.printInOrder();
		System.out.println("\nTraverse in pre-order");
		myTree.printPreOrder();
		System.out.println("\nTraverse in post-order");
		myTree.printPostOrder();
		String search = "peach";

		// Testing the delete function.
		System.out.println("\nSearching the tree for " + search
				+ ": Is it in the tree? " + myTree.contains(search));
		System.out.println("Attempting to delete" + search + " Deleted: "
				+ myTree.delete(search));

		search = "pear";
		System.out.println("\nSearching the tree for " + search
				+ ": Is it in the tree? " + myTree.contains(search));
		System.out.println("Attempting to delete " + search + " Deleted: "
				+ myTree.delete(search));

		System.out.println("The root is " + myTree.getRoot());
		System.out.println("After deleting 1 entry. Height of tree is "
				+ myTree.height() + " the tree is size " + myTree.size());

		System.out.println("Tree now contains these items in-order");
		System.out.println("\nTraverse in in-order");
		myTree.printInOrder();
		System.out.println("\nTraverse in pre-order");
		myTree.printPreOrder();
		System.out.println("\nTraverse in post-order");
		myTree.printPostOrder();
		search = "orange";

		System.out.println("\nSearching the tree for " + search
				+ ": Is it in the tree? " + myTree.contains(search));
		System.out.println("Attempting to delete " + search + " Deleted: "
				+ myTree.delete(search));

		System.out.println("The root is " + myTree.getRoot());
		System.out.println("After deleting 1 entry. Height of tree is "
				+ myTree.height() + " the tree is size " + myTree.size());

		System.out.println("Tree now contains these items in-order");
		System.out.println("\nTraverse in in-order");
		myTree.printInOrder();
		System.out.println("\nTraverse in pre-order");
		myTree.printPreOrder();
		System.out.println("\nTraverse in post-order");
		myTree.printPostOrder();

		search = "banana";
		System.out.println("\nSearching the tree for " + search
				+ ": Is it in the tree? " + myTree.contains(search));
		System.out.println("Attempting to delete " + search + " Deleted: "
				+ myTree.delete(search));
		System.out.println("The root is " + myTree.getRoot());
		System.out.println("After deleting 1 entry. Height of tree is "
				+ myTree.height() + " the tree is size " + myTree.size());
		System.out.println("Tree now contains these items in-order");
		System.out.println("\nTraverse in in-order");
		myTree.printInOrder();
		System.out.println("\nTraverse in pre-order");
		myTree.printPreOrder();
		System.out.println("\nTraverse in post-order");
		myTree.printPostOrder();

	}

}
