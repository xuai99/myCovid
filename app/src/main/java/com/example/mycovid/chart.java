package com.example.mycovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class chart extends AppCompatActivity {
    TextView back,totalDeath,totalCases,totalRecovered,Countrys;
    String recoverCase,ActiveCase,DeathCase,Countries;
    Double recoverCases,ActiveCases,DeathCases,answerR,answerD;
    private PieDataSet pieDataSet;
    private PieChart myPiechart;
    final int [] colorArray =new int[]{Color.RED,Color.BLUE,Color.GRAY};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        back = findViewById(R.id.backTostats);
        myPiechart = findViewById(R.id.myPieChart);
        totalDeath = findViewById(R.id.showTotalDeath);
        totalCases = findViewById(R.id.showTotalCases);
        totalRecovered = findViewById(R.id.ShowRecoverd);
        Countrys = findViewById(R.id.showCountry);
        Bundle bundle =getIntent().getExtras();
        if(bundle.getString("recoverCase")!=null){
            recoverCase = bundle.getString("recoverCase");
            ActiveCase = bundle.getString("ActiveCase");
            DeathCase = bundle.getString("Deathcase");
            Countries = bundle.getString("Country");
        }
         recoverCases = Double.parseDouble(recoverCase);
         ActiveCases = Double.parseDouble(ActiveCase);
         DeathCases = Double.parseDouble(DeathCase);

          answerR = recoverCases*100/ActiveCases;
          answerD = 100-answerR;

         Countrys.setText(Countries);
         totalCases.setText("Total Cases: "+ActiveCase);
         totalDeath.setText("Total Death-: "+DeathCase);
        totalRecovered.setText("Recovered: "+recoverCase);

         int iRecoverCases =(int) Math.round(answerR);
        int iDeathCase =(int) Math.round(answerD);

        myChart(iRecoverCases,iDeathCase);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(chart.this,globalStats.class);
                startActivity(i);
            }
        });


    }
    public void myChart(int recoverCases,int DeathCases){
        List<PieEntry> dataValue= new ArrayList<>();

        dataValue.add(new PieEntry(recoverCases,"% Recovered"));
        dataValue.add(new PieEntry(DeathCases,"% Death"));

        pieDataSet = new PieDataSet(dataValue," value in %");
        pieDataSet.setColors(colorArray);
        pieDataSet.setValueTextColor(Color.WHITE);
        //show data into as a pie chart format
        PieData pieData = new PieData(pieDataSet);
        myPiechart.setData(pieData);
        myPiechart.getDescription().setEnabled(false);
        myPiechart.getLegend().setEnabled(true);
        myPiechart.invalidate();

    }
}
