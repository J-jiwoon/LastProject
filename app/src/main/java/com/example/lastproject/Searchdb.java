package com.example.lastproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Searchdb extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button btn;
    private String jsonString;
    ArrayList<HelperDB> memberArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdb);

        textView = (TextView) findViewById(R.id.idname);
        editText = (EditText) findViewById(R.id.edittext);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JsonParse jsonParse = new JsonParse();
                jsonParse.execute("http://192.168.219.103/connect.php");
            }
        });
    }

    public class JsonParse extends AsyncTask<String, Void, String>{
        String TAG = "JsonParseMember";

        @Override
        protected String doInBackground(String... strings) {
            //execute의 매개변수를 받아와서 사용
            String url = strings[0];
            try{
                String selectData = "Data=" + editText.getText().toString();
                //따옴표안과 php의 post[ ]안이 이름이 같아야한다.
                URL serverURL = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) serverURL.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(selectData.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();
                //어플에서 데이터 전송

                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK){
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }   // 연결상태 확인

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();
                Log.d(TAG, sb.toString().trim());

                return sb.toString().trim();    //받아온 JSON의 공백을 제거

            } catch (Exception e) {
                Log.d(TAG, "insertData : Error", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String fromdoInBackgroundString){  // doInBackgroundString에서 return한 값을 받음
            super.onPostExecute(fromdoInBackgroundString);

            if(fromdoInBackgroundString == null) {
                textView.setText("error");
            }
            else{
                jsonString = fromdoInBackgroundString;
                memberArrayList = doParse();
                if(memberArrayList.size() == 0){
                    textView.setText("검색결과없음"); // 객체의 크기가 0일때는 검색 결과가 없을 때이므로 검색결과 없음 설정
                }
                else{
                    textView.setText(memberArrayList.get(0).getPassword());
                }
            }
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s){
            super.onCancelled(s);
        }

        private ArrayList<HelperDB> doParse(){
            ArrayList<HelperDB> tmpmemberArray = new ArrayList<HelperDB>();
            try{
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("member");

                for(int i = 0; i<jsonArray.length(); i++){
                    HelperDB tmpmember = new HelperDB();
                    JSONObject item = jsonArray.getJSONObject(i);
                    tmpmember.setId(item.getString("아이디"));
                    tmpmember.setPassword(item.getString("비번"));

                    tmpmemberArray.add(tmpmember);
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
            return tmpmemberArray;
        }
    }

}