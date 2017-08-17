package common.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by paul on 2017/8/16.
 */
@Component
public class RedisResource {

    @Autowired
    private  ShardedJedisPool shardedJedisPool;




    private ShardedJedis shardedJedis;

    public ShardedJedis getShardedJedis() {
        return shardedJedis;
    }

    public void setShardedJedis(ShardedJedis shardedJedis) {
        this.shardedJedis =shardedJedis;
    }


    public void openShardedJedis(){
        this.setShardedJedis(shardedJedisPool.getResource());
    }

    public  void closeshardedJedis(){
        shardedJedis.close();
    }



}
