package asus.com.bwie.day1227lx.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import asus.com.bwie.day1227lx.R;
import asus.com.bwie.day1227lx.bean.ShopBean;

public class LinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ShopBean.DataBean> mlist;
    private Context context;
    private boolean isLinear;

    public LinearAdapter(Context context, boolean isLinear) {
        this.context = context;
        this.isLinear=isLinear;
        mlist=new ArrayList<>();
    }

    public int getPid(int position){
        return mlist.get(position).getPid();
    }

    public void setMlist(List<ShopBean.DataBean> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }
    public void addMlist(List<ShopBean.DataBean> mlist) {
        this.mlist.addAll(mlist);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(isLinear){
            View view=LayoutInflater.from(context).inflate(R.layout.linear_layout,viewGroup,false);
            return new ViewHolder(view);
        }else{
            View view=LayoutInflater.from(context).inflate(R.layout.grid_layout,viewGroup,false);
            return new ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder holder= (ViewHolder) viewHolder;
        ShopBean.DataBean bean=mlist.get(i);
        String[] split = bean.getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.imageView);
        holder.title.setText(bean.getTitle());
        holder.price.setText("¥"+bean.getPrice());
        holder.salenum.setText("已售"+bean.getSalenum()+"件");
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listenter!=null){
                    listenter.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView price;
        private TextView salenum;
        private RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.linear_image);
            title=itemView.findViewById(R.id.linear_title);
            price=itemView.findViewById(R.id.linear_bargainPrice);
            salenum=itemView.findViewById(R.id.linear_salenum);
            relativeLayout=itemView.findViewById(R.id.linaer);
            TextPaint paint = title.getPaint();
            paint.setFakeBoldText(true);
        }
    }

    public ClickListenter listenter;

    public void setOnClickListenter(ClickListenter clickListenter){
        listenter=clickListenter;
    }

    public interface ClickListenter{
        void onClick(int position);
    }
}
