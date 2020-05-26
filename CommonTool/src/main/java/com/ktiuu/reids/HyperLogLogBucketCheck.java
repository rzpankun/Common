package com.ktiuu.reids;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Create by pankun
 * @DATE 2020/5/25
 */
public class HyperLogLogBucketCheck {
    static class BitKeeper{
        private int maxbits;
        public void random(long m){
            int i = lowZeros(m);
            if(i > this.maxbits){
                this.maxbits = i;
            }

        }

        private int lowZeros(long l){
            int i = 1;
            for(;i < 32; i ++){
                if(l>>i<<i != l){
                    break;
                }
            }
            return i - 1;
        }
    }

    static class Experiment{
        private int n;
        private int k;
        private BitKeeper[] keepers;


        public Experiment(int n ){
            this(n,1024);
        }

        public Experiment(int n , int k){
            this.n = n ;
            this.k = k;
            this.keepers = new BitKeeper[k];
            for(int i = 0 ; i < k ; i ++){
                this.keepers[i] = new BitKeeper();
            }
        }

        public void work(){
            for(int i = 0 ; i < this.n ; i ++){
                long l = ThreadLocalRandom.current().nextLong(1l << 32);
                BitKeeper keeper = keepers[(int) (((l & 0xfff00000) >> 16) % keepers.length)];
                keeper.random(l);
            }
        }


        public double estimate(){
            double sumBitsInverse = 0.0;
            for(BitKeeper keeper : keepers){
                sumBitsInverse += 1.0/(float)keeper.maxbits;
            }
            double v = (float) keepers.length / sumBitsInverse;

            return Math.pow(2, v) * this.k;
        }
    }

    public static void main(String[] args) {
        for(int i = 100000 ; i < 1000000 ; i += 100000){
            Experiment experiment = new Experiment(i);
            experiment.work();
            double estimate = experiment.estimate();
            System.out.printf("%d %.2f %.2f\n", i , estimate , Math.abs(estimate - 1)/i);
        }
    }
}
