package com.example.ana.android7json;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import java.lang.String;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button){
            getJsonPost();
            //getJsonAllPost();
        }
    }

    private void getJsonAllPost(){
        JsonArrayRequest jsonarray = new JsonArrayRequest(
                Request.Method.GET,
                "https://jsonplaceholder.typicode.com/posts",
                "",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        TextView text = (TextView) findViewById(R.id.textView);

                        try {
                            JSONObject json = response.getJSONObject(2);
                            text.setText(json.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getMessage();
                    }
                }
        );
        RequestQueue requestque = Volley.newRequestQueue(this);
        requestque.add(jsonarray);
    }

    public void getJsonPost(){
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                "https://jsonplaceholder.typicode.com/posts/1",
                "",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TextView text = (TextView) findViewById(R.id.textView);
                        try {
                            text.setText(response.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getMessage();
                    }
                }
        );
        RequestQueue requestque = Volley.newRequestQueue(this);
        requestque.add(jsonRequest);
    }
}
