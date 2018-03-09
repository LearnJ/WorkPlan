package com.open.jxm.workplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.open.jxm.workplan.db.Plan;

public class CardAddActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText nameET,idNumET,contentET,startTimeET,endTimeET,nextET,extraET,progressET;
    private Button sumbitBTN;
    private Button addNewBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_card);
        initView();
    }


    void initView(){

        addNewBtn=findViewById(R.id.add_new_btn);
        nameET=findViewById(R.id.card_name);
        idNumET=findViewById(R.id.card_num);
        contentET=findViewById(R.id.card_content);
        startTimeET=findViewById(R.id.start_time);
        endTimeET=findViewById(R.id.end_time);
        nextET=findViewById(R.id.next_task);
        extraET=findViewById(R.id.extra_msg);
        progressET=findViewById(R.id.progress);

        sumbitBTN=findViewById(R.id.submit);
        sumbitBTN.setOnClickListener(this);
        addNewBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:

                System.out.println("jm---click back");
                Intent intent=new Intent();
                intent.putExtra("new_card",saveAndGetInputData());
                setResult(RESULT_OK,intent);
                finish();
                System.out.println("jm---click back2");
                break;
            case R.id.add_new_btn:
                resetViewAndSaveData();
                break;

        }
    }

    Plan saveAndGetInputData(){
        System.out.println("jm======"+nextET.getText().toString());
        Plan plan =new Plan();
        plan.setEnd(endTimeET.getText().toString());
        plan.setStart(startTimeET.getText().toString());
        plan.setNext(nextET.getText().toString());
        plan.setProgress(progressET.getText().toString());
        plan.setExtra(extraET.getText().toString());
        if (idNumET.getText().toString()==null||"".equals(idNumET.getText().toString())) {
            plan.setId(idNumET.getText().toString());
        }
        plan.setContent(contentET.getText().toString());
        plan.setName(nameET.getText().toString());
        plan.setNextCardID(nextET.getText().toString());
        plan.save();
        return plan;
    }
    void resetViewAndSaveData(){

        if (nextET.getText().toString()==null||"".equals(nextET.getText().toString())){
            Toast.makeText(this, "请先设置好下一任务的ID再点击添加。", Toast.LENGTH_SHORT).show();
            return;
        }
        Plan plan =new Plan();
        plan.setEnd(endTimeET.getText().toString());
        plan.setStart(startTimeET.getText().toString());
        plan.setNext(nextET.getText().toString());
        plan.setProgress(progressET.getText().toString());
        plan.setExtra(extraET.getText().toString());
        if (idNumET.getText().toString()==null||"".equals(idNumET.getText().toString())) {
            plan.setId(idNumET.getText().toString());
        }
        plan.setContent(contentET.getText().toString());
        plan.setName(nameET.getText().toString());
        plan.setNextCardID(nextET.getText().toString());
        plan.save();

        idNumET.setText(nextET.getText().toString());
        idNumET.setEnabled(false);
        endTimeET.setText("");
        startTimeET.setText("");
        nextET.setText("");
        progressET.setText("");
        extraET.setText("");
        contentET.setText("");
        nameET.setText("");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                setResult(RESULT_OK);
                finish();
                break;

        }

        return super.onKeyDown(keyCode, event);
    }
}
