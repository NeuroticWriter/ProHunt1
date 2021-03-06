package com.example.lol.prohunt1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.example.lol.prohunt1.R;
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
//import com.github.mikephil.charting.utils.Highlight;
//import com.github.mikephil.charting.utils.PercentFormatter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    private LinearLayout mainLayout;
    private PieChart mChart;
    private String jsonResponse;
    public MyJsonParser jsonParser;


    // we're going to display pie chart for Job martket shares
    private float[] yData = { 5, 10, 15, 30, 40 };
    private String[] xData = { "Art", "Mechanical", "IT", "Medical", "Bank" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (LinearLayout) findViewById(R.id.main);
        Set<String> industry = new HashSet<>();
        Map<String, Float> map = new HashMap<>();
        jsonParser = new MyJsonParser(this.getApplicationContext());

        // parse json data  CONTEXT
        try {
            String jsonData = jsonParser.loadJSONFromAsset("output-final.json");
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i<jsonArray.length();i++){
                JSONObject industryObj = jsonArray.getJSONObject(i);
                String name = industryObj.getString("industry");
                industry.add(name);
            }

            Iterator iterator = industry.iterator();
            int i=0;
            while(iterator.hasNext()){
                String ind = (String)iterator.next();
                float openingNumber=0;
                for(int j = 0; j<jsonArray.length(); j++){
                    JSONObject openings = jsonArray.getJSONObject(j);
                    String name = openings.getString("industry");
                    if(name.equals(ind)){
//                        Log.d("opening", "heyyyy");
                        openingNumber += openings.getInt("openings");
                    }
                    map.put(ind, openingNumber);
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


        mChart = new PieChart(this);
        // add pie chart to main layout
        mainLayout.addView(mChart);
        mainLayout.setBackgroundColor(Color.parseColor("#55656C"));

        // configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("Market Share");

        //layout piechart

        ViewGroup.LayoutParams params = mChart.getLayoutParams();
        params.height= ViewGroup.LayoutParams.MATCH_PARENT;
        params.width=ViewGroup.LayoutParams.MATCH_PARENT;

        // enable hole and configure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.BLACK);

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

        // customize legends
        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
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
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
    }

}
