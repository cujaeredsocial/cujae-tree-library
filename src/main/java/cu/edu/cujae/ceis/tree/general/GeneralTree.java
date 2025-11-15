package cu.edu.cujae.ceis.tree.general;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.TreeNode;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.general.BreadthNode;
import cu.edu.cujae.ceis.tree.iterators.general.InBreadthIterator;
import cu.edu.cujae.ceis.tree.iterators.general.InBreadthIteratorWithLevels;
import cu.edu.cujae.ceis.tree.iterators.general.InDepthIterator;

/**
 * Implementation of a general tree (multi-way tree) using a binary tree representation.
 * Each node can have multiple children represented as a linked list through right pointers.
 * 
 * @param <E> the type of elements stored in the tree
 */
public class GeneralTree<E> extends Tree<E> implements Serializable {	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an empty general tree.
	 */
	public GeneralTree() {
		super();
	}

	/**
	 * Constructs a general tree with the specified root node.
	 * 
	 * @param root the root node of the tree
	 */
	public GeneralTree(BinaryTreeNode<E> root) {
		super(root);
	}

	/**
	 * Returns the total number of nodes in the tree.
	 * 
	 * @return the total number of nodes
	 */
	public int totalNodes() {
		int count = 0;

		InDepthIterator<E> iterator = inDepthIterator();		

		while(iterator.hasNext()){
			iterator.next();
			count++;			
		}		

		return count;
	}

	/**
	 * Deletes the specified node from the tree.
	 * 
	 * @param node the node to delete
	 * @return the information stored in the deleted node
	 */
	public E deleteNode(BinaryTreeNode<E> node) {
		E info = null;

		if (node != null) {	
			if(node.equals(root))
				root = null;
			else{
				InDepthIterator<E> iterator = inDepthIterator();

				boolean foundedNode = false;

				while(iterator.hasNext() && !foundedNode){
					BinaryTreeNode<E> father = iterator.nextNode();

					//search if node its son of father
					if(father.getLeft() != null){
						if(father.getLeft().equals(node)){
							foundedNode = true;

							father.setLeft(node.getRight());
						}
						else{
							BinaryTreeNode<E> prev = father.getLeft();
							BinaryTreeNode<E> cursor = prev.getRight();

							while(cursor != null && !foundedNode){
								if(cursor.equals(node)){
									foundedNode = true;

									prev.setRight(cursor.getRight());
								}
								else{
									prev = cursor;

									cursor = cursor.getRight();
								}
							}
						}
					}					
				}

				if(foundedNode)
					info = node.getInfo();
			}
		}

		return info;
	}

	/**
	 * Returns the parent node of the specified node.
	 * 
	 * @param node the node to find the parent for
	 * @return the parent node, or null if node is root or not found
	 */
	public BinaryTreeNode<E> getFather(BinaryTreeNode<E> node) {
		BinaryTreeNode<E> father = null;

		if (node != null && !isEmpty() || !root.equals(node)) {
			InDepthIterator<E> iterator = inDepthIterator();

			boolean foundedNode = false;

			while(iterator.hasNext() && !foundedNode){
				BinaryTreeNode<E> cursor = iterator.nextNode();

				if (node.equals(((BinaryTreeNode<E>) cursor).getLeft())){ 
					father = cursor;
					foundedNode = true;
				}
				else{
					if(cursor.getLeft() != null){
						BinaryTreeNode<E> aux = cursor.getLeft();

						while(aux.getRight() != null && !foundedNode){
							aux = aux.getRight();

							if(aux.equals(node)){
								foundedNode = true;

								father = cursor;
							}												
						}
					}
				}
			}		
		}

		return father;
	}

	/**
	 * Returns a list of all leaf nodes in the tree.
	 * 
	 * @return list of leaf nodes
	 */
	public List<TreeNode<E>> getLeaves() {
		ArrayList<TreeNode<E>> leavesList = new ArrayList<TreeNode<E>>();

		if (!isEmpty()) {							
			InDepthIterator<E> iterator = inDepthIterator();

			while(iterator.hasNext()){
				BinaryTreeNode<E> node = iterator.nextNode();

				if(((BinaryTreeNode<E>) node).getLeft() == null)
					leavesList.add(node);
			}						
		}

		return leavesList;
	}

