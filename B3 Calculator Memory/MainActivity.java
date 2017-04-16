package com.example.vaishnavi.b3;

        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;
        import java.util.HashMap;
        import java.util.Map;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String mem_store = "";
    String expression = "";
    String op = "0";
    String num1 = "";
    String num2 = "";
    String prefs = "history";
    SharedPreferences preferences ;
    boolean dec = false;
    boolean flag = false;
    TextView display,memstore;
    Button mem,zero,one,two,three,four,five,six,seven,eight,nine,decimal_point, equals,sine,cos,percent,clear,add,subtract,divide,multiply,reset,open,close;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(prefs,MODE_PRIVATE);
        display = (TextView)findViewById(R.id.display);
        memstore = (TextView)findViewById(R.id.memstore);
        try {
            mem = (Button) findViewById(R.id.memory);
            mem.setOnClickListener(this);
            open = (Button) findViewById(R.id.open);
            open.setOnClickListener(this);
            close = (Button) findViewById(R.id.close);
            close.setOnClickListener(this);
            reset = (Button) findViewById(R.id.reset);
            reset.setOnClickListener(this);
            zero = (Button) findViewById(R.id.zero);
            zero.setOnClickListener(this);
            one = (Button) findViewById(R.id.one);
            one.setOnClickListener(this);
            two = (Button) findViewById(R.id.two);
            two.setOnClickListener(this);
            three = (Button) findViewById(R.id.three);
            three.setOnClickListener(this);
            four = (Button) findViewById(R.id.four);
            four.setOnClickListener(this);
            five = (Button) findViewById(R.id.five);
            five.setOnClickListener(this);
            six = (Button) findViewById(R.id.six);
            six.setOnClickListener(this);
            seven = (Button) findViewById(R.id.seven);
            seven.setOnClickListener(this);
            eight = (Button) findViewById(R.id.eight);
            eight.setOnClickListener(this);
            nine = (Button) findViewById(R.id.nine);
            nine.setOnClickListener(this);
            decimal_point = (Button) findViewById(R.id.decimal);
            decimal_point.setOnClickListener(this);
            equals = (Button) findViewById(R.id.equals);
            equals.setOnClickListener(this);
            sine = (Button) findViewById(R.id.sine);
            sine.setOnClickListener(this);
            cos = (Button) findViewById(R.id.cosine);
            cos.setOnClickListener(this);
            percent = (Button) findViewById(R.id.percent);
            percent.setOnClickListener(this);
            clear = (Button) findViewById(R.id.clear);
            clear.setOnClickListener(this);
            add = (Button) findViewById(R.id.add);
            add.setOnClickListener(this);
            subtract = (Button) findViewById(R.id.subtract);
            subtract.setOnClickListener(this);
            divide = (Button) findViewById(R.id.divide);
            divide.setOnClickListener(this);
            multiply = (Button) findViewById(R.id.multiply);
            multiply.setOnClickListener(this);
        } catch (NullPointerException e) {
            Log.d("ABC","Null pointer exception encountered");
        }
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.zero:
                expression += "0";
                mem_store += "0";
                display.setText(expression);
                 break;
            case R.id.one:
                expression += "1";
                mem_store += "1"; display.setText(expression);
                break;
            case R.id.two:
                mem_store += "2";
                expression += "2"; display.setText(expression);
                break;
            case R.id.three:
                mem_store += "3";
                expression += "3";
                display.setText(expression);
                break;
            case R.id.four:
                mem_store += "4";
                expression += "4";
                display.setText(expression);
                break;
            case R.id.five:
                mem_store += "5";
                expression += "5";
                display.setText(expression);
                break;
            case R.id.six:
                mem_store += "6";
                expression += "6";
                display.setText(expression);
                break;
            case R.id.seven:
                mem_store += "7";
                expression += "7";
                display.setText(expression);
                break;
            case R.id.eight:
                mem_store += "8";
                expression += "8";
                display.setText(expression);
                break;
            case R.id.nine:
                mem_store += "9";
                expression += "9";
                display.setText(expression);
                break;
            case R.id.decimal:
                if(!dec) {
                    mem_store += ".";
                    expression += ".";
                    display.setText(expression);
                    dec = true;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Can’t add another decimal.",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.open:
                mem_store += "(";
                expression += " ( ";
                display.setText(expression);
                break;
            case R.id.close:
                mem_store += ")";
                expression += " ) ";
                display.setText(expression);
            case R.id.equals:
                calculate();
                break;
            case R.id.sine:
                mem_store += "sin ";
                display.setText("sin ");
                expression = "";
                num1 = "";
                num2 = "";
                op = "1";
                dec= false;
                flag = false;
                expression = "sin ";
                break;
            case R.id.cosine:
                mem_store += "cos ";
                display.setText("cos ");
                expression = "";
                num1 = "";
                num2 = "";
                dec= false;
                flag = false;
                op = "2";
                expression = "cos ";
                break;
            case R.id.percent:
                break;
            case R.id.clear:
                break;
            case R.id.add:
                mem_store += "+";
                expression += " + ";
                display.setText(expression);
                dec = false;
                break;
            case R.id.subtract:
                mem_store += "-";
                dec = false;
                expression += " - ";
                display.setText(expression);
                break;
            case R.id.divide:
                mem_store += "/";
                dec = false;
                expression += " / ";
                display.setText(expression);
                break;
            case R.id.multiply:
                mem_store += "*";
                dec = false;
                expression += " * ";
                display.setText(expression);
                break;
            case R.id.memory:
                Map<String, ?> stored = preferences.getAll();
                String temp = "";
                for(int i=1;i<=stored.size();i++) {
                    temp += preferences.getString(""+i,"nothing to show");
                    temp +="\n";
                }
                memstore.setText(""+temp);
                break;
            case R.id.reset:
                if(op.equals("0")) {
                    mem_store += " = "; mem_store += expression;
                }
                Log.d("ABC","Full expression is "+mem_store);
                if(!mem_store.equals(" = ")) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(""+(preferences.getAll().size()+1),mem_store);
                    editor.commit();
                }
                op = "0"; mem_store = "";
                expression = " ";
                dec = false;
                display.setText(expression);
                break;
        }
    }
    public void calculate()
    {
        if(op.equals("1")) {
        mem_store += " = ";
        mem_store += Math.sin(Double.parseDouble (expression.substring(4))*(Math.PI/180));
        display.setText(""+Math.sin(Double.parseDouble (expression.substring(4))*(Math.PI/180)));
    }
    else if(op.equals("2")) {
        mem_store += " = ";
        mem_store += Math.cos(Double.parseDouble (expression.substring(4)) * (Math.PI / 180));
        display.setText(""+Math.cos(Double.parseDouble (expression.substring(4))*(Math.PI/180)));
    }
    else {
        expression = "" + EvaluateString.evaluate(expression);
        display.setText(expression);
    }
    }
}
