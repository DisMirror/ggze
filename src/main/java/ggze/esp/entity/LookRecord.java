package ggze.esp.entity;
import ggze.pub.ggze;
public class LookRecord {
    private String uuid;
    @ggze
    private String status;
    @ggze
    private String spack;
    @ggze
    private String pageView;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSpack() {
        return spack;
    }

    public void setSpack(String spack) {
        this.spack = spack;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPageView() {
        return pageView;
    }

    public void setPageView(String pageView) {
        this.pageView = pageView;
    }
}
