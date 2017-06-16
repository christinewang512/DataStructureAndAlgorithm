package com.christine.algorithm.sort;

/**
 * Created by christine.wang on 6/6/2017.
 */
public class BinaryTree {
    private static Node root;

    public BinaryTree() {
        this.root = null;
    }

    /*
     * Find node in Binary Tree
     *
     * @param  data  search data
     * @return       search result
     */
    public boolean find(int data) {
        // check root: if it is null, then return false directly
        if (root == null) {
            return false;
        }

        Node current = root;
        while (current != null) {
            if (current.data == data) {
                return true;
            }

            if (current.data > data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    /*
     * Insert node into Binary Tree
     *
     * @param  data  insert data
     * @return  result of insertion
     */
    public boolean insert(int data) {
        if (root == null) {
            root = new Node(data);
            return true;
        }

        Node current = root;
        Node parent = null;
        boolean isLeft = true;
        while (current != null) {
            parent = current;
            if (current.data == data) {
                return false;
            } else if (current.data > data) {
                current = current.left;
                isLeft = true;
            } else {
                current = current.right;
                isLeft = false;
            }
        }
        if (isLeft) {
            parent.left = new Node(data);
            return true;
        } else {
            parent.right = new Node(data);
            return true;
        }
    }

    /*
     * Delete node from Binary Tree
     *
     * @param  data  delete data
     * @return delete result
     */
    public boolean delete(int data) {
        if (root == null) {
            return false;
        }

        Node current = root;
        Node parent = current;
        boolean isLeft = true;
        while (current != null) {
            if (current.data == data) {
                // if it is a leaf node, then delete node directly
                if (current.left == null && current.right == null) {
                    if (current == root) {
                        root = null;
                    }else if (isLeft) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                    current = null;
                }

                // if it has only one child, then delete it and add its child to its parent
                if (current.right == null || current.left == null) {
                    Node child = current.left == null ? current.right: current.left;
                    if (current == root) {
                        root = child;
                    } else if (isLeft) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                    current = null;
                }

                // if it has two children, find the smallest node in its right child to replace it
                Node succesor = findSuccessor(current);
                succesor.left = current.right;
                succesor.right = current.right;
                if (current == root) {
                    root = succesor;
                } else if (isLeft) {
                    parent.left = succesor;
                } else {
                    parent.right = succesor;
                }
                current = null;

            } else if (current.data > data) {
                current = current.left;
                isLeft = true;
            } else {
                current = current.right;
                isLeft = false;
            }
            return true;
        }
        return false;
    }

    private Node findSuccessor(Node current) {
        Node smaller = current;
        current = current.right;
        while (current != null) {
            if (smaller.data > current.data) {
                smaller = current;
                current = current.left;
            }
        }
        return smaller;
    }

    /*
     * Using inorder traversal to display Binary Tree
     *
     * @param node  current root node
     */
    public void display(Node node) {
        if (node.left != null) {
            display(node.left);
        }

        System.out.print(node.data + " ");

        if (node.right != null) {
            display(node.right);
        }

    }

    public static void main(String[] args) {
        int[] test = {62, 88, 58, 47, 35, 73, 51, 99, 37, 93};
        BinaryTree BT = new BinaryTree();
        for (int i = 0; i < test.length; i++) {
            BT.insert(test[i]);
        }
        BT.display(root);
    }

    /*
     * Binary Tree Node Structure
     */
    class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

}
