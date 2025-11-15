package cu.edu.cujae.ceis.tree.iterators;

import java.util.Iterator;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

/**
 * Interface for tree iterators that can return both node information and node objects.
 * Extends the standard Iterator interface with additional functionality for tree traversal.
 * 
 * @param <E> the type of elements stored in the tree
 */
public interface ITreeIterator<E> extends Iterator<E> {
	/**
	 * Returns the next node in the iteration.
	 * 
	 * @return the next binary tree node
	 */
	BinaryTreeNode<E> nextNode();
}