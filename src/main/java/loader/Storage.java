package loader;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Storage {
    /*
    * 临时存储区
    *
    * */
    private static Storage storage=new Storage();
    public static Storage getObj(){
        return storage;
    }
    /*
    *
    * 配置文件的绝对地址
    * */
    public String configPath;

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }
    /*
    * 项目的绝对地址
    *
    * */
    public String projectPath;

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }
    /*
    *
    * 扫描的配置文件
    *
    * */

    public Map<String,String> config=new LinkedHashMap<String, String>();

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    /*
    *实体的名字
    *
    * */
    public List<String> beanNames;

    public List<String> getBeanNames() {
        return beanNames;
    }

    public void setBeanNames(List<String> beanNames) {
        this.beanNames = beanNames;
    }

    /*
    *
    * 根据bean生成的sql
    *
    *
    */
    public  List<String> beanToSql=new LinkedList<String>();

    public List<String> getBeanToSql() {
        return beanToSql;
    }

    public void setBeanToSql(List<String> beanToSql) {
        this.beanToSql = beanToSql;
    }
}
