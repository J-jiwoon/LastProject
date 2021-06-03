package com.example.lastproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DietActivity extends AppCompatActivity {

    private ListView registerlistView;          // 검색을 보여줄 리스트변수
    private DietRegisterAdapter rgadapter;
    private List<DietrfList> dietrfList;          // 데이터를 넣은 리스트변수
    private Button btn_dietregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);


        btn_dietregister = findViewById(R.id.btn_dietregister);

        registerlistView = (ListView) findViewById(R.id.registerlistView);
        dietrfList = new ArrayList<DietrfList>();


        rgadapter = new DietRegisterAdapter(getApplicationContext(), dietrfList);
        registerlistView.setAdapter(rgadapter);

        new BackgroundTask().execute();

        btn_dietregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DietActivity.this, DietRegisterActivity.class );
                startActivity(intent);
            }
        });


    }

    class BackgroundTask extends AsyncTask<Void, Void, String>{

        String target;

        @Override
        protected void onPreExecute(){
            target = "http://122.39.240.72/Dietrflist.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String temp;
                StringBuilder stringBuilder = new StringBuilder();

                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result){
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");

                int count = 0;

                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    String foodname = object.getString("foodname");
                    String foodkcal = object.getString("foodkcal");
                    String carbo = object.getString("carbo");
                    String protein = object.getString("protein");
                    String fat = object.getString("fat");

                    DietrfList diet_register_item = new DietrfList(foodname, foodkcal, carbo, protein, fat);
                    dietrfList.add(diet_register_item);

                    rgadapter.notifyDataSetChanged();

                    count++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

