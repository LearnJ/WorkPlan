package com.open.jxm.workplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.open.jxm.workplan.adapter.PlanRecycleViewAdapter;
import com.open.jxm.workplan.bean.EventInfo;
import com.open.jxm.workplan.bean.RowItem;
import com.open.jxm.workplan.db.Plan;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addBtn,deleteBtn,updateBtn,searchBtn;
    private RecyclerView recyclerView;
    private PlanRecycleViewAdapter recycleViewAdapter;
    private List<Plan>planDatas;
    private final int ADD_NEW_TASK_CARD=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LitePal.getDatabase();
        initView();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(EventInfo info){
        System.out.println("jm----onEventMain= "+info.getId());
        switch (info.getId()){

            case 0://任务名称

                break;
            case 1:  //任务内容

                break;
            case 2: //开始时间

                break;
            case 3: //结束时间

                break;
            case 4: //进展状况

                break;
            case 5: //下一阶段
                RowItem item = (RowItem) info.getObj();
                System.out.println("jm----item.getNextCardID()"+item.getNextCardID());
                List<Plan>temp = DataSupport.where("id=?",item.getNextCardID()).find(Plan.class);
                if (temp.size()==0){
                    Intent intent =new Intent(this,CardAddActivity.class);
                    startActivityForResult(intent,ADD_NEW_TASK_CARD);
                }else {
                    Plan p=temp.get(0);
                    planDatas.add(p);
                    recycleViewAdapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(planDatas.size() - 1);
                }
                break;
            case 6: //备注

                break;
        }
    }

    void initView(){

        planDatas=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        //initTestDatas();
        //planDatas= DataSupport.findAll(Plan.class);
        recycleViewAdapter=new PlanRecycleViewAdapter(planDatas,this);
        addBtn=findViewById(R.id.add);
        deleteBtn=findViewById(R.id.delete);
        updateBtn=findViewById(R.id.update);
        searchBtn=findViewById(R.id.search);
        addBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleViewAdapter);

    }

    void initTestDatas(){

        for (int i=0;i<10;i++){
            Plan plan=new Plan();
            plan.setContent("饿了么，去吃饭");
            plan.setName("吃饭安排");
            plan.setId(i+"");
            plan.setExtra("想吃什么，在次说明");
            plan.setProgress("未开始");
            plan.setNext("吃完看电影");
            plan.setStart("2018-3-8:19");
            plan.setEnd("2018-3-10:20");
            plan.save();
            //planDatas.add(plan);
        }

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.add:
                Intent intent =new Intent(this,CardAddActivity.class);
                startActivityForResult(intent,ADD_NEW_TASK_CARD);
                break;
            case R.id.delete:
                DataSupport.deleteAll(Plan.class);
                break;
            case R.id.update:

                break;
            case R.id.search:
                List<Plan>plans =DataSupport.findAll(Plan.class);
                for (Plan plan : plans){
                    planDatas.add(plan);
                }
                System.out.println("jm---总计有： "+ planDatas.size());
                recycleViewAdapter.notifyDataSetChanged();
                break;


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case ADD_NEW_TASK_CARD:
                if (data!=null) {
                    planDatas.add((Plan) data.getSerializableExtra("new_card"));
                    recycleViewAdapter.notifyDataSetChanged();
                }
                break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
