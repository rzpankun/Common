package com.ktiuu.reids;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.beans.beancontext.BeanContext;
import java.security.Key;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Create by pankun
 * @DATE 2020/5/22
 */
public class RedisUtils {
    /**
     * redis服务器ip
     */
    private static final Logger log = Logger.getLogger(RedisUtils.class);
    private static final String _ip = "39.105.198.99";
    private static final int _port = 6379;
    private static final int _maxIdle = 5;
    private static final int _maxTotal = 8;
    private static final int _minIdle = 3;
    private static final boolean _lifo = true;
    private static final long _minEvictableIdleTimeMillis = 600000;
    private static final boolean _testOnBorrow = false;
    private static final boolean _testOnReturn = false;
    private static final long _maxWaitMillis = -1;
    private static final boolean _testWhileIdle = false;
    private static final long _timeBetweenEvictionRunsMillis = -1;
    private static final int _numTestsPerEvictionRun = 3;
    private static JedisPoolConfig jedisPoolConfig ;
    private static JedisPool jedisPool;

    //    private static JedisCluster jedisCluster;
    static {
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(_maxIdle);
        jedisPoolConfig.setMaxTotal(_maxTotal);
        jedisPoolConfig.setMinIdle(_minIdle);
        jedisPoolConfig.setLifo(_lifo);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(_minEvictableIdleTimeMillis);
        jedisPoolConfig.setTestOnBorrow(_testOnBorrow);
        jedisPoolConfig.setTestOnReturn(_testOnReturn);
        jedisPoolConfig.setMaxWaitMillis(_maxWaitMillis);
        jedisPoolConfig.setTestWhileIdle(_testWhileIdle);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(_timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setNumTestsPerEvictionRun(_numTestsPerEvictionRun);
        jedisPool = new JedisPool(jedisPoolConfig, _ip, _port);

    }

    public static void set(String key , String value){
        Jedis jedis =jedisPool.getResource();
        try {
            String set = jedis.set(key, value);
            log.info(set);
        }finally {
            jedis.close();
        }

    }

    public static void setex(String key ,String value , int exprire){
        Jedis jedis =jedisPool.getResource();
        try {
            String setex = jedis.setex(key, exprire, value);
            log.info(setex);
        }finally {
            jedis.close();
        }

    }
    public static long setnx(String key,String value){
        Jedis jedis =jedisPool.getResource();
        try {
            Long setnx = jedis.setnx(key, value);
            log.info(setnx);
            return setnx;
        }finally {
            jedis.close();
        }

    }
    public static String set(String key ,String value,boolean exists,int expire){
        Jedis jedis =jedisPool.getResource();
        try {
            SetParams parms = new SetParams();
            if(exists){
                parms.nx();
            }
            if(expire > 0){
                parms.ex(expire);
            }
            String set = jedis.set(key, value, parms);
            log.info(set);
            return set;
        }finally {
            jedis.close();
        }

    }

    public static boolean expire(String key , int expire){
        Jedis jedis =jedisPool.getResource();
        try {
            Long expire1 = jedis.expire(key, expire);
            log.info(expire1);
            return expire1 > 0;
        }finally {
            jedis.close();
        }

    }

    public static long incr(String key){
        Jedis jedis =jedisPool.getResource();
        try {
            Long incr = jedis.incr(key);
            log.info(incr);
            return incr;
        }finally {
            jedis.close();
        }

    }
    public static long incrby(String key , long increment){
        Jedis jedis =jedisPool.getResource();
        try {
            Long aLong = jedis.incrBy(key, increment);
            log.info(aLong);
            return aLong;
        }finally {
            jedis.close();
        }

    }
    public static String get(String key){
        Jedis jedis =jedisPool.getResource();
        try {
            String s = jedis.get(key);
            log.info(s);
            return s;
        }finally {
            jedis.close();
        }
    }



    public static long rpush(String key , String... args){
        Jedis jedis =jedisPool.getResource();
        try {

            Long rpush = jedis.rpush(key, args);
            log.info(rpush);
            return rpush;
        }finally {
            jedis.close();
        }
    }

    public static long llen(String key){
        Jedis jedis =jedisPool.getResource();
        try {
            Long llen = jedis.llen(key);
            log.info(llen);
            return llen;
        }finally {
            jedis.close();
        }

    }

    public static String lpop(String key){
        Jedis jedis =jedisPool.getResource();
        try {
            String lpop = jedis.lpop(key);
            log.info(lpop);
            return lpop;
        }finally {
            jedis.close();
        }

    }

    public static String rpop(String key){
        Jedis jedis = jedisPool.getResource();
        try {
            String rpop = jedis.rpop(key);
            log.info(rpop);
            return rpop;
        }finally {
            jedis.close();
        }
    }
    public static String lindex(String key , long index){
        Jedis jedis =jedisPool.getResource();
        try {
            String lindex = jedis.lindex(key, index);
            log.info(lindex);
            return lindex;
        }finally {
            jedis.close();
        }

    }

    public static List<String> lrange(String key, long start, long stop){
        Jedis jedis =jedisPool.getResource();
        try {
            List<String> lrange = jedis.lrange(key, start, stop);
            log.info(lrange);
            return lrange;
        }finally {
            jedis.close();
        }
    }

    public static long hset(String key ,String field , String value){
        Jedis jedis =jedisPool.getResource();
        try {
            Long hset = jedis.hset(key, field, value);
            log.info(hset);
            return hset;
        }finally {
            jedis.close();
        }
    }
    public static long hlen(String key){
        Jedis jedis =jedisPool.getResource();
        try {
            Long hlen = jedis.hlen(key);
            log.info(hlen);
            return hlen;
        }finally {
            jedis.close();
        }
    }
    public static String hget(String key , String field){
        Jedis jedis =jedisPool.getResource();
        try {
            String hget = jedis.hget(key, field);
            log.info(hget);
            return hget;
        }finally {
            jedis.close();
        }
    }

    public static Map<String, String> hgetall(String key){
        Jedis jedis = jedisPool.getResource();
        try {
            Map<String, String> stringStringMap = jedis.hgetAll(key);
            log.info(stringStringMap);
            return stringStringMap;
        }finally {
            jedis.close();
        }
    }
    public static String hmset(String key , Map<String , String> map){
        Jedis jedis = jedisPool.getResource();
        try {
            String hmset = jedis.hmset(key, map);
            log.info(hmset);
            return hmset;
        }finally {
            jedis.close();
        }
    }
    public static List<String> hmget(String key , String... args){
        Jedis jedis = jedisPool.getResource();
        try {
            List<String> hmget = jedis.hmget(key, args);
            log.info(hmget);
            return hmget;
        }finally {
            jedis.close();
        }
    }

    public static long hincrby(String key , String field , long offset){
        Jedis jedis = jedisPool.getResource();
        try {
            Long aLong = jedis.hincrBy(key, field, offset);
            log.info(aLong);
            return aLong;
        }finally {
            jedis.close();
        }
    }

    public static long sadd(String key , String... args){
        try(Jedis jedis = jedisPool.getResource()){
            Long sadd = jedis.sadd(key, args);
            log.info(sadd);
            return sadd;
        }
    }

    public static Set<String> smembers(String key){
        try(Jedis jedis = jedisPool.getResource()){
            Set<String> smembers = jedis.smembers(key);
            log.info(smembers);
            return smembers;
        }
    }

    public static boolean sismember(String key , String member){
        try(Jedis jedis = jedisPool.getResource()){
            Boolean sismember = jedis.sismember(key, member);
            log.info(sismember);
            return sismember;
        }
    }

    public static long scard(String key){
        try(Jedis jedis = jedisPool.getResource()){
            Long scard = jedis.scard(key);
            log.info(scard);
            return scard;
        }
    }

}
