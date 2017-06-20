package com.example.mrityunjay.androidassignment191;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnWebServiceResult {
   RecyclerView recyclerView;
    String url="http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa7972254124 29c1c50c122a1 ";
    List<DataHandler> model= new ArrayList<>();// arrayLiat
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerVeiw);// reyclerView
        hitRequest();
    }
    private void hitRequest(){
        FormEncodingBuilder parameters= new FormEncodingBuilder();
        parameters.add("page" , "1");
        if(NetworkStatus.getInstance(this).isConnectedToInternet()) {// checking internet
            CallAddr call = new CallAddr(this, url,parameters,CommonUtilities.SERVICE_TYPE.GET_DATA, this);
            call.execute();
        }else {
            Toast.makeText(this, "No Network!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type) {
        Log.e("res", "type= "+ type+ " res= "+ result);
        try {
            JSONObject obj= new JSONObject(result);// jason object
            JSONArray arr= obj.getJSONArray("cast");
            for(int i=0; i<arr.length(); i++) {
                JSONObject jobj= arr.getJSONObject(i);
                DataHandler handler = new DataHandler();
                handler.setId(jobj.getInt("id"));
                handler.setMain(jobj.getString("main"));
                handler.setName(jobj.getString("name"));
                handler.setDiscription(jobj.getString("description"));
                model.add(handler);
            }
            DataAdapter adapter= new DataAdapter(this, model);// adtapter call
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }catch (Exception e){
            e.printStackTrace();
        }
    }}

