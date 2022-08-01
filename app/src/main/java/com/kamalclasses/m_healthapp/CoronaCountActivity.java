package com.kamalclasses.m_healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.font.NumericShaper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoronaCountActivity extends AppCompatActivity {

    TextView tvCoronaCount, tvLastUpdate;
    Button btnCorona;
    String cases, deaths, lastdate;
    Integer rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_count);

        tvCoronaCount = findViewById(R.id.tvCoronaCount);
        btnCorona = findViewById(R.id.btnCorona);
        tvLastUpdate = findViewById(R.id.tvLastUpdate);

        btnCorona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                MyRun myRun = new MyRun();
                executorService.execute(myRun);
                executorService.shutdown();
            }
        });
    }

    class MyRun implements Runnable{
        @Override
        public void run(){

            String a = "https://covid19.mathdro.id/api/countries/india";

            try {
                URL url = new URL(a);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                Log.i("MON", line);


                JSONObject abc = new JSONObject(line);
                JSONObject def = abc.getJSONObject("confirmed");
                cases = def.getString("value");



                JSONObject xyz = new JSONObject(line);
                JSONObject mon = xyz.getJSONObject("deaths");
                deaths = mon.getString("value");

                rec = Integer.valueOf(cases) - Integer.valueOf(deaths);

                JSONObject rst = new JSONObject(line);
                lastdate = rst.getString("lastUpdate");

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String p = "Cases: " + cases + "\n" + "Recovered: " + rec + "\n" + "Deaths: " + deaths;
                        Toast.makeText(CoronaCountActivity.this, "Data successfully displayed, Accuracy may fluctuate." , Toast.LENGTH_SHORT).show();
                        tvCoronaCount.setText(p);
                        tvLastUpdate.setText("Last Update: " + lastdate);
                    }
                });

            }
            catch (Exception e) {
                Log.i("MON", "" + e);
            }
        }
    }
}