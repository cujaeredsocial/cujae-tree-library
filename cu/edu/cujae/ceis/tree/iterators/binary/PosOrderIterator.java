package cu.edu.cujae.ceis.tree.iterators.binary;

import java.util.ArrayDeque;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;
import cu.edu.cujae.ceis.tree.iterators.StackNode;

public class PosOrderIterator<E> implements ITreeIterator<E> {
	private StackNode<E> nextNode;
	private BinaryTreeNode<E> currentNode;
	private Tree<E> tree;	
	private ArrayDeque<StackNode<E>> stack;

	public PosOrderIterator(Tree<E> tree) {
		this.tree = tree;

		stack = new ArrayDeque<StackNode<E>>();
		this.currentNode = null;
		this.nextNode = null;
		
		if(!tree.isEmpty())		
			this.nextNode = new StackNode<E>(moveCursorToLastLeftOrRightNode((BinaryTreeNode<E>)tree.getRoot()));
		
		this.tree = tree;	
	}

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
