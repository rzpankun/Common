package com.ktiuu.tree;

/**
 * @Create by pankun
 * @DATE 2020/5/27
 */
public class AVLTree<T extends Comparable<T>> {
    public AVLNode<T> root;



    public boolean isEmpty(){
        return root == null;
    }

    public void makeEmpty(){
        root = null;
    }

    public T findMin(){
        return findMin(root).element;
    }

    private AVLNode<T> findMin(AVLNode<T> node){
        if(node.left == null){
            return node;
        }
        return findMin(node.left);
    }

    public T findMax(){
        return findMax(root).element;
    }

    private AVLNode<T> findMax(AVLNode<T> node){
        if(node.right == null){
            return node;
        }
        return findMax(node.right);
    }

    public boolean contains(T element){
        return contains(root, element);
    }

    private boolean contains(AVLNode<T> node , T element){
        if(node == null){
            return false;
        }
        int compareTo = node.element.compareTo(element);
        if(compareTo == 0){
            return true;
        }else if(compareTo > 0){
            return contains(node.left, element);
        }else {
            return contains(node.right, element);
        }
    }


    public void insert(T element){
        root = insert(root, element);
    }

    private AVLNode<T> insert(AVLNode<T> node , T element){
        if(node == null){
            return new AVLNode<T>(element);
        }
        int compareTo = node.element.compareTo(element);
        if(compareTo > 0){
            node.left = insert(node.left, element);
            if(height(node.left) - height(node.right) == 2){
                if(node.left.element.compareTo(element) > 0){
                    node = rotateWithLeftChild(node);
                }else{
                    node = doubleWithLeftChild(node);
                }
            }
        }else if(compareTo < 0){
            node.right = insert(node.right, element);
            if(height(node.right) - height(node.left) == 2){
                if(node.right.element.compareTo(element) > 0){
                    node = doubleWithRightChild(node);
                }else{
                    node = rotateWithRightChild(node);
                }
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void remove(T element){
        root = remove(root, element);
    }

    public AVLNode<T> remove(AVLNode<T> node,T element){
        if(node == null){
            return null;
        }
        int compareTo = node.element.compareTo(element);
        if(compareTo > 0){
            node.left = remove(node.left, element);
            node.height = Math.max(height(node.left), height(node.right)) + 1;
            if(height(node.right) - height(node.left) == 2){
                if(node.right.left != null){
                    node = doubleWithRightChild(node);
                }else{
                    node = rotateWithRightChild(node);
                }

            }
        }else if(compareTo < 0){
            node.right = remove(node.right, element);
            node.height = Math.max(height(node.left), height(node.left)) + 1;
            if(height(node.left) - height(node.right) == 2){
                if(node.left.right != null){
                    node = doubleWithLeftChild(node);
                }else{
                    node = rotateWithLeftChild(node);
                }
            }
        }else{
            if(node.left != null && node.right != null){
                T min = findMin(node.right).element;
                node.element = min;
                node.right = remove(node.right, min);
                if(height(node.left) - height(node.right) == 2){
                    node = rotateWithLeftChild(node);
                }
            }else{
                node = node.left != null ? node.left : node.right;
            }
        }
        return node;
    }
    private AVLNode<T> rotateWithLeftChild(AVLNode<T> node){
        AVLNode<T> left = node.left;
        node.left = left.right;
        left.right = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        left.height = Math.max(height(left.left),node.height) + 1;
        return left;
    }

    private AVLNode<T> rotateWithRightChild(AVLNode<T> node){
        AVLNode<T> right = node.right;
        node.right = right.left;
        right.left = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        right.height = Math.max(height(right.left),node.height) + 1;
        return right;
    }


    private AVLNode<T> doubleWithLeftChild(AVLNode<T> node){
        node.left = rotateWithRightChild(node.left);
        return rotateWithLeftChild(node);
    }

    private AVLNode<T> doubleWithRightChild(AVLNode<T> node){
        node.right = rotateWithLeftChild(node.right);
        return rotateWithRightChild(node);
    }




    private int height(AVLNode<T> element){
        return element == null ? -1 : element.height;
    }
    private void printTree(AVLNode<T> node , int i){
        if(node == null){
            return;
        }
        printSpit(i);
        System.out.print(node.element);
        System.out.println();
        int a = ++i;
        printTree(node.left,a);
        printTree(node.right,a);

    }
    public void printSpit(int i){
        for(int j = 0 ; j < i;j++){
            System.out.print("*");
        }
    }
    public void printTree(){
        printTree(root, 0);
    }

    static class AVLNode<T>{
        T element;
        AVLNode<T> left;
        AVLNode<T> right;
        int height;
        public AVLNode(){

        }
        public AVLNode(T element){
            this(element,null,null);
        }
        public AVLNode(T element , AVLNode<T> left , AVLNode<T> right){
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }

    }


    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(1);
        tree.insert(11);
        tree.insert(12);
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.printTree();
        System.out.println("=====================");
        tree.remove(12);
        tree.remove(11);
        tree.printTree();

    }
}
