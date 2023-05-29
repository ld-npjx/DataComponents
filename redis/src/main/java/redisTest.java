import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Set;

public class redisTest {
    private JedisPool jedisPool;

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
        jedisPool=new JedisPool(config,"192.168.255.200",6379);
    }

    @AfterTest
    public void afterTest(){
        jedisPool.close();
    }

    @Test
    public void keyTest(){
        Jedis jedis=jedisPool.getResource();
        jedis.auth("123");

        Set<String> keys = jedis.keys("*");

        for (String key:keys) {
            System.out.println(key);
        }
    }
    @Test
    public void keyAdd(){
        Jedis resource = jedisPool.getResource();
        resource.auth("123");

//        resource.set("pv","0");
        System.out.println(resource.get("pv"));
        resource.incrBy("pv",1000);
        System.out.println(resource.get("pv"));
        resource.incr("pv");
        System.out.println(resource.get("pv"));

        resource.expire("pv",100);
        System.out.println(resource.ttl("pv"));
        System.out.println(resource.pttl("pv"));

    }

    @Test
    public void hashTest(){
        Jedis resource = jedisPool.getResource();
        resource.auth("123");
        //初始设置
        resource.hset("goods","iphone","10000");
        resource.hset("goods","macbookpro","8999");

        System.out.println(resource.hget("goods","iphone"));
        //得到商品名称集合
        Set<String> goods = resource.hkeys("goods");
        for(String good:goods)
            System.out.println(good);

        //得到并改变macbookpro数量
        String mac = resource.hget("goods", "macbookpro");
        Long aLong = Long.valueOf(mac);
        aLong+=2000;
        resource.hset("goods","macbookpor",aLong.toString());
        //原子自增
        resource.hincrBy("goods","iphone",2000);
        System.out.println(resource.hget("goods","iphone"));
        System.out.println(resource.hget("goods","macbookpor"));
    }


    @Test
    public void listTest(){
        Jedis resource = jedisPool.getResource();
        resource.auth("123");
        //从左右添加
        resource.lpush("list","1329713","1231242141");
        resource.rpush("list","123143455","321314143");
        //从右去除元素
        resource.rpop("list");
        List<String> list = resource.lrange("list", 0, -1);
        for(String string:list)
            System.out.println(string);
    }

    @Test
    public void setTest(){
        Jedis resource = jedisPool.getResource();
        resource.auth("123");

        resource.sadd("set","user1");
        resource.sadd("set","user2");
        resource.sadd("set","user1");

        System.out.println("uv:"+resource.scard("set"));
    }
}

