package ggze.tool;

import redis.clients.jedis.Jedis;

import java.io.*;
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
        try {
            RedisUtils.conntion.set(key.getBytes(),serialize(values));
            return 0;
        }catch (Exception e){
            return 1;
        }
    }

    public T getList(String key, T values){
        try {
            byte[] re = RedisUtils.conntion.get(key.getBytes());
            T obj=(T)unserizlize(re);
            return obj;
        }catch (Exception e){
            return null;
        }
    }

    //序列化
    public static byte [] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //反序列化
    public static Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {

            e.printStackTrace();
        }


        return null;
    }
}
