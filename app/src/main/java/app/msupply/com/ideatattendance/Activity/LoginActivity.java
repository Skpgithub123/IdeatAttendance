package app.msupply.com.ideatattendance.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import app.msupply.com.ideatattendance.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Toast.makeText(this,"welcome",Toast.LENGTH_LONG).show();


    }
}
