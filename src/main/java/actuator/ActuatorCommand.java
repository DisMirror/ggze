package actuator;

import loader.Storage;
import net.sf.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ActuatorCommand {
    private Map beanMap = new LinkedHashMap();

    //初始化方法，将传进来的实体转化成Map形式
    public ActuatorCommand(Object object) {
        Map<String, String> jsonMap = (Map<String, String>) JSONObject.fromObject(object);
        beanMap = jsonMap;
    }

    public String[] getBeanNames() {
        Set<String> set = beanMap.keySet();
        String[] name = new String[beanMap.size()];
        int x = 0;
        for (String key : set) {
            name[x] = key;

            x++;
        }
        x = 0;
        return name;
    }

    public String[] getBeanValues() {
        Set<String> set = beanMap.keySet();
        String[] value = new String[beanMap.size()];
        int x = 0;
        for (String key : set) {

            value[x] = beanMap.get(key).toString();
            x++;

        }
        x = 0;
        return value;
    }

    /*
     *
     * add方法，先增加主键为一行，其他为空，然后以update方式插入其他。请尽量在程序里控制字段是否为空
     * 返回值为主键uuid，请以最少为二范式来设计数据库
     *
     * */
    public String add(Object object) throws Exception {
        String uuid = null;
        Class uidCLZ = Class.forName(Storage.getObj().getConfig().get("uuidClass"));
        Object uidOBJ = uidCLZ.newInstance();
        uuid = (String) uidOBJ.getClass().getMethod(Storage.getObj().getConfig().get("uuidFunction"), null).invoke(uidOBJ);
        object.getClass().getMethod("add", String.class, String.class).invoke(object, "uuid", uuid);
        for (int i = 0; i < getBeanNames().length; i++) {
            if (getBeanNames()[i].toLowerCase().indexOf("uuid") >= 0) {
                continue;
            } else {
                if (getBeanNames()[i].equals("getClass")) {
                    continue;
                } else {
                    object.getClass().getMethod("update", String.class, String.class, String.class, String.class).invoke(object, getBeanNames()[i].replace("get", "").toLowerCase(), getBeanValues()[i], "uuid", uuid);
                    System.out.println(getBeanNames()[i].replace("get", "").toLowerCase() + ":" + getBeanValues()[i]);
                }
            }
        }
        return uuid;
    }

    /*
     *
     * update方法，一个字段一个字段更新，根据uuid主键更新
     *返回值为false或true
     * 这个字段如果为空，不更新，如果非要更细为空请写成空格
     *
     * */
    public boolean update(Object object) {
        try {
            for (int i = 0; i < getBeanNames().length; i++) {
                if (getBeanNames()[i].toLowerCase().equals("uuid") || getBeanValues()[i].length() == 0) {
                    continue;
                } else {
                    if (getBeanNames()[i].equals("getClass")) {
                        continue;
                    } else {
                        System.out.println(getBeanNames()[i].replace("get", "").toLowerCase() + ":" + getBeanValues()[i]);
                        object.getClass().getMethod("update", String.class, String.class, String.class, String.class).invoke(object, getBeanNames()[i].replace("get", "").toLowerCase(), getBeanValues()[i], "uuid", this.beanMap.get("getUuid"));
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("更新失败");
            return false;
        }
    }
}
