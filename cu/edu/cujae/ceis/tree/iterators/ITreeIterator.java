package cu.edu.cujae.ceis.tree.iterators;

import java.util.Iterator;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

public interface ITreeIterator<E> extends Iterator<E> {
	BinaryTreeNode<E> nextNode();
}
