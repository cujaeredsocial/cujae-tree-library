package cu.edu.cujae.ceis.tree.binary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.TreeNode;
import cu.edu.cujae.ceis.tree.iterators.binary.PosOrderIterator;
import cu.edu.cujae.ceis.tree.iterators.binary.PreorderIterator;
import cu.edu.cujae.ceis.tree.iterators.binary.SymmetricIterator;

public class BinaryTree<E> extends Tree<E> implements Serializable{

	private static final long serialVersionUID = 1L;

	public BinaryTree() {
		super();
	}

	public BinaryTree(TreeNode<E> root) {
		super(root);
	}

	public BinaryTree(BinaryTreeNode<E> root) {
		this.root = root;
	}
	
	protected int level(BinaryTreeNode<E> cursor) {
		if (cursor != null) {
			int levelLST = level(cursor.getLeft());
			int levelRST = level(cursor.getRight());
			return ((levelLST >= levelRST) ? levelLST : levelRST) + 1;
		}
		return -1;
	}

	public int treeLevel() {
		int level = -1;
		
		if(root != null)
			level = 0;
		
		return level;
	}

	public int nodeLevel(TreeNode<E> node) {
		if (node != null) {
			return node.equals(root) ? 0 : nodeLevel(getFather((BinaryTreeNode<E>)node)) + 1;
		}
		return -1;
	}		

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

	private void deleteNotRoot(BinaryTreeNode<E> node, BinaryTreeNode<E> father) {
		if (node != null && father != null) {			
			if(father.getLeft() != null && father.getLeft().equals(node))
				father.setLeft(null);
			else
				if(father.getRight() != null && father.getRight().equals(node))
					father.setRight(null);			
		}
	}

	public int nodeDegree(TreeNode<E> node) {
		int degree = 0;

		if (((BinaryTreeNode<E>)node).getLeft () != null)
			degree++;

		if (((BinaryTreeNode<E>)node).getRight () != null)
			degree++;

		return degree;
	}

	/*public boolean divideTree(BinaryTreeNode<E> node, BinaryTree<E> treeA,
			BinaryTree<E> treeB) {
		boolean divided = true;
		if (node != null) {
			if (node.equals(root)) {
				getNodeSubTree((BinaryTreeNode<E>) root, null, treeA);
				treeB.setRoot(null);
			} else {
				getNodeSubTree((BinaryTreeNode<E>) root, node, treeA);
				getNodeSubTree(node, null, treeB);
			}
		} else {
			treeA.setRoot(null);
			treeB.setRoot(null);
			divided = false;
		}
		return divided;
	}*/

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
					if (type == 'L') {//izq
						node.setLeft(father.getLeft());
						father.setLeft(node);
					} else {//der
						node.setRight(father.getRight());
						father.setRight(node);
					}
					inserted = true;
				}
			}//llave del else
		} //llave del if node  != null

		return inserted;
	}	

	public int totalNodes() {		
		int count = 0;

		PreorderIterator<E> iterator = preOrderIterator();		

		while(iterator.hasNext()){
			iterator.next();
			count++;			
		}

		return count;
	}

	public TreeNode<E> getRoot() {return root;}	

	public PreorderIterator<E> preOrderIterator(){
		return new PreorderIterator<E>(this);
	}

	public SymmetricIterator<E> symmetricIterator(){
		return new SymmetricIterator<E>(this);
	}

	public PosOrderIterator<E> posOrderIterator(){
		return new PosOrderIterator<E>(this);
	}

	public boolean nodeIsLeaf(TreeNode<E> node) {		
		return ((BinaryTreeNode<E>)node).getLeft() == null && ((BinaryTreeNode<E>)node).getRight() == null;
	}

	@Override
	public int treeHeight() {
		return level((BinaryTreeNode<E>) root);
	}

	// Added By Danielo
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		traversePreOrder((BinaryTreeNode<E>) root, sb);
		return sb.toString();
	}
}




