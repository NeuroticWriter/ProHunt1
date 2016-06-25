package com.example.lol.prohunt1;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sugam on 6/25/2016.
 */
public class MyJsonParser {
    private String jsonData;

    private Context context;

    public MyJsonParser(String jsonData){
        this.jsonData = jsonData;
    }
    public MyJsonParser(){}
    public MyJsonParser(Context context){
        this.context = context;
    }
    public MyJsonParser(Context context, String jsonData){
        this.context = context;
        this.jsonData = jsonData;
    }



    public Map parseJson(String path){
        Set<String> category = new HashSet<>();
        Map<String, Float> map = new HashMap<>();
        try {
            jsonData = loadJSONFromAsset(path);
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i<jsonArray.length();i++){
                JSONObject industry = jsonArray.getJSONObject(i);
                String name = industry.getString("category");
                category.add(name);
            }

            Iterator iterator = category.iterator();
            int i=0;
            while(iterator.hasNext()){
                String cat = (String)iterator.next();
                float openingNumber=0;
                for(int j = 0; j<jsonArray.length(); j++){
                    JSONObject openings = jsonArray.getJSONObject(j);
                    String name = openings.getString("category");
                    if(name.equals(cat)){
//                        Log.d("opening", "heyyyy");
                        openingNumber += openings.getInt("openings");
                    }
                    map.put(cat, openingNumber);
                }
                i++;
            }
            Iterator it = map.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry pair = (Map.Entry)it.next();
                //System.out.println("category: "+pair.getKey()+"  value: "+pair.getValue());
                Log.d("ta", pair.getValue().toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }



    public String loadJSONFromAsset(String path) {
        String json = null;
        try {

            InputStream is =  context.getAssets().open(path);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
