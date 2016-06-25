package com.example.lol.prohunt1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

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

public class CategoryActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private BarChart mBarChart;
    private BarDataSet barDataSet;
    MyJsonParser m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        //FragmentManager fragmentManager = getFragmentManager();
        MainDetailsFragment bargraphFragment = new MainDetailsFragment();


        m = new MyJsonParser(getApplicationContext());
        Map<String, Float> map = m.parseJson("output-final.json");

        ArrayList<BarEntry> myentries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        Iterator it = map.entrySet().iterator();
        int index = 0;
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            BarEntry b = new BarEntry((Float)pair.getValue(), index);
            myentries.add(b);
            labels.add(pair.getKey().toString());
//            Log.e("tag", pair.getKey().toString());
//            Log.d("tagg", pair.getValue().toString());
            index++;
        }
        barDataSet = new BarDataSet(myentries, "Categories");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(labels, barDataSet);
        mainLayout = (LinearLayout)findViewById(R.id.main);

        // to make horizontal bar chart, initialize graph id this way
        HorizontalBarChart chart = (HorizontalBarChart) findViewById(R.id.barchart);
//        BarChart chart = (BarChart)findViewById(R.id.barchart);
        chart.setData(data);
        chart.setDescription("# of job openings per category");
        bargraphFragment.getData(chart);






    }
}
