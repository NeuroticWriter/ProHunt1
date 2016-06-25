//package com.example.lol.prohunt1;
//
//import android.content.res.AssetManager;
//import android.util.Log;
//
////import com.google.gson.annotations.SerializedName;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.content.Context;
//import android.content.res.AssetManager;
//import android.view.Menu;
//import android.widget.ListView;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class Jobs {
//
//    @SerializedName("category")
//    private String category;
//
//  /*  public class Fields {
//        String Accounting;
//        String IT;
//        String Banking;
//
//    }
//*/
//    @SerializedName("posted_date")
//    private String posted_date;
//    @SerializedName("level")
//    private String level;
//    @SerializedName("location")
//    private String location;
//    @SerializedName("openings")
//    private String openings;
//
//    public Jobs(String category, String posted_Date, String level, String location, String openings) {
//        this.category = category;
//        this.posted_date = posted_Date;
//        this.level = level;
//        this.location = location;
//        this.openings = openings;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public String getPosted_date() {
//        return posted_date;
//    }
//
//    public void setPosted_date(String posted_date) {
//        this.posted_date = posted_date;
//    }
//
//    public String getLevel() {
//        return level;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getOpenings() {
//        return openings;
//    }
//
//    public void setOpenings(String openings) {
//        this.openings = openings;
//    }
///*
//    public String loadJSONFromAsset() {
//        String json = null;
//        try {
//            InputStream is = getActivity().getAssets().open("output.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }
///*
//    try
//
//    {
//        JSONObject obj = new JSONObject(loadJSONFromAsset());
//        JSONArray m_jArry = obj.getJSONArray("category");
//        ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
//        HashMap<String, String> m_li;
//
//        for (int i = 0; i < m_jArry.length(); i++) {
//            JSONObject jo_inside = m_jArry.getJSONObject(i);
//            Log.d("Details-->", jo_inside.getString("category"));
//            String category = jo_inside.getString("category");
//            //String url_value = jo_inside.getString("url");
//
//            //Add your values in your `ArrayList` as below:
//            m_li = new HashMap<String, String>();
//            m_li.put("category", category);
//            m_li.put("level", level);
//            m_li.put("location", location);
//            m_li.put("openings", openings);
//            //m_li.put("url", url_value);
//
//            formList.add(m_li);
//        }
//    }
//
//    catch(
//    JSONException e
//    )
//
//    {
//        e.printStackTrace();
//    }
//
//*/
//}