	/**
	 * Returns a list of direct children of the specified node.
	 * 
	 * @param node the parent node
	 * @return list of child nodes
	 */
	public List<BinaryTreeNode<E>> getSons(BinaryTreeNode<E> node) {
		List<BinaryTreeNode<E>> sonsList = new ArrayList<BinaryTreeNode<E>>();

		if (node != null) {
			if (node.getLeft() != null) {
				sonsList.add(node.getLeft());
				if (node.getLeft().getRight() != null) {
					node = node.getLeft();
					while (node.getRight() != null) {
						sonsList.add(node.getRight());
						node = node.getRight();
					}
				}				
			}			
		}

		return sonsList;
	}

	/**
	 * Returns a list of information from the direct children of the specified node.
	 * 
	 * @param node the parent node
	 * @return list of child node information
	 */
	public List<E> getSonsInfo(BinaryTreeNode<E> node) {
		List<E> sonsInfoList = new ArrayList<E>();

		if (node != null) {
			if (node.getLeft() != null) {
				sonsInfoList.add(node.getLeft().getInfo());

				if (node.getLeft().getRight() != null) {
					node = node.getLeft();
					while (node.getRight() != null) {
						sonsInfoList.add((E) node.getRight().getInfo());
						node = node.getRight();
					}
				}
			}
		}

		return sonsInfoList;
	}	

	/**
	 * Inserts a node as a child of the specified parent node.
	 * 
	 * @param node the node to insert
	 * @param father the parent node, or null to insert as sibling of root
	 * @return true if insertion was successful, false otherwise
	 */
	public boolean insertNode(BinaryTreeNode<E> node, BinaryTreeNode<E> father) {
		boolean inserted = false;

		if(node != null)
		{
			if (isEmpty()) {
				if (father == null) {
					setRoot(node);
					inserted = true;
				} 			
			} 
			else {
				if (father != null) {							
					InDepthIterator<E> iterator = inDepthIterator();

					boolean stop = false;

					while(iterator.hasNext() && !stop){
						BinaryTreeNode<E> iterNode = iterator.nextNode();

						if(iterNode.equals(father)){
							stop = true;

							BinaryTreeNode<E> cursor = father.getLeft();

							if (cursor == null) {
								father.setLeft(node);
							} else {
								while (cursor.getRight() != null) {
									cursor = cursor.getRight();
								}
								cursor.setRight(node);
							}
						}

						inserted = true;
					}				
				} 
				else {
					if (((BinaryTreeNode<E>) root).getRight() != null) {
						BinaryTreeNode<E> cursor = ((BinaryTreeNode<E>) root).getRight();

						while (cursor.getRight() != null) {
							cursor = cursor.getRight();
						}
						cursor.setRight(node);
					} 
					else {
						((BinaryTreeNode<E>) root).setRight(node);
					}

					inserted = true;
				}
			}
		}

		return inserted;
	}

	/** 
	 * Inserts a node as the first child of the specified parent node.
	 * 
	 * @param node the node to insert
	 * @param father the parent node
	 * @return true if insertion was successful, false otherwise
	 */
	public boolean insertAsFirstSon(BinaryTreeNode<E> node, BinaryTreeNode<E> father) {		
		boolean founded = false;

		if(node != null && father != null){
			InDepthIterator<E> iter = inDepthIterator();

			while(iter.hasNext() && ! founded){
				BinaryTreeNode<E> elem = iter.nextNode();

				if(father.equals(elem)){
					founded = true;

					if(father.getLeft() == null)
						father.setLeft(node);
					else{
						BinaryTreeNode<E> h = father.getLeft();
						node.setRight(h);
						father.setLeft(node);
					}				
				}
			}
		}

		return founded;		
	}

	/**
	 * Returns the level of the specified node in the tree.
	 * 
	 * @param node the node to find the level for
	 * @return the level of the node, or -1 if node is not found
	 */
	public int nodeLevel(TreeNode<E> node) {
		int level = -1;

		if(node != null){
			if(node.equals(root))
				level = 0;
			else{
				InBreadthIteratorWithLevels<E> iter = inBreadthIteratorWithLevels();

				boolean found = false;			

				while(iter.hasNext() && !found){
					BreadthNode<E> cursor =  iter.nextNodeWithLevel();

					if(cursor.getNode().equals(node)){
						found = true;

						level = cursor.getLevel();
					}
				}
			}
		}
		
		return level;
	}

