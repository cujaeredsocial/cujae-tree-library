package cu.edu.cujae.ceis.tree;

import java.util.List;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

/**
 * Abstract base class representing a generic tree data structure.
 * Provides common tree operations and defines the interface for tree implementations.
 * 
 * @param <E> the type of elements stored in the tree
 */
abstract public class Tree<E> {

	protected TreeNode<E> root;
	
	/**
	 * Constructs an empty tree.
	 */
	public Tree() {
		root = null;
	}

	/**
	 * Constructs a tree with the specified root node.
	 * 
	 * @param root the root node of the tree
	 */
	public Tree(TreeNode<E> root) {
		this.root = root;
	}

	/**
	 * Returns the root node of the tree.
	 * 
	 * @return the root node, or null if the tree is empty
	 */
	public TreeNode<E> getRoot() {
		return root;
	}

	/**
	 * Sets the root node of the tree.
	 * 
	 * @param root the new root node
	 */
	public void setRoot(TreeNode<E> root) {
		this.root = root;
	}

	/**
	 * Checks if the tree is empty.
	 * 
	 * @return true if the tree has no nodes, false otherwise
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Returns the total number of nodes in the tree.
	 * 
	 * @return the total number of nodes
	 */
	public abstract int totalNodes();
	
	/**
	 * Returns a list of all leaf nodes in the tree.
	 * 
	 * @return list of leaf nodes
	 */
	public abstract List<TreeNode<E>> getLeaves();
	
	/**
	 * Returns the level of the specified node in the tree.
	 * 
	 * @param node the node to find the level for
	 * @return the level of the node, or -1 if node is not found
	 */
	public abstract int nodeLevel(TreeNode<E> node);
	
	/**
	 * Returns the maximum level of the tree.
	 * 
	 * @return the tree level
	 */
	public abstract int treeLevel();
	
	/**
	 * Returns the height of the tree.
	 * 
	 * @return the tree height
	 */
	public abstract int treeHeight();
	
	/**
	 * Checks if the specified node is a leaf node.
	 * 
	 * @param node the node to check
	 * @return true if the node is a leaf, false otherwise
	 */
	public abstract boolean nodeIsLeaf(TreeNode<E> node);
	
	/**
	 * Returns the degree (number of children) of the specified node.
	 * 
	 * @param node the node to check
	 * @return the degree of the node
	 */
	public abstract int nodeDegree(TreeNode<E> node);
	
	/**
	 * Deletes the specified node from the tree.
	 * 
	 * @param node the node to delete
	 * @return the information stored in the deleted node
	 */
	public abstract E deleteNode(BinaryTreeNode<E> node);	
}