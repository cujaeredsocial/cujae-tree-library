package cu.edu.cujae.ceis.tree.binary;

import java.io.Serializable;
import cu.edu.cujae.ceis.tree.TreeNode;

/**
 * Represents a node in a binary tree with left and right child references.
 * 
 * @param <E> the type of element stored in the node
 */
public class BinaryTreeNode<E> extends TreeNode<E> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected BinaryTreeNode<E> left;
	protected BinaryTreeNode<E> right;
	
	/**
	 * Constructs an empty binary tree node.
	 */
	public BinaryTreeNode() {
		this.info = null;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Constructs a binary tree node with the specified information.
	 * 
	 * @param info the information to store in the node
	 */
	public BinaryTreeNode(E info) {
		this.info = info;
		this.left = null;
		this.right = null;
	}
			
	/**
	 * Returns the information stored in this node.
	 * 
	 * @return the node information
	 */
	public E getInfo() {
		return info;
	}
	
	/**
	 * Sets the information for this node.
	 * 
	 * @param info the information to store
	 */
    public void setInfo(E info) {
		this.info = info;
	}
    
    /**
     * Returns the left child of this node.
     * 
     * @return the left child node
     */
    public BinaryTreeNode<E> getLeft() {
		return left;
	}
    
    /**
     * Sets the left child of this node.
     * 
     * @param left the left child node
     */
    public void setLeft(BinaryTreeNode<E> left) {
		this.left = left;
	}
    
    /**
     * Returns the right child of this node.
     * 
     * @return the right child node
     */
    public BinaryTreeNode<E> getRight() {
		return right;
	}
    
    /**
     * Sets the right child of this node.
     * 
     * @param right the right child node
     */
    public void setRight(BinaryTreeNode<E> right) {
		this.right = right;
	}
}