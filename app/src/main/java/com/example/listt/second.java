package com.example.listt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class second extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView first, jump, n, sn, typetxt;
    double x1, d;
    Intent gi;
    boolean type;
    ListView lv;
    String[] series = new String[20];
    double[] nums = new double[20];
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        first = findViewById(R.id.x1);
        jump = findViewById(R.id.d);
        typetxt = findViewById(R.id.type);
        n = findViewById(R.id.n);
        sn = findViewById(R.id.sn);
        lv = findViewById(R.id.series);
        back = findViewById(R.id.back);


        gi = getIntent();
        x1 = gi.getDoubleExtra("a1", 0);
        d = gi.getDoubleExtra("d", 0);
        type = gi.getBooleanExtra("type", false);


        nums[0] = x1;
        series[0] = String.valueOf(x1);
        for (int i = 1; i < 20; i++) {
            if (type == false) {
                nums[i] = nums[i - 1] + d;
                series[i] = String.valueOf(nums[i]);
            } else {
                nums[i] = nums[i - 1] * d;
                series[i] = String.valueOf(nums[i]);
            }


        }


        first.setText("x1 = " + x1);
        jump.setText("d = " + d);
        if (type == false) typetxt.setText("Arithmetic");
        else typetxt.setText("Geometric");

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, series);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        n.setText("n = " + nums[position]);
        if (type == true) {
            sn.setText("Sn = " + sumOfGP(x1, d, position + 1));
        } else {
            sn.setText("Sn = " + sumOfAP(x1, d, position + 1));
        }


    }


    static double sumOfGP(double a, double r, int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + a;
            a = a * r;
        }
        return sum;
    } // driver function public static void main(String args[]) { int a = 1; // first term float r = (float)(1/2.0) ;// common ratio int n = 10 ; // number of terms System.out.printf("%.5f",(sumOfGP(a, r, n))); } } //This code is contributed by Nikita Tiwari

    static double sumOfAP(double a, double d,
                          int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + a;
            a = a + d;
        }
        return sum;
    }

    public void backk(View view) {
        gi.putExtra("first", x1);
        gi.putExtra("jump", d);
        gi.putExtra("type", type);
        setResult(RESULT_OK, gi);
        finish();

    }
}