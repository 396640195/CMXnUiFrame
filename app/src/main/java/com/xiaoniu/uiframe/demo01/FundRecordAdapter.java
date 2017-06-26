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

public class FundRecordAdapter extends BaseAdapter {
    private Context context;

    public FundRecordAdapter(Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fund_item,null,false);
            mViewHolder.textView =(TextView) convertView.findViewById(R.id.text);
            mViewHolder.axisView = (AxisView)convertView.findViewById(R.id.axis);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder)convertView.getTag();
        }
        String content = datas.get(position);
        mViewHolder.textView.setText(content);

        if(position == 0){
            mViewHolder.axisView.setStart(true);
        }else if(position == datas.size()-1){
            mViewHolder.axisView.setEnd(true);
        }

        return convertView;
    }

    class ViewHolder{
        AxisView axisView;
        TextView textView;
    }
    public void setDatas(List<String> datas) {
        this.datas = datas;
    }
}
