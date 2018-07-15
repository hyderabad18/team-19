package com.example.youthforseva;

import com.example.youthforseva.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
Button bt1,bt2,bt3;
EditText et1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et1=(EditText) findViewById(R.id.editText1);
	 bt1=(Button)findViewById(R.id.neweve);
	 bt2=(Button)findViewById(R.id.volunterev);
	 bt3=(Button)findViewById(R.id.notifyeve);
	 bt1.setOnClickListener(new View.OnClickListener(){
		 public void onClick(View view){
			 Intent it=new Intent(MainActivity.this,EventPostActivity.class);
			 startActivity(it);
			 
		 }
	 });
	 bt2.setOnClickListener(new View.OnClickListener(){
		 public void onClick(View view){
			 Intent it=new Intent(MainActivity.this,VolunteerEventActivity.class);
			 startActivity(it);
		 }
	 });
	 bt3.setOnClickListener(new View.OnClickListener(){
		 public void onClick(View view){
			 Intent it=new Intent(MainActivity.this,VolunteerNotificationActivity.class);
			 startActivity(it);
		 }
	 });
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
