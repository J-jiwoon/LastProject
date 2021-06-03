package com.example.lastproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
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

public class DietRegisterActivity extends AppCompatActivity {

    private EditText editSearch;        // 검색어를 입력할 Input 창

    private ListView dietfdListView;          // 검색을 보여줄 리스트변수
    private DietListAdapter fdadapter;
    private List<DietfdList> dietfdList;          // 데이터를 넣은 리스트변수
    private List<DietfdList> searchfdList;  // 검색을 위한 리스트변수

    private List<DietrfList> dietrfList;          // 데이터를 넣은 리스트변수
    private DietRegisterAdapter rgadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_register);

        dietfdListView = (ListView) findViewById(R.id.dietfdListView);
        dietfdList = new ArrayList<DietfdList>();
        searchfdList = new ArrayList<DietfdList>();

        dietrfList = new ArrayList<DietrfList>();


        fdadapter = new DietListAdapter(getApplicationContext(), dietfdList, searchfdList, dietrfList);
        dietfdListView.setAdapter(fdadapter);

        rgadapter = new DietRegisterAdapter(getApplicationContext(), dietrfList);
        /*
        editSearch = (EditText) findViewById(R.id.editSearch);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchFood(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });   검색기능 구현중 */

        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>{

        String target;

        @Override
        protected void onPreExecute(){
            target = "http://122.39.240.72/Dietfdlist.php";
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

                    DietfdList diet_list_item = new DietfdList(foodname, foodkcal);
                    dietfdList.add(diet_list_item);

                    DietrfList diet_register_item = new DietrfList(foodname, foodkcal, carbo, protein, fat);
                    dietrfList.add(diet_register_item);

                    fdadapter.notifyDataSetChanged();

                    count++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /*
    public void searchFood(String editSearch){
        dietfdList.clear();
        for(int i = 0; i < searchfdList.size(); i++){
            if(searchfdList.get(i).getFoodname().contains(editSearch)){
                dietfdList.add(searchfdList.get(i));
            }
        }
        fdadapter.notifyDataSetChanged();
    } 검색 기능 구현중*/
}

