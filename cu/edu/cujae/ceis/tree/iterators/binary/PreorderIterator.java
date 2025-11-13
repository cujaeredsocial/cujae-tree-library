package cu.edu.cujae.ceis.tree.iterators.binary;

import java.util.ArrayDeque;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;
import cu.edu.cujae.ceis.tree.iterators.StackNode;

public class PreorderIterator<E> implements ITreeIterator<E>{
	private BinaryTreeNode<E> nextNode;
	private BinaryTreeNode<E> currentNode;
	private Tree<E> tree;	
	private ArrayDeque<StackNode<E>> stack;

	
	public PreorderIterator(Tree<E> tree) {
		this.currentNode = null;
		stack = new ArrayDeque<StackNode<E>>();
		this.nextNode = (BinaryTreeNode<E>) tree.getRoot();
		this.tree = tree;					
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
		if (currentNode != null)
			tree.deleteNode(currentNode);
	}
	
	public BinaryTreeNode<E> nextNode() {
		BinaryTreeNode<E> returnNode = nextNode;
		
		currentNode = nextNode;
		
		if(nextNode != null)
		{				
			if(nextNode.getLeft() != null){
				StackNode<E> newStackNode = new StackNode<E>(nextNode); 
				newStackNode.incrementCount();
				
				stack.push(newStackNode);
				nextNode = nextNode.getLeft();
			}
			else{								
				if(nextNode.getRight() != null){
					StackNode<E> newStackNode = new StackNode<E>(nextNode); 
					newStackNode.incrementCount();
					
					stack.push(newStackNode);
					
					//if(!stack.isEmpty()){
						StackNode<E> node = stack.pop();
						node.incrementCount();
					
						stack.push(node);											
					//}
					
					nextNode = nextNode.getRight();
				}
				else{										
					boolean foundedNextNode = false;
					
					while(!stack.isEmpty() && !foundedNextNode){
						StackNode<E> father = stack.pop();
						
						if(father.getRight() != null && father.getCount() == 1){
							foundedNextNode = true;
							
							nextNode = father.getRight();
							
							father.incrementCount();
							
							stack.push(father);
						}						
					}
						
					if(!foundedNextNode)
						nextNode = null;						
				}
			}
		}
		
		return returnNode;
	}	
}
