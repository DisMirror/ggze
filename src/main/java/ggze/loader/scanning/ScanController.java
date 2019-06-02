package ggze.loader.scanning;

public class ScanController {
    public ScanController(){
        //装载配置文件
        new ScanConfig().scaning();
        //扫描bean并生成创建表的sql
        new ScanBean().scaning();
    }
}
