package com.example.kirito.wechat.support;

import com.example.kirito.wechat.entity.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirito on 2016.10.20.
 */

public class JsonHelper {

    public static List<Item> toJsonObject(String data){
        List<Item> items = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray list = result.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                Item item = new Item();
                JSONObject obj = list.getJSONObject(i);
                item.setImg(obj.getString("firstImg"));
                item.setSource(obj.getString("source"));
                item.setTitle(obj.getString("title"));
                item.setUrl(obj.getString("url"));
                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }
}
