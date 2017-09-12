package com.jiyun.day01_demo02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lenovo on 2017/9/11.
 */

class SubAdapter extends BaseAdapter{
    private List<StudentBean.DataBean> data;
    private Context context;

    public SubAdapter(List<StudentBean.DataBean> data, Main2Activity main2Activity) {

        this.data=data;
        this.context=main2Activity;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
             holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.xiaobuju, null);
            holder. img = convertView.findViewById(R.id.x_img);
            holder. price = convertView.findViewById(R.id.x_price);
            holder. content = convertView.findViewById(R.id.x_content);
            convertView.setTag(holder);
        }else{
             holder = (ViewHolder) convertView.getTag();
        }
        holder.content.setText(data.get(position).getGoods_name());
        holder.price.setText(data.get(position).getMarket_price()+"");
        Glide.with(context).load(data.get(position).getGoods_img()).into(holder.img);
        return convertView;
    }
    class ViewHolder{
        ImageView img;
        TextView price;
        TextView content;
    }
}
