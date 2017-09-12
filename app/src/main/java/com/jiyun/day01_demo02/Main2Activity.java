package com.jiyun.day01_demo02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView lv;
    private String string;
    public OnClick onClick;
    private List<StudentBean.DataBean> data;

    public interface OnClick{
        void suibian(List<StudentBean.DataBean> data);
    };

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        setSupportActionBar(toolbar);
        getUrl();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lv = (ListView) findViewById(R.id.lv);
    }

    public void getUrl() {
        Utils.getInstance().sendGet("http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=17", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        StudentBean studentBean = gson.fromJson(string, StudentBean.class);
                        data = studentBean.getData();
                        SubAdapter subAdapter = new SubAdapter(data,Main2Activity.this);
                        lv.setAdapter(subAdapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                onClick.suibian(data);
                                startActivity(new Intent(Main2Activity.this,Main3Activity.class));
                            }
                        });
                    }
                });
            }
        });
    }
}
