package cu.edu.cujae.ceis.tree.iterators.general;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.iterators.binary.PreorderIterator;

/**
 * Iterator for traversing a general tree in depth-first order (pre-order).
 * Extends the binary tree pre-order iterator for use with general trees.
 * 
 * @param <E> the type of elements stored in the tree
 */
public class InDepthIterator<E> extends PreorderIterator<E> {

	/**
	 * Constructs a depth-first iterator for the specified tree.
	 * 
	 * @param tree the tree to iterate over
	 */
	public InDepthIterator(Tree<E> tree) {
		super(tree);
	}
}