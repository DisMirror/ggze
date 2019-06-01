package loader.scanning;

import loader.Storage;
import pub.ggze;

import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class ScanBean implements Scan{
    public void scaning(){
        //获得bean的名字
        Storage.getObj().setBeanNames(this.scanBeanNames(Storage.getObj().getConfig().get("page")));
        Storage.getObj().setBeanToSql(this.scanBeanRO());
    }

    private List<String> scanBeanNames(String path){
        path=path.replace(".","/");
        path=Storage.getObj().getProjectPath()+path;
        List<String> beanNames=new LinkedList<String>();
        File files = new File(path);
        File[] filesList = files.listFiles();
        try {
            for (File file : filesList) {
                String[] fileStrings=file.getName().split("\\.");
                beanNames.add(fileStrings[0]);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return beanNames;
    }

    private List<String> scanBeanRO(){
        List<List<String>> lineSql=new LinkedList<List<String>>();
        List<String> createSql=new LinkedList<String>();
        List<String> beanNames=Storage.getObj().getBeanNames();
        for(int i = 0;i<beanNames.size();i++){
            List<String> tables=new LinkedList<String>();
            tables.add(beanNames.get(i));
            try {
                Class c = Class.forName(Storage.getObj().getConfig().get("page")+"."+beanNames.get(i));
                Field[] declaredFields = c.getDeclaredFields();
                for( Field field : declaredFields ){
                    int age=10;
                    String age1="varchar";
                    boolean fieldHasAnno = field.isAnnotationPresent(ggze.class);
                    if(fieldHasAnno){
                        ggze fieldAnno = field.getAnnotation(ggze.class);
                        age = fieldAnno.leng();
                        age1 =fieldAnno.type();
                        tables.add(field.getName()+"."+age+"."+age1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            lineSql.add(tables);
        }
        for(List<String> sqlList:lineSql){
            String sql="";
            for(int j=1;j<sqlList.size();j++){
                String[] valeAndType=sqlList.get(j).split("\\.");
                sql=sql+",\n"+valeAndType[0]+" "+valeAndType[2]+"("+valeAndType[1]+")";
            }
            String fg="-------------------创建表"+sqlList.get(0)+"----------------------------\n";
            sql=fg+"create table "+sqlList.get(0)+"(\n uuid varchar(100) primary key"+sql+" \n);";
            System.out.println(sql.toLowerCase());
            createSql.add(sql);
        }
        return createSql;
    }
}
