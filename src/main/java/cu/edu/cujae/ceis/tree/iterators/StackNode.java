package cu.edu.cujae.ceis.tree.iterators;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

/**
 * Helper class for tree iterators that maintains node traversal state.
 * Used in iterative tree traversal algorithms to track visited nodes and their children.
 * 
 * @param <E> the type of elements stored in the tree
 */
public class StackNode<E> {
	private BinaryTreeNode<E> node;
	private int count;
	
	/**
	 * Constructs a StackNode for the specified binary tree node.
	 * 
	 * @param node the binary tree node to wrap
	 */
	public StackNode(BinaryTreeNode<E> node) {	
		this.node = node;
		count = 0;
	}
	
	/**
	 * Returns the visit count for this node.
	 * 
	 * @return the number of times this node has been visited
	 */
	public int getCount(){
		return count;
	}
	
	/**
	 * Increments the visit count for this node.
	 */
	public void incrementCount(){
		count++;
	}
		
	/**
	 * Returns the right child of the wrapped node.
	 * 
	 * @return the right child node
	 */
	public BinaryTreeNode<E> getRight(){
		return node.getRight();
	}
	
	/**
	 * Returns the left child of the wrapped node.
	 * 
	 * @return the left child node
	 */
	public BinaryTreeNode<E> getLeft(){
		return node.getLeft();
	}

	/**
	 * Returns the wrapped binary tree node.
	 * 
	 * @return the binary tree node
	 */
	public BinaryTreeNode<E> getNode() {
		return node;
	}
}