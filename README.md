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
import cu.cujae.ceis.tree;
```

## Métodos Disponibles

```java
// Tree.java (Clase Abstracta)
isEmpty()                     // Verifica si el árbol está vacío
getRoot()                     // Obtiene el nodo raíz
setRoot(TreeNode<E> root)     // Establece el nodo raíz
totalNodes()                  // Retorna el total de nodos (abstracto)
getLeaves()                   // Obtiene todas las hojas (abstracto)
nodeLevel(TreeNode<E> node)   // Obtiene el nivel de un nodo (abstracto)
treeLevel()                   // Obtiene el nivel del árbol (abstracto)
treeHeight()                  // Obtiene la altura del árbol (abstracto)
nodeIsLeaf(TreeNode<E> node)  // Verifica si un nodo es hoja (abstracto)
nodeDegree(TreeNode<E> node)  // Obtiene el grado de un nodo (abstracto)
deleteNode(BinaryTreeNode<E> node) // Elimina un nodo (abstracto)
```

```java
// TreeNode.java (Clase Abstracta)
TreeNode()                    // Constructor por defecto
TreeNode(E info)              // Constructor con información
```

```java
// BinaryTree.java
BinaryTree()                  // Constructor vacío
BinaryTree(TreeNode<E> root)  // Constructor con raíz

treeLevel()                   // Obtiene el nivel del árbol
nodeLevel(TreeNode<E> node)   // Obtiene el nivel de un nodo
deleteNode(BinaryTreeNode<E> node) // Elimina un nodo
nodeDegree(TreeNode<E> node)  // Obtiene el grado de un nodo
getFather(BinaryTreeNode<E> node) // Obtiene el padre de un nodo
getLeaves()                   // Obtiene todas las hojas
getSons(BinaryTreeNode<E> node) // Obtiene los hijos de un nodo
getSubTree(BinaryTreeNode<E> node) // Obtiene un subárbol
insertNode(BinaryTreeNode<E> node, char type, BinaryTreeNode<E> father) // Inserta un nodo
totalNodes()                  // Cuenta el total de nodos
preOrderIterator()            // Iterador en preorden
symmetricIterator()           // Iterador simétrico (inorden)
posOrderIterator()            // Iterador en postorden
nodeIsLeaf(TreeNode<E> node)  // Verifica si un nodo es hoja
treeHeight()                  // Obtiene la altura del árbol
toString()                    // Representación visual del árbol
```

```java
// BinaryTreeNode.java
BinaryTreeNode()              // Constructor por defecto
BinaryTreeNode(E info)        // Constructor con información

getInfo()                     // Obtiene la información
setInfo(E info)               // Establece la información
getLeft()                     // Obtiene el hijo izquierdo
setLeft(BinaryTreeNode<E> left) // Establece el hijo izquierdo
getRight()                    // Obtiene el hijo derecho
setRight(BinaryTreeNode<E> right) // Establece el hijo derecho
```

```java
// GeneralTree.java
totalNodes()                  // Total de nodos en el árbol
deleteNode(BinaryTreeNode<E> node) // Elimina un nodo
getFather(BinaryTreeNode<E> node) // Obtiene el padre de un nodo
getLeaves()                   // Obtiene las hojas del árbol
getSons(BinaryTreeNode<E> node) // Obtiene los hijos de un nodo
getSonsInfo(BinaryTreeNode<E> node) // Obtiene la información de los hijos
insertNode(BinaryTreeNode<E> node, BinaryTreeNode<E> father) // Inserta un nodo
insertAsFirstSon(BinaryTreeNode<E> node, BinaryTreeNode<E> father) // Inserta como primer hijo
nodeLevel(TreeNode<E> node)   // Nivel de un nodo
treeLevel()                   // Nivel del árbol
nodeIsLeaf(TreeNode<E> node)  // Verifica si es hoja
nodeDegree(TreeNode<E> node)  // Grado de un nodo
inDepthIterator()             // Iterador en profundidad
inBreadthIterator()           // Iterador en anchura
inBreadthIteratorWithLevels() // Iterador en anchura con niveles
treeHeight()                  // Altura del árbol
toString()                    // Representación visual
```

```java
// LexicographicTree.java
insertValueRecursive(E info)  // Inserta valor recursivamente
deleteNode(E info)            // Elimina un nodo por información
insertValue(E value)          // Inserta un valor
getOrderedItems()             // Obtiene elementos ordenados
insertNode(BinaryTreeNode<E> node, char type, BinaryTreeNode<E> father) // Inserta nodo
```

```java
// Iteradores
PreorderIterator<E>           // Iterador preorden para árboles binarios
SymmetricIterator<E>          // Iterador simétrico para árboles binarios
PosOrderIterator<E>           // Iterador postorden para árboles binarios
InDepthIterator<E>            // Iterador en profundidad para árboles generales
InBreadthIterator<E>          // Iterador en anchura para árboles generales
InBreadthIteratorWithLevels<E> // Iterador en anchura con niveles
```