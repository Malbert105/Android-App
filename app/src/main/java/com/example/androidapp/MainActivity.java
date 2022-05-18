package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private EditText editTextName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private  Button galleryButton,buttonRegister;
    private Spinner spinnerCountries;
    private CheckBox checkBoxAgree;
    private TextView textWarningName,textWarningEmail, textWarningPassword, textWarningConfirmPassowrd;
    private ConstraintLayout parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        galleryButton.setOnClickListener(this::galleryButtonClick);
        buttonRegister.setOnClickListener(this::buttonRegisterClick);
        buttonRegister.setOnClickListener(this::buttonRegisterClick);
    }

    private void galleryButtonClick (View view){
        Toast.makeText(this, "Ready to pick an image", Toast.LENGTH_SHORT).show();

    }
    private void buttonRegisterClick (View view){
        initRegister();
    }
    private void initRegister(){
        Log.d(TAG,"initRegister:Started");

        if (validateData()){
            if (checkBoxAgree.isChecked()){
                showSnackBar();
            }
            else {
                Toast.makeText(this, "Please Agree on our Policy to continue", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void showSnackBar(){
        Log.d(TAG,"showSnackBar: Started");
        textWarningName.setVisibility(View.GONE);
        textWarningEmail.setVisibility(View.GONE);
        textWarningPassword.setVisibility(View.GONE);
        textWarningConfirmPassowrd.setVisibility(View.GONE);

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String country = spinnerCountries.getSelectedItem().toString();
        String gender = "";

        switch (radioGroupGender.getCheckedRadioButtonId()){
            case R.id.radioButtonMale:
                gender = "Male";
                break;

            case R.id.radioButtonFemale:
                gender = "Female";
                break ;

            default:
                gender = "Unknown";
                break;
        }

        String snackText = "Name "+ name + "\n" +
                "Email "+email + "\n"+
                "Country "+country + "\n" +
                "Gender "+gender ;

        Log.d(TAG,"showSnackBar:" + snackText);

        Snackbar.make(parent,snackText  , Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editTextName.setText("");
                        editTextEmail.setText("");
                        editTextPassword.setText("");
                        editTextConfirmPassword.setText("");

                    }
                }).show();

    }
    private boolean validateData(){
        Log.d(TAG,"validateData:Started");
        if (editTextName.getText().toString().equals("")){
            textWarningName.setVisibility(View.VISIBLE);
            textWarningName.setText("Enter Your Name");
            return false;
        }
        if (editTextEmail.getText().toString().equals("")){
            textWarningEmail.setVisibility(View.VISIBLE);
            textWarningEmail.setText("Enter your Email");
            return false;
        }
        if (editTextPassword.getText().toString().equals("")){
            textWarningPassword.setVisibility(View.VISIBLE);
            textWarningPassword.setText("Enter Your Password");
            return false;
        }
        if (editTextConfirmPassword.getText().toString().equals("")){
            textWarningConfirmPassowrd.setVisibility(View.VISIBLE);
            textWarningConfirmPassowrd.setText("Re-enter Your Password");
            return false;
        }
        if (!editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())){
            textWarningConfirmPassowrd.setVisibility(View.VISIBLE);
            textWarningConfirmPassowrd.setText("Your Password does not match");
            return false;
        }
        return true;
    }
    private void initViews(){

        Log.d(TAG,"initViews : Started");
//textboxes
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editEmail);
        editTextPassword = findViewById(R.id.editPassowrd);
        editTextConfirmPassword = findViewById(R.id.editConfirmPassword);
//radio buttons
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioButtonMale = findViewById(R.id.radioButtonMale);
//Buttons
        galleryButton = findViewById(R.id.galleryButton);
        buttonRegister = findViewById(R.id.buttonRegister);
        //Spinner
        spinnerCountries =findViewById(R.id.spinnerCountries);

//Checkboxes
        checkBoxAgree = findViewById(R.id.checkBoxAgree);
// Warnings
        textWarningName = findViewById(R.id.textWarningName);
        textWarningEmail =findViewById(R.id.textWarningEmail);
        textWarningPassword =findViewById(R.id.textWarningPassword);
        textWarningConfirmPassowrd= findViewById(R.id.textWarningConfirmPassword);

//Constraint layout
        parent= findViewById(R.id.parent);



    }




}