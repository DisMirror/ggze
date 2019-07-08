package ggze.esp.entity;
import ggze.pub.ggze;
public class Api {
    private String uuid;
    //客户端IP地址
    @ggze
    private String a_ip;
    //是否禁用 0:允许；1：禁用；
    @ggze
    private String a_is;
    @ggze
    private String createTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getA_ip() {
        return a_ip;
    }

    public void setA_ip(String a_ip) {
        this.a_ip = a_ip;
    }

    public String getA_is() {
        return a_is;
    }

    public void setA_is(String a_is) {
        this.a_is = a_is;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
