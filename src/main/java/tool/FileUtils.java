package tool;

import java.io.*;

public class FileUtils {
    public String path;
    public FileUtils(String paths,String name) throws Exception {
        this.path=paths+"/"+name;
    }
    public void OutPut(String value) throws Exception{
        FileOutputStream in = new FileOutputStream(new File(path));
        OutputStreamWriter out = new OutputStreamWriter(in, "utf-8");
        PrintWriter pw=new PrintWriter(out);
        StringBuilder tes=new StringBuilder(value);
        pw.println(tes.toString());
        pw.flush();
        pw.close();
    }
    public String OutText() throws Exception {
        FileInputStream reads = new FileInputStream(new File(path ));
        StringBuilder txt = new StringBuilder();
        InputStreamReader read = new InputStreamReader(reads, "UTF-8");//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            txt.append(lineTxt);
        }
        read.close();
        return txt.toString();
    }
}
