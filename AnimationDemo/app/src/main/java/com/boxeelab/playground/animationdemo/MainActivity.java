package com.boxeelab.playground.animationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Integer totalTranslation  = 0;

    @Override
    protected void onResume() {
        super.onResume();
        Button btn = (Button)findViewById(R.id.btnAnimation);
        btn.animate().setDuration(0).translationYBy(totalTranslation);
        Toast.makeText(this,totalTranslation.toString(),Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this,totalTranslation.toString(),Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        totalTranslation = savedInstanceState.getInt("ANIM");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ANIM", totalTranslation);


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Slide out animation
    public void onClick(View view)
    {
        Animation animation = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);

        animation.setDuration(300);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
        view.setVisibility(View.INVISIBLE);
    }


    // Slide out animation
    public void onClickMove(View view)
    {

        view.animate().translationYBy(400).setInterpolator(new OvershootInterpolator(2)).setDuration(300);
        totalTranslation = totalTranslation + 400;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickObjectAnimator(View view)
    {
        AnimatorSet animationSet = new AnimatorSet();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"alpha",.5f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view,"SCALE_X",.6f);
        objectAnimator.setDuration(300);
        animationSet.playTogether(objectAnimator,objectAnimator1);
        objectAnimator.start();
    }
}
