package com.maven.inanay;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class PasswordActivity extends AppCompatActivity {

    EditText oldpass, newpass, repass;
    Button button5, button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        oldpass = (EditText)findViewById(R.id.oldpass);
        newpass = (EditText)findViewById(R.id.newpass);
        repass = (EditText)findViewById(R.id.repass);
        button2 = (Button)findViewById(R.id.button2);
        button5 = (Button)findViewById(R.id.button5);
    }

    public void canclick(View view){
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void okclick(View view){
        String oldpasttyped = oldpass.getText().toString();
        String newpasstyped = newpass.getText().toString();
        String repasstyped = repass.getText().toString();

        SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);
        String oldpass0 = shared.getString("password", "0");

        if(Objects.equals(oldpass0, oldpasttyped) && Objects.equals(newpasstyped, repasstyped)){
            SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("password", newpasstyped);
            editor.apply();

            Toast.makeText(PasswordActivity.this,"Password Changed",Toast.LENGTH_SHORT).show();

            finish();
        } else{
            if(!Objects.equals(oldpass0, oldpasttyped)){
                oldpass.setTextColor(R.color.red);
            } else{
                oldpass.setTextColor(R.color.black);
            }

            if(!Objects.equals(newpasstyped, repasstyped)){
                newpass.setTextColor(R.color.red);
                repass.setTextColor(R.color.red);
            } else{
                newpass.setTextColor(R.color.black);
                repass.setTextColor(R.color.black);
            }
        }

    }
}
