package main.game.map;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
  private NodeTree<String> root;

  public BinaryTree() {
    this.root = new NodeTree<>();
  }

  public void insert(String value) {
    if(this.root.getValue() == null) {
      this.root.setValue(value);
    }else {
      insertInTree(value, this.root);
    }
  }

  public void insertInTree(String value, NodeTree<String> node) {
    if(node.getValue().equals(value)) {
      if(node.getLeft() == null) {
        NodeTree<String> newNode = new NodeTree<String>();
        newNode.setValue(value);
        node.setLeft(newNode);
      }else {
        insertInTree(value, node.getLeft());
      }
    }else {
      if(node.getRight() == null) {
        NodeTree<String> newNode = new NodeTree<String>();
        newNode.setValue(value);
        node.setRight(newNode);
      }else {
        insertInTree(value, node.getRight());
      }
    }
  }

  public boolean findBFS(String value) {
    if (this.root == null) {
      System.out.println("Arvore vazia");
      return false;
    }
    Queue<NodeTree<String>> queue = new LinkedList<>();
    queue.add(this.root);

    while (!queue.isEmpty()) {
      NodeTree<String> nextNode = queue.poll();
      if(nextNode.getValue().equals(value)) {
        return true;
      }
      if(nextNode.getLeft() != null) {
        queue.add(nextNode.getLeft());
      }
      if(nextNode.getRight() != null) {
        queue.add(nextNode.getRight());
      }
    }

    return false;
  }

  public void DFS() {
    posOrder(this.root);
  }

  public void preOrder(NodeTree<String> node) {
    if(node != null && node.getValue() != null) {
      System.out.println(node.getValue() + "->");
      preOrder(node.getLeft());
      preOrder(node.getRight());
    }
  }

  public void inOrder(NodeTree<String> node) {
    if(node != null && node.getValue() != null) {
      inOrder(node.getLeft());
      System.out.println(node.getValue() + "->");
      inOrder(node.getRight());
    }
  }

  public void posOrder(NodeTree<String> node) {
    if(node != null && node.getValue() != null) {
      posOrder(node.getLeft());
      posOrder(node.getRight());
      System.out.println(node.getValue() + "->");
    }
  }

  public NodeTree<String> buildTree(String[][] map, int i, int j) {
    if(i < 0 || i >= map.length || j < 0 || j >= map[0].length) {
      return null;
    }
    
    NodeTree<String> newNode = new NodeTree<>();
    newNode.setValue(map[i][j]);

    newNode.setLeft(buildTree(map, i + 1, j));
    newNode.setRight(buildTree(map, i, j + 1));

    return newNode;
  }
}