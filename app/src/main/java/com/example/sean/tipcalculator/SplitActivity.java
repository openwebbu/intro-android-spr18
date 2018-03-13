package com.example.sean.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplitActivity extends AppCompatActivity {
    private EditText numEditTxt;
    private TextView billTxt, tipTxt, totalTxt;
    private Button doneBtn;

    private double billTotal, tipTotal, finalTotal;
    private int numPeople;
    private double billPerPerson, tipPerPerson, totalPerPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);

        // get bill total/tip total passed from the original activity
        billTotal = getIntent().getExtras().getDouble("billTotal");
        tipTotal = getIntent().getExtras().getDouble("tip");
        finalTotal = getIntent().getExtras().getDouble("finalTotal");
        numPeople = 1;      // initialize to just 1 person

        // bind components
        numEditTxt = findViewById(R.id.numEditTxt);
        billTxt = findViewById(R.id.billTxt);
        tipTxt = findViewById(R.id.tipTxt);
        totalTxt = findViewById(R.id.totalTxt);
        doneBtn = findViewById(R.id.doneBtn);

        // calculate initial split with one person
        updateViews();

        numEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String numEditTxtContents = charSequence.toString();

                // check if user has entered anything into the editText field yet
                if(numEditTxtContents.equals("")){
                    numPeople = 1;
                } else {
                    numPeople = Integer.parseInt(numEditTxtContents);
                }

                updateViews();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // if user is done, go back to main activity
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * Calculates the bill, tip and final total per person and updates the views.
     */
    public void updateViews() {
        billPerPerson = billTotal / numPeople;
        tipPerPerson = tipTotal / numPeople;
        totalPerPerson = finalTotal / numPeople;

        billTxt.setText("From bill (per person): $" + String.format("%.2f", billPerPerson));
        tipTxt.setText("Tip (per person): $" + String.format("%.2f", tipPerPerson));
        totalTxt.setText("Total (per person): $" + String.format("%.2f", totalPerPerson));
    }
}
