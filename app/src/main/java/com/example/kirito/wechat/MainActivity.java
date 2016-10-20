package com.example.kirito.wechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kirito.wechat.adapter.ListAdapter;
import com.example.kirito.wechat.entity.Item;
import com.example.kirito.wechat.support.LoadData;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.lv)
    ListView lv;

    private static final String URL = "http://v.juhe.cn/weixin/query";
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Fresco.initialize(this);
        LoadData load = new LoadData();
        load.execute(URL);
        load.setListener(new LoadData.listener() {
            @Override
            public void setItems(List<Item> items) {
                if (items != null){
                    adapter = new ListAdapter(MainActivity.this,items);
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Item item = (Item) parent.getItemAtPosition(position);
                            String url = item.getUrl();
                            Intent intent = new Intent(MainActivity.this,ShowDetail.class);
                            intent.putExtra("url",url);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

    }
}
