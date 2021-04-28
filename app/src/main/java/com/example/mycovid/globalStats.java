package com.example.mycovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class globalStats extends AppCompatActivity {
    TextView tvCase, tvRecover, tvCritical, tvActive, tvTodayCases, tvCountry,backTohome;
    String DeathRate,activeRate,recoverRate;
    String url,country="china";
    EditText myCountry;
    ImageView mybutton;
    FloatingActionButton myChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_stats);
        myCountry = findViewById(R.id.EnterMycountry);
        tvCountry = findViewById(R.id.tvCountry);
        tvCase = findViewById(R.id.tvCases);
        tvRecover = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        backTohome = findViewById(R.id.backTohome);
        mybutton = findViewById(R.id.myButton);
        myChart = findViewById(R.id.showChart);

        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = myCountry.getText().toString().trim();
                fetchData(country);
            }
        });

        fetchData(country);
        backTohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(globalStats.this,MainActivity.class);
                startActivity(i);
            }
        });
        myChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(globalStats.this,chart.class);
                i.putExtra("recoverCase",String.valueOf(recoverRate));
                i.putExtra("ActiveCase",String.valueOf(activeRate));
                i.putExtra("Deathcase",String.valueOf(DeathRate));
                i.putExtra("Country",String.valueOf(country));
                startActivity(i);
                Toast.makeText(globalStats.this, "Active case"+activeRate+" "+DeathRate+" "+recoverRate, Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void fetchData(String name){

        url = "https://disease.sh/v3/covid-19/countries/"+name;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Creating object of JSONObject
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    tvCountry.setText(jsonObject.getString("country"));
                    tvRecover.setText(jsonObject.getString("recovered"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvCase.setText(jsonObject.getString("cases"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));

                    country = jsonObject.getString("country");
                    activeRate = jsonObject.getString("cases");
                    DeathRate = jsonObject.getString("deaths");
                    recoverRate =jsonObject.getString("recovered");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error unable to get country", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        requestQueue.getCache().clear();
    }

}
