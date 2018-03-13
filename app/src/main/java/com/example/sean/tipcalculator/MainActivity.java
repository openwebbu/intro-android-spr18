package com.example.sean.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText totalEditTxt;
    private SeekBar tipSeekBar;
    private TextView tipPercentTxt, tipTxt, totalTxt;
    private Button splitBtn;

    // values used for computation
    private double billTotal;
    private double tip;
    private double finalTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize bill total and tip values
        billTotal = 0;
        tip = 18;

        // bind components
        totalEditTxt = findViewById(R.id.totalEditTxt);
        tipSeekBar = findViewById(R.id.tipSeekBar);
        tipPercentTxt = findViewById(R.id.tipPercentTxt);
        tipTxt = findViewById(R.id.tipTxt);
        totalTxt = findViewById(R.id.totalTxt);
        splitBtn = findViewById(R.id.splitBtn);

        // when the SeekBar changes, update TextViews
        tipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                String totalEditTxtContents = totalEditTxt.getText().toString();
                updateTextViews(totalEditTxtContents, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // when the bill total EditText changes, update TextViews
        totalEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateTextViews(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // when the split button is called, start a new intent to the SplitActivity
        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent splitIntent = new Intent(getApplicationContext(), SplitActivity.class);
                splitIntent.putExtra("billTotal", billTotal);
                splitIntent.putExtra("finalTotal", finalTotal);
                splitIntent.putExtra("tip", tip);

                startActivity(splitIntent);
            }
        });
    }

    /**
     * Updates Text Views when the bill total EditText has been changed.
     *
     * @param editTxtContent
     */
    public void updateTextViews(String editTxtContent) {
        // check if user has entered anything into the editText field yet
        if(editTxtContent.equals("")){
            billTotal = 0;
        } else {
            billTotal = Double.parseDouble(editTxtContent);
        }

        // update the total
        finalTotal = tip + billTotal;
        tipTxt.setText("Tip: $"+ String.format("%.2f", tip));
        totalTxt.setText("Total: $" + String.format("%.2f", finalTotal));
    }

    /**
     * Updates Text Views when the tip SeekBar has been changed.
     *
     * @param editTxtContent
     * @param progress
     */
    public void updateTextViews(String editTxtContent, int progress) {
        // check if user has entered anything into the editText field yet
        if(editTxtContent.equals("")){
            billTotal = 0;
        } else {
            billTotal = Double.parseDouble(editTxtContent);
        }

        // calculate the tip
        tip = (progress / 100.0) * billTotal;
        finalTotal = tip + billTotal;

        // update the tip and total
        tipPercentTxt.setText("Tip: " + progress + "%");
        tipTxt.setText("Tip: $"+ String.format("%.2f", tip));
        totalTxt.setText("Total: $" + String.format("%.2f", finalTotal));
    }
}
