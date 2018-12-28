package asus.com.bwie.day1227lx.model;

import java.util.Map;


import asus.com.bwie.day1227lx.callback.MyCallBack;
import asus.com.bwie.day1227lx.okhttp.OkhttpUtils;

public class ImodelImpl implements Imodel {
    @Override
    public void startRequestData(String urlData, final Map<String, String> map, Class clazz, final MyCallBack myCallBack) {
        OkhttpUtils.getOkhttpUtils().getEneuque(urlData, map, clazz, new MyCallBack() {
            @Override
            public void OnSuccess(Object data) {
                myCallBack.OnSuccess(data);
            }

            @Override
            public void OnFail(Exception e) {
                myCallBack.OnFail(e);
            }
        });
    }
}

