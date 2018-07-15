package com.example.android.volleylibrary;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    Button button;
    EditText Eventname,Startdate,Enddate,Locality,Description,Trainee,Status;
    String server_url="http://10.0.2.2/cfg/v1/createEvent";
    //String server_url="http://10.0.2.2/update_info.php";
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.eventbutton);
        Eventname=(EditText)findViewById(R.id.eventname);
        Startdate=(EditText)findViewById(R.id.startdate);
        Enddate=(EditText)findViewById(R.id.enddate);
        Locality=(EditText)findViewById(R.id.locality);
        Description=(EditText)findViewById(R.id.description);
        Trainee=(EditText)findViewById(R.id.trainee);
        Status=(EditText)findViewById(R.id.status);
        builder=new AlertDialog.Builder(MainActivity.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name,startdate,enddate,description,locality,trainee,status;
                name=Eventname.getText().toString();
                startdate=Startdate.getText().toString();
                enddate=Enddate.getText().toString();
                description=Description.getText().toString();
                locality=Locality.getText().toString();
                trainee=Trainee.getText().toString();
                status=Status.getText().toString();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                builder.setTitle("server response");
                                builder.setMessage("response:"+response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Eventname.setText("");
                                        Startdate.setText("");
                                        Enddate.setText("");
                                        Description.setText("");
                                        Locality.setText("");
                                        Trainee.setText("");
                                        Status.setText("");


                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                            }
                        }
                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"error..",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/x-www-form-urlencoded");
                        return params;
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("eventname",name);
                        params.put("startdate",startdate);
                        params.put("enddate",enddate);
                        params.put("description",description);
                        params.put("locality",locality);
                        params.put("trainee",trainee);
                        params.put("status",status);
                        Log.i("PARAMS", params.toString());


                        return params;
                    }

                };
                MySingleton.getInstance(MainActivity.this).addTorequestque(stringRequest);
            }
        });

    }
}
