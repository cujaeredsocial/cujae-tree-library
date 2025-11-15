package cu.edu.cujae.ceis.tree.iterators.general;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

/**
 * Wrapper class for tree nodes that includes level information.
 * Used in breadth-first traversal to track the depth of each node.
 * 
 * @param <E> the type of elements stored in the tree
 */
public class BreadthNode<E> {
	private BinaryTreeNode<E> node;
	private int level;
		
	/**
	 * Constructs a BreadthNode for the specified binary tree node at level 0.
	 * 
	 * @param node the binary tree node to wrap
	 */
	public BreadthNode(BinaryTreeNode<E> node) {		
		this.node = node;
		level = 0;
	}

	/**
	 * Constructs a BreadthNode for the specified binary tree node with level based on parent.
	 * 
	 * @param node the binary tree node to wrap
	 * @param fatherLevel the level of the parent node
	 */
	public BreadthNode(BinaryTreeNode<E> node, int fatherLevel) {		
		this.node = node;
		level = fatherLevel + 1;
	}
	
	/**
	 * Returns the level of this node in the tree.
	 * 
	 * @return the node level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the level of this node in the tree.
	 * 
	 * @param level the node level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Returns the wrapped binary tree node.
	 * 
	 * @return the binary tree node
	 */
	public BinaryTreeNode<E> getNode() {
		return node;
	}

	/**
	 * Sets the binary tree node.
	 * 
	 * @param node the binary tree node
	 */
	public void setNode(BinaryTreeNode<E> node) {
		this.node = node;
	}

	/**
	 * Returns the information stored in the wrapped node.
	 * 
	 * @return the node information
	 */
	public E getInfo(){
		return node.getInfo();
	}
}