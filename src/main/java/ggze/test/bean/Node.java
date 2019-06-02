package ggze.test.bean;

import ggze.pub.ggze;

public class Node {
    private String uuid;
    @ggze
    private String viewName;
    @ggze
    private String spackName;
    @ggze
    private String spackFuValue;
    @ggze
    private String spackValue;
    @ggze
    private String openUlr;
    @ggze
    private String icon;
    @ggze
    private String createTime;
    @ggze
    private String methodTime;
    @ggze
    private String belongMethodId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getSpackName() {
        return spackName;
    }

    public void setSpackName(String spackName) {
        this.spackName = spackName;
    }

    public String getSpackFuValue() {
        return spackFuValue;
    }

    public void setSpackFuValue(String spackFuValue) {
        this.spackFuValue = spackFuValue;
    }

    public String getSpackValue() {
        return spackValue;
    }

    public void setSpackValue(String spackValue) {
        this.spackValue = spackValue;
    }

    public String getOpenUlr() {
        return openUlr;
    }

    public void setOpenUlr(String openUlr) {
        this.openUlr = openUlr;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMethodTime() {
        return methodTime;
    }

    public void setMethodTime(String methodTime) {
        this.methodTime = methodTime;
    }

    public String getBelongMethodId() {
        return belongMethodId;
    }

    public void setBelongMethodId(String belongMethodId) {
        this.belongMethodId = belongMethodId;
    }
}
