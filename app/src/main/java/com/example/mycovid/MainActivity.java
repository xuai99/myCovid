package com.example.mycovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsPromptResult;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView myHealthInfo,globalStats,whoWebsite,Email;
    ImageView facebooks,twitters,instagrams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHealthInfo = findViewById(R.id.myHealthInfo);
        globalStats = findViewById(R.id.Vstats);
        whoWebsite = findViewById(R.id.myWhoWebsite);
        Email = findViewById(R.id.myEmail);
        facebooks = findViewById(R.id.facedookS);
        twitters = findViewById(R.id.twitterS);
        instagrams = findViewById(R.id.instagramS);

        facebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/WHO"));
                startActivity(browser);
            }
        });
        twitters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com/WHO"));
                startActivity(browser);
            }
        });
        instagrams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/who"));
                startActivity(browser);
            }
        });

        whoWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.who.int/emergencies/diseases/novel-coronavirus-2019"));
                startActivity(browser);
            }
        });
        myHealthInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.webmd.com/lung/covid-19-symptoms#1"));
                startActivity(browser);
            }
        });
        globalStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, globalStats.class);
                startActivity(i);
            }
        });
        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:erecruit@who.int?subject=" + "Employment" + "&body=" + "Send in your Resume!");
                intent.setData(data);
                startActivity(intent);
            }
        });

    }


}



