package loader;

import actuator.ActuatorCommand;
import loader.Printing.PrintController;
import loader.scanning.ScanBean;
import loader.scanning.ScanConfig;
import loader.scanning.ScanController;

public class Loading {
    public Loading() throws Exception {
        //扫描配置文件
        //配置文件的地址
        String path = this.getClass().getClassLoader().getResource("config.properties").getPath();
        path=path.substring(1,path.length());
        Storage.getObj().setConfigPath(path);
        //项目的地址
        path = this.getClass().getClassLoader().getResource("").getPath();
        path=path .substring(1,path.length());
        Storage.getObj().setProjectPath(path);
        new ScanController();
        if(Storage.getObj().getConfig().get("input").equals("true")){
            new PrintController();
        }
    }
    /*public static void main(String[] args) throws Exception {
        new Loading();
    }*/
}