package cu.edu.cujae.ceis.tree.iterators.binary;

import java.util.ArrayDeque;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;
import cu.edu.cujae.ceis.tree.iterators.StackNode;

/**
 * Iterator for traversing a binary tree in pre-order (root-left-right).
 * 
 * @param <E> the type of elements stored in the tree
 */
public class PreorderIterator<E> implements ITreeIterator<E>{
	private BinaryTreeNode<E> nextNode;
	private BinaryTreeNode<E> currentNode;
	private Tree<E> tree;	
	private ArrayDeque<StackNode<E>> stack;

	/**
	 * Constructs a pre-order iterator for the specified tree.
	 * 
	 * @param tree the tree to iterate over
	 */
	public PreorderIterator(Tree<E> tree) {
		this.currentNode = null;
		stack = new ArrayDeque<StackNode<E>>();
		this.nextNode = (BinaryTreeNode<E>) tree.getRoot();
		this.tree = tree;					
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
		E currentInfo = null;
		
		BinaryTreeNode<E> current = nextNode();
		
		if(current != null)
			currentInfo = current.getInfo();
		
		return currentInfo;		
	}

	/**
	 * Removes the current node from the tree.
	 */
	public void remove() {
		if (currentNode != null)
			tree.deleteNode(currentNode);
	}
	
	/**
	 * Returns the next node in pre-order traversal.
	 * 
	 * @return the next binary tree node
	 */
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
					
					StackNode<E> node = stack.pop();
					node.incrementCount();
				
					stack.push(node);											
					
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