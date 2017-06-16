package com.christine.algorithm.sort;

/**
 * Created by christine.wang on 6/16/2017.
 */
public class AVLTree {
    private static Node root;

    public AVLTree() {
        this.root = null;
    }

    public Node rightRotate(Node node) {
        Node left = node.left;
        left.right = node;
        node.left = left.right;
        return left;
    }

    public Node leftRotate(Node node) {
        Node right = node.right;
        right.left = node;
        node.right = right.left;
        return right;
    }

    public boolean insert(int data) {
        if (root == null) {
            root = new Node(data);
            return true;
        }

        Node current = root;
        while (current != null) {
            if (current.data == data) {
                return false;
            }

            if (current.data > data) {
                insert(current.left, data);
            }
        }
    }

    /*
     * Binary Tree Node Structure
     */
    class Node {
        int data;
        Node left;
        Node right;
        int height;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }
}
