package cu.edu.cujae.ceis.tree.iterators.general;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;

public class InBreadthIteratorWithLevels<E> implements ITreeIterator<E> {
	private ArrayDeque<BreadthNode<E>> deque;
	private BreadthNode<E> currentNode;
	private BreadthNode<E> nextNode;
	GeneralTree<E> tree;
	
	
	
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
				
				if(!tree.nodeIsLeaf(nextNode.getNode())){
					ArrayList<BreadthNode<E>> sons = getSonsWithLevels(tree.getSons(nextNode.getNode()), nextNode.getLevel());
					
					deque.addAll(sons);
				}
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
				
				if(!tree.nodeIsLeaf(nextNode.getNode())){
					ArrayList<BreadthNode<E>> sons = getSonsWithLevels(tree.getSons(nextNode.getNode()), nextNode.getLevel());
					
					deque.addAll(sons);
				}
			}
		}
		
		return currentNode.getNode();
	}
	
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
	
	public void remove() {
		tree.deleteNode(currentNode.getNode());
	}

	public ArrayList<BreadthNode<E>> getSonsWithLevels(List<BinaryTreeNode<E>> sons, int fatherLevel){
		ArrayList<BreadthNode<E>> list = new ArrayList<BreadthNode<E>>(sons.size());
		
		for (BinaryTreeNode<E> node : sons) 
			list.add(new BreadthNode<E>(node, fatherLevel));
		
		return list;
	}
}
