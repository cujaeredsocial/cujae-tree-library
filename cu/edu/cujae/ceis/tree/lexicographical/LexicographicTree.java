package cu.edu.cujae.ceis.tree.lexicographical;

import java.util.LinkedList;
import cu.edu.cujae.ceis.tree.binary.BinaryTree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.binary.SymmetricIterator;
/**
 * 
 * @author Diana Rodríguez Colli - Estudiante del if-24 curso 2013-2014
 *
 * @param <E>
 */

public class LexicographicTree<E> extends BinaryTree<E>{

	/**
	 * Los objetos del tipo E utilizados deben implementar la interfaz Comparable
	 */
	private static final long serialVersionUID = -4904578653765147521L;

	public enum Order {ASC, DESC};

	private Order order;

	public LexicographicTree(){
		super();

		this.order = Order.ASC;
	}

	public LexicographicTree(Order order){
		super();

		this.order = order;
	}

	public LexicographicTree(Order order, E rootInfo) throws DoesNotImplementsComparable{
		super();

		this.order = order;

		if(implementsComparable(rootInfo))
			setRoot(new BinaryTreeNode<E>(rootInfo));		
		else
			throw new DoesNotImplementsComparable("El objeto que se intenta insertar no implementa la interfaz Comparable");
	}

	public void insertValueRecursive(E info) throws DoesNotImplementsComparable {
		if(implementsComparable(info)){
			BinaryTreeNode<E> node = new BinaryTreeNode<E>(info);

			if (isEmpty())
				root = node;
			else {
				insertInTree((BinaryTreeNode<E>)root, node);
			}
		}
		else
			throw new DoesNotImplementsComparable("El objeto que se intenta insertar no implementa la interfaz Comparable");	
	}
	
	// Devuelve null si no el elemento no esta en el arbol

	public E deleteNode(E info) throws  DoesNotImplementsComparable{
		BinaryTreeNode<E> cursor;
		boolean delete = false;
		BinaryTreeNode<E> nodeToDelete= new BinaryTreeNode<E>();
		E deleteInfo = null;
		if(search(info)){

			if(((Comparable) ((BinaryTreeNode<E>)root).getInfo()).compareTo(info)==0){
				if(((BinaryTreeNode<E>)root).getLeft() == null && ((BinaryTreeNode<E>)root).getRight() ==null){
					setRoot(null);
				}
				else{
					if(((BinaryTreeNode<E>)root).getLeft() != null && ((BinaryTreeNode<E>)root).getRight() ==null){
						setRoot(((BinaryTreeNode<E>)root).getLeft());
					}
					else{
						if(((BinaryTreeNode<E>)root).getLeft() == null && ((BinaryTreeNode<E>)root).getRight() !=null){
							setRoot(((BinaryTreeNode<E>)root).getRight());
						}
						else{

							nodeToDelete = (BinaryTreeNode<E>)root;
							deleteInfo = delete(nodeToDelete,(BinaryTreeNode<E>)root).getInfo();

						}

					}
				}
			}

			else{

				cursor = (BinaryTreeNode<E>) this.getRoot();
				int compare;

				while(!delete){
					compare = ((Comparable)cursor.getInfo()).compareTo(info);

					if(this.order == Order.DESC){
						compare = compare*-1;
					}
					if(compare<0){
						cursor = cursor.getRight();
					}
					if(compare >0){
						cursor = cursor.getLeft();
					}
					if(compare==0){
						nodeToDelete = cursor;
						delete=true;
					}
				}
				deleteInfo = delete(nodeToDelete,getFather(nodeToDelete)).getInfo();
			}
		}

		return deleteInfo;
	}	

	//Busca el menor elemento en un subarbol
	private BinaryTreeNode<E> searchMinor(BinaryTreeNode<E> subTreeRoot) {

		if(this.order == Order.ASC){
			while(subTreeRoot.getLeft()!=null){
				subTreeRoot = subTreeRoot.getLeft();
			}
		}
		else{
			while(subTreeRoot.getRight()!=null){

				subTreeRoot = subTreeRoot.getRight();
			}
		}
		return subTreeRoot;
	}

	//Devuelve la nueva raiz del arbol
	private BinaryTreeNode<E> deleteMin(BinaryTreeNode<E> subTreeRoot){

		if(this.order == Order.ASC){
			if(subTreeRoot.getLeft() != null){
				subTreeRoot.setLeft(deleteMin(subTreeRoot.getLeft()));
			}
			else
				subTreeRoot = subTreeRoot.getRight();
		}
		else{
			if(subTreeRoot.getRight() != null){
				subTreeRoot.setRight(deleteMin(subTreeRoot.getRight()));
			}
			else
				subTreeRoot = subTreeRoot.getLeft();
		}
		return subTreeRoot;
	}

