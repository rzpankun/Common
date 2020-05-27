package com.ktiuu.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Create by pankun
 * @DATE 2020/5/27
 * 二叉查找树
 */
public class BinarySearchTree<T extends Comparable <? super T>> {
    public BinaryNode<T> root;


    public BinarySearchTree(){
        this.root = null;
    }


    public void makeEmpty(){
        this.root = null;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public boolean contains(T t){
        return contains(root, t);
    }

    private boolean contains(BinaryNode<T> node , T element){
        if(node == null){
            return false;
        }
        int compareTo = node.element.compareTo(element);
        if(compareTo == 0){
            return true;
        }else if(compareTo > 0){
            return contains(node.left , element);
        }else {
            return contains(node.right , element);
        }
    }

    public T findMax(){
        if(isEmpty()){
            return null;
        }
        return findMax(root).element;
    }

    private BinaryNode<T> findMax(BinaryNode<T> node){


        if(node.right == null){
            return node;
        }else {
            return findMax(node.right);
        }
    }

    public T findMin(){
        if(isEmpty()){
            return null;
        }
        return findMin(root).element;
    }

    private BinaryNode<T> findMin(BinaryNode<T> node){
        if(node.left == null){
            return node;
        }else{
            return findMin(node.left);
        }
    }

    public void insert(T element){
        if(element == null){
            throw new RuntimeException("非法参数");
        }
        root = insert(root, element);
    }

    private BinaryNode<T> insert(BinaryNode<T> node , T element){
        if(node == null){
            node = new BinaryNode<>(element);
        }
        int compareTo = node.element.compareTo(element);
        if(compareTo > 0 ){
            node.left = insert(node.left, element);
        }else if(compareTo < 0){
            node.right = insert(node.right, element);
        }
        return node;
    }

    public void remove(T element){
        if(element == null){
            return;
        }
        root = remove(root, element);
    }

    private BinaryNode<T> remove(BinaryNode<T> node , T element){
        if(node == null){
            return null;
        }
        int compareTo = node.element.compareTo(element);
        if(compareTo > 0 ){
            node.left = remove(node.left, element);
        }else if(compareTo < 0){
            node.right = remove(node.right, element);
        }else{
            if(node.left != null && node.right != null){
                node.element = findMin(node.right).element;
                node.right = remove(node.right,node.element);
            }else{
                node = node.left != null ? node.left : node.right;
            }
        }
        return node;
    }









    static class BinaryNode<T>{
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;
        public BinaryNode(){

        }
        public BinaryNode(T element){
            this(element,null,null);
        }
        public BinaryNode(T element , BinaryNode<T> left , BinaryNode<T> right){
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }


    public static <T extends  Comparable<T>> void printBinaryTree(BinarySearchTree<T> tree){
        BinaryNode<T> root = tree.root;
        Queue<BinaryNode> queue = new LinkedList<>();
        if(root == null){
            return;
        }
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                BinaryNode poll = queue.poll();
                System.out.print(poll.element + " ");
                if(poll.left != null){
                    queue.offer(poll.left);
                }
                if(poll.right != null){
                    queue.offer(poll.right);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(3);
        tree.insert(4);
        tree.insert(1);
        tree.insert(5);
        printBinaryTree(tree);
        tree.remove(3);
        printBinaryTree(tree);
    }
}
