package ggze.tool;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {
    private Properties pro;
    private String path;
    public PropertiesUtils(String path){
        this.path=path;
        Properties pro=new Properties();
        try {
            ClassPathResource classPathResource = new ClassPathResource("config.properties");
            InputStream is =classPathResource.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            pro.load(in);
            this.pro=pro;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getNode(String nodeName){
        return pro.getProperty(nodeName);
    }
    public int setNode(String nodeName,String nodeVale){
        pro.setProperty(nodeName,nodeVale);
        try {
            FileOutputStream fre=new FileOutputStream(path);
            pro.store(fre,"success");
            fre.close();
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }
    public Map<String,String> getNodes(){
        Map<String,String> nodes=new LinkedHashMap<String, String>();
        for(String key:pro.stringPropertyNames()){
            nodes.put(key,pro.getProperty(key));
        }
        return nodes;
    }
}
