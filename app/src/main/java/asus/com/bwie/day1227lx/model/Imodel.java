package asus.com.bwie.day1227lx.model;

import java.util.Map;

import asus.com.bwie.day1227lx.callback.MyCallBack;

public interface Imodel {

    void startRequestData(String urlData, Map<String, String> map, Class clazz, MyCallBack myCallBack);

}
