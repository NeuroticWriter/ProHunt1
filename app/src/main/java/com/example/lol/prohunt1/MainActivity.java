package com.example.lol.prohunt1;

/*
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import layout.MainDetailsFragment;
*/

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MainActivity extends AppCompatActivity {

//    private LinearLayout mainLayout;
//    private BarChart mBarChart;
//    private BarDataSet barDataSet;
    private RelativeLayout mainLayout;
    private PieChart mChart;
    private String category = null;
    private String jstring;
    private JsonElement elem;
    //ViewGroup params;
    //martket shares
    private float[] yData = {5, 10, 15, 30, 40};
    private String[] xData = {"IT", "Mechanical", "Art", "Medical", "Bank"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        mChart = new PieChart(this);
        // add pie chart to main layout
        mainLayout.addView(mChart);
        mainLayout.setBackgroundColor(Color.parseColor("#55656C"));

        // configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("Market Share");

        //layout of the chart
        ViewGroup.LayoutParams params = mChart.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;


        // enable hole and configure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.BLUE);
        //mChart.setHoleColor(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        // set a chart value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry entry, int dataSetIndex, Highlight highlight) {
                // display msg when value selected
                if (entry == null)
                    return;

                Toast.makeText(MainActivity.this,
                        xData[entry.getXIndex()] + " = " + entry.getVal() + "%", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        // add data
        addData();

JsonElement elem = new JsonElement() {
    @Override
    public BigDecimal getAsBigDecimal() {
        return super.getAsBigDecimal();
    }
}
        try {
            elem = new JsonParser().parse(loadJSONFromAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().create();
            Jobs o = gson.fromJson(elem, Jobs.class);
            System.out.println(o.getCategory());
        //List<Jobs> lcs = (List<Jobs>) new Gson().fromJson( jstring , Jobs.class);

        Type collectionType = new TypeToken<Collection<Jobs>>(){}.getType();
        List<Jobs> lcs = (List<Jobs>) new Gson().fromJson(jstring , Jobs.class);
        Collection<Jobs> enums = gson.fromJson("output.json", collectionType);


        // customize legends
        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    public String loadJSONFromAsset() throws JSONException {
        String json = null;
        try {

            InputStream is = getAssets().open("output.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();


            int id;

            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                id = row.getInt("id");
                category = row.getString("category");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    private void addData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new Entry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Market Share");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        //data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
    }

    public void gotoActivity(View v) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("industry", "industry");
        startActivity(intent);
    }

}
