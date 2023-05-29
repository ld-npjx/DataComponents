import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class redisSentinelTest {

    private JedisSentinelPool jedisSentinelPool;
    @BeforeTest
    public void beforeTest(){
        JedisPoolConfig config = new JedisPoolConfig();
        //最大空闲连接
        config.setMaxIdle(10);
        //最小空闲连接
        config.setMaxIdle(5);

        //最大连接数
        config.setMaxTotal(50);

        //最大等待时间
        config.setMaxWaitMillis(3000);

        HashSet<String> sentinelSet = new HashSet<>();
        sentinelSet.add("hadoop200:26379");
        sentinelSet.add("hadoop201:26379");
        sentinelSet.add("hadoop202:26379");
        jedisSentinelPool=new JedisSentinelPool("hadoop200",sentinelSet,config,"123");

    }

    @Test
    public void test(){
        Jedis resource = jedisSentinelPool.getResource();

        Set<String> keys = resource.keys("*");
        for(String key:keys)
            System.out.println(key);
    }

    @AfterTest
    public void afterTest(){
        jedisSentinelPool.close();
    }
}
