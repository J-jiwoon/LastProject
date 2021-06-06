package com.example.lastproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class DietRegisterFragment extends Fragment {

    private ListView registerlistView;          // 검색을 보여줄 리스트변수
    private DietRegisterAdapter rgadapter;
    private List<DietrfList> dietrfList;          // 데이터를 넣은 리스트변수

    public static String userID;

    public DietRegisterFragment() {
        // Required empty public constructor
    }

    public static DietRegisterFragment newInstance() {
        DietRegisterFragment fragment = new DietRegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle b) {

        super.onActivityCreated(b);

        registerlistView = (ListView) getView().findViewById(R.id.registerlistView);
        dietrfList = new ArrayList<DietrfList>();

        userID = (String) getArguments().get("userID");

        rgadapter = new DietRegisterAdapter(getActivity(), dietrfList);
        registerlistView.setAdapter(rgadapter);

        new BackgroundTask().execute();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet_register, container, false);
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute(){
            try {
                target = "http://122.39.240.72/Dietrflist.php?userID="+userID;
            } catch (Exception e) {
                e.printStackTrace();
            }
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

                    DietrfList diet_register_item = new DietrfList(foodname, foodkcal, carbo, protein, fat, userID);
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