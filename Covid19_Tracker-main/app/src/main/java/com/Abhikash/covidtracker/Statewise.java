package com.Abhikash.covidtracker;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Statewise extends AppCompatActivity {

    CityModel cityModel;
    ArrayList<CityModel> cityModelList = new ArrayList<>();
    CityAdapter cityAdapter;
    RecyclerView recyclerView;
    String statename;
    TextView stateheading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statevise);
        this.getSupportActionBar().hide();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        stateheading=findViewById(R.id.statehead);
        statename=(String)getIntent().getStringExtra("name");
        stateheading.setText(statename.toUpperCase());
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview2);
        cityAdapter =new CityAdapter(cityModelList, Statewise.this);
        recyclerView.setAdapter(cityAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Statewise.this));
        fetchData();
    }

    private void fetchData() {

        String url = "https://data.covid19india.org/state_district_wise.json";

        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject object = new JSONObject(response);
                    Iterator<String> keys = object.keys();

                    while (keys.hasNext()) {
                        String key = keys.next();
                        if(key.equals(statename)){
                            JSONObject obj1 = object.getJSONObject(key);
                            JSONObject obj2 = obj1.getJSONObject("districtData");
                            Iterator<String> subkeys = obj2.keys();

                            while (subkeys.hasNext()) {
                                String subkey = subkeys.next();
                                JSONObject obj3 = obj2.getJSONObject(subkey);
                                JSONObject obj4 = obj3.getJSONObject("delta");


                                String active = obj3.getString("active");
                                String confirmed = obj3.getString("confirmed");
                                String deceased = obj3.getString("deceased");
                                String recovered = obj3.getString("recovered");


                            cityModel = new CityModel(subkey,active,confirmed,deceased
                                    ,recovered);
                            cityModelList.add(cityModel);

                            }

                        }
                    }

                    cityAdapter = new CityAdapter(cityModelList, Statewise.this) {

                    };
                    recyclerView.setAdapter(cityAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // In case of error it will run
                Toast.makeText(Statewise.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue reqQueue = Volley.newRequestQueue(this);
        reqQueue.add(req);
    }
}