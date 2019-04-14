package com.fwahyudianto.learn.volume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String STATE_RESULT = "state_result";
    EditText etLength, etWidth, etHeight;
    Button btnCalculate;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLength = findViewById(R.id.et_length);
        etWidth = findViewById(R.id.et_width);
        etHeight = findViewById(R.id.et_height);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

        if (savedInstanceState != null) {
            String strResultState = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(strResultState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String strLength = etLength.getText().toString().trim();
            String strWidth = etWidth.getText().toString().trim();
            String strHeight = etHeight.getText().toString().trim();

            boolean bIsEmpty = false;
            boolean bIsInvalidDouble = false;

            if (TextUtils.isEmpty(strLength)) {
                bIsEmpty = true;
                etLength.setError("Length Is Required!");
            }

            if (TextUtils.isEmpty(strWidth)) {
                bIsEmpty = true;
                etWidth.setError(("Width Is Required!"));
            }

            if (TextUtils.isEmpty(strHeight)) {
                bIsEmpty = true;
                etHeight.setError("Height Is Required!");
            }

            Double dblLength = parseDouble(strLength);
            Double dblWidth = parseDouble(strWidth);
            Double dblHeight = parseDouble(strHeight);

            if (dblLength == null) {
                bIsInvalidDouble = true;
                etLength.setError("Length Not Valid!");
            }

            if (dblWidth == null) {
                bIsInvalidDouble = true;
                etWidth.setError("Width Not Valid!");
            }

            if (dblHeight == null) {
                bIsInvalidDouble = true;
                etHeight.setError("Height Not Valid!");
            }

            if (!bIsEmpty && !bIsInvalidDouble) {
                double dblVolume = dblLength * dblWidth * dblHeight;
                tvResult.setText(String.valueOf(dblVolume));
            }
        }
    }

    Double parseDouble (String p_strValue) {
        try {
            return Double.valueOf(p_strValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
