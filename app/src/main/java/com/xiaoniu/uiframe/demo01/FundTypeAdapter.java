package com.xiaoniu.uiframe.demo01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaoniu.uiframe.R;

import java.util.List;

/**
 * Created by xn068074 on 2017/6/26.
 */

public class FundTypeAdapter extends BaseAdapter {
    private Context context;

    public FundTypeAdapter(Context context) {
        this.context = context;
    }

    List<String> datas;
    @Override
    public int getCount() {
        return datas == null ?  0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder;
        if(convertView == null){
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.fund_type_item,null,false);
            mViewHolder.textView =(TextView) convertView.findViewById(R.id.type);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder)convertView.getTag();
        }
        String content = datas.get(position);
        mViewHolder.textView.setText(content);
        return convertView;
    }

    class ViewHolder{
        TextView textView;
    }
    public void setDatas(List<String> datas) {
        this.datas = datas;
    }
}
