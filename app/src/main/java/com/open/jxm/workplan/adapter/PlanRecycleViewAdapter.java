package com.open.jxm.workplan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.open.jxm.workplan.R;
import com.open.jxm.workplan.bean.RowItem;
import com.open.jxm.workplan.db.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamao on 2018/3/8.
 */

public class PlanRecycleViewAdapter extends RecyclerView.Adapter{

    private List<Plan>planList;
    private Context mContext;

    public PlanRecycleViewAdapter(List<Plan> planList, Context mContext) {
        this.planList = planList;
        this.mContext = mContext;
    }

    public class MViewHolder extends RecyclerView.ViewHolder{

        public TextView taskName;
        public ListView taskList;

        public MViewHolder(View itemView) {
            super(itemView);
            taskName=itemView.findViewById(R.id.task_name);
            taskList=itemView.findViewById(R.id.task_list);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println(" --onCreateViewHolder ");
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item,parent,false);
        MViewHolder viewHolder=new MViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        System.out.println(" --onBindViewHolder ");
        Plan plandata=planList.get(position);
        MViewHolder viewHolder=(MViewHolder) holder;

        viewHolder.taskName.setText(plandata.getId()+"");
        ListItemAdapter itemAdapter=new ListItemAdapter(getItemData(plandata),mContext);
        viewHolder.taskList.setAdapter(itemAdapter);



    }

    @Override
    public int getItemCount() {
        return planList.size();
    }

    private List<RowItem> getItemData(Plan plan){

        List<RowItem> rowItems=new ArrayList<>();

        RowItem item=new RowItem();
        item.setName("任务名称：");
        item.setDesp(plan.getName());
        rowItems.add(item);

        RowItem item1=new RowItem();
        item1.setName("任务内容：");
        item1.setDesp(plan.getContent());
        rowItems.add(item1);

        RowItem item2=new RowItem();
        item2.setName("开始时间：");
        item2.setDesp(plan.getStart());
        rowItems.add(item2);

        RowItem item3=new RowItem();
        item3.setName("结束时间：");
        item3.setDesp(plan.getEnd());
        rowItems.add(item3);

        RowItem item4=new RowItem();
        item4.setName("完成状态：");
        item4.setDesp(plan.getProgress());
        rowItems.add(item4);

        RowItem item5=new RowItem();
        item5.setName("下一阶段:");
        item5.setDesp(plan.getNext());
        item5.setLastCardID(plan.getLastCardID());
        item5.setNextCardID(plan.getNextCardID());
        rowItems.add(item5);

        RowItem item6=new RowItem();
        item6.setName("备注：");
        item6.setDesp(plan.getExtra());
        rowItems.add(item6);

        return rowItems;
    }
}
