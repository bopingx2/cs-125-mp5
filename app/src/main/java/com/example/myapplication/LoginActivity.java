package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;


/**
 * Represents the launcher activity that ensures the user is logged in.
 */
public class LoginActivity extends AppCompatActivity {

    /** Request code. */
    private static final int RC = 1;
    /** User. */
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    /**
     * .
     */
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            String email = user.getEmail();
            intent.putExtra("user", email);
            startActivity(intent);
            finish();
        } else {
            createSignInIntent();
        }
        Button button = findViewById(R.id.button5);
        button.setOnClickListener(v -> createSignInIntent());
    }

    /**
     * Create sign in intent.
     */
    public void createSignInIntent() {
        List<AuthUI.IdpConfig> ways = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build());
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(ways).build(), RC);
    }

    /**
     * result.
     * @param requestCode rc.
     * @param resultCode rc.
     * @param data rc.
     */
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                user = FirebaseAuth.getInstance().getCurrentUser();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        }
    }
}
