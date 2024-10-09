package com.example.groceryapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.groceryapp.R;
import com.example.groceryapp.ui.grocerydetail.GroceryDetailsActivity;
import com.example.groceryapp.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginButton, signupButton;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUser().observe(this, firebaseUser -> {
            if(firebaseUser != null){
                Intent intent = new Intent(LoginActivity.this, GroceryDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        userViewModel.getLoginFailed().observe(this, loginFailed -> {
            if(loginFailed){
                Toast.makeText(this, "Login failed, Check username and pass", Toast.LENGTH_SHORT).show();
            }
        });

        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            userViewModel.loginUser(email, password);
        });

        signupButton.setOnClickListener(v -> {
            Toast.makeText(this, "Sign up under implementation", Toast.LENGTH_SHORT).show();
        });
    }
}