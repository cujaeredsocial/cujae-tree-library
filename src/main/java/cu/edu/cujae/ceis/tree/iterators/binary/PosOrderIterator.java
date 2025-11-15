package cu.edu.cujae.ceis.tree.iterators.binary;

import java.util.ArrayDeque;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;
import cu.edu.cujae.ceis.tree.iterators.StackNode;

/**
 * Iterator for traversing a binary tree in post-order (left-right-root).
 * 
 * @param <E> the type of elements stored in the tree
 */
public class PosOrderIterator<E> implements ITreeIterator<E> {
	private StackNode<E> nextNode;
	private BinaryTreeNode<E> currentNode;
	private Tree<E> tree;	
	private ArrayDeque<StackNode<E>> stack;

	/**
	 * Constructs a post-order iterator for the specified tree.
	 * 
	 * @param tree the tree to iterate over
	 */
	public PosOrderIterator(Tree<E> tree) {
		this.tree = tree;

		stack = new ArrayDeque<StackNode<E>>();
		this.currentNode = null;
		this.nextNode = null;
		
		if(!tree.isEmpty())		
			this.nextNode = new StackNode<E>(moveCursorToLastLeftOrRightNode((BinaryTreeNode<E>)tree.getRoot()));
		
		this.tree = tree;	
	}

	/**
	 * Returns the next node in post-order traversal.
	 * 
	 * @return the next binary tree node
	 */
	public BinaryTreeNode<E> nextNode() {
		currentNode = null;

		if(nextNode != null){
			currentNode = nextNode.getNode();

			if(nextNode.getRight() != null && nextNode.getCount() != 2){					
				nextNode.incrementCount();
				nextNode.incrementCount();

				stack.push(nextNode);

				nextNode = new StackNode<E>(moveCursorToLastLeftOrRightNode(nextNode.getRight()));
			}
			else{ 
				nextNode = null;

				if(!stack.isEmpty()){					
					StackNode<E> father = stack.pop();

					nextNode = father;

					if(father.getCount() == 1 && father.getRight() != null){
						father.incrementCount();

						stack.push(father);

						nextNode = new StackNode<E>(moveCursorToLastLeftOrRightNode(father.getRight()));
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
	 * Moves the cursor to the last left or right node in the current path.
	 * 
	 * @param initialNode the starting node
	 * @return the last node in the current path
	 */
	private BinaryTreeNode<E> moveCursorToLastLeftOrRightNode(BinaryTreeNode<E> initialNode){
		BinaryTreeNode<E> cursor = initialNode;

		while(cursor.getLeft() != null){
			StackNode<E> node = new StackNode<E>(cursor);			
			node.incrementCount();

			stack.push(node);

			cursor = cursor.getLeft();
		}

		if(cursor.getRight() != null){
			StackNode<E> stackNode = new StackNode<E>(cursor);
			
			stackNode.incrementCount();
			stackNode.incrementCount();
			
			stack.push(stackNode);
			
			cursor = moveCursorToLastLeftOrRightNode(cursor.getRight());
		}
		
		return cursor;
	}
}