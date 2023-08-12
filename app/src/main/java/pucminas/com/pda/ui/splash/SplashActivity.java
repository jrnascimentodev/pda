package pucminas.com.pda.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import pucminas.com.pda.R;
import pucminas.com.pda.database.AppDataBase;
import pucminas.com.pda.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    public static AppDataBase appDataBase;
    private Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        bLogin = findViewById(R.id.bLogin);

        bLogin.setOnClickListener(view -> {

            // Inicia a activity login
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });
    }
}