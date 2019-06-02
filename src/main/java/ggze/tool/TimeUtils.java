package ggze.tool;

import java.util.Date;

public class TimeUtils {
    public static String getTimeStmp(){
        return String.valueOf(new Date().getTime());
    }
    public static String getTimeFormat(){
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
        return  formatter.format(currentTime);
    }
}
