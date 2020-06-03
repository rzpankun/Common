package com.ktiuu.graph;

import sun.security.provider.certpath.Vertex;

/**
 * @Create by pankun
 * @DATE 2020/6/3
 * 图论
 * G(V,E)表示一个图，其中V是顶点Vertex的集合 E是边Edge的集合
 * 图的数据结构的表示方法有两种
 *     1。邻接矩阵
 *     2.邻接表
 * 下面的数据使用邻接矩阵表示
 * 平权无相图
 */
public class Graph {
    private final int MAX_SIZE = 20;
    private Vertex[] vertexList;
    private int[][] adjMat;
    private int nVerts;
    private StackX stack;
    public Graph(){
        vertexList = new Vertex[MAX_SIZE];
        adjMat = new int[MAX_SIZE][MAX_SIZE];
        nVerts = 0;
        for(int i = 0 ; i < MAX_SIZE ; i++){
            for(int j = 0 ; j < MAX_SIZE ; j ++){
                adjMat[i][j] = 0;
            }
        }
        stack = new StackX();
    }

    public void addVertex(String string){
        vertexList[nVerts ++] = new Vertex(string);
    }


    public void addEdge(int start , int end){
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }


    public void displayVertex(int v){
        System.out.print(vertexList[v].label);
    }


    /**
     * 深度优先搜索算法
     * 使用栈来实现
     */
    public void depthFirstSearch(){
        vertexList[0].wasVisited = true;
        displayVertex(0);
        stack.push(0);
        while(!stack.isEmpty()){
            int i = getadjUninvisitedVertex(stack.peek());
            if( i == -1){
                stack.pop();
            }else{
                vertexList[i].wasVisited = true;
                displayVertex(i);
                stack.push(i);
            }
        }
        for(int i = 0 ; i < nVerts ; i ++){
            vertexList[i].wasVisited = false;
        }
    }


    public int getadjUninvisitedVertex(int v){
        for(int i = 0 ; i < nVerts ; i ++){
            //v顶点与i顶点相邻，且未被访问
            if(adjMat[v][i] == 1 && vertexList[i].wasVisited == false ){
                return i;
            }
        }
        return -1;
    }



    static class Vertex{
        String label;
        boolean wasVisited;

        public Vertex(String label) {
            this.label = label;
            this.wasVisited = false;
        }
    }

    static class StackX{
        private final int SIZE = 20 ;
        private int[] st;
        private int top;

        public StackX(){
            st = new int[SIZE];
            top = -1;
        }

        public boolean isEmpty(){
            return top == -1;
        }

        public void push(int i){
            st[++top] = i;
        }

        public int pop(){
            return st[top--];
        }

        public int peek(){
            return st[top];
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("S");
        graph.addVertex("D");
        graph.addVertex("R");
        graph.addVertex("P");
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(0, 5);
        graph.depthFirstSearch();
    }

}
