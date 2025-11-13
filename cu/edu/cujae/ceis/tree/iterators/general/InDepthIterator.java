package cu.edu.cujae.ceis.tree.iterators.general;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.iterators.binary.PreorderIterator;

public class InDepthIterator<E> extends PreorderIterator<E> {

	public InDepthIterator(Tree<E> tree) {
		super(tree);
	}

}
