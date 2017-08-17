package common.Redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/** redisCache
 * Created by paul on 2017/8/15.
 */
//@Component
public class RedisCache implements IRedis{


//    @Autowired
//    private  RedisResource redisResource;

    private ShardedJedis shardedJedis;


    private ShardedJedisPool shardedJedisPool;

    public ShardedJedis getShardedJedis() {
        return shardedJedis;
    }

    public void setShardedJedis(ShardedJedis shardedJedis) {
        this.shardedJedis = shardedJedis;
    }

    public ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    private void openShardedJedis() {
        shardedJedis=shardedJedisPool.getResource();
    }

    private void closeshardedJedis() {
        shardedJedis.close();
    }

    //    public void setShardedJedis(ShardedJedis shardedJedis) {
//        this.shardedJedis = redisResource.getShardedJedis();
//    }

    //    private static ShardedJedisPool shardedJedisPool;
//
//    public RedisCache() {
//
//    }
//
//    @Autowired
//    public RedisCache(ShardedJedisPool shardedJedisPool) {
//        RedisCache.shardedJedisPool = shardedJedisPool;
//    }
//
//    private  ShardedJedis shardedJedis;
//
//
//    public  ShardedJedis getShardedJedis(){
//        shardedJedis=shardedJedisPool.getResource();
//        return shardedJedis;
//    }
//
//
//
//    public  void closeshardedJedis(){
//        shardedJedis.close();
//    }



    @Override
    public String set(String key, String value) {
        openShardedJedis();
        String result = shardedJedis.set(key, value);
       closeshardedJedis();
        return result;
    }

    @Override
    public String get(String key) {
        openShardedJedis();
        String result=shardedJedis.get(key);
        closeshardedJedis();
        return result;
    }




}
