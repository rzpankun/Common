package com.ktiuu.examle;

public class SparseArrayUtils {


    /**
     * 将普通数据转化成稀疏数组
     * @param array
     * @param
     * @return
     *
     */
    public static int[][] convertArrayToSparse(int[][] array , int def){
        int rows = array.length;
        int columns = array[0].length;
        int count = 0;
        for (int[] ts : array) {
            for (int t : ts) {
                if(t != def){
                    count ++ ;
                }
            }
        }
        int[][] sparse = new int[count+1][3];
        sparse[0][0] = rows ;
        sparse[0][1] = columns;
        sparse[0][2] = count;
        int c = 0;
        for(int i = 0 ; i < array.length ; i ++){
            for(int j = 0 ; j < array[i].length ; j ++){
                if(array[i][j] != def){
                    c++;
                    sparse[c][0] = i;
                    sparse[c][1] = j;
                    sparse[c][2] = array[i][j];
                }
            }
        }
        return sparse;
    }


    /**
     *  将稀疏数组恢复成普通数据
     * @param sparse
     * @return
     */
    public static int[][] convertSparseToArray(int[][] sparse){
        int rows = sparse[0][0];
        int columns = sparse[0][1];
        int count = sparse[0][2];
        int[][] a = new int[rows][columns];
        for(int i = 1 ; i < sparse.length ; i ++){
            a[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        }
        return a;
    }
    public static void main(String[] args) {
        int[][] a  = new int[5][6];
        a[0][3] = 1;
        a[2][5] = 2;
        a[3][0] = 1;
        print(a);
        int[][] ints = convertArrayToSparse(a, 0);
        print(ints);


        int[][] ints1 = convertSparseToArray(ints);
        print(ints1);
    }


    public static void print(int[][] a){
        for (int[] ints : a) {
            for (int anInt : ints) {
                System.out.printf("%d " , anInt);
            }
            System.out.println();
        }
    }
}
