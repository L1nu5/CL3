package com.example.abhishek.employee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    public class Employee{
        public int id;
        public String name;
        public int attendance;

        Employee(){
            this.id = -1;
            name = "";
            attendance = 0;
        }

        public static final String notice = "All Employees should be present on" +
                " this 20th May for reporting to their respected managers.";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Employee Records
        final Employee[] records = new Employee[100];

        for(int i=0;i<100;i++){
            records[i] = new Employee();
            records[i].id = i+1;
            records[i].name = "x"+(i+1);
            records[i].attendance = 0;
        }

        // All Buttons
        Button btnAttd = (Button)findViewById(R.id.buttonAttendance);
        Button btnNotices = (Button)findViewById(R.id.buttonNotices);
        Button btnPayroll = (Button)findViewById(R.id.buttonPayroll);

        // All TextViews
        final TextView emp1 = (TextView)findViewById(R.id.emp1);
        final TextView emp2 = (TextView)findViewById(R.id.emp2);
        final TextView emp3 = (TextView)findViewById(R.id.emp3);

        // All EditTexts
        final EditText editID = (EditText)findViewById(R.id.empID);
        final EditText editAttd = (EditText) findViewById(R.id.empAttd);

        btnAttd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idd = 0;
                int attd = 0;
                if(!(editID.getText().toString().trim().isEmpty()) && !(editAttd.getText().toString().trim().isEmpty())){
                    idd = Integer.parseInt(editID.getText().toString());
                    attd = Integer.parseInt(editAttd.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(),"Fields cannot be empty",Toast.LENGTH_LONG).show();
                }

                records[idd-1].attendance = attd;

                emp1.setText("ID: " + idd);
                emp2.setText("Name: " + records[idd-1].name);
                emp3.setText("Attendance: " +records[idd-1].attendance);
            }
        });

        btnNotices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), Employee.notice, Toast.LENGTH_SHORT).show();
                emp1.setText("");
                emp2.setText("");
                emp3.setText("");
            }
        });

        btnPayroll.setOnClickListener(new View.OnClickListener() {
            int idd = 0;
            @Override
            public void onClick(View v) {
                if(!editID.getText().toString().trim().isEmpty()){
                    idd = Integer.parseInt(editID.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(),"ID cannot be empty",Toast.LENGTH_SHORT).show();
                }

                emp1.setText("ID: " + idd);
                emp2.setText("Name: " + records[idd-1].name);
                double payroll = (records[idd-1].attendance/100.0) * 100000;
                emp3.setText("Payroll: " + payroll);
            }
        });
    }
}
