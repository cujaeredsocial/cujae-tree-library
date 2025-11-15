package cu.edu.cujae.ceis.tree.iterators.binary;

import java.util.ArrayDeque;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;
import cu.edu.cujae.ceis.tree.iterators.StackNode;

/**
 * Iterator for traversing a binary tree in in-order (left-root-right).
 * 
 * @param <E> the type of elements stored in the tree
 */
public class SymmetricIterator<E> implements ITreeIterator<E>{

	private BinaryTreeNode<E> nextNode;
	private BinaryTreeNode<E> currentNode;
	private Tree<E> tree;	
	private ArrayDeque<StackNode<E>> stack;

	/**
	 * Constructs an in-order iterator for the specified tree.
	 * 
	 * @param tree the tree to iterate over
	 */
	public SymmetricIterator(Tree<E> tree) {		
		this.tree = tree;

		stack = new ArrayDeque<StackNode<E>>();
		this.currentNode = null;
		this.nextNode = moveCursorToLastLeftNode((BinaryTreeNode<E>)tree.getRoot());
		this.tree = tree;		
	}

	/**
	 * Returns the next node in in-order traversal.
	 * 
	 * @return the next binary tree node
	 */
	public BinaryTreeNode<E> nextNode() {
		currentNode = nextNode;

		if(currentNode != null){
			if(currentNode.getRight() != null){
				StackNode<E> node = new StackNode<E>(currentNode);

				node.incrementCount();
				node.incrementCount();

				stack.push(node);

				nextNode = moveCursorToLastLeftNode(currentNode.getRight());
			}
			else{ 
				nextNode = null;

				if(!stack.isEmpty()){
					boolean foundedNextNode = false;

					while (!stack.isEmpty() && !foundedNextNode){
						StackNode<E> father = stack.pop();

						if(father.getCount() == 1){
							foundedNextNode = true;
							nextNode = father.getNode();
						}
					}
				}	
			}
		}

		return currentNode;
	}

	/**
	 * Returns true if the iteration has more elements.
	 * 
	 * @return true if there are more elements to iterate over
	 */
	public boolean hasNext() {
		return nextNode != null;
	}

	/**
	 * Returns the next element in the iteration.
	 * 
	 * @return the next element
	 */
	public E next() {
		E currentInfo = null;

		BinaryTreeNode<E> current = nextNode();

		if(current != null)
			currentInfo = current.getInfo();

		return currentInfo;
	}

	/**
	 * Removes the current node from the tree.
	 */
	public void remove() {
		tree.deleteNode(currentNode);
	}

	/**
	 * Moves the cursor to the last left node in the current path.
	 * 
	 * @param initialNode the starting node
	 * @return the last left node in the current path
	 */
	private BinaryTreeNode<E> moveCursorToLastLeftNode(BinaryTreeNode<E> initialNode){
		BinaryTreeNode<E> cursor = null;

		if(initialNode != null){
			cursor = initialNode;

			while(cursor.getLeft() != null){
				StackNode<E> node = new StackNode<E>(cursor);			
				node.incrementCount();

				stack.push(node);

				cursor = cursor.getLeft();
			}
		}

		return cursor;
	}
}