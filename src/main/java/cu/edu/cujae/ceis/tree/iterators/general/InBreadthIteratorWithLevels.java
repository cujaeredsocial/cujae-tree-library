package cu.edu.cujae.ceis.tree.iterators.general;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;

/**
 * Iterator for traversing a general tree in breadth-first order with level information.
 * 
 * @param <E> the type of elements stored in the tree
 */
public class InBreadthIteratorWithLevels<E> implements ITreeIterator<E> {
	private ArrayDeque<BreadthNode<E>> deque;
	private BreadthNode<E> currentNode;
	private BreadthNode<E> nextNode;
	GeneralTree<E> tree;
	
	/**
	 * Constructs a breadth-first iterator with levels for the specified general tree.
	 * 
	 * @param tree the tree to iterate over
	 */
	public InBreadthIteratorWithLevels(GeneralTree<E> tree) {	
		this.tree = tree;		
		currentNode = null;
		nextNode = new BreadthNode<E>((BinaryTreeNode<E>)tree.getRoot());
		deque = new ArrayDeque<BreadthNode<E>>();
		
		if(nextNode != null){
			ArrayList<BreadthNode<E>> sons = getSonsWithLevels(tree.getSons(nextNode.getNode()), nextNode.getLevel());
			
			deque.addAll(sons);
		}
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
				
				if(!tree.nodeIsLeaf(nextNode.getNode())){
					ArrayList<BreadthNode<E>> sons = getSonsWithLevels(tree.getSons(nextNode.getNode()), nextNode.getLevel());
					
					deque.addAll(sons);
				}
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
				
				if(!tree.nodeIsLeaf(nextNode.getNode())){
					ArrayList<BreadthNode<E>> sons = getSonsWithLevels(tree.getSons(nextNode.getNode()), nextNode.getLevel());
					
					deque.addAll(sons);
				}
			}
		}
		
		return currentNode.getNode();
	}
	
	/**
	 * Returns the next node with level information in breadth-first traversal.
	 * 
	 * @return the next breadth node containing both node and level information
	 */
	public BreadthNode<E> nextNodeWithLevel(){
		currentNode = nextNode;
		
		if(nextNode != null){							
			if(deque.isEmpty())
				nextNode = null;
			else{
				nextNode = deque.poll();
				
				if(!tree.nodeIsLeaf(nextNode.getNode())){
					ArrayList<BreadthNode<E>> sons = getSonsWithLevels(tree.getSons(nextNode.getNode()), nextNode.getLevel());
					
					deque.addAll(sons);
				}
			}
		}
		
		return currentNode;
	}
	
	/**
	 * Removes the current node from the tree.
	 */
	public void remove() {
		tree.deleteNode(currentNode.getNode());
	}

	/**
	 * Converts a list of binary tree nodes to breadth nodes with level information.
	 * 
	 * @param sons the list of child nodes
	 * @param fatherLevel the level of the parent node
	 * @return list of breadth nodes with appropriate levels
	 */
	public ArrayList<BreadthNode<E>> getSonsWithLevels(List<BinaryTreeNode<E>> sons, int fatherLevel){
		ArrayList<BreadthNode<E>> list = new ArrayList<BreadthNode<E>>(sons.size());
		
		for (BinaryTreeNode<E> node : sons) 
			list.add(new BreadthNode<E>(node, fatherLevel));
		
		return list;
	}
}