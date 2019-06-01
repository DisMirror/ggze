package loader.Printing;

import loader.Storage;
import tool.FileUtils;
import tool.TimeUtils;

import java.util.List;

public class PrintSql implements Print{

    @Override
    public void printful() throws Exception {
        StringBuilder sql=new StringBuilder();
        List<String> beanToSql=Storage.getObj().getBeanToSql();
        for(int i=0;i<beanToSql.size();i++){
            sql.append(beanToSql.get(i));
        }
        new FileUtils(Storage.getObj().getProjectPath(), TimeUtils.getTimeStmp()+".sql").OutPut(sql.toString());
    }
}
