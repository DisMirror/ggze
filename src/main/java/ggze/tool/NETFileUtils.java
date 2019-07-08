package ggze.tool;


import net.sf.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

public class NETFileUtils {
    /*
    *
    * 将网络资源下载到本地
    *
    * */
    public static void downloadFile(String urlString, String filename) throws Exception {
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        String code = con.getHeaderField("Content-Encoding");
        if ((null != code) && code.equals("gzip")) {
            GZIPInputStream gis = new GZIPInputStream(is);
            byte[] bs = new byte[1024];
            int len;
            OutputStream os = new FileOutputStream(filename);
            while ((len = gis.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            gis.close();
            os.close();
            is.close();
        } else {
            byte[] bs = new byte[1024];
            int len;
            OutputStream os = new FileOutputStream(filename);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
        }
    }
    /*public static void main(String[] args) throws Exception {
        NETFileUtils.downloadFile("http://qcloud.dpfile.com/pc/TGZItqlxbmTCfzUDWPeP0usl1G4E5SPXfmUgvHBrFJxja_PqY5pz2VCgWI-En_jKl0cm-Lf9tDMlLZpO7rb3bg.jpg", "E://img/"+ TimeUtils.getTimeStmp()+".jpg");
       *//* try {
            String[] path=
                    {"//img.meishic.com/uploadfile/300_2009_1109_1_111218010418_859f6e103eb0.jpg", "//img.meishic.com/uploadfile/300_2009_1012_1_111200133000_0996d7f39bc9.jpg", "//img.meishic.com/uploadfile/300_2009_1023_20091023083703458.jpg", "//img.meishic.com/uploadfile/300_2009_1016_20091016095213346.jpg", "//img.meishic.com/uploadfile/300_2009_1101_20091101072854875.jpg", "//img.meishic.com/uploadfile/300_2009_1101_20091101075650905.jpg", "//img.meishic.com/uploadfile/300_2009_1101_20091101082233408.jpg", "//img.meishic.com/uploadfile/300_2009_1110_20091110041626552.jpg"};

            for(int i=0;i<path.length;i++){
                String xp="https:"+path[i];

            }
        } catch (Exception e) {
            e.printStackTrace();
        }*//*
    }*/
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding","gzip, deflate");
            connection.setRequestProperty("Accept-Languag", "zh-CN,zh;q=0.9");
            connection.setRequestProperty("Cache-Control", "max-age=0");
            connection.setRequestProperty("Proxy-Connection", "keep-alive");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    //post请求json
    public static JSONObject  sendPost(String url, JSONObject json) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
