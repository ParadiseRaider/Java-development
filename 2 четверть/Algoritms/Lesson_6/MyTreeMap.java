package Lesson_6;

import java.util.NoSuchElementException;

public class MyTreeMap<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int size;
        int height;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size = 1;
            height = 0;
        }
    }

    public int getHeight() {
        if (root==null) {
            return 0;
        }
        return root.height;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node==null) {
            return 0;
        }
        return node.size;
    }

    public boolean isEmpty() {
        return root==null;
    }

    public boolean isKeyNotNull(Key key) {
        if (key==null) {
            throw new IllegalArgumentException("key не должен быть null");
        }
        return true;
    }

    public final boolean contains(Key key) {
        return get(key) != null;
    }

    public final Value get(Key key) {
        isKeyNotNull(key);
        return get(root,key);
    }

    private Value get(Node node, Key key) {
        if (node==null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp==0) {
            return node.value;
        } else if (cmp<0) {
            return get(node.left,key);
        } else {
            return get(node.right,key);
        }
    }

    public final void put(Key key, Value value) {
        isKeyNotNull(key);
        if (value==null) {
            return;
        }
        root=put(root,key,value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node==null) {
            return new Node(key,value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp==0) {
            node.value = value;
        } else if (cmp<0) {
            node.left = put(node.left, key, value);
            if (node.height<node.left.height + 1) {
                node.height = node.left.height + 1;
            }
        } else {
            node.right = put(node.right, key, value);
            if (node.height<node.right.height + 1) {
                node.height = node.right.height + 1;
            }
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Key minKey() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left==null) {
            return node;
        }
        return min(node.left);
    }

    public void deletMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left==null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public final void delete(Key key) {
        isKeyNotNull(key);
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node==null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp<0) {
            node.left = delete(node.left, key);
        } else if (cmp>0) {
            node.right = delete(node.right,key);
        } else {
            if (node.left==null) {
                return node.right;
            }
            if (node.right==null) {
                return node.left;
            }
            Node tmp = node;
            node = min(node.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node==null) return true;
        int leftHight = (node.left==null) ? 0 : node.left.height;
        int rightHight = (node.right==null) ? 0 : node.right.height;
        boolean balanced = (Math.abs(leftHight-rightHight)<=1);
        if (!balanced) return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node node) {
        String res="";
        if(node == null){
            return "";
        }
        return toString(node.left)
                + "[" + node.key + "=" + node.value + "], "
                + toString(node.right);
    }
}
