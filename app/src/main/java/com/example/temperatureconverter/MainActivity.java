package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView1, textView2;
    ArrayAdapter<String> adapter;
    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView2;
    String[] temp1;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.black));
        Objects.requireNonNull(getSupportActionBar()).hide();

        temp1 = new String[]{"Celsius(°C)", "Fahrenheit(°F)", "Kelvin(K)", "Rankine(°R)"};
        adapter = new ArrayAdapter<>(this, R.layout.drop_down_item, temp1);
        autoCompleteTextView = findViewById(R.id.filled_temp);
        autoCompleteTextView2 = findViewById(R.id.filled_temp2);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView2.setAdapter(adapter);


        textView1 = findViewById(R.id.temp1);
        textView2 = findViewById(R.id.temp2);

        assign(R.id.convert);
        assign(R.id.button_c);
        assign(R.id.button_7);
        assign(R.id.button_8);
        assign(R.id.button_9);
        assign(R.id.button_4);
        assign(R.id.button_5);
        assign(R.id.button_6);
        assign(R.id.button_1);
        assign(R.id.button_2);
        assign(R.id.button_3);
        assign(R.id.button_0);
        assign(R.id.button_10);
        assign(R.id.button_decimal);
        assign(R.id.button_ac);

    }

    void assign(int id) {
        MaterialButton button = findViewById(id);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonTxt = button.getText().toString();
        String data = textView1.getText().toString();
        switch (buttonTxt) {
            case "AC":
                textView1.setText("");
                textView2.setText("");
                return;
            case "C":
                data = data.substring(0, data.length() - 1);
                break;
            case "+/-":
                double x = Double.parseDouble(data);
                x = x * -1;
                data = df.format(x);
                break;
            case "Convert":
                String text1 = autoCompleteTextView.getText().toString();
                String text2 = autoCompleteTextView2.getText().toString();
                textView1.setText(text1);
                if (text1.equals("")) {
                    break;
                }
                int select = -1;
                int select2 = -1;
                switch (text1) {
                    case "Celsius(°C)":
                        select = 1;
                        break;
                    case "Fahrenheit(°F)":
                        select = 2;
                        break;
                    case "Kelvin(K)":
                        select = 3;
                        break;
                    case "Rankine(°R)":
                        select = 4;
                        break;
                }
                switch (text2) {
                    case "Celsius(°C)":
                        select2 = 1;
                        break;
                    case "Fahrenheit(°F)":
                        select2 = 2;
                        break;
                    case "Kelvin(K)":
                        select2 = 3;
                        break;
                    case "Rankine(°R)":
                        select2 = 4;
                        break;
                }
                double temp1 = Double.parseDouble(data);
                if (select == 1) { //Celsius
                    if (select2 == 1) {
                        textView2.setText(data);//
                    } else if (select2 == 2) {
                        textView2.setText(C_F(temp1, 1)); //
                    } else if (select2 == 3) {
                        textView2.setText(C_K(temp1, 1)); //
                    } else if (select2 == 4) {
                        textView2.setText(C_R(temp1, 1)); //
                    }
                } else if (select == 2) {//Fahrenheit
                    if (select2 == 1) {
                        textView2.setText(C_F(temp1, 0)); //
                    } else if (select2 == 2) {
                        textView2.setText(data);//
                    } else if (select2 == 3) {
                        textView2.setText(F_K(temp1, 1)); //
                    } else if (select2 == 4) {
                        textView2.setText(F_R(temp1, 1));//
                    }
                } else if (select == 3) { //Kelvin
                    if (select2 == 1) {
                        textView2.setText(C_K(temp1, 0));
                    } else if (select2 == 2) {
                        textView2.setText(F_K(temp1, 0));
                    } else if (select2 == 3) {
                        textView2.setText(data);
                    } else if (select2 == 4) {
                        textView2.setText(K_R(temp1, 1));
                    }
                } else if (select == 4) { //Rankine
                    if (select2 == 1) {
                        textView2.setText(C_R(temp1, 0));
                    } else if (select2 == 2) {
                        textView2.setText(F_R(temp1, 0));
                    } else if (select2 == 3) {
                        textView2.setText(K_R(temp1, 0));
                    } else if (select2 == 4) {
                        textView2.setText(data);
                    }
                }
                break;
            default:
                data = data + buttonTxt;
                break;
        }
        textView1.setText(data);
    }

    public String K_R(double temp1, int i) {
        if (i == 1) {
            double R = temp1 * 9 / 5;
            return df.format(R);
        } else {
            double K = temp1 * 5 / 9;
            return df.format(K);
        }
    }

    public String F_R(double temp1, int i) {
        if (i == 1) {

            double R = temp1 + 459.67;
            return df.format(R);
        } else {

            double F = temp1 - 459.67;
            return df.format(F);
        }
    }

    public String F_K(double temp1, int i) {
        if (i == 1) {
            double K = (temp1 - 32) * 5 / 9 + 273.15;
            return df.format(K);
        } else {

            double F = (temp1 - 273.15) * 9 / 5 + 32;
            return df.format(F);
        }
    }

    public String C_R(double t, int i) {
        if (i == 1) {
            double R = ((t + 273.15) * 9 / 5);
            return df.format(R);
        } else {
            double C = (t * 5 / 9) - 273.15;
            return df.format(C);
        }
    }

    public String C_K(double t, int i) {
        if (i == 1) {
            double K = t + 273.15;
            return df.format(K);
        } else {
            double C = t - 273.15;
            return df.format(C);
        }
    }

    public String C_F(double tmp, int t) {
        if (t == 1) {
            double F = (tmp * 9 / 5) + 32;
            return df.format(F);
        } else {
            double C = (tmp - 32) * 5 / 9;
            return df.format(C);
        }
    }
}
