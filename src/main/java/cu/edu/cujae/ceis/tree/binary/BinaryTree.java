package cu.edu.cujae.ceis.tree.binary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.TreeNode;
import cu.edu.cujae.ceis.tree.iterators.binary.PosOrderIterator;
import cu.edu.cujae.ceis.tree.iterators.binary.PreorderIterator;
import cu.edu.cujae.ceis.tree.iterators.binary.SymmetricIterator;

/**
 * Implementation of a binary tree data structure.
 * Provides operations for binary tree manipulation and traversal.
 * 
 * @param <E> the type of elements stored in the tree
 */
public class BinaryTree<E> extends Tree<E> implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an empty binary tree.
	 */
	public BinaryTree() {
		super();
	}

	/**
	 * Constructs a binary tree with the specified root node.
	 * 
	 * @param root the root node of the tree
	 */
	public BinaryTree(TreeNode<E> root) {
		super(root);
	}

	/**
	 * Constructs a binary tree with the specified binary tree node as root.
	 * 
	 * @param root the binary tree node to use as root
	 */
	public BinaryTree(BinaryTreeNode<E> root) {
		this.root = root;
	}
	
	/**
	 * Calculates the level of the subtree starting from the specified cursor node.
	 * 
	 * @param cursor the starting node
	 * @return the level of the subtree
	 */
	protected int level(BinaryTreeNode<E> cursor) {
		if (cursor != null) {
			int levelLST = level(cursor.getLeft());
			int levelRST = level(cursor.getRight());
			return ((levelLST >= levelRST) ? levelLST : levelRST) + 1;
		}
		return -1;
	}

	/**
	 * Returns the level of the tree.
	 * 
	 * @return 0 if tree has root, -1 if empty
	 */
	public int treeLevel() {
		int level = -1;
		
		if(root != null)
			level = 0;
		
		return level;
	}

	/**
	 * Returns the level of the specified node in the tree.
	 * 
	 * @param node the node to find the level for
	 * @return the level of the node, or -1 if node is not found
	 */
	public int nodeLevel(TreeNode<E> node) {
		if (node != null) {
			return node.equals(root) ? 0 : nodeLevel(getFather((BinaryTreeNode<E>)node)) + 1;
		}
		return -1;
	}		

	/**
	 * Deletes the specified node from the tree.
	 * 
	 * @param node the node to delete
	 * @return the information stored in the deleted node
	 */
	public E deleteNode(BinaryTreeNode<E> node) {
		if (node != null) {
			if (root != null && root.equals(node)) {
				this.root = null;
			} else {
				BinaryTreeNode<E> father = getFather(node);
				deleteNotRoot(node, father);
			}
			return node.getInfo();
		}
		return null;
	}

	/**
	 * Deletes a node that is not the root.
	 * 
	 * @param node the node to delete
	 * @param father the parent of the node to delete
	 */
	private void deleteNotRoot(BinaryTreeNode<E> node, BinaryTreeNode<E> father) {
		if (node != null && father != null) {			
			if(father.getLeft() != null && father.getLeft().equals(node))
				father.setLeft(null);
			else
				if(father.getRight() != null && father.getRight().equals(node))
					father.setRight(null);			
		}
	}

	/**
	 * Returns the degree (number of children) of the specified node.
	 * 
	 * @param node the node to check
	 * @return the degree of the node (0, 1, or 2 for binary trees)
	 */
	public int nodeDegree(TreeNode<E> node) {
		int degree = 0;

		if (((BinaryTreeNode<E>)node).getLeft () != null)
			degree++;

		if (((BinaryTreeNode<E>)node).getRight () != null)
			degree++;

		return degree;
	}

	/**
	 * Returns the parent node of the specified node.
	 * 
	 * @param node the node to find the parent for
	 * @return the parent node, or null if node is root or not found
	 */
	public BinaryTreeNode<E> getFather(BinaryTreeNode<E> node) {
		BinaryTreeNode<E> returnNode = null;

		if (node != null && !node.equals(root)) {							
			PreorderIterator<E> iterator = preOrderIterator();

			boolean stop = false;

			while(iterator.hasNext() && !stop){
				BinaryTreeNode<E> iterNode = iterator.nextNode();

				if((node.equals(((BinaryTreeNode<E>)iterNode).getLeft())) || (node.equals(((BinaryTreeNode<E>)iterNode).getRight()))){
					stop = true;
					returnNode = iterNode;
				}
			}							
		}		

		return returnNode;
	}

	/**
	 * Returns a list of all leaf nodes in the tree.
	 * 
	 * @return list of leaf nodes
	 */
	public List<TreeNode<E>> getLeaves() {
		List<TreeNode<E>> leavesList = new ArrayList<TreeNode<E>>();

		PreorderIterator<E> iterator = preOrderIterator();

		while(iterator.hasNext()){
			BinaryTreeNode<E> node = iterator.nextNode();

			if (node.getLeft() == null && node.getRight() == null) 
				leavesList.add(node);
		}

		return leavesList;
	}

	/**
	 * Helper method to get a subtree starting from the specified root.
	 * 
	 * @param root the root of the subtree
	 * @param node the node to stop at
	 * @param tree the tree to build
	 */
	private void getNodeSubTree(BinaryTreeNode<E> root, BinaryTreeNode<E> node,
			BinaryTree<E> tree) {
		if (root != null && !root.equals(node)) {
			BinaryTreeNode<E> cursor = new BinaryTreeNode<E>(root.getInfo());

			if (root.getLeft() != null && !root.getLeft().equals(node)) {
				getNodeSubTree(root.getLeft(), node, tree);
				cursor.setLeft((BinaryTreeNode<E>) tree.getRoot());
			} else {
				cursor.setLeft(null);
			}
			if (root.getRight() != null && root.getRight().equals(node)) {
				getNodeSubTree(root.getRight(), node, tree);
				cursor.setRight((BinaryTreeNode<E>) tree.getRoot());
			} else {
				cursor.setRight(null);
			}

			tree.setRoot(cursor);
		}
	}

	/**
	 * Returns a list of direct children of the specified node.
	 * 
	 * @param node the parent node
	 * @return list of child nodes
	 */
	public List<BinaryTreeNode<E>> getSons(BinaryTreeNode<E> node) {
		List<BinaryTreeNode<E>> sons = new ArrayList<BinaryTreeNode<E>>();

		if (node != null) {
			if (node.getLeft() != null) {
				sons.add(node.getLeft());
			}
			if (node.getRight() != null) {
				sons.add(node.getRight());
			}
		}
		return sons;
	}

	/**
	 * Returns a subtree rooted at the specified node.
	 * 
	 * @param node the root of the subtree
	 * @return a new binary tree representing the subtree
	 */
	public BinaryTree<E> getSubTree(BinaryTreeNode<E> node) {
		BinaryTree<E> tree = null;
		
		if(node != null){
			PreorderIterator<E> iter = preOrderIterator();
			boolean found = false;
			
			while(iter.hasNext() && !found){
				BinaryTreeNode<E> cursor = iter.nextNode();
				
				if(cursor.equals(node)){
					found = true;
					
					BinaryTreeNode<E> newRoot = new BinaryTreeNode<E>(node.getInfo());
					
					buildSubTree(node, newRoot);	
					
					tree = new BinaryTree<E>(newRoot);
				}
			}
			
		}
					
		return tree;
	}
	
	/**
	 * Recursively builds a subtree by copying nodes from source to destination.
	 * 
	 * @param srcFather the source parent node
	 * @param newFather the destination parent node
	 */
	private void buildSubTree(BinaryTreeNode<E> srcFather, BinaryTreeNode<E> newFather){
		if(srcFather.getLeft() != null){
			BinaryTreeNode<E> newLeft = new BinaryTreeNode<E>(srcFather.getLeft().getInfo());
			
			newFather.setLeft(newLeft);
			
			buildSubTree(srcFather.getLeft(), newFather.getLeft());
		}
		
		if(srcFather.getRight() != null){
			BinaryTreeNode<E> newRight = new BinaryTreeNode<E>(srcFather.getRight().getInfo());
			
			newFather.setRight(newRight);
			
			buildSubTree(srcFather.getRight(), newFather.getRight());
		}
	}

	/**
	 * Inserts a node into the tree at the specified position relative to a parent node.
	 * 
	 * @param node the node to insert
	 * @param type 'L' for left child, 'R' for right child, 'R' for root if father is null
	 * @param father the parent node, or null for root insertion
	 * @return true if insertion was successful, false otherwise
	 */
	public boolean insertNode(BinaryTreeNode<E> node, char type, BinaryTreeNode<E> father) {		
		boolean inserted = false;

		if (node != null) {
			if (type == 'R' && father == null) {
				if (isEmpty())
					setRoot(node);
				else {
					node.setLeft((BinaryTreeNode<E>)root);
					setRoot(node);
				}
				inserted = true;
			} 
			else {								
				PreorderIterator<E> iterator = preOrderIterator();

				boolean existsFather = false;

				while(iterator.hasNext() && !existsFather){
					BinaryTreeNode<E> currentNode = iterator.nextNode();

					if(currentNode.equals(father))
						existsFather = true;
				}

				if (existsFather) {
					if (type == 'L') {//left
						node.setLeft(father.getLeft());
						father.setLeft(node);
					} else {//right
						node.setRight(father.getRight());
						father.setRight(node);
					}
					inserted = true;
				}
			}
		}

		return inserted;
	}	

	/**
	 * Returns the total number of nodes in the tree.
	 * 
	 * @return the total number of nodes
	 */
	public int totalNodes() {		
		int count = 0;

		PreorderIterator<E> iterator = preOrderIterator();		

		while(iterator.hasNext()){
			iterator.next();
			count++;			
		}

		return count;
	}

	/**
	 * Returns the root node of the tree.
	 * 
	 * @return the root node
	 */
	public TreeNode<E> getRoot() {return root;}	

	/**
	 * Returns a pre-order iterator for the tree.
	 * 
	 * @return pre-order iterator
	 */
	public PreorderIterator<E> preOrderIterator(){
		return new PreorderIterator<E>(this);
	}

	/**
	 * Returns an in-order (symmetric) iterator for the tree.
	 * 
	 * @return in-order iterator
	 */
	public SymmetricIterator<E> symmetricIterator(){
		return new SymmetricIterator<E>(this);
	}

	/**
	 * Returns a post-order iterator for the tree.
	 * 
	 * @return post-order iterator
	 */
	public PosOrderIterator<E> posOrderIterator(){
		return new PosOrderIterator<E>(this);
	}

	/**
	 * Checks if the specified node is a leaf node.
	 * 
	 * @param node the node to check
	 * @return true if the node is a leaf, false otherwise
	 */
	public boolean nodeIsLeaf(TreeNode<E> node) {		
		return ((BinaryTreeNode<E>)node).getLeft() == null && ((BinaryTreeNode<E>)node).getRight() == null;
	}

	/**
	 * Returns the height of the tree.
	 * 
	 * @return the tree height
	 */
	@Override
	public int treeHeight() {
		return level((BinaryTreeNode<E>) root);
	}

	/**
	 * Traverses the tree in pre-order and builds a string representation with visual formatting.
	 * 
	 * @param root the root node to start traversal from
	 * @param sb the string builder to append the representation to
	 * @return string representation of the tree
	 */
	public String traversePreOrder(BinaryTreeNode root, StringBuilder sb) {

		if (root == null) {
			return "";
		}

		sb.append(root.getInfo());

		String pointerRight = "└──";
		String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

		traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
		traverseNodes(sb, "", pointerRight, root.getRight(), false);

		return sb.toString();
	}

	/**
	 * Helper method for tree traversal and string representation.
	 * 
	 * @param sb the string builder to append to
	 * @param padding the padding for the current level
	 * @param pointer the pointer symbol for the current node
	 * @param node the current node
	 * @param hasRightSibling whether the node has a right sibling
	 */
	private void traverseNodes(StringBuilder sb, String padding, String pointer, BinaryTreeNode node, boolean hasRightSibling) {
		if (node != null) {
			sb.append("\n");
			sb.append(padding);
			sb.append(pointer);
			sb.append(node.getInfo());

			StringBuilder paddingBuilder = new StringBuilder(padding);
			if (hasRightSibling) {
				paddingBuilder.append("│  ");
			} else {
				paddingBuilder.append("   ");
			}

			String paddingForBoth = paddingBuilder.toString();
			String pointerRight = "└──";
			String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

			traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
			traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
		}
	}

	/**
	 * Returns a string representation of the tree with visual formatting.
	 * 
	 * @return string representation of the tree
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		traversePreOrder((BinaryTreeNode<E>) root, sb);
		return sb.toString();
	}
}