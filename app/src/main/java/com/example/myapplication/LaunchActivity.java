package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;


import android.content.Intent;
import android.widget.Button;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;

import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

/**
 * sb.
 */
public class LaunchActivity extends AppCompatActivity {

    /**
     * Request code for sign in.
     */
    private static final int SIGNIN = 0;
    /**
     * FirebaseUser.
     */
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    /**
     * Create Signin intent.
     */
    public void createSignInIntent() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(), SIGNIN);
    }

    /**
     * on Activity result.
     * @param requestCode - requestcode.
     * @param resultCode - resultcode.
     * @param data - data.
     */
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGNIN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                user = FirebaseAuth.getInstance().getCurrentUser();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    /**
     * onCreate function.
     */
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            createSignInIntent();
        }
        Button label = findViewById(R.id.Login);
        label.setOnClickListener(v -> createSignInIntent());
    }
}
