package com.example.raj.recycleview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private JSONArray result;
    private List<Listitem> listitem;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public static String mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitem = new ArrayList<>();
        
        mobile = getIntent().getStringExtra("phone_no"); // using phone number student data will ftech from server


        //progess dialog.
        /*final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loding data...");
        progressDialog.setCancelable(false);
        progressDialog.show();*/

        //show the data by simple method using for in recycleView
        /*for (int i=0;i<10;i++)
        {
            Listitem item = new Listitem(
                    "subject"
            );
            listitem.add(item);
        }
        adapter = new MyAdapter(listitem, getApplicationContext());
        recyclerView.setAdapter(adapter);*/


        //this is response listner of volley through this we are fetching the data from servern databse.
        final Response.Listener <String> listener = new Response.Listener <String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject object = new JSONObject(response);
                    result = object.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++)
                    {
                        JSONObject o = result.getJSONObject(i);
                        Listitem item = new Listitem
                                (
                                        o.getString("s_name"),
                                        o.getString("scholar_id"),
                                        o.getString("class_id"),
                                        o.getString("class_no")
                                );
                        listitem.add(item);
                    }
                    adapter = new MyAdapter(listitem, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        //anotherchild class is responsible to send  the req
        Anotherchild anotherchild = new Anotherchild(mobile, listener);
        RequestQueue requestQueue = Volley.newRequestQueue(SecondActivity.this);
        requestQueue.add(anotherchild);
        requestQueue.start();
    }

    //this fuction is use to redirect another activity when recycleview item get click.
    public static void onClick(View view) {
        view.getContext().startActivity(new Intent(view.getContext(),ThirdActivity.class));
    }
}
