package app.msupply.com.ideatattendance;

import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import app.msupply.com.ideatattendance.CommonClass.CommonMethos;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView backgroundOne,backgroundtwo;

    TextView tv_ideaemployee,tv_ideaadmin,tv_ideafsedevice;

    Typeface bold,regular,thin;

    CommonMethos commonMethos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        commonMethos  = new CommonMethos(this);

        bold = Typeface.createFromAsset(getApplicationContext().getAssets(), "Lato-Bold.ttf");
        regular = Typeface.createFromAsset(getApplicationContext().getAssets(), "Lato-Regular.ttf");
        thin = Typeface.createFromAsset(getApplicationContext().getAssets(), "Roboto-Thin.ttf");

        backgroundOne = (ImageView) findViewById(R.id.movieimage);
        backgroundtwo = (ImageView) findViewById(R.id.movieimage2);

        tv_ideaemployee = (TextView) findViewById(R.id.tv_ideaemployee);
        tv_ideaadmin = (TextView) findViewById(R.id.tv_ideaadmin);
        tv_ideafsedevice = (TextView) findViewById(R.id.tv_ideafsedevice);

        tv_ideaemployee.setAlpha((float) 0.5);
        tv_ideaadmin.setAlpha((float) 0.5);
        tv_ideafsedevice.setAlpha((float) 0.5);


        tv_ideaemployee.setTypeface(bold);
        tv_ideaadmin.setTypeface(bold);
        tv_ideafsedevice.setTypeface(bold);

        tv_ideaemployee.setOnClickListener(this);
        tv_ideaadmin.setOnClickListener(this);
        tv_ideafsedevice.setOnClickListener(this);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(30000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundtwo.setTranslationX(translationX - width);
            }
        });
        animator.start();

    }

    @Override
    public void onClick(View view) {


        switch (view.getId())
        {
            case R.id.tv_ideaemployee:
            {
                commonMethos.Calling_LoginActivity();
            }
            case R.id.tv_ideaadmin:
            {
                commonMethos.Calling_LoginActivity();
            }
            case R.id.tv_ideafsedevice:
            {
                commonMethos.Calling_LoginActivity();
            }
        }

    }
}
