package com.ktiuu.highconcurrent.limit;

/**
 * @Create by pankun
 * @DATE 2020/11/17
 * 计数器限流
 *
 */
public class Counter {
    private static long timeStamp = System.currentTimeMillis();

    private static long limitCount = 100;

    private static long interval = 1000;

    private static long reqCount = 0;



    public static boolean grant(){
        long now = System.currentTimeMillis();
        if(now < timeStamp + interval){
            if(reqCount < limitCount){
                reqCount ++ ;
                return true;
            }else {
                return false;
            }
        }else{
            timeStamp = System.currentTimeMillis();
            reqCount = 0 ;
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("开始时间"+timeStamp);
        for(int i = 0 ; i < 500 ; i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(grant()){
                        System.out.println("执行业务逻辑 @" + System.currentTimeMillis());
                    }else{
                        System.out.println("限流 @" + System.currentTimeMillis());
                    }
                }
            }).start();
        }
    }
}
