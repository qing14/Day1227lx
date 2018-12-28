package asus.com.bwie.day1227lx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import asus.com.bwie.day1227lx.R;

import asus.com.bwie.day1227lx.bean.CZBean;




public class XQFragment extends Fragment {

    public TextView shopname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xq_layout, container, false);
        shopname = view.findViewById(R.id.shopname);
        ev();
        return view;
    }
    public void ev(){
        EventBus.getDefault().register(XQFragment.this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onCZBean(CZBean bean){
        if (bean.getObject().equals("title")){
            shopname.setText(bean.getId()+"");
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
