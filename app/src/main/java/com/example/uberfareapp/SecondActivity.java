package com.example.uberfareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    //create view objects
    private Button btnAct3;
    private Button btnMain;

    private TextView sumVChoice;

    private TextView sumDistance;

    private TextView sumCost;

    //create Preference Object
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        sharedPref = getSharedPreferences("myprefs",MODE_PRIVATE);


        //grab views
        btnAct3 = (Button) findViewById(R.id.btnAct3);
        btnMain = (Button) findViewById(R.id.btnMain1);
        sumVChoice = (TextView) findViewById(R.id.txtSumVehicle);
        sumDistance = (TextView) findViewById(R.id.txtSumDistance);
        sumCost = (TextView) findViewById(R.id.txtSumCost);

        //update summary
        updateSummary();

        //setup click events
        btnAct3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {openThirdActivity();}
        });
        btnMain.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {openMain();}
        });
    }

    public void updateSummary(){
//        float prefSumCost = sharedPref.getFloat("float_final_cost",0);
        float prefSumDistance = sharedPref.getFloat("float_miles",0);
        String prefSumCost = sharedPref.getString("string_final_cost",null);
        String prefSumVChoice = sharedPref.getString("vehicle_choice",null);
        sumCost.setText("Final Cost: $"+ prefSumCost);
        sumDistance.setText("Distance: "+prefSumDistance);
        sumVChoice.setText("Vehicle Choice: "+ prefSumVChoice);

    }
    public void openThirdActivity(){
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}