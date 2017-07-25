package app.msupply.com.ideatattendance.CommonClass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

import app.msupply.com.ideatattendance.Activity.LoginActivity;
import app.msupply.com.ideatattendance.R;

/**
 * Created by Zest Developer on 7/20/2017.
 */

public class CommonMethos {

    private static Context context;

    public CommonMethos(Context c) {
        context = c;
    }


    public void displayToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void Calling_LoginActivity()
    {

        Intent myIntent = new Intent(context, LoginActivity.class);
      //  myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(myIntent);

    }

    public void showErrorMessage(String title,String message) {

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(
                new ContextThemeWrapper(context, R.style.popup_theme));

        // dlgAlert.setTitle(title);
        dlgAlert.setMessage(message);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

}

