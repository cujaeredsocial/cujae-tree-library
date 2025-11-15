package cu.edu.cujae.ceis.tree.iterators.general;

import java.util.ArrayDeque;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;

/**
 * Iterator for traversing a general tree in breadth-first order (level order).
 * 
 * @param <E> the type of elements stored in the tree
 */
public class InBreadthIterator<E> implements ITreeIterator<E> {
	private ArrayDeque<BinaryTreeNode<E>> deque;
	private BinaryTreeNode<E> currentNode;
	private BinaryTreeNode<E> nextNode;
	GeneralTree<E> tree;
	
	/**
	 * Constructs a breadth-first iterator for the specified general tree.
	 * 
	 * @param tree the tree to iterate over
	 */
	public InBreadthIterator(GeneralTree<E> tree) {	
		this.tree = tree;		
		currentNode = null;
		nextNode = (BinaryTreeNode<E>)tree.getRoot();
		deque = new ArrayDeque<BinaryTreeNode<E>>();
		
		if(nextNode != null)
			deque.addAll(tree.getSons(nextNode));
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
		E returnInfo = null;
		currentNode = nextNode;
		
		if(nextNode != null){
			returnInfo = nextNode.getInfo();			
			
			if(deque.isEmpty())
				nextNode = null;
			else{
				nextNode = deque.poll();
				
				if(!tree.nodeIsLeaf(nextNode))
					deque.addAll(tree.getSons(nextNode));
			}
		}
		
		return returnInfo;
	}

	/**
	 * Returns the next node in breadth-first traversal.
	 * 
	 * @return the next binary tree node
	 */
	public BinaryTreeNode<E> nextNode(){
		currentNode = nextNode;
		
		if(nextNode != null){							
			if(deque.isEmpty())
				nextNode = null;
			else{
				nextNode = deque.poll();
				
				if(!tree.nodeIsLeaf(nextNode))
					deque.addAll(tree.getSons(nextNode));
			}
		}
		
		return currentNode;
	}
	
	/**
	 * Removes the current node from the tree.
	 */
	public void remove() {
		tree.deleteNode(currentNode);
	}
}