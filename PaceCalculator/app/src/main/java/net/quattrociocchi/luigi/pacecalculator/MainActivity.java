package net.quattrociocchi.luigi.pacecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    float TimeM;
    float PaceM;
    float TimeS;
    float PaceS;
    float Distance;

    float distMultiplier = 1;
    float paceMultiplier = 1;

    EditText timeInputM;
    EditText paceInputM;
    EditText timeInputS;
    EditText paceInputS;
    EditText distanceInput;

    Button btnTime;
    Button btnDistance;
    Button btnPace;
    ToggleButton swDistance;
    Spinner swPace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeInputM = findViewById(R.id.timeInputM);
        paceInputM = findViewById(R.id.paceInputM);
        timeInputS = findViewById(R.id.timeInputS);
        paceInputS = findViewById(R.id.paceInputS);
        distanceInput = findViewById(R.id.distanceInput);


        Toast.makeText(MainActivity.this, "Pace Calculator v1.2", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "Created by: Luigi Q.", Toast.LENGTH_SHORT).show();



        swPace = findViewById(R.id.swPace);
        swPace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (parent.getSelectedItem().toString().equals("km")) {
                    paceMultiplier = 1;

                }

                if (parent.getSelectedItem().toString().equals("400m")) {
                    paceMultiplier = (float) 0.4;

                }

                if (parent.getSelectedItem().toString().equals("mi")) {
                    paceMultiplier = (float) 1.60934;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swDistance = findViewById(R.id.swDistance);
        swDistance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    distMultiplier = (float) 1.60934;

                }
                if (!(isChecked)) {
                    distMultiplier = 1;

                }

                EditText editText = findViewById(R.id.distanceInput);
                if (distanceInput.getText().toString().equals("")) {
                    editText.setText("0");

                } else {
                    if (isChecked) {
                        if ((Math.floor(Float.valueOf(distanceInput.getText().toString())) != 0) || ((Float.valueOf(distanceInput.getText().toString())) < 1)) {
                            editText.setText("" + String.format("%.2f",(Float.valueOf(distanceInput.getText().toString()) / 1.60934)));

                        } else {
                            editText.setText("" + String.format("%.0f",(Float.valueOf(distanceInput.getText().toString()) / 1.60934)));

                        }

                    } else {
                        if ((Math.floor(Float.valueOf(distanceInput.getText().toString())) != 0) || ((Float.valueOf(distanceInput.getText().toString())) < 1)) {
                            editText.setText("" + String.format("%.2f",(Float.valueOf(distanceInput.getText().toString()) / 0.6215)));

                        } else {
                            editText.setText("" + String.format("%.0f",(Float.valueOf(distanceInput.getText().toString()) / 0.6215)));

                        }

                    }

                }

            }
        });


        btnTime = findViewById(R.id.btnTime);
        btnTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (PaceM < 0 || PaceS < 0 || Distance < 0) {
                    Toast.makeText(getApplicationContext(), "Number error", Toast.LENGTH_LONG).show();

                }

                if (paceInputM.getText().toString().equals("")) {
                    PaceM = 0;

                } else {
                    PaceM = Float.valueOf(paceInputM.getText().toString());

                }

                if (paceInputS.getText().toString().equals("")) {
                    PaceS = 0;

                } else {
                    PaceS = Float.valueOf(paceInputS.getText().toString());

                }

                if (distanceInput.getText().toString().equals("")) {
                    Distance = 0;

                } else {
                    Distance = Float.valueOf(distanceInput.getText().toString());

                }

                EditText editText = findViewById(R.id.timeInputM);
                editText.setText("" + String.format("%.0f", Math.floor((PaceM + (PaceS / 60)) * (Distance * distMultiplier / paceMultiplier))));
                editText = findViewById(R.id.timeInputS);
                editText.setText("" + String.format("%.1f", (((PaceM + (PaceS / 60)) * (Distance * distMultiplier / paceMultiplier)) - (Math.floor((PaceM + (PaceS / 60)) * (Distance * distMultiplier / paceMultiplier)))) * 60));


            }
        });


        btnDistance = findViewById(R.id.btnDistance);
        btnDistance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (TimeM < 0 || TimeS < 0 || PaceM < 0 || PaceS < 0) {
                    Toast.makeText(getApplicationContext(), "Number error", Toast.LENGTH_LONG).show();

                }

                if (paceInputM.getText().toString().equals("")) {
                    PaceM = 0;

                } else {
                    PaceM = Float.valueOf(paceInputM.getText().toString());

                }

                if (paceInputS.getText().toString().equals("")) {
                    PaceS = 0;

                } else {
                    PaceS = Float.valueOf(paceInputS.getText().toString());

                }

                if (timeInputM.getText().toString().equals("")) {
                    TimeM = 0;

                } else {
                    TimeM = Float.valueOf(timeInputM.getText().toString());

                }

                if (timeInputS.getText().toString().equals("")) {
                    TimeS = 0;

                } else {
                    TimeS = Float.valueOf(timeInputS.getText().toString());


                }

                if (PaceM == 0 & PaceS == 0) {
                    EditText editText = findViewById(R.id.distanceInput);
                    editText.setText("0");

                } else {
                    EditText editText = findViewById(R.id.distanceInput);
                    editText.setText("" + String.format("%.2f",((TimeM + TimeS / 60) / (PaceM + PaceS / 60)) / distMultiplier * paceMultiplier));
                }

            }
        });

        btnPace = findViewById(R.id.btnPace);
        btnPace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (TimeM < 0 || TimeS < 0 || Distance < 0) {
                    Toast.makeText(getApplicationContext(), "Number error", Toast.LENGTH_LONG).show();

                }

                if (distanceInput.getText().toString().equals("")) {
                    Distance = 0;

                } else {
                    Distance = Float.valueOf(distanceInput.getText().toString());

                }

                if (timeInputM.getText().toString().equals("")) {
                    TimeM = 0;

                } else {
                    TimeM = Float.valueOf(timeInputM.getText().toString());

                }

                if (timeInputS.getText().toString().equals("")) {
                    TimeS = 0;

                } else {
                    TimeS = Float.valueOf(timeInputS.getText().toString());

                }

                if (Distance == 0) {
                    EditText editText = findViewById(R.id.paceInputM);
                    editText.setText("0");
                    editText = findViewById(R.id.paceInputS);
                    editText.setText("0");

                } else {
                    EditText editText = findViewById(R.id.paceInputM);
                    editText.setText("" + String.format("%.0f", Math.floor(TimeM / (Distance * distMultiplier / paceMultiplier) + (TimeS / (Distance * distMultiplier / paceMultiplier)) / 60)));
                    editText = findViewById(R.id.paceInputS);
                    editText.setText("" + String.format("%.1f", ((TimeM / (Distance * distMultiplier / paceMultiplier) + (TimeS / (Distance * distMultiplier / paceMultiplier)) / 60)%1 * 60)));

                }
            }
        });

    }
}