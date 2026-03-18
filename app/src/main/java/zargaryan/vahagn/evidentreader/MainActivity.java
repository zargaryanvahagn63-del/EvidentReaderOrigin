package zargaryan.vahagn.evidentreader;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import zargaryan.vahagn.evidentreader.Models.User;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    private void showRegWin() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Registration");
        dialog.setMessage("Please enter your email and password");
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_create_account, null);
        dialog.setView(view);

        final MaterialEditText name = view.findViewById(R.id.name);
        final MaterialEditText email = view.findViewById(R.id.email);
        final MaterialEditText password = view.findViewById(R.id.password);
        final MaterialEditText repeatPassword = view.findViewById(R.id.repeat_password);
        CardView container = view.findViewById(R.id.Container);

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Create account", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    Snackbar.make(container, "Please enter your name", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(container, "Please enter your email", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (!password.getText().toString().equals(repeatPassword.getText().toString()) ||
                        password.getText().toString().length() < 8) {
                    Snackbar.make(container, "Please enter your password correctly", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email.getText().toString(),
                        password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User(name.getText().toString(), email.getText().toString(),
                                password.getText().toString());
                        users.child(user.getName()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Snackbar.make(container, "Account created successfully", Snackbar.LENGTH_SHORT).show();
                                // Intent intent = new Intent(MainActivity.this, MainBodyActivity.class);
                                // startActivity(intent);
                            }
                        });
                    }
                });
            }
        });

        dialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button signInEm = findViewById(R.id.sign_in_em);
        Button signInGg = findViewById(R.id.sign_in_gg);
        Button crAcc = findViewById(R.id.cr_acc);
        Button signInFb = findViewById(R.id.sign_in_fb);
        TextView anApp = findViewById(R.id.an_app);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("users");

        crAcc.setOnClickListener(v -> {
            showRegWin();
        });

        signInEm.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginEmActivity.class);
            startActivity(intent);
        });


        signInGg.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignInGgActivity.class);
            startActivity(intent);
        });

        signInFb.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignInFbActivity.class);
            startActivity(intent);
        });

        /* anApp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainBodyActivity.class);
            startActivity(intent);
        }); */



    }
}