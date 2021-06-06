package com.example.lastproject;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddRequest extends StringRequest {

    final static private String URL = "http://122.39.240.72/DietRegister.php";
    private Map<String, String> parameters;

    public AddRequest(String foodname, String foodkcal, String carbo, String protein, String fat, String userID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("foodname", foodname);
        parameters.put("foodkcal", foodkcal);
        parameters.put("carbo", carbo);
        parameters.put("protein", protein);
        parameters.put("fat", fat);
        parameters.put("userID", userID);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }

}
