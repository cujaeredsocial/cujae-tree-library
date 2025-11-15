package cu.edu.cujae.ceis.tree;

/**
 * Abstract base class representing a node in a tree structure.
 * 
 * @param <E> the type of element stored in the node
 */
abstract public class TreeNode<E> {
	
	protected E info;
	
	/**
	 * Constructs an empty node with no information.
	 */
	public TreeNode() {
		this.info = null;	
	}
	
	/**
	 * Constructs a node with the specified information.
	 * 
	 * @param info the information to store in the node
	 */
	public TreeNode(E info) {
		this.info = info;
	}
}