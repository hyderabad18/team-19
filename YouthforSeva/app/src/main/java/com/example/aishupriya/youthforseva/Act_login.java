package com.example.aishupriya.youthforseva;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static android.R.attr.password;
import static android.R.id.content;

public class Act_login extends Activity {
    EditText e1, e2;
    Button b1;
    TextView t1;
    String uname,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_login);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        t1=  (TextView) findViewById(R.id.textView2);

        b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(e1.getText().toString(),e2.getText().toString());
            }
        });

    }
    private void validate(String username ,String userpassword){
        if ((username.equals("admin")) && (userpassword.equals("admin"))) {
            Intent intent = new Intent(Act_login.this, EventReg.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"wrong credentials",Toast.LENGTH_SHORT).show();
        }
    }
    // Create GetText Metod
    public  void  GetText()  throws UnsupportedEncodingException
    {
        // Get user defined values
        uname = e1.getText().toString();
        pass   = e2.getText().toString();
        // Create data variable for sent values to server
        String data = URLEncoder.encode("name", "UTF-8")
                + "=" + URLEncoder.encode(uname, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(pass, "UTF-8");
        String text = "";
        BufferedReader reader=null;
        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL("/media/webservice/httppost.php");

            // Send POST data request
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }
            text = sb.toString();
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {
                reader.close();
            }

            catch(Exception ex) {}
        }
        // Show response on activity
        t1.setText( text  );
    }

}