package ggze.loader.Printing;

import ggze.loader.Storage;

public class PrintController {
    public PrintController() throws Exception {
        if(Storage.getObj().getConfig().get("sqlSwitch").equals("true")){
            //输出为sql文件
            new PrintSql().printful();
        }
        //输出dao接口
        new PrintInter().printful();
        //输出Mapper文件
        new PrintMapper().printful();
        //输出controller.java
        new PrintContro().printful();
    }
}
