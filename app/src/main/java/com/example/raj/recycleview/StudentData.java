package com.example.raj.recycleview;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class StudentData extends StringRequest {

    private  static final String Token_Insert_Url = "http://www.stjohnvianneygwalior.org/androidphp/StudentData.php";
    private Map<String, String > params ;

    public StudentData(String mobile,  Response.Listener<String> Listener)
    {
        super(Method.POST,Token_Insert_Url,Listener,null);

        params = new HashMap<>();
        params.put("mobile",mobile);
    }

    @Override
    public Map<String , String> getParams()
    {
        return  params;
    }
}
