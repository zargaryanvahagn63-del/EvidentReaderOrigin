package zargaryan.vahagn.evidentreader;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import zargaryan.vahagn.evidentreader.Models.User;

public class WelcomeActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    private void showRegWin() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Registration");
        dialog.setMessage("Please enter all the fields to register");
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_create_account, null);
        dialog.setView(view);

        final TextInputLayout name = view.findViewById(R.id.name);
        final TextInputLayout email = view.findViewById(R.id.email);
        final TextInputLayout password = view.findViewById(R.id.password);
        final TextInputLayout repeatPassword = view.findViewById(R.id.repeat_password);
        
        LinearLayout root = findViewById(R.id.main);

        dialog.setNegativeButton("Cancel", (dialogInterface, which) -> dialogInterface.dismiss());
        
        dialog.setPositiveButton("Create account", (dialogInterface, which) -> {
            if (TextUtils.isEmpty(name.getEditText().getText().toString())) {
                Snackbar.make(root, "Please enter your name", Snackbar.LENGTH_INDEFINITE).show();
                return;
            }
            if (TextUtils.isEmpty(email.getEditText().getText().toString())) {
                Snackbar.make(root, "Please enter your email", Snackbar.LENGTH_INDEFINITE).show();
                return;
            }
            if (!password.getEditText().getText().toString().equals(repeatPassword.getEditText().getText().toString()) ||
                    password.getEditText().getText().toString().length() < 8) {
                Snackbar.make(root, "Please enter your password correctly (min 8 chars)", Snackbar.LENGTH_INDEFINITE).show();
                return;
            }

            auth.createUserWithEmailAndPassword(email.getEditText().getText().toString().trim(),
                    password.getEditText().getText().toString()).addOnSuccessListener(authResult -> {
                User user = new User(name.getEditText().getText().toString(), email.getEditText().getText().toString(),
                        password.getEditText().getText().toString());
                users.child(auth.getCurrentUser().getUid()).setValue(user).addOnSuccessListener(unused -> {
                    Snackbar.make(root, "Account created successfully", Snackbar.LENGTH_INDEFINITE).show();
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                });
            }).addOnFailureListener(e -> Snackbar.make(root, "Registration failed: " + e.getMessage(), Snackbar.LENGTH_LONG).show());
        });

        dialog.show();
    }

    private void showSignInWin() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Signing in");
        dialog.setMessage("Please enter your email and password");
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_login_em, null);
        dialog.setView(view);

        final TextInputLayout email = view.findViewById(R.id.email);
        final TextInputLayout password = view.findViewById(R.id.password);
        
        LinearLayout root = findViewById(R.id.main);

        dialog.setNegativeButton("Cancel", (dialogInterface, which) -> dialogInterface.dismiss());
        
        dialog.setPositiveButton("Sign in", (dialogInterface, which) -> {
            if (TextUtils.isEmpty(email.getEditText().getText().toString())) {
                Snackbar.make(root, "Please enter your email", Snackbar.LENGTH_INDEFINITE).show();
                return;
            }
            if (TextUtils.isEmpty(password.getEditText().getText().toString()) ||
                    password.getEditText().getText().toString().length() < 8) {
                Snackbar.make(root, "Please enter your password", Snackbar.LENGTH_INDEFINITE).show();
                return;
            }
            
            auth.signInWithEmailAndPassword(email.getEditText().getText().toString().trim(),
                    password.getEditText().getText().toString()).addOnSuccessListener(authResult -> {
                Snackbar.make(root, "Signed in successfully", Snackbar.LENGTH_INDEFINITE).show();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }).addOnFailureListener(e -> Snackbar.make(root, "Signing in failed: " + e.getMessage(), Snackbar.LENGTH_LONG).show());
        });

        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        
        LinearLayout root = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button signInEm = findViewById(R.id.sign_in_em);
        Button signInGg = findViewById(R.id.sign_in_gg);
        Button crAcc = findViewById(R.id.cr_acc);
        Button signInFb = findViewById(R.id.sign_in_fb);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("users");

        crAcc.setOnClickListener(v -> showRegWin());
        signInEm.setOnClickListener(v -> showSignInWin());

        signInGg.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, SignInGgActivity.class);
            startActivity(intent);
        });

        signInFb.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, SignInFbActivity.class);
            startActivity(intent);
        });
    }
}