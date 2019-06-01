package loader.Printing;

import loader.Storage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tool.FileUtils;

public interface PrintDao extends Print{
    @Override
    void printful() throws Exception;
    void printHead();
    void prntSystem(String beanName) throws Exception;
    void printExtension(String beanName) throws Exception;
    void printing(String beanName) throws  Exception;
}
