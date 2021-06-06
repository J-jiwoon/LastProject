package com.example.lastproject;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DietListFragment extends Fragment {

    private EditText editSearch;        // 검색어를 입력할 Input 창

    private ListView dietfdListView;          // 검색을 보여줄 리스트변수
    private DietListAdapter fdadapter;
    private List<DietfdList> dietfdList;          // 데이터를 넣은 리스트변수
    private List<DietfdList> searchfdList;  // 검색을 위한 리스트변수

    private List<DietrfList> dietrfList;          // 데이터를 넣은 리스트변수

    public static String userID;

    public DietListFragment() {
        // Required empty public constructor
    }

    public static DietListFragment newInstance() {
        DietListFragment fragment = new DietListFragment();
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
        dietfdListView = (ListView) getView().findViewById(R.id.dietfdListView);
        dietfdList = new ArrayList<DietfdList>();
        searchfdList = new ArrayList<DietfdList>();

        dietrfList = new ArrayList<DietrfList>();


        userID = (String) getArguments().get("userID");

        fdadapter = new DietListAdapter(getActivity(), dietfdList, searchfdList, dietrfList, this);
        dietfdListView.setAdapter(fdadapter);

        new BackgroundTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet_list, container, false);
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

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

                    DietrfList diet_register_item = new DietrfList(foodname, foodkcal, carbo, protein, fat, userID);
                    dietrfList.add(diet_register_item);

                    fdadapter.notifyDataSetChanged();

                    count++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}