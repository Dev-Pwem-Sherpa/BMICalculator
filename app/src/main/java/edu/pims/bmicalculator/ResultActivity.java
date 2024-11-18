package edu.pims.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    TextView bmiTypeText;
    TextView bmiValuesText;
    TextView bmiMessageText;
    Button recalculateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        bmiTypeText = findViewById(R.id.bmiType);
        bmiValuesText = findViewById(R.id.bmiValues);
        bmiMessageText = findViewById(R.id.bmiMessage);
        recalculateBtn = findViewById(R.id.btnReCalcualateAgain);

        Intent intent = getIntent();
        float BMI = intent.getFloatExtra("BMI_RESULT",0);

//        Log.d("BMI_RESULT", "Received BMI: " + BMI);

        bmiValuesText.setText(String.format("%.2f",BMI));

        DisplayBmiCategory(BMI);

        recalculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recalcuate();
            }
        });
    }

    void DisplayBmiCategory(float BMI){
        String bmiType;
        String bmiMessage;
        int color;

        if(BMI < 18.5){
            bmiType = "UNDERWEIGHT";
            bmiMessage = "You are underweight. Please eat more!";
            color = getResources().getColor(R.color.colorUnderWeight);
        }
        else if(BMI >= 18.5 && BMI <= 24.9){
            bmiType = "NORMAL";
            bmiMessage = "You are fit. Keep up the good work!";
            color = getResources().getColor(R.color.colorNormal);
        }
        else if(BMI >= 25 && BMI <= 29.9){
            bmiType = "OVERWEIGHT";
            bmiMessage = "You are overweight. Exercise more!";
            color = getResources().getColor(R.color.colorOverWeight);
        }
        else {
            bmiType = "OBESE";
            bmiMessage = "You are extremely unhealthy. Consult a doctor!";
            color = getResources().getColor(R.color.colorObese);
        }

//        Log.d("BMI_CATEGORY", "BMI Type: " + bmiType + " Message: " + bmiMessage);

        bmiTypeText.setText(bmiType);
        bmiTypeText.setTextColor(color);
        bmiMessageText.setText(bmiMessage);
    }
    void recalcuate(){
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);
    }
}