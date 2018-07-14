package com.example.dakhilreddy.jpmcproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.dakhilreddy.jpmcproj.R.layout.Registration;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                EditText ed = (EditText) findViewById(R.id.editTextRegistration);
                String dbname = ed.getText().toString();

                EditText ed2 = (EditText) findViewById(R.id.RegisEmail);
                String dbemail = ed2.getText().toString();

                EditText ed3 = (EditText) findViewById(R.id.RegisPno);
                String dbregpno = ed3.getText().toString();

                EditText ed4 = (EditText) findViewById(R.id.Regispass);
                String dbregpass= ed4.getText().toString();

                EditText ed5 = (EditText) findViewById(R.id.Regisrepass);
                String dbregrepass = ed5.getText().toString();

                EditText ed6 = (EditText) findViewById(R.id.RegisPincode);
                String dbregpincode = ed6.getText().toString();

                Button bt1= (Button) findViewById(R.id.btSubmit);


                bt1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {


                        Intent intent = new Intent(Registration.this,NextForm.class);
                        startActivity(intent);
                    }
                });
                }











            }
        });
    }

}
