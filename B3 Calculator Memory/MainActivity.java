package com.example.abhishek.myapplication;

        import android.os.Environment;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ScrollView;
        import android.widget.TextView;

        import org.w3c.dom.Text;

        import java.io.BufferedWriter;
        import java.io.File;
        import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    String op1="",op2="",oper="",mem="";
    Double opd1=0.0,opd2=0.0,ans=0.0;

    Calculator calc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.calc = new Calculator();
    }

    public void onClick(View v){
        EditText edit1 = (EditText) findViewById(R.id.editText);
        EditText edit2  = (EditText) findViewById(R.id.editText2);
        TextView text = (TextView) findViewById(R.id.text1);

        Button btn = (Button) v;
        oper = (String) btn.getText().toString();

        if(oper.contains("+")){
            op1= edit1.getText().toString();
            opd1= Double.parseDouble(op1);
            op2 = edit2.getText().toString();
            opd2 = Double.parseDouble(op2);
            ans = calc.add(opd1,opd2);


        }


        if(oper.contains("-")){
            op1= edit1.getText().toString();
            opd1= Double.parseDouble(op1);
            op2 = edit2.getText().toString();
            opd2 = Double.parseDouble(op2);
            ans = calc.sub(opd1,opd2);


        }
        if(oper.contains("*")){
            op1= edit1.getText().toString();
            opd1= Double.parseDouble(op1);
            op2 = edit2.getText().toString();
            opd2 = Double.parseDouble(op2);
            ans = calc.mul(opd1,opd2);


        }

        if(oper.contains("/")){
            op1= edit1.getText().toString();
            opd1= Double.parseDouble(op1);
            op2 = edit2.getText().toString();
            opd2 = Double.parseDouble(op2);
            ans = calc.div(opd1,opd2);


        }

        if(oper.contains("sin")){
            op1= edit1.getText().toString();
            opd1= Double.parseDouble(op1);
            ans = calc.sin(opd1);


        }

        if(oper.contains("cos")){
            op1= edit1.getText().toString();
            opd1= Double.parseDouble(op1);
            ans = calc.cos(opd1);


        }


        if(oper.contains("tan")){
            op1= edit1.getText().toString();
            opd1= Double.parseDouble(op1);
            ans = calc.tan(opd1);


        }

        if(oper.contains("sqrt")){
            op1= edit1.getText().toString();
            opd1= Double.parseDouble(op1);
            ans = calc.sqrt(opd1);


        }

        if(oper.contains("c")){
            edit1.setText("");
            edit2.setText("");
            ans = 0.0;


        }




        if(oper.contains("sto")){
            File root = Environment.getExternalStorageDirectory();
            try{
                File file = new File(root,"mycal.txt");
                FileWriter fwriter = new FileWriter(file);
                BufferedWriter out1 = new BufferedWriter(fwriter);
                out1.write(mem);
                out1.close();


            }
            catch (Exception e){
                e.printStackTrace();

            }


        }
        if(oper.contains("mem")){
            TextView text3 = (TextView) findViewById(R.id.text3);
            text3.setText(mem);


        }
        if (!oper.contains("c") && !oper.contains("mem") && !oper.contains("sto")){
            mem+=op1+oper+op2+"="+ans+"\n";
            text.setText(String.valueOf(ans));
            oper="";
        }



    }


}
