package asus.com.bwie.day1227lx.bean;

public class CZBean {

    String id;
    Object object;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public CZBean(String id, Object object) {
        this.id = id;
        this.object = object;
    }
}
