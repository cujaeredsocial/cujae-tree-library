package cu.edu.cujae.ceis.tree.iterators.general;

import java.util.ArrayDeque;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;

public class InBreadthIterator<E> implements ITreeIterator<E> {
	private ArrayDeque<BinaryTreeNode<E>> deque;
	private BinaryTreeNode<E> currentNode;
	private BinaryTreeNode<E> nextNode;
	GeneralTree<E> tree;
	
	
	
	public InBreadthIterator(GeneralTree<E> tree) {	
		this.tree = tree;		
		currentNode = null;
		nextNode = (BinaryTreeNode<E>)tree.getRoot();
		deque = new ArrayDeque<BinaryTreeNode<E>>();
		
		if(nextNode != null)
			deque.addAll(tree.getSons(nextNode));
	}

	public boolean hasNext() {	
		return nextNode != null;
	}

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
	
	public void remove() {
		tree.deleteNode(currentNode);
	}

}