	/**
	 * Returns the maximum level of the tree.
	 * 
	 * @return the tree level
	 */
	public int treeLevel() {				
		return nodeLevel(root);
	}

	/**
	 * Checks if the specified node is a leaf node.
	 * 
	 * @param node the node to check
	 * @return true if the node is a leaf, false otherwise
	 */
	public boolean nodeIsLeaf(TreeNode<E> node) {
		if(node != null)
			return ((BinaryTreeNode<E>)node).getLeft() == null;

		return false;
	}

	/**
	 * Returns the degree (number of children) of the specified node.
	 * 
	 * @param node the node to check
	 * @return the degree of the node
	 */
	public int nodeDegree (TreeNode<E> node) {
		int degree = -1;

		if(node != null){
			degree = 0;

			if (((BinaryTreeNode<E>)node).getLeft () != null) 
				degree = 1 + rightBrotherCount(((BinaryTreeNode<E>)node).getLeft());
		}
		return degree;
	}

	/**
	 * Counts the number of right siblings (children of the same parent).
	 * 
	 * @param node the starting node
	 * @return the count of right siblings
	 */
	private int rightBrotherCount(BinaryTreeNode<E> node) {
		int brother = 0;
		if (node.getRight() != null) 
			brother = 1 + rightBrotherCount(node.getRight());
		return brother;
	}

	/**
	 * Returns an in-depth (pre-order) iterator for the tree.
	 * 
	 * @return in-depth iterator
	 */
	public InDepthIterator<E> inDepthIterator(){
		return new InDepthIterator<E>(this);
	}

	/**
	 * Returns a breadth-first iterator for the tree.
	 * 
	 * @return breadth-first iterator
	 */
	public InBreadthIterator<E> inBreadthIterator(){
		return new InBreadthIterator<E>(this);
	}

	/**
	 * Returns a breadth-first iterator that includes level information.
	 * 
	 * @return breadth-first iterator with levels
	 */
	public InBreadthIteratorWithLevels<E> inBreadthIteratorWithLevels(){
		return new InBreadthIteratorWithLevels<E>(this);
	}

	/**
	 * Returns the height of the tree.
	 * 
	 * @return the tree height
	 */
	public int treeHeight() {
		int height = -1;
		InBreadthIteratorWithLevels<E> iter = inBreadthIteratorWithLevels();

		BreadthNode<E> lastNode = null;

		while(iter.hasNext())
			lastNode = iter.nextNodeWithLevel();

		if(lastNode != null)	
			height = lastNode.getLevel();

		return height;
	}

	/**
	 * Traverses the tree in pre-order and builds a string representation with visual formatting.
	 * 
	 * @param root the root node to start traversal from
	 * @param sb the string builder to append the representation to
	 * @return string representation of the tree
	 */
	private String traversePreOrder(BinaryTreeNode root, StringBuilder sb) {

		if (root == null) {
			return "";
		}

		sb.append(root.getInfo().toString());

		List<BinaryTreeNode> binaryTreeNodes = getSons(root);
		for (int i = 0; i < binaryTreeNodes.size(); i++) {
			boolean hasRightSibling = i < binaryTreeNodes.size() - 1;
			String currentPointer = (hasRightSibling) ? "├──" : "└──";
			BinaryTreeNode<E> currentNode = binaryTreeNodes.get(i);
			traverseNodes(sb, "", currentPointer, currentNode, hasRightSibling);
		}

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
			sb.append(node.getInfo().toString());

			StringBuilder paddingBuilder = new StringBuilder(padding);
			if (hasRightSibling) {
				paddingBuilder.append("│  ");
			} else {
				paddingBuilder.append("   ");
			}

			List<BinaryTreeNode> binaryTreeNodes = getSons(node);
			for (int i = 0; i < binaryTreeNodes.size(); i++) {
				hasRightSibling = i < binaryTreeNodes.size() - 1;
				String currentPointer = (hasRightSibling) ? "├──" : "└──";
				BinaryTreeNode<E> currentNode = binaryTreeNodes.get(i);
				traverseNodes(sb, paddingBuilder.toString(), currentPointer, currentNode, hasRightSibling);
			}
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