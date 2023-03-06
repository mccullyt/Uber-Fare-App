package com.example.uberfareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //create view objects
    private Button btnAct2;

    private EditText editNumMiles;

    private RadioGroup radioGroup;

    private RadioButton radioButton;

    //create Shared Preference Object
    private SharedPreferences sharedPref;

    //create cost variables
    private float initialFee = (float)3.00;
    private float costPerMile = (float)3.25;
    private float finalCost = (float)0.00;
    private float smartCarCost = (float)2.00;
    private float sedanCost = (float)0.00;
    private float vanCost =(float)5.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //grab views
        btnAct2 = (Button) findViewById(R.id.btnAct2);
        editNumMiles = (EditText) findViewById(R.id.editNumMiles);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        //setup click events
        btnAct2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                setPreferences();
                openSecondActivity();
            }
        });

        //create Shared Preference File
        sharedPref = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
    }

    public void setPreferences(){

        int checkedId = radioGroup.getCheckedRadioButtonId();
        float miles = Float.parseFloat(editNumMiles.getText().toString());
        String strFinalCost;
        radioButton = (RadioButton) findViewById(checkedId);
        String radioButtonStr = radioButton.getText().toString();

        // calculate cost based on radio button choice
        if(radioButtonStr.equals("Sedan")){finalCost = initialFee +sedanCost + (miles * costPerMile);}
        if(radioButtonStr.equals("Smart Car")){finalCost = initialFee + smartCarCost + (miles * costPerMile);}
        if(radioButtonStr.equals("Van")){finalCost = initialFee +vanCost+ (miles * costPerMile);}

        //format finalCost with two decimals
        strFinalCost = String.format("%.2f",finalCost);

       //apply values to preference file
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.putString("test_pref","This is a test");
        editor.putString("vehicle_choice",radioButtonStr);
        editor.putString("string_final_cost",strFinalCost);
        editor.putFloat("float_miles",miles);
        editor.putFloat("float_final_cost",finalCost);
        editor.apply();
    }

    public void readPreferences(){

    }
    public void openSecondActivity(){

//        test to see if radio buttons are working
//        int checkedId = radioGroup.getCheckedRadioButtonId();
//        if(checkedId == R.id.radioSedan){}
//        Toast.makeText(MainActivity.this,"Checked: "+checkedId,Toast.LENGTH_LONG).show();
//        Toast.makeText(MainActivity.this,"test_pref: "+sharedPref.getString("test_pref",null),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}