package ggze.loader.scanning;

import ggze.loader.Storage;

public class ScanController {
    public ScanController(){
        //装载配置文件
        new ScanConfig().scaning();
        //扫描bean并生成创建表的sql
        if(Storage.getObj().getConfig().get("sqlPut").equals("true")){
            new ScanBean().scaning();
        }
    }
}
