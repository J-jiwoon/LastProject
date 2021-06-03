package com.example.lastproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class DietRegisterAdapter extends BaseAdapter {

    private Context context;
    private List<DietrfList> registerlistView;

    public DietRegisterAdapter(Context context, List<DietrfList> registerlistView) {
        this.context = context;
        this.registerlistView = registerlistView;
    }

    @Override
    public int getCount() {
        return registerlistView.size();
    }

    @Override
    public Object getItem(int i) {
        return registerlistView.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.diet_register_item, null);
        TextView foodname = (TextView) v.findViewById(R.id.foodname);
        TextView foodkcal = (TextView) v.findViewById(R.id.foodkcal);
        TextView carbo = (TextView) v.findViewById(R.id.carbo);
        TextView protein = (TextView) v.findViewById(R.id.protein);
        TextView fat = (TextView) v.findViewById(R.id.fat);

        foodname.setText(registerlistView.get(i).getFoodname());
        foodkcal.setText(registerlistView.get(i).getFoodkcal());
        carbo.setText(registerlistView.get(i).getCarbo());
        protein.setText(registerlistView.get(i).getProtein());
        fat.setText(registerlistView.get(i).getFat());

        v.setTag(registerlistView.get(i).getFoodname());

        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), registerlistView.get(i).getFoodname(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
