package ggze.loader.Printing;

import ggze.loader.Storage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ggze.tool.FileUtils;

public class PrintMapper implements PrintDao {
    public StringBuilder mapperText= new StringBuilder();
    @Override
    public void printful() throws Exception {
        for (int i = 0; i < Storage.getObj().getBeanNames().size(); i++) {
            mapperText=new StringBuilder();
            this.printHead();
            this.prntSystem(Storage.getObj().getBeanNames().get(i));
            this.printing(Storage.getObj().getBeanNames().get(i));
        }
    }

    @Override
    public void printHead() {
        mapperText.append("<?xml version='1.0' encoding='UTF-8'?>\n");
        mapperText.append("<!DOCTYPE mapper PUBLIC '-//mybatis.org//DTD Mapper 3.0//EN' 'http://mybatis.org/dtd/mybatis-3-mapper.dtd'>\n");
    }

    @Override
    public void prntSystem(String beanName) throws Exception {
        String interPath = Storage.getObj().getConfig().get("page");
        interPath = interPath.substring(0, interPath.lastIndexOf(".")) + ".dao.inter";
        mapperText.append("<mapper namespace='"+interPath+"."+beanName+"Dao'>\n");
        mapperText.append("<insert id='add'>insert into "+beanName+" (${name0}) values ('${value0}')</insert>\n");
        mapperText.append("<delete id='delete'>delete from "+beanName+" where ${name1}='${value1}'</delete>\n");
        mapperText.append("<update id='update'>update "+beanName+" set ${name2}='${value2}' where ${name4}='${value4}'</update>\n");
        mapperText.append("<select id='allSelect' resultType='"+Storage.getObj().getConfig().get("page")+'.'+beanName+"'>select * from "+beanName+"</select>\n");
        mapperText.append("<select id='isSelect' resultType='"+Storage.getObj().getConfig().get("page")+'.'+beanName+"'>select  * from "+beanName+" where ${name3}='${value3}'</select>\n");
        if (Storage.getObj().getConfig().get("daoConfigSwitch").equals("true")) {
            this.printExtension(beanName);
        } else {
            mapperText.append("</mapper>");
        }
    }

    @Override
    public void printExtension(String beanName) throws Exception {
        JSONArray extension = JSONArray.fromObject(new FileUtils(Storage.getObj().getProjectPath(), "extension.json").OutText());
        for (int i = 0; i < extension.size(); i++) {
            JSONObject exOBJ = extension.getJSONObject(i);
            JSONObject exInterOBJ=exOBJ.getJSONObject("inter");
            exOBJ = exOBJ.getJSONObject("mapper");
            StringBuilder pub = new StringBuilder();
            pub.append(" <" + exOBJ.get("mapperType").toString());
            pub.append("  id='"+exInterOBJ.get("name")+"' ");
            pub.append("  resultType='"+Storage.getObj().getConfig().get("page")+"."+exOBJ.get("resultType").toString().replace("?",beanName)+"'>\n");
            pub.append("  "+exOBJ.get("sql").toString().replace("?",beanName)+"\n");
            pub.append(" </" + exOBJ.get("mapperType").toString()+">\n");
            mapperText.append(pub);

        }
        mapperText.append("</mapper>");
    }

    @Override
    public void printing(String beanName) throws Exception {
        System.out.println(mapperText);
        String interPath = Storage.getObj().getConfig().get("page");
        interPath = interPath.substring(0, interPath.lastIndexOf(".")) + "/dao/mapper";
        interPath = interPath.replace(".", "/");
        new FileUtils(Storage.getObj().getConfig().get("filePath") + "/" + interPath, beanName + "Mapper.xml").OutPut(mapperText.toString());
    }
}
