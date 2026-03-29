package zargaryan.vahagn.evidentreader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo_image);

        Animation pop = AnimationUtils.loadAnimation(this, R.anim.splash_pop);
        logo.startAnimation(pop);

        pop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Плавное исчезновение
                Animation fade = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_out);
                logo.startAnimation(fade);

                fade.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) { }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        Intent intent = (auth.getCurrentUser() != null)
                                ? new Intent(SplashActivity.this, MainActivity.class)
                                : new Intent(SplashActivity.this, WelcomeActivity.class);

                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
    }
}