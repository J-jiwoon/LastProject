package com.example.lastproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

public class DietListAdapter extends BaseAdapter {

    private Context context;
    private List<DietfdList> dietfdList;
    private List<DietfdList> searchfdList;

    private List<DietrfList> dietrfList;

    private Fragment parent;


    public DietListAdapter(Context context, List<DietfdList> dietfdList, List<DietfdList> searchfdList, List<DietrfList> dietrfList, Fragment parent) {
        this.context = context;
        this.dietfdList = dietfdList;
        this.searchfdList = searchfdList;

        this.dietrfList = dietrfList;
        this.parent = parent;
    }

    @Override
    public int getCount() {
        return dietfdList.size();
    }

    @Override
    public Object getItem(int i) {
        return dietfdList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.diet_list_item, null);
        TextView foodname = (TextView) v.findViewById(R.id.foodname);
        TextView foodkcal = (TextView) v.findViewById(R.id.foodkcal);

        foodname.setText(dietfdList.get(i).getFoodname());
        foodkcal.setText(dietfdList.get(i).getFoodkcal());

        v.setTag(dietfdList.get(i).getFoodname());

        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String fdname = dietrfList.get(i).getFoodname();
                String fdkcal = dietrfList.get(i).getFoodkcal();
                String carbo = dietrfList.get(i).getCarbo();
                String protein = dietrfList.get(i).getProtein();
                String fat = dietrfList.get(i).getFat();

                String userID = DietListFragment.userID;

                Response.Listener<String> responseListener = (response) ->{
                    try{
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if(success){
                            Toast.makeText(v.getContext(), "?????? ?????? ?????? = " + dietrfList.get(i).getFoodname(), Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(v.getContext(), "?????? ??????", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                };
                AddRequest addRequest = new AddRequest(fdname, fdkcal, carbo, protein, fat, userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(v.getContext());
                queue.add(addRequest);
            }
        });
/*
        for(int j = 0; j < searchfdList.size(); j++){
            if(searchfdList.get(i).getFoodname().equals(foodname.getText().toString())){
                searchfdList.remove(j);
                break;
            }
        }
        notifyDataSetChanged();   ?????? ?????? ?????? ???*/

        return v;
    }
}
