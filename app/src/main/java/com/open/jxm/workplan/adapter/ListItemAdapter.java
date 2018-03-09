package com.open.jxm.workplan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.open.jxm.workplan.R;
import com.open.jxm.workplan.bean.EventInfo;
import com.open.jxm.workplan.bean.RowItem;
import com.open.jxm.workplan.db.Plan;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by jiamao on 2018/3/8.
 */

public class ListItemAdapter extends BaseAdapter {

    private List<RowItem> planList;
    private Context mContext;

    public ListItemAdapter(List<RowItem> planList, Context mContext) {
        this.planList = planList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return planList.size();
    }

    @Override
    public Object getItem(int position) {
        return planList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final RowItem rowItem=planList.get(position);
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.single_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.rowName=convertView.findViewById(R.id.row_name);
            viewHolder.rowDesp=convertView.findViewById(R.id.row_desp);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.rowDesp.setText(rowItem.getDesp());
        viewHolder.rowName.setText(rowItem.getName());
        viewHolder.rowName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventInfo eventInfo=new EventInfo(position);
                eventInfo.setObj(rowItem);
                EventBus.getDefault().post(eventInfo);
            }
        });

        viewHolder.rowDesp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventInfo eventInfo=new EventInfo(position);
                eventInfo.setObj(rowItem);
                EventBus.getDefault().post(eventInfo);
            }
        });

        return convertView;
    }


    static final class ViewHolder {

        public TextView rowName;
        public TextView rowDesp;

    }
}
