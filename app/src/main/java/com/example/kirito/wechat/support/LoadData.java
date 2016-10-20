package com.example.kirito.wechat.support;

import android.os.AsyncTask;

import com.example.kirito.wechat.entity.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kirito on 2016.10.20.
 */

public class LoadData extends AsyncTask<String,Void,String> {
    private static final String KEY = "40e8c3f83754f100cbffd5dfee2f1ecf";
    private listener listener;

    public LoadData(LoadData.listener listener) {
        this.listener = listener;
    }

    public LoadData() {
    }

    @Override
    protected String doInBackground(String... params) {
        Map<String,Object> map = new HashMap<>();
        map.put("key",KEY);
        map.put("ps",100);
        return Http.getDataFromUrl(params[0],map);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        List<Item> items = JsonHelper.toJsonObject(s);
        if (listener != null && items != null){
            listener.setItems(items);
        }
    }

    public void setListener(listener l){
        this.listener = l;
    }

    public interface listener{
        void setItems(List<Item> items);
    }
}
