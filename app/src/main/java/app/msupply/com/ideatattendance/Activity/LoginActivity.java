package app.msupply.com.ideatattendance.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import app.msupply.com.ideatattendance.CommonClass.CommonMethos;
import app.msupply.com.ideatattendance.CommonClass.ConnectionDetector;
import app.msupply.com.ideatattendance.MainActivity;
import app.msupply.com.ideatattendance.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    TextView tv_signup,tv_forgot;

    TextView tv_login;

    EditText et_mbl,et_password;


    CommonMethos commonMethos;
    ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        commonMethos = new CommonMethos(this);

        connectionDetector = new ConnectionDetector(this);



        tv_login  = (TextView) findViewById(R.id.tv_login);

        tv_signup = (TextView) findViewById(R.id.tv_signup);
        tv_forgot = (TextView) findViewById(R.id.tv_forgotpass);



        et_mbl    = (EditText) findViewById(R.id.et_mbl);
        et_password   = (EditText) findViewById(R.id.et_password);
        et_mbl.addTextChangedListener(new MyTextWatcher(et_mbl));
        et_password.addTextChangedListener(new MyTextWatcher(et_password));


        tv_signup.setOnClickListener(this);
        tv_forgot.setOnClickListener(this);
        tv_login.setOnClickListener(this);

        et_mbl.addTextChangedListener(new MyTextWatcher(et_mbl));

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.tv_signup:
            {

                Intent intent = new Intent(LoginActivity.this,RegisterationActivity.class);
             //   intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);


                 break;
            }
            case R.id.tv_forgotpass:
            {




                break;

            }

            case R.id.tv_login:
            {

                if (!validateMblNumber())
                {
                    Log.d("=====","validatemblno");
                    return;
                }

                else if (!validatePassword())
                {
                    Log.d("=====","passwordexectue");

                    return;
                }else
                {

                    Log.d("=====","suceesss");

                    Log.d("toast","****   ");
                    Login_Server();
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);

                    commonMethos.displayToast("Login Successfully");
                }




                break;

            }


        }
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private boolean validateMblNumber() {
        String mobile_number = et_mbl.getText().toString().trim();

        Log.d("isvalidmblnumber","****   "+isValidMbl(mobile_number));

        if (mobile_number.isEmpty() || !isValidMbl(mobile_number)) {
            et_mbl.setError(getString(R.string.err_msg_email));
            requestFocus(et_mbl);
            return false;
        }else
        {
            et_mbl.setFocusable(false);
        }

        return true;
    }
    private static boolean isValidMbl(String mbl) {
        return !TextUtils.isEmpty(mbl) && mbl.length()==10;
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.et_mbl:
                    validateMblNumber();
                    break;
                case R.id.et_password:
                     validatePassword();
                    break;
            }
        }
    }


    private boolean validatePassword() {

        if (et_password.getText().toString().trim().isEmpty() || isValid_Pass(et_password.getText().toString().trim()) ) {
            et_password.setError(getString(R.string.err_msg_password));
            requestFocus(et_password);
            return false;
        } /*else {
            et_password.setError("");
        }*/

        return true;
    }

    private  boolean isValid_Pass(String password_length)
    {
        return  !TextUtils.isEmpty(password_length) && password_length.length()==4;
    }

    public void Login_Server()
    {



    }
}
