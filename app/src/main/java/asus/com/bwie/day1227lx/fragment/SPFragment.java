package asus.com.bwie.day1227lx.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asus.com.bwie.day1227lx.Apis;
import asus.com.bwie.day1227lx.R;
import asus.com.bwie.day1227lx.bean.CZBean;
import asus.com.bwie.day1227lx.bean.ShopBean;
import asus.com.bwie.day1227lx.bean.Shops;
import asus.com.bwie.day1227lx.presenter.IpresenterImpl;
import asus.com.bwie.day1227lx.view.IView;


public class SPFragment extends Fragment implements IView {

    private IpresenterImpl ipresenter;
    private Banner banner;
    private TextView title;
    private TextView price;
    private TextView barginprice;
    private String pids;
    private Button jg;
    private Button mc;
    private Shops.DataBean data1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sp_layout, container, false);
        banner = view.findViewById(R.id.banner);
        title = view.findViewById(R.id.title);
        price = view.findViewById(R.id.price);
        barginprice = view.findViewById(R.id.barginprice);
        ipresenter = new IpresenterImpl(this);
        Intent intent=getActivity().getIntent();
        String pids = intent.getStringExtra("pid");
        mc = view.findViewById(R.id.mc);
        jg = view.findViewById(R.id.jg);


        mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().register(SPFragment.this);
                CZBean object=new CZBean(data1.getTitle(),"title");
                EventBus.getDefault().postSticky(object);
                onStop();
            }
        });
        jg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().register(SPFragment.this);
                CZBean object=new CZBean(data1.getPrice()+"","price");
                EventBus.getDefault().postSticky(object);
                onStop();

            }
        });
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {


            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getActivity()).load(path).into(imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView=new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        Map<String,String> map=new HashMap<>();
        map.put("pid",pids);
        ipresenter.startRequest(Apis.spPath,map,Shops.class);
        return view;
        }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void jianting(CZBean czBean) {

    }


    @Override
    public void OnSuccessData(Object data) {
        List<String> list=new ArrayList<>();
        Shops shops= (Shops) data;
        data1 = shops.getData();
        String[] split = shops.getData().getImages().split("\\|");
        for (int i=0;i<split.length;i++){
            list.add(split[i]);
        }
        banner.setImages(list);
        banner.start();
        barginprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        price.setText("¥"+shops.getData().getPrice()+"");
        title.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        title.setText(shops.getData().getTitle());
        barginprice.setText("¥"+shops.getData().getBargainPrice()+"");
    }

    @Override
    public void OnFailData(Exception e) {

    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
