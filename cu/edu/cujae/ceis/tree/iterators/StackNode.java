package cu.edu.cujae.ceis.tree.iterators;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

public class StackNode<E> {
	private BinaryTreeNode<E> node;
	private int count;
	
	public StackNode(BinaryTreeNode<E> node) {	
		this.node = node;
		count = 0;
	}
	
	public int getCount(){
		return count;
	}
	
	public void incrementCount(){
		count++;
	}
		
	public BinaryTreeNode<E> getRight(){
		return node.getRight();
	}
	
	public BinaryTreeNode<E> getLeft(){
		return node.getLeft();
	}

	public BinaryTreeNode<E> getNode() {
		return node;
	}
}
