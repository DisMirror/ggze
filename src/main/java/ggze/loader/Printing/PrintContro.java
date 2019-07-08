package ggze.loader.Printing;


import ggze.loader.Storage;
import ggze.tool.FileUtils;
import net.sf.json.JSONObject;

public class PrintContro implements PrintDao {
    public String priString;
    public JSONObject jsonObject;
    @Override
    public void printful() throws Exception {
        for (int i = 0; i < Storage.getObj().getBeanNames().size(); i++) {
            jsonObject=new JSONObject();
            this.printHead();
            this.prntSystem(Storage.getObj().getBeanNames().get(i));
            if(Storage.getObj().config.get("noController").indexOf(Storage.getObj().getBeanNames().get(i))>=0){
                 continue;
            }else{
                this.printing(Storage.getObj().getBeanNames().get(i));
            }
        }
    }

    @Override
    public void printHead() {
        String interPath = Storage.getObj().getConfig().get("page");
        interPath = interPath.substring(0, interPath.lastIndexOf(".")) + ".controller";
        jsonObject.put("package",interPath);
    }

    @Override
    public void prntSystem(String beanName) throws Exception {
        String interPath = Storage.getObj().getConfig().get("page");
        String pathjx=interPath;
        interPath = interPath.substring(0, interPath.lastIndexOf(".")) + ".dao";
        jsonObject.put("daoURI",interPath+".inter."+beanName+"Dao");
        jsonObject.put("controller",beanName+"Controller");
        jsonObject.put("dao",beanName+"Dao");
        jsonObject.put("daoName",beanName+"Daoes");
        jsonObject.put("entityURI",pathjx+"."+beanName);
        jsonObject.put("entity",beanName);
        this.printExtension(beanName);
    }

    @Override
    public void printExtension(String beanName) throws Exception {
        FileUtils fileUtils=new FileUtils(this.getClass().getClassLoader().getResource("").getPath(),"control.txt");
        java.lang.String text=fileUtils.OutText();
        text=text.replace("$daoURI",jsonObject.get("daoURI").toString());
        text=text.replace("$controller",jsonObject.get("controller").toString());
        text=text.replace("$dao",jsonObject.get("dao").toString());
        text=text.replace("$daoName",jsonObject.get("daoName").toString());
        text=text.replace("$entityURI",jsonObject.get("entityURI").toString());
        text=text.replace("$entity",jsonObject.get("entity").toString());
        text=text.replace("$package",jsonObject.get("package").toString());
        this.priString=text;
    }

    @Override
    public void printing(String beanName) throws Exception {
        System.out.println(priString.toString());
        String interPath = Storage.getObj().getConfig().get("page");
        interPath = interPath.substring(0, interPath.lastIndexOf(".")) + "/controller";
        interPath = interPath.replace(".", "/");
        new FileUtils(Storage.getObj().getConfig().get("filePath") + "/" + interPath, beanName + "Controller.java").OutPut(priString);
    }
}

