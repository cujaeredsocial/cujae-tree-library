# Cujae Tree Library

![CUJAE](https://img.shields.io/badge/CUJAE-University-blue?style=for-the-badge&logo=graduation-cap)
![Java 8](https://img.shields.io/badge/Java->=8-orange?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Apache%20Maven-3.8.7-red?style=for-the-badge&logo=apache-maven)

Librería para el trabajo con estructuras de árboles del CEIS (Cujae) para estudiantes de Ingeniería Informática de segundo año en la asignatura Estructura de Datos.

## Instalación

Esta librería está disponible como archivo JAR en la sección de Releases. Para utilizarla en tu proyecto:

1. Descarga el archivo `.jar` de la última release
2. Importa el JAR en tu proyecto:
   - **En Eclipse**: Click derecho en el proyecto → Build Path → Configure Build Path → Libraries → Add External JARs
   - **En IntelliJ**: File → Project Structure → Modules → Dependencies → + → JARs or directories
   - **En NetBeans**: Click derecho en el proyecto → Properties → Libraries → Add JAR/Folder

## Estructura del Proyecto

La librería contiene implementaciones de diferentes tipos de árboles:
- Árboles binarios
- Árboles generales  
- Árboles lexicográficos

## Paquete
```java
import cu.edu.cujae.ceis.tree;
```

## Métodos Disponibles

```java
// Tree.java (Clase Abstracta)
public abstract class Tree<E> {
    public boolean isEmpty()
    public TreeNode<E> getRoot()
    public void setRoot(TreeNode<E> root)
    public abstract int totalNodes()
    public abstract List<TreeNode<E>> getLeaves()
    public abstract int nodeLevel(TreeNode<E> node)
    public abstract int treeLevel()
    public abstract int treeHeight()
    public abstract boolean nodeIsLeaf(TreeNode<E> node)
    public abstract int nodeDegree(TreeNode<E> node)
    public abstract void deleteNode(BinaryTreeNode<E> node)
}
```

```java
// TreeNode.java (Clase Abstracta)
public abstract class TreeNode<E> {
    public TreeNode()
    public TreeNode(E info)
}
```

```java
// BinaryTree.java
public class BinaryTree<E> extends Tree<E> {
    public BinaryTree()
    public BinaryTree(TreeNode<E> root)
    
    public int treeLevel()
    public int nodeLevel(TreeNode<E> node)
    public void deleteNode(BinaryTreeNode<E> node)
    public int nodeDegree(TreeNode<E> node)
    public BinaryTreeNode<E> getFather(BinaryTreeNode<E> node)
    public List<TreeNode<E>> getLeaves()
    public List<BinaryTreeNode<E>> getSons(BinaryTreeNode<E> node)
    public BinaryTree<E> getSubTree(BinaryTreeNode<E> node)
    public void insertNode(BinaryTreeNode<E> node, char type, BinaryTreeNode<E> father)
    public int totalNodes()
    public Iterator<E> preOrderIterator()
    public Iterator<E> symmetricIterator()
    public Iterator<E> posOrderIterator()
    public boolean nodeIsLeaf(TreeNode<E> node)
    public int treeHeight()
    public String toString()
}
```

```java
// BinaryTreeNode.java
public class BinaryTreeNode<E> extends TreeNode<E> {
    public BinaryTreeNode()
    public BinaryTreeNode(E info)
    
    public E getInfo()
    public void setInfo(E info)
    public BinaryTreeNode<E> getLeft()
    public void setLeft(BinaryTreeNode<E> left)
    public BinaryTreeNode<E> getRight()
    public void setRight(BinaryTreeNode<E> right)
}
```

```java
// GeneralTree.java
public class GeneralTree<E> extends Tree<E> {
    public int totalNodes()
    public void deleteNode(BinaryTreeNode<E> node)
    public BinaryTreeNode<E> getFather(BinaryTreeNode<E> node)
    public List<TreeNode<E>> getLeaves()
    public List<BinaryTreeNode<E>> getSons(BinaryTreeNode<E> node)
    public List<E> getSonsInfo(BinaryTreeNode<E> node)
    public void insertNode(BinaryTreeNode<E> node, BinaryTreeNode<E> father)
    public void insertAsFirstSon(BinaryTreeNode<E> node, BinaryTreeNode<E> father)
    public int nodeLevel(TreeNode<E> node)
    public int treeLevel()
    public boolean nodeIsLeaf(TreeNode<E> node)
    public int nodeDegree(TreeNode<E> node)
    public Iterator<E> inDepthIterator()
    public Iterator<E> inBreadthIterator()
    public Iterator<Pair<E, Integer>> inBreadthIteratorWithLevels()
    public int treeHeight()
    public String toString()
}
```

```java
// LexicographicTree.java
public class LexicographicTree<E> extends BinaryTree<E> {
    public void insertValueRecursive(E info)
    public void deleteNode(E info)
    public void insertValue(E value)
    public List<E> getOrderedItems()
    public void insertNode(BinaryTreeNode<E> node, char type, BinaryTreeNode<E> father)
}
```

```java
// Iteradores
public class PreorderIterator<E> implements Iterator<E>
public class SymmetricIterator<E> implements Iterator<E>
public class PosOrderIterator<E> implements Iterator<E>
public class InDepthIterator<E> implements Iterator<E>
public class InBreadthIterator<E> implements Iterator<E>
public class InBreadthIteratorWithLevels<E> implements Iterator<Pair<E, Integer>>
```