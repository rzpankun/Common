package com.ktiuu.reids;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Create by pankun
 * @DATE 2020/5/25
 */
public class HyperLogLogCheck {

    static class BitKeeper{
        private int maxbits;
        private int lowZeros(long value){
            int i = 1;
            for(;i < 32; i ++){
                if(value>>i<<i != value){
                    break;
                }
            }
            return i - 1;
        }
        public void random(){
            long l = ThreadLocalRandom.current().nextLong(2L << 32);
            int i = lowZeros(l);
            if(i > this.maxbits){
                this.maxbits = i;
            }
        }
    }

    static class Experiment{
        private int n;
        private BitKeeper keeper;

        public Experiment(int n, BitKeeper keeper) {
            this.n = n;
            this.keeper = keeper;
        }
        public void work(){
            for(int i = 0 ; i < n ; i ++){
                this.keeper.random();
            }
        }
        public void debug(){
            System.out.printf("%d %.2f %d\n",this.n, Math.log(this.n)/ Math.log(2),this.keeper.maxbits);
        }
    }


    public static void main(String[] args) {
        for(int i = 1000 ; i < 100000 ; i +=100){
            Experiment ex = new Experiment(i, new BitKeeper());
            ex.work();
            ex.debug();
        }
        /*Experiment experiment = new Experiment(1000, new BitKeeper());
        experiment.work();
        experiment.debug();*/
    }
}
