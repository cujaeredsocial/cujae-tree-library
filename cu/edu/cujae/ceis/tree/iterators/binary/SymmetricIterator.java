package cu.edu.cujae.ceis.tree.iterators.binary;

import java.util.ArrayDeque;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;
import cu.edu.cujae.ceis.tree.iterators.StackNode;

public class SymmetricIterator<E> implements ITreeIterator<E>{

	private BinaryTreeNode<E> nextNode;
	private BinaryTreeNode<E> currentNode;
	private Tree<E> tree;	
	private ArrayDeque<StackNode<E>> stack;

	public SymmetricIterator(Tree<E> tree) {		
		this.tree = tree;

		stack = new ArrayDeque<StackNode<E>>();
		this.currentNode = null;
		this.nextNode = moveCursorToLastLeftNode((BinaryTreeNode<E>)tree.getRoot());
		this.tree = tree;		
	}

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

	public boolean hasNext() {
		return nextNode != null;
	}

	public E next() {
		E currentInfo = null;

		BinaryTreeNode<E> current = nextNode();

		if(current != null)
			currentInfo = current.getInfo();

		return currentInfo;
	}

	public void remove() {
		tree.deleteNode(currentNode);
	}

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
