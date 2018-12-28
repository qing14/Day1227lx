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


public class PLFragment extends Fragment {

    public TextView shopPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pl_layout, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shopPrice = view.findViewById(R.id.shopprice);
        EventBus.getDefault().register(this);

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)

    public void onCZBean(CZBean bean){
        if (bean.getObject().equals("price")){
            shopPrice.setText(bean.getId()+"");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
