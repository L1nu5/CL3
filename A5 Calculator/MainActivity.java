package com.example.acer5742g.calculator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button add, sub, mul, div, tan, cos,sin, sqrt, save, recall, clear, file_read;
    private TextView res;
    private EditText num1, num2;
    double saved_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    public void init(){
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);
        sin = (Button) findViewById(R.id.sin);
        cos = (Button) findViewById(R.id.cos);
        tan = (Button) findViewById(R.id.tan);
        sqrt = (Button) findViewById(R.id.sqrt);
        save = (Button) findViewById(R.id.save);
        recall = (Button) findViewById(R.id.recall);
        clear = (Button) findViewById(R.id.clear);
        file_read = (Button) findViewById(R.id.fileread);
        res = (TextView) findViewById(R.id.res);
        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        saved_value = 0;
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        save.setOnClickListener(this);
        recall.setOnClickListener(this);
        clear.setOnClickListener(this);
        file_read.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        String numb1 = num1.getText().toString();
        String numb2 = num2.getText().toString();

        switch(v.getId()){
            case R.id.add:
                double add_ans = Double.parseDouble(numb1) + Double.parseDouble(numb2);
                res.setText(String.valueOf(add_ans));
                break;
            case R.id.sub:
                double sub_ans = Double.parseDouble(numb1) - Double.parseDouble(numb2);
                res.setText(String.valueOf(sub_ans));
                break;
            case R.id.mul:
                double mul_ans = Double.parseDouble(numb1) * Double.parseDouble(numb2);
                res.setText(String.valueOf(mul_ans));
                break;
            case R.id.div:
                try {
                    BigDecimal n1 = new BigDecimal(Double.parseDouble(numb1));
                    BigDecimal n2 = new BigDecimal(Double.parseDouble(numb2));
                    BigDecimal div_ans = n1.divide(n2,5,BigDecimal.ROUND_HALF_UP);
                    res.setText(String.valueOf(div_ans));
                }
                catch(Exception e) {
                    res.setText("cannot divide by 0");
                }
                break;
            case R.id.sin:
                num2.setText(" ");
                double sin_ans = Math.sin(Double.parseDouble(numb1));
                res.setText(String.valueOf(sin_ans));
                break;
            case R.id.cos:
                num2.setText(" ");
                double cos_ans = Math.cos(Double.parseDouble(numb1));
                res.setText(String.valueOf(cos_ans));
                break;
            case R.id.tan:
                num2.setText(" ");
                double tan_ans = Math.tan(Double.parseDouble(numb1));
                res.setText(String.valueOf(tan_ans));
                break;
            case R.id.sqrt:
                num2.setText(" ");
                double sq_ans = Math.sqrt(Double.parseDouble(numb1));
                res.setText(String.valueOf(sq_ans));
                break;
            case R.id.save:
                String value = res.getText().toString();
                saved_value = Double.parseDouble(value);
                num1.setText(" ");
                num2.setText(" ");
                res.setText("Result");
                break;
            case R.id.recall:
                num1.setText(String.valueOf(saved_value));
                break;
            case R.id.clear:
                num1.setText(" ");
                num2.setText(" ");
                res.setText("Result");
                break;
            case R.id.fileread:
                try {
                    Context context=this;
                    AssetManager am =context.getAssets();
                    InputStream is = am.open("file.txt");
                    BufferedReader buf = new BufferedReader(new InputStreamReader(is));
                    String n1=buf.readLine();
                    String n2=buf.readLine();
                    num1.setText(n1);
                    num2.setText(n2);

                } catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;

        }

    }
}
