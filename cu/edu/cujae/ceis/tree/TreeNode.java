package cu.edu.cujae.ceis.tree;

abstract public class TreeNode<E> {
	
	protected E info;
	
	public TreeNode() {
		this.info = null;	
	}
	
	public TreeNode(E info) {
		this.info = info;
	}
}
