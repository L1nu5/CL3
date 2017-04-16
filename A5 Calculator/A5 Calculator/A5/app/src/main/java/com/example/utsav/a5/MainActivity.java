package com.example.utsav.a5;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button; import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public TextView textView;
    double calc;
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        calc=0;
        textView = (TextView) findViewById(R.id.textView);
    }
    ArrayList<String> arrayList =new ArrayList<String>();
    String string= "";
    String string1= "";
    //this function accepts the equation and sorts it
    public void onClick1 (View v)
     {
         TextView textView2= (TextView) findViewById(R.id.textView2);
         Button button = (Button) v;
         string= (String) button.getText().toString();

            if ( !string.contains("+") && !string.contains("-") && !string.contains("*") && !string.contains("/") && !string.contains("S") && !string.contains("C") && !string.contains("T"))
            {
                string1= string1+ string;
                if(arrayList.size()>0)
                {
                    arrayList.remove(arrayList.size()-1);
                }
                arrayList.add(string1);
                calc = Double.parseDouble(string1);
            }
            else
            {
                arrayList.add(string);
                arrayList.add(string);
                string1="";

            }
         textView2.setText(textView2.getText().toString()+ string);

         //textView2.setText(string);
    }
    //this function performs the actual calculation
    public void onClick (View v) {
        Double calc=0.0;
        int c= arrayList.size();
        while(c!=1)
        {
            if (c>3)
            {
                if(arrayList.get(3).contains("*") ||arrayList.get(3).contains("/"))
                {
                    if(arrayList.get(3).contains("*"))  { calc= Double.parseDouble(arrayList.get(2)) * Double.parseDouble(arrayList.get(4));}
                    if(arrayList.get(3).contains("/"))  { calc= Double.parseDouble(arrayList.get(2))/ Double.parseDouble(arrayList.get(4));}
                    arrayList.remove(2);
                    arrayList.remove(2);
                    arrayList.remove(2);
                    arrayList.add(2, Double.toString(calc));
                    c=arrayList.size();
                }
                else
                {
                    if (arrayList.get(1).contains("+")) {calc = add(arrayList);}
                    if (arrayList.get(1).contains("-")) {calc = sub(arrayList);}
                    if (arrayList.get(1).contains("*")) {calc = mul(arrayList);}
                    if (arrayList.get(1).contains("/")) {calc = div(arrayList);}
                    arrayList.remove(0);
                    arrayList.remove(0);
                    arrayList.remove(0);
                    arrayList.add(0, Double.toString(calc));
                    c=arrayList.size();
                }
            }
            else
            {
                if (arrayList.get(1).contains("+")) {
                    calc= add(arrayList);
                }
                if (arrayList.get(1).contains("-")) {
                    calc= sub(arrayList);
                }
                if (arrayList.get(1).contains("*")) {
                    calc= mul(arrayList);
                }
                if (arrayList.get(1).contains("/")){
                    calc= div(arrayList);
                }
                if (arrayList.get(1).contains("S")){
                    temp= sinFunct(arrayList);
                    calc=Double.parseDouble(temp);
                }
                if (arrayList.get(1).contains("C")){
                    temp= cosfn(arrayList);
                    calc=Double.parseDouble(temp);
                }
                if (arrayList.get(1).contains("T")){
                    temp= tanfn(arrayList);
                    calc=Double.parseDouble(temp);
                }
                arrayList.remove(0);
                arrayList.remove(0);
                arrayList.remove(0);
                arrayList.add(0, Double.toString(calc));
                c=arrayList.size();
            }
        }
        if(arrayList.size()==1) {
            calc= Double.parseDouble(arrayList.get(0));

        }
        textView.setText("" + calc);
    }
    public Double add(ArrayList<String> arrayList){
        calc= Double.parseDouble(arrayList.get(0)) + Double.parseDouble(arrayList.get(2));
        return calc;
    }
    public Double sub(ArrayList<String> arrayList){
        calc= Double.parseDouble(arrayList.get(0)) - Double.parseDouble(arrayList.get(2));
        return calc;
    }
    public Double mul(ArrayList<String> arrayList){
        calc= Double.parseDouble(arrayList.get(0)) * Double.parseDouble(arrayList.get(2));
        return calc;
    }
    public Double div(ArrayList<String> arrayList){
        calc= Double.parseDouble(arrayList.get(0)) / Double.parseDouble(arrayList.get(2));
        return calc;
    }


    public String sinFunct(ArrayList<String> arrayList) {
        calc = Math.sin(Math.toDegrees(Double.parseDouble(arrayList.get(0))));
        return String.format( "%.2f", calc ) ;
    }
    public String cosfn(ArrayList<String> arrayList) {
        calc = Math.cos(Math.toDegrees(Double.parseDouble(arrayList.get(0))));
        return String.format( "%.2f", calc ) ;
    }
    public String tanfn(ArrayList<String> arrayList) {
        calc = Math.tan(Math.toDegrees(Double.parseDouble(arrayList.get(0))));
        return String.format( "%.2f", calc ) ;
    }

    public void cosecfn(View v) { textView.setText("" + 1/(Math.sin(calc))); }
    public void secfn(View v) { textView.setText("" + 1/(Math.cos(calc))); }
    public void cotfn(View v) { textView.setText("" + 1/Math.tan(calc)); }
    public void sqrtfn(View v) { textView.setText("" + Math.sqrt(calc)); }
    public void storeres(View v)
    {
        File root = Environment.getExternalStorageDirectory();
        try
        {
            File gpxfile = new File(root, "samplefile.txt");
            FileWriter gpxwriter = new FileWriter(gpxfile);
            BufferedWriter out1 = new BufferedWriter(gpxwriter);
            out1.write(String.valueOf(calc));
            out1.close();
        }
        catch(Exception e) { }
    }
    public void ansres(View v)
    {
        onClick(v);
        TextView textView= (TextView) findViewById(R.id.textView);
        TextView textView2= (TextView) findViewById(R.id.textView2);
        textView2.setText(textView.getText().toString());
    }

    public void clear(View v)
    {
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView textView2 =(TextView) findViewById(R.id.textView2);
        string1="";
        string="";
        textView.setText("0");
        textView2.setText("");
        arrayList.clear();
    }
}


