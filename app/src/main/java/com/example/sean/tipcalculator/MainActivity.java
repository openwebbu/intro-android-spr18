package com.example.sean.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText totalEditTxt;
    private SeekBar tipSeekBar;
    private TextView tipPercentTxt, tipTxt, totalTxt;
    private Button splitBtn;

    private double billTotal;
    private double tip;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billTotal = 0;
        tip = 18;

        totalEditTxt = findViewById(R.id.totalEditTxt);
        tipSeekBar = findViewById(R.id.tipSeekBar);
        tipPercentTxt = findViewById(R.id.tipPercentTxt);
        tipTxt = findViewById(R.id.tipTxt);
        totalTxt = findViewById(R.id.totalTxt);
        splitBtn = findViewById(R.id.splitBtn);

        tipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                String totalEditTxtContents = totalEditTxt.getText().toString();
                // check if user has entered anything into the editText field yet
                if(totalEditTxtContents.equals("")){
                    billTotal = 0;
                } else {
                    billTotal = Double.parseDouble(totalEditTxtContents);
                }
                tip = (progress / 100.0) * billTotal;
                total = tip + billTotal;

                tipPercentTxt.setText("Tip: " + progress + "%");
                tipTxt.setText("Tip: $"+ tip);
                totalTxt.setText("Total: $" + total);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        totalEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String totalEditTxtContents = charSequence.toString();
                // check if user has entered anything into the editText field yet
                if(totalEditTxtContents.equals("")){
                    billTotal = 0;
                } else {
                    billTotal = Double.parseDouble(totalEditTxtContents);
                }
//                tip = (progress / 100.0) * billTotal;
                total = tip + billTotal;

//                tipPercentTxt.setText("Tip: " + progress + "%");
                tipTxt.setText("Tip: $"+ tip);
                totalTxt.setText("Total: $" + total);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