	private BinaryTreeNode<E> delete(BinaryTreeNode<E> nodeToDelete,BinaryTreeNode<E> subTreeRoot) throws DoesNotImplementsComparable{
		if(implementsComparable(nodeToDelete.getInfo())){

			if(this.order == Order.ASC){
				if(((Comparable)nodeToDelete.getInfo()).compareTo(subTreeRoot.getInfo())<0){
					subTreeRoot.setLeft(delete(nodeToDelete,subTreeRoot.getLeft()));
				}
				else
					if(((Comparable)nodeToDelete.getInfo()).compareTo(subTreeRoot.getInfo())>0){
						subTreeRoot.setRight(delete(nodeToDelete,subTreeRoot.getRight()));
					}
					else
						if(subTreeRoot.getLeft()!=null && subTreeRoot.getRight()!=null){//Dos hijos
							subTreeRoot.setInfo(searchMinor(subTreeRoot.getRight()).getInfo());
							subTreeRoot.setRight(deleteMin(subTreeRoot.getRight()));
						}
						else{//Cambio de raiz
							subTreeRoot = (subTreeRoot.getLeft() !=null) ? subTreeRoot.getLeft():subTreeRoot.getRight();

						}
			}
			else{
				if(((Comparable)nodeToDelete.getInfo()).compareTo(subTreeRoot.getInfo())<0){
					subTreeRoot.setRight(delete(nodeToDelete,subTreeRoot.getRight()));
				}
				else
					if(((Comparable)nodeToDelete.getInfo()).compareTo(subTreeRoot.getInfo())>0){
						subTreeRoot.setLeft(delete(nodeToDelete,subTreeRoot.getLeft()));
					}
					else
						if(subTreeRoot.getLeft()!=null && subTreeRoot.getRight()!=null){//Dos hijos
							subTreeRoot.setInfo(searchMinor(subTreeRoot.getLeft()).getInfo());
							subTreeRoot.setLeft(deleteMin(subTreeRoot.getLeft()));
						}
						else{//Cambio de raiz
							subTreeRoot = (subTreeRoot.getRight() !=null) ? subTreeRoot.getRight():subTreeRoot.getLeft();

						}
			}
		}
		else{
			throw new DoesNotImplementsComparable("El objeto que se intenta insertar no implementa la interfaz Comparable");
		}
		return subTreeRoot;
	}

	//Busca un valor en el arbol

	private boolean search(E value){

		BinaryTreeNode<E> cursor;
		boolean found = false;
		BinaryTreeNode<E> nodeToInsert= new BinaryTreeNode<E>();
		nodeToInsert.setInfo(value);
		if(root != null && implementsComparable(value)){

			cursor = (BinaryTreeNode<E>) this.getRoot();
			int compare;
			while(!found && cursor !=null){
				compare = ((Comparable)cursor.getInfo()).compareTo(value);

				if(this.order == Order.DESC){
					compare = compare*-1;
				}
				if(compare<0)
					cursor = cursor.getRight();
				if(compare >0){
					cursor = cursor.getLeft();
				}
				if(compare==0){
					found = true;
				}
			}
		}
		return found;
	}

	private void insertInTree(BinaryTreeNode<E> root, BinaryTreeNode<E> node){
		int compareValue = ((Comparable)root.getInfo()).compareTo(node.getInfo());

		if(order == Order.ASC)
			compareValue = compareValue * -1; //Invertir el orden

		if(compareValue < 0){
			if(root.getRight() != null)
				insertInTree(root.getRight(), node);
			else
				root.setRight(node);
		}
		else{
			if(root.getLeft() != null)
				insertInTree(root.getLeft(), node);
			else
				root.setLeft(node);
		}
	}

	public void insertValue(E value) throws DoesNotImplementsComparable {

		BinaryTreeNode<E> cursor;
		boolean insert = false;
		BinaryTreeNode<E> nodeToInsert= new BinaryTreeNode<E>();
		nodeToInsert.setInfo(value);
		if(!implementsComparable(value)){
			throw new DoesNotImplementsComparable("El objeto que se intenta insertar no implementa la interfaz Comparable");
		}
		else{
			if(root == null){

				this.setRoot(nodeToInsert);
			}
			else{
				cursor = (BinaryTreeNode<E>) this.getRoot();
				int compare;
				while(!insert){
					compare = ((Comparable)cursor.getInfo()).compareTo(value);

					if(this.order == Order.DESC){
						compare = compare*-1;
					}

					if(compare<0){
						if(cursor.getRight() == null){
							cursor.setRight(nodeToInsert);
							insert=true;
						}
						else{
							cursor = cursor.getRight();
						}
					}
					if(compare >0){
						if(cursor.getLeft() == null){
							cursor.setLeft(nodeToInsert);
							insert=true;
						}
						else{
							cursor = cursor.getLeft();
						}
					}
				}
			}
		}


	}

	public LinkedList<E> getOrderedItems(){
		LinkedList<E> orderedItems = new LinkedList<E>();

		SymmetricIterator<E> iter = symmetricIterator();

		while(iter.hasNext())
			orderedItems.add(iter.next());

		return orderedItems;
	}

	public boolean insertNode(BinaryTreeNode<E> node, char type, BinaryTreeNode<E> father) {		
		boolean inserted = false;

		if (node != null) {
			inserted = true;

			try {
				insertValue(node.getInfo());
			} catch (DoesNotImplementsComparable e) {
				inserted = false;

				e.printStackTrace();
			}
		}

		return inserted;
	}

	private boolean implementsComparable(Object object){
		boolean doesImplements = false;

		Class[] classes = object.getClass().getInterfaces();

		int pos = 0;

		while(pos < classes.length && !doesImplements){
			if(classes[pos].getCanonicalName().equals("java.lang.Comparable"))
				doesImplements = true;

			pos++;
		}			

		return doesImplements;
	}


}
