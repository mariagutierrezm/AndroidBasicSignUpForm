package com.example.firstform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    //added a log?
    private static final String TAG = "MainActivity";
    //declare all UI elements
    private EditText editTxtName, editTxtEmail, editTxtPassword, editTxtPassword2;
    private Button btnUpload, btnSubmit;
    private TextView warnTxtView, warnTxtView2, warnTxtView3, warnTxtView4;
    private Spinner spinnerCountries;
    private RadioGroup radioGroupGender;
    private CheckBox checkBox;

    //initialize the constraint layout
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        //set up a click listener
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "kudos, image upload", Toast.LENGTH_SHORT).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //doing the task on another method
                initRegister();
            }
        });

    }


    private void initRegister() {
        Log.d(TAG, "initViews: Started");

        //if user added data, continue (data added using another method)
        if(validateData()) {
            if(checkBox.isChecked()){
                //if is checked we continue and show the snack bar
                showSnackBar();
            } else {
                //if not print a message
                Toast.makeText(this, "You need to agree to the licence agreement", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: Started");
        //first remove all warning texts
        warnTxtView.setVisibility(View.GONE);
        warnTxtView2.setVisibility(View.GONE);
        warnTxtView3.setVisibility(View.GONE);
        warnTxtView4.setVisibility(View.GONE);

        //change the text of the snackbar, first get the values of name, email, country and gender
        String name = editTxtName.getText().toString();
        String email = editTxtEmail.getText().toString();
        String country = spinnerCountries.getSelectedItem().toString();
        String gender = "";
        //for getting the users gender we create a switch statement
        switch (radioGroupGender.getCheckedRadioButtonId()) {
            case R.id.rbFemale:
                gender = "Female";
                break;
            case R.id.rbMale:
                gender = "Male";
            case R.id.rbOther:
                gender = "Not Specified";
                break;
            default: //unlikely to be used but written in case something goes wrong is for security purposes
                gender = "Unknown";
                break;
        }

        //change text
        String snackText = "Name: " + name + "\n" + "Email: " + email + "\n" +
                "Country: " + country + "\n" + "Gender: " + gender;

        //first pass the layout then the text, then the constant
        Snackbar.make(parent, snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //clear the edit text inputs
                        editTxtName.setText("");
                        editTxtEmail.setText("");
                        editTxtPassword.setText("");
                        editTxtPassword2.setText("");
                    }
                }).show();
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: Started");
        //if the inputs text are empty do following
        if(editTxtName.getText().toString().equals("")) {
            warnTxtView.setVisibility(View.VISIBLE);
            warnTxtView.setText("Please enter your name");
            return false;
        }

        if(editTxtEmail.getText().toString().equals("")) {
            warnTxtView2.setVisibility(View.VISIBLE);
            warnTxtView2.setText("Please enter your email");
            return false;
        }

        if(editTxtPassword.getText().toString().equals("")) {
            warnTxtView3.setVisibility(View.VISIBLE);
            warnTxtView3.setText("Please enter your password");
            return false;
        }

        if(editTxtPassword2.getText().toString().equals("")) {
            warnTxtView4.setVisibility(View.VISIBLE);
            warnTxtView4.setText("Confirm your password");
            return false;
        }

        //validate passwords match
        if(!editTxtPassword.getText().toString().equals(editTxtPassword2.getText().toString())) {
            warnTxtView4.setVisibility(View.VISIBLE);
            warnTxtView4.setText("Passwords do not match");
            return false;
        }

        return true; //means all data has been entered
    }

    private void initViews(){
        //add a log
        Log.d(TAG, "initViews: Started");
        //initialize all the UI items
        editTxtName = findViewById(R.id.txtName);
        editTxtEmail = findViewById(R.id.txtEmail);
        editTxtPassword = findViewById(R.id.txtPassword);
        editTxtPassword2 = findViewById(R.id.txtPassword2);

        btnUpload = findViewById(R.id.btn);
        btnSubmit = findViewById(R.id.btnSubmit);

        warnTxtView = findViewById(R.id.warnTextView);
        warnTxtView2 = findViewById(R.id.warnTextView2);
        warnTxtView3 = findViewById(R.id.warnTextView3);
        warnTxtView4 = findViewById(R.id.warnTextView4);

        spinnerCountries = findViewById(R.id.spinnerCountry);
        radioGroupGender = findViewById(R.id.radioGroup);
        checkBox = findViewById(R.id.checkBox);
        parent = findViewById(R.id.parent);



    }
}