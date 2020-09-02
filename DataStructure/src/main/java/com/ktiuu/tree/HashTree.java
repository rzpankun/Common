package com.ktiuu.tree;

import java.time.temporal.Temporal;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Create by pankun
 * @DATE 2020/7/15
 */
public class HashTree {

    private static Integer[] PRIME_NUMBER = { 2,3,5,7,11,13,17,19,23,29};

    private Node root = new Node(0, PRIME_NUMBER[0]);




    public static class Node{
        private Integer data = 0;
        private Node[] next = null;
        public boolean isDel = false;

        public Node(Integer data, Integer level) {
            this.data = data;
            this.next = new Node[level];
        }

        public Node(Integer data){
            this.data = data;
        }
    }

    public boolean insertData(Integer data){
        return insertData(root, data, 0);
    }

    private boolean insertData(Node node, Integer data,Integer index){
        Integer a = data % PRIME_NUMBER[index];
        Node node1 = node.next[a];
        if(node1 == null){
            node.next[a] = new Node(data, PRIME_NUMBER[index + 1]);
        }else{
            if(node.isDel){
                node.isDel = false;
                node.data = data;
            }else{
                if(node.data != data){
                    insertData(node1, data, index+1);
                }
            }
        }
        return true;
    }


    public void showHashTree(){
        System.out.println("===========层序遍历开始============");
        Node point = this.root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(point);
        while(!queue.isEmpty()){
            Node poll = queue.poll();
            if(poll.data == 0){
                System.out.println("根节点 :" + poll.data);
            }
            System.out.print(" "+poll.data+" ");
            for(int i = 0 ; i < poll.next.length ; i++){
                if(poll.next[i] == null || poll.next[i].isDel){
                    continue;
                }else{
                    queue.offer(poll.next[i]);
                }
            }
        }
        System.out.println("===========层序遍历结束============");
    }


    public static void main(String[] args) {
        HashTree tree = new HashTree();
        tree.insertData(3);
        tree.insertData(3123);
        tree.insertData(1);
        tree.insertData(22);
        tree.insertData(8);
        tree.showHashTree();
    }
}
