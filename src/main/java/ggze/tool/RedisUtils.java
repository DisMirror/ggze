package ggze.tool;

import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisUtils<T> {
    private T key;

    public static Jedis conntion=new Jedis("120.77.251.162");

    public static int setString(String key,String value){
        try {
            RedisUtils.conntion.set(key,value);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
    public static String getString(String key){
        try {
            return RedisUtils.conntion.get(key);
        }catch (Exception e){
            return "0";
        }
    }

    public int setList(String key, T values){
        return 0;
    }
}
