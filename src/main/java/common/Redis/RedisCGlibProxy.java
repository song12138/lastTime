package common.Redis;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.lang.reflect.Method;

/**CGlib动态代理
 * Created by paul on 2017/8/15.
 */
@Component
public class RedisCGlibProxy implements MethodInterceptor{

//    @Autowired
    private ShardedJedisPool shardedJedisPool;
//
//
    private ShardedJedis shardedJedis;
//
//    public ShardedJedis getShardedJedis() {
//        return shardedJedis;
//    }
//
//    public void setShardedJedis(ShardedJedis shardedJedis) {
//        this.shardedJedis = shardedJedis;
//    }

    //    public ShardedJedisPool getShardedJedisPool() {
//        return shardedJedisPool;
//    }
//
//    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
//        this.shardedJedisPool = shardedJedisPool;
//    }

//    @Autowired
//    private RedisCache redisCache;

//    @Autowired
//    private RedisResource redisResource;




//    private Enhancer enhancer=new Enhancer();
//
//    public <T> T getProxy(Class<T> clazz){
//        //设置需要创建子类的类
//        enhancer.setSuperclass(clazz);
//        enhancer.setCallback(this);
//        //通过字节码技术动态创建子类实例
//        return (T) enhancer.create();
//    }

    @Autowired
    public RedisCGlibProxy(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //获取jedis连接
        shardedJedis=shardedJedisPool.getResource();
        Object result = methodProxy.invokeSuper(o, objects);
        //关闭
        shardedJedis.close();
        return result;
    }



}
