package com.kamalclasses.m_healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.opencsv.CSVReader;

public class CalorieTrackerActivity extends AppCompatActivity {

    TextView tvCalorieCount;
    EditText etFoodName;
    Button btnFindCount, btnViewCalories;
    String calories, kcal;
    static DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker);

        tvCalorieCount = findViewById(R.id.tvCalorieCount);
        etFoodName = findViewById(R.id.etFoodName);
        btnFindCount = findViewById(R.id.btnFindCount);
        btnViewCalories = findViewById(R.id.btnViewCalories);
        dbHandler = new DbHandler(this);

        btnFindCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                YourRun yourRun = new YourRun();
                executorService.execute(yourRun);
                executorService.shutdown();
            }
        });

        btnViewCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodname = etFoodName.getText().toString();
                String foodcalories = tvCalorieCount.getText().toString();
                food f = new food(foodname, foodcalories);
                CalorieTrackerActivity.dbHandler.add(f);
                Intent a = new Intent(CalorieTrackerActivity.this, ViewCalorieActivity.class);
                startActivity(a);
            }
        });
    }

    class YourRun implements Runnable {
        @Override
        public void run() {
            String a1 = "https://api.edamam.com/api/food-database/v2/parser?app_id=296c0772&app_key=fb0c122cf225bec51457404550d5ba10&ingr=";
            String a2 = etFoodName.getText().toString();
            String a3 = "&nutrition-type=cooking";

            String a = a1 + a2 + a3;

            try {
                URL url = new URL(a);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                Log.i("MON", line);


                JSONObject abc = new JSONObject(line);
                JSONArray def = abc.getJSONArray("hints");
                for (int i = 0; i < def.length(); i = 0 ) {
                    JSONObject c = def.getJSONObject(i);
                    JSONObject pqr = c.getJSONObject(String.valueOf("food"));
                    JSONObject xyz = pqr.getJSONObject("nutrients");
                    calories = xyz.getString("ENERC_KCAL");



                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String p = "Kilo Calories: " + calories + "\n";
                            Toast.makeText(CalorieTrackerActivity.this, "Data successfully displayed, Accuracy may fluctuate.", Toast.LENGTH_SHORT).show();
                            tvCalorieCount.setText(p);
                        }
                    });
                    break;

                }
                } catch(Exception e){
                    Log.i("MON", "" + e);
                    Toast.makeText(CalorieTrackerActivity.this, "e", Toast.LENGTH_SHORT).show();
                }


            }
        }
        }


