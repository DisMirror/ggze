package ggze.esp.entity;
import ggze.pub.ggze;
public class DataRecord {
    private String uuid;
    @ggze
    private String status;
    @ggze
    private String uri;
    @ggze
    private String spack;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getSpack() {
        return spack;
    }

    public void setSpack(String spack) {
        this.spack = spack;
    }
}
