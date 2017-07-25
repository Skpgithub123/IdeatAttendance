package app.msupply.com.ideatattendance.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import app.msupply.com.ideatattendance.CommonClass.CommonMethos;
import app.msupply.com.ideatattendance.CommonClass.ConnectionDetector;
import app.msupply.com.ideatattendance.R;

import static java.security.AccessController.getContext;

public class RegisterationActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText Mbl_number,et_otp,New_password,Confirm_password;
    private TextInputLayout inputLayout_Mblnumber, inputLayout_Otp,inputLayout_Newpassword,inputLayout_confirmpassword;

    TextView tv_registerpage;

    CommonMethos commonMethos;
    ConnectionDetector connectionDetector;

    Typeface regular,bold;

    TextView create_Profile;
    boolean isIconChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        commonMethos = new CommonMethos(this);

        connectionDetector = new ConnectionDetector(this);


        bold = Typeface.createFromAsset(getApplicationContext().getAssets(), "Lato-Bold.ttf");
        regular = Typeface.createFromAsset(getApplicationContext().getAssets(), "Lato-Regular.ttf");



        inputLayout_Mblnumber = (TextInputLayout) findViewById(R.id.input_layout_mbl);
        inputLayout_Otp = (TextInputLayout) findViewById(R.id.input_layout_otp);
        inputLayout_Newpassword = (TextInputLayout) findViewById(R.id.input_layout_newpassword);
        inputLayout_confirmpassword = (TextInputLayout) findViewById(R.id.input_layout_confirmpassword);



        tv_registerpage  = (TextView) findViewById(R.id.tv_registerpage);


        Mbl_number = (EditText) findViewById(R.id.et_input_mblno);
        et_otp = (EditText) findViewById(R.id.et_input_otp);
        New_password = (EditText) findViewById(R.id.et_input_newpassword);
        Confirm_password = (EditText) findViewById(R.id.et_input_confirmpassword);

        create_Profile = (TextView) findViewById(R.id.create_Profile);

        Mbl_number.addTextChangedListener(new MyTextWatcher(Mbl_number));
        et_otp.addTextChangedListener(new MyTextWatcher(et_otp));
        New_password.addTextChangedListener(new MyTextWatcher(New_password));
        Confirm_password.addTextChangedListener(new MyTextWatcher(Confirm_password));

        tv_registerpage.setOnClickListener(this);

        Mbl_number.setTypeface(regular);
        et_otp.setTypeface(regular);
        New_password.setTypeface(regular);
        Confirm_password.setTypeface(regular);
        tv_registerpage.setTypeface(bold);
        create_Profile.setTypeface(bold);

    }

    @Override
    public void onClick(View view) {


        switch (view.getId())
        {
            case R.id.tv_registerpage:


                submitForm();

            /*    Log.d("checkboolean","status"+!validateOTP());
                Log.d("checkboolean","!validateMblNumber()"+!validateMblNumber());

                if(!validateMblNumber())
                {
                    Log.d("validmblno","Notavalid");
                    return;
                }else if(!validateOTP())
                {
                    Log.d("validmblno","success");
                }*/




                break;
        }


    }
    public  void  submitForm()
    {

      //  Log.d("checkboolean","status"+     validateOTP());

      //  Log.d("checkboolean","!validateMblNumber()"+    validateMblNumber());


     //   Log.d("checkboolean","status"+     !validateOTP());
      //  Log.d("checkboolean","!validateMblNumber()"+    !validateMblNumber());



        if(!validateMblNumber())
        {
            Log.d("validmblno","Notavalid"+!validateMblNumber());
            return;
        }else if(!validateOTP())
        {
            Log.d("validmblno","success");
            return;
        }else if (!validatepassword())
        {
            commonMethos.showErrorMessage("Passwordmismatch", getResources().getString(R.string.error_login_short_password));
            Log.d("validmblno","validatepassword");
            return;
        }else if (!validateConfirpassword())
        {
            commonMethos.showErrorMessage("Passwordmismatch", getResources().getString(R.string.error_login_short_password));
            Log.d("validmblno","validatepassword");
            return;
        }else if (!(New_password.getText().toString().trim().equals(Confirm_password.getText().toString().trim())))
        {
            commonMethos.showErrorMessage("Passwordmismatch", getResources().getString(R.string.error_sign_up_password_mismatch));
            return;
        }else
        {
            commonMethos.displayToast("Login Success");
        }


    }
    private boolean validateOTP()
    {
        String OTP = et_otp.getText().toString().trim();
        if ((OTP.isEmpty() ||  !isOTP(OTP)))
        {
            inputLayout_Otp.setError(getString(R.string.err_enterotp));
            requestFocus(et_otp);
            return false;
        }else
        {
            inputLayout_Otp.setErrorEnabled(false);
        }
        return true;
    }


  private  boolean validatepassword()
  {

      String str_password = New_password.getText().toString().trim();

      Log.d("validpasswrods","displayhere"+isValidPass(str_password));

      if (str_password.isEmpty() || !isValidPass(str_password)) {

          Log.d("notvalidmbdl","error");
          inputLayout_Newpassword.setError(getString(R.string.err_validpassword));
          requestFocus(New_password);
          return false;
      }else {

          inputLayout_Newpassword.setErrorEnabled(false);
      }


      return true;
  }

    private  boolean validateConfirpassword()
    {

        String str_confirmpassword = Confirm_password.getText().toString().trim();

        if (str_confirmpassword.isEmpty() || !isValidPass(str_confirmpassword)) {

            Log.d("notvalidmbdl","error");
            inputLayout_confirmpassword.setError(getString(R.string.err_validConfirmPassword));
            requestFocus(Confirm_password);
            return false;
        }else {

            inputLayout_confirmpassword.setErrorEnabled(false);
        }


        return true;
    }


    private boolean validateMblNumber() {
        String mobile_number = Mbl_number.getText().toString().trim();

        if (mobile_number.isEmpty() || !isValidMbl(mobile_number)) {

            Log.d("notvalidmbdl","error");
            inputLayout_Mblnumber.setError(getString(R.string.err_msg_email));
            requestFocus(Mbl_number);
            Mbl_number.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            return false;
        }else
        {

            Log.d("notvalidmbdl","elseLoop");
         /*   inputLayout_Mblnumber.setError(null);//removes error
            inputLayout_Mblnumber.clearFocus();
            inputLayout_Mblnumber.setFocusable(false);*/
            inputLayout_Mblnumber.setErrorEnabled(false);

            Mbl_number.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            Mbl_number.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_check_circle_black_18dp,0);

            /*Drawable img = RegisterationActivity.this.getResources().getDrawable( R.drawable.dpass);
            img.setBounds( 0, 0, 50, 50 );
            Mbl_number.setCompoundDrawables( img, null, null, null );*/

/*
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

            Log.d("notvalidmbdl","elseLoop");
            inputLayout_Mblnumber.setError(null);//removes error
            inputLayout_Mblnumber.clearFocus();
            inputLayout_Mblnumber.setFocusable(false);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterationActivity.this);

            // Setting Dialog Title
            alertDialog.setTitle("SENT SMS OTP...");

            alertDialog.setCancelable(false);
            // Setting Dialog Message
            alertDialog.setMessage("Are you sure allow to sent OTP");

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.ph_user);

            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    // Write your code here to invoke YES event
                   // Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to invoke NO event
                   // Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });

            // Showing Alert Message
            alertDialog.show();*/


        }
        return true;

    }


    private static boolean isValidMbl(String mbl) {
        return !TextUtils.isEmpty(mbl) && mbl.length()==10;
    }

    private static boolean isValidPass(String mbl) {
        return !TextUtils.isEmpty(mbl) && mbl.length() >=4;
    }

    private static boolean isOTP(String otp) {
        return !TextUtils.isEmpty(otp) && otp.length()==4;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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

                case R.id.et_input_mblno:

                    Log.d("values","aftertextchecdcalling");
                     validateMblNumber();
                    break;
                case R.id.et_input_otp:

                    validateOTP();
                    break;

                case R.id.et_input_newpassword:

                    validatepassword();
                    break;

                case  R.id.et_input_confirmpassword:

                    validateConfirpassword();
                    break;
            }
        }
    }
}
