package com.example.kirito.wechat.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by kirito on 2016.10.20.
 */

public class Http {
    private static final String agent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    public static String getDataFromUrl(String pathurl,Map<String,Object> data){
        String result = "";
        StringBuilder sb = null;
        HttpURLConnection con = null;
        try {
            pathurl = pathurl + "?" + urlEncoder(data);
            URL url = new URL(pathurl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(8000);
            con.setConnectTimeout(8000);
            con.setRequestProperty("User-agent",agent);
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            sb = new StringBuilder();
            String s = "";
            while ((s = br.readLine()) != null){
                sb.append(s);
            }
            result = sb.toString();
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (con != null){
                con.disconnect();
            }
        }
        return result;
    }

    public static String urlEncoder(Map<String,Object> data){
        StringBuffer sb = new StringBuffer();
        for (Map.Entry e : data.entrySet()
             ) {
            try {
                sb.append(e.getKey()).append("=").append(URLEncoder.encode(e.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
        return sb.toString();
    }

}
