package ggze.tool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BeanToJson {
    public static String toJsonArrayString(Object tobean){
        JSONArray jsonArray = JSONArray.fromObject(tobean);
        return jsonArray.toString();
    }
    public static String toJsonObjectString(Object tobean){
        JSONObject jsonObject = JSONObject .fromObject(tobean);
        return jsonObject.toString();
    }
    public static Object jsonTobean(String json,Class toClass){
        com.alibaba.fastjson.JSONObject jsonObject= com.alibaba.fastjson.JSONObject.parseObject(json);
        return  com.alibaba.fastjson.JSONObject.toJavaObject(jsonObject, toClass);
    }
}
