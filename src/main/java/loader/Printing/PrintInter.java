package loader.Printing;

import loader.Storage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tool.FileUtils;

public class PrintInter implements PrintDao {
    public StringBuilder interText = new StringBuilder();

    @Override
    public void printful() throws Exception {
        for (int i = 0; i < Storage.getObj().getBeanNames().size(); i++) {
            this.printHead();
            this.prntSystem(Storage.getObj().getBeanNames().get(i));
            this.printing(Storage.getObj().getBeanNames().get(i));
        }
    }

    public void printHead() {
        String interPath = Storage.getObj().getConfig().get("page");
        interPath = interPath.substring(0, interPath.lastIndexOf(".")) + ".inter";
        //package
        interText.append("/*************创建接口***************/\n");
        interText.append("package " + interPath + ";\n");
        interText.append("import org.apache.ibatis.annotations.Param;\n");
        interText.append("import java.util.List;\n");

    }

    public void prntSystem(String beanName) throws Exception {
        interText.append("import " + Storage.getObj().getConfig().get("page") + "." + beanName + ";\n");
        //接口名
        interText.append("public interface " + beanName + "Dao{\n");
        //五大基础方法
        interText.append("  int add(@Param(\"name0\")String n,@Param(\"value0\")String v);\n");
        interText.append("  int delete(@Param(\"name1\")String n,@Param(\"value1\")String v);\n");
        interText.append("  int update(@Param(\"name2\")String n,@Param(\"value2\")String v,@Param(\"name4\")String va,@Param(\"value4\")String vas);\n");
        interText.append("  List<" + beanName + "> allSelect();\n");
        interText.append("  " + beanName + " isSelect(@Param(\"name3\")String n,@Param(\"value3\")String v);\n");
        if (Storage.getObj().getConfig().get("daoConfigSwitch").equals("true")) {
            this.printExtension(beanName);
        } else {
            interText.append("}");
        }
    }

    public void printExtension(String beanName) throws Exception {
        JSONArray extension = JSONArray.fromObject(new FileUtils(Storage.getObj().getProjectPath(), "extension.json").OutText());
        for (int i = 0; i < extension.size(); i++) {
            JSONObject exOBJ = extension.getJSONObject(i);
            exOBJ = exOBJ.getJSONObject("inter");
            StringBuilder pub = new StringBuilder();
            pub.append(" " + exOBJ.get("type").toString().replace("?", beanName));
            pub.append(" " + exOBJ.get("name").toString());
            JSONArray parameter = exOBJ.getJSONArray("parameter");
            for (int j = 0; j < parameter.size(); j++) {
                JSONObject parOBJ = parameter.getJSONObject(j);
                if (j == 0) {
                    pub.append("(");
                }
                pub.append("@Param(\"" + parOBJ.get("parameterName") + "\")" + parOBJ.get("parameterType") + " " + parOBJ.get("parameterName") + ",");
            }
            String putPub = pub.toString().substring(0, pub.toString().length() - 1);
            putPub = putPub + ");\n";
            interText.append(putPub);
        }
        interText.append("}");
    }

    public void printing(String beanName) throws Exception {
        System.out.println(interText.toString());
        String interPath = Storage.getObj().getConfig().get("page");
        interPath = interPath.substring(0, interPath.lastIndexOf(".")) + "/inter";
        interPath = interPath.replace(".", "/");
        new FileUtils(Storage.getObj().getConfig().get("filePath") + "/" + interPath, beanName + "Dao.java").OutPut(interText.toString());
    }
}
