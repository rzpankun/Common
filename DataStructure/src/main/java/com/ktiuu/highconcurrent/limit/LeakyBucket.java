package com.ktiuu.highconcurrent.limit;

/**
 * @Create by pankun
 * @DATE 2020/11/17
 * 漏斗算法
 */
public class LeakyBucket {
    private static long time = System.currentTimeMillis();

    private static int water = 0;

    private static int size = 10;

    private static int rate = 3000;

    public static synchronized boolean grant(){
        long now = System.currentTimeMillis();
        int out = (int)(( now - time ) / 700 * rate);
        water = Math.max(0 , water - out);
        time = now;
        if(water + 1 < size){
            water ++;
            return true;
        }else{
            return false;
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(grant()){
                        System.out.println("执行业务逻辑");
                    }else{
                        System.out.println("限流");
                    }
                }
            }).start();
        }
    }
}